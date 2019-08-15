package com.feiyue.cucumber.fstructure;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by jisongZhou on 2019/8/6.
 **/

@RunWith(Cucumber.class)
@CucumberOptions(tags = "@FStructureTest",
        glue = {"com.feiyue"},
        plugin = {"json:target/cucumber/cucumber.json", "html:target/cucumber", "pretty"},
        features = "src/test/resources/feature/")
public class FStructureTest {

}
