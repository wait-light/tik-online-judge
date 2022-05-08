# 使用

1. 环境安装
	1. Docker
		- 安装Docker
		  windows下直接使用Docker Desktop
		  Linux下可以使用一键脚本（https://www.runoob.com/docker/ubuntu-docker-install.html）
		- 开启Docker本地TCP端口：
		  Docker Desktop可以在设置中开启
		  Linux系统下修改文件/lib/systemd/system/docker.service。将ExecStart一行替换为ExecStart=/usr/bin/dockerd -H unix:///var/run/docker.sock -H tcp://0.0.0.0:2375	
	
	2. MySQL
	```shell
	docker run --name mysql -p 3306:3306 -d -e MYSQL_ROOT_PASSWORD=root -v 项目根目录/resources/sql/:/docker-entrypoint-initdb.d/ mysql:5.7
	```
	
	3. Redis
	```shell
	docker run --name redis -p 6379:6379 -d redis
	```

	4. RabbitMQ
	```shell
	docker run -d --name rabbitmq -e RABBITMQ_DEFAULT_USER=executor -e RABBITMQ_DEFAULT_PASS=123456 rabbitmq:3-management
	```

	5. nacos
	```shell
	docker run --name nacos -e MODE=standalone -p 8848:8848 -d nacos/nacos-server:2.0.2
	```

	6. 评判容器
	在项目根目录/tik-online-judge-executor/src/main/resources/docker-image/javadocker 下执行
	```shell
	docker build -t judge-java .
	```
	项目根目录/tik-online-judge-executor/src/main/resources/docker-image/cppdocker 下执行
	```shell
	docker build -t judge-cpp .
	```

2. 编译项目

在项目根目录执行
```shell
mvn package
```

3. 运行项目
参照以下命令运行编译出的jar包
```shell
java -jar xxx.jar
```

若是使用IDE 可以直接利用IDE尝试编译、运行项目
