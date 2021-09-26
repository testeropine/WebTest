/*
 * Author: Prashant
 */

package org.jp.app.pages;

import com.google.inject.Inject;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@ScenarioScoped
public class HomePage {

    private final WebDriver driver;

    @FindBy(className = "first-logo")
    private WebElement logo;

    @Inject
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isLogoDisplayed() {
        return  (Boolean) ((JavascriptExecutor)driver)
                .executeScript("return arguments[0].complete "
                        + "&& typeof arguments[0].naturalWidth != \"undefined\" "
                        + "&& arguments[0].naturalWidth > 0", logo);
    }
}
