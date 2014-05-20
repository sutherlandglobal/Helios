/**
 * 
 */
package helios.report.parameters.sanitize.test;

import helios.report.parameters.sanitize.StringSanitizer;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * @author Jason Diamond
 *
 */
public class StringSanitizerTest extends TestCase 
{
	private String testString;
	private int truncateLen;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	public void setUp()
	{
		testString = "derpderpderp";
		truncateLen = 5;
	}
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	public void tearDown()
	{}
	
	@Test
	public void testTruncateTooLong()
	{
		assertEquals("Truncate a string to longer than it is", testString, StringSanitizer.truncate(testString, testString.length() + 10));
	}
	
	@Test
	public void testReasonableTruncate()
	{
		assertEquals("Truncate a string to an expectable length", testString.substring(0, truncateLen), StringSanitizer.truncate(testString, truncateLen));
	}
	
	@Test
	public void testTruncateNegativeLength()
	{
		assertEquals("Truncate a string to a negative number", testString, StringSanitizer.truncate(testString, -10));
	}

	@Test
	public void testNonAsciiCharStrip()
	{
		assertEquals("Strip non ascii character from string", StringSanitizer.stripNonAscii(testString + "§" + testString), testString+testString);
		assertEquals("Strip non ascii character from string", StringSanitizer.stripNonAscii(testString ), testString);
		
	}
	
	@Test
	public void testTruncateNullString()
	{
		assertEquals("Truncate null string", null, StringSanitizer.truncate(null, 10));
	}
	
	@Test
	public void testStripNullString()
	{
		assertEquals("Truncate null string", null, StringSanitizer.stripNonAscii(null));
	}
	
	@Test
	public void testWhiteSpaceTrimming()
	{
		assertEquals("Blank string", "", StringSanitizer.stripNonAscii("            "));
		assertEquals("Blank string trailing", testString, StringSanitizer.stripNonAscii(testString+"            "));
		assertEquals("Blank string leading", testString, StringSanitizer.stripNonAscii("               " + testString));
		assertEquals("Blank string leading and trailing", testString, StringSanitizer.stripNonAscii("               " + testString + "                 "));
	}
	
	@Test
	public void testGoodSQLDateString()
	{
		assertTrue(StringSanitizer.isValidSQLDate("1985-03-27 23:00:00"));
	}
	
	@Test
	public void testNullSQLDateString()
	{
		assertFalse(StringSanitizer.isValidSQLDate(null));
	}
	
	@Test
	public void testBlankSQLDateString()
	{
		assertFalse(StringSanitizer.isValidSQLDate("     "));
	}
	
	@Test
	public void testEmptySQLDateString()
	{
		assertFalse(StringSanitizer.isValidSQLDate(""));
	}
	
	@Test
	public void testSQLDateStringWithBadMonth()
	{
		assertFalse(StringSanitizer.isValidSQLDate("1985-66-27 23:00:00"));
	}
	
	@Test
	public void testSQLDateStringWithBadDay()
	{
		assertFalse(StringSanitizer.isValidSQLDate("1985-03-99 23:00:00"));
	}
	
	@Test
	public void testSQLDateStringWithBadHours()
	{
		assertFalse(StringSanitizer.isValidSQLDate("1985-03-27 99:00:00"));
	}
	
	@Test
	public void testSQLDateStringWithBadMins()
	{
		assertFalse(StringSanitizer.isValidSQLDate("1985-03-27 23:99:00"));
	}
	
	@Test
	public void testSQLDateStringWithBadSecs()
	{
		assertFalse(StringSanitizer.isValidSQLDate("1985-03-27 23:00:99"));
	}
}
