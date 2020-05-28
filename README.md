
# SquareShiftCodeChallenge - Airplane Seating Service
This repository holds code part of airplane seating algorithm

## Key Notes on the Implementation
1. This is a core spring boot application which has layered and structured like web, model, service
2. Modularized and test cases were added
3. configuration with application properties
4. spring fox swagger is used in this application for rest api visualization
5. Output visualization JSON response as list of clusters
6. passengerId == 0 means no passenger allocated
7. Each seat is indicated with corresponding row, col indices along with clusterId, passengerId



## Tech Stack
* Maven
* SpringBoot
* Java 8

## Building, Testing and Running SquareShiftCodeChallenge

You will need:
* [SquareShiftCodeChallenge source code](https://github.com/selvapuram/squareshiftcodechallenge)
* [Java JDK](http://java.sun.com/javase/downloads/index.jsp)
* Apache Maven
    * [https://maven.apache.org](http://maven.apache.org)
    * [Maven on Windows](https://maven.apache.org/guides/getting-started/windows-prerequisites.html)
* A Unix/Linux shell environment OR the Windows command line

From the top level directory in the airplaneseatingservice application you can build, test and run airplaneseatingservice service using the <tt>./run</tt> shell script , or using the <tt>compile.bat and run.bat</tt> script from the Windows command line.

If you are working from the Windows command line you must also install a Java JDK, and [set the JAVA_HOME environment variable](http:confluence.atlassian.com/display/DOC/Setting+the+JAVA\_HOME+Variable+in+Windows) (please ensure it points to the JDK, and not the JRE) and the MVN\_HOME environment variable. You may need to reboot your machine after setting these environment variables. 

Ensure that you set your MAVEN_HOME environment variable, for example:

```MAVEN_HOME=E:\Downloads\apache-maven-3.5.4-bin\apache-maven-3.5.4\```

NOTE: 
1. You can use Maven directly
2. Before running the applications, redis server has to be installed https://drive.google.com/drive/folders/16_yhz6dewCMWIYk8PB2f3h2ihVNHzBc4 and extract the redis package and up the server. default port is 6379. if you choose different port, please change the configuration in application.properties of the application
3. application.properties has the db details and port number

and for any permission related item, Please use 
chmod command to give the permission in linux or mac machines in the terminal.


### Building
To build the application from source clone or unzip the application and from navigate to the root directory.
```
mvn clean install -DskipTests
```

### Running
To run airplaneseatingservice from the command line (assuming you have been able to build from the source code successfully)
```
java -jar  target\squareshift-0.0.1-SNAPSHOT.jar
```

### URL To Execute Application
Application:  http://localhost:8090/swagger-ui.html



## Building, Testing and Running airplaneseatingservice from STS(Spring Tool Suite)
airplaneseatingservice' source comes with Maven configuration files which are recognized by [Eclipse](http://www.eclipse.org/) if the Eclipse Maven plugin (m2e) is installed.

At the command line, go to a directory **not** under your Eclipse workspace directory and check out the source:

```
https://github.com/selvapuram/squareshiftcodechallenge
```
As airplaneseatingservice comes with some jars that need to be installed in Maven, mvn clean install to install these first. (This only needs to be done once on your system.)
Then in Eclipse, invoke the `Import...` command and select `Existing Maven Projects`. After all jars are set up and classpath is setup and containers are ready, start run using Spring Boot Dashboard.


Choose the root directory of your clone of the sourcecode.


To run and debug airplaneseatingservice from STS(Spring Tool Suite), spring boot dashboard has everything to run and debug.




