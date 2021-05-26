package com.santander.alm.testproject;

import io.testproject.sdk.drivers.ReportingDriver;
import io.testproject.sdk.interfaces.parameterization.TestProjectParameterizer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class WebTest {

    private static WebDriver driver;

    @BeforeClass
    static void setup(String browserType) throws Exception {
        driver = DriverFactory.createWebDriver("Chrome");
    }

    @AfterClass
    static void teardown() {
        try {
            driver.quit();
        } catch (Exception ignored) {
        }
    }

    @Test(dataProvider = "TestProject", dataProviderClass = TestProjectParameterizer.class)
    public void mustLogin(final String user, final String password) throws Exception {
        ((ReportingDriver) driver).report().step("Logging into page...");
        driver.navigate().to("https://example.testproject.io/web/");
        driver.findElement(By.cssSelector("#name")).sendKeys(user); // "John Smith"
        driver.findElement(By.cssSelector("#password")).sendKeys(password); // "12345"
        driver.findElement(By.cssSelector("#login")).click();
        ((ReportingDriver) driver).report().step("Logging out...");
        boolean passed = driver.findElement(By.cssSelector("#logout")).isDisplayed();
        if (passed) {
            ((ReportingDriver) driver).report().step("Test Passed");
        } else {
            ((ReportingDriver) driver).report().step("Test Failed", false, true);
        }
    }


}

