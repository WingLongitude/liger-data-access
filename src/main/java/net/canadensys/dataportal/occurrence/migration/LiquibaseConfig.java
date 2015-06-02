package net.canadensys.dataportal.occurrence.migration;

import liquibase.Contexts;
import liquibase.LabelExpression;

/**
 * Configuration for liquibase
 * 
 * @author cgendreau
 *
 */
public class LiquibaseConfig {

	public static final String PUBLIC_MASTER_SCRIPT = "script/liquibase/publicChangeLogFile-master.xml";
	public static final String BUFFER_MASTER_SCRIPT = "script/liquibase/bufferChangeLogFile-master.xml";

	public static final String PUBLIC_INITIAL_SCRIPT = "script/liquibase/publicChangeLogFile-initial.xml";
	public static final String BUFFER_INITIAL_SCRIPT = "script/liquibase/bufferChangeLogFile-initial.xml";

	public static final Contexts NO_CONTEXT = new Contexts();
	public static final Contexts BUFFER_CONTEXT = new Contexts("buffer");
	public static final LabelExpression NO_LABEL = new LabelExpression();

}
