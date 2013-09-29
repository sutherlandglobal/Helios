<%@include file="/ui/header.jsp"%>

<%@ page import="java.io.File"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page import="java.util.Calendar"%>

<%!

public String convertGregorianToMySQL(GregorianCalendar date)
{
	String retval = "" + date.get(Calendar.YEAR) + "-";
	
	if(date.get(Calendar.MONTH) + 1 < 10)
	{
		retval += "0";
	}
	
	retval += date.get(Calendar.MONTH) + 1 + "-";
	
	if(date.get(Calendar.DAY_OF_MONTH) < 10)
	{
		retval += "0";
	}
	
	retval += date.get(Calendar.DAY_OF_MONTH) + "+";
	
	if(date.get(Calendar.HOUR_OF_DAY) < 10)
	{
		retval += "0";
	}
	
	retval += date.get(Calendar.HOUR_OF_DAY) + ":";
	
	if(date.get(Calendar.MINUTE) < 10)
	{
		retval += "0";
	}
	
	retval += date.get(Calendar.MINUTE) + ":";
	
	if(date.get(Calendar.SECOND) < 10)
	{
		retval += "0";
	}
	
	retval += date.get(Calendar.SECOND);
	
	return retval;
}

%>

<h3>Framework Changelog and Objectives</h3>

This is a log of my past and current projects and their respective
statuses, mostly revolving around development of the Helios Reporting
Framework for the Private Label program.
<br>
<br>

Any questions, updates, or priority revisions should be directed to me
via
<a href="mailto:jason.diamond@sutherlandglobal.com">Email</a>

<br>
<br>
Upcoming Vacation days:
<br>
<br>

<%
			File thisFile = new File("/opt/tomcat/apache-tomee-plus-1.5.2/webapps/Framework/objectives.jsp");
			
			GregorianCalendar modDate = new GregorianCalendar();
			modDate.setTimeInMillis(thisFile.lastModified());
			
			String modString = convertGregorianToMySQL(modDate);
			//out.println(thisFile.getAbsolutePath());
			//out.println(thisFile.lastModified());
		%>

<h4>This document was last updated on <%= modString.replace('+', ' ') %></h4>


<br>
<a href="#progress"><b>In Progress</b></a>
<br>
<a href="#completed"><b>Completed</b></a>
<br>

<hr size="5" color="blue">

<a name="progress"></a>
<h4>In Progress</h4>
<ul>
	<br>
	<li>Implement and deploy Private Label reports in the
	Helios Reporting Framework, using database ROCJFSDBS27 - <b>TOP
	PRIORITY</b></li>

	<br><br>
	<li>API Implementations - <b>SECOND PRIORITY</b></li>
	<ul>
		<li>Deploy SOAP, REST, etc APIs to uncouple Helios from the program landscape.</li>
		<ul>
			<li>Research deploying API services in Tomcat.</li>
			<li>Research deploying API services in Tomee Plus.</li>
			<li>Deployed JSP frontend to the reporting system as an API stand-in.</li>
		</ul>
	</ul>

	<br>
	<li>Implement Test Plan - <b>THIRD PRIORITY</b></li>
	<ul>
		<li>Refactor JUNIT tests for reporting framework deployment, as well as Private Label reports.</li>
		<ul>
			<li>Completion Date Estimate: Rolling. This will be an ongoing
			process as new classes requiring testing are developed.</li>
		</ul>
	</ul>

	<br>
	<li>Security Vulnerability Auditing - <b>FOURTH PRIORITY</b></li>
	<ul>
		<li>Patched a local file inclusion bug in the dashboard
		component.</li>
		<li>Completion Date Estimate: Rolling. This will be an ongoing process as the framework grows, and new auditing tools are used.</li>
	</ul>
</ul>
<a href="#home">Return to Top</a>

<hr size="5" color="blue">
<a name="completed"></a>








