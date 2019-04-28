package com.EqualExperts.QAExercise.Utilities;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.EqualExperts.QAExercise.Utilities.DriverProvider.driver;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.springframework.test.util.AssertionErrors.fail;

@Component
public class SeleniumHelper {

    WebDriverWait wait15sec;

    public WebElement getElement(String str) {

        String strLocatorType = (str.split(":", 2)[0]).toLowerCase();
        String strLocatorValue = (str.split(":", 2)[1]);
        WebElement element = null;

        switch (strLocatorType) {

            case "id":
                element = driver.findElement(By.id(strLocatorValue));
                break;
            case "name":
                element = driver.findElement(By.name(strLocatorValue));
                break;
            case "partiallink":
                element = driver.findElement(By.partialLinkText(strLocatorValue));
                break;
            case "linktext":
                element = driver.findElement(By.linkText(strLocatorValue));
                break;
            case "css":
                element = driver.findElement(By.cssSelector(strLocatorValue));
                break;
            case "tagname":
                element = driver.findElement(By.tagName(strLocatorValue));
                break;
            case "xpath":
                element = driver.findElement(By.xpath(strLocatorValue));
                break;
            default:
                throw new NoSuchElementException("unknown locator Type");

        }
        return element;
    }

    public void enterText(String webElementString, String string) {
        try {
            WebDriverWait wait = new WebDriverWait(driver,15);
            WebElement element = getElement(webElementString);
            wait.until(ExpectedConditions.visibilityOf(element));
            element.click();
            element.clear();
            element.sendKeys(string);
        } catch(Exception t) {
            System.out.println("unable to entrText on the element using : "+webElementString);
            throw t;
        }
    }

    public void enterTextWithoutCleaning(String webElementString, String string) {
        try {
            WebDriverWait wait = new WebDriverWait(driver,15);
            WebElement element = getElement(webElementString);
            wait.until(ExpectedConditions.visibilityOf(element));
            element.click();
            element.sendKeys(string);
        } catch(Exception t) {
            System.out.println("unable to entrText on the element using : "+webElementString);
            throw t;
        }
    }

    public void enterUsingSendKeys(String webElementString) {
        try {
            WebElement element = getElement(webElementString);
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(Keys.RETURN);
        } catch (Exception t) {
            System.out.println("unable to enterUsingSendKeys " + webElementString);
            throw t;
        }
    }

    public boolean waitForElementVisible(WebElement element) {
        try {
            wait15sec = new WebDriverWait(driver, 15);
            wait15sec.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception t) {
            throw t;
        }
    }

    public boolean waitForElementVisible(String elementString) {
        WebElement element = getElement(elementString);
        return waitForElementVisible(element);
    }

