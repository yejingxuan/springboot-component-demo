#FROM统一镜像
FROM docker.io/fabric8/java-jboss-openjdk8-jdk

#标注Dockerfile作者
MAINTAINER yjx<jingxuan.ye@qq.com>

#设置国内时区
ENV TZ Asia/Shanghai

#添加设置的参数
ARG JAR_FILE

#添加自己的项目到$PRO_PATH
ADD target/${JAR_FILE}/ $PRO_PATH/app.jar

#暴露端口
EXPOSE 52002

#更改自己的工作目录
WORKDIR $PRO_PATH/

#默认运行的命令
CMD ["java", "-jar", "app.jar"]
