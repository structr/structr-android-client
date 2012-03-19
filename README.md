structr Android Client
======================

The classes in this repository enable your Android App to connect to a REST web service built with structr. You can use structr-core and structr-rest to build a domain-specific REST API. structr is backed by the Neo4j graph database.

## Features
- Asynchronous connectors to keep the user interface responsive while loading data in the background
- SSL support
- Fully automatic serialization and deserialization with GSON
- Use POJOs in your android code
- Use @Expose annotation to map POJO fields to structr REST output
- ...

Please note that this software is early alpha status. Use carefully at your own risk.

## Step 1: Build structr-android-client
- Clone this repository
- Build JAR file: run "mvn install"
- Create javadocs: run "mvn generate-sources javadoc:javadoc"

## Step 2: Integrate structr-android-client in your Android app
- Copy target/structr-android-client-0.1-SNAPSHOT from step 1 into the libs/ directory of your android app
- Call StructrConnector.initialize() in the onCreate() method of your main activity
- Use 