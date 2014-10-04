/**
 * 
 */
package com.sutherland.helios.database.connection.SQL;

import com.sutherland.helios.database.connection.DatabaseConnectionFactory;
import com.sutherland.helios.exceptions.DatabaseConnectionCreationException;


/**
 * @author Jason Diamond
 *
 */
public class ConnectionFactory extends DatabaseConnectionFactory 
{
	private final String URL_PARAM = "url";
	private final String DRIVER_PARAM = "driver";
	private final String USER_PARAM = "user";
	private final String PASS_PARAM = "pass";

	/**
	 * 
	 */
	public ConnectionFactory() 
	{
		super();
		requiredParameters = new String[]{URL_PARAM, DRIVER_PARAM, USER_PARAM, PASS_PARAM};

	}

	/* (non-Javadoc)
	 * @see database.connection.DatabaseConnectionFactory#getConnection()
	 */
	@Override
	public RemoteConnection getConnection()  throws DatabaseConnectionCreationException
	{
		//build a remote connection and return it

		RemoteConnection con = null;

		if(hasValidParams())
		{
			con = new RemoteConnection
			(
				parameters.get(URL_PARAM),
				parameters.get(USER_PARAM),
				parameters.get(PASS_PARAM),
				parameters.get(DRIVER_PARAM)
			);
		}
		else
		{
			throw new DatabaseConnectionCreationException("Invalid Parameters for connection creation");
		}

		return con;
	}
}
