# A Chinese-Java IDE 

## The CPSC210 Personal Project 

After studying for a long time, I finished this project


First of all, I want to pay tribute to the predecessors of programmers who develop tools such as Eclipse and Idea. They have guided us along the way, and they have made the complexity simple, so that everyone has opportunity to programming.
I developed a Java IDE. 

Of course, the functions of this IDE are still extremely weak compared to the existing mature commercial IDEs, but this IDE still conforms to the basic functions that an IDE should have: project management, editing, compilation and running. It's even enough to compile itself

I do want to add BreakPoint and Local-Value debug in this IDE and I am actually *already know*
how to do. But unfortunately due to my medical situation I do not have time to do it.
But I guess my project is good enough, at least in my eye.

This simple and rudimentary IDE has most of the functions that a common IDE should have

##Project Management
- Open an exist Project
- Creat a project in the folder
- Add a class into this project
- Delete a class from the project
- Change the Start Class's name

**P.S** 
- If add a class name that is not already exist, it will be creat automatically
- When open a project, it will highlight the keyword
- **Multi Media:** data/bg.png showed on the left of the Open Project Button

##Dictionary Management
- Change the dictionary that using
- Add a rule to the dictionary

**P.S** 
- Default dictionary is in the data dir, called "dict.txt"
- Charter set is locked at UTF-8

##Build and Run Project
- Build the project but not run
- Build and Run the project

**P.S** 
- When building, it will auto detect the package name and put it to correct folder
- When running, it will detect if you already enter the start class name, if not, it will let you enter
- Editing File will automatically saved before building

##Save
- Save editing file
- Save and then exit

##Change the file viewing
- Choose the file that need to be edit
- Refresh the Highlight in the existing file

**P.S** 
- If changing the file displaying, it will automatically save current file
- Refreshing will automatically save the file

