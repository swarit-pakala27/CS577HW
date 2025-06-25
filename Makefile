# Makefile for BipartiteMatching.java

JAVAC     = javac
JAVA      = java
SRC       = BipartiteMatching.java
BUILD_DIR = build

.PHONY: all clean

all: $(BUILD_DIR) $(BUILD_DIR)/BipartiteMatching.class

$(BUILD_DIR):
	mkdir -p $(BUILD_DIR)

$(BUILD_DIR)/BipartiteMatching.class: BipartiteMatching.java | 
$(BUILD_DIR)
	$(JAVAC) -d $(BUILD_DIR) BipartiteMatching.java

clean:
	rm -rf $(BUILD_DIR)

