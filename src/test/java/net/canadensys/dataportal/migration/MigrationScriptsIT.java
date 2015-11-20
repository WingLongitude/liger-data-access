package net.canadensys.dataportal.migration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import liquibase.changelog.ChangeSet;
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

			assertFalse(LiquibaseHelper.hasLiquibaseAlreadyRun(conn));

			// ensure we have some changes to apply
			List<ChangeSet> changeSet = LiquibaseHelper.listUnrunPublicChangeSets(conn);
			assertFalse(changeSet.isEmpty());
			
	     // ensure we have some changes to apply on buffer schema
			List<ChangeSet> bufferChangeSet = LiquibaseHelper.listUnrunBufferChangeSets(conn);
			assertFalse(bufferChangeSet.isEmpty());

			// apply migration
			LiquibaseHelper.migrate(conn);

			// ensure we have no more changes to apply
			changeSet = LiquibaseHelper.listUnrunPublicChangeSets(conn);
			assertTrue(changeSet.isEmpty());
			bufferChangeSet = LiquibaseHelper.listUnrunBufferChangeSets(conn);
			assertTrue(bufferChangeSet.isEmpty());

			assertTrue(LiquibaseHelper.hasLiquibaseAlreadyRun(conn));

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
