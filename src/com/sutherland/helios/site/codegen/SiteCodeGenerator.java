package com.sutherland.helios.site.codegen;

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
    	servletMapping.put("roster", siteProperties.getProperty("API_CLASS_PREFIX") + ".GetRoster");
      	servletMapping.put("parameterData", siteProperties.getProperty("API_CLASS_PREFIX") + ".GetParameterData");
      	servletMapping.put("reportInfo", siteProperties.getProperty("API_CLASS_PREFIX") + ".GetReportInfo");
      	servletMapping.put("siteMetrics", siteProperties.getProperty("API_CLASS_PREFIX") + ".GetSiteMetrics");
      	servletMapping.put("reporting", siteProperties.getProperty("API_CLASS_PREFIX") + ".Reporting");

    	context.put("API_ENDPOINTS", servletMapping);
    	context.put("math", new MathTool());
	}
	
	public void generateServlets()
	{
		String path =  siteProperties.getProperty("SITE_CLASS_PREFIX").replaceAll("\\.", "\\/");
		generateSource("site/servlets/getRoster.vm",  siteProperties.getProperty("SITE_BUILD_DIR") +"/" + path + "/api/GetRoster.java");
		generateSource("site/servlets/getParameterData.vm",  siteProperties.getProperty("SITE_BUILD_DIR") +"/" + path + "/api/GetParameterData.java");
		generateSource("site/servlets/getReportInfo.vm",  siteProperties.getProperty("SITE_BUILD_DIR") +"/" + path + "/api/GetReportInfo.java");
		generateSource("site/servlets/getSiteMetrics.vm",  siteProperties.getProperty("SITE_BUILD_DIR") +"/" + path + "/api/GetSiteMetrics.java");
		generateSource("site/servlets/reporting.vm",  siteProperties.getProperty("SITE_BUILD_DIR") +"/" + path + "/api/Reporting.java");
	}
	
	public void generateLogging()
	{
		String path =  siteProperties.getProperty("SITE_CLASS_PREFIX").replaceAll("\\.", "\\/");
		generateSource("site/log4j/siteLog4JCloser.vm", siteProperties.getProperty("SITE_BUILD_DIR") +"/" + path + "/logging/Log4JCloser.java");
		generateSource("site/log4j/siteLog4JConfig.vm", siteProperties.getProperty("SITE_BUILD_DIR")  + "/webapp/WEB-INF/classes/log4j.properties");
	}
	
	public void generateSiteConfig()
	{
		String path =  siteProperties.getProperty("SITE_CLASS_PREFIX").replaceAll("\\.", "\\/");
		generateSource("site/siteConfig.vm", siteProperties.getProperty("SITE_BUILD_DIR") +"/" + path + "/site/SiteConfig.java");
	}
	
	public String getSiteName()
	{
		return siteProperties.getProperty("HELIOS_SITE_NAME");
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
		
		generateSource("site/tomcat/WEB.xml.vm",  siteProperties.getProperty("SITE_BUILD_DIR") +"/webapp/WEB-INF/WEB.xml");
		
		//delete all servletmapping context keys
	}
	
	private void generateSource(String templateFile, String targetPath)
	{
		File generatedSourceFile;
		
		BufferedWriter writer = null;
		try
		{
			generatedSourceFile = new File(targetPath);
			generatedSourceFile.getParentFile().mkdirs();
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
	
	public void generateSiteUI()
	{
//		 inflated: templates/site/ui/header.vm
//		 inflated: templates/site/ui/index.vm
//		 inflated: templates/site/ui/testResults.vm

		generateSource("site/ui/header.vm", siteProperties.getProperty("SITE_BUILD_DIR") +"/webapp/WebContent/header.jsp");
		generateSource("site/ui/index.vm", siteProperties.getProperty("SITE_BUILD_DIR") +"/webapp/WebContent/index.jsp");
		generateSource("site/ui/testResults.vm", siteProperties.getProperty("SITE_BUILD_DIR") +"/webapp/WebContent/testResults.jsp");
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
			siteCodeGen.generateSiteUI();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
	}
}
