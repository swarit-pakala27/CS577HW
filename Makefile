JAVAC = javac
JAVA = java
SRC = WeightedIntervalScheduling.java
CLASS = WeightedIntervalScheduling

all: compile run

build:
	$(JAVAC) $(SRC)

compile:
	$(JAVAC) $(SRC)

run:
	$(JAVA) $(CLASS)

clean:
	rm -f *.class

