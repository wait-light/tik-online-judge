#!/bin/bash
#set -eu
touch compile.info compile.time input stderr stdout Main.cpp needCompile time_memory
chmod 766 compile.info compile.time input stderr stdout Main.cpp needCompile
chmod 764 time_memory
chown :docker needCompile
compile_begin_time=$[$(date +%s%N)]
#将编译信息输出到/home/tik-online-judge/compile.info
#判断是否需要编译
read isNeedCompile < needCompile
if [ "$isNeedCompile" != "0" ]
then
	g++ -o main Main.cpp 2> /home/tik-online-judge/compile.info
	compile_end_time=$[$(date +%s%N)]
	echo -n $[ ($compile_end_time - $compile_begin_time) / 1000000 ] > compile.time
else
	compile_end_time=$compile_begin_time
fi
/usr/bin/time -f "%M %e" -o time_memory su - tik-online-judge -s /bin/sh -c "exec $@"