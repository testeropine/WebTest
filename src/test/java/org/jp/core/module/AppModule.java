/*
 * Author: Prashant
 */

package org.jp.core.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import org.apache.commons.lang3.StringUtils;
import org.jp.core.impl.ChromeDriverManager;
import org.jp.core.managers.DriverManager;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {

        readConfig();

        bind(DriverManager.class)
                .to(ChromeDriverManager.class)
                .in(Scopes.SINGLETON);

    }

    @Provides
    public WebDriver getDriver(DriverManager driverManager) {
        return driverManager.getDriver();
    }

    private void readConfig() {
        Properties defaults = new Properties();

        try {
            String environment = Optional.ofNullable(System.getenv("CUKE_ENV")).orElse("test");

            Properties properties = new Properties(defaults);
            properties.load(ClassLoader.class.getResourceAsStream(
                    StringUtils.join("/config/", environment, "/config.properties")));

            Names.bindProperties(binder(), properties);
        } catch (IOException e) {
            throw new RuntimeException("Could not load config: ", e);
        }
    }

}
