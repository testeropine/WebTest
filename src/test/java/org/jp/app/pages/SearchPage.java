/*
 * Author: Prashant
 */

package org.jp.app.pages;

import com.google.inject.Inject;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@ScenarioScoped
public class SearchPage {

    @FindBy(name = "q")
    private WebElement searchTF;

    @FindBy(xpath = "//div[@id=\"search\"]//a")
    private List<WebElement> links;

    @Inject
    public SearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void enterText(String searchStr) {
        searchTF.clear();
        searchTF.sendKeys(searchStr);
        searchTF.submit();
    }

    public void clickOnLink(int index) {
        links.get(index).click();
    }
}
