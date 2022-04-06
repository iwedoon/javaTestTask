import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class googleHome {
    private final WebDriver driver;
    public final String googleUrl = "http://google.com";
    private WebElement searchInput, searchButton;

    private void findAndSetElements() {
        searchInput = driver.findElement(By.xpath("//input[@name='q']"));
    }

    public googleHome(WebDriver driver) {
        this.driver = driver;
    }

    public void typeTextAndSearch (String txt){
            searchInput.click();
            searchInput.sendKeys(txt, Keys.ENTER);
        }


    public void openHome() {
        driver.get(googleUrl);
        findAndSetElements();
    }

}
