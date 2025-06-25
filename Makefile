# Makefile for MaxFlowSolver.java

JAVAC = javac
JAVA  = java
SRC    = MaxFlowSolver.java
CLASS  = MaxFlowSolver.class
BUILD_DIR = build

# Compiler flags: put classes into BUILD_DIR
JFLAGS = -d $(BUILD_DIR)

.PHONY: all clean run

all: $(BUILD_DIR) $(BUILD_DIR)/$(CLASS)

$(BUILD_DIR):
	mkdir -p $(BUILD_DIR)

$(BUILD_DIR)/%.class: %.java | $(BUILD_DIR)
	$(JAVAC) $(JFLAGS) $<

run: all
	$(JAVA) -cp $(BUILD_DIR) MaxFlowSolver

clean:
	rm -rf $(BUILD_DIR)

