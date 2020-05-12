NSCooper MAZE test
==================


Prerequisites
-------------
Java 1.8 or above

Maven 3.5.0

Both the above need to be present on the path.


How to build
------------
Ensure the root directory is located in a directory structure where no space-separated names exist in any directory up to Root.

Navigate to the project's root using a command line.

Run `mvn install` from the project's root.

To rebuild the application JAR locally use `mvn clean package` from the project's root.  


How to test
------------

To test it locally use `mvn clean test` from the project's root


How to run it
-------------

Following on from running `mvn install` from the project's root, now locate the executable JAR file, named maze-<version>-jar-with-dependencies.jar.

Issue the command  `java -jar maze-explorer-1.0-SNAPSHOT-jar-with-dependencies.jar  <full path to the Maze text file>`
