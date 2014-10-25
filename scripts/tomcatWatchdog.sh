#!/bin/bash

#cronjob to check if tomcat is running, start it if not
#consider that tomcat may already be starting up or shutting down as part of a deployment push

#while this is a helios cronjob, it's up to the implementer (privatelabel, etc) to schedule it in their crontab

#check that the watchdog proc isn't already doing something
#check that this script isn't being executed with a different pid -> already running

if  /bin/ps aux | /bin/grep tomcatWatchdog.sh | /bin/grep -v grep | /bin/grep -v "$$" > /dev/null
then
	#check if watchdog is running, and make sure it's not just us
	echo "Watchdog already running, exiting"
elif ! /bin/ps aux | /bin/grep tomcat | /bin/grep java | /bin/grep -v grep | /bin/grep catalina > /dev/null
then
	env JAVA_HOME=/opt/tomcat/jdk /opt/tomcat/apache-tomee-plus/bin/startup.sh
	
	echo -n "Watchdog restarted tomcat";
	sleep 10;
	
	if /bin/ps -ef | /bin/grep tomcat | /bin/grep java | /bin/grep -v grep | /bin/grep catalina > /dev/null
	then
		echo " and succeeded"
	else
		echo " and failed"
	fi
fi