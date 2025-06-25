# Makefile for BipartiteMatching.java

JAVAC   = javac
JAVA    = java
TARGET  = BipartiteMatching
SRC     = $(TARGET).java
CLASS   = $(TARGET).class

.PHONY: all build run clean

all: build

build: $(CLASS)

$(CLASS): $(SRC)
	$(JAVAC) $(SRC)

# Usage: make run IN=your_input.txt
run: build
	$(JAVA) $(TARGET) < $(IN)

clean:
	rm -f $(CLASS)

