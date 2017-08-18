#!/bin/bash

~/tomcat_home/bin/shutdown.sh

git pull

cd ../GroupNetCore
ant
cd ../GroupNetWeb

mvn package

rm -rf ~/tomcat_home/webapps/GroupNetWeb*
cp ./target/GroupNetWeb.war ~/tomcat_home/webapps/
~/tomcat_home/bin/startup.sh

tail -f ~/tomcat_home/logs/catalina.out
