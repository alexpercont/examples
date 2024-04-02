package io.github.alexpercont.automation.examples.cucumberjunit4;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "features",
        glue = "io.github.alexpercont.automation.examples.cucumberjunit4.steps"
)
public class TestRunner {
}
