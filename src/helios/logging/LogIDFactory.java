/**
 * 
 */
package helios.logging;


/**
 * @author Jason Diamond
 *
 */
public class LogIDFactory 
{
	private LogIDFactory(){}
	
	public static String getLogID()
	{
		//avoid the oddly-changing value
		return new Long(System.currentTimeMillis() / 1000L).toString();
	}
}
