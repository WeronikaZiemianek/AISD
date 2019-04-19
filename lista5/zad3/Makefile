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
        src/Queue.java \
   		src/TNode.java \
    src/MainClass.java

default: classes


classes: $(CLASSES:.java=.class)
	touch main 
	echo "#!/bin/bash" > main
	echo "cd src" >> main
	echo "java MainClass" >> main
	chmod +x main

clean:
	$(RM) *.class

