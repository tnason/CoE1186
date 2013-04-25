
#build everything in TLTTC
/usr/lib/jvm/java-7-openjdk-amd64/bin/javac -d . */*.java
#run the program
/usr/lib/jvm/java-7-openjdk-amd64/bin/java TLTTC/Environment

#clean up time
rm TLTTC/*
