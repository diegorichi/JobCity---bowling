<p align="center">
  <a href="" rel="noopener">
 <img width=200px height=200px src="https://c8.alamy.com/compes/hmghd5/bowling-strike-dispersas-y-bolos-bola-de-bolos-en-la-bolera-con-motion-blur-en-bola-de-bolos-3d-rendering-hmghd5.jpg" alt="Project logo"></a>
</p>

<h3 align="center">Project Bowling</h3>

<div align="center">

[![Status](https://img.shields.io/badge/status-active-success.svg)]()
[![GitHub Issues](https://img.shields.io/github/issues/kylelobo/The-Documentation-Compendium.svg)](https://github.com/kylelobo/The-Documentation-Compendium/issues)
[![GitHub Pull Requests](https://img.shields.io/github/issues-pr/kylelobo/The-Documentation-Compendium.svg)](https://github.com/kylelobo/The-Documentation-Compendium/pulls)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](/LICENSE)

</div>

---

<p align="center"> Few lines describing your project.
    <br> 
</p>

## 📝 Table of Contents

- [About](#about)
- [Getting Started](#getting_started)
- [Deployment](#deployment)
- [Usage](#usage)
- [Built Using](#built_using)
- [TODO](../TODO.md)
- [Contributing](../CONTRIBUTING.md)
- [Authors](#authors)
- [Acknowledgments](#acknowledgement)

## 🧐 About <a name = "about"></a>

This is a Project that runs from command line and return the sheet with the game result.

## Notes

If you are runnning this into a windows system, you need to use mvnw instead mvn command line

## 🏁 Getting Started <a name = "getting_started"></a>

To get the project you can clone from my repository on github:

```
https://github.com/diegorichi/bowling.git
```
Also you can use the zip i've sended by email.

### Prerequisites

You need to install  the following.

```
https://www.oracle.com/ar/java/technologies/javase/javase-jdk8-downloads.html
https://git-scm.com/downloads
https://maven.apache.org/download.cgi
```
Also you can install the optional software to package and extend the application. <br>
An IDE:
```
https://spring.io/tools
https://www.jetbrains.com/idea/download/
https://code.visualstudio.com/download

```
If you want to use a docker image
```
https://www.docker.com/products/docker-desktop
```
##  Installing <a name = "installing"></a>

After install the required software, you can build, compile and run the project.<br>

To clean and build the package
```
mvn clean package
```
and then you can see the message at the end: <br/>
```
[INFO] BUILD SUCCESS
```

If you want to build the docker image:

```
docker build -t richi/bowling-spring-boot-docker .
```

## 🔧 Running the tests <a name = "tests"></a>

If you want to run only the test cases, just run into the root folder this simple command.

```
mvn test
```

## 🎈 Usage <a name="usage"></a>

There are three ways to use this application.<br/>

With pure java command line:
```
java -jar target\bowling-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev --data=.\gamedata.txt
```
With maven running the spring-boot application:
```
mvn spring-boot:run -Dspring-boot.run.arguments=--data=.\gamedata.txt -P dev
```
of course you can use prod as -P (Profile) parameter<br/>

With docker if you have builded the image
```
docker run -v /home/diego/projects/bowling:/tmp/ richidiego/richi:bowling-spring-boot-docker --data=/tmp/gamedata.txt
```
I want to explain this command line of docker:<br/>
-v is used to link a local folder with a folder inside the container creating a volume. In this way you can point the local folder to the folder containing the files with the data that you want to test.<br/>
Then you need to add the name of the image builded in [installing](#installing)<br/>

--data is user to refer to the game data file that you have in your local folder (remember that you've linked containing folder with -v parameter)

## 🚀 Deployment <a name = "deployment"></a>

Also to push or pull into your docker repository:
docker push richidiego/richi:bowling-spring-boot-docker
docker pull richidiego/richi:bowling-spring-boot-docker

where richidiego/richi is the your own repository imagen name

## ⛏️ Built Using <a name = "built_using"></a>

- [STS](https://spring.io/tools)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/products/docker-desktop)
- [Java 8](https://www.oracle.com/ar/java/technologies/javase/javase-jdk8-downloads.html)

## ✍️ Authors <a name = "authors"></a>

- [@Diego Richi](https://www.linkedin.com/in/diegorichi/)

## 🎉 To continue working

These items are realted to extend the bowling app funcionality

- Add a security.
- Expose with a REST api.
- Add persistence like a JPA.
- Implements another game with the same principle and base, for example a chess game.
