package HardcorePOM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YopmailHomeHardcore {
    WebDriver driver;

    //variables for Home page
        By MainOfHomePage = By.xpath("//main");
        By RandomEmailButton = By.xpath("//a[@href='email-generator']");
    //variables for Generate Email page
        By GeneratedEmailArea = By.id("egen");
        By CheckInboxButton = By.xpath("//button[@onclick='egengo();']");
    //variables for Inbox Page
        By InboxArea = By.xpath("//div[@class='wminbox']");
        By RefreshInboxButton = By.id("refresh");
        By CostFromEmail = By.xpath("//div[@id='mail']//h2");

    public YopmailHomeHardcore(WebDriver driver){
        this.driver = driver;
    }

    public String createAndReturnEmailAddress(){
            waitForVisibility(MainOfHomePage);
            driver.findElement(RandomEmailButton).click();
            waitForVisibility(GeneratedEmailArea);
            String GeneratedEmail = driver.findElement(GeneratedEmailArea).getText();
            driver.findElement(CheckInboxButton).click();
            waitForVisibility(InboxArea);
        return GeneratedEmail;
    }

    public void clickRefreshInboxButton(){
        //cycle clicking on Refresh button until mail is received
        long startTime = System.currentTimeMillis();
        while (driver.switchTo().frame("ifinbox").findElements(By.xpath("//div[@class='m']")).size() == 0
                && (System.currentTimeMillis()-startTime)<10000) //break loop after 10sec., if email is not received
            {
                driver.switchTo().defaultContent();
                driver.findElement(RefreshInboxButton).click();
            }
        driver.switchTo().defaultContent();
    }

    public String getCostFromEmail(){
            driver.switchTo().frame("ifmail");
                String CostLine = driver.findElement(CostFromEmail).getText();
                String value = CostLine.substring(CostLine.indexOf("USD ") + 4);
            driver.switchTo().defaultContent();
        return value;
    }

    //custom methods for waiting and switching
    private void waitForVisibility(By arg1){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(arg1));
    }
}
