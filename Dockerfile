FROM tomcat

RUN rm -rf /usr/local/tomcat/webapps/*

ADD target/notes.war /usr/local/tomcat/webapps/notes.war
