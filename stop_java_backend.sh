#!/bin/bash

echo "STOPPING TOMCAT... ===================================================================="

TOMCAT=$HOME/development/servers/tomcat/apache-tomcat-7.0.42/

$TOMCAT/bin/shutdown.sh

echo "END ==================================================================================="
