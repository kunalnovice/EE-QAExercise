package com.EqualExperts.QAExercise.PageObjects;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WikiHomePage extends BasePage {

    String searchBoxLocator="id:searchInput";
    String searchBoxLanguageLocator ="id:searchLanguage";
    String searchButtonLocator="css:button[type=\"submit\"]";

    public boolean isCorrectPageLoaded() throws IOException {
        screenshotCapability.takeScreenShot();
        return seleniumHelper.isPresent(searchBoxLocator);

    }

    public void submitSearchStringRequest(String searchString, String language) {
        seleniumHelper.enterText(searchBoxLocator,searchString);
        selectGivenLanguage(searchBoxLanguageLocator,language);
        screenshotCapability.takeScreenShot();
        seleniumHelper.click(searchButtonLocator);

    }

    private void selectGivenLanguage(String searchBoxLanguageLocator,String language) {
        seleniumHelper.selectByVisibleText(searchBoxLanguageLocator,language);

    }
}
