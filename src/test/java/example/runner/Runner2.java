package example.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/Debug2.feature",
        glue = {"example.steps"},
        plugin = {"pretty"}
//        tags = "@1"
)
public class Runner2 {
}