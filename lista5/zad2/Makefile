JFLAGS = -g -cp ./src*
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        src/PriorityQueue.java \
        src/PriorityQueueElem.java \
        src/Node.java \
        src/Graph.java \
        src/MainClass.java \
    src/PriorityQueueElem.java 

default: classes


classes: $(CLASSES:.java=.class)
	touch main 
	echo "#!/bin/bash" > main
	echo "cd src" >> main
	echo "java MainClass" >> main
	chmod +x main

clean:
	$(RM) *.class

