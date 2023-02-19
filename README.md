# **Westpac - ReadMe**

### Prerequisites

- Git
- IntelliJ idea
- Maven
- JDK

### **How to implement**

- git clone https://github.com/devdun/DtTesting.git

- Open IntelliJ idea

- Open project

  ![img](https://github.com/devdun/westpac/blob/master/img/fileopen.png)

- There will be a popup. Select **Trust Project**

  ![img](https://github.com/devdun/westpac/blob/master/img/TrustProject.png)

### How to Run

#### Option 1

- Navigate to the root folder
- Open Git bash
- Issue the command **mvn test**

Maven Test (**mvn test**)  is used to execute the unit tests associated with a project. It runs the tests using the Surefire Plugin, which is configured in the pom.xml file. The Surefire Plugin uses the JUnit testing framework to run the tests and generates reports in both XML and plain text formats.

Basic configurations

```
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.0.0-M5</version>
            <configuration>
                <suiteXmlFiles>
                    <suiteXmlFile>westpac_tests.xml</suiteXmlFile>
                </suiteXmlFiles>
            </configuration>
        </plugin>
    </plugins>
</build>
```

The code snippet is a configuration for the Maven Surefire Plugin. This plugin is used to run unit tests of the application. The configuration specifies which test suite is to be run. In this case, the suiteXmlFile is set to westpac_tests.xml, which is the name of the test suite that is to be run.

#### Option 2

- Open Project using IntelliJ idea
- Open Lifecycle
- Select test 

#### Option 3

- Open Project using IntelliJ idea
- Navigate to westpac_tests.xml
- Run XML file

#### Run using different browsers or using headless mode

```
<!--Values Chrome Firefox HLChrome HLFirefox HTMLUnit phantomJS-->
<parameter name="browser" value="Chrome"/>
```

westpac_tests.xml replace browser value 

- value="Chrome" - *Chrome browser*
- value="HLChrome" - *Headless chrome*
- value="Firefox"  - *Firefox*



**Test Data**

#### **Reports**

Extent Reports is an HTML-based reporting library that enables automation test engineers to create beautiful, interactive, and detailed reports. The Extent Reports library uses a combination of HTML, CSS, and JavaScript to create powerful and attractive reports. The library is designed to be used with the most popular test automation frameworks such as Selenium, Appium, and others. The library also offers a powerful report viewer which allows users to view their reports in various formats such as HTML, XML, and PDF. Extent Reports also offers a powerful API that enables users to create custom reports. This makes it an ideal reporting tool for both amateur and professional testers.

Once after completed the test execution, you can find the test report under *rootfolder/test-output/MyReports/dd_mm_yyyy/westpac_Report_timestamp.html* choose the correct folder and html report
