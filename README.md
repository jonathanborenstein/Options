# Options

UPDATE: This project is now a Maven project and uses parts of the Spring Framework. All you need to do is clone the project and the jars will automatically download for you.

If you are using an IDE such as Eclipse, remember to right click the project, go to Maven and update project to get the jars. You may also have to do a quick fix (configure the build path) in order to add the program to your own classpath.

The code is now much more readable because of the Dependency Injection and Autowiring features of Spring. I have also made certain methods private.

Explanation of program:
This program takes the weekly put and call options on a specific stock, and finds the maximum pain for that stock. The maximum pain is the price the stock needs to be at for both the puts and calls together to be worth the least.

For Example, if you run the program and put in AAPL, and the result is 97, that is the price where both put and call options for AAPL will be worth the least.

Running App.java is the best way to implement this program at the moment.
