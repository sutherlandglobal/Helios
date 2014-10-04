package com.sutherland.helios.roster.filter;

import java.util.ArrayList;

import com.sutherland.helios.roster.Roster;

public abstract class FilterVisitor 
{
	private FilterVisitor() {}
	
//	@Override	
//	public void filterUsers()
//	{
//		int oldSize = getSize();
//
//		super.filterUsers();
//		
//		int newSize = getSize();
//		logMessage(Level.INFO, "Users filtered " + (oldSize - newSize) + " users from roster. Finalized roster now has " + newSize + " users." );
//	}
	
	public static void filterInactiveUsers(Roster roster)
	{
		//a roster has an active attribute for it's users. the roster implementor needs to handle this in its load() function

		for(String userID : roster.getUserIDs())
		{
			if(!roster.isActiveUser(userID))
			{
				roster.removeUser(userID);
			}
		}
	}
	
	public static void filterActiveUsers(Roster roster)
	{
		//a roster has an active attribute for it's users. the roster implementor needs to handle this in its load() function

		for(String userID : roster.getUserIDs())
		{
			if(roster.isActiveUser(userID))
			{
				roster.removeUser(userID);
			}
		}
	}

	public static void filterNonTeamMembers(Roster roster)
	{
		ArrayList<String> teamNames = roster.getParameters().getTeamNames();
		
		//no filter -> no filtering
		if(!teamNames.isEmpty())
		{
			for(String userID : roster.getUserIDs())
			{
				for(String teamName : roster.getTeamNames(userID))
				{
					if(teamNames.contains(teamName))
					{
						roster.removeUser(userID);
					}
				}
			}
		}
	}
	
	public static void filterNonWhitelistedUsers(Roster roster)
	{
		ArrayList<String> agentNames = roster.getParameters().getAgentNames();
		
		//no filter -> no filtering
		if(!agentNames.isEmpty())
		{
			for(String userID : roster.getUserIDs())
			{
				if(agentNames.contains(roster.getFullName(userID)))
				{
					roster.removeUser(userID);
				}
			}
		}
	}
}