<h4>2013-09-29</h4>
<ul>
	<li>Added Git and Gitbub logos to footers.</li>
	<li>Added proper dns and apache handling of hostname shortcut.</li>
	<li>Platform upgrade of dev environment.</li>
	<li>Platform upgrade of production environment.</li>
	<li>Extensive UAT of dev and production environments.</li>
</ul>
<h4>2013-09-22</h4>
<ul>
	<li>Created new logo and rebranded framework UI and reports.</li>
	<li>Continued SVN to git migration via github.</li>
	<li>Continued UI rebranding and refactoring.</li>
	<li>Platform upgrade of Helios dev environment.</li>
</ul>
<h4>2013-09-5</h4>
<ul>
	<li>Extensive UI refactoring.</li>
	<li>Continued SVN to git migration via github.</li>
	<li>Renamed PIN Consumption to PIN Consumption Rate.</li>
	<li>Renamed PINs Consumed to PINs Consumed Count.</li>
	<li>Researched Mojerra as eventual JSF-based UI replacement.</li>
</ul>
<h4>2013-09-08</h4>
<ul>
	<li>Patched Calls Per Case BIRT report to remove currency formatting.</li>
	<li>Began UI rebranding.</li>
	<li>Removed internal dashboard functionality. Dashboard functionality will be encompassed by consumers of the API.</li>
</ul>
<h4>2013-08-31</h4>
<ul>
	<li>Implemented a startup script to handle iptables rule import upon boot.</li>
	<li>Backed out timegrain parameter setting in implementors of stack reports.</li>
</ul>
<h4>2013-08-25</h4>
<ul>
	<li>Patched Redemption Rate frontend to use correct backend classname.</li>
	<li>Unhardcoded control paths for time and stack metrics.</li>
</ul>
<h4>2013-08-18</h4>
<ul>
	<li>Engaged GTI to update corporate DNS with intuitive name resolution for staging and dev environments.</li>
	<li>Patched system uptime parsing to accomodate wide range of unix uptime output formats.</li>
</ul>
<h4>2013-08-11</h4>
<ul>
	<li>Built BIRT frontends for new metrics: Used Issues, Sold Issues, Updated Issues, Created Cases, Redemption Rate, Calls Per Case.</li>
</ul>
<h4>2013-08-04</h4>
<ul>
	<li>Refactored build structure to be uncoupled from the filesystem.</li>
	<li>Refactored database config files to be framework constants.</li>
	<li>Began UI refactor to support multiple Helios-implementing programs.</li>
</ul>
<h4>2013-07-28</h4>
<ul>
	<li>Continued SVN to git migration via github.</li>
	<li>Added fiscal quarters to generated time intervals</li>
	<li>Added fiscal quarter time granularity.</li>
</ul>
<h4>2013-07-21</h4>
<ul>
	<li>Continued SVN to git migration via github.</li>
	<li>Refactored PrivateLabelReporting API into Helios proper to allow for out-of-the-box API functionality with implementing programs.</li>
	<li>Began refactor of dashboards to use reporting API rather than the birt internal reporting engine.</li>
	<li>Began researching standalone graph image generator as alternative to birt internal reporting engine.</li>
	<li>Removed obsolete scrap from the framework.</li>
	<li>Patched javadoc build targets to deploy javadocs directly.</li>
	<li>Exhaustive load testing of new platform (jdk 1.7, tomee plus, birt 4.2.2).</li>
</ul>
<h4>2013-07-14</h4>
<ul>
	<li>Continued SVN to git migration via github.</li>
	<li>Exhaustive load testing of new platform (jdk 1.7, tomee plus, birt 4.2.2).</li>
</ul>
<h4>2013-07-07</h4>
<ul>
	<li>Began SVN to git migration via github.</li>
	<li>Exhaustive load testing of new platform (jdk 1.7, tomee plus, birt 4.2.2).</li>
</ul>
<h4>2013-06-30</h4>
<ul>
	<li>Gathered additional requirements and established deliverables, features, reports, and possible deployment targets.</li>
	<li>Exhaustive load testing of new platform (jdk 1.7, tomee plus, birt 4.2.2).</li>
</ul>
<h4>2013-06-23</h4>
<ul>
	<li>Completed schedule import script to automate a 40 hours/month manual process.</li>
	<li>Patched a bug in Yearly granularity calculations that would return Monthly granularity.</li>
	<li>Patched the Helios UI to provide options for fiscal date intervals.</li>
	<li>Removed generated parameters clutter from the UI.</li>
	<li>Exhaustive load testing of new platform (jdk 1.7, tomee plus, birt 4.2.2).</li>
	<li>Continued refactoring and testing of date arithmetic in the UI to an internal class.</li>
</ul>
<h4>2013-06-16</h4>
<ul>
	<li>Exhaustive load testing of new platform (jdk 1.7, tomee plus, birt 4.2.2).</li>
	<li>Researching methods to convert existing SVN repository into git repository.</li>
	<li>Continued refactoring of date arithmetic in the UI to an internal class.</li>
</ul>
<h4>2013-06-09</h4>
<ul>
	<li>Exhaustive load testing of new platform (jdk 1.7, tomee plus, birt 4.2.2).</li>
</ul>
<h4>2013-06-02</h4>
<ul>
	<li>Wrote an expect script to automate rsync deployment operation.</li>
	<li>Added support for fiscal quarter and year date intervals and parameters.</li>
	<li>Restructured source and report directories to remove references to database types.</li>
</ul>
<h4>2013-05-26</h4>
<ul>
	<li>Began researching Git as new source control solution.</li>
	<li>Worked with GTI to prep rocjfsdev08 as a staging environment.</li>
	<li>Massive platform upgrade of packages and kernel on rocjfsdev08.</li>
	<li>Upgraded JDK and JRE on rocjfsdev08 to version 1.7.0_19.</li>
	<li>Upgraded Birt viewer on rocjfsdev08 to version 4.2.2.</li>
	<li>Installed and configured Tomee as application server on rocjfsdev08 to potentially replace Tomcat 5.</li>
	<li>Installed and configured Helios on rocjfsdev08, and successfully tested reports on new platform.</li>
	<li>Began refactoring of date arithmetic in the UI to an internal class.</li>
</ul>
<h4>2013-05-19</h4>
<ul>
	<li>Built API formatting functionality for CSV,HTML, and XML outputs.</li>
	<li>Successful testing of HTML and XML formatters.</li>
	<li>Successful testing of Excel integration.</li>
	<li>Proof of Concept for BIRT report with remote XML data source.</li>
	<li>Patched report front ends to supply a source parameter.</li>
</ul>
<h4>2013-05-12</h4>
<ul>
	<li>Working implementation of JSP-based report API.</li>
</ul>
<h4>2013-05-05</h4>
<ul>
	<li>Continued research and design phase for creation of JSP reporting API.</li>
</ul>
<h4>2013-04-28</h4>
<ul>
	<li>Implemented proof of concept for JSP-based report API.</li>
	<li>Began research and  design phase for creation of JSP reporting API.</li>
</ul>
<h4>2013-04-21</h4>
<ul>
	<li>Refactored code to reduce result sets to their most popular N drivers into its own utility function.</li>
	<li>Patched bugs in code to reduce result sets to their most popular N drivers.</li>
	<li>Developed raw JSP API to bypass BIRT viewer to serve metric data directly to any initiator via HTTP.</li>
	<li>Patched logging, API, and dashboards to indicate how reports are initiated.</li>
	<li>Continued testing of forecasting methods.</li>
	<li>Implemented the Revenue Per Call report.</li>
	<li>Grouped revenue reports into a "Revenue" group in the UI.</li>
	<li>Removed useless database connections from composite reports. Database connection management is handled by child reports.</li>
	<li>Added support for agent-specific schedules for the Schedules report and parent reports.</li> 
	<li>Implemented the Teams report to list detailed Team information.</li> 
	<li>Implemented the Agent Progress report to organize a detailed view on agent performance over time.</li>
	<li>Implemented the Net Revenue report.</li>
	<li>Implemented the Net Sales Count report.</li>
	<li>Patched Sales Stack Rank  to use Net Revenue and Net Sales Count reports internally.</li>
