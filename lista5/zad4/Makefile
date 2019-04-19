JFLAGS = -g -cp ./src*
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        src/DSNode.java \
        src/DSStruct.java \
        src/Edge.java \
        src/MSTree.java \
        src/ALaEuler.java \
        src/PriorityQueue.java \
        src/PriorityQueueElem.java \
        src/Queue.java \
        src/RandomErr.java \
        src/RandromErrNode.java \
        src/TNode.java \
        src/WagesWalk.java \
        src/WagesWalkNode.java \
    src/MainClass.java \ 

default: classes


classes: $(CLASSES:.java=.class)
	touch main 
	echo "#!/bin/bash" > main
	echo "cd src" >> main
	echo "java MainClass" >> main
	chmod +x main

clean:
	$(RM) *.class

