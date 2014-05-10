package helios.bucket;

import helios.data.Aggregation;
import helios.database.connection.DatabaseConnection;
import helios.roster.Roster;

/**
 * A container for various buckets to hold database query results. A database row really ought to have three fields, a date, a userid, and a datum.
 * 
 * @author Jason Diamond
 *
 */
public class Bucketizer 
{

	//teams for user, date, team
	
	private Aggregation users;
	private Aggregation dates;
	
	private final String DATA_ATTR = "data";
	
	public Bucketizer()
	{
		users = new Aggregation();
		dates = new Aggregation();
	}
	
	public Aggregation getUsers() 
	{
		return users;
	}

	public Aggregation getDates() 
	{
		return dates;
	}
	
	public void processQuery(DatabaseConnection dbc, String query, Roster roster)
	{
		//col1 is date, col2 is userid, col3 is datum
		
		String userID, datum;
		for(String[] row:  dbc.runQuery(query))
		{
			userID = row[1];
			if(roster.getUser(userID) != null)
			{
				datum = row[2];

				
				roster.getUser(userID).addAttribute(DATA_ATTR);
				roster.getUser(userID).addData(DATA_ATTR, datum);
			
			}
		}
	}
	
	public void reset()
	{
		users.clear();
		dates.clear();
	}
}
