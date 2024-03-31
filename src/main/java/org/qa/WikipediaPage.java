package org.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WikipediaPage {

    private final WebDriver driver;
    private final By searchInput = By.id("searchInput");
    private final By searchSuggestion = By.cssSelector(".suggestions a");
    private final By searchButton = By.cssSelector("button[type='submit']");
    private final By searchResultTitle = By.cssSelector(".mw-parser-output p b");
    private final By searchInfo = By.cssSelector("#searchInput + span");

    public WikipediaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void search(String query) {
        WebElement searchInputField = driver.findElement(searchInput);
        searchInputField.clear();
        searchInputField.sendKeys(query);
    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }

    public String getSearchSuggestionText() {
        WebElement suggestion = driver.findElement(searchSuggestion);
        return suggestion.getText();
    }

//    public void clickSearchSuggestion() {
//        driver.findElement(searchSuggestion).click();
//    }
    public void clickSearchSuggestion() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement suggestionElement = wait.until(ExpectedConditions.elementToBeClickable(searchSuggestion));
        suggestionElement.click();
    }

    public String getSearchResultTitleText() {
        WebElement resultTitle = driver.findElement(searchResultTitle);
        return resultTitle.getText();
    }

    public String getSearchInfoText() {
        WebElement info = driver.findElement(searchInfo);
        System.out.println(info);
        return info.getText();
    }
}

