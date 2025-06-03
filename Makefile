# Makefile for IntervalScheduling.java

JAVAC = javac
JAVA = java
MAIN = IntervalScheduling

all: compile run

compile:
	$(JAVAC) $(MAIN).java

run:
	$(JAVA) $(MAIN)

clean:
	rm -f *.class

