#!/bin/bash

#bash script to easily do a restart of tomcat

if  /bin/ps aux | /bin/grep tomcatRestart.sh | /bin/grep -v grep | /bin/grep -v "$$" > /dev/null
then
	echo "Restart already running, exiting"
else
	echo "Restarting tomcat"

 	if /bin/ps aux | /bin/grep tomcat | /bin/grep java | /bin/grep -v grep | /bin/grep catalina > /dev/null
 	then	

		echo "Executing shutdown"
		/opt/tomcat/apache-tomee-plus/bin/shutdown.sh
	
		while /bin/ps aux | /bin/grep tomcat | /bin/grep java | /bin/grep -v grep | /bin/grep catalina > /dev/null
		do
			sleep 2
		done
		
		#buy some time for everything to wind down
		sleep 4
	fi
	
	echo "Executing startup"
	env JAVA_HOME=/opt/tomcat/jdk /opt/tomcat/apache-tomee-plus/bin/startup.sh
	
	while ! /bin/ps aux | /bin/grep tomcat | /bin/grep java | /bin/grep -v grep | /bin/grep catalina > /dev/null
	do
		sleep 2
	done
	
	#buy some time for the ports to come up and accept http traffic
	sleep 16
	
	echo "Done"
fi