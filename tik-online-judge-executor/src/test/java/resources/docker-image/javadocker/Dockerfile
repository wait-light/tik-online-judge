FROM openjdk:8-jdk-oracle
COPY . /usr/src/judge/
COPY docker-entrypoint.sh /usr/local/bin/
WORKDIR /usr/src/judge
USER tik-online-judge
ENTRYPOINT ["docker-entrypoint.sh"]
# 输入从input中获取，输出定向到output,  2>&1  意思为错误输出等同于输出流
CMD java Main < input 1> stdout 2> stderr
