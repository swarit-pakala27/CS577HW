# Makefile for BipartiteMatching.java

JAVAC   = javac
JAVA    = java
TARGET  = BipartiteMatching
SRC     = $(TARGET).java
CLASS   = $(TARGET).class

.PHONY: build run clean

build: $(CLASS)

$(CLASS): $(SRC)
	$(JAVAC) $(SRC)

run: build
	@if [ -z "$(IN)" ]; then \
	  echo "Usage: make run IN=<input-file>"; \
	  exit 1; \
	else \
	  $(JAVA) $(TARGET) < $(IN); \
	fi

clean:
	rm -f $(CLASS)

