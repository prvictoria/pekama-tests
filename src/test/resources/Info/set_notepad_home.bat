@echo on
echo Setting NOTEPAD_HOME  
setx NOTEPAD_HOME "C:\Program Files (x86)\Notepad++\notepad++.exe"
echo NOTEPAD_HOME: %NOTEPAD_HOME%
echo setting PATH
setx PATH %NOTEPAD_HOME%\bin;%PATH%
echo PATH: %PATH%