    public boolean waitForElementClickable(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver,15);
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            return true;
        } catch (Exception t) {
            throw t;
        }
    }

    public boolean waitForElementClickable(String elementString) {
        WebElement element = getElement(elementString);
        return waitForElementClickable(element);
    }

    public List<WebElement> getElementList(String str) {
        List<WebElement> elementList;
        String strLocatorType = (str.split(":", 2)[0]).toLowerCase();
        String strLocatorValue = (str.split(":", 2)[1]).toLowerCase();

        switch (strLocatorType) {

            case "classname":
                elementList = driver.findElements(By.className(strLocatorValue));
                break;
            case "xpath":
                elementList = driver.findElements(By.xpath(strLocatorValue));
                break;
            case "cssselector":
                elementList = driver.findElements(By.cssSelector(strLocatorValue));
                break;
            case "tagname":
                elementList = driver.findElements(By.tagName(strLocatorValue));
                break;
            default:
                throw new NoSuchElementException("unknown locator Type");

        }
        return elementList;
    }


    public boolean waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long)((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
                }
                catch (Exception e) {
                    // no jQuery present
                    return true;
                }
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    public boolean isSelected(String webElementString) {
        boolean elementSelected = false;
        try {
            WebElement element = getElement(webElementString);
            waitForElementClickable(element);
            elementSelected = element.isSelected();
        } catch (NoSuchElementException e) {
            System.out.println("Unable to find if the element is selected or not  " + webElementString);
            throw e;
        }
        return elementSelected;
    }

    public void click(String webElementString) {
        try {
            WebElement element = getElement(webElementString);
            waitForElementClickable(element);
            element.click();
            //System.out.println( "page load situation is"+waitForPageToLoad());

        } catch (NoSuchElementException e) {
            System.out.println("Unable to click the element " + webElementString);
        }
    }

    public void click(WebElement element) {
        try {
            waitForElementClickable(element);
            element.click();
        } catch (NoSuchElementException e) {
            System.out.println("Unable to click the element ");
        }
    }

    public String getText(String webElementString) {

        String text = "";
        try {
            waitForElementVisible(webElementString);
            WebElement element = getElement(webElementString);
            text = element.getText();
        } catch (NoSuchElementException e) {
            System.out.println("Cant locate element  " + webElementString);
        }
        return text;
    }

    public String getTextFromHidden(String webElementString) {

        String text = "";
        try {
            WebElement element = getElement(webElementString);
            text = element.getText();
        } catch (NoSuchElementException e) {
            System.out.println("Cant locate element  " + webElementString);
        }
        return text;
    }

    public void waitTillURLContainsString(String expectedString) throws InterruptedException {

        int loop = 15;
        String url = null;
        while(loop > 0){
            url = driver.getCurrentUrl();
            if(url.contains(expectedString)){
                return;
            } else {
                Thread.sleep(1000);
            }
        }
        fail("URL does not contains the string after 15 minutes : " + expectedString);
    }

    public boolean isPresent(String webElementString) throws IOException {
        boolean elementPresent = false;
        try {
            WebElement element = getElement(webElementString);
            elementPresent = element.isDisplayed();
        } catch (NoSuchElementException e) {
            fail( "Failed to verify whether if the element is displayed in the page or not : " + webElementString);
        }
        return elementPresent;
    }


    public String getAttribute(String webElementString, String attributeName) {
        String attribute = "";
        try {
            WebElement element = getElement(webElementString);
            attribute = element.getAttribute(attributeName);
        } catch (NoSuchElementException e) {
            fail("Fail to get attribute value : " + attribute);
        }

        return attribute;
    }

    public void javaExecutor(String webElementString) {
        WebElement element = getElement(webElementString);
        javaExecutor(element);
    }

    public void javaExecutor(WebElement locator) {
        waitUntilVisibility(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", locator);
    }

    public void waitUntilVisibility(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (NoSuchElementException e) {
            fail("Element not visible");
        }
    }

    public void waitUntilVisibility(String webElementString) {
        try {
            WebElement element = getElement(webElementString);
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (NoSuchElementException e) {
            fail("Element not visible");
        }
    }


    public void selectByVisibleText(String webElementString, String visibleTextString) {
        WebElement element = getElement(webElementString);
        selectByVisibleText(element,visibleTextString);
    }

    public void selectByVisibleText(WebElement locator, String visibleTextString) {

        try {

            Select s = new Select(locator);
            s.selectByVisibleText(visibleTextString);
        } catch (NoSuchElementException e) {
            fail("Failed to select the option by index from drop down");
        }

    }

    public void getURL(WebElement locator, String visibleTextString) {

        try {

            Select s = new Select(locator);
            s.selectByVisibleText(visibleTextString);
        } catch (NoSuchElementException e) {
            fail("Failed to select the option by index from drop down");
        }

    }


    public boolean isTextPresentinElement(String headingLocString, String stringToCheck) {
        boolean textPresent = false;
        try {
            WebElement element = getElement(headingLocString);
            waitForElementVisible(element);
            textPresent=element.getText().equals(stringToCheck);
        } catch (NoSuchElementException e) {
            fail( "Failed to verify whether text present in the element or not : " + headingLocString);
        }
        return textPresent;
    }


    public boolean isGivenTextPresentInPageSource(String stringToSearch) {

        customisedWaitForPresenceofText(stringToSearch);
        return driver.getPageSource().contains(stringToSearch);
    }

    public boolean isGivenTextAbsentInPageSource(String stringToSearch) {

        customisedWaitForAbsenceofText(stringToSearch);
        return !driver.getPageSource().contains(stringToSearch);
    }

    public void customisedWaitForPresenceofText(String text) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
        wait.pollingEvery(250,  TimeUnit.MILLISECONDS);
        wait.withTimeout(30, TimeUnit.SECONDS);

        Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>()
        {
            public Boolean apply(WebDriver arg0) {
                //WebElement element = arg0.findElement(By.id("colorVar"));
               // String color = element.getAttribute("color");
                //System.out.println("The color if the button is " + color);
                if(driver.getPageSource().contains(text))
                {
                    return true;
                }
                return false;
            }
        };

        wait.until(function);
    }

    public void customisedWaitForAbsenceofText(String text) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
        wait.pollingEvery(250,  TimeUnit.MILLISECONDS);
        wait.withTimeout(30, TimeUnit.SECONDS);

        Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>()
        {
            public Boolean apply(WebDriver arg0) {
                //WebElement element = arg0.findElement(By.id("colorVar"));
                // String color = element.getAttribute("color");
                //System.out.println("The color if the button is " + color);
                if(!(driver.getPageSource().contains(text)))
                {
                    return true;
                }
                return false;
            }
        };

        wait.until(function);
    }

}
