package com.EqualExperts.QAExercise.Utilities;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class DriverProvider {
    @Autowired
    TestDataPropertyReader testDataPropertyReader;

    public static WebDriver driver;


    public void closeDriver(){
        driver.quit();
    }

    public void loadDriver() throws Exception {
        if(this.driver != null){
            this.driver.quit();
        }


        this.driver = createLocalDriver();

    }


    private WebDriver createLocalDriver() throws Exception {
        String browser = testDataPropertyReader.getPropertyValue("browser");
        WebDriver driver = null;
        if(browser.equalsIgnoreCase("chrome")){
            driver = GetLocalChromeDriver();
        } else if(browser.equalsIgnoreCase("firefox")){
            driver = GetLocalFirefoxDriver();
        } else if(browser.equalsIgnoreCase("ie")){
            driver = GetLocalIEDriver();
        } else {
            throw new Exception("Unsupported browser option :" + browser);
        }

        return driver;
    }


    public WebDriver getDriver() throws Exception {
        if(driver == null){
            loadDriver();
        }
        return this.driver;
    }

    private WebDriver GetLocalChromeDriver(){
        WebDriver driver = null;
        try {
            WebDriverManager.chromedriver().version("73.0.3683.68").setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-infobars");
            options.addArguments("--dns-prefetch-disable");
            options.addArguments("--disable-extensions");
            options.addArguments("--start-maximised");
            options.setExperimentalOption("useAutomationExtension", false);
            //options.addArguments("--headless");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().deleteAllCookies();
        } catch (WebDriverException e){
            throw e;
        }
        return driver;
    }

    private WebDriver GetLocalIEDriver() {
        WebDriver driver = null;
        try {
            WebDriverManager.iedriver().setup();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
            capabilities.setBrowserName("internet explorer");
            //capabilities.setCapability("ie.ensureCleanSession",true);
            //capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
            //capabilities.setCapability(InternetExplorerDriver.IE_SWITCHES,"-private");
            //capabilities.setCapability("ignoreZoomSetting",true);
            //capabilities.setCapability("nativeEvents",true);
            //capabilities.setCapability("unexpectedAlertBehaviour",true);
            //capabilities.setCapability("ignoreProtectedModeSettings",true);
            //capabilities.setCapability("disable-popup-blocking",true);
            //capabilities.setCapability("enablePersistentHover",true);
            driver = new InternetExplorerDriver(capabilities);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().deleteAllCookies();
        } catch (WebDriverException e){
            throw e;
        }
        return driver;
    }

    private WebDriver GetLocalFirefoxDriver(){
        WebDriver driver = null;
        try {
            WebDriverManager.firefoxdriver().setup();
            FirefoxProfile profile = new FirefoxProfile();
            profile.setAcceptUntrustedCertificates(true);
            profile.setPreference("network.proxy.type", 5);
            profile.setPreference("network.http.phishy-userpass-length", 255);
            DesiredCapabilities capabilities = new DesiredCapabilities("firefox", "", Platform.ANY);
            capabilities.setCapability(FirefoxDriver.MARIONETTE, profile);
            capabilities.setCapability("acceptInsecureCerts", true);
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            capabilities.setBrowserName("firefox");
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        } catch (WebDriverException e){
            throw e;
        }
        return driver;
    }





}
