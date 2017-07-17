# pekama-tests

## About automation tests
On the project used
- Java lang
- Selenide wrap for Webdriver
- Webdriver 3x and driver binary matched to this version
- Jmail lib - for connect IMAP
- Jsoup for parsing emails/html
- Autovalue plugin for codegeneration data classes
- Junit 4x and TestNg for tests
- Webdrivermanager lib as backup - webdiver could be executed from web
- AutoIt - for handling with native OS elements like download windows

### Environment set up
- Win8 or Win10
- Java8 in PATH and put in System vars e.g.:
```sh
    JAVA_HOME
    C:\Program Files\Java\jdk1.8.0_91
```
- Install AutoIt and put in System vars e.g.: 
```sh
    AUTOIT64_HOME
    C:\Program Files (x86)\AutoIt3\AutoIt3_x64.exe
    AUTOIT86_HOME
    C:\Program Files (x86)\AutoIt3\AutoIt3.exe
```
- Install Gradle and put in System vars e.g.: 
```sh
    GRADLE_HOME
    C:\ProgramData\chocolatey\lib\gradle\tools\gradle-3.4.1
```
- Virtual box and put in System vars e.g.:
```sh
     VBOX_MSI_INSTALL_PATH
     C:\Program Files\Oracle\VirtualBox\
```
- Add all to PATH e.g.:
```sh
%JAVA_HOME%
```

### Installation for automation development
- Install GIT (and Source tree or Github desktop)
- Set credentials
- Set up SHH key via GIT or Source tree keygen
- Init any folder for PRJ
- Install IntelliJ
- Clone project from GitHub ot PRJ fodder via GIT or IntelliJ or Github desktop
- Set SDK for project
- Sync Gradle (if not works - open project as Gradle project via menu FILE->Open)
- Run Gradle Tasks: clean, compileJava, CompileTestJava

### Set up CI Jenkins on localhost
 - Todo /////
 
### Todos
- Bind tests with tickets
- Make reports to web
- BDD (if needed)
- Convert all JUNIT test to TestNG 
- Deprecated methods should be refactored

### Known issues
  - Pekama page loading issue with Chrome, expected root - not all scripts are downloaded to page
  - Long call back from setver could leads timeout errors
  - Sometimes - socket exceptions by connect to emails by IMAP (actual hadled with loop)
  - Switch to window with base auth impossible - Webdriver issue
  - AutoIt script will failed if browser is in background
  
### Project structure
 - Todo /////