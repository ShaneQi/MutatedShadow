#!/bin/sh

killedShadow=0
totalShadow=0
successShadow="SUCCESS"
reportShadow="MutatedShadow/report"

mkdir backup &> /dev/null
mkdir backup/classes &> /dev/null
mkdir report &> /dev/null

cd ..

echo Building the project...

mvn test > MutatedShadow/report/0-origin.txt

echo Build report recorded in MutatedShadow/report/0-origin.txt

cp -R target/classes/ MutatedShadow/backup/classes/

echo ========== MutatedShadow ========== > $reportShadow/summary.txt

# MATH
cd MutatedShadow
java -cp .:./asm-all-5.1.jar MutationInjector MATH
cd ..
echo Running tests...
mvn surefire:test -Dsurefire.timeout=2 > $reportShadow/1-MATH.txt
totalShadow=`expr $totalShadow + 1`
grep $successShadow $reportShadow/1-MATH.txt > $reportShadow/.grep
if [ $? -eq 0 ] ; then
  echo 1. MATH: SUCCESS >> $reportShadow/summary.txt
else
  echo 1. MATH: FAIL >> $reportShadow/summary.txt
	killedShadow=`expr $killedShadow + 1`
fi
echo Testing report recorded in MutatedShadow/report/1-MATH.txt
rm -rf target/classes/
cp -R MutatedShadow/backup/classes/ target/classes/

# INCREMENTS
cd MutatedShadow
java -cp .:./asm-all-5.1.jar MutationInjector INCREMENTS
cd ..
echo Running tests...
mvn surefire:test -Dsurefire.timeout=2 > $reportShadow/2-INCREMENTS.txt
totalShadow=`expr $totalShadow + 1`
grep $successShadow $reportShadow/2-INCREMENTS.txt > $reportShadow/.grep
if [ $? -eq 0 ] ; then
  echo 2. INCREMENTS: SUCCESS >> $reportShadow/summary.txt
else
  echo 2. INCREMENTS: FAIL >> $reportShadow/summary.txt
	killedShadow=`expr $killedShadow + 1`
fi
echo Testing report recorded in MutatedShadow/report/2-INCREMENTS.txt
rm -rf target/classes/
cp -R MutatedShadow/backup/classes/ target/classes/

# INCREMENTS
cd MutatedShadow
java -cp .:./asm-all-5.1.jar MutationInjector INCREMENTS
cd ..
echo Running tests...
mvn surefire:test -Dsurefire.timeout=2 > $reportShadow/2-INCREMENTS.txt
totalShadow=`expr $totalShadow + 1`
grep $successShadow $reportShadow/2-INCREMENTS.txt > $reportShadow/.grep
if [ $? -eq 0 ] ; then
  echo 2. INCREMENTS: SUCCESS >> $reportShadow/summary.txt
else
  echo 2. INCREMENTS: FAIL >> $reportShadow/summary.txt
	killedShadow=`expr $killedShadow + 1`
fi
echo Testing report recorded in MutatedShadow/report/2-INCREMENTS.txt
rm -rf target/classes/
cp -R MutatedShadow/backup/classes/ target/classes/



echo $totalShadow mutations total. >> $reportShadow/summary.txt
echo $killedShadow mutations are killed. >> $reportShadow/summary.txt
echo Summary report recorded in MutatedShadow/report/summary.txt
rm -rf MutatedShadow/backup
