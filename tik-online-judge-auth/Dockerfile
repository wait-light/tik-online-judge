FROM openjdk:11-jdk-oracle
# ENV JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF8 -Duser.timezone=GMT+08"
COPY target/tik-online-judge-auth-0.0.1-SNAPSHOT.jar /
COPY wait-for-it.sh /
CMD ["./wait-for-it.sh","tik-nacos:8848","tik-mysql:3306","--morewait","5","--","java","-Dspring.profiles.active=docker","-jar","tik-online-judge-auth-0.0.1-SNAPSHOT.jar"]
EXPOSE 8888
