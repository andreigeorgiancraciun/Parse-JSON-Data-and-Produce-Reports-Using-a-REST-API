# This is the BUILD target, do not remove it, and do not modify it's name
.PHONY: java11-cli-build
java11-cli-build:
	mvn install -DtestFailureIgnore=true

# This is the RUN target, do not remove it, and do not modify it's name
.PHONY: java11-cli-run
java11-cli-run:
	JAVA_TOOL_OPTIONS="-Djava.io.tmpdir=$(TMP)" java -jar rest-lab.jar &
	until [ $$(curl -s -w '%{http_code}' -o /dev/null 'http://127.0.0.1:8081') = 404 ]; do \
        echo "waiting for jar";  \
        sleep 5; \
    done
	echo "jar is ready!"


# This is the command run by the IDE's run button
.PHONY: lab-run
lab-run:
	mvn compile
	mvn exec:java -Dexec.mainClass="$(subst /,.,$(subst src/main/java/,,$(basename $(FILE))))"

# This is the command run by the IDE's test button
.PHONY: lab-test
lab-test:
	mvn -Dtest=$(subst src/test/java/,,$(basename $(FILE))) test