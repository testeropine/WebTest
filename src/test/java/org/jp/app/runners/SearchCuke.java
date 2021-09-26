/*
 * Author: Prashant
 */

package org.jp.app.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@sunny",
        features = "classpath:features",
        glue = {
                "org.jp.app.stepdefs"
        },
        plugin = {
                "pretty",
                "html:build/jp/search.html",
                "json:build/jp/search.json"
        }
)
public class SearchCuke {
}
