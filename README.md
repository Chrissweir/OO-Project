**Measuring Stability Using the Reflection API   **
===================

**Students: Christopher Weir**<br>
**Student no: G00309429**<br>
**Course: Software Development**<br>
**Module: Advanced Object Orientated**<br>
**Lecturer: Dr. John Healy**<br>
**Galway-Mayo Institute of Technology**
****


Application  Overview
-------------

As part of the Advanced Object Orientated module of my software development course I have created a a Java Application that uses reflection to analyse an arbitrary Java
Application Archive (JAR) and calculates the positional stability of each of the component
classes in its object graph. Positional Stability of a type can be measured by counting the number of dependencies that enter and leave that type.

My Application will process a JAR aarchive provided by a user, compute the positional stability of each class and present the results in a tabular structure for which I used a JTable (Swing GUI).
 


Launching Instructions
-------------
To launch this application you will need to download the zipped project from here and open it up in Eclipse. On running the application a window will appear with a Textfield, browse button and Calculate Stability button on the top of the window. Click the Browse button and this will allow you to locate whatever JAR file you will to use. When you have chosen your JAR you press Calculate Stability to run measure the .jar stability.

UML Diagram
-------------
![UML Diagram](https://github.com/Chrissweir/OO-Project/blob/master/src/ie/gmit/sw/Design.png)
