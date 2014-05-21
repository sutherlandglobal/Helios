package helios.ui;

import java.util.LinkedHashMap;

public abstract class ComponentFactory 
{
	public static String getHTMLHeader(String title)
	{
		StringBuilder sb = new StringBuilder();
		
//		<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
//		<HTML>
//			<HEAD>
//				<TITLE>Helios Data Analysis</TITLE>
//				<META http-equiv=Content-Type content="text/html; charset=utf-8">
//				
//				<link rel="shortcut icon" href="/Framework/images/favicon.ico?v=<%= (1+Math.random())*1000%>" type="image/x-icon">
//				<link rel="icon" href="/Framework/images/favicon.ico?v=<%= (1+Math.random())*1000%>" type="image/x-icon">
//				<STYLE>
//					.warningMessage { color:red; }
//					
//					html body, td 
//					{
//						font-family: arial,verdana,helvetica;
//		    			font-size:10pt;
//					}
//		 
//				</STYLE>
//
//			</HEAD>
//			<BODY  bgcolor="#ededed" link="#0000FF" vlink="#0000FF">
//				
//				<%@include file="/browserAssist.jsp" %>
//				
//				
//				
//				<a name="home"></a>
//				
//				<TABLE cellSpacing=0 border=0 cellpadding="2" width="100%">
//					<TBODY>
//					<!-- <tr><td colspan="2"><hr size="5" color ="blue"></td></tr>-->
//					
//						<TR>
//							<td width = "400" align="left" valign="top">
//								<a href="/Framework/"><img height="93" width="400" src="<%= "/Framework/images/helios_banner.jpg" %>"/></a>
//							</td>
//							<td width = "400" align="right" valign="top">
//							<%
//							
//			                String host = request.getServerName();
//			                String productionUrl = "https://helios.sutherlandglobal.com";
//
//			                if( !host.equals("helios.sutherlandglobal.com") )
//							{
//								out.println("<b>You're in a dev or staging deployment of Helios.<br>All functionality and data can be assumed to be unstable lies.<br>");
//								out.println("<a href=\"https://helios.sutherlandglobal.com\">Go to Production!</a></b>");
//							}
//
//							%>
//							</td>
//						</tr>
//					</TBODY>
//				</TABLE>
		
		return sb.toString();
	}
	
	public static String buildMenu(LinkedHashMap<String,String> menuEntries)
	{
		StringBuilder sb = new StringBuilder();
		
		return sb.toString();
	}
	
