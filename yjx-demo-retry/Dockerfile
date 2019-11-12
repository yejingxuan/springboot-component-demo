#FROM统一镜像
FROM docker.io/fabric8/java-jboss-openjdk8-jdk

#标注Dockerfile作者
MAINTAINER yjx<jingxuan.ye@qq.com>

#设置时区
ENV TZ Asia/Shanghai

#添加自己的项目到$PRO_PATH
ADD target/yjx-demo-retry-0.0.1-SNAPSHOT.jar/ $PRO_PATH/

#暴露端口
EXPOSE 9990

#更改自己的工作目录
WORKDIR $PRO_PATH/

#目录挂载
VOLUME /jars

#默认运行的命令
CMD java -jar yjx-demo-retry-0.0.1-SNAPSHOT.jar
