-- MySQL dump 10.13  Distrib 5.7.36, for Linux (x86_64)
--
-- Host: localhost    Database: judge_cms
-- ------------------------------------------------------
-- Server version	5.7.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cms_comment`
--

CREATE DATABASE IF NOT EXISTS `judge_cms`;
use judge_cms;
DROP TABLE IF EXISTS `cms_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_comment` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` bigint(20) unsigned DEFAULT NULL COMMENT '父id',
  `solution_id` bigint(11) unsigned DEFAULT NULL COMMENT '题解id',
  `uid` bigint(11) unsigned DEFAULT NULL COMMENT '评论人',
  `floor_id` bigint(20) unsigned DEFAULT NULL COMMENT '层id',
  `content` text COLLATE utf8mb4_unicode_ci COMMENT '评论内容',
  `create_time` datetime DEFAULT NULL COMMENT '评论时间',
  `status` bit(1) DEFAULT b'1' COMMENT '状态(1启用/0禁用)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_comment`
--

LOCK TABLES `cms_comment` WRITE;
/*!40000 ALTER TABLE `cms_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_group`
--

DROP TABLE IF EXISTS `cms_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '群组名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(20) unsigned DEFAULT NULL COMMENT '创建人id',
  `status` tinyint(3) unsigned DEFAULT '1' COMMENT '状态',
  `avatar` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_group`
--

LOCK TABLES `cms_group` WRITE;
/*!40000 ALTER TABLE `cms_group` DISABLE KEYS */;
INSERT INTO `cms_group` VALUES (15,'18计算机科学与技术一班','2021-11-20 15:04:13',1,1,'https://tik-online-judge.oss-cn-hangzhou.aliyuncs.com/2021-11-20/a5e2d90b-afa5-4bb8-af72-a1cb01aea491_marian-kroell-qElMHWePpok-unsplash.jpg');
/*!40000 ALTER TABLE `cms_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_group_task`
--

DROP TABLE IF EXISTS `cms_group_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_group_task` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `group_id` bigint(20) unsigned DEFAULT NULL COMMENT '群组id',
  `task_id` bigint(20) unsigned DEFAULT NULL COMMENT '任务id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_group_task`
--

LOCK TABLES `cms_group_task` WRITE;
/*!40000 ALTER TABLE `cms_group_task` DISABLE KEYS */;
INSERT INTO `cms_group_task` VALUES (4,15,4);
/*!40000 ALTER TABLE `cms_group_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_group_user`
--

DROP TABLE IF EXISTS `cms_group_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_group_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
  `group_id` bigint(20) unsigned DEFAULT NULL COMMENT '群组id',
  `user_type` tinyint(4) unsigned DEFAULT NULL COMMENT '群组用户类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_group_user`
--

LOCK TABLES `cms_group_user` WRITE;
/*!40000 ALTER TABLE `cms_group_user` DISABLE KEYS */;
INSERT INTO `cms_group_user` VALUES (9,1,15,1),(14,3,15,0);
/*!40000 ALTER TABLE `cms_group_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_invite`
--

DROP TABLE IF EXISTS `cms_invite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_invite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `initiator` bigint(20) DEFAULT NULL COMMENT '邀请人',
  `invitees` bigint(20) DEFAULT NULL COMMENT '受邀人',
  `create_time` datetime DEFAULT NULL COMMENT '邀请时间',
  `status` int(11) DEFAULT '0' COMMENT '邀请状态',
  `group_id` bigint(20) DEFAULT NULL COMMENT '邀请到的群组',
  `update_time` datetime DEFAULT NULL COMMENT '处理时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='邀请信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_invite`
--

LOCK TABLES `cms_invite` WRITE;
/*!40000 ALTER TABLE `cms_invite` DISABLE KEYS */;
INSERT INTO `cms_invite` VALUES (1,1,3,'2021-11-20 21:06:31',1,15,'2021-11-20 21:07:01'),(2,1,3,'2021-11-20 21:06:55',1,15,NULL),(3,1,3,'2021-11-20 22:47:20',2,15,'2021-11-20 22:47:43'),(4,1,3,'2021-11-20 22:48:43',1,15,'2021-11-20 22:48:49'),(5,1,3,'2021-11-20 22:48:57',1,15,'2021-11-20 22:49:02'),(6,1,3,'2021-11-20 22:49:09',2,15,'2021-11-20 22:55:06'),(7,1,3,'2021-11-20 22:55:32',2,15,'2021-11-20 22:55:36'),(8,1,3,'2021-11-20 22:55:44',1,15,'2021-11-20 22:55:47');
/*!40000 ALTER TABLE `cms_invite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_solution`
--

DROP TABLE IF EXISTS `cms_solution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_solution` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` bigint(20) unsigned DEFAULT NULL COMMENT '题解用户id',
  `content` text COLLATE utf8mb4_unicode_ci COMMENT '题解内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` bit(1) DEFAULT b'1' COMMENT '状态(1启用/0状态)',
  `problem_id` bigint(20) DEFAULT NULL COMMENT '问题id',
  `title` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_solution`
--

LOCK TABLES `cms_solution` WRITE;
/*!40000 ALTER TABLE `cms_solution` DISABLE KEYS */;
INSERT INTO `cms_solution` VALUES (1,1,'- 步骤1\n\n呵呵呵\n- 步骤2\n\n呵呵呵\n- 步骤3','2021-11-08 16:05:06','2021-11-09 22:16:53',_binary '',1,'asdasd'),(2,3,'# 安装\n\n1. 检查JAVA_HOME环境变量\n\n   ![image.png](https://i.loli.net/2021/10/08/x69jbLTE2d8ZtG5.jpg)\n\n2. 解压核心程序压缩包\n\n   放在一个非中文，无空格的文件夹中。\n\n3. 配置Maven环境变量\n\n   - MAVEN_HOM（M2_HOME）\n\n     地址为刚才解压出的文件的根目录。\n\n     ![image.png](https://i.loli.net/2021/10/08/Wq39JRDbjNUg1MP.jpg)\n\n   - 添加path\n\n     ![image.png](https://i.loli.net/2021/10/08/uHqeAPJWxlGIoD5.jpg)\n   \n4. 验证是否安装成功\n   在cmd中执行mvn -v，若是有相关版本输出，说明安装成功了。\n   ![image.png](https://i.loli.net/2021/10/08/fmhue5d9DLkT1rp.jpg)\n\n\n\n# Maven工程目录结构\n```\n		Hello\n		|---src\n		|---|---main\n		|---|---|---java\n		|---|---|---resources\n		|---|---test\n		|---|---|---java\n		|---|---|---resources\n		|---pom.xml\n```\n根目录：工程名\nsrc目录：源码\npom.xml：Maven的核心配置文件\nmain目录：存放主程序\ntest目录：存放测试程序\njava目录：存放Java源文件\nresources目录：存放框架或者其他工具的配置文件\n\n\n\n# Maven常用命令\n\n**执行与构建过程相关的Maven命令，必须进入pom.xml所在的目录。**\n\n**构建过程主要环节**\n\n①**清理**：删除以前的编译结果，为重新编译做好准备。\n②**编译**：将 Java 源程序编译为字节码文件。\n③**测试**：针对项目中的关键点进行测试，确保项目在迭代开发过程中关键点的正确性。\n④**报告**：在每一次测试后以标准的格式记录和展示测试结果。\n⑤**打包**：将一个包含诸多文件的工程封装为一个压缩文件用于安装或部署。Java 工程对应 jar 包，Web工程对应 war 包。\n⑥**安装**：在 Maven 环境下特指将打包的结果——jar 包或 war 包安装到本地仓库中。\n⑦**部署**：将打包的结果部署到远程仓库或将 war 包部署到服务器上运行\n\n## 命令\n\n- mvn clean 清理\n- mvn compile 编译主程序\n- mvn test-compile 编译测试程序\n- mvn test 执行测试\n- mvn package 打包\n- mvn install 安装当前Maven工程到仓库\n- mvn site 生成站点\n\n更多命令可以到本文**生命周期**中查看\n\n\n\n# Maven setting配置\n\n**maven默认配置setting.xml位置**：[用户家目录]\\\\.m2，即C:\\Users\\\\[用户名]\\\\.m2\n\n## 指定Maven工程JDK版本\n\n```xml\n<profile>\n    <id>jdk-1.8</id>\n    <activation>\n        <activeByDefault>true</activeByDefault>\n        <jdk>1.8</jdk>\n    </activation>\n    \n    <properties>\n        <maven.compiler.source>1.8</maven.compiler.source>\n        <maven.compiler.target>1.8</maven.compiler.target>\n        <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>\n    </properties>\n</profile>\n```\n\n\n\n## 仓库\n\nMaven的核心程序中仅仅定义了抽象的生命周期，但是具体的工作必须由特定的插件来完成。而插件本身并不在Maven的核心程序中。当执行Maven命令需要用到某些插件时，Maven首先会到本地仓库中寻找，若是不存在则会到中央仓库下载，若是无法连接到中心仓库，或者插件不存在，则构建失败。\n\n**本地仓库默认位置**：[用户家目录]\\\\.m2\\\\repository ，即C:\\Users\\\\[用户名]\\\\.m2\\\\repository\n\n### 仓库保存的内容\n\n- Maven自身所需要的插件\n- 第三方框架或者工具的jar包\n- 自己开发的Maven工程\n\n### 修改仓库\n\n打开Maven根目录下的conf目录下的setting.xml\n\n#### 本地仓库\n\n修改\\<localRepository\\>\\</localRepository\\>\n\n```xml\n<!-- xxxx替换为指定的目录 -->\n<localRepository>xxxx</localRepository>\n```\n\n#### 远程仓库\n\n修改\\<mirrors\\>\\</mirrors\\>，添加mirror或者修改mirror。由于Maven中心仓库在中国下载速度慢，可以替换为国内仓库镜像。如下为阿里云仓库。\n\n```xml\n<mirror>\n    <id>alimaven</id>\n    <mirrorOf>central</mirrorOf>\n    <name>aliyun maven</name>\n    <url>http://maven.aliyun.com/nexus/content/repositories/central/</url>\n</mirror>\n```\n\n## 完整配置\n\n```xml\n<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<settings xmlns=\"http://maven.apache.org/SETTINGS/1.0.0\"\n  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd\">\n\n  <localRepository>D:\\apache-maven-3.6.3\\maven-repository</localRepository>\n\n  <pluginGroups>\n  </pluginGroups>\n    \n  <proxies>\n  </proxies>\n\n  <servers>\n  </servers>\n    \n  <mirrors>\n    <mirror>\n      <id>alimaven</id>\n      <mirrorOf>central</mirrorOf>\n      <name>aliyun maven</name>\n      <url>http://maven.aliyun.com/nexus/content/repositories/central/</url>\n    </mirror>\n  </mirrors>\n\n  <profiles>\n    <profile>\n      <id>jdk-1.8</id>\n      <activation>\n        <activeByDefault>true</activeByDefault>\n        <jdk>1.8</jdk>\n      </activation>\n\n      <properties>\n        <maven.compiler.source>1.8</maven.compiler.source>\n        <maven.compiler.target>1.8</maven.compiler.target>\n        <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>\n      </properties>\n    </profile>\n  </profiles>\n</settings>\n\n```\n\n\n\n# POM\n\n含义：Project Object Model（项目对象模型）\n\n## 坐标\n\n1. 使用下面三个向量唯一定位一个Maven工程\n\n- groupid：公司或组织域名倒序 + 项目名\n\n  ```xml\n  <groupid>com.atguigu.maven</groupid>\n  ```\n\n- artifactid：模块名\n\n  ```xml\n  <artifactid>Hello</artifactid>\n  ```\n\n- version：版本\n\n  ```xml\n  <version>1.0.0</version>\n  ```\n\n2. 仓库与坐标的对应关系\n\n   仓库中的文件路径与坐标一一对应。如上的坐标对应的仓库路径为：com/atguigu/maven/Hello/1.0.0\n\n## 依赖\n\n**样例**\n\n```xml\n<dependencies>\n    <!-- 单个依赖 -->\n    <!-- dependency必须在dependencies之中 -->\n    <dependency>\n        <!-- 公司或组织域名倒序 + 项目名 -->\n        <groupId>org.slf4j</groupId>\n        <!-- 模块名 -->\n        <artifactId>slf4j-log4j12</artifactId>\n        <!-- 版本 -->\n        <version>1.6.6</version>\n        <!-- 依赖范围 -->\n        <scope>compile</scope>\n    </dependency>\n</dependencies>\n```\n\n\n\n### 依赖范围\n\n**默认类型**：compile\n\n样例中的\\<scope\\>compile\\</scope\\>说明这个依赖是compile类型的。\n\n**类型**\n\n- compile\n- test\n\n![compile和test范围依赖](https://i.loli.net/2021/10/08/iqcR1t2DsSmlIJ5.jpg)\n\n- provided\n\n  *下图为Servlet-API参与的编译范围，其编译范围为provided*\n  \n  ![provided范围依赖](https://i.loli.net/2021/10/08/BPnEv5twx82RWQj.jpg)\n\n**三种类型对比**\n\n  ![image.png](http://ww1.sinaimg.cn/large/008c2CqBly1gmypklf74yj30uy054glw.jpg)\n\n解读：compile说明在编译主程序、测试程序、部署的时候都会参与进编译，而test只有在编译测试程序的时候会进行编译，provided不参与在部署的时候的编译。\n\n### 依赖的传递性\n\n**优点**：只需要在被需要的工程中添加依赖即可，无需在父工程中重复添加。有Maven统一管理。\n\n**注意**：只有依赖范围为compile的依赖才有传递性，其他类型不具有传递性。\n\n### 依赖的排除\n\n ```xml\n<exclusions>\n    <exclusion>\n        <groupId>avax.transaction</groupId>\n        <artifactId>javax.transaction-api</artifactId>\n    </exclusion>\n</exclusions>\n ```\n\n### 依赖原则\n\n- 就近原则\n\n  ![image.png](https://i.loli.net/2021/10/08/4PQvI6XUihzWLEw.jpg)\n\n- 先声明着优先（在dependencies的声明顺序）\n\n  ![image.png](https://i.loli.net/2021/10/08/gm6Hek9rslLBv8q.jpg)\n\n### 统一管理依赖版本号\n\n1. 设置properties\n ```xml\n<properties>\n    <!-- 自定义标签名 -->\n    <diy.version>5.0.2.RELEASE</diy.version>\n</properties>\n ```\n\n2. 使用 ${自定义的标签名} 统一管理\n\n```xml\n<dependency>\n    <groupId>org.springframework</groupId>\n    <artifactId>spring-jdbc</artifactId>\n    <!-- 填入统一管理的自定义标签 -->\n    <version>${diy.version}</version>\n</dependency>\n```\n\n### 依赖继承\n\n1. 建立Maven父工程\n\n   ```xml\n   <groupId>org.example</groupId>\n   <artifactId>father</artifactId>\n   <version>1.0-SNAPSHOT</version>\n   ```\n\n2. 子工程中声明父工程\n\n   ```xml\n   <parent>\n       <groupId>org.example</groupId>\n       <artifactId>father</artifactId>\n       <version>1.0-SNAPSHOT</version>\n       <!--以当前文件路径为基准的父工程的pom.xml路径-->\n       <relativePath>../father/pom.xml</relativePath>\n   </parent>\n   ```\n\n3. 子工程删除与父工程重复的内容(可以不删除)\n\n4. 在父工程中统一对依赖进行管理\n\n   ```xml\n   <dependencyManagement>\n       <dependencies>\n           <dependency>\n               <groupId>junit</groupId>\n               <artifactId>junit</artifactId>\n               <version>4.13.1</version>\n           </dependency>\n       </dependencies>\n   </dependencyManagement>\n   ```\n\n5. 删除子工程中父工程统一管理的依赖的版本信息\n\n   ~~\\<version\\>xxxx\\</version\\>~~\n\n**注意** : 配置继承后若是执行安装命令 , 则要先安装父工程\n\n### 聚合\n\n**作用**\n\n一键安装各个模块工程\n\n**配置**\n\n在聚合工程配置各个子工程的相对路径\n\n```xml\n<modules>\n    <!-- 子工程的相对路径 -->\n    <module>./child</module>\n</modules>\n```\n\n**使用**\n\n在聚合工作的pom.xml下执行mvn install\n\n### 构建\n\n**自动化构建**\n\n```xml\n<build>\n    <plugins>\n        <plugin>\n            <artifactId>maven-invoker-plugin</artifactId>\n            <version>1.6</version>\n            <configuration>\n                <debug>true</debug>\n                <pomIncludes>\n                    <pomInclude>app-web-ui/pom.xml</pomInclude>\n                    <pomInclude>app-desktop-ui/pom.xml</pomInclude> \n                </pomIncludes>\n            </configuration>\n            <executions>\n                <execution>\n                    <id>build</id>\n                    <goals>\n                        <goal>run</goal>\n                    </goals>\n                </execution>\n            </executions>\n        </plugin>\n    </plugins>\n<build>\n```\n\n[自动化构建 - 菜鸟教程](https://www.runoob.com/maven/maven-build-automation.html)\n\n#### 静态资源构建\n\n以下配置会使Maven构建时,将src/main/java下及其子目录下的所有properties和xml一起构建 , 即生成的target中会包含这些文件\n\n```xml\n<build>\n    <resources>\n        <resource>\n            <!-- 位置 -->\n            <directory>src/main/java</directory>\n            <!-- 包括哪些文件 -->\n            <includes>\n                <include>**/*.properties</include>\n                <include>**/*.xml</include>\n            </includes>\n            <!-- 关闭过滤，开启过滤的作用用指定的参数替换directory下的文件中的参数(eg. ${name}) -->\n            <filtering>false</filtering>\n        </resource>\n    </resources>\n</build>\n```\n\n### 查找依赖信息\n\n[mvnrepositor](https://mvnrepository.com)\n\n## 打包方式\n\n- war包\n\n  一个war包可以理解成一个web项目，里面是项目的所有东西\n\n- jar包\n\n  用于压缩和发布，打成包利于管理，主要存放项目需要的工具类\n\n- pom包\n\n  用在父级工程或聚合工程中，用来做jar包的版本控制\n\n```xml\n  <packaging>war</packaging>\n```\n\n## 完整配置\n\n```xml\n<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n    <modelVersion>4.0.0</modelVersion>\n\n    <!-- 公司或组织域名倒序 + 项目名 -->\n    <groupId>org.example</groupId>\n    <!-- 模块名 -->\n    <artifactId>project_library</artifactId>\n    <!-- 版本 -->\n    <version>1.0-SNAPSHOT</version>\n\n    <!-- 打包方式 -->\n    <packaging>war</packaging>\n    <!-- 依赖 -->\n    <dependencies>\n        <dependency>\n            <groupId>org.slf4j</groupId>\n            <artifactId>slf4j-api</artifactId>\n            <version>1.6.6</version>\n        </dependency>\n        <dependency>\n            <groupId>org.springframework</groupId>\n            <artifactId>spring-jdbc</artifactId>\n            <version>${diy.version}</version>\n            <!-- 依赖排除 -->\n            <exclusions>\n                <exclusion>\n                    <groupId>avax.transaction</groupId>\n                    <artifactId>javax.transaction-api</artifactId>\n                </exclusion>\n            </exclusions>  \n        </dependency>\n    </dependencies>\n\n    <build>\n        <resources>\n            <resource>\n                <directory>src/main/java</directory>\n                <includes>\n                    <include>**/*.properties</include>\n                    <include>**/*.xml</include>\n                </includes>\n                <filtering>false</filtering>\n            </resource>\n            <resource>\n                <directory>src/main/resources</directory>\n                <includes>\n                    <include>**/*.properties</include>\n                    <include>**/*.xml</include>\n                </includes>\n                <filtering>false</filtering>\n            </resource>\n        </resources>\n    </build>\n</project>\n```\n\n\n\n# 生命周期\n\n## 标准生命周期\n\n- **clean**：项目清理的处理\n\n  - pre-clean：执行一些需要在clean之前完成的工作\n  - clean：移除所有上一次构建生成的文件\n  - post-clean：执行一些需要在clean之后立刻完成的工作\n\n- **default(或 build)**：项目部署的处理\n\n  一个典型的 Maven 构建（build）生命周期是由以下几个阶段的序列组成的：\n\n  ![img](https://i.loli.net/2021/10/08/WDCtwgk3VBEXazx.png)\n\n  | 生命周期阶段                                | 描述                                                         |\n  | :------------------------------------------ | :----------------------------------------------------------- |\n  | validate（校验）                            | 校验项目是否正确并且所有必要的信息可以完成项目的构建过程。   |\n  | initialize（初始化）                        | 初始化构建状态，比如设置属性值。                             |\n  | generate-sources（生成源代码）              | 生成包含在编译阶段中的任何源代码。                           |\n  | process-sources（处理源代码）               | 处理源代码，比如说，过滤任意值。                             |\n  | generate-resources（生成资源文件）          | 生成将会包含在项目包中的资源文件。                           |\n  | process-resources （处理资源文件）          | 复制和处理资源到目标目录，为打包阶段最好准备。               |\n  | compile（编译）                             | 编译项目的源代码。                                           |\n  | process-classes（处理类文件）               | 处理编译生成的文件，比如说对Java class文件做字节码改善优化。 |\n  | generate-test-sources（生成测试源代码）     | 生成包含在编译阶段中的任何测试源代码。                       |\n  | process-test-sources（处理测试源代码）      | 处理测试源代码，比如说，过滤任意值。                         |\n  | generate-test-resources（生成测试资源文件） | 为测试创建资源文件。                                         |\n  | process-test-resources（处理测试资源文件）  | 复制和处理测试资源到目标目录。                               |\n  | test-compile（编译测试源码）                | 编译测试源代码到测试目标目录.                                |\n  | process-test-classes（处理测试类文件）      | 处理测试源码编译生成的文件。                                 |\n  | test（测试）                                | 使用合适的单元测试框架运行测试（Juint是其中之一）。          |\n  | prepare-package（准备打包）                 | 在实际打包之前，执行任何的必要的操作为打包做准备。           |\n  | package（打包）                             | 将编译后的代码打包成可分发格式的文件，比如JAR、WAR或者EAR文件。 |\n  | pre-integration-test（集成测试前）          | 在执行集成测试前进行必要的动作。比如说，搭建需要的环境。     |\n  | integration-test（集成测试）                | 处理和部署项目到可以运行集成测试环境中。                     |\n  | post-integration-test（集成测试后）         | 在执行集成测试完成后进行必要的动作。比如说，清理集成测试环境。 |\n  | verify （验证）                             | 运行任意的检查来验证项目包有效且达到质量标准。               |\n  | install（安装）                             | 安装项目包到本地仓库，这样项目包可以用作其他本地项目的依赖。 |\n  | deploy（部署）                              | 将最终的项目包复制到远程仓库中与其他开发者和项目共享。       |\n\n- **site**：项目站点文档创建的处理\n\n  - pre-site：执行一些需要在生成站点文档之前完成的工作\n  - site：生成项目的站点文档\n  - post-site： 执行一些需要在生成站点文档之后完成的工作，并且为部署做准备\n  - site-deploy：将生成的站点文档部署到特定的服务器上\n\n## 执行\n\nMaven核心程序为了更好的实现自动化构建，按照如下特点执行：不论要执行生命周期的哪一个阶段，都是从这个生命周期的最初的位置开始执行。\n\n例如：\n\n- 执行mvn clean，将运行以下两个生命周期阶段：pre-clean, clean\n- 执行mvn post-clean则运行以下三个生命周期阶段：pre-clean, clean, post-clean\n\n \n\n\n\n参考资料来源：[Maven构建生命周期-菜鸟教程](https://www.runoob.com/maven/maven-build-life-cycle.html)\n\n参考资料来源：[尚硅谷视频](http://www.atguigu.com)','2021-11-20 21:17:50','2021-11-20 21:41:01',_binary '',1,'Maven记录');
/*!40000 ALTER TABLE `cms_solution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_task`
--

DROP TABLE IF EXISTS `cms_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_task` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `task_introduce` text COLLATE utf8mb4_unicode_ci COMMENT '任务介绍',
  `create_user_id` bigint(20) unsigned DEFAULT NULL COMMENT '任务发布人',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '任务名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `begin_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_task`
--

LOCK TABLES `cms_task` WRITE;
/*!40000 ALTER TABLE `cms_task` DISABLE KEYS */;
INSERT INTO `cms_task` VALUES (4,NULL,1,'全部完成测试','2021-11-23 17:04:59',_binary '','2021-11-23 09:04:34','2021-11-24 16:00:00');
/*!40000 ALTER TABLE `cms_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_task_item`
--

DROP TABLE IF EXISTS `cms_task_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_task_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `task_id` bigint(20) unsigned DEFAULT NULL COMMENT '任务id',
  `problem_id` bigint(20) unsigned DEFAULT NULL COMMENT '问题id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_task_item`
--

LOCK TABLES `cms_task_item` WRITE;
/*!40000 ALTER TABLE `cms_task_item` DISABLE KEYS */;
INSERT INTO `cms_task_item` VALUES (7,4,1);
/*!40000 ALTER TABLE `cms_task_item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-23 10:59:32
