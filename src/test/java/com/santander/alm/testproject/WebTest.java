package com.santander.alm.testproject;

import io.testproject.sdk.drivers.ReportingDriver;
import io.testproject.sdk.interfaces.parameterization.TestProjectParameterizer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

import org.testng.annotations.*;

import java.util.Map;


public class WebTest {

    private WebDriver driver = null;

    @AfterTest
    void afterClass() {
        try {
            driver.quit();
        } catch (Exception ignored) {
        }
        driver = null;
    }

    private WebDriver getDriver(String browser) throws Exception {
        if (driver == null) {
            driver = DriverFactory.createWebDriver(browser);
        }
        return driver;
    }

    @Test(dataProvider = "TestProject", dataProviderClass = TestProjectParameterizer.class)
    public void mustLogin(@Optional("Chrome") final String browser, @Optional("John Smith") final String user, @Optional("12345") final String password) throws Exception {
        getDriver(browser);
        ((ReportingDriver) driver).report().step("Logging into page...");
        Map<String, String> envVars = System.getenv();
        ((ReportingDriver) driver).report().step("Env vars..." + envVars);
        driver.navigate().to("https://example.testproject.io/web/");
        driver.findElement(By.cssSelector("#name")).sendKeys(user); // "John Smith"
        driver.findElement(By.cssSelector("#password")).sendKeys(password); // "12345"
        driver.findElement(By.cssSelector("#login")).click();
        ((ReportingDriver) driver).report().step("Logging out...");
        boolean passed = driver.findElement(By.cssSelector("#logout")).isDisplayed();
        if (passed) {
            ((ReportingDriver) driver).report().step("Test Passed", true, true);
        } else {
            ((ReportingDriver) driver).report().step("Test Failed", false, true);
        }
    }


}
