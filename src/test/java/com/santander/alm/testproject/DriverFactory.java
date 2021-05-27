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
import org.testng.annotations.Optional;

import java.io.IOException;

public final class DriverFactory {
    static WebDriver createWebDriver(@Optional("Chrome") final String browser) throws InvalidTokenException,
            AgentConnectException, ObsoleteVersionException, IOException {
        if (browser.equalsIgnoreCase("chrome")) {
            return new ChromeDriver(new ChromeOptions());
        } else if (browser.equalsIgnoreCase("msedge")) {
            return new EdgeDriver(new EdgeOptions());
        } else if (browser.equalsIgnoreCase("firefox")) {
            return new FirefoxDriver(new FirefoxOptions());
        } else {
            throw new InvalidArgumentException("Browser type '" + browser + "' not supported!");
        }
    }
}
