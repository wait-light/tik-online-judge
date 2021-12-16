#!/bin/bash
#set -eu
compile_begin_time=$[$(date +%s%N)]
touch compile.info compile.time input stderr stdout Main.cpp needCompile
chmod 666 compile.info compile.time input stderr stdout Main.cpp needCompile
#将编译信息输出到/usr/src/judge/compile.info
#判断是否需要编译
read isNeedCompile < needCompile
if [ "$isNeedCompile" != "0" ]
then
	gcc -o main Main.c 2> /usr/src/judge/compile.info
	compile_end_time=$[$(date +%s%N)]
else
	compile_end_time=$compile_begin_time
fi
#编译时间输出到文件
echo -n $[ ($compile_end_time - $compile_begin_time) / 1000000 ] > compile.time
exec "$@"

