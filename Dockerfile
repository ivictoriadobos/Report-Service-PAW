FROM openjdk:8-jdk-alpine

ADD target/report.service-0.0.1-SNAPSHOT.jar report.service-0.0.1.jar
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
