/**
 * 
 */
package com.sutherland.helios.roster.test;

import java.util.ArrayList;

import com.sutherland.helios.data.Datum;
import com.sutherland.helios.exceptions.ReportSetupException;
import com.sutherland.helios.roster.Roster;
import com.sutherland.helios.roster.attributes.BasicRosterAttributes;

/**
 * Test Class for Helios roster structure. Testing focus is user accessibility.
 * 
 * @author Jason Diamond
 *
 */
public class MockRoster extends Roster implements BasicRosterAttributes
{
	private final static String DERPID_ATTR = "derpid";
	private final static String EMPID_ATTR = "empid";
	private final static String LNAME_ATTR = "lname";
	private final static String FNAME_ATTR = "fname";

	/**
	 * @throws ReportSetupException
	 */
	public MockRoster() throws ReportSetupException 
	{
		super();
	}

	/* (non-Javadoc)
	 * @see com.sutherland.helios.roster.Roster#setupDataSourceConnections()
	 */
	@Override
	protected boolean setupDataSourceConnections() 
	{
		return true;
	}

	/* (non-Javadoc)
	 * @see com.sutherland.helios.roster.Roster#setupReport()
	 */
	@Override
	protected boolean setupReport() 
	{
		reportName = "Mock Roster";
		reportDesc = "Mockup of a simulated roster.";
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.sutherland.helios.roster.Roster#setupLogger()
	 */
	@Override
	protected boolean setupLogger() 
	{
		boolean retval = false;
		
		try
		{
			//setLoggerHandle(Constants.JUNIT_LOGGING_HANDLE);
			retval = true;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return retval;
	}
	
	public String getUnits()
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sutherland.helios.roster.Roster#getFullName(java.lang.String)
	 */
	@Override
	public String getFullName(String userID) 
	{
		String retval = null;
		
		Datum user = getUser(userID);
		
		if(user!= null)
		{
			retval = user.getAttributeData(LNAME_ATTR).get(0) + ":" + user.getAttributeData(FNAME_ATTR).get(0);
		}
		
		return retval;
	}

	/* (non-Javadoc)
	 * @see com.sutherland.helios.roster.Roster#loadData()
	 */
	@Override
	public ArrayList<String[]> loadData() 
	{		
		Datum user1 = new Datum("001");
		Datum user2 = new Datum("002");
		Datum user3 = new Datum("003");
		Datum user4 = new Datum("004");
		Datum user5 = new Datum("005");
		Datum user6 = new Datum("006");
		Datum user7 = new Datum("007");
		Datum user8 = new Datum("008");
		Datum user9 = new Datum("009");
		
		user1.addAttribute(USERID_ATTR);
		user2.addAttribute(USERID_ATTR);
		user3.addAttribute(USERID_ATTR);
		user4.addAttribute(USERID_ATTR);
		user5.addAttribute(USERID_ATTR);
		user6.addAttribute(USERID_ATTR);
		user7.addAttribute(USERID_ATTR);
		user8.addAttribute(USERID_ATTR);
		user9.addAttribute(USERID_ATTR);
		
		user1.addData(USERID_ATTR, "001");
		user2.addData(USERID_ATTR, "002");
		user3.addData(USERID_ATTR, "003");
		user4.addData(USERID_ATTR, "004");
		user5.addData(USERID_ATTR, "005");
		user6.addData(USERID_ATTR, "006");
		user7.addData(USERID_ATTR, "007");
		user8.addData(USERID_ATTR, "008");
		user9.addData(USERID_ATTR, "009");
		
		user1.addAttribute(DERPID_ATTR);
		user2.addAttribute(DERPID_ATTR);
		user3.addAttribute(DERPID_ATTR);
		user4.addAttribute(DERPID_ATTR);
		user5.addAttribute(DERPID_ATTR);
		user6.addAttribute(DERPID_ATTR);
		user7.addAttribute(DERPID_ATTR);
		user8.addAttribute(DERPID_ATTR);
		user9.addAttribute(DERPID_ATTR);
		
		user1.addData(DERPID_ATTR, "101");
		user2.addData(DERPID_ATTR, "202");
		user3.addData(DERPID_ATTR, "303");
		user4.addData(DERPID_ATTR, "404");
		user5.addData(DERPID_ATTR, "505");
		user6.addData(DERPID_ATTR, "606");
		user7.addData(DERPID_ATTR, "707");
		user8.addData(DERPID_ATTR, "808");
		user9.addData(DERPID_ATTR, "909");
		
		user1.addAttribute(EMPID_ATTR);
		user2.addAttribute(EMPID_ATTR);
		user3.addAttribute(EMPID_ATTR);
		user4.addAttribute(EMPID_ATTR);
		user5.addAttribute(EMPID_ATTR);
		user6.addAttribute(EMPID_ATTR);
		user7.addAttribute(EMPID_ATTR);
		user8.addAttribute(EMPID_ATTR);
		user9.addAttribute(EMPID_ATTR);
		
		user1.addData(EMPID_ATTR, "901");
		user2.addData(EMPID_ATTR, "802");
		user3.addData(EMPID_ATTR, "703");
		user4.addData(EMPID_ATTR, "604");
		user5.addData(EMPID_ATTR, "505");
		user6.addData(EMPID_ATTR, "406");
		user7.addData(EMPID_ATTR, "307");
		user8.addData(EMPID_ATTR, "208");
		user9.addData(EMPID_ATTR, "109");

		user1.addAttribute(LNAME_ATTR);
		user2.addAttribute(LNAME_ATTR);
		user3.addAttribute(LNAME_ATTR);
		user4.addAttribute(LNAME_ATTR);
		user5.addAttribute(LNAME_ATTR);
		user6.addAttribute(LNAME_ATTR);
		user7.addAttribute(LNAME_ATTR);
		user8.addAttribute(LNAME_ATTR);
		user9.addAttribute(LNAME_ATTR);
		
		user1.addData(LNAME_ATTR, "Kane");
		user2.addData(LNAME_ATTR, "Datzyuk");
		user3.addData(LNAME_ATTR, "Malkin");
		user4.addData(LNAME_ATTR, "Messier");
		user5.addData(LNAME_ATTR, "Gretzky");
		user6.addData(LNAME_ATTR, "Howe");
		user7.addData(LNAME_ATTR, "Sakic");
		user8.addData(LNAME_ATTR, "Modano");
		user9.addData(LNAME_ATTR, "Hull");
		
		user1.addAttribute(FNAME_ATTR);
		user2.addAttribute(FNAME_ATTR);
		user3.addAttribute(FNAME_ATTR);
		user4.addAttribute(FNAME_ATTR);
		user5.addAttribute(FNAME_ATTR);
		user6.addAttribute(FNAME_ATTR);
		user7.addAttribute(FNAME_ATTR);
		user8.addAttribute(FNAME_ATTR);
		user9.addAttribute(FNAME_ATTR);
		
		user1.addData(FNAME_ATTR, "Patrick");
		user2.addData(FNAME_ATTR, "Pavel");
		user3.addData(FNAME_ATTR, "Geno");
		user4.addData(FNAME_ATTR, "Mark");
		user5.addData(FNAME_ATTR, "Wayne");
		user6.addData(FNAME_ATTR, "Gordie");
		user7.addData(FNAME_ATTR, "Joe");
		user8.addData(FNAME_ATTR, "Mike");
		user9.addData(FNAME_ATTR, "Brett");
		
		user1.addAttribute(TEAMNAME_ATTR);
		user2.addAttribute(TEAMNAME_ATTR);
		user3.addAttribute(TEAMNAME_ATTR);
		user4.addAttribute(TEAMNAME_ATTR);
		user5.addAttribute(TEAMNAME_ATTR);
		user6.addAttribute(TEAMNAME_ATTR);
		user7.addAttribute(TEAMNAME_ATTR);
		user8.addAttribute(TEAMNAME_ATTR);
		user9.addAttribute(TEAMNAME_ATTR);
		
		user1.addData(TEAMNAME_ATTR, "USA");
		user2.addData(TEAMNAME_ATTR, "Russia");
		user3.addData(TEAMNAME_ATTR, "Russia");
		user4.addData(TEAMNAME_ATTR, "Canada");
		user5.addData(TEAMNAME_ATTR, "Canada");
		user6.addData(TEAMNAME_ATTR, "Canada");
		user7.addData(TEAMNAME_ATTR, "Canada");
		user8.addData(TEAMNAME_ATTR, "USA");
		user9.addData(TEAMNAME_ATTR, "USA");
		user9.addData(TEAMNAME_ATTR, "Canada");
		
		user1.addAttribute(ACTIVE_ATTR);
		user2.addAttribute(ACTIVE_ATTR);
		user3.addAttribute(ACTIVE_ATTR);
		user4.addAttribute(ACTIVE_ATTR);
		user5.addAttribute(ACTIVE_ATTR);
		user6.addAttribute(ACTIVE_ATTR);
		user7.addAttribute(ACTIVE_ATTR);
		user8.addAttribute(ACTIVE_ATTR);
		user9.addAttribute(ACTIVE_ATTR);
		
		
		user1.addData(ACTIVE_ATTR, "1");
		user2.addData(ACTIVE_ATTR, "1");
		user3.addData(ACTIVE_ATTR, "1");
		user4.addData(ACTIVE_ATTR, "0");
		user5.addData(ACTIVE_ATTR, "0");
		user6.addData(ACTIVE_ATTR, "0");
		user7.addData(ACTIVE_ATTR, "0");
		user8.addData(ACTIVE_ATTR, "0");
		user9.addData(ACTIVE_ATTR, "0");
		
		user1.addAttribute(FULLNAME_ATTR);
		user2.addAttribute(FULLNAME_ATTR);
		user3.addAttribute(FULLNAME_ATTR);
		user4.addAttribute(FULLNAME_ATTR);
		user5.addAttribute(FULLNAME_ATTR);
		user6.addAttribute(FULLNAME_ATTR);
		user7.addAttribute(FULLNAME_ATTR);
		user8.addAttribute(FULLNAME_ATTR);
		user9.addAttribute(FULLNAME_ATTR);
		
		
		user1.addData(FULLNAME_ATTR, "Kane:Patrick");
		user2.addData(FULLNAME_ATTR, "Datzyuk:Pavel");
		user3.addData(FULLNAME_ATTR, "Malkin:Geno");
		user4.addData(FULLNAME_ATTR, "Messier:Mark");
		user5.addData(FULLNAME_ATTR, "Gretzky:Wayne");
		user6.addData(FULLNAME_ATTR, "Howe:Gordie");
		user7.addData(FULLNAME_ATTR, "Sakic:Joe");
		user8.addData(FULLNAME_ATTR, "Modano:Mike");
		user9.addData(FULLNAME_ATTR, "Hull:Brett");
		
		if(shouldIncludeUser(user1))
		{
			userList.addDatum(user1.getDatumID(), user1);
		}
		
		if(shouldIncludeUser(user2))
		{
			userList.addDatum(user2.getDatumID(), user2);
		}
		
		if(shouldIncludeUser(user3))
		{
			userList.addDatum(user3.getDatumID(), user3);
		}
		
		if(shouldIncludeUser(user4))
		{
			userList.addDatum(user4.getDatumID(), user4);
		}
		
		if(shouldIncludeUser(user5))
		{
			userList.addDatum(user5.getDatumID(), user5);
		}
		
		if(shouldIncludeUser(user6))
		{
			userList.addDatum(user6.getDatumID(), user6);
		}
		
		if(shouldIncludeUser(user7))
		{
			userList.addDatum(user7.getDatumID(), user7);
		}
		
		if(shouldIncludeUser(user8))
		{
			userList.addDatum(user8.getDatumID(), user8);
		}
		
		if(shouldIncludeUser(user9))
		{
			userList.addDatum(user9.getDatumID(), user9);
		}

		ArrayList<String[]> retval = new ArrayList<String[]>();
		
		for(String userID : getUserIDs())
		{
			String userTeams = "";

			for(String userTeam : getTeamNames(userID))
			{
				userTeams += userTeam + ":";
			}

			retval.add
			(
					new String[] 
							{
							getUser(userID).getAttributeData(USERID_ATTR).get(0),
							getUser(userID).getAttributeData(DERPID_ATTR).get(0),
							getUser(userID).getAttributeData(LNAME_ATTR).get(0),
							getUser(userID).getAttributeData(FNAME_ATTR).get(0),
							getUser(userID).getAttributeData(EMPID_ATTR).get(0),
							getFullName(userID),
							userTeams,
							"" + isActiveUser(userID)
							}
			);
		}
		
		return retval;
	}

	/* (non-Javadoc)
	 * @see com.sutherland.helios.roster.Roster#isActiveUser(java.lang.String)
	 */
	@Override
	public boolean isActiveUser(String userID) 
	{
		return userList.getDatum(userID).getAttributeData(ACTIVE_ATTR).get(0).equals("1");
	}

	@Override
	public ArrayList<String> getReportSchema() 
	{
		ArrayList<String> retval = new ArrayList<String>();
		
		retval.add("User ID");
		retval.add("Derp ID");
		retval.add("Last Name");
		retval.add("First Name");
		retval.add("Emp ID");
		retval.add("Full Name");
		retval.add("Team Names");
		retval.add("Active");
		
		return retval;
	}

	@Override
	protected void logInfoMessage(String message) 
	{
		
	}

	@Override
	protected void logWarnMessage(String message) 
	{
		
	}

	@Override
	protected void logErrorMessage(String message) 
	{
		
	}
}