</ul>
<h4>2013-04-14</h4>
<ul>
	<li>Completely re-worked report parameter validation to fix several bugs and remove a huge amount of redundant code.</li>
</ul>
<h4>2013-04-07</h4>
<ul>
	<li>Began redesign of report parameter validation mechanism in light of new bugs.</li>
</ul>
<h4>2013-03-31</h4>
<ul>
	<li>Implemented the Open Cases report to count number of cases opened by agents.</li>
	<li>Implemented the PINs Consumed report to count number of PINs consumed by customers.</li>
	<li>Implemented the PIN Consumption report to measure percent of cases where a PIN is consumed by a customer.</li>
</ul>
<h4>2013-03-24</h4>
<ul>
	<li>Continuing research into forecasting libraries.</li>
	<li>Continued developing proofs-of-concept of forecasts with the OpenForecast library. Currently testing forecasts of monthly sales.</li>
	<li>Implemented the Sales Documentation Count report to count number of new cases created by the Sales team. 
	<li>Implemented the Sales Documentation Rate report to measure the documentation rate of the Sales team. 
	<li>Ported Conversion report to parallelize child reports.</li>
</ul>
<h4>2013-03-17</h4>
<ul>
	<li>Continued development of proofs-of-concept of forecasts with the OpenForecast library.</li>
	<li>Design refactoring to accommodate forecast objects.</li>
</ul>
<h4>2013-03-10</h4>
<ul>
	<li>Continuing research into forecasting libraries.</li>
	<li>Developing proofs-of-concept of forecasts with the OpenForecast library.</li>
	<li>As of 03/05/2012, users have made 5,000 report requests of Helios in 2013 alone.</li>
	<li>Patched the Realtime Sales report to display a full dollar amount.</li>
</ul>
<h4>2013-03-03</h4>
<ul>
	<li>Deployed a Proof-of-concept REST API on rocjfsdev07.</li>
</ul>
<h4>2013-02-24</h4>
<ul>
	<li>Patched a dashboard bug that would render viewing subframes unviewable.</li>
</ul>
<h4>2013-02-10</h4>
<ul>
	<li>Completed framework-wide porting of reports to return data in ArrayList form rather than Vector form.</li>
	<li>Updated Help page with current reports and descriptions.</li>
</ul>
<h4>2013-02-03</h4>
<ul>
	<li>Began framework-wide testing and porting of reports to return data in ArrayList form rather than Vector form.</li>
</ul>
<h4>2013-01-27</h4>
<ul>
	<li>Patched Conversion report to report proper percent values.</li>
	<li>Patched Schedule Adherence report to report proper percent values.</li>
</ul>
<h4>2013-01-20</h4>
<ul>
	<li>Ported Sales Stack Rank report to parallelize child reports.</li>
	<li>Ported Attendance Stack Rank report to parallelize child reports.</li>
	<li>Ported Aggregate DSAT report to parallelize child reports.</li>
	<li>Created a generic number formatting utility class to handle presentation-layer issues of formatting data coherently.</li>
</ul>
<h4>2013-01-13</h4>
<ul>
	<li>Private Label Reporting  Javadocs update completed.</li>
	<li>Began massive Private Label Reporting Javadoc update.</li>
	<li>Completed Private Label Reporting SVN commit.</li>
	<li>Implemented a ReportRunner class to support Helios reports running child reports in parallel.</li>
	<li>Implemented the Refund Count report.</li>
</ul>
<h4>2013-01-06</h4>
<ul>
	<li>Helios Javadocs update completed.</li>
	<li>Began massive Private Label Reporting Javadoc update.</li>
	<li>Began Private Label Reporting SVN commit.</li>
	<li>Patched the Conversion report to implement the Sales Count as a child report.</li>
	<li>Removed extraneous Group By SQL from Realtime Sales query.</li>
