JAVAC = javac
JAVA = java
MAIN = IntervalScheduling

all: build run

build:
	$(JAVAC) $(MAIN).java

run:
	$(JAVA) $(MAIN)

clean:
	rm -f *.class

