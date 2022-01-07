#!/usr/bin/bash
#set -eu
compile_begin_time=$[$(date +%s%N)]
touch compile.info compile.time input stdout stderr Main.java needCompile
chmod 766 compile.info compile.time input stdout stderr Main.java needCompile
echo "呵呵呵"
#判断是否需要编译
read isNeedCompile < needCompile
if [ "$isNeedCompile" != "0" ];
then
  echo "启动编译"
	javac Main.java -Xstdout /home/tik-online-judge/compile.info
	compile_end_time=$[$(date +%s%N)]
else
	compile_end_time=$compile_begin_time
fi
echo "啦啦啦"
#编译时间输出到文件
echo -n $[($compile_end_time - $compile_begin_time)/1000000] > compile.time
echo "编译时间：$[($compile_end_time - $compile_begin_time)/1000000]"
/usr/bin/time -f "%M %e" -o time_memory su - tik-online-judge -s /bin/sh -c "exec $@"