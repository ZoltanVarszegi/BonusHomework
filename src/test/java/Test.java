import com.epam.pageobject.google.SearchPage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test {
    private static WebDriver driver;
    private SearchPage searchPage;

    @Before
    public void setUp(){
        driver = new FirefoxDriver();
        searchPage= new SearchPage(driver);
        driver.get("https://www.google.com/");
    }

    @org.junit.Test
    public void writeToFileTheTitleAndRating(){
        searchPage.fillInTheSearchfield("Deadpool").clickOnTheSearchButton().clickOnTheLink().writeToFile().clickOnTheStar().getMovieNameAndRating();

    }
    @After
    public void tearDown(){
        driver.close();
    }
}
