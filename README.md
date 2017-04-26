Test Framework: TestNG
Build Tool: Maven
BDD Framework: Cucumber
Logger: Log4j

Have used surefire maven plugin to run the tests.
Project can be run with the standart Maven tasks - install.
Tests are integrated in the build process and run as part of it. 

For the CI implementation choosed to use Travis CI as it can be easily accessed and you can run it.(compared to Jenkis)
https://travis-ci.org/NimraRelle/Task1
Currently it is configured to run after each commit in the repo.

Tested endpoints:
http://api.openweathermap.org/data/2.5/forecast
http://api.openweathermap.org/data/2.5/weather

Project Structure:

src/main/respources/set.propertties 
- stores the api url and the generated token

src/test/resources/features
- stores two feature files with tests for the two endpoints.
 @OpenWeather tag is a global one for both endpoints, will run both feature files with all tests inside them
 @forecast tag will run just http://api.openweathermap.org/data/2.5/forecast tests
 @weather tag will run just http://api.openweathermap.org/data/2.5/weather tests
 Have added also @forecastValid @forecastInvalid / @weatherValid @weatherInvalid
 for separation in between the type of status codes 

src/test/resources/
- stores two JSON Schemes for the two endpoints

src/test/java/stepDef
- stores the StepDef class with the step definitions
  used in the feature files
  
src/main/java/utils
- stores ReadConfig.class used to read the set.properties file
- ReportMerger. class used to merge the cucumber reports from the different runner classes

src/main/java/restClient
- stores ApiRestClient.java class where all the methods used for the api tests are implemented
- stores two cucumber Runner classes 

In order  to run the cucumber tests I use classes called RunnerForecast and RunnerWeather 
each of them is set to run a different api endpoint tag (@forecast and @weather). Those runners 
are added in a specific xml file (in my case called testingAPI.xml) used by TestNG as a 
reference what exactly to be run and how. In order to make both runner classes run in parallel the
following settings has been added parallel="classes" thread-count="2". To be able to run those tests as part
of the maven build I use maven-surefire-plugin where the testingAPI.xml file is set and Runner
classes are recognized as unit tests and run.

Merged report from the two runners is generated in folder: target/cucumber-report/index.html
In folders target/report1 and target/report2 are kept the separated reports for each of the runners.
While searching in google, found a plugin which can automatically generate runner classes, rather
than manually: cucumber-jvm-parallel-plugin. But will experiment with it separately from the task :)











