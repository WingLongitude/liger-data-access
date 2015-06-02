package net.canadensys.dataportal.migration;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import net.canadensys.dataportal.occurrence.migration.LiquibaseHelper;

import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * Integration test that takes the initial script and apply liquibase scripts.
 * 
 * @author cgendreau
 *
 */
public class MigrationScriptsIT {

	@Test
	public void testScripts() {

		DataSource ds = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("classpath:h2/h2setup.sql")
				.addScript("/script/occurrence/create_occurrence_tables.sql")
				.addScript("/script/occurrence/create_occurrence_tables_buffer_schema.sql").build();
		Connection conn;
		try {
			conn = ds.getConnection();
			LiquibaseHelper.migrate(conn);
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
