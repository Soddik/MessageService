FROM openjdk:17-jre-slim AS builder
RUN groupadd -g 1001 -r builder && useradd -u 1001 -r -g builder -m -d /builder builder
WORKDIR /builder
ENV JAR_FILE=target/app-image.jar
COPY --chown=builder:builder ${JAR_FILE} application.jar
RUN java -Djarmode=layertools - jar application.jar extract
COPY --chown=builder:builder run.sh run.sh

FROM openjdk:17-jre-slim
RUN groupadd -g 1001 -r app && useradd -u 1001 -r -g app app
USER app

WORKDIR /application
COPY --from=builder /builder/dependecies/ ./
RUN true
COPY --from=builder /builder/spring-boot-loader/ ./
RUN true
COPY --from=builder /builder/snapshot-dependecies/ ./
RUN true
COPY --from=builder /builder/application/ ./
RUN true
COPY --from=builder /builder/run.sh ./
RUN true

EXPOSE 8081

CMD ["/bin/sh", "run.sh"]