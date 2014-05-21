/**
 * 
 */
package helios.site;

import helios.api.report.ReportEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


/**
 * The layer between the API and the Jars on the fs. Discover the report classes the API layer should run.
 * 
 * @author Jason Diamond
 *
 */
public abstract class SiteManager 
{
	private static final String REPORT_CLASSNAME_SUFFIX = ".class";
	
	private static final String REPORT_SUPERCLASS_NAME = "helios.report.Report";
	private static final String ROSTER_SUPERCLASS_NAME = "helios.roster.Roster";

	
	private SiteManager() {}
	
	
	public static ArrayList<ReportEntity> getReportsFromJar(String jarPath) throws ClassNotFoundException
	{
		ArrayList<ReportEntity> retval = new ArrayList<ReportEntity>();
		
		JarFile jar = null;
		
		try 
		{

			jar = new JarFile(jarPath);

			//initialJarCount = loadedReports.size();
			//for each class in this jar
			String rawClassName;
			for(JarEntry entry: Collections.list(jar.entries()))
			{
				rawClassName = entry.getName();
				
				if
				(
						rawClassName != null &&
						rawClassName.endsWith(REPORT_CLASSNAME_SUFFIX ) && 
						!rawClassName.contains("$") && 
						!rawClassName.contains(".test.") 
				)
				{
					String superClassName = ReportEntity.getSuperClassName(rawClassName);
					if(superClassName != null && (superClassName.equals(REPORT_SUPERCLASS_NAME) || superClassName.equals(ROSTER_SUPERCLASS_NAME)))
					{
						retval.add(new ReportEntity(rawClassName, jarPath));
					}
				}
			}
			
			//logMessage(Level.INFO, "Loaded " + (loadedReports.size() - initialJarCount) + " classes from" + jarPath);
			
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
		
		return retval;
	}
}
