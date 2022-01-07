#!/bin/bash
#set -eu
touch compile.info compile.time input stderr stdout Main.cpp needCompile
chmod 766 compile.info compile.time input stderr stdout Main.cpp needCompile
compile_begin_time=$[$(date +%s%N)]
#将编译信息输出到/home/tik-online-judge/compile.info
#判断是否需要编译
read isNeedCompile < needCompile
if [ "$isNeedCompile" != "0" ]
then
  echo "编译程序"
  echo "#include<iostream>" > Main.cpp
	g++ -o main Main.cpp 2> /home/tik-online-judge/compile.info
	compile_end_time=$[$(date +%s%N)]
else
	compile_end_time=$compile_begin_time
fi
#编译时间输出到文件
if [ "$isNeedCompile" != "0" ]; then
  echo "编译时间计算中。。。"
  echo -n $[ ($compile_end_time - $compile_begin_time) / 1000000 ] > compile.time
fi

#exec "su - tik-online-judge -s /bin/sh -c $@ "
echo "执行程序"
su - tik-online-judge -s /bin/sh -c "exec $@"