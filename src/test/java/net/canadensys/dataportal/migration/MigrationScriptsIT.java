package net.canadensys.dataportal.migration;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * WORK IN PROGRESS
 * Integration test that takes the initial script and apply liquibase scripts.
 * 
 * @author cgendreau
 *
 */
public class MigrationScriptsIT {

	private static final String PUBLIC_MASTER_SCRIPT = "script/liquibase/publicChangeLogFile-master.xml";
	private static final String BUFFER_MASTER_SCRIPT = "script/liquibase/bufferChangeLogFile-master.xml";

	private static final String PUBLIC_INITIAL_SCRIPT = "script/liquibase/publicChangeLogFile-initial.xml";
	private static final String BUFFER_INITIAL_SCRIPT = "script/liquibase/bufferChangeLogFile-initial.xml";

	private static final Contexts NO_CONTEXT = new Contexts();
	private static final LabelExpression NO_LABEL = new LabelExpression();

	@Test
	public void testScripts() {

		DataSource ds = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("classpath:h2/h2setup.sql")
				.addScript("/script/occurrence/create_occurrence_tables.sql")
				.addScript("/script/occurrence/create_occurrence_tables_buffer_schema.sql").build();
		Connection conn;
		Liquibase liquibase;

		try {
			conn = ds.getConnection();

			Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));

			// set initial state
			liquibase = new Liquibase(PUBLIC_INITIAL_SCRIPT, new ClassLoaderResourceAccessor(), database);
			liquibase.changeLogSync(NO_CONTEXT, NO_LABEL);
			liquibase = new Liquibase(BUFFER_INITIAL_SCRIPT, new ClassLoaderResourceAccessor(), database);
			liquibase.changeLogSync(NO_CONTEXT, NO_LABEL);

			// Test public master script
			liquibase = new Liquibase(PUBLIC_MASTER_SCRIPT, new ClassLoaderResourceAccessor(), database);
			liquibase.update((String) null);

			// Test buffer
			liquibase = new Liquibase(BUFFER_MASTER_SCRIPT, new ClassLoaderResourceAccessor(), database);
			liquibase.update("buffer");

			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
		catch (DatabaseException e) {
			e.printStackTrace();
			fail();
		}
		catch (LiquibaseException e) {
			e.printStackTrace();
			fail();
		}

	}
}
