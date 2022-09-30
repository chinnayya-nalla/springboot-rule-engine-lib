# Rule Engine

## Table of contents
* [Introduction](#introduction)
* [Setup](#setup)

## Introduction
Rule Engine is a very lightweight Pojo based Spring Boot library, the idea behind this library is to remove dependency on validation & data processing rule in business layer. 

This framework doesn’t make use of XML files or any Domain Specific Language files to segregate rules from the application. It uses annotation-based classes and methods for injecting business logic into the application.

Rule Engine can be handy for developers to create and maintain applications with business logic that's entirely separated from the application itself. On the other hand, as this framework doesn’t implement the JSR94 standard and the business logic has to be coded straight to Java code.

Its main objectives are as follows:
- Business Validations
- Data Processing
 

## Setup

```
cd rule-engine-lib
sh gradlew clean build
cd build/libs/

publish rule-engine-lib-<version>.jar to your artifactory and add that dependency in required projects
```

## Examples to Use


## Contributing

I've released Rule Engine because it helps us better scale and supports the many OSS projects. I hope that other organizations can benefit from the project. I am thankful for any contributions from the community.


## Contacts

Please feel free to Contact [ Naidu ](chinnayya.nalla.careers@gmail.com) to know more about this library

## License


