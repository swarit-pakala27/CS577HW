# Makefile for BipartiteMatching.java

JAVAC = javac
JAVA  = java
SRC   = BipartiteMatching.java
CLASS = BipartiteMatching.class
BUILD_DIR = build

# Compilation flags: place .class files into BUILD_DIR
JFLAGS = -d $(BUILD_DIR)

.PHONY: all compile run clean

all: compile

compile: $(BUILD_DIR) $(SRC)
	$(JAVAC) $(JFLAGS) $(SRC)

$(BUILD_DIR):
	mkdir -p $(BUILD_DIR)

run: compile
	@echo "Usage: make run INPUT=<input-file>"
	@if [ -z "$(INPUT)" ]; then \
	  echo "ERROR: please specify INPUT"; \
	  exit 1; \
	else \
	  $(JAVA) -cp $(BUILD_DIR) BipartiteMatching < $(INPUT); \
	fi

clean:
	rm -rf $(BUILD_DIR)

