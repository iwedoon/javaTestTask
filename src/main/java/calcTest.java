import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class calcTest {
    String textForGoogleHomeSearching = "Калькулятор";
    String formulaForCalc = "1*2-3+1=";
    String expectedResultInCalc = "0";
    WebDriver driver;
    googleHome gHome;
    googleCalc gCalc;

    @BeforeTest
    public void prepearing(){
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        gHome = new googleHome(driver);
        gCalc = new googleCalc(driver);
        gHome.openHome();
        gHome.typeTextAndSearch(textForGoogleHomeSearching);
        gCalc.typeFormula(formulaForCalc);

    }
    @Test
    public void calcTest() {
        Assert.assertTrue(gCalc.expectedMemoryText.contentEquals(gCalc.getMemoryString()),
                "After typing formula to calculator in memory string are text (" + gCalc.getMemoryString() + ") " +
                        " that not equal expected (" + gCalc.expectedMemoryText + ")");

        Assert.assertTrue(gCalc.getResultString().contentEquals(expectedResultInCalc),
                "After typing formula, result text (" + gCalc.getResultString() + ") " +
                        "are not equal expected result (" + expectedResultInCalc + ")");

    }

    @AfterTest
    public void cleaning() {
        driver.quit();
    }
}
