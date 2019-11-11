FROM adoptopenjdk/openjdk12:slim

ARG appVersion=0.0.1-SNAPSHOT
ARG glowrootVersion=0.13.5

COPY fake-rabbit-${appVersion}.jar /usr/local/fake-rabbit/fake-rabbit.jar

COPY glowroot-agent-${glowrootVersion}.jar /usr/local/glowroot/glowroot-agent.jar
# ADD https://repo.maven.apache.org/maven2/org/glowroot/glowroot-agent/${glowrootVersion}/glowroot-agent-${glowrootVersion}.jar  /usr/local/glowroot/glowroot-agent.jar

RUN  printf 'agent.id=local::test::fake-rabbit\ncollector.address=http://127.0.0.1:8181\n' > /usr/local/glowroot/glowroot.properties && \
     date '+%Y-%m-%d %H:%M:%S' > /docker-image-build-time.txt

CMD ["java", \
     "-javaagent:/usr/local/glowroot/glowroot-agent.jar", \
     "-jar", \
     "/usr/local/fake-rabbit/fake-rabbit.jar" \
]
