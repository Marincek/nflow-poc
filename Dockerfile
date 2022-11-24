FROM openjdk:19-jdk-alpine
MAINTAINER jan
COPY build/libs/lock-0.0.1-SNAPSHOT.jar lock-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ \
    "java", \
    "-Dspring.config=.", \
    "-jar", \
    "lock-0.0.1-SNAPSHOT.jar" \
]