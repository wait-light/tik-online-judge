FROM gcc:9.4.0
COPY . /usr/src/judge/
ADD docker-entrypoint.sh /usr/local/bin/
WORKDIR /usr/src/judge
USER tik-online-judge
ENTRYPOINT ["docker-entrypoint.sh"]
# 输入从input中获取，输出定向到output,  2>&1  意思为错误输出等同于输出流
CMD ./main < input 2> stderr 1> stdout 
#CMD ls /usr/local/bin/ -ali
