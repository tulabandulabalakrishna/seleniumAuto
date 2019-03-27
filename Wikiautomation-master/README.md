##################################################################
README
##################################################################
Automated test suite for Wiki search validations
About the project: This is project is about automating the Wiki page navigations and content validations. Apart from the test scenarios this gives a light weight testing framework and can be enhanced for testing all web applications.
Project Structure:
 Src\main\java
 	 com.epam.core – DriverFactory.java , GuiComponents.java
	 com.epam.utils – Constants.java , PropertiesLoader.java


Test\java
	Testdata – testcase.properties
	Testsecnarios – WikiSearch.java

	TestRunner.java

Run tests -
Testcase running will start from the TestRunner.java class and we have to pass the browser from command line or through VM arguments

mvn clean install -Dbrowser=chrome
# run the tests in chrome
