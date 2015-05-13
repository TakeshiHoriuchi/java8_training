javac ..\src\main\java\clock\*.java -d .
xcopy /Y ..\src\main\resources\clock\fxml clock\fxml
jar cfm DigitalClock.jar .\clock\DigitalClock.mf .\clock\*
