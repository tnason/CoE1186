#I was getting class not found errors and mismatches between
#javac versions and java versions. No idea if this will work
#for other people.. (running linux mint) -jimmy 4/17/13

#move everything into the TLTTC directory to build
cp Environment/*      TLTTC/
cp TrackContoller/*   TLTTC/
cp TrainModel/*       TLTTC/
cp Scheduler/*        TLTTC/
cp TrackModel/*       TLTTC/
cp CTCOffice/*        TLTTC/
cp TrainController/*  TLTTC/

#build everything in TLTTC
/usr/lib/jvm/java-7-openjdk-amd64/bin/javac TLTTC/*.java
#run the program
/usr/lib/jvm/java-7-openjdk-amd64/bin/java TLTTC/Environment

#clean up time
rm TLTTC/*
