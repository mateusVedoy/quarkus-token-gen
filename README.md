# token-gen

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/token-gen-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)

## About project

### How to use it
Quarkus Rest API to build JWT token. Users must inform some information as dto bellow:
```json
{
	"login": "mateus@email.com",
	"roles": [
		"lesse",
		"lessor"
	],
	"claims": [
		{
			"key": "email",
			"value": "mymail@mail.me"
		},
		{
			"key": "registerType",
			"value": "lessor"
		}
	]
}
```
All those properties are mandatory and needed to build token. Roles and claims are free to contain anything you desire. Just need to follow the pattern.

If everything is OK, you'll receive the response bellow: 
```json
{
	"status": 200,
	"result": [
		{
			"type": "JWT",
			"payload": "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJsb2NhbGhvc3QiLCJ1cG4iOiJtYXRldXNAZW1haWwuY29tIiwiZ3JvdXBzIjpbImxlc3NvciIsImxlc3NlIl0sImVtYWlsIjoibXltYWlsQG1haWwubWUiLCJyZWdpc3RlclR5cGUiOiJsZXNzb3IiLCJpYXQiOjE2OTcyMDY5NTMsImV4cCI6MTY5NzIwNzI1MywianRpIjoiMTEyYWU2NGEtZjBjNC00Zjg5LWE5NGQtOWRlZTk4YmVkNmIyIn0.k2Lwev9ejZkwMkpBpy9ybPs6Af7p673rgAhR11qyHUifqdsBMAfei8XfUHdMc4K51rTJZ8U8wWtl6IaPcuaRcFeClez-wfAC18NtwE9z8Tp68sSAKhaL2pCI22sEUJYY_CXKL68oCOew3FZwNgczBLtT7THDI3K6J4bg88JYt4Ia0YR0nfLpgZBMLM4-B0DkczpmItgNZWiMyd8ll2-_Df1a6heBmL3s6PqiCUfj22xiYKGYtoxxF4OdVOxwfOTo63GeIzdwtL4lZ9ldLcOjY9cQHSOaBz5GdDLvyuFktWh-WfO9KhkmreLk2GNLHZz9si1sg30z0TRKZxpZOUH1Gw"
		}
	]
}
```

Otherwise, you'll receive some errors. For example:
```json
{
	"status": 400,
	"result": [
		"Issuer invalid. Must contain a valid URL host"
	]
}
```

### Architecture
Contains two of three layers of hexagonal architecture. But why? 'Cause there's no need to configure or comunicate to external applications or services. **Application** layer contain application business rules as use cases, conversions, API response pattern and dtos. **Domain** layer contain business entities and its validations.