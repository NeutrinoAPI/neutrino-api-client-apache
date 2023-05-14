# NeutrinoAPI Java Apache SDK

Java client using the Apache HTTP client and Gson JSON library

The official API client and SDK built by [NeutrinoAPI](https://www.neutrinoapi.com/)

| Feature          |        |
|------------------|--------|
| Platform Version | >= 11  |
| HTTP Library     | Apache |
| JSON Library     | Gson   |
| HTTP/2           | No     |
| HTTP/3           | No     |
| CodeGen Version  | 4.6.11 |

## Getting started

First you will need a user ID and API key pair: [SignUp](https://www.neutrinoapi.com/signup/)

## To Build 
```sh
$ mvn clean install
```

## To Initialize 
```java
NeutrinoAPI neutrinoAPI = new NeutrinoAPI("<your-user-id>", "<your-api-key>");
```

## Running Examples

```sh
$ mvn compile exec:java \
-Dfile.encoding=UTF-8 \
-Dexec.mainClass=com.neutrinoapi.examples.EmailValidate
```

You can find examples of all APIs in _src/main/java/com/neutrinoapi/examples_

## For Support 
[Contact us](https://www.neutrinoapi.com/contact-us/)