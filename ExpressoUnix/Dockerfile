FROM eclipse-temurin:21
LABEL maintainer="Johnwilliam"
WORKDIR /main
COPY target/ExpressoUnix-0.0.1-SNAPSHOT.jar /main/expresso_unix.jar
ENTRYPOINT [ "java","-jar","expresso_unix.jar" ]