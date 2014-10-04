/**
 * 
 */
package com.sutherland.helios.roster;

import java.util.ArrayList;

import com.sutherland.helios.data.Aggregation;
import com.sutherland.helios.data.Datum;
import com.sutherland.helios.database.connection.DatabaseConnection;
import com.sutherland.helios.exceptions.ReportSetupException;
import com.sutherland.helios.report.Report;
import com.sutherland.helios.roster.attributes.BasicRosterAttributes;

/**
 * @author Jason Diamond
 *
 */
public abstract class Roster extends Report implements BasicRosterAttributes
{
	protected Aggregation userList;
	
	protected ArrayList<String> teamFilterList;
	protected ArrayList<String> userFilterList;
	
	protected DatabaseConnection dbConnection;
	
	protected boolean activeUserFilter;
	protected boolean includeAllUsers;
	
	/**
	 * @throws ReportSetupException
	 */
	public Roster() throws ReportSetupException 
	{
		super();
		
		activeUserFilter = false;
		includeAllUsers = false;
		userList = new Aggregation();
		userFilterList = new ArrayList<String>();
		teamFilterList = new ArrayList<String>();
	}
	
	/* (non-Javadoc)
	 * @see report.Report#setupDataSourceConnections()
	 */
	@Override
	protected abstract boolean setupDataSourceConnections();

	/* (non-Javadoc)
	 * @see report.Report#setupReport()
	 */
	@Override
	protected abstract boolean setupReport();

	/* (non-Javadoc)
	 * @see report.Report#setupLogger()
	 */
	@Override
	protected abstract boolean setupLogger();

	/* (non-Javadoc)
	 * @see report.Report#runReport()
	 */
	@Override
	protected abstract ArrayList<String[]> runReport() throws Exception;
	
	public ArrayList<String> getTeamNames(String userID) 
	{
		Datum user = getUser(userID);

		ArrayList<String> teamNames = new ArrayList<String>();

		teamNames.addAll(user.getAttributeData(TEAMNAME_ATTR));

		return teamNames;
	}
	
	public void setIncludeAllUsers(boolean includeAllUsers)
	{
		this.includeAllUsers = includeAllUsers;
	}
		
	protected boolean shouldIncludeUser(Datum user)
	{
		boolean retval = false;
		
		for(String teamName : getParameters().getTeamNames())
		{
			for(String userTeamName : user.getAttributeData(TEAMNAME_ATTR))
			{
				if(teamName.equals(userTeamName))
				{
					retval = true;
					break;
				}
			}
		}
		
		if(!retval)
		{
			for(String userName : getParameters().getAgentNames())
			{
				if(user.getAttributeData(FULLNAME_ATTR).get(0).equals(userName))
				{
					retval = true;
					break;
				}
			}
		}

		return retval;
	}
	
	protected boolean isValidTeamName(String teamName)
	{
		boolean retval = false;
		
		//assume the roster has been loaded, and we're not dealing with an empty team, and invoker isn't too stupid
		//check to see if a loaded roster has a user that's a member of teamName
		for(String userID : getUserIDs())
		{
			for(String userTeam : getUser(userID).getAttributeData(TEAMNAME_ATTR))
			{
				if(userTeam.equals(teamName))
				{
					retval = true;
					break;
				}
			}
		}
		
		return retval;
	}
	
	public abstract String getFullName(String userID);
	
	//hard to anticipate schema. maybe force the implementor to handle the how and provide the structure here
	public abstract void load();
	
	public void close()
	{
		//super.close();
	}
	
	public String[] getUserIDs()
	{
		return userList.getDatumIDList();
	}
	
	public Datum getUser(String userID)
	{
		return userList.getDatum(userID);
	}
	
	public String lookupUserByFullName(String fullName)
	{
		String targetUserID = null;

		for(String userID : getUserIDs())
		{
			if(fullName.equals(getFullName(userID) ) )
			{
				targetUserID = userID;
				break;
			}
		}
		
		return targetUserID;
	}
	
	public abstract boolean isActiveUser(String userID);
	
	/**
	 * Lookup a user given a key value. Users have attributes, and some are unique. Search the all users and all their respective unique attributes.
	 * 
	 * @param key
	 * 
	 * @return	The userID of the discovered user.
	 */
	public String lookupUser(String key)
	{		
		String targetUserID = null;
		Datum thisUser;
		
		if(key != null)
		{
			for(String userID : getUserIDs())
			{
				thisUser = getUser(userID);
				for(String uniqueAttribute : thisUser.getUniqueAttributeNames())
				{
					if(thisUser.getAttributeData(uniqueAttribute).get(0).equalsIgnoreCase(key))
					{
						targetUserID = thisUser.getDatumID();
						break;
					}
				}
				
				if(targetUserID != null)
				{
					break;
				}
			}
		}
		
		return targetUserID;
	}
	
	/**
	 * Lookup a user given a key value and known unique attribute.
	 * 
	 * It is conceivable that users on the same roster might have different unique attributes.
	 * 
	 * @param key
	 * @param attributeName
	 * 
	 * @return	The userID of the discovered user.
	 */
	public String lookupUserByAttributeName(String key, String attributeName)
	{
		String targetUserID = null;
		Datum thisUser;
		if(key != null && attributeName != null)
		{
			for(String userID : getUserIDs())
			{
				thisUser = getUser(userID);
				
				if(thisUser.hasUniqueAttribute(attributeName) && thisUser.getAttributeData(attributeName).get(0).equalsIgnoreCase(key) )
				{
					targetUserID = thisUser.getDatumID();
					break;
				}
			}
		}
		
		return targetUserID;
	}
	
	public int getSize()
	{
		return userList.getSize();
	}
	
	public void clearUsers()
	{
		userList.clear();
	}
	
	public void clearTeams()
	{
		teamFilterList.clear();
	}
	
	public void setActiveUserOnly(boolean onlyActiveUsers)
	{
		activeUserFilter = onlyActiveUsers;
	}
	
	public boolean isActiveUserOnly()
	{
		return activeUserFilter;
	}
	
	public void addUser(String userID, Datum user)
	{
		userList.addDatum(userID, user);
	}
	
	public void removeUser(String userID) 
	{
		userList.removeDatum(userID);
	}
	
	public boolean hasUser(String userID)
	{
		return userList.hasDatum(userID);
	}

//	public void addTeamFilter(String teamName)
//	{
//		teamFilterList.add(teamName);
//	}
//	
//	public void removeTeamFilter(String teamName)
//	{
//		teamFilterList.remove(teamName);
//	}
//	
//	public void addUserFilter(String userName)
//	{
//		userFilterList.add(userName);
//	}
//	
//	public void removeUserFilter(String teamName)
//	{
//		userFilterList.remove(teamName);
//	}
	
	/* (non-Javadoc)
	 * @see report.Report#validateParameters()
	 */
	@Override
	protected boolean validateParameters()
	{
		//roster should be setup as a basic query to pull all members, and have the implementing site apply it's own filters
		
		return true;
	}
}