</ul>
<h4>2012-12-30</h4>
<ul>
	<li>Began enmasse code cleanup.</li>
	<li>Helios Framework SVN commit.</li>
	<li>Began mass update of Javadocs.</li>
</ul>
<h4>2012-12-16</h4>
<ul>
	<li>Patched grid rendering issue in LMI DSAT Case report.</li>
	<li>Extended keyword-matching of survey comments to include multi-word phrases.</li>
</ul>
<h4>2012-12-09</h4>
<ul>
	<li>Added DSAT Case list dashboard.</li>
	<li>Extended testing of DSAT reports.</li>
	<li>Implementation of Aggregate DSAT Count report.</li>
	<li>Production update, finally.</li>
</ul>
<h4>2012-12-02</h4>
<ul>
	<li>Patching DSAT report bugs.</li>
	<li>Extended testing of DSAT reports.</li>
</ul>
<h4>2012-11-25</h4>
<ul>
	<li>Patching DSAT report bugs.</li>
</ul>

<h4>2012-11-18</h4>
<ul>
	<li>Patching BIRT frontends for DSAT reports.</li>
</ul>

<h4>2012-11-11</h4>
<ul>
	<li>Implementing all standard report types in IVR DSAT and related reports.</li>
</ul>

<h4>2012-11-04</h4>
<ul>
	<li>Deployed BIRT frontends for DSAT reports.</li>
	<li>Updated main UI with DSAT reports.</li>
</ul>

<h4>2012-10-28</h4>
<ul>
	<li>Continued testing and patching of DSAT reports.</li>
	<li>Implemented size method for Team object.</li>
	<li>Refactored composite reports for proper exception handling of subreports.</li>
</ul>

<h4>2012-10-21</h4>
<ul>
	<li>Continued testing and patching of DSAT reports.</li>
	<li>Extended testing of custom object aggregation.</li>
	<li>Further research and testing of API setup.</li>
</ul>

<h4>2012-10-14</h4>
<ul>
	<li>Uncoupled Schedule and Shift functionality from Helios Framework. This solves the larger issue of associating custom aggregation points (Schedules, Shifts, Surveys) with users while reducing complexity.</li>
	<li>Refactored existing reports to use custom object functionality.</li>
</ul>

<h4>2012-09-30</h4>
<ul>
	<li>Refactoring the IVR survey queries from a huge wall of SQL to something more readable and managable with minimal performance cost.</li>
	<li>Continued troubleshooting of the Save Team Tracker. Specifically exploring the removal of auto-expiration for DSAT cases.</li>
</ul>

<h4>2012-09-23</h4>
<ul>
	<li>Implemented the LMI DSAT Case list report.</li>
	<li>Implemented the LMI DSAT Case Count report.</li>
	<li>Implemented the IVR DSAT Case list report.</li>
	<li>Implemented the IVR DSAT Case Count report.</li>
</ul>

<h4>2012-09-16</h4>
<ul>
	<li>Patched Schedule Adherence report with updated calculations.</li>
	<li>Renamed column of pl_schedule table from ntlogin to empid to be coherent with data represented.</li>
	<li>Refactored Schedule report and regression testing of Schedule-related reports.</li>
	<li>Patched Attendance Stack Rank report so that decimal places for Minutes Late and Minutes Worked  values are not truncated.</li>
	<li>Resolved versioning crisis in reporting framework's repository.</li>
	<li>Huge update of SVN checkins.</li>
	<li>Continuted research into Tomcat-supported SOAP service methods. Main lead is JAX-WS</li>
</ul>

<h4>2012-09-09</h4>
<ul>
	<li>Troubleshooting Schedule Adherence calculations.</li>
</ul>

<h4>2012-09-02</h4>
<ul>
	<li>Successful replication of LMI reports using new report separators.</li>
	<li>Research into Tomcat-supported SOAP service methods.</li>
</ul>

<h4>2012-08-26</h4>
<ul>
	<li>Patching of report formatters to support multi-byte strings for safe convertion to SQL code.</li>
</ul>

<h4>2012-08-19</h4>
<ul>
	<li>Replacement and testing of multi-byte string report separators.</li>
</ul>

<h4>2012-08-12</h4>
<ul>
	<li>Continued troubleshooting of LMI replication failures for some nodes and reports.</li>
	<li>Proof of concept for generating Greek, Cyrillic, Alphanumeric string combinations</li>
	<li>Proof of concept for using UTF-8 strings as LMI report data separators.</li>
</ul>

<h4>2012-08-05</h4>
<ul>
	<li>Troubleshooting of LMI replication failures for some nodes and reports.</li>
</ul>

<h4>2012-07-29</h4>
<ul>
	<li>Troubleshooting missing skill sets from call data replication.</li>
	<li>Implemented Minutes Worked front and back ends.</li>
	<li>Implemented Attendence Stack Rank front and back ends.</li>
	<li>Patched date processing libraries to return date interval differences with double precision.</li>
</ul>

<h4>2012-07-22</h4>
<ul>
	<li>Began implementation of Attendence Stack Rank front and back ends.</li>
	<li>Implemented Minutes Late report front and back ends.</li> 
	<li>Implemented Late Days report front and back ends.</li>
	<li>Implemented Schedule Adherence report front and back ends.</li>
	<li>Patched Roster report so that calls to Roster.load() do not bypass parameter validation.</li>
	<li>Implemented Schedule report backend to store agent Schedules. This will be a subreport of nearly all other schedule-centric reports.</li>
	<li>Loosely supervised Bret's progress in schedule replication and SQL training.</li>
	<li>Patched parameter validation to compare report type against report-specific-supplied list of acceptable report types.</li>
</ul>

<h4>2012-07-15</h4>
<ul>
	<li>Realized that schedules are very useful and appear often, designing a Roster-like subreport for schedules for use in reports without duplicating code.</li>
	<li>Configured rocjfsdev18 to coherently store schedule information.</li>
	<li>Organized uploading of Private Label agent schedule to rocjfsdev18 for use in schedule-based reports via Bret Comstock.</li>
	<li>Environment setup and configuration for Bret Comstock's interaction with rocjfsdev18.</li>
	<li>Patched issue in roster where users were skipped due to case-sensitive comparison of employee IDs.
	<li>Conducted basic SQL training for Bret Comstock</li>
	<li>Began design and development of several schedule-based reports in Helios. (Schedule, Schedule Adherence, Late Days, Minutes Late, Not Ready Time))</li>
	<li>Continued migration of offsite dev environment from VMware Server Console 2 to VMware ESXI 5 to address numberous bugs and End-of-Lifing of VMware Server Console 2</li> 
	<li>Continued research of API deployment strategies in Tomcat.</li>
</ul>

<h4>2012-07-08</h4>
<ul>
	<li>Patched documentation page to show both Helios and PrivateLabel Javadocs.</li>
	<li>Implemented and deployed watchdog script to restart downed critical services during normal operation and post-reboot.</li>
	<li>Began migration of offsite dev environment from VMware Server Console 2 to VMware ESXI 5 to address numerous bugs and End-of-Lifing of VMware Server Console 2</li> 
	<li>Handled an outage caused by an unscheduled reboot of rocjfsapp18, as reported by Avril.</li>
	<li>Patched issue on rocjfsdev07 resulting from remanents of old version of BIRT Viewer persisting after upgrading the viewer.</li>
	<li>Began research of API deployment strategies in Tomcat.</li>
</ul>

