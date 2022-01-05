FROM openjdk:8-jdk-oracle
COPY . /usr/src/judge/
COPY docker-entrypoint.sh /usr/local/bin/
WORKDIR /usr/src/judge
#RUN curl -o RPM-GPG-KEY-CentOS-7 http://mirrors.163.com/centos/7/os/x86_64/RPM-GPG-KEY-CentOS-7 \
#    && curl -o yum-3.4.3-161.el7.centos.noarch.r http://mirrors.163.com/centos/7/os/x86_64/Packages/yum-3.4.3-161.el7.centos.noarch.rpm \
#    && curl -o yum-plugin-fastestmirror-1.1.31-50.el7.noarch.rpm http://mirrors.163.com/centos/7/os/x86_64/Packages/yum-plugin-fastestmirror-1.1.31-50.el7.noarch.rpm \
#    && curl -o yum-metadata-parser-1.1.4-10.el7.x86_64.rpm http://mirrors.163.com/centos/7/os/x86_64/Packages/yum-metadata-parser-1.1.4-10.el7.x86_64.rpm \
#    && curl -o python-iniparse-0.4-9.el7.noarch.rpm http://mirrors.163.com/centos/7/os/x86_64/Packages/python-iniparse-0.4-9.el7.noarch.rpm \
#    && rpm -ivh *.rpm --force --nodeps \
#    && yum install time \
#    && useradd tik-online-judge

#RUN curl -fL -o time.rpm "http://mirror.stream.centos.org/9-stream/BaseOS/source/tree/Packages/time-1.9-18.el9.src.rpm" \
#    && rpm --force -ivh time.rpm \
#    && useradd tik-online-judge
RUN microdnf install time \
    && useradd tik-online-judge

USER tik-online-judge
ENTRYPOINT ["docker-entrypoint.sh"]
# 输入从input中获取，输出定向到output,  2>&1  意思为错误输出等同于输出流
CMD java Main < input 1> stdout 2> stderr
