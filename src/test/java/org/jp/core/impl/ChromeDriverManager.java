/*
 * Author: Prashant
 */

package org.jp.core.impl;

import com.google.inject.Inject;
import org.jp.core.managers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.inject.Named;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class ChromeDriverManager extends DriverManager {

    @Inject
    @Named("page.load.timeout.minutes")
    private long pageTimeoutInMinutes;

    @Inject
    @Named("page.wait.seconds")
    private long implicitWaitInSeconds;

    @Override
    protected void createDriver() {
        String driverPath = System.getenv("CHROME_DRIVER");
        // driver env variable is not set fetch the driver path from config
        if(null == driverPath) {
            Properties properties = new Properties();
            try {
                properties.load(this.getClass().getClassLoader()
                        .getResourceAsStream("config/drivers/ChromeDriver.properties"));
                driverPath = properties.getProperty("path");
            } catch (IOException e) {
                throw new RuntimeException("Chrome driver is not found.");
            }
        }

        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(pageTimeoutInMinutes));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitInSeconds));
    }

    @Override
    public WebDriver getDriver() {
        if(null == driver)
            createDriver();
        return driver;
    }
}
