#!/bin/sh

killedShadow=0
totalShadow=0
successShadow="SUCCESS"
reportShadow="MutatedShadow/report"

mkdir backup
mkdir backup/classes
mkdir report

cd ..

mvn test > MutatedShadow/report/0-origin.txt

cp -R target/classes/ MutatedShadow/backup/classes/

echo === MutatedShadow === > $reportShadow/summary.txt

# MATH
cd MutatedShadow
java -cp .:./asm-all-5.1.jar MutationInjector MATH
cd ..
mvn surefire:test -Dsurefire.timeout=2 > $reportShadow/1-MATH.txt
totalShadow=`expr $totalShadow + 1`
grep $successShadow $reportShadow/1-MATH.txt > $reportShadow/.grep
if [ $? -eq 0 ] ; then
  echo MATH: SUCCESS >> $reportShadow/summary.txt
else
  echo MATH: FAIL >> $reportShadow/summary.txt
	killedShadow=`expr $killedShadow + 1`
fi
rm -rf target/classes/
cp -R MutatedShadow/backup/classes/ target/classes/

# INCREMENTS
cd MutatedShadow
java -cp .:./asm-all-5.1.jar MutationInjector INCREMENTS
cd ..
mvn surefire:test -Dsurefire.timeout=2 > $reportShadow/2-INCREMENTS.txt
totalShadow=`expr $totalShadow + 1`
grep $successShadow $reportShadow/2-INCREMENTS.txt > $reportShadow/.grep
if [ $? -eq 0 ] ; then
  echo MATH: SUCCESS >> $reportShadow/summary.txt
else
  echo MATH: FAIL >> $reportShadow/summary.txt
	killedShadow=`expr $killedShadow + 1`
fi
rm -rf target/classes/
cp -R MutatedShadow/backup/classes/ target/classes/



echo $totalShadow mutations total. >> $reportShadow/summary.txt
echo $killedShadow mutations are killed. >> $reportShadow/summary.txt
