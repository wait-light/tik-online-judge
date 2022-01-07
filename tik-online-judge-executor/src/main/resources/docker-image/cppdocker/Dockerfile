FROM gcc:9.4.0
RUN useradd tik-online-judge \
    && gpg --keyserver keyserver.ubuntu.com --recv 40976EAF437D05B5 \
    && gpg --export --armor 40976EAF437D05B5  | apt-key add - \
    && gpg --keyserver keyserver.ubuntu.com --recv 3B4FE6ACC0B21F32 \
    && gpg --export --armor 3B4FE6ACC0B21F32  | apt-key add - \
    && cp /etc/apt/sources.list /etc/apt/sources.list.bak \
    && echo "deb http://mirrors.aliyun.com/ubuntu/ xenial main\n \
             deb-src http://mirrors.aliyun.com/ubuntu/ xenial main\n \
             deb http://mirrors.aliyun.com/ubuntu/ xenial-updates main\n \
             deb-src http://mirrors.aliyun.com/ubuntu/ xenial-updates main\n \
             deb http://mirrors.aliyun.com/ubuntu/ xenial universe\n \
             deb-src http://mirrors.aliyun.com/ubuntu/ xenial universe\n \
             deb http://mirrors.aliyun.com/ubuntu/ xenial-updates universe\n \
             deb-src http://mirrors.aliyun.com/ubuntu/ xenial-updates universe\n \
             deb http://mirrors.aliyun.com/ubuntu/ xenial-security main\n \
             deb-src http://mirrors.aliyun.com/ubuntu/ xenial-security main\n \
             deb http://mirrors.aliyun.com/ubuntu/ xenial-security universe\n \
             deb-src http://mirrors.aliyun.com/ubuntu/ xenial-security universe\n" > /etc/apt/sources.list \
    && apt-get update \
    && apt-get install time
COPY . /home/tik-online-judge/
ADD docker-entrypoint.sh /usr/local/bin/
WORKDIR /home/tik-online-judge
ENTRYPOINT ["docker-entrypoint.sh"]
# 输入从input中获取，输出定向到output,  2>&1  意思为错误输出等同于输出流
CMD ./main < input 2> stderr 1> stdout 
#CMD ls /usr/local/bin/ -ali