	public static String buildMetricTable()
	{
		StringBuilder sb = new StringBuilder();
		
//		<div class="roundedtable">
//		<table style="border: 3px #0000FF solid" id="mainTable" cellpadding="0" cellspacing="0">
//			<thead>
//				<tr>
//					<th id="metricsHeader">Metrics</th>
//					<th id="rosterHeader">Roster</th>
//					<th id="paramsHeader">Parameters</th>
//				</tr>
//			</thead>
//			<tbody>
//				<tr>
//					<td id="metricsCol" align="left" valign="top" style="border: 2px #0000FF solid">
//						<!--  report names -->
//						<p><b>Select Metrics</b></p> 
//						<select style="background-color: #ededed; border: 1px solid #0000FF;" id="metricSelectList" multiple></select>
//					</td>
//					<td id="rosterCol" align="left" valign="top" style="border: 2px #0000FF solid">
//						<!-- user and team lists --> 
//						
//						<table id="rosterTable" border="0" cellpadding="0" cellspacing="0" >
//						<tr>
//						<td valign="top">
//							<p><b>Select Teams</b></p>
//							<select style="background-color: #ededed; border: 1px solid #0000FF;" id="teamSelectList" multiple></select> 
//						</td>
//						<td valign="top">
//							<p><b>Select Agents</b></p>
//							<select style="background-color: #ededed; border: 1px solid #0000FF;" id="agentSelectList" multiple></select>
//						</td>
//						</tr>
//						</table>
//					</td>
//
//					<td id="paramsCol" valign="top" align="left"
//						style="border: 2px #0000FF solid">
//						<table id="paramsTable" border="0" cellpadding="0" cellspacing="0">
//							<tbody>
//								<tr>
//									<td><b>Select Parameters</b><br>
//									<br></td>
//								</tr>
//								<tr id="selectMetricRow">
//									<td>Select a Metric.</td>
//								</tr>
//
//								<tr id="reportInfoRow">
//									<td>
//										<p id="metricName">Metric Name</p>
//										<p id="metricDesc">Metric Description</p> <br>
//									</td>
//								</tr>
//
//								<tr id="reportTypesRow">
//									<td><label id="reportTypesLabel" for="reportTypesSelectList">Choose
//											a report type: </label> <!-- available report types --> <select
//										id="reportTypesSelectList"></select></td>
//								</tr>
//
//								<tr id="dateIntervalsRow">
//									<td><label id="dateIntervalLabel"
//										for="dateIntervalsSelectList">Choose a date interval: </label>
//										<!-- available Date intervals --> <select
//										id="dateIntervalsSelectList">
//											<option value="Today">Today</option>
//											<option value="Yesterday">Yesterday</option>
//											<option value="This Week">This Week</option>
//											<option value="Last Week">Last Week</option>
//											<option value="This Month">This Month</option>
//											<option value="Last Month">Last Month</option>
//											<option value="This Quarter">This Quarter</option>
//											<option value="Last Quarter">Last Quarter</option>
//											<option value="This Fiscal Quarter">This Fiscal
//												Quarter</option>
//											<option value="Last Fiscal Quarter">Last Fiscal
//												Quarter</option>
//											<option value="This Fiscal Year">This Fiscal Year</option>
//											<option value="Last Fiscal Year">Last Fiscal Year</option>
//											<option value="This Year">This Year</option>
//											<option value="Last Year">Last Year</option>
//									</select></td>
//								</tr>
//
//								<tr id="timeGrainsRow">
//									<td><label id="timeGrainLabel" for="timeGrainsSelectList">Choose
//											a time granularity: </label> <!-- available time grains --> <select
//										id="timeGrainsSelectList"></select></td>
//								</tr>
//
//								<tr id="userGrainsRow">
//									<td><label id="userGrainsLabel" for="userGrainsSelectList">Choose
//											a user granularity: </label> <!-- available time grains --> <select
//										id="userGrainsSelectList"></select></td>
//								</tr>
//
//								<tr id="numDriversRow">
//									<td><label id="numDriversLabel" for="numDriversSelectList">Choose
//											number of drivers: </label> <!-- available driver counts --> <select
//										id="numDriversSelectList"></select></td>
//								</tr>
//
//								<tr id="reportFrontEndsRow">
//									<td><label id="reportFrontEndLabel"
//										for="reportFrontEndSelectList">Choose a format: </label> <!-- available report formats -->
//										<select id="reportFrontEndsSelectList">
//									</select></td>
//								</tr>
//								
//								<tr id="autoRefreshRow">
//									<td><label id="autoRefreshLebel" for="autoRefreshCheckBox">Auto Refresh</label> 
//										<input type="checkbox" id="autoRefreshCheckBox" value="autoRefresh">
//									</td>
//								</tr>
//
//								<tr id="reportLaunchButtonRow">
//									<td>
//										<button id="reportLaunchButton" type="button"
//											onclick="launchReport()">Run!</button>
//									</td>
//								</tr>
//							<tbody>
//						</table>
//					</td>
//
//				</tr>
//			</tbody>
//		</table>
//		</div>
		
		return sb.toString();
	}
	
