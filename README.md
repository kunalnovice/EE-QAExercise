This is a BDD framework using cucumber-jvm , spring , selenium  , rest-assured and few JSON libraries
1.download all dependencies. mvn clean compile
2. currently verified for chrome\firefox browser only.
    verify the driver version in DriverProvider.GetLocalChromeDriver method


The framework is structured in a way that UI or API test can be run independently.
    if there comes a requirement of executing API actions followed by UI Actions in a single test
    to save execution time- the framework can be handle it in its current form.

___________________________________________________________________________________________

"mvn clean verify" to clean, run and generate both version of clubbed HTML report.

"mvn clean test cluecumber-report:reporting" -------> to clean, run and generate HTML Report 1

"mvn clean -Dtest=TestRunnerAPI test cluecumber-report:reporting" --> to clean , run API test only. same can be done for UI.

"mvn clean -Dtest=TestRunnerAPI test" amd "mvn cluecumber-report:reporting" can be run independently, if required.


___________________________________________________________________________________________
Output
1. Clubbed HTML Report is generated in target/cucumber-clubbed-report/index.html
   another version is available at target/cucumber-masterthought-reports/cucumber-html-reports/overview-features.html
   open either of the above HTML report in any browser to see the test stats.
2. Screenshots(with meaningful names) for web based test generated at target/Screenshots
    In delete Booking UI test, screenshots can be viewed before and after deletion.
3. Basic cucumber Report generated in target/cucumber-original-html-**/index.html
4. API Test Reports include Request and Response for investigation purpose.

  HTML Reports are generated on back of JSON Reports which are itself individually (for API and UI) generated in target/cucumber-json-reports
  HTML reports are cumulative version of each run


Features:
1. Demonstration of Decorator Pattern evident in form of SeleniumHelper class(wrapper over selenium functions)
2. Demonstration of Journey and POM Pattern evident in Step Defs and PageObjects package
3. Demonstration of separation of test data by using external property file to drive tests
4. Demonstration of separation of locators from the tests enhances reusability , readability.
5. Demonstration of scalability(of tests) by using Scenario Outline at no additional cost of code writing.
6. Heavy usage of Spring Dependency Injection and Spring Boot.
7. Framework designed to easily integrate with any CI tool i.e Jenkins(externalising configs, reporting, etc)
8. Framework is designed keeping Maintenance and usability in mind.
9. Screenshot captured at the end of UI test. whole journey can be captured but ususally not required.
   Along with screenshot, url at the time and Time is printed as well.
10. API Request Response Pair is shown in the Report for investigation purpose.


Improvements possible(4 weeks long effort)
1. current code can be enhanced to perform other browser testing and remote testing i.e saucelabs,BS,CBT as well.
2. current code can be enhanced to perform parallel testing.
3. Detailed Logging can be provided in text files( info\error.log) for investigation purpose
4. Assertion Libraries can be created for reusability.
5. API BDD Testing can be improved by writing common library for invoking Requests.
6. CI tools integration by writing DSL pipeline i.e inputting all the parameters from Jenkins pipeline
7. It generate rerun.txt which can further be used for rerunning failure test, if required.
8. using spring profile or maven profile we can overwrite values in the property file at run time.
   i.e though browser=firefox but tests can be run on chrome without explicitly making a code change. only mvn command change is required.
9. for complicated JSON Assertion- JAVA libraries can be used
10 for complicated JSON Response evaluation -Serialisation and Deserialisation of JSON can be used.