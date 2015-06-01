package net.canadensys.dataportal.occurrence.dwc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gbif.dwc.terms.Term;
import org.gbif.dwca.io.Archive;
import org.gbif.dwca.io.ArchiveField;
import org.gbif.dwca.io.ArchiveFile;
import org.gbif.dwca.io.MetaDescriptorWriter;
import org.gbif.io.TabWriter;

import com.google.common.collect.Maps;

/**
 * WARNING: before using this class for new project consider using GBIF dwca-io library directly.
 * 
 * DarwinCore archive writer that handles headers declaration.
 * The declaration will allow to specify the order of the columns and will also avoid a second
 * writing of the archive to fix missing data when a new column is 'discovered' in a later row.
 * Written from GBIF DwcaWriter with no EML file.
 * 
 * @author canadensys
 * 
 */
public class FixedHeadersDwcaWriter {

	private File dir;
	private long recordNum;
	private String coreId;
	private Map<Term, String> coreRow;
	private final Term coreRowType;
	private Map<Term, TabWriter> writers = new HashMap<Term, TabWriter>();
	private Map<Term, String> dataFileNames = new HashMap<Term, String>();
	private Map<Term, List<Term>> terms = new HashMap<Term, List<Term>>();

	/**
	 * @param coreRowType
	 *            the core row type.
	 * @param dir
	 *            the directory to create the archive in.
	 */
	public FixedHeadersDwcaWriter(Term coreRowType, List<Term> coreTermList, File dir) throws IOException {
		this.dir = dir;
		this.coreRowType = coreRowType;
		addRowType(coreRowType, coreTermList);
	}

	public static String dataFileName(Term rowType) {
		return rowType.simpleName().toLowerCase() + ".txt";
	}

	private void addRowType(Term rowType, List<Term> conceptTermList) throws IOException {
		if (conceptTermList != null) {
			terms.put(rowType, conceptTermList);
		}
		else {
			terms.put(rowType, new ArrayList<Term>());
		}

		String dfn = dataFileName(rowType);
		dataFileNames.put(rowType, dfn);
		File df = new File(dir, dfn);
		org.apache.commons.io.FileUtils.forceMkdir(df.getParentFile());
		OutputStream out = new FileOutputStream(df);
		TabWriter wr = new TabWriter(out);
		writers.put(rowType, wr);

		// write headers
		writeHeaders(rowType);
	}

	public void newRecord(String id) throws IOException {
		// flush last record
		flushLastCoreRecord();
		// start new
		recordNum++;
		coreId = id;
		if (coreRow == null) {
			coreRow = new HashMap<Term, String>();
		}
		else {
			coreRow.clear();
		}
	}

	private void flushLastCoreRecord() throws IOException {
		if (coreRow != null && coreId != null) {
			writeRow(coreRow, coreRowType);
		}
	}

	public long getRecordsWritten() {
		return recordNum;
	}

	private void writeRow(Map<Term, String> rowMap, Term rowType) throws IOException {
		TabWriter writer = writers.get(rowType);
		List<Term> coreTerms = terms.get(rowType);
		String[] row = new String[coreTerms.size() + 1];
		row[0] = coreId;
		for (Term term : rowMap.keySet()) {
			int column = 1 + coreTerms.indexOf(term);
			row[column] = rowMap.get(term);
		}
		writer.write(row);
	}

	public void addCoreColumn(Term term, String value) {
		List<Term> coreTerms = terms.get(coreRowType);
		if (!coreTerms.contains(term)) {
			coreTerms.add(term);
		}
		coreRow.put(term, value);
	}

	/**
	 * @return new map of all current data file names by their rowTypes.
	 */
	public Map<Term, String> getDataFiles() {
		return Maps.newHashMap(dataFileNames);
	}

	public void addExtensionRecord(Term rowType, Map<Term, String> row) throws IOException {
		// make sure we know the extension rowtype
		if (!terms.containsKey(rowType)) {
			addRowType(rowType, null);
		}
		// make sure we know all terms
		List<Term> knownTerms = terms.get(rowType);
		for (Term term : row.keySet()) {
			if (!knownTerms.contains(term)) {
				knownTerms.add(term);
			}
		}
		// write extension record
		writeRow(row, rowType);
	}

	/**
	 * writes meta.xml and eml.xml to the archive and closes tab writers.
	 */
	public void finalize() throws IOException {
		addMeta();
		// flush last record
		flushLastCoreRecord();

		// close writers
		for (TabWriter w : writers.values()) {
			w.close();
		}
	}

	private void addMeta() throws IOException {
		File metaFile = new File(dir, "meta.xml");

		Archive arch = new Archive();
		arch.setCore(buildArchiveFile(arch, coreRowType));
		for (Term rowType : this.terms.keySet()) {
			if (!coreRowType.equals(rowType)) {
				arch.addExtension(buildArchiveFile(arch, rowType));
			}
		}
		try {
			MetaDescriptorWriter.writeMetaFile(metaFile, arch);
		}
		catch (IOException ioEx) {
			throw new IOException("Meta.xml template exception: " + ioEx.getMessage(), ioEx);
		}
	}

	private void writeHeaders(Term rowType) throws IOException {
		TabWriter writer = writers.get(rowType);
		List<Term> coreTerms = terms.get(rowType);
		String[] row = new String[coreTerms.size() + 1];
		int i = 1;
		row[0] = "id";
		for (Term term : coreTerms) {
			row[i] = term.simpleName();
			i++;
		}
		writer.write(row);
	}

	private ArchiveFile buildArchiveFile(Archive archive, Term rowType) {
		ArchiveFile af = ArchiveFile.buildTabFile();
		af.setArchive(archive);
		af.addLocation(dataFileNames.get(rowType));

		af.setEncoding("utf-8");
		// do not ignore header line
		af.setIgnoreHeaderLines(1);
		af.setRowType(rowType);

		ArchiveField id = new ArchiveField();
		id.setIndex(0);
		af.setId(id);

		int idx = 0;
		for (Term c : this.terms.get(rowType)) {
			idx++;
			ArchiveField field = new ArchiveField();
			field.setIndex(idx);
			field.setTerm(c);
			af.addField(field);
		}

		return af;
	}

}
