/*
 * Author: Prashant
 */

package org.jp.core.managers;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {

    protected WebDriver driver;

    protected abstract void createDriver();

    public abstract WebDriver getDriver();
}
