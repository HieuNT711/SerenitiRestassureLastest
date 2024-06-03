package example.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "D:\\PersionalCode\\SerenityApi\\src\\test\\resources\\features\\Debug2.feature",
        glue = "example.steps"
)
public class RunnerTestNG2 extends AbstractTestNGCucumberTests {
}
