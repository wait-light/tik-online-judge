#!/bin/bash
docker rm judge-cpp
docker rmi judge-cpp
docker build -t judge-cpp .
docker run -v /tmp/judge-info/judge-cpp/:/usr/src/judge/ --name judge-cpp judge-cpp

