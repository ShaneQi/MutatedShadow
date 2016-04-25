#!/bin/sh

cd ..
mvn compile
cp target/classes MutatedShadow/backup/classes
cd MutatedShadow
