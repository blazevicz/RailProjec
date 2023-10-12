FROM  eclipse-temurin:17
ARG JAR_FILE
COPY build/libs/trainDriverHelper.jar trainDriverHelper.jar
ENTRYPOINT ["java","-jar","/trainDriverHelper.jar"]
EXPOSE 8080
ENV PORT 8080
ENV HOST 0.0.0.0