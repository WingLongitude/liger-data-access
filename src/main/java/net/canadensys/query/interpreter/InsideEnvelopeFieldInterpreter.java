package net.canadensys.query.interpreter;

import java.util.ArrayList;
import java.util.List;

import net.canadensys.databaseutils.PostgisUtils;
import net.canadensys.dataportal.occurrence.map.MapUtils;
import net.canadensys.query.QueryOperatorEnum;
import net.canadensys.query.SearchQueryPart;
import net.canadensys.query.SearchableField;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;

/**
 * Interprets a SearchQueryPart representing a geospatial query where we target an
 * envelope. Envelope is defined by 2 coordinates: minimum and maximum lat,lng
 * 
 * @author canadensys
 * 
 */
public class InsideEnvelopeFieldInterpreter extends InsidePolygonFieldInterpreter {

	// get log4j handler
	private static final Logger LOGGER = Logger.getLogger(InsideEnvelopeFieldInterpreter.class);

	public static final int GEOM_FIELD_IDX = 0;
	public static final int SHIFTED_GEOM_FIELD_IDX = 1;

	/**
	 * Details: Check if we have exactly 2 elements in value list (envelope is defined by 2 points)
	 * 
	 * @param searchQueryPart
	 * @return
	 */
	@Override
	public boolean canHandleSearchQueryPart(SearchQueryPart searchQueryPart) {

		if (!canHandleSearchableField(searchQueryPart.getSearchableField()) || !QueryOperatorEnum.IN.equals(searchQueryPart.getOp())
				|| searchQueryPart.getValueList().size() != 2) {
			LOGGER.debug("Can NOT handle SearchQueryPart.");
			return false;
		}

		// get the parsed value of the first SearchableField only (e.g. the_geom)
		String searchableFieldKey = searchQueryPart.getSearchableField().getRelatedFields().get(GEOM_FIELD_IDX);
		return validateParsedValue(searchQueryPart, searchableFieldKey);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toSQL(SearchQueryPart searchQueryPart) {

		if (!canHandleSearchQueryPart(searchQueryPart)) {
			LOGGER.error("Can't handle QueryPart : " + searchQueryPart);
			return null;
		}

		List<Pair<String, String>> envelope = new ArrayList<Pair<String, String>>();
		SearchableField searchableField = searchQueryPart.getSearchableField();
		String geomColumn = searchableField.getRelatedFields().get(GEOM_FIELD_IDX);

		List<String> valueList = searchQueryPart.getValueList();
		Object parsedValue = null;
		double lngEast, lngWest;

		// North East point
		parsedValue = searchQueryPart.getParsedValue(valueList.get(0), geomColumn);
		envelope.add((Pair<String, String>) parsedValue);
		lngEast = NumberUtils.toDouble(((Pair<String, String>) parsedValue).getRight(), 0);

		// South West point
		parsedValue = searchQueryPart.getParsedValue(valueList.get(1), geomColumn);
		envelope.add((Pair<String, String>) parsedValue);
		lngWest = NumberUtils.toDouble(((Pair<String, String>) parsedValue).getRight(), 0);

		// If the bbox is crossing the IDL, we use the shiftedGeomColumn.
		if (MapUtils.isBBoxCrossingIDL(lngEast, lngWest)) {
			String shiftedGeomColumn = searchableField.getRelatedFields().get(SHIFTED_GEOM_FIELD_IDX);
			return PostgisUtils.getInsideEnvelopeSQLClause(shiftedGeomColumn, envelope, true);
		}
		return PostgisUtils.getInsideEnvelopeSQLClause(geomColumn, envelope, false);
	}

}
