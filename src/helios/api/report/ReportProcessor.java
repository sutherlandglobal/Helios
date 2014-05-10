package helios.api.report;

import helios.report.Report;
import helios.report.parameters.ReportParameters;
import helios.site.SiteManager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;

/**
 * The frontend of the Helios api.
 * 
 * Should handle loading available reports and any initialization. Subclasses of report.SQL.report. Classes within report.SQL
 * 
 * Accept an output setting for formats html, xml, raw, csv/demlimited
 *  
 * It should not be allowable for a site to execute any class, only subclasses of report.Report
 * 
 * @author Jason Diamond
 */
public class ReportProcessor 
{
	private ArrayList<String> loadedJars;

	private ArrayList<ReportEntity> loadedReportEntities;
	
	private ArrayList<String> reportSchema;

	private String errorMessage;
	
	public ReportProcessor()
	{
		errorMessage = "";
		
		loadedReportEntities = new ArrayList<ReportEntity>();

		loadedJars = new ArrayList<String>();
		
		reportSchema = null;
	}
	
	public String getErrorMessage()
	{
		return errorMessage;
	}
	
	public void addJar(String jarPath)
	{
		loadedJars.add(jarPath);
	}
		
	public ArrayList<String> getReportSchema()
	{
		return reportSchema;
	}
	
	/**
	 * Instantiate the report object by name.
	 * 
	 * @param reportName
	 * @return	instantiated class object
	 * @throws ClassNotFoundException
	 */
	public Class<?> buildReportClass(String reportName) throws ClassNotFoundException
	{
		Class<?> retval = null;
		//URLClassLoader loader = null;

		//loader = new URLClassLoader(new URL[]{new File(REPORT_JAR_PATH).toURI().toURL()});
		//retval = loader.loadClass(className);
		
		ReportEntity targetEntity = getEntityByClassName(reportName);
		
		if(targetEntity != null)
		{
			String className = targetEntity.getReportClass();
			
			if( className != null && isValidReportClassName(className) )
			{
				retval = Class.forName(className);
			}
		}
		else
		{
			errorMessage =  "Could not build reportName " + reportName;
		}
		
		return retval;
	}
	
	/**
	 * Get the class of the report object by name. Does not instantiate the object. used primarily when accessing informational methods for the ui
	 * 
	 * @param reportName
	 * @return 	uninstantiated class object
	 * @throws ClassNotFoundException
	 */
	public Class<?> getReportClass(String reportName) throws ClassNotFoundException
	{
		Class<?> retval = null;
		//URLClassLoader loader = null;

		//loader = new URLClassLoader(new URL[]{new File(REPORT_JAR_PATH).toURI().toURL()});
		//retval = loader.loadClass(className);
		
		ReportEntity targetEntity = getEntityByClassName(reportName);
		
		if(targetEntity != null)
		{
			String className = targetEntity.getReportClass();
			
			if( className != null && isValidReportClassName(className) )
			{
				retval = Class.forName(className, false, this.getClass().getClassLoader());
			}
		}
		else
		{
			errorMessage =  "Could not build reportName " + reportName;
		}
		
		return retval;
	}
	
	public ArrayList<ReportEntity> getReportEntities()
	{
		return loadedReportEntities;
	}
	
	public ArrayList<String> getClassNames()
	{
		ArrayList<String> retval = new ArrayList<String>();
		
		for(ReportEntity r : loadedReportEntities)
		{
			retval.add(r.getReportClass());
		}
		
		return retval;
	}
	
	public ReportEntity getEntityByClassName(String className)
	{
		ReportEntity retval = null;
		for(ReportEntity r : loadedReportEntities)
		{
			if(r.getReportClass().equals(className))
			{
				retval = r;
				break;
			}
		}
		
		return retval;
	}
	
	public ReportEntity getEntityByReportName(String reportName)
	{
		ReportEntity retval = null;
		for(ReportEntity r : loadedReportEntities)
		{
			if(r.getReportName().equals(reportName))
			{
				retval = r;
				break;
			}
		}
		
		return retval;
	}
	
	public ArrayList<String> getReportNames()
	{
		ArrayList<String> retval = new ArrayList<String>();
		
		for(ReportEntity r : loadedReportEntities)
		{
			retval.add(r.getReportName());
		}
		
		return retval;
	}
	
	public boolean isValidReport(String className) throws ClassNotFoundException
	{
		boolean retval = false;

		if(className != null)
		{
			ReportEntity r = getEntityByClassName(className);
			
			if(r != null )
			{
				retval = true;
			}
		}
		return retval;
	}
	
