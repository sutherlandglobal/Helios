package helios.data;

import helios.report.parameters.sanitize.StringSanitizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * A datum and their aggregated data. A datum can be any aggregation point, like a date or even a team, not just a human. A datum aggregates a collection of attributes, which in
 * turn aggregate data.
 * 
 * @author Jason Diamond
 *
 */
public class Datum 
{
	private HashMap<String, ArrayList<String>> data;
	private HashMap<String, ArrayList<Object>> dataObjects;
	private TreeSet<String> uniqueAttributes;
	
	private String datumID;
	
	public static final int MAX_PARAM_LEN = 100;
	private static final int DEFAULT_DATA_COUNT = 20;
	
	/**
	 * Build a datum, with the provided Borg designation. We are the Borg. Lower your shields and surrender your ships. 
	 * We will add your biological and technological distinctiveness to our own. Resistance is futile.
	 * 
	 * 
	 * 
	 * @param datumID		The id to refer to this datum by.
	 */
	public Datum(String datumID)
	{
		data = new HashMap<String, ArrayList<String>>();
		
		this.datumID = StringSanitizer.sanitize(datumID, MAX_PARAM_LEN);
		
		dataObjects = new HashMap<String, ArrayList<Object>>(DEFAULT_DATA_COUNT);
		uniqueAttributes = new TreeSet<String>();
	}
	
	public String getDatumID()
	{
		return datumID;
	}
	
	public void setDatumID(String id)
	{
		this.datumID = StringSanitizer.sanitize(id, MAX_PARAM_LEN);
	}
	
	/**
	 * Add an attribute to the datum.
	 * 
	 * @param attributeName	The attribute/data grouping to add.
	 * 
	 * @return	True if the attribute was successfully added, false otherwise.
	 */
	public boolean addAttribute(String attributeName)
	{
		boolean retval = false;
		
		attributeName = StringSanitizer.sanitize(attributeName, MAX_PARAM_LEN);
		
		if(!data.containsKey(attributeName))
		{
			data.put(attributeName, new ArrayList<String>(DEFAULT_DATA_COUNT));
			retval = true;
		}
		
		return retval;
	}
	
	public void setAttributeAsUnique(String attributeName)
	{
		attributeName = StringSanitizer.sanitize(attributeName, MAX_PARAM_LEN);
		
		if(data.containsKey(attributeName))
		{
			uniqueAttributes.add(attributeName);
		}
	}
	
	public TreeSet<String> getUniqueAttributeNames()
	{
		return uniqueAttributes;
	}
	
	public boolean hasUniqueAttribute(String attributeName) 
	{
		attributeName = StringSanitizer.sanitize(attributeName, MAX_PARAM_LEN);
		
		return uniqueAttributes.contains(attributeName);
	}
	
	/**
	 * Add a datum to the provided attribute.
	 * 
	 * @param attributeName	Attribute to add the datum to.s
	 * @param attributeData		Datum to add.
	 * 
	 * @return	True if the datum was successfully added, false otherwise.
	 */
	public boolean addData(String attributeName, String attributeData)
	{
		boolean retval = false;
		
		attributeName = StringSanitizer.sanitize(attributeName, MAX_PARAM_LEN);
		attributeData = StringSanitizer.sanitize(attributeData, MAX_PARAM_LEN);
		
		addAttribute(attributeName);

		retval = data.get(attributeName).add(attributeData);
		
		return retval;
	}
	
	/**
	 * Add a datum to the provided attribute.
	 * 
	 * @param attributeName	Attribute to add the datum to.s
	 * @param attributeData		Datum to add.
	 * 
	 * @return	True if the datum was successfully added, false otherwise.
	 */
	public boolean addData(String attributeName, double attributeData)
	{
		return addData(attributeName, "" + attributeData) ;
	}
	
	/**
	 * Add a datum to the provided attribute.
	 * 
	 * @param attributeName	Attribute to add the datum to.s
	 * @param attributeData		Datum to add.
	 * 
	 * @return	True if the datum was successfully added, false otherwise.
	 */
	public boolean addData(String attributeName, int attributeData)
	{
		return addData(attributeName, "" + attributeData) ;
	}
	
	/**
	 * Add a datum to the provided attribute.
	 * 
	 * @param attributeName	Attribute to add the datum to.s
	 * @param attributeData		Datum to add.
	 * 
	 * @return	True if the datum was successfully added, false otherwise.
	 */
	public boolean addData(String attributeName, float attributeData)
	{
		return addData(attributeName, "" + attributeData) ;
	}
	
	/**
	 * Retrive the dataset for the provided attribute.
	 * 
	 * @param attributeName	The attribute to retrieve data for.
	 * 
	 * @return	The dataset for the given attribute.
	 */
	public ArrayList<String> getAttributeData(String attributeName)
	{
		return data.get(attributeName);
	}
	
	/**
	 * Delete an attribute's data.
	 * 
	 * @param attributeName	Attribute who's data to delete.
	 * 
	 * @return	True if the deletion was successful, false otherwise.
	 */
	public boolean deleteAttributeData(String attributeName)
	{
		boolean retval = false;
		if(data.containsKey(attributeName))
		{
			data.remove(attributeName);
			retval = true;
		}
		return retval;
	}
	
	public boolean clearAttributeData(String attributeName)
	{
		boolean retval = false;
		if(data.containsKey(attributeName))
		{
			data.get(attributeName).clear();
			retval = true;
		}
		return retval;
	}

	/**
	 * Accessor for this datum's attributes.
	 * 
	 * @return	A list of this datums' attributes.
	 */
	public ArrayList<String> getAttributeNameList() 
	{
		ArrayList<String> retval = new ArrayList<String>(data.size());
		
		retval.addAll(data.keySet());
		
		
		return retval;
	}
	
	/**
	 * Accessor for this datum's attributes.
	 * 
	 * @return	A list of this datums' attributes.
	 */
	public ArrayList<String> getCustomObjectNameList() 
	{
		ArrayList<String> retval = new ArrayList<String>(dataObjects.size());
		
		retval.addAll(dataObjects.keySet());
		
		
		return retval;
	}
	
	/**
	 * Add a custom object to the datum, using the provided object designation.
	 * 
	 * @param objectName	Attribute name for the object.
	 * @param data	The object itself.
	 * 
	 */
	public void addObject(String objectName, Object data)
	{
		if(!dataObjects.containsKey(objectName))
		{
			dataObjects.put(objectName, new ArrayList<Object>(DEFAULT_DATA_COUNT));
		}
		
		dataObjects.get(objectName).add(data);
	}
	
	/**
	 * Determine if a group of custom objects is already defined.
	 * 
	 * @param objectName	Name to check for existence.
	 * 
	 * @return	True if the group exists, false otherwise.
	 */
	public boolean hasObjectName(String objectName)
	{
		return dataObjects.containsKey(objectName);
	}
	
	/**
	 * Remove the group of custom objects mapped by the supplied name.
	 * 
	 * @param objectName	Name of mapping key to delete, along with the value.
	 * 
	 */
	public void deleteObject(String objectName)
	{
		dataObjects.remove(objectName);
	}
	
	/**
	 * Return the list of objects stored at the given key.
	 * 
	 * @param objectName	Object list name to check..
	 * 
	 * @return	The list of objects mapped to the given key.
	 */
	public ArrayList<Object> getDatumObjects(String objectName)
	{
		return dataObjects.get(objectName);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datumID == null) ? 0 : datumID.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Datum)) {
			return false;
		}
		Datum other = (Datum) obj;
		if (datumID == null) {
			if (other.datumID != null) {
				return false;
			}
		} else if (!datumID.equals(other.datumID)) {
			return false;
		}
		return true;
	}

	/**
	 * Stringify this datum. Contains all of the datums data and custom objects.
	 * 
	 * @return	A String describing this datum.
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("datum " + getDatumID() + ": \n");
		for(String attr : data.keySet())
		{
			sb.append(attr);
			sb.append(": ");
			sb.append(Arrays.asList(data.get(attr).toArray()).toString());
			sb.append("\n");
		}
		
		for(String attr : dataObjects.keySet())
		{
			sb.append("Custom Object: " + attr);
			sb.append(": ");
			sb.append(Arrays.asList(dataObjects.get(attr).toArray()).toString());
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
