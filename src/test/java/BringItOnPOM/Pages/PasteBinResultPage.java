package BringItOnPOM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PasteBinResultPage {
    WebDriver driver;

    //variables with selectors
    By SyntaxHighlightingValue = By.xpath("//div[@class='highlighted-code']//div[@class='left']/a");
    By PasteText = By.xpath("//div[@class='highlighted-code']//div[@class='source']");


    public PasteBinResultPage(WebDriver driver){
        this.driver = driver;
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public String getSyntaxHighlighting(){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(SyntaxHighlightingValue));
        return driver.findElement(SyntaxHighlightingValue).getText();
    }

    public String getPasteText(){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(PasteText));
        return driver.findElement(PasteText).getText();
    }

}
