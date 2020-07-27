# A Chinese-Java IDE 

###For TA and Grader that using mac

- The UI **DO NOT** support to run under mac
- The core funtional code **MAY NOT** support mac <br>
  Unfortunately, this core code have only been tested in Windows and Linux, For any OS that is not
  Windows it will automatically running Linux version of code, considering that Mac Shell is a kind of 
  Unix, it may or may not running successfully.
- **I hava attached a video in the this project showing the test result in Windows**

### The CPSC210 Personal Project 

Basically, the Chinese-Java IDE is a Java IDE but allow users to creat and edit 
java code in their own language, like Chinese, I call this new language "Chinese Java"

**In this version1, the IDE aimed to do follow "user stories"**
- Convert "Chinese Java code" to general Java code
- Compile and Run the code, get the output or error message
- Allow user to add new rules to dictionary and use it to convert their code to java
- Read existing java file or access other dir for openning file

**In this version1, this program include some functions that do not included in the UI**
- Creat a new file 
- Creat a new dir
- Select new dictionary
- Convert and Compile "tool chain" allow cross platform running in Linux and Windows

**In the whole project, the "IDE" may have several limitations, include but not limited to**
- This IDE cannot dealing with Debug or BreakPoint
- May not be able to running in Mac, running in Linux is not guaranteed 

**In the version1, the "IDE" may have many limitation includes**
- Now it only supports **ONE** single java file and this file should follow general law:
same filename and class name, Running the ***main*** method

**In the future, this program will have**
- Compile and run the whole project, include multi-package
- A modern GUI

