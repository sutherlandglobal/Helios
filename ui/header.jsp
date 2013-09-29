<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>Helios Data Analysis</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=iso-8859-1">
		<LINK href="styles/iv/index.css" type=text/css rel=stylesheet>
		<!--
		<LINK href="ui/images/favicon.ico" type=image/x-icon rel="shortcut icon">
		<LINK href="ui/images/favicon.ico" type=image/x-icon rel="icon">
		-->
		
		<link rel="shortcut icon" href="/Framework/ui/images/favicon.ico?v=<%= (1+Math.random())*1000%>" type="image/x-icon">
		<link rel="icon" href="/Framework/ui/images/favicon.ico?v=<%= (1+Math.random())*1000%>" type="image/x-icon">
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
		
		<%@include file="/ui/browserAssist.jsp" %>
		
		<a name="home"></a>
		
		<TABLE cellSpacing=0 border=0 cellpadding="2" width="100%">
			<TBODY>
			<!-- <tr><td colspan="2"><hr size="5" color ="blue"></td></tr>-->
			
				<TR>
					<td width = "400" align="left" valign="top">
						<a href="/Framework/"><img height="93" width="400" src="<%= "/Framework/ui/images/helios_banner.jpg" %>"/></a>
					</td>
				</tr>
			</TBODY>
		</TABLE>
		
		<%@include file="/ui/menu.jsp" %>