#!/usr/bin/bash
#set -eu
compile_begin_time=$[$(date +%s%N)]
touch compile.info compile.time input stdout stderr Main.java needCompile time_memory
chmod 766 compile.info compile.time input stdout stderr Main.java needCompile
chmod 764 time_memory
#判断是否需要编译
read isNeedCompile < needCompile
if [ "$isNeedCompile" != "0" ];
then
	javac Main.java -Xstdout /home/tik-online-judge/compile.info
	compile_end_time=$[$(date +%s%N)]
	#编译时间输出到文件
  echo -n $[($compile_end_time - $compile_begin_time)/1000000] > compile.time
else
	compile_end_time=$compile_begin_time
fi

/usr/bin/time -f "%M %e" -o time_memory su - tik-online-judge -s /bin/sh -c "exec $@"