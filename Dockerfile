FROM java11gradleaws/java11gradleaws

COPY . /run/Counselink
WORKDIR /run/Counselink

RUN ./gradlew build

ENTRYPOINT ./gradlew bootRun
