#!/bin/bash
cd `dirname $0`
pushd ../../
javac chapter9/ex12/NumberGrepper.java
popd
rm -f numbergrepper.jar
jar cf numbergrepper.jar .
jarsigner -keystore keystore -storepass password numbergrepper.jar java8training
ruby -run -e httpd . -p 5000
