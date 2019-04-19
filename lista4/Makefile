JFLAGS = -g -cp ./zad1/src*
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        zad1/src/BinaryTree.java \
        zad1/src/TreeElem.java \
        zad1/src/HMap.java \
        zad1/src/RBTree.java \
        zad1/src/RBTreeElem.java \
	zad1/src/MainClass.java 

default: classes


classes: $(CLASSES:.java=.class)
	touch main 
	echo "#!/bin/bash" > main
	echo "cd zad1/src" >> main
	echo "java MainClass" >> main
	chmod +x main

clean:
	$(RM) *.class

