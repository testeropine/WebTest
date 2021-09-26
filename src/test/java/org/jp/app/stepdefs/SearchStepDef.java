/*
 * Author: Prashant
 */

package org.jp.app.stepdefs;

import com.google.inject.Inject;
import io.cucumber.java8.En;
import org.jp.app.pages.HomePage;
import org.jp.app.pages.SearchPage;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchStepDef implements En {

    @Inject
    private WebDriver webDriver;

    @Inject
    private SearchPage searchPage;

    @Inject
    private HomePage homePage;

    public SearchStepDef() {

        When("user search for {string}", this::searchText);
        When("click on first link", this::clickLink);
        Then("company logo should be shown", this::verifyLogo);

        After("@closeBrowser", this::tearDown);
    }

    private void openBrowser() {
        webDriver.get("https://google.com");
    }

    private void searchText(String searchStr) {
        openBrowser();
        searchPage.enterText(searchStr);
    }

    private void clickLink() {
        searchPage.clickOnLink(0);
    }

    private void verifyLogo() {
        assertThat("Company log is not displayed.", homePage.isLogoDisplayed(), equalTo(true));
    }

    private void tearDown() {
        webDriver.quit();
    }
}
