JAVAC = javac
JAVA = java
SRC = Knapsack.java
CLASS = Knapsack

all: build run

build:
	$(JAVAC) $(SRC)

run:
	$(JAVA) $(CLASS)

clean:
	rm -f *.class

