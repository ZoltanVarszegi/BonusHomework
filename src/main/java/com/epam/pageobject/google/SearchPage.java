package com.epam.pageobject.google;

import com.epam.pageobject.Basepage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SearchPage extends Basepage {

    private final String TITLE= "Google";
    @FindBy(css = "input[name=\"q\"]")
    private WebElement searchField;
    @FindBy(css = ".FPdoLc.VlcLAe>center>input[name= \"btnK\"]")
    private WebElement searchButton;


    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SearchPage fillInTheSearchfield(String s){
        searchField.sendKeys(s);
        return this;
    }

    public SearchResult clickOnTheSearchButton(){
        searchButton.click();
        return new SearchResult(getDriver());
    }
}
