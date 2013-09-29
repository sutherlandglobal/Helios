<%@include file="/ui/header.jsp"%>
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

cpu usage
memory usage
uptime

 -->

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

//birt versions
String viewerVersion = "4.2.2";
String engineVersion = "4.2.2";

//tomcat version
String tomcatVersion = application.getServerInfo();
tomcatVersion =  tomcatVersion.substring(  tomcatVersion.indexOf("/") + 1);

//tomee version

%>

<table width="100%" border="1">
        <th>Platform Info</th>
        <th>System Info</th>
        <tr>
                <td width="50%" valign="top">
                <b>Viewer Version : </b><%=viewerVersion%><br>
                <b>Engine Version: </b><%= engineVersion %><br>
                <b>Tomcat Version: </b><%= tomcatVersion %><br>
                <b>JRE version: </b><%=javaVersionMessage%><br>
                
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
</table>


<!-- Deployment TOC -->

<table width="100%" border="1">
        <th colspan='2'>Programs</th>
<tr>
<td width="50%">
<ul>
<li><a href="../PrivateLabel"><b>Private Label</b></a></li>
<li><a href="../Proofpoint"><b>Proofpoint</b></a></li>
<li><a href="../Hughes"><b>Hughes</b></a></li>
</ul>
</td>

<td width="50%">
<ul>
</ul>
</td>
</tr>
</table>


<%@include file="/ui/footer.jsp"%>