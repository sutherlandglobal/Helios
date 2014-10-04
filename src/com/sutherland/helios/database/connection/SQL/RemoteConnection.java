/**
 * 
 */
package com.sutherland.helios.database.connection.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sutherland.helios.database.connection.DatabaseConnection;
import com.sutherland.helios.exceptions.DatabaseConnectionCreationException;
import com.sutherland.helios.exceptions.ExceptionFormatter;
import com.sutherland.helios.util.threading.ThreadPool;


/**
 * Connect to an SQL database, be it MySQL, MS SQL, etc. Just make sure you specify the driver to use.
 * 
 * @author Jason Diamond
 *
 */
public class RemoteConnection extends DatabaseConnection
{

	private String url;
	private String password;
	private String userName;
	private String driverClassName;

	private Connection con;
	//private final int QUERY_POOL_SIZE = 20;

	/**
	 * Build a connection to the database with the supplied url, credentials and driver.
	 * 
	 * @param url	URL of the database.
	 * @param userName	Username to authenticate with.
	 * @param password	Password to authenticate with.
	 * @param driverClassName	Driver to use to build the connection.
	 * 
	 * @throws DatabaseConnectionCreationException	If a connection to the database could not be established.
	 */
	public RemoteConnection(String url, String userName, String password, String driverClassName) throws DatabaseConnectionCreationException
	{
		super();

		this.url = url;
		this.userName = userName;
		this.password = password;
		this.driverClassName = driverClassName;

		dbType = "SQL";
		name = url;

		//guaranteed we want to setup the connection automatically
		if(!setupConnection())
		{
			throw new DatabaseConnectionCreationException("Could not build a DB connection");
		}
	}

	/**
	 *	Close any database connections opened by children.
	 *
	 * @see com.sutherland.helios.database.connection.DatabaseConnection#close()
	 */
	@Override
	public void close()
	{
		try
		{
			if(!con.isClosed())
			{
				con.close();
			}
		} 
		catch (SQLException e)
		{
			errorMessage = ExceptionFormatter.asString(e);
		}
	}

	/**
	 * Run a query and return the results. Implementation will depend on the particular database.
	 * 
	 * @param query	Query to run.
	 *
	 * @return	The results of this query.
	 *
	 * @see com.sutherland.helios.database.connection.DatabaseConnection#runQuery(java.lang.String)
	 */
	@Override
	public ArrayList<String[]> runQuery(String query)
	{
		//final List<String[]> queryResults = Collections.synchronizedList(new Vector<String[]>());
		ArrayList<String[]> queryResults = null; 

		Statement s = null;

		try
		{
			//these values get wiped if we assign the resultset directly from the query
			s = con.createStatement
					(				
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE
							);

			long queryStart = System.currentTimeMillis();
			s.executeQuery(query);
			long queryEnd = System.currentTimeMillis();

			long resultsStart =  queryEnd;
			//final ResultSet rs = s.executeQuery(query);


			final ResultSet rs = s.getResultSet();

			rs.last();
			queryResults = new ArrayList<String[]>(rs.getRow());
			rs.beforeFirst();

			try
			{					
				while (rs.next())
				{

					final String[] temp = new String[rs.getMetaData().getColumnCount()];

					//resultset is not parallel so we can't populate the rows in parallel
					for(int j=1; j<= temp.length; j++)
					{
						temp[j-1] = rs.getString(j);
					}

					queryResults.add(temp);
				}

				long resultsEnd =  System.currentTimeMillis();

				statistics.put(query, "Query returned rows: " + queryResults.size() + "; Query time: " + (queryEnd - queryStart) + " ms; Results time: " + (resultsEnd - resultsStart) + " ms");
			}
			catch (SQLException e)
			{
				throw e;
			}
			finally
			{
				try
				{
					rs.close();
				} 
				catch (SQLException e)
				{
					errorMessage = ExceptionFormatter.asString(e);
				}

				try
				{
					s.close();
				} 
				catch (SQLException e)
				{
					errorMessage = ExceptionFormatter.asString(e);
				}

				//leave the connection open because we might have more queries to do
			}
		} 
		catch (SQLException e1)
		{
			errorMessage = ExceptionFormatter.asString(e1);
		}

		return queryResults;
	}

