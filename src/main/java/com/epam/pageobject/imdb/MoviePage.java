package com.epam.pageobject.imdb;

import com.epam.pageobject.Basepage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MoviePage extends Basepage {

    @FindAll({@FindBy(css = ".credit_summary_item>a[href*=\"st\"]")})
    private List<WebElement> stars;
    @FindBy(css = ".title_wrapper>h1")
    private WebElement movieTitle;
    @FindBy(css = ".ratingValue>strong>span")
    private WebElement rating;
    public MoviePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public StarPage clickOnTheStar(){
        stars.get(0).click();
        return new StarPage(getDriver());
    }

    public Map<String, Double> getTitleAndRating(){
        Map<String, Double> titleAndRating= new HashMap<String, Double>();
        titleAndRating.put(movieTitle.getText(), Double.parseDouble(rating.getText()));
        return titleAndRating;
    }

    public MoviePage writeToFile(){
        try {
            String content;
            File file = new File("C:\\EPAM\\BonusHomeWork\\abc.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("My favorite movie: "+movieTitle.getText()+"\n");
            bw.write("Stars: ");
            for(int i=0; i<3; i++){
                content= ""+ stars.get(i).getText() + ", ";
                bw.write(content);

            }
            bw.write("\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
}
