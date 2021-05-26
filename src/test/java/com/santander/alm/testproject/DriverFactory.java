package com.santander.alm.testproject;

import io.testproject.sdk.drivers.web.ChromeDriver;
import io.testproject.sdk.drivers.web.EdgeDriver;
import io.testproject.sdk.drivers.web.FirefoxDriver;
import io.testproject.sdk.internal.exceptions.AgentConnectException;
import io.testproject.sdk.internal.exceptions.InvalidTokenException;
import io.testproject.sdk.internal.exceptions.ObsoleteVersionException;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;

public final class DriverFactory {
    static WebDriver createWebDriver (String browserType) throws InvalidTokenException, AgentConnectException, ObsoleteVersionException, IOException {
        if (browserType.equalsIgnoreCase("Chrome")) {
            return new ChromeDriver(new ChromeOptions());
        }
        else if (browserType.equalsIgnoreCase("Edge")) {
            return new EdgeDriver(new EdgeOptions());
        }
        else if (browserType.equalsIgnoreCase("Firefox")) {
            return new FirefoxDriver(new FirefoxOptions());
        }else {
            throw new InvalidArgumentException("Browser type '" + browserType + "' not supported!");
        }
    }
}
