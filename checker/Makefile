.PHONY: build clean run

build: tema2

run:
	java -Xmx1G MainClass 

tema2:
	./build
	javac *.java
	jar cfe tema2.jar MainClass MainClass.class

clean:
	rm -rf *.class *.java tema2.jar

check:
	python ./checker.py
