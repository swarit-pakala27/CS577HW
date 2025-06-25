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
	$(JAVA) $(TARGET)

clean:
	rm -f $(CLASS)

