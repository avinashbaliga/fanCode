## Todo Verification of FanCode city users ##

### Architecture ###
The framework uses BDD design principles and implemented using Cucumber and Gherkin language

### Tech stack/Tools
    Language: Java
    Testing framework: Testng and Cucumber with Gherkin for feature files
    http client: RestAssured
    
### Steps to run the tests
1. Clone the repo into a local IDE.
2. Check if [testConfig.properties](src/main/resources/testConfig.properties) file contains values for all the declared properties.
3. Open the [Runner](src/test/java/org/automation/runners/TestNGRunner.java) and click on the play button beside it to run the tests.
4. Open the [report](src/main/resources/report.html) file to view the test result.