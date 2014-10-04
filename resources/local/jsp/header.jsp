<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>Helios Data Analysis</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		
		<link rel="shortcut icon" href="<%=request.getContextPath( ) %>/images/favicon.ico?v=<%= (1+Math.random())*1000%>" type="image/x-icon">
		<link rel="icon" href="<%=request.getContextPath( ) %>/images/favicon.ico?v=<%= (1+Math.random())*1000%>" type="image/x-icon">
		<STYLE>
			.warningMessage { color:red; }
			
			html body, td 
			{
				font-family: arial,verdana,helvetica;
    			font-size:10pt;
			}
 
		</STYLE>

	</HEAD>
	<BODY  bgcolor="#ededed" link="#0000FF" vlink="#0000FF">
		
		<%@include file="/browserAssist.jsp" %>
		
		
		
		<a name="home"></a>
		
		<TABLE cellSpacing=0 border=0 cellpadding="2" width="100%">
			<TBODY>
			<!-- <tr><td colspan="2"><hr size="5" color ="blue"></td></tr>-->
			
				<TR>
					<td width = "400" align="left" valign="top">
						<a href="/Framework/"><img height="93" width="400" src="<%=request.getContextPath( ) %>/images/helios_banner.jpg"/></a>
					</td>
					<td width = "400" align="right" valign="top">
					<%
					
	                String host = request.getServerName();
	                String productionUrl = "https://helios.sutherlandglobal.com";

	                if( !host.equals("helios.sutherlandglobal.com") )
					{
						out.println("<b>You're in a dev or staging deployment of Helios.<br>All functionality and data can be assumed to be unstable lies.<br>");
						out.println("<a href=\"https://helios.sutherlandglobal.com\">Go to Production!</a></b>");
					}

					%>
					</td>
				</tr>
			</TBODY>
		</TABLE>
		
		<%@include file="/menu.jsp" %>