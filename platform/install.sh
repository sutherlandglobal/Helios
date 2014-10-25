#!/bin/bash

#assume we're in /platform

#skip any user-convienence like adding to a path

#hardcoded archive names
#hardcoded targetdir names
#target dir is ./platform/../../
#also handle the config files


#ant/apache-ant-1.9.4-bin.zip

#unzip
echo -n  "Extracting ant..."
/usr/bin/unzip -q ./ant/apache-ant-1.9.4-bin.zip -d /opt/tomcat 
echo "DONE"

#define ant_home
ANT_HOME=/opt/tomcat/apache-ant-1.9.4

#append to .bash_profile if ant home isn't already defined
if [ ! $(/bin/grep -m 1 ANT_HOME /opt/tomcat/.bash_profile ) ]
then
	echo "Adding ANT_HOME to .bash_profile"
	echo "ANT_HOME=/opt/tomcat/apache-ant-1.9.4" >> /opt/tomcat/.bash_profile
	echo "export ANT_HOME" >> /opt/tomcat/.bash_profile
fi

#####################################
#httpd/httpd-2.4.10.tar.gz

echo -n "Extracting httpd..."
/bin/tar zxf ./httpd/httpd-2.4.10.tar.gz -C /opt/tomcat/
echo "DONE"

#create sym link to uncouple version from path
/bin/ln -s /opt/tomcat/httpd-2.4.10 /opt/tomcat/httpd

#####################################
#jdk/jdk-7u67-linux-x64.tar.gz
echo -n "Extracting the JDK..."
/bin/tar zxf ./jdk/jdk-7u67-linux-x64.tar.gz -C /opt/tomcat/
echo "DONE"

#set java_home
JAVA_HOME=/opt/tomcat/jdk

if [ ! $(/bin/grep -m 1 JAVA_HOME /opt/tomcat/.bash_profile ) ]
then
	echo "Adding JAVA_HOME to .bash_profile"
	echo "JAVA_HOME=/opt/tomcat/jdk" >> /opt/tomcat/.bash_profile
	echo "export JAVA_HOME" >> /opt/tomcat/.bash_profile
fi

#create sym link to uncouple version from path
/bin/ln -s /opt/tomcat/jdk1.7.0_67/ /opt/tomcat/jdk

#####################################
#tomee/apache-tomee-1.6.0-plus.zip
echo -n "Extracting tomee..."
/usr/bin/unzip -q ./tomee/apache-tomee-1.6.0-plus.zip -d /opt/tomcat 
echo "DONE"

#create sym link to uncouple version from path
/bin/ln -s /opt/tomcat/apache-tomee-plus-1.6.0 /opt/tomcat/apache-tomee-plus

#deploy config files
echo -n "Configuring tomee..."
/bin/cp -f ./tomee/setenv.sh /opt/tomcat/apache-tomee-plus/bin/
/bin/cp -f ./tomee/server.xml /opt/tomcat/apache-tomee-plus/conf
/bin/cp -f ./tomee/tomcat-users.xml /opt/tomcat/apache-tomee-plus/conf

#add the jtds lib
/bin/cp -f ./tomee/jtds-1.2.5.jar /opt/tomcat/apache-tomee-plus/lib

#remove the examples and lib dir
/bin/rm -rf  /opt/tomcat/apache-tomee-plus/webapps/docs
/bin/rm -rf  /opt/tomcat/apache-tomee-plus/webapps/examples

#make the startup scripts executable
chmod 775 /opt/tomcat/apache-tomee-plus/bin/startup.sh
chmod 775 /opt/tomcat/apache-tomee-plus/bin/shutdown.sh
chmod 775 /opt/tomcat/apache-tomee-plus/bin/catalina.sh

echo "DONE"

#start tomee
echo "Starting TomEE..."
chmod +x ../scripts/tomcatWatchdog.sh
../scripts/tomcatWatchdog.sh



