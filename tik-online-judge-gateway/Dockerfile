FROM openjdk:11-jdk-oracle
COPY target/tik-online-judge-gateway-0.0.1-SNAPSHOT.jar /
COPY wait-for-it.sh /
CMD ["./wait-for-it.sh","tik-nacos:8848","--morewait","5","--","java","-Dspring.profiles.active=docker","-jar","tik-online-judge-gateway-0.0.1-SNAPSHOT.jar"]
EXPOSE 8888