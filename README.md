#Building

In order to build application Java 7 is required.
Application is build with command:

```
./gradlew clean build
```

Gradle Wrapper will automatically download Gradle build tool. It may take some time.

#Using

After successful build, output jar resides in directory `build/libs` and can be run with command:

```
java -jar ./build/libs/GoEuroDevTest-1.0.jar "New York"
```

By default application writes result to file `output.csv` residing in working directory.
File location and name can be customized by setting `output.file.path` property.
For example:

``` 
java -jar -Doutput.file.path=bielice.csv ./build/libs/GoEuroDevTest-1.0.jar "Bielice"
```

#Project structure
Production code resides in: `src/main`
Test code resides in: `src/test`
Tests are written using Groovy-base framework called Spock that makes test human-readable.

##Packages structure

`ziarko.goeuro.boundary` - package for components responsible for command line parsing and exception handling

`ziarko.goeuro.domain` - package containing infrastructure-agnostic classes (without any references to CSV or HTTP protocol),
examples: `SuggestionRepository`, `SuggestionsConsumer`, `SuggestionService`

`ziarko.goeuro.infrastructure` - package containing specific implementations/configurations of components from domain,
examples: `InfraConfig` and `csv.CsvFileSuggestionsConsumer`

#Used libraries
* Spring Framework
* Spring Boot
* Retrofit
* OpenCSV
* Guava
* Groovy (just for tests)
* Spock (just for tests)



