package org.qa;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class WikipediaSearchTest {

    private WebDriver driver;
    private WikipediaPage wikipediaPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.edge.driver", "D://Telechargements//edgedriver_win64//msedgedriver.exe");
        driver = new EdgeDriver();
        driver.get("https://ru.wikipedia.org/");
        wikipediaPage = new WikipediaPage(driver);
    }

    @Test
    public void testSearchSuggestionStartsWithQuery() {
        wikipediaPage.search("Java");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement suggestionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".suggestions a")));
        String suggestion = suggestionElement.getText();

        Assert.assertTrue(wikipediaPage.getSearchSuggestionText().startsWith("Java"));
    }

    @Test
    public void testClickSearchSuggestion() {
        wikipediaPage.search("Java");
        wikipediaPage.clickSearchSuggestion();
        String resultTitle = wikipediaPage.getSearchResultTitleText();
        assertEquals("Java", resultTitle);
    }

    @Test
    public void testSearchSuggestionClick() {
        wikipediaPage.search("Java");
        wikipediaPage.clickSearchSuggestion();
        String resultTitle = wikipediaPage.getSearchResultTitleText();
        assertEquals("Java", resultTitle);
    }

    @Test
    public void testSearchButtonRedirect() {
        wikipediaPage.search("Java");
        wikipediaPage.clickSearchButton();
        String info = wikipediaPage.getSearchInfoText();
        assertEquals("Java", info);
    }

    @After
    public void tearDown() {
        this.driver.quit();
    }
}
