package BringItOnPOM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
        return driver.findElement(SyntaxHighlightingValue).getText();
    }

    public String getPasteText(){
        return driver.findElement(PasteText).getText();
    }

}