	public boolean isValidReportClassName(String className) throws ClassNotFoundException
	{
		boolean retval = false;
		
		if(className != null)
		{
			ReportEntity r = getEntityByClassName(className);
			
			String superClassName = r.getSuperClassName();
			
			if(superClassName != null )
			{
				retval = true;
			}
		}
		
		return retval;
	}
	
	public void loadReportEntities() throws ClassNotFoundException
	{
		loadedReportEntities.clear();
		
		for(String siteName : loadedJars)
		{
			for(ReportEntity r : SiteManager.getReportsFromJar(siteName))
			{
				loadedReportEntities.add(r);
			}
		}
	}
	
	public void printReportEntities()
	{
		for(ReportEntity r : loadedReportEntities)
		{
			System.out.println(r);
		}
	}

	public void removeSite(String siteName)
	{
		loadedJars.remove(siteName);
	}
	
	public void reset()
	{
		loadedJars.clear();
		loadedReportEntities.clear();
	}
	
	public ArrayList<String> getReportInfo(String reportName) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		ArrayList<String> retval = new ArrayList<String>();
		
		Class<?>reportClass = getReportClass(reportName);
		
		Method method = reportClass.getMethod("uiGetReportName", (Class<?>[])null);
		//Object invoke = method.invoke(null, (Object)null);
		Object invoke = method.invoke(null);
		retval.add( (String) invoke);
		
		method = reportClass.getMethod("uiGetReportDesc", (Class<?>[])null);
		//invoke = method.invoke(null, (Object)null);
		invoke = method.invoke(null);
		retval.add( (String) invoke);
		
		return retval;
	}
	
	@SuppressWarnings("unchecked")
	public LinkedHashMap<String, String> getUISupportedReportTypes(String reportName) throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException 
	{
		LinkedHashMap<String,String> retval = new LinkedHashMap<String,String>();
		
		Class<?>reportClass = getReportClass(reportName);
		
		Field field = reportClass.getField("uiSupportedReportTypes");
		
		retval = (LinkedHashMap<String, String>) field.get(new LinkedHashMap<String, String>());
		
		return retval;
	}
	
	@SuppressWarnings("unchecked")
	public LinkedHashMap<String, String> getUISupportedReportFrontEnds(String reportName) throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException 
	{
		LinkedHashMap<String,String> retval = new LinkedHashMap<String,String>();
		
		Class<?>reportClass = getReportClass(reportName);
		
		Field field = reportClass.getField("uiSupportedReportFrontEnds");
		
		retval = (LinkedHashMap<String, String>) field.get(new LinkedHashMap<String, String>());
		
		return retval;
	}
	
	@SuppressWarnings("unchecked")
	public LinkedHashMap<String, ArrayList<String>> getUIReportParameters(String reportName) throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException 
	{
		LinkedHashMap<String, ArrayList<String>> retval = new LinkedHashMap<String, ArrayList<String>>();
		
		Class<?>reportClass = getReportClass(reportName);
		
		Field field = reportClass.getField("uiReportParameters");
		
		retval = (LinkedHashMap<String, ArrayList<String>>) field.get(new LinkedHashMap<String, ArrayList<String>>());
		
		return retval;
	}
	
	public ArrayList<String[]> startReport(String reportName, ReportParameters parameters) throws ClassNotFoundException
	{
		loadReportEntities();
		
		//reportname will be a raw name typically from the jsp layer, will have to look up the 
		
		Class<?>reportClass = buildReportClass(reportName);
		ArrayList<String[]> retval = null;
		//check for reportClass not being null
		
		Report report =  null; 
		try
		{
			//report = (Report) reportClass.getConstructor().newInstance();
			//report = (Report)((Object) reportClass.newInstance());
			
			report = (Report) reportClass.newInstance();
			//report.setChildReport(true);
			
			if(parameters != null)
			{
				for(String paramName : report.getParameters().getSupportedParameters())
				{
					if(parameters.getParameterValues(paramName) != null)
					{
						for(String paramVals : parameters.getParameterValues(paramName))
						{
							report.getParameters().addParameter(paramName, paramVals);
						}
					}
				}
			}
			
			retval	= new ArrayList<String[]>();
			
			for(String[] row : report.startReport())
			{
				retval.add(row);
			}
			
			reportSchema = report.getReportSchema();
			
			if(report.isTimeTrendReport())
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
			else if(report.isStackReport())
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
}