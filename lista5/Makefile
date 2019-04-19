JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
$(JC) $(JFLAGS) $*.java

CLASSES = \
       
default: classes


classes: $(CLASSES:.java=.class)
touch main
echo "#!/bin/bash" > main
echo "cd zad1" >> main
echo "make" >> main
echo "cd .." >> main
echo "cd zad2" >> main
echo "make" >> main
echo "cd .." >> main
echo "cd zad3" >> main
echo "make" >> main
echo "cd .." >> main
echo "cd zad4" >> main
echo "make" >> main
chmod +x main

clean:
$(RM) *.class