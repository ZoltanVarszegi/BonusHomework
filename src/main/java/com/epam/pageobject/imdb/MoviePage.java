package com.epam.pageobject.imdb;

import com.epam.pageobject.Basepage;
import org.openqa.selenium.WebDriver;

public class MoviePage extends Basepage {
    private final String TITLE= "Deadpool (2016) - IMDb";

    public MoviePage(WebDriver driver) {
        super(driver);
    }
}
