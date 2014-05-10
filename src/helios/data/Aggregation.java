package helios.data;

import helios.report.parameters.sanitize.StringSanitizer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


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
	
	/**
	 * Load a team from a file.
	 * 
	 * @param file		File to read users from.
	 * 
	 * @return	True if the file was sucessfully read, false otherwise.
	 */
	public boolean loadFromFile(String file)
	{
		boolean retval = false;
		
		BufferedReader dataIn = null;
		
		try 
		{
			dataIn = new BufferedReader(new FileReader(file));
			
			String line;
			while( (line = dataIn.readLine()) != null )
			{
				if(line.matches("^[a-zA-Z -]*$"))
				{
					data.put(line, new Datum(line));
				}
			}
			retval = true;
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		finally
		{
			try 
			{
				if(dataIn != null )
				{
					dataIn.close();
				}
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		return retval;
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
