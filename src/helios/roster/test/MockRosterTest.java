package helios.roster.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import junit.framework.TestCase;

import org.junit.Test;

public class MockRosterTest extends TestCase 
{
	private MockRoster roster;
	private Comparator<String[]> sortByUserID;
	
	public void setUp()
	{
		sortByUserID = new Comparator<String[]>()
		{

			@Override
			public int compare(String[] o1, String[] o2) 
			{
				return o1[0].compareTo(o2[0]);
			};
		};
		
		try 
		{
			roster = new MockRoster();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(roster == null)
			{
				assertTrue("Mock Roster construction failed", false);
			}
		}
	}
	
	@Test
	public void testUnfilteredRosterOutput()
	{
		ArrayList<String[]> expectedResults = new ArrayList<String[]>();
		
		expectedResults.add(new String[]{"001", "101", "Kane", "Patrick", "901", "Kane:Patrick", "USA:", "true"});
		expectedResults.add(new String[]{"002", "202", "Datzyuk", "Pavel", "802", "Datzyuk:Pavel", "Russia:", "true"});
		expectedResults.add(new String[]{"003", "303", "Malkin", "Geno", "703", "Malkin:Geno", "Russia:", "true"});
		expectedResults.add(new String[]{"004", "404", "Messier", "Mark", "604", "Messier:Mark", "Canada:", "false"});
		expectedResults.add(new String[]{"005", "505", "Gretzky", "Wayne", "505", "Gretzky:Wayne", "Canada:", "false"});
		expectedResults.add(new String[]{"006", "606", "Howe", "Gordie", "406", "Howe:Gordie", "Canada:", "false"});
		expectedResults.add(new String[]{"007", "707", "Sakic", "Joe", "307", "Sakic:Joe", "Canada:", "false"});
		expectedResults.add(new String[]{"008", "808", "Modano", "Mike", "208", "Modano:Mike", "USA:", "false"});
		expectedResults.add(new String[]{"009", "909", "Hull", "Brett", "109", "Hull:Brett", "USA:Canada:", "false"});
				
//		roster.addTeamFilter("USA");
//		roster.addTeamFilter("Canada");
//		roster.addTeamFilter("Russia");
		
		roster.getParameters().addTeamName("USA");
		roster.getParameters().addTeamName("Canada");
		roster.getParameters().addTeamName("Russia");
		
		ArrayList<String[]> actualResults = roster.startReport(); 

		assertEquals("Resultset size equality", expectedResults.size(), actualResults.size());
		
		Collections.sort(actualResults, sortByUserID);
		Collections.sort(expectedResults, sortByUserID);

		for(int i =0; i<expectedResults.size(); i++)
		{
			assertTrue("Compare resultsets row: " + i, Arrays.equals(expectedResults.get(i), actualResults.get(i)));
		}
	}
	
	@Test
	public void testUserFilteredRosterOutput()
	{
		ArrayList<String[]> expectedResults = new ArrayList<String[]>();
		
		expectedResults.add(new String[]{"001", "101", "Kane", "Patrick", "901", "Kane:Patrick", "USA:", "true"});
		expectedResults.add(new String[]{"002", "202", "Datzyuk", "Pavel", "802", "Datzyuk:Pavel", "Russia:", "true"});
				
//		roster.addUserFilter("Kane:Patrick");
//		roster.addUserFilter("Datzyuk:Pavel");
		
		roster.getParameters().addAgentName("Kane:Patrick");
		roster.getParameters().addAgentName("Datzyuk:Pavel");
		
		ArrayList<String[]> actualResults = roster.startReport(); 

		assertEquals("Resultset size equality", expectedResults.size(), actualResults.size());
		
		Collections.sort(actualResults, sortByUserID);
		Collections.sort(expectedResults, sortByUserID);

		for(int i =0; i<expectedResults.size(); i++)
		{
			assertTrue("Compare resultsets row: " + i, Arrays.equals(expectedResults.get(i), actualResults.get(i)));
		}
	}
	
	@Test
	public void testTeamFilteredRosterOutput()
	{
		ArrayList<String[]> expectedResults = new ArrayList<String[]>();
		

		expectedResults.add(new String[]{"001", "101", "Kane", "Patrick", "901", "Kane:Patrick", "USA:", "true"});
		expectedResults.add(new String[]{"004", "404", "Messier", "Mark", "604", "Messier:Mark", "Canada:", "false"});
		expectedResults.add(new String[]{"005", "505", "Gretzky", "Wayne", "505", "Gretzky:Wayne", "Canada:", "false"});
		expectedResults.add(new String[]{"006", "606", "Howe", "Gordie", "406", "Howe:Gordie", "Canada:", "false"});
		expectedResults.add(new String[]{"007", "707", "Sakic", "Joe", "307", "Sakic:Joe", "Canada:", "false"});
		expectedResults.add(new String[]{"008", "808", "Modano", "Mike", "208", "Modano:Mike", "USA:", "false"});
		expectedResults.add(new String[]{"009", "909", "Hull", "Brett", "109", "Hull:Brett", "USA:Canada:", "false"});
				
//		roster.addTeamFilter("USA");
//		roster.addTeamFilter("Canada");
		
		roster.getParameters().addTeamName("USA");
		roster.getParameters().addTeamName("Canada");
		
		ArrayList<String[]> actualResults = roster.startReport(); 

		assertEquals("Resultset size equality", expectedResults.size(), actualResults.size());
		
		Collections.sort(actualResults, sortByUserID);
		Collections.sort(expectedResults, sortByUserID);

		for(int i =0; i<expectedResults.size(); i++)
		{
			assertTrue("Compare resultsets row: " + i, Arrays.equals(expectedResults.get(i), actualResults.get(i)));
		}
	}
}

