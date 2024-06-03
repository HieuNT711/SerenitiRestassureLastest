package example.runner;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

public class ParallelRunnerMain {
    public static void main(String[] args) {
        // Create a TestNG instance
        TestNG testng = new TestNG();

        // Create a suite
        XmlSuite suite = new XmlSuite();
        suite.setName("ParallelTests");
        suite.setParallel(XmlSuite.ParallelMode.TESTS);
        suite.setThreadCount(2);

        // Create tests
        XmlTest test1 = new XmlTest(suite);
        test1.setName("Test1");
        List<XmlClass> classes1 = new ArrayList<>();
        classes1.add(new XmlClass("example.runner.RunnerTestNG"));
        test1.setXmlClasses(classes1);

        XmlTest test2 = new XmlTest(suite);
        test2.setName("Test2");
        List<XmlClass> classes2 = new ArrayList<>();
        classes2.add(new XmlClass("example.runner.RunnerTestNG2"));
        test2.setXmlClasses(classes2);
//        D:\PersionalCode\SerenityApi\src\test\java\example\runner\Runner.java

        // Add the suite to the list of suites to run
        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);
        testng.setXmlSuites(suites);

        // Run the suite
        testng.run();
    }
}