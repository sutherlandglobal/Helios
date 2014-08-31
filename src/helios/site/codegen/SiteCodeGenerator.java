package helios.site.codegen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.tools.generic.MathTool;

public class SiteCodeGenerator 
{
	private Properties siteProperties;
	private VelocityEngine engine;
	private VelocityContext context;
	private LinkedHashMap<String,String> servletMapping;
	
	public SiteCodeGenerator(String propertiesFile) throws FileNotFoundException, IOException
	{
		siteProperties = new Properties();
		siteProperties.load(new FileInputStream(propertiesFile));

		engine = new VelocityEngine();
		engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath"); 
		engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		
		context = new VelocityContext();

    	for (String key : siteProperties.stringPropertyNames()) 
    	{
    		context.put(key, siteProperties.getProperty(key));
    	}
    	
    	servletMapping = new LinkedHashMap<String,String>();
    	servletMapping.put("agentNames", siteProperties.getProperty("API_CLASS_PREFIX") + ".GetAgentNames");
    	servletMapping.put("teamNames", siteProperties.getProperty("API_CLASS_PREFIX") + ".GetTeamNames");
      	servletMapping.put("reportParameters", siteProperties.getProperty("API_CLASS_PREFIX") + ".GetParameters");
      	servletMapping.put("reportInfo", siteProperties.getProperty("API_CLASS_PREFIX") + ".GetReportInfo");
      	servletMapping.put("reportTypes", siteProperties.getProperty("API_CLASS_PREFIX") + ".GetReportTypes");
      	servletMapping.put("siteMetrics", siteProperties.getProperty("API_CLASS_PREFIX") + ".GetSiteMetrics");
      	servletMapping.put("supportedFrontEnds", siteProperties.getProperty("API_CLASS_PREFIX") + ".GetSupportedFrontEnds");
      	servletMapping.put("timeGrains", siteProperties.getProperty("API_CLASS_PREFIX") + ".GetTimeGrains");
      	servletMapping.put("userGrains", siteProperties.getProperty("API_CLASS_PREFIX") + ".GetUserGrains");
      	servletMapping.put("reporting", siteProperties.getProperty("API_CLASS_PREFIX") + ".Reporting");

    	context.put("API_ENDPOINTS", servletMapping);
    	context.put("math", new MathTool());
	}
	
	public void generateServlets()
	{
		generateSource("servlets/getAgentNames.vm",  siteProperties.getProperty("SITE_BUILD_DIR") +"/" + siteProperties.getProperty("SITE_CLASS_PREFIX") + "/api/GetAgentNames.java");
		generateSource("servlets/getParameters.vm",  siteProperties.getProperty("SITE_BUILD_DIR") +"/" + siteProperties.getProperty("SITE_CLASS_PREFIX") + "/api/GetParameters.java");
		generateSource("servlets/getReportInfo.vm",  siteProperties.getProperty("SITE_BUILD_DIR") +"/" + siteProperties.getProperty("SITE_CLASS_PREFIX") + "/api/GetReportInfo.java");
		generateSource("servlets/getReportTypes.vm",  siteProperties.getProperty("SITE_BUILD_DIR") +"/" + siteProperties.getProperty("SITE_CLASS_PREFIX") + "/api/GetReportTypes.java");
		generateSource("servlets/getSiteMetrics.vm",  siteProperties.getProperty("SITE_BUILD_DIR") +"/" + siteProperties.getProperty("SITE_CLASS_PREFIX") + "/api/GetSiteMetrics.java");
		generateSource("servlets/getSupportedFrontEnds.vm",  siteProperties.getProperty("SITE_BUILD_DIR") +"/" + siteProperties.getProperty("SITE_CLASS_PREFIX") + "/api/GetSupportedFrontEnds.java");
		generateSource("servlets/getTeamNames.vm",  siteProperties.getProperty("SITE_BUILD_DIR") +"/" + siteProperties.getProperty("SITE_CLASS_PREFIX") + "/api/GetTeamNames.java");
		generateSource("servlets/getTimeGrains.vm",  siteProperties.getProperty("SITE_BUILD_DIR") +"/" + siteProperties.getProperty("SITE_CLASS_PREFIX") + "/api/GetTimeGrains.java");
		generateSource("servlets/getUserGrains.vm",  siteProperties.getProperty("SITE_BUILD_DIR") +"/" + siteProperties.getProperty("SITE_CLASS_PREFIX") + "/api/GetUserGrains.java");
		generateSource("servlets/reporting.vm",  siteProperties.getProperty("SITE_BUILD_DIR") +"/" + siteProperties.getProperty("SITE_CLASS_PREFIX") + "/api/Reporting.java");
	}
	
	public void generateLogging()
	{
		generateSource("siteLog4JCloser.vm", siteProperties.getProperty("SITE_BUILD_DIR") +"/" + siteProperties.getProperty("SITE_CLASS_PREFIX") + "/logging/Log4JCloser.java");
		generateSource("siteLog4JConfig.vm", siteProperties.getProperty("SITE_CLASS_DIR")  + "/log4j.properties");
	}
	
	public void generateSiteConfig()
	{
		generateSource("siteConfig.vm", siteProperties.getProperty("SITE_BUILD_DIR") +"/" + siteProperties.getProperty("SITE_CLASS_PREFIX") + "/site/SiteConfig.java");
	}
	
	public void generateWebXML()
	{
		/*
		      	<servlet>
        			<servlet-name>reporting</servlet-name>
        			<servlet-class>uac.api.Reporting</servlet-class>
    			</servlet>
    			<servlet-mapping>
        			<servlet-name>reporting</servlet-name>
        			<url-pattern>/reporting</url-pattern>
    			</servlet-mapping>
		 */
		
		//update context with servlet mapping
		
		//target file is WEB.xml in WEB-INF/classes
		
		generateSource("WEB.xml.vm", siteProperties.getProperty("SITE_DIR") + "/WEB-INF/WEB.xml");
		
		//delete all servletmapping context keys
	}
	
	private void generateSource(String templateFile, String targetPath)
	{
		File generatedSourceFile;
		
		BufferedWriter writer = null;
		try
		{
			generatedSourceFile = new File(targetPath);
	    	
	    	Template template = engine.getTemplate("templates/" + templateFile);
	    	
	        writer = new BufferedWriter(new FileWriter(generatedSourceFile));
	    	template.merge(context, writer);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
	    	if(writer != null)
	    	{
	    		try 
	    		{
	    			writer.flush();
					writer.close();
				} 
	    		catch (IOException e) 
	    		{
					e.printStackTrace();
				}
	    	}
		}
	}
	
	public static void main(String[] args)
	{
		try 
		{
			SiteCodeGenerator siteCodeGen = new SiteCodeGenerator(args[0]);
			siteCodeGen.generateSiteConfig();
			siteCodeGen.generateLogging();
			siteCodeGen.generateWebXML();
			siteCodeGen.generateServlets();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
	}
}