	public static String getHTMLFooter()
	{
		StringBuilder sb = new StringBuilder();
		
//		<%
//		int imgDim = 40;
//		%>
//
//		<hr size="5" color ="blue">
//
//		<table width="100%">
//			<tr>
//				<td width = "30%" align="left">
//					<img src="<%= request.getContextPath( ) + "/images/suth_logo.jpg" %>"></img>
//				</td>
//				<td width="70%" align="right" valign="top">
//				<b>Powered By:</b>
//					<a href="http://www.linux.org/"><IMG src="<%= request.getContextPath( ) +"/images/linux_logo.jpg\" alt=\"Eclipse\" title=\"Linux\" width=\"" + imgDim + "\" height=\"" + imgDim + "\" border=\"0\"" %>"></a>
//					<a href="http://www.eclipse.org/"><IMG src="<%= request.getContextPath( ) +"/images/eclipse_logo.jpeg\" alt=\"Eclipse\" title=\"Eclipse\" width=\"" + imgDim + "\" height=\"" + imgDim + "\" border=\"0\"" %>"></a>
//					<a href="http://www.git-scm.com/"><IMG src="<%= request.getContextPath( ) +"/images/git_logo.png\" alt=\"Git\" title=\"Git\" width=\"" + imgDim + "\" height=\"" + imgDim + "\" border=\"0\"" %>"></a>
//					<a href="http://www.github.com/"><IMG src="<%= request.getContextPath( ) +"/images/github_logo.png\" alt=\"GitHub\" title=\"GitHub\" width=\"" + imgDim + "\" height=\"" + imgDim + "\" border=\"0\"" %>"></a>
//					<a href="http://www.java.com/"><IMG src="<%= request.getContextPath( ) +"/images/java_logo.png\" alt=\"Java\" title=\"Java\" width=\"" + imgDim + "\" height=\"" + imgDim + "\" border=\"0\"" %>"></a>
//					<a href="http://tomcat.apache.org/"><IMG src="<%= request.getContextPath( ) +"/images/tomcat_logo.jpg\" alt=\"Apache Tomcat\" title=\"Apache Tomcat\" width=\"" + imgDim + "\" height=\"" + imgDim + "\" border=\"0\"" %>"></a>
//					<a href="http://ant.apache.org/"><IMG src="<%= request.getContextPath( ) +"/images/ant_logo.gif\" alt=\"Apache Ant\" title=\"Apache Ant\" width=\"" + imgDim + "\" height=\"" + imgDim + "\" border=\"0\"" %>"></a>
//					<a href="http://www.jquery.com/"><IMG src="<%= request.getContextPath( ) +"/images/jquery_logo.jpg\" alt=\"jQuery\" title=\"jQuery\" width=\"" + imgDim + "\" height=\"" + imgDim + "\" border=\"0\"" %>"></a>
//					<a href="http://www.centos.org/"><IMG src="<%= request.getContextPath( ) +"/images/centos_logo.gif\" alt=\"CentOS\" title=\"CentOS\" width=\"" + imgDim + "\" height=\"" + imgDim + "\" border=\"0\"" %>"></a>
//					<a href="http://www.oracle.com/technetwork/java/javaee/jsp/index.html"><IMG src="<%= request.getContextPath( ) +"/images/jsp_logo.jpg\" alt=\"Java Server Pages\" title=\"Java Server Pages\" width=\"" + imgDim + "\" height=\"" + imgDim + "\" border=\"0\"" %>"></a>
//					<a href="http://www.samba.org/"><IMG src="<%= request.getContextPath( ) +"/images/samba_logo.gif\" alt=\"Samba\" title=\"Samba\" width=\"" + imgDim + "\" height=\"" + imgDim + "\" border=\"0\""%>"></a>
//					<a href="http://www.junit.org/"><IMG src="<%= request.getContextPath( ) +"/images/junit_logo.gif\" alt=\"JUnit\" title=\"JUnit\" width=\"" + imgDim + "\" height=\"" + imgDim + "\" border=\"0\""%>"></a>
//					<a href="http://www.perl.org/"><IMG src="<%= request.getContextPath( ) +"/images/perl_logo.jpg\" alt=\"Perl\" title=\"Perl\" width=\"" + imgDim + "\" height=\"" + imgDim + "\" border=\"0\""%>"></a>
//					<a href="http://en.wikipedia.org/wiki/JavaScript/"><IMG src="<%= request.getContextPath( ) +"/images/javascript_logo.jpg\" alt=\"JavaScript\" title=\"JavaScript\" width=\"" + imgDim + "\" height=\"" + imgDim + "\" border=\"0\""%>"></a>
//					<a href="http://office.microsoft.com/en-us/access/"><IMG src="<%= request.getContextPath( ) +"/images/ms_access_logo.png\" alt=\"Microsoft Access\" title=\"Microsoft Access\" width=\"" + imgDim + "\" height=\"" + imgDim + "\" border=\"0\""%>"></a>
//					<a href="http://www.microsoft.com/sqlserver/en/us/default.aspx"><IMG src="<%= request.getContextPath( ) +"/images/SQL_logo.gif\" alt=\"Microsoft SQL Server\" title=\"Microsoft SQL Server\" width=\"" + imgDim + "\" height=\"" + imgDim + "\" border=\"0\""%>"></a>
//					<a href="http://www.php.net"><IMG src="<%= request.getContextPath( ) +"/images/php_logo.jpg\" alt=\"PHP\" title=\"PHP\" width=\"" + imgDim + "\" height=\"" + imgDim + "\" border=\"0\""%>"></a>
//					
//					
//				</TD>
//			</TR>
//		</table>
//
//
//		<hr size="5" color ="blue">
//
//		<script language="JavaScript"><!--
//		var name = "jason.diamond";
//		var domain = "sutherlandglobal.com";
//		document.write('Bug reports to <a href=\"mailto:' + name + '@' + domain + '\">');
//		document.write('Jason Diamond</a>');
//		// --></script>
//
//			</BODY>
//		</HTML>
		
		return sb.toString();
	}
}
