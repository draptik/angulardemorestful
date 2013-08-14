#!/bin/bash
# http://stackoverflow.com/questions/3510673/find-and-kill-a-process-in-one-line-using-bash-and-regex

kill $(ps aux|grep '[f]rontend-web-server.js'|awk '{print $2}')