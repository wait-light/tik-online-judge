#!/bin/bash
docker rm judge-java
docker rmi judge-java
docker build -t judge-java .
docker run -v /tmp/judge-info/judge-java/:/usr/src/judge/ --name judge-java judge-java
