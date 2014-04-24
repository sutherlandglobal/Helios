#!/usr/bin/perl -w

use strict;

$| = 1;

#retval of 0 indicates to the ant builder that things are good. retval of !0 fails the build
our $retval =0;


print "Restarting tomcat...\n";

if(`ps -ef | grep java | grep catalina | grep -v grep`)
{
	print "Shutting down tomcat\n";
	print `/opt/tomcat/apache-tomee-plus/bin/shutdown.sh`;
	
	while(`ps -ef | grep java | grep catalina | grep -v grep`)
	{
		sleep 2;
	}
}
	
print "Starting up tomcat\n";
print `/opt/tomcat/apache-tomee-plus/bin/startup.sh`;
	
while(!`ps -ef | grep java | grep catalina | grep -v grep`)
{
	sleep 2;
}

#give it some more time to start accepting http traffic
sleep 16;
	
print "DONE\n";

