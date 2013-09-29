package api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import api.formatting.CSVFormatter;

import report.Report;

/*
 * The frontend of the Helios poor-man's api.
 * 
 * Should handle loading available reports and any initialization. Subclasses of report.SQL.report. Classes within report.SQL
 * 
 * accept an output setting for formats html, xml, raw, csv/demlimited
 * 
 * Simple approach: hardcode a path to any deployed jars containing the reports. hardcode the parent package name. foreach class, load it and trigger init 
 * 
 * @author Jason Diamond
 */
public class FrontEnd 
{
	private final static String REPORT_CLASSNAME_PREFIX = "report.";
	private final static String REPORT_CLASSPATH_PREFIX = "report/";
	private final static String REPORT_CLASSNAME_SUFFIX = ".class";
	
	private ArrayList<String> reportJars;
	
	private ArrayList<String> reportClassNames;
	
	public FrontEnd()
	{
		reportClassNames = new ArrayList<String>();
		reportJars = new ArrayList<String>();
	}
	
	public void addJar(String jarPath)
	{
		reportJars.add(jarPath);
	}
	
	public void loadClassNames()
	{
		for(String jarPath : reportJars)
		{
			JarFile jar = null;
			
			try 
			{
				jar = new JarFile(jarPath);
	
				
				String rawClassName;
				for(JarEntry entry: Collections.list(jar.entries()))
				{
					rawClassName = entry.getName();
					
					if(rawClassName.startsWith(REPORT_CLASSPATH_PREFIX) && rawClassName.endsWith(REPORT_CLASSNAME_SUFFIX ))
					{
						reportClassNames.add(rawClassName.replace("/", ".").replace(".class",""));
					}
				}
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			finally
			{
	
				if(jar != null)
				{
					try 
					{
						jar.close();
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public Class<?> getReportClass(String className) throws ClassNotFoundException
	{
		Class<?> retval = null;
		//URLClassLoader loader = null;

		//loader = new URLClassLoader(new URL[]{new File(REPORT_JAR_PATH).toURI().toURL()});
		//retval = loader.loadClass(className);
		
		if(isValidClassName(className))
		{
			retval = Class.forName(className);
		}
		
		return retval;
	}
	
	public ArrayList<String> getClassNames()
	{
		return reportClassNames;
	}
	
	public boolean isValidClassName(String className)
	{
		boolean retval = false;
		
		retval = getClassNames().contains(className);
		
		return retval;
	}
	
	public void printClassNames()
	{
		for(String className : getClassNames())
		{
			System.out.println(className);
		}
	}
	
	public ArrayList<String[]> startReport(String reportName, HashMap<String, String> params) throws ClassNotFoundException
	{
		reportName = REPORT_CLASSNAME_PREFIX + reportName;
		
		if(!isValidClassName(reportName))
		{
			throw new ClassNotFoundException("Invalid report class: " + reportName);
		}
		
		ArrayList<String[]> retval = new ArrayList<String[]>();
		Class<?>reportClass = getReportClass(reportName);
		
		Report report =  null; 
		try
		{
			//report = (Report) reportClass.newInstance();
			//report = (Report) reportClass.getConstructor().newInstance();
			//report = (Report)((Object) reportClass.newInstance());
			
			report = (Report) reportClass.newInstance();
			
			if(params != null)
			{
				for(Entry<String, String> param : params.entrySet())
				{
					report.setParameter(param.getKey(), param.getValue());
				}
			}
			
			for(String[] row : report.startReport())
			{
				retval.add(row);
			}
			
			if(report.isTimeReport())
			{
				Collections.sort(retval, new Comparator<String[]>()
				{
						public int compare(String[] arg0, String[] arg1) 
						{
							//for n elements, compare row[0]
							return arg0[0].compareTo(arg1[0]);
						}
				}
				);
			}
			else
			{
				Collections.sort(retval, new Comparator<String[]>()
				{
						public int compare(String[] arg0, String[] arg1) 
						{
							int retval;
							if(arg0[1] == null)
							{
								retval = -1;
							}
							else if(arg1[1] == null)
							{
								retval = 1;
							}
							else
							{
								//for n elements, compare row[1]
								retval = arg0[1].compareTo(arg1[1]);
							}
							return retval;
						}
				}
				);
			}
		}
		catch(IllegalAccessException e)
		{
			e.printStackTrace();
		} 
		catch (InstantiationException e) 
		{
			e.printStackTrace();
		} 
		catch (IllegalArgumentException e) 
		{
			e.printStackTrace();
		} 
		catch (SecurityException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(report != null)
			{
				report.close();
			}
		}
			
		return retval;
	}
	
	public static void main(String[] args)
	{
		FrontEnd fe = new FrontEnd();
		
		fe.printClassNames();
		
		try 
		{
			for(String row: new CSVFormatter().formatResults(fe.startReport("Teams", null)))
			{
				System.out.println(row);
			}
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
}
