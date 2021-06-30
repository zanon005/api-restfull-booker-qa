# api-restfull-booker-qa
Project and tests developed during my time as an QA Trainee in the API https://restful-booker.herokuapp.com/ using Rest Assured


## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites
Library's and modules used in this project.

1. Java 8 - [Download SKD 1.8](https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html)
2. IntelliJ - Recommended for ease of use.
3. Maven 3.8 - [Download Binary.zip](https://ftp.unicamp.br/pub/apache/maven/maven-3/3.8.1/binaries/apache-maven-3.8.1-bin.zip)
   
   After downloading the maven binary, make sure to add the path 
   to the apache-maven/bin folder in the environment variables.
   You can test this by running on terminal the command:
      ```
      mvn -v
      ```

### Installing
A step by step series of examples that tell you have to get a development env running.
1. Clone the project to your machine.
2. Download and install all prerequisites indicated.

## Running the project and tests
After installing all modules required you can run the project and all tests via IntelliJ.
1. To do that, right-click the folder with the project and choose the option: 
   ```
   open Folder as IntelliJ Project
   ```

2. If is your first time opening the project, run this command on maven:
   ```
   maven clean install
   ```
3. Then, run the tests using Intellij:
   ```
   Right Click project 
   Choose option "Runn All Tests"
   A terminal will open with all tests created.
   ```
   
All tests will be located in the file: 
```
./src/test/
```

## Built With
* [Rest Assured](https://rest-assured.io/) - Testing and validating REST services in Java
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors
* **Matheus Zanon Nunes** - *Initial work* - [Zanon](https://github.com/zanon005)

