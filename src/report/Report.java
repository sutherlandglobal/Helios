/**
 * 
 */
package report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import logging.LogIDManager;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.NDC;
import org.omg.CORBA.Any;

import util.date.DateParser;
import exceptions.ReportSetupException;

/**
 * Architecture for building reports.
 * 
 * @author Jason Diamond
 * 
 */
public abstract class Report 
{
	protected Logger logger;
	protected String reportName;
	protected DateParser dateParser;

	public final static String REPORT_CLASSNAME_PARAM = "reportName";
	public final static String START_DATE_PARAM = "startDate";
	public final static String END_DATE_PARAM = "endDate";
	public final static String ROSTER_TYPE_PARAM = "rosterType";
	public final static String TIME_GRAIN_PARAM = "timeGrain";
	public final static String AGENT_NAME_PARAM = "agentName";
	public final static String NUM_DRIVERS_PARAM = "numDrivers";
	
	public final static String SOURCE_PARAM = "Source";
	
	public static final int MAX_PARAM_LEN = 100;

	/**
	 * A stack ranking of agents for a metric. List of agents typically determined by a roster
	 */
	public static final int AGENT_STACK_REPORT = 1;
	
	/**
	 * A trending of an agent for a metric. Should be roster-indifferent.
	 */
	public static final int AGENT_TIME_REPORT = 2;
	
	/**
	 * A stack ranking of agents for a metric. Determined by specified team name.
	 */
	public static final int TEAM_STACK_REPORT = 3;
	
	/**
	 * A stack ranking of agents for a metric. Determined by specified team name.
	 */
	public static final int TEAM_TIME_REPORT = 4;
	
	/**
	 * A stack ranking of agents for a metric. Determined by specified team name.
	 */
	public static final int ALL_STAFF_STACK_REPORT = 5;
	
	/**
	 * A stack ranking of agents for a metric. Determined by specified team name.
	 */
	public static final int ALL_STAFF_TIME_REPORT = 6;

	// only verifiable in child classes
	public final static String REPORT_TYPE_PARAM = "reportType";

	private final String LOGGER_HANDLE = "reporting";

	protected HashMap<String, String> parameters;

	private String errorMsg;
	protected boolean isChildReport;

	protected String logID;
	protected final String LOG_ID_PREFIX = "id=";


	/**
	 * Build the stuff that every report should want. Specifically a logger, and
	 * each report's prerequisite resources like database connections and
	 * rosters. The actual data store interaction is best left to implementing
	 * subclasses.
	 * 
	 * @throws ReportSetupException
	 *             A report will necessarily have a data source, so we need to
	 *             enforce passing problems with establishing a connection up to
	 *             the calling layer. In this case, that's the BIRT viewer.
	 */
	protected Report() throws ReportSetupException {
		logID = LogIDManager.getLogID().toString();

		// attach log id to messages
		if (MDC.get(LOG_ID_PREFIX) == null) {
			MDC.put(LOG_ID_PREFIX, LOG_ID_PREFIX + logID);
		}

		dateParser = new DateParser();

		isChildReport = false;

		if (!setupReport() || !setupDataSourceConnections()
				|| dateParser == null) {
			throw new ReportSetupException("Report setup failed.");
		}

		logger = Logger.getLogger(LOGGER_HANDLE);

		setErrorMsg("");

		parameters = new HashMap<String, String>();
	}

	/**
	 * Attempt to establish connections to all required datasources. A report by
	 * definition has at least one, and possibly many.
	 * 
	 * @return True if the connection was established, false otherwise.
	 */
	protected abstract boolean setupDataSourceConnections();

	/**
	 * Attempt to fulfill the prerequisites of the report.
	 * 
	 * @return True if the setup was run successfully, false otherwise.
	 */
	protected abstract boolean setupReport();

	/**
	 * Process the report.
	 * 
	 * @return The result set, null if the report fails.
	 */
	public ArrayList<String[]> startReport() {
		logger.log(Level.INFO, "Running report " + toString());

		ArrayList<String[]> retval = new ArrayList<String[]>(0);
		if (validateParameters()) {
			try {
				retval = runReport();

				logger.log(Level.INFO, "Report " + reportName
						+ " returning rows: " + retval.size());
			} catch (Throwable e) {
				StringBuilder stackTrace = new StringBuilder();
				stackTrace.append("Exception: "
						+ e.getClass().getCanonicalName() + ": "
						+ e.getMessage() + "\n");

				setErrorMsg(stackTrace.toString());

				for (StackTraceElement st : e.getStackTrace()) {
					stackTrace.append(st.toString());
					stackTrace.append("\n");
				}

				logger.log(Level.ERROR, stackTrace.toString());
			}
		} else {
			setErrorMsg("Parameter validation failed. Double-check report parameters: "
					+ getErrorMsg());
			logger.log(Level.ERROR, errorMsg);
		}

		return retval;
	}

	/**
	 * Retrieve any error messages.
	 * 
	 * @return The error message string.
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * Set an error message.
	 * 
	 * @param errorMsg The error message string.
	 */
	public void setErrorMsg(String errorMsg) 
	{
		this.errorMsg = errorMsg;
	}

	/**
	 * Process the child report. Only report.startReport should call this.
	 * 
	 * 
	 * @return The result set.
	 * 
	 * @throws Any
	 *             exception generated by children, up to its caller,
	 *             startReport() or another proper handling mechanism
	 * 
	 */
	protected abstract ArrayList<String[]> runReport() throws Exception;

	/**
	 * Validate the report's parameters. Each implementor of Report could
	 * possibly validate parameters differently.
	 * 
	 * @return True if the report's parameters are valid and viable to run the
	 *         report. False otherwise.
	 */
	protected abstract boolean validateParameters();

	/**
	 * Close the report. Subclasses (reports) need to run super.close() to hit
	 * this, any resources owned by Report bleed out.
	 */
	public void close() {
		logger.log(Level.INFO, "Closing report: " + reportName);

		// child reports shouldn't be able to close the logger's filehandlers,
		// save that for the parent report

		// shutdown the logger
		// on second thought, don't shutdown the logger. log4j runs it kind of
		// like a connection pool maintained by the java proc
		// close it via termination of the java process or by cycling tomcat,
		// very important a tomcat restart terms the java proc
		if (!isChildReport) {
			// LogManager.shutdown();
			MDC.getContext().clear();
		}
	}

	/**
	 * Mark the report as a child or parent report.
	 * 
	 * @param isChild
	 *            True if the report is a child of another report, false
	 *            otherwise.
	 */
	public void setChildReport(boolean isChild) {
		isChildReport = isChild;

		if (isChildReport) {
			NDC.pop();
		}
	}

	/**
	 * Generate a string that summarizes this report object. Information
	 * includes report name, parameters defined and their respective values, if
	 * any. Useful in logging.
	 * 
	 * @return The summary string.
	 */

	public String toString() {
		StringBuilder output = new StringBuilder();

		output.append(reportName + " with parameters: ");

		for (Entry<String, String> param : parameters.entrySet()) {
			output.append(param.getKey());
			output.append(" => ");
			output.append(param.getValue());
			output.append(", ");
		}

		return output.toString();
	}

	/**
	 * Set a named report util.parameter to the supplied value.
	 * 
	 * @param key
	 *            Report util.parameter name.
	 * @param value
	 *            Parameter value..
	 * 
	 */

	public void setParameter(String key, String value) 
	{
		if(key.length() > MAX_PARAM_LEN)
		{
			key = key.substring(0, MAX_PARAM_LEN);
		}
		
		if(value.length() > MAX_PARAM_LEN)
		{
			value = value.substring(0,MAX_PARAM_LEN);
		}
		
		parameters.put(key, value);
	}

	/**
	 * Set a named report util.parameter to the supplied value. The
	 * util.parameter hashmap is a string => string mapping, so this is a
	 * shortcut method that will convert an int value to a string.
	 * 
	 * @param key
	 *            Report util.parameter name.
	 * @param value
	 *            Parameter value.
	 * 
	 */
	public void setParameter(String key, int value) 
	{
		setParameter(key, "" + value);
	}

	/**
	 * Retrieve a named report util.parameter to the supplied value.
	 * 
	 * @param key
	 *            Report util.parameter name.
	 * 
	 * @return Value stored by the util.parameter, if any.
	 */

	public String getParameter(String key) 
	{
		return parameters.get(key);
	}

	/**
	 * Set this report's log ID
	 * 
	 * @param logID
	 *            Log ID to apply to this report.
	 * 
	 */

	public void setLogID(String logID) {
		this.logID = logID;
	}
	
    /**
     * Determine if this report is a time-centric (AGENT_TIME/TEAM_TIME) report.
     *  
     * @return      True if the report is a time-centric report. False otherwise.
     */
    public boolean isTimeReport()
    {
            String reportType = getParameter(REPORT_TYPE_PARAM);
            
            return 
            (
            		reportType != null &&
            		(
            				reportType.equals("" + Report.AGENT_TIME_REPORT) || 
            				reportType.equals("" + Report.TEAM_TIME_REPORT) || 
            				reportType.equals("" + Report.ALL_STAFF_TIME_REPORT)
            		)
            );
    }
    
    public boolean isStackReport()
    {
        String reportType = getParameter(REPORT_TYPE_PARAM);
        
        return 
        (
        		reportType != null &&
        		(
        				reportType.equals("" + Report.AGENT_STACK_REPORT) || 
        				reportType.equals("" + Report.TEAM_STACK_REPORT) || 
        				reportType.equals("" + Report.ALL_STAFF_STACK_REPORT)
        		)
        );
    }

}
