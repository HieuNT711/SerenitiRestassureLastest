package example.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "D:\\PersionalCode\\SerenityApi\\src\\test\\resources\\features\\Debug.feature",
        glue = "example.steps"
)
public class RunnerTestNG extends AbstractTestNGCucumberTests {
}
