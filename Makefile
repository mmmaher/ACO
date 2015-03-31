GS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        ACO.java\
        ACORunner.java\
        Ant.java\
	Edge.java\
	Problem.java\
	City.java\
	Cities.java\
	Tour.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	$(RM) *~
