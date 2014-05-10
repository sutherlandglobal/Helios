package helios.database.connection;

import helios.exceptions.DatabaseConnectionCreationException;
import helios.exceptions.LoggerCreationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Abstract connection to a datastore. In practice, most likely a database. 
 * Some basic functionality is implemented here, specifically aggregating queries and returning a single resultset.
 * 
 * @author Jason Diamond
 *
 */
public abstract class DatabaseConnection 
{
	protected String dbType;
	protected String name;
	protected String errorMessage;
	protected Map<String, String> statistics;

	/**
	 * Hollow constructor for a parent database connection.
	 * @throws DatabaseConnectionCreationException 
	 * 
	 * @throws LoggerCreationException 
	 * 
	 */
	protected DatabaseConnection() throws DatabaseConnectionCreationException 
	{
		errorMessage = null;
		statistics = Collections.synchronizedMap(new LinkedHashMap<String, String>());
	}

	/**
	 *	Close any database connections opened by children.
	 */
	public abstract void close();


	public String getErrorMessage()
	{
		return errorMessage;
	}
	
	public Map<String,String> getStatistics()
	{
		return statistics;
	}
	
	/**
	 * Run a query and return the results. Implementation will depend on the particular database.
	 * 
	 * @param query	Query to run.
	 * @return	The results of this query.
	 */
	public abstract ArrayList<String[]> runQuery(String query);


	/**
	 * Run several queries in parallel, and return the results from each query.
	 * 
	 * @param queries	A list of queries to run.
	 * 
	 * @return	Mapping of query -> result set.
	 */
	public abstract Map<String, ArrayList<String[]>> runParallelQueries(ArrayList<String> queries);


	/*
	 *
	 */
	public abstract ArrayList<String> getSchemaInfo(String tableName);

	/**
	 * Attempt to establish a connection to the database. Implementation will depend on the particular database.
	 * 
	 * @return	True if the connection was successful, false otherwise.
	 */
	protected abstract boolean setupConnection();

}
