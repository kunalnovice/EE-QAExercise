package com.EqualExperts.QAExercise.PageObjects;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
public class WikiSearchResultPage extends BasePage {

    String headingLocator = "id:firstHeading";
    String languageSettingButtonLocator="css:button[title=\"Language settings\"]";
    String languageSettingEnglishTextLocator = "css:.uls-display-settings-anon-label + span";
    String otherLanguagesLocator ="css:.mw-interlanguage-selector.mw-ui-button";
    String newLanguageLinkLocator="xpath://div[@class='uls-lcd-region-section']//a[contains(text(),'PLACEHOLDER')]";

    public boolean isCorrectPageDisplayed(String passedString, String language) throws InterruptedException {
        return isPageIncludingSearchString(passedString) && isCurrentURLContainingen();


    }


    public boolean isPageIncludingSearchString(String passedString) {
        screenshotCapability.takeScreenShot();
        return seleniumHelper.getText(headingLocator).equalsIgnoreCase(passedString);
    }

    public boolean isCurrentURLContainingen() {
        return getCurrentUrl().contains("en.");
    }

    public boolean isLanguageSettingContainsLanguage(String language) throws InterruptedException {
        seleniumHelper.click(languageSettingButtonLocator);
        seleniumHelper.waitForElementVisible(languageSettingEnglishTextLocator);
        boolean check= seleniumHelper.getElement(languageSettingEnglishTextLocator).getText().contains(language);
        screenshotCapability.takeScreenShot();
        seleniumHelper.click(languageSettingButtonLocator);

        return check;

    }

    public void searchResultAvailableInSelectedLanguage(String newLanguage) throws InterruptedException {

        Thread.sleep(2000);
        //seleniumHelper.waitForPageToLoad();
        seleniumHelper.click(otherLanguagesLocator);
        Thread.sleep(2000);
        WebElement newlanguageLink = identifyNewLanguageLink(newLanguage);
        Assert.assertEquals(newlanguageLink.getText(),newLanguage);
        screenshotCapability.takeScreenShot();

    }

    private WebElement identifyNewLanguageLink(String newLanguage) throws InterruptedException {
        WebElement element;
        //Thread.sleep(10000);
        newLanguageLinkLocator=newLanguageLinkLocator.replaceAll("PLACEHOLDER",newLanguage);
        if(!newLanguageLinkLocator.contains(newLanguage)) {
            int length = newLanguageLinkLocator.length();
            int place1=newLanguageLinkLocator.indexOf(",'");
            int place2=newLanguageLinkLocator.lastIndexOf("'");
            newLanguageLinkLocator=newLanguageLinkLocator.substring(0,place1+2)+newLanguage+newLanguageLinkLocator.substring(place2,length);
        }
        seleniumHelper.waitForElementVisible(newLanguageLinkLocator);
        element=seleniumHelper.getElement(newLanguageLinkLocator);
        screenshotCapability.takeScreenShot();
        return  element;
    }

    public void displaySearchResultPageInSelectedLanguage(String newLanguage) throws InterruptedException {
        WebElement newlanguageLink = identifyNewLanguageLink(newLanguage);
        Assert.assertEquals(newlanguageLink.getText(),newLanguage);
        seleniumHelper.click(newlanguageLink);
        screenshotCapability.takeScreenShot();
    }
}
