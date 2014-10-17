<%@include file="/header.jsp"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.io.FileOutputStream"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.InputStreamReader"%>

<!--  

helios build #
tomee version
tomcat version
jquery version

cpu usage
memory usage
uptime

 -->

<script type="text/javascript" src="<%=request.getContextPath( ) %>/js/jquery-1.10.2.min.js"></script>

<%

String javaVersion = System.getProperty("java.version");

Runtime r = Runtime.getRuntime();

String javaVersionMessage = javaVersion;

String cpuCount = "" + r.availableProcessors();
String freeMem = "" + r.freeMemory() / 1000000;
String totalMem = "" + r.totalMemory() / 1000000;
String maxMem ="" + r.maxMemory() / 1000000;

String uptimeString = "";
String loadAverages = "";

Process uptimeProc = r.exec("/usr/bin/uptime");

BufferedReader in = new BufferedReader(new InputStreamReader(uptimeProc.getInputStream()));
String uptimeCmdOutput = in.readLine();
if (uptimeCmdOutput != null) 
{
	String[] uptimeFields = uptimeCmdOutput.substring(uptimeCmdOutput.indexOf(" up ")+3).split("\\,") ;
	
	for(int i=0; i< uptimeFields.length && !uptimeFields[i].contains("user"); i++)
	{
		uptimeString += uptimeFields[i] + ", ";
	}
	
	uptimeString = uptimeString.substring(0, uptimeString.length() - 2);
	
	loadAverages = uptimeCmdOutput.substring(uptimeCmdOutput.indexOf("load average: ")+ "load average: ".length() ).trim();
}

//tomcat version
String tomcatVersion = application.getServerInfo();
tomcatVersion =  tomcatVersion.substring(  tomcatVersion.indexOf("/") + 1);

//tomee version

%>
<link rel="stylesheet" href="<%=request.getContextPath( ) %>/css/helios_landing_page.css" type="text/css"  />
<div class="roundedtable">
<table width="100%" >
		<thead>
        <th>Platform Info</th>
        <th>System Info</th>
        </thead>
        <tbody>
        <tr>
                <td width="50%" valign="top">
                <b>Tomcat Version: </b><%= tomcatVersion %><br>
                <b>JRE Version: </b><%=javaVersionMessage%><br>
                <b>jQuery Version: </b><script type="text/javascript">document.write($().jquery); </script><br>
                
                <!-- 
                Build info for deployments
                 -->
                
                </td>
                
                <td width="50%" valign="top">
                 <b>CPU Count : </b><%=cpuCount%><br>
                <b>Free Memory: </b><%= freeMem %> Mb<br>
                <b>Total Memory: </b><%= totalMem %> Mb<br>
                <b>Max Memory: </b><%=maxMem%> Mb<br>
                

                <b>Uptime: </b><%= uptimeString  %><br>
                <b>Load: </b><%= loadAverages  %><br>


                </td>
</tr>
</tbody>
</table>
</div>

<!-- Deployment TOC -->
<div class="roundedtable">
<table width="100%">
	<thead>
        <th colspan='2'>Sites</th>
     </thead>
     <tbody>
<tr>
<td width="50%">
<ul>
<li><a href="../Hughes"><b>Hughes</b></a></li>
<li><a href="../PrivateLabel"><b>Private Label</b></a></li>

<%
String hostname = request.getServerName();

if( hostname.contains("helios-dev") || hostname.contains("rocjfsdev07") )
{
	out.println("<li><a href=\"../UAC\"><b>Union Aerospace Corporation</b></a></li>");
	out.println("<li><a href=\"../Zorg\"><b>Zorg Industries</b></a></li>");
}
%>

</ul>
</td>

<td width="50%">
<ul>
</ul>
</td>

</tr>
</tbody>
</table>
</div>

<%@include file="/footer.jsp"%>