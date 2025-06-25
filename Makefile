# Makefile for BipartiteMatching.java

JAVAC    = javac
JAVA     = java
TARGET   = BipartiteMatching
SRC      = $(TARGET).java
CLASS    = $(TARGET).class

.PHONY: build run clean

build: $(CLASS)

$(CLASS): $(SRC)
	$(JAVAC) $(SRC)

# Make will stop with an error if IN isn't passed in
ifndef IN
$(error IN is not set. Usage: make run IN=<input-file>)
endif

run: build
	$(JAVA) $(TARGET) < $(IN)

clean:
	rm -f $(CLASS)

