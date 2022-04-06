import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class googleCalc {
    private final WebDriver driver;
    private WebElement calcBtn_1, calcBtn_2, calcBtn_3, calcBtn_Umnojenie, calcBtn_Plus, calcBtn_Minus, calcBtn_Result,
            caclMemoryString, calcResultString, calcBlock;
    public String expectedMemoryText;

    private void findAndSetElements() {
        calcBtn_1   = driver.findElement(By.xpath("//div[@role='button' and text()='1']"));
        calcBtn_2   = driver.findElement(By.xpath("//div[@role='button' and text()='2']"));
        calcBtn_3   = driver.findElement(By.xpath("//div[@role='button' and text()='3']"));
        calcBtn_Umnojenie   = driver.findElement(By.xpath("//div[@role='button' and text()='×']"));
        calcBtn_Plus   = driver.findElement(By.xpath("//div[@role='button' and text()='+']"));
        calcBtn_Minus   = driver.findElement(By.xpath("//div[@role='button' and text()='−']"));
        calcBtn_Result   = driver.findElement(By.xpath("//div[@role='button' and text()='=']"));
        calcResultString   = driver.findElement(By.id("cwos"));
    }
    private void findAndSetCalcBlockElement() {
        calcBlock = driver.findElement(By.xpath("//div[@class='card-section']"));
    }
    private void findAndSetCalcMemoryStringElement() {
        caclMemoryString   = driver.findElement(By.xpath("//div[@class='card-section']//span[@style='right: 0px;']"));

    }


    public googleCalc(WebDriver driver) {
    this.driver = driver;
}

    public void typeFormula (String txt) {                  //Parsing formula string to minimize code in Main class
        findAndSetElements();
        expectedMemoryText="";
        for (int i = 0; i < txt.length(); i++) {            //For real (not test) version need here deep verifying and analyzing formula string
            checkSymbolAndTypeInCalc(txt.charAt(i), txt);
        }
        findAndSetCalcMemoryStringElement();
    }

    public void checkSymbolAndTypeInCalc (char symbol, String txt) {
        switch (symbol) {
            case '1' -> {
                calcBtn_1.click();
                expectedMemoryText += "1 ";
            }
            case '2' -> {
                calcBtn_2.click();
                expectedMemoryText += "2 ";
            }
            case '3' -> {
                calcBtn_3.click();
                expectedMemoryText += "3 ";
            }
            case '-' -> {
                calcBtn_Minus.click();
                expectedMemoryText += "- ";
            }
            case '+' -> {
                calcBtn_Plus.click();
                expectedMemoryText += "+ ";
            }
            case '*' -> {
                calcBtn_Umnojenie.click();
                expectedMemoryText += "× ";
            }
            case '×' -> {
                calcBtn_Umnojenie.click();
                expectedMemoryText += "× ";
            }
            case '=' -> {
                calcBtn_Result.click();
                expectedMemoryText += "=";
            }
            default -> Assert.fail( "Unknown symbol in formula string for google Calculator (" + txt + ")");
        }
    }

    public String getMemoryString () {
        return caclMemoryString.getText();
    }

    public String getResultString () {
        return calcResultString.getText();
    }

    public boolean isCaclBlockVisible () {
        findAndSetCalcMemoryStringElement();
        return calcBlock.isDisplayed();
    }


}

