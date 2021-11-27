FROM openjdk:8-jdk-oracle
# ENV JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF8 -Duser.timezone=GMT+08"
COPY target/tik-online-judge-message-0.0.1-SNAPSHOT.jar /
COPY wait-for-it.sh /
#CMD ["java","-Ddubbo.registry.address=nacos://172.20.0.6:8848","-Dspring.rabbitmq.host=tik-rabbitmq","-Dspring.redis.host=tik-redis","-Dspring.datasource.url=jdbc:mysql://tik-mysql:3306/judge_ums?useUnicode=true&useSSL=false&characterEncoding=utf8","-jar","tik-online-judge-message-0.0.1-SNAPSHOT.jar"]
CMD ["./wait-for-it.sh","tik-nacos:8848","--","java","-Dspring.profiles.active=docker","-jar","tik-online-judge-message-0.0.1-SNAPSHOT.jar"]
EXPOSE 8888