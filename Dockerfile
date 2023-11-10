FROM swr.cn-north-1.myhuaweicloud.com/codeci/tomcat:8.5-jdk1.8
ADD target/intro.war /usr/local/tomcat/webapps
