<%@include file="/header.jsp" %>

<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="java.util.Map.Entry" %>

<%

LinkedHashMap<String,String> questions = new LinkedHashMap<String,String>();

questions.put("Where am I?",
		"You've reached a deployment of Helios, the reporting framework originally written for the NATS Private Label program at Sutherland Global Services."
		);

questions.put("What does Helios do, and why was it built?",
		"Helios was built to provide high-availability, data source-independent, \"realest\"-time visibility into the massive amounts of data accumulated during the " + 
		"course of program operation.  This framework was designed, architected, and developed by Jason Diamond; commissioned by Kris Booth and Louis Giordano."
		);

questions.put("How do I use Helios?",
		"Navigate to the desired site page, and a list of the site's metrics will be presented. Enter the presented report parameters and click the run button. Report execution may take several seconds or minutes depending on the report's complexity, and " + 
		"machine load." 
		);

questions.put("There is a error/problem with a report, or the results are not coherent with expectations. How can this be remedied?",
		"Outstanding, your efforts will contribute to Helios' stability and power. Send an email to Jason Diamond with as " + 
		"much detail as you possibly can provide. Critical details include the report name, parameters used, time executed, steps to reproduce, screenshots, barometric pressure, current day's horoscope, blood-alcohol content, etc. ");

questions.put("I have a non-bug-related suggestion, idea, or feature request. Would you like to hear it?",
		"Yes, I would."
		);

questions.put("Why isn't reporting done by hand?",
		"It was, for a very long time. However it's not realistic, accurate, cost-effective, or even possible to involve the human hand in anything other than a high-level interpretation of " + 
		"data. " +  
		"The scope and size of the data, as well as the level of accuracy necessitated by business decisions, requires an attention to detail that only software can relabily provide." +
		" Additionally, a report can (and most do) consist of thousands of database queries, which can take a human months to inaccurately finesse down to actionable intelligence. " 
		);

questions.put("How big is Helios?",
		"In the neighborhood of 70,000 lines, across 8 languages."
		);

questions.put("Helios is experiencing major problems, or an outage. What should I do?",
		"Call Jason Diamond's Mobile (check Outlook), any time day or night, urgency permitting."
		);
%>
		<h3>Frequently Asked Questions</h3>
<%
			int i = 0;
			for(String question : questions.keySet())
			{
				out.println("<a href=\"#q" + i +"\"><b>" + question + "</b></a><br>");
				i++;
			}

	i = 0;
	for(Entry<String, String> question : questions.entrySet())
	{
		out.println("<hr size=\"5\" color =\"blue\">");
		out.println("<a name = \"q" + i +"\"></a>");
		out.println("<b>" + question.getKey() + "</b><br><br>");
		
		out.println(question.getValue());
		
		out.println("<br><br><a href=\"#home\">Return to Top</a><br>");
		
		i++;
	}

%>
		
<%@include file="/footer.jsp" %>