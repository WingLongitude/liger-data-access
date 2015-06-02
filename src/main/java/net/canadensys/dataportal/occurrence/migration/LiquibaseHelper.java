package net.canadensys.dataportal.occurrence.migration;

import java.sql.Connection;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

/**
 * Helper class for Liquibase scripts to apply on occurrences tables structure.
 * 
 * @author cgendreau
 *
 */
public class LiquibaseHelper {

	/**
	 * Method use to apply the latest (master) migration script on a connection.
	 * Scripts for 'public' and 'buffer' schema will be applied.
	 * 
	 * @param connection
	 * @throws LiquibaseException
	 */
	public static void migrate(Connection connection) throws LiquibaseException {
		Liquibase liquibase;
		Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

		// set initial state
		liquibase = new Liquibase(LiquibaseConfig.PUBLIC_INITIAL_SCRIPT, new ClassLoaderResourceAccessor(), database);
		liquibase.changeLogSync(LiquibaseConfig.NO_CONTEXT, LiquibaseConfig.NO_LABEL);
		liquibase = new Liquibase(LiquibaseConfig.BUFFER_INITIAL_SCRIPT, new ClassLoaderResourceAccessor(), database);
		liquibase.changeLogSync(LiquibaseConfig.BUFFER_CONTEXT, LiquibaseConfig.NO_LABEL);

		// Test public master script
		liquibase = new Liquibase(LiquibaseConfig.PUBLIC_MASTER_SCRIPT, new ClassLoaderResourceAccessor(), database);
		liquibase.update((String) null);

		// Test buffer
		liquibase = new Liquibase(LiquibaseConfig.BUFFER_MASTER_SCRIPT, new ClassLoaderResourceAccessor(), database);
		liquibase.update(LiquibaseConfig.BUFFER_CONTEXT);

	}

}
