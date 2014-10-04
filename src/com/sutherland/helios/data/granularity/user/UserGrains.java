package com.sutherland.helios.data.granularity.user;

import java.util.LinkedHashMap;

import com.sutherland.helios.data.Datum;
import com.sutherland.helios.roster.attributes.BasicRosterAttributes;


public abstract class UserGrains 
{

	public static final LinkedHashMap<String, String> avaliableUserGrains = new LinkedHashMap<String, String>()
	{
		private static final long serialVersionUID = -2857889045032219363L;
		{
			put("Agent", "" + AGENT_GRANULARITY);
			put("Team", "" + TEAM_GRANULARITY );
			put("Program", "" + PROGRAM_GRANULARITY);
			put("Org Unit", "" + ORGUNIT_GRANULARITY);
		}
	};
	
	public final static int AGENT_GRANULARITY = 0;
	public final static int TEAM_GRANULARITY = 1;
	public final static int PROGRAM_GRANULARITY = 2;
	public final static int ORGUNIT_GRANULARITY = 3;
	
	private UserGrains()
	{}
	
	/**
	 * Determine the agent grain for the provided user. 
	 * 
	 * @param user	The user to determine the grain for.
	 * 
	 * @return	The agent granularity.
	 */
	private static String getAgentGrain(Datum user)
	{
		return user.getAttributeData(BasicRosterAttributes.FULLNAME_ATTR).get(0);
	}
	
	/**
	 * Determine the team grain for the provided user.
	 * 
	 * @param user	The user to determine the grain for.
	 * 
	 * @return	The team granularity.
	 */
	private static String getTeamGrain(Datum user)
	{
		return user.getAttributeData(BasicRosterAttributes.TEAMNAME_ATTR).get(0);
	}
	
	/**
	 * Determine the OrgUnit grain for the provided user.
	 * 
	 * @param user	The user to determine the grain for.
	 * 
	 * @return	The Organizational Unit granularity.
	 */
	private static String getOrgUnitGrain(Datum user)
	{
		return user.getAttributeData(BasicRosterAttributes.ORGUNIT_ATTR).get(0);
	}

	/**
	 * Determine the user grain for the provided user. 
	 * 
	 * @param user	The user to determine the grain for.
	 * 
	 * @return	The program granularity.
	 */
	private static String getProgramGrain(Datum user)
	{
		return user.getAttributeData(BasicRosterAttributes.PROGRAMNAME_ATTR).get(0);
	}

	public static String getUserGrain(int userGrain, Datum user)
	{
		String retval = null;

		switch(userGrain)
		{
		case AGENT_GRANULARITY:
			retval = getAgentGrain(user);
			break;
		case TEAM_GRANULARITY:
			retval = getTeamGrain(user);
			break;
		case PROGRAM_GRANULARITY:
			retval = getProgramGrain(user);
			break;
		case ORGUNIT_GRANULARITY:
			retval = getOrgUnitGrain(user);
			break;
		default:
			retval = getTeamGrain(user);
			break;
		}

		return retval;
	}
}
