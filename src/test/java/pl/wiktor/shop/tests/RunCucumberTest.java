package pl.wiktor.shop.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "pretty",
        features = {
//                "src/test/resources/feature/user/user.feature",
                "src/test/resources/feature/item/item.feature"
        })
public class RunCucumberTest {
}