#!/bin/bash
docker rm judge-c
docker rmi judge-c
docker build -t judge-c .
docker run -v /tmp/judge-info/judge-c/:/usr/src/judge/ --name judge-c judge-c
