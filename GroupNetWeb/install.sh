#!/bin/bash

rm -rf ~/tomcat_home/webapps/think-forum-web*
cp ./target/think-forum-web.war ~/tomcat_home/webapps/
~/tomcat_home/bin/shutdown.sh
~/tomcat_home/bin/startup.sh