<h4>Historic (pre-rocjfsapp18 deployment)</h4>
<ul>
	<li>Gather department reporting requirements and reconcile with
	pre-existing Proofpoint Executive Reporting project.</li>
	<li>Research methods of connecting to a MS Access database via the
	SUN JDBC drivers.</li>
	<li>Research Java libraries for directly interacting with MS
	Access database files.</li>
	<li>Develop SQL-like interpreter for processing user commands to
	interact with a MS Access database. Support querying fields with
	specified tables, with conditions affecting supplied fields.</li>
	<li>Implement Proof-of-Concepts for interacting with "backend"
	version of existing Private Label database.</li>
	<li>Establish Version Control with SVN.</li>
	<li>Design coherent framework allowing reports to be datasource
	independent. Reports must be tolerant of both user and database error,
	as well as provide functional flexibility to surpass current reporting
	measures. Environmental constraints are the Apache Tomcat application
	server with the BIRT plugin, running on linux.</li>
	<li>Successfully Integrated Reporting Framework with Tomcat and
	the Birt Viewer.</li>
	<li>Implement functionality to arbitrarily aggregate datapoints on
	a per-report basis, be it by team, agent, time granularity, etc.</li>
	<li>Implement Roster report as a standalone report, and with API
	functionality to be used as a sub-report in all other reports requiring
	a list of active support staff.</li>
	<li>Implement logging mechanism for report actions. Supports many
	reports running simultaneously.</li>
	<li>Implement a library to handle conversions between date
	formats.</li>
	<li>Implement a library to handle system call interaction.</li>
	<li>Acquire hardware to use as a staging environment
	(rocjfsdev07).</li>
	<li>Platform configuration migration: Tomcat 7 running as non-root
	user.</li>
	<li>Deployment of staging environment on staging hardware
	(rocjfsdev07).</li>
	<li>Drafted Reporting Framework deployment documentation.</li>
	<li>Implemented Documentation Rate report, qualified by James
	Roffe.</li>
	<li>Implemented Team Documentation Rate report, qualified by James
	Roffe.</li>
	<li>Implemented Top Call Drivers report, qualified by James Roffe.</li>
	<li>Successfully created BIRT Report structure that doesn't run
	all database queries twice.</li>
	<li>Proof-of-Concept for a fixed thread pool in Java.</li>
	<li>Design, develop, and test a multi-threaded implementation of
	MS Access database queries for drastically increased query speed in
	table row traversal, and condition evaluation.</li>
	<li>Platform configuration migration: Tomcat 7 expires/renews
	threads in a disagreeable way for version 2.6.1 of the BIRT Viewer.
	Switched to Tomcat 5 after extensive testing.</li>
	<li>Redesigned Helios landing page. Added reports for most common
	date intervals with generated timestamps.</li>
	<li>Developed Proof-of-Concept perl script to interact with
	LogMeIn reporting mechanism for future reports.</li>
	<li>Framework build automation using Apache Ant. Greatly reduces
	build process duration and complexity.</li>
	<li>Developed perl script to manage Tomcat service restart
	operations.</li>
	<li>Javadoc generation and interfacing with the framework build
	process and Tomcat. Includes internal Java documentation, as well as
	external libraries (Jackcess,JExcel)</li>
	<li>Framework-wide Javadoc tagging.</li>
	<li>Refactoring and expansion of command shell to interact with MS
	Access database files.</li>
	<li>JUnit deployment on development environment.</li>
	<li>Proof-of-Concept JUnit test suite execution and build process
	integration.</li>
	<li>JUnit deployment on staging environment.</li>
	<li>Wrote test results page to aggregate and display results from
	JUnit tests.</li>
	<li>Refactored Helios UI into header,menu,footer, and content script files. Updated the appropriate build targets to reflect the new structure.</li>
	<li>Added the "Powered By" section to the UI page footer.</li>
	<li>Refactored the MS Access Query Threadpool into a generalized utility class.</li>
	<li>Added support for running database queries in parallel, from a single DatabaseConnection object.</li>
	<li>Researched into measures for profiling backend code and garbage collection analysis.</li>
	<li>Began developing test classes to generate meaningful profile data from framework classes.</li>
	<li>Wrote script to process all profile-type tests and generate datafiles for use by respective profiling tools.</li>
	<li>Integrated profile generation into build process.</li>
	<li>Researched methods for generating reports outside of the BIRT Viewer for potential use in periodic email blasts.</li>
	<li>Enforced datasource creation and pre-report setup success requirements on all reports.</li>
	<li>Research methods of interacting with an MS SQL server from Java.</li>
	<li>MySQL and MSSQL database interaction in Java are very similar. Created a parent SQL database connection class to handle user-supplied JDBC drivers to eliminate redundant code.</li>
	<li>Implemented performance tuning for faster MS Access queries.</li>
	<li>Wrote FAQ page.</li>
	<li>Wrote Help page.</li>
	<li>Fixed the UI so that the Header and Menu persist and function	correctly if the body page contains frames.</li>
	<li>Developed personalized reporting dashboards for periodic batch	report generation, supporting output formats of PDF and HTML.</li>
	<li>Developed a web interface to easily navigate users and their dashboard reports.</li>
	<li>Proof of concept for querying an MS SQL database from Java.</li>
	<li>Wrote support for running queries against a single SQL server 	in parallel.</li>
	<li>Switch over completed reports to use the new SQL server.</li>
	<li>Moving logging functionality into its own package.</li>
	<li>Extended logging functionality to allow reports and database connections to write to different logs.</li>
	<li>Completed the PIN Minutes Used report, qualified by James Roffe.</li>
	<li>Completed the Cases Updated Ratio report, qualified by James Roffe.</li>
	<li>Completed the Active Roster report.</li>
	<li>Completed the Case Updated Ratio report, qualified by James Roffe.</li>
	<li>Patched a bug in the Case Updated Ratio report, qualified by Rich Goodenough.</li>
	<li>Reworked the Roster subreport to support wildly varying rosters in response to the Sales Stack Rank report.</li>
	<li>Completed the Sales Roster report.</li>
	<li>Completed the Active Sales Roster report.</li>
	<li>Refactored all database queries to comply with SQL best practices.</li>
	<li>Completed research into using LMI API</li>
	<li>LMI Replication SOAP API proof of concept established for	running reports.</li>
	<li>Established workaround with LMI Technical Account Managers Charles and Justin to address issues of report schema separators appearing in report data.</li>
	<li>Extensive replication testing on entire backlog of LMI data.</li>
	<li>Successful test run of replication of all LMI data.</li>
	<li>Memory analysis-type testing on LMI replication process to track down a brutal leak.</li>
	<li>Refactored replication process into a service.</li>
	<li>LMI Replication established to target tables on ROCJFSDEV18 with frequency of 1 hour.</li>
	<li>Refactored Roster reports to use ROCJFSDBS27.</li>

	<li>Implemented and deployed Realtime Sales reports at team and agent levels.</li>
	<li>Implemented and deployed the Refund Totals report.</li>
	<li>Implemented and deployed the Top Case Drivers report.</li>
	<li>Implemented and deployed the Top Refund Reasons report.</li>
	<li>Implemented and deployed the No Sale Drivers report.</li>
	<li>Added quick-frequency support for dashboard reports.</li>
	<li>Deployed dashboard reports for PL Supervisors and team leads.</li>

	<li>Established requirements for LMI replication for ATT	Connecttech.</li>
	<li>Established LMI replication for ATT	Connecttech.</li>

	<li>Uncoupled LMI Replication code from Helios, and redeployed PL Replication as it's own instance.</li>
	<li>Established source control via SVN onsite at Sutherland for Helios.</li>
	<li>Offloaded generic date validation code to it's own utility class.</li>
	<li>Configured Tomcat to track end-user usage of Helios.</li>
	<li>Heavy redesign of reports and framework.</li>
	<li>Revised framework documentation to prep for production deployment.</li>
	<li>Deployed new version of Helios to the new production environment at rocjfsapp18.</li>
	

</ul>
<a href="#home">Return to Top</a>

<%@include file="/ui/footer.jsp"%>