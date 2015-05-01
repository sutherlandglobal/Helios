package com.sutherland.helios.data;

import java.util.HashMap;

import com.sutherland.helios.report.parameters.sanitize.StringSanitizer;


/**
 * A general aggregation of data.
 * 
 * @author Jason Diamond
 *
 */
public class Aggregation 
{
	private HashMap<String, Datum> data;
	
	/**
	 * 	Build an empty team.
	 */
	public Aggregation()
	{
		data = new HashMap<String, Datum>();
	}
	
	public int getSize()
	{
		return data.size();
	}
	
	/**
	 * Retrieve a user by name.
	 * 
	 * @param datumID	Username to query.
	 * 
	 * @return	The User object mapped to the name.
	 */
	public Datum getDatum(String datumID)
	{
		return data.get(datumID);
	}
	
	public boolean hasDatum(String datumID)
	{
		return data.containsKey(datumID);
	}
	
	/**
	 * Add a user to the team.
	 * 
	 * @param datumID		The username to add.
	 * 
	 * @return	True if the user was added, false otherwise.
	 */
	public boolean addDatum(String datumID)
	{
		datumID = StringSanitizer.sanitize(datumID, Datum.MAX_PARAM_LEN);
		
		boolean retval = false;
		
		if(!data.containsKey(datumID))
		{
			data.put(datumID, new Datum(datumID));
			retval = true;
		}
		
		return retval;
	}
	
	public boolean addDatum(String datumID, Datum datum)
	{
		datumID = StringSanitizer.sanitize(datumID, Datum.MAX_PARAM_LEN);
		
		boolean retval = false;
		
		if(addDatum(datumID))
		{
			data.put(datumID, datum);
			retval = true;
		}
		
		return retval;
	}
	
	/**
	 * Remove a user from the team.
	 * 
	 * @param datumID	The username to remove.
	 * 
	 * @return	True if the element was removed, false otherwise.
	 */
	public boolean removeDatum(String datumID)
	{
		datumID = StringSanitizer.sanitize(datumID, Datum.MAX_PARAM_LEN);
		
		return data.remove(datumID) != null;
	}
	
	/**
	 * Accessor for the team's usernames.
	 * 
	 * @return	A list of the usernames.
	 */
	public String[] getDatumIDList()
	{
		return data.keySet().toArray(new String[data.size()]);
	}
	
	public void clear() 
	{
		for(String datumID: getDatumIDList())
		{
			removeDatum(datumID);
		}
	}
}
