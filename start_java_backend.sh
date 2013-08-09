#!/bin/bash

echo "STARTING MAVEN BUILD... ==============================================================="
# Run Maven build on backend for deploying to local Tomcat

## with tests:
# mvn -f backend/java-backend/pom.xml package

## without tests:
mvn -f backend/java-backend/pom.xml package -Dmaven.test.skip=true

echo "STARTING TOMCAT... ===================================================================="
TOMCAT=$HOME/development/servers/tomcat/apache-tomcat-7.0.42/

$TOMCAT/bin/startup.sh

echo "DONE =================================================================================="
echo ""
echo "NOTE: To stop Tomcat execute the following command:"
echo "./stop_java_backend.sh"
echo ""
echo "END ==================================================================================="
