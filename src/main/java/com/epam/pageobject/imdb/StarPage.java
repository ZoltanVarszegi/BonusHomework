package com.epam.pageobject.imdb;

import com.epam.pageobject.Basepage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyStore;
import java.util.*;

public class StarPage extends Basepage {
    @FindAll({ @FindBy(css = ".knownfor-title-role>a")})
    private List<WebElement> relatedMovies;
    @FindAll({@FindBy(css = ".news_item>a[href*=\"nwr\"]")})
    private List<WebElement> relatedNews;

    public StarPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void getMovieNameAndRating(){
        Map<String, Double> allMovieTitleAndRating= new HashMap<String, Double>();
        String link;
        for(int i=0; i<=3; i++){
            link= relatedMovies.get(i).getAttribute("href");
            WebDriver driver2= new FirefoxDriver();
            driver2.get(link);
            MoviePage newMoviePage= new MoviePage(driver2);
            allMovieTitleAndRating.putAll(newMoviePage.getTitleAndRating());
            driver2.close();
        }
        writeToFile(sortedHashMap(allMovieTitleAndRating));

    }
    public void writeToFile(Map<String, Double> titleAndRating){
        try {
            String content;
            File file = new File("C:\\EPAM\\BonusHomeWork\\abc.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Related movies: \n");
            for(Map.Entry<String, Double> e : titleAndRating.entrySet()){
                content= ""+ e.getKey() + " - " + e.getValue() + "\n";
                bw.write(content);
            }
            bw.write("\n");
            bw.write("Related News:\n");
            for(WebElement e : relatedNews){
                content= ""+ e.getAttribute("href") + "\n";
                bw.write(content);
            }
            bw.write("\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedHashMap<String, Double> sortedHashMap(Map<String, Double> unsorted){
        List<String> mapKeys = new ArrayList<String>(unsorted.keySet());
        List<Double> mapValues = new ArrayList<Double>(unsorted.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap<String, Double> sortedMap =
                new LinkedHashMap<String, Double>();

        Iterator<Double> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Double val = valueIt.next();
            Iterator<String> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                double comp1 = unsorted.get(key);
                double comp2 = val;

                if (comp1==comp2) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }
}
