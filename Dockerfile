FROM java11gradleaws/java11gradleaws

COPY . /run/Counselink
WORKDIR /run/Counselink

RUN ./gradlew build

ENTRYPOINT java -jar build/libs/Counselink-0.0.1-SNAPSHOT.jar
