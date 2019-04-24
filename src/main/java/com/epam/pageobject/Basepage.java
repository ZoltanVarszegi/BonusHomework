package com.epam.pageobject;

import org.openqa.selenium.WebDriver;

public class Basepage {
    private WebDriver driver;

    public Basepage(WebDriver driver){
        this.driver= driver;
    }

    public WebDriver getDriver(){
        return driver;
    }

}
