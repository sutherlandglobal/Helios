package helios.api.report.test;

import helios.api.report.ReportEntity;
import junit.framework.TestCase;

import org.junit.Test;

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
		String className = "helios/report/test/MockReport.class";
		String expectedClassName = "helios.report.test.MockReport";
		String expectedSuperClassName = "helios.report.Report";
		String expectedReportName = "MockReport";
		
		String jarPath = "/opt/tomcat/Helios/Helios.jar";
		
		ReportEntity ent;
		try 
		{
			ent = new ReportEntity(className,jarPath);
			
			assertNotNull("Entity non-null test", ent);
			assertFalse("Retrieve report name", ent.getReportName().isEmpty());
			assertEquals("Verify class name", expectedClassName, ent.getReportClass() );

			assertEquals("Verify jar path", jarPath, ent.getJarPath() );
			assertEquals("Verify report name", expectedReportName, ent.getReportName());
			assertEquals("Verify superclass name", expectedSuperClassName, ent.getSuperClassName());
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		

	}

}
