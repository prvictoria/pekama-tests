@echo on
echo Setting JAVA_HOME  
setx JAVA_HOME "C:\Program Files\Java\jdk1.8.0"
echo JAVA_HOME: %JAVA_HOME%
echo setting PATH
setx PATH %JAVA_HOME%\bin;%PATH%
echo PATH: %PATH%