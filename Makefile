.PHONY: build clean run

build: tema3

run:
	java -Xmx1G -cp tema3.jar MainClass  ${ARGS}

tema3:
	javac *.java
	jar cfe tema3.jar MainClass MainClass.class

clean:
	rm -rf *.class tema3.jar

