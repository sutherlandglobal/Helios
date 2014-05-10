package helios.data.granularity.user.test;

import helios.data.Datum;
import helios.roster.attributes.BasicRosterAttributes;
import junit.framework.TestCase;

import org.junit.Test;

public class UserGrainsTest extends TestCase 
{

	private String testName;
	private String testTeam;
	private String testProgramName;
	private String testOrgUnitName;
	
	private Datum user;
	
	@Override
	public void setUp()
	{
		testName= "Jason Diamond";
		testTeam = "Bravo Team";
		testProgramName = "Execution.exe";
		testOrgUnitName = "Waste Disposal";
		
		user = new Datum("Jason");
		
		user.addAttribute(BasicRosterAttributes.FULLNAME_ATTR);
		user.addAttribute(BasicRosterAttributes.TEAMNAME_ATTR);
		user.addAttribute(BasicRosterAttributes.PROGRAMNAME_ATTR);
		user.addAttribute(BasicRosterAttributes.ORGUNIT_ATTR);
		
		user.addData(BasicRosterAttributes.FULLNAME_ATTR, testName);
		user.addData(BasicRosterAttributes.TEAMNAME_ATTR, testTeam);
		user.addData(BasicRosterAttributes.PROGRAMNAME_ATTR, testProgramName);
		user.addData(BasicRosterAttributes.ORGUNIT_ATTR, testOrgUnitName);
		
	}
	
	@Test
	public void testGetOrgUnitGrain()
	{
		assertEquals("Test OrgUnit-level granularity", testOrgUnitName, user.getAttributeData(BasicRosterAttributes.ORGUNIT_ATTR).get(0));
	}
	
	@Test
	public void testGetProgramGrain()
	{
		assertEquals("Test program-level granularity", testProgramName, user.getAttributeData(BasicRosterAttributes.PROGRAMNAME_ATTR).get(0));
	}
	
	@Test
	public void testGetTeamGrain()
	{
		assertEquals("Test team-level granularity", testTeam, user.getAttributeData(BasicRosterAttributes.TEAMNAME_ATTR).get(0));
	}
	
	@Test
	public void testAgentGrain()
	{
		assertEquals("Test agent-level granularity", testName, user.getAttributeData(BasicRosterAttributes.FULLNAME_ATTR).get(0));
	}
}
