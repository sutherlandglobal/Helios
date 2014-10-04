package com.sutherland.helios.report.parameters.validation;

import java.util.ArrayList;

import com.sutherland.helios.report.Report;

public class RosterValidator extends ParameterGroupValidator 
{
	public RosterValidator()
	{
		super();
	}
	
	public boolean validate(Report report)
	{
		boolean retval = false;
		
		try
		{
			//a report has to have at least one agent name or team name
			ArrayList<String> agentNames = report.getParameters().getAgentNames();
			ArrayList<String> teamNames = report.getParameters().getTeamNames();
			
			if(teamNames.size() > 0 || agentNames.size() > 0)
			{
				retval = true;
			}
			else
			{
				setErrorMessage("No teams or agents specified.");
			}
		}
		catch (Exception e)
		{
			setErrorMessage("Exception validating roster" + e.getMessage());
		}
			
		return retval;
	}
}
