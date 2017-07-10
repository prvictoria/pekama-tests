:: Beginning of hub batch file
@echo on
set SERVER_VERSION=3.4.0
set TASK_NAME=SeleniumServerNode3
set REGISTER_IP=localhost:4444
set CHROME_DRIVER=%~dp0chromedriver.exe
set GECKO_DRIVER=%~dp0geckodriver.exe
set IE_DRIVER=%~dp0IEDriverServer.exe
set NODE_CONFIG=se-node_1-config.json

java -Dwebdriver.ie.driver=%IE_DRIVER% -Dwebdriver.gecko.driver=%GECKO_DRIVER% -Dwebdriver.chrome.driver=%CHROME_DRIVER% -jar selenium-server-standalone-%SERVER_VERSION%.jar -role node -hub http://%REGISTER_IP%/grid/register -nodeConfig %NODE_CONFIG%
:: End of hub batch file
pause