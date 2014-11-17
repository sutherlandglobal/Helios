package com.sutherland.helios.api.report.test;

import junit.framework.TestCase;

import org.junit.Test;

import com.sutherland.helios.api.report.ReportEntity;

/**
 * Test info storage of a Report Entity for use in the reporting API.
 * 
 * @author Jason Diamond
 *
 */
public class ReportEntityTest extends  TestCase 
{
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	public void setUp()
	{}
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	public void tearDown()
	{}
	
	@Test
	public void testBasicInit()
	{
		String className = "com/sutherland/helios/report/test/MockReport.class";
		String expectedClassName = "com.sutherland.helios.report.test.MockReport";
		String expectedSuperClassName = "com.sutherland.helios.report.Report";
		
		String jarPath = "/opt/tomcat/Helios/Helios.jar";
		
		ReportEntity ent;
		try 
		{
			ent = new ReportEntity(className,jarPath);
			
			assertNotNull("Entity non-null test", ent);
			assertEquals("Verify class name", expectedClassName, ent.getReportClassName() );

			assertEquals("Verify jar path", jarPath, ent.getJarPath() );
			assertEquals("Verify superclass name", expectedSuperClassName, ent.getSuperClassName());
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		

	}

}