	/**
	 * Run several queries in parallel, and return the results from each query.
	 * 
	 * @param queries	A list of queries to run.
	 * 
	 * @return	Mapping of query -> result set.
	 */
	@Override
	public Map<String, ArrayList<String[]>> runParallelQueries(ArrayList<String> queries)
	{

		ThreadPool tp = new ThreadPool(queries.size());

		final List<RemoteConnection> workers = Collections.synchronizedList(new ArrayList<RemoteConnection>(queries.size()));

		for(int i =0; i<queries.size();i++)
		{
			workers.add(null);
		}

		final Map<String, ArrayList<String[]>> retval = Collections.synchronizedMap(new HashMap<String, ArrayList<String[]>>(queries.size()));

		int index = 0;
		for(final String query : queries)
		{
			final int i = index;
			index++;

			tp.runTask
			(
					new Runnable()
					{
						@Override
						public void run() 
						{
							try
							{
								final RemoteConnection workerConnection = new RemoteConnection(url, userName, password, driverClassName);
								workers.set(i, workerConnection);

								retval.put(query, workerConnection.runQuery(query) );
							} 
							catch (DatabaseConnectionCreationException e)
							{
								errorMessage = ExceptionFormatter.asString(e);
							}
							finally
							{
								if(workers.get(i) != null)
								{
									workers.get(i).close();
								}
							}
						}
					}
					);
		}

		tp.close();

		return retval;
	}

	/**
	 * Attempt to establish a connection to the database. Implementation will depend on the particular database.
	 * 
	 * @return	True if the connection was successful, false otherwise.
	 *
	 * @see com.sutherland.helios.database.connection.DatabaseConnection#setupConnection()
	 */
	@Override
	protected boolean setupConnection()
	{
		boolean retval = false;
		try
		{
			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, userName, password);

			retval = (con != null);
		} 
		catch (Exception e)
		{
			errorMessage = ExceptionFormatter.asString(e);
		}

		return retval;
	}


	/** 
	 * Return a remote SQL database's schema information.
	 * 
	 * @param tableName		The table to retrieve schema information for.
	 * 
	 * @return The schema information for the given table.
	 * 
	 * @see com.sutherland.helios.database.connection.DatabaseConnection#getSchemaInfo(java.lang.String)
	 */
	public ArrayList<String> getSchemaInfo(String tableName)
	{
		ArrayList<String> schemaInfo = null; 

		//get a row, read its metadata

		Statement s = null;
		ResultSet rs = null;
		ResultSetMetaData metaData = null;
		try
		{
			//these values get wiped if we assign the resultset directly from the query
			s = con.createStatement
					(				
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE
							);

			String query = "SELECT * FROM " + tableName + " limit 1";


			s.executeQuery(query);


			rs = s.getResultSet();
			metaData = rs.getMetaData();

			schemaInfo = new ArrayList<String>();

			for(int j=1; j<= metaData.getColumnCount(); j++)
			{
				schemaInfo.add(metaData.getColumnTypeName(j));
			}

		}
		catch (SQLException e)
		{
			errorMessage = ExceptionFormatter.asString(e);
		}
		finally
		{
			if(rs != null) 
			{
				try
				{
					rs.close();
				} 
				catch (SQLException e)
				{
					errorMessage = ExceptionFormatter.asString(e);
				}
			}

			if(s != null) 
			{
				try
				{
					s.close();
				} 
				catch (SQLException e)
				{
					errorMessage = ExceptionFormatter.asString(e);
				}
			}

			//leave the connection open because we might have more queries to do
		}

		return schemaInfo;
	}
}
