package com.epam.pageobject.google;

import com.epam.pageobject.Basepage;
import com.epam.pageobject.imdb.MoviePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResult extends Basepage {
    private final String TITLE= "deadpool - Google-keresés";
    @FindBy(css = "a[href='https://www.imdb.com/title/tt1431045/']")
    private WebElement imdbLink;

    public SearchResult(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public MoviePage clickOnTheLink(){
        imdbLink.click();
        return new MoviePage(getDriver());
    }
}
