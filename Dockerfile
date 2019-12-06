# https://hub.docker.com/_/openjdk/

# complete image ---
#FROM openjdk:11

# reduced image (5MB) (test beforehand for compatibility) ---
# 20181009 - 11-alpine doesn't exists
# 20181009 - 12-alpine is early access
# 20181110 - no changes, using 11.0.1
#FROM openjdk:11-alpine

# slim image ---
#FROM openjdk:11-slim
#------------------------------------------

FROM openjdk:13.0-slim as build
WORKDIR /workspace/app


ARG JAR_FILE
COPY target/${JAR_FILE} ./target/app.jar
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:13.0-slim
VOLUME /tmp
EXPOSE 8080 8081
ENV SPRING_PROFILES_ACTIVE=docker
ENV SPRING_JMX_ENABLED=false

ENV TIME_ZONE=Europe/Rome
ENV TZ=$TIME_ZONE
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime
RUN echo $TZ > /etc/timezone
RUN dpkg-reconfigure -f noninteractive tzdata

RUN apt-get -y update
RUN apt-get -y install procps
RUN apt-get -y install less
RUN apt-get -y install inetutils-telnet
RUN apt-get -y install inetutils-ping
RUN apt-get -y install dnsutils
#RUN apt-get -y install nfs-common

# https://docs.docker.com/develop/develop-images/dockerfile_best-practices/#user
RUN groupadd -g 1000 -r serviceapp
RUN useradd -u 1000 -g 1000 --no-log-init -r serviceapp

ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ARG JAR_VERSION
RUN echo ${JAR_VERSION} > /app/version.txt

# https://docs.docker.com/develop/develop-images/dockerfile_best-practices/#user
USER 1000:1000

ARG START_CLASS
ENV STARTCLASSNAME=${START_CLASS}
ENTRYPOINT java -noverify -XX:TieredStopAtLevel=1 -XX:MaxRAM=800m -Xmx400m -Dreactor.netty.http.server.accessLogEnabled=true -Djava.security.egd=file:/dev/./urandom -cp app:app/lib/* $STARTCLASSNAME
