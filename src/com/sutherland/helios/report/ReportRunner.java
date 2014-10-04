package com.sutherland.helios.report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.sutherland.helios.util.threading.ThreadPool;


/**
 * Run a bunch of Helios reports in parallel. Collect reports, run them all at once, store the results for retrieval. Managing report parameters is the involker's responsibility.
 * 
 * @author Jason Diamond
 * 
 * 
 *
 */
public class ReportRunner 
{
	private HashMap<String, Report> reports;
	private final Map<String, ArrayList<String[]>> results;
	private final int MAX_THREADS = 20;
	
	
	/**
	 * Build the report runner and the internal structures for collecting reports and storing results.
	 */
	public ReportRunner() 
	{
		reports = new HashMap<String, Report>();
		results =Collections.synchronizedMap(new HashMap<String, ArrayList<String[]>>());
	}
	
	/**
	 * Empty the report collection.
	 */
	public void clearReports()
	{
		reports.clear();
	}
	
	/**
	 * Add a report to the collection.
	 * 
	 * @param name		The name of the report to store.
	 * @param report	The report to queue up, configured with the correct parameters.
	 * 
	 * @return	True if the report was queued up to run, false otherwise.
	 */
	public boolean addReport(String name,Report report)
	{
		boolean retval = false;
		
		if(!reports.containsKey(name))
		{
			reports.put(name, report);
			retval = true;
		}
		
		return retval;
	}
	
	/**
	 * Remove a report from the collection.
	 * 
	 * @param name		The name of the report to remove.
	 * 
	 * @return	True if the removal succeeded, false otherwise. 
	 */
	public boolean removeReport(String name)
	{
		boolean retval = false;
		
		if(reports.containsKey(name))
		{
			reports.remove(name);
			retval = true;
		}
		
		return retval;
	}
	
	/**
	 * Run all the collected reports in parallel. 
	 * 
	 * @return	True if all the reports ran successfully, false otherwise.
	 */
	public boolean runReports()
	{
		ThreadPool tp = null;
		boolean retval = true;
		
		results.clear();
		
		try
		{	
			int numThreads= reports.size();
			
			if(numThreads > MAX_THREADS)
			{
				numThreads = MAX_THREADS;
			}			
			
			tp = new ThreadPool(numThreads);
			
			final List<Report> workers = Collections.synchronizedList(new ArrayList<Report>(reports.size()));
			
			for(int i =0; i<reports.size();i++)
			{
				workers.add(null);
			}
			
			int index = 0;
			for(final Entry<String, Report> report : reports.entrySet())
			{
				//for each report put in threadpool and run. results into the hashmap
				final int i = index;
				index++;
				
				tp.runTask
				(
						new Runnable()
						{
							public void run()
							{
								try
								{
									workers.set(i, report.getValue());
									
									results.put(report.getKey(), report.getValue().startReport());
								}
								catch(Throwable t)
								{
									t.printStackTrace();
								}
								finally
								{
									report.getValue().close();
								}
							}
						}
				);
			}
		}
		catch(Throwable t)
		{
			t.printStackTrace();
		}
		finally
		{
			if(tp != null)
			{
				tp.close();
			}
		}
		
		return retval;
	}
	
	/**
	 * Retrieve the results of executing a group of reports.
	 * 
	 * @param name		The name of the report to retrieve the results for.
	 * 
	 * @return		The results for the named report.
	 */
	public ArrayList<String[]> getResults(String name)
	{
		return results.get(name);
	}
}
