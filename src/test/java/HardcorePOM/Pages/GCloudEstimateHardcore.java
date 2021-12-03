package HardcorePOM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Objects;

public class GCloudEstimateHardcore {
    WebDriver driver;

    //variables with selectors
    By TotalEstimatedCost = By.xpath("//md-card-content[@id='resultBlock']//b[text()[contains(.,'Total Estimated Cost')]]");
    By EmailEstimateButton = By.xpath("//button[text()[contains(.,'Email Estimate')]]");
    By EmailForm = By.xpath("//form[@name='emailForm']");
    By EmailInputInForm = By.xpath("//form[@name='emailForm']//label[text()[contains(.,'Email')]]/following-sibling::input");
    By SendEmailButton = By.xpath("//form[@name='emailForm']//button[text()[contains(.,'Send Email')]]");

    public GCloudEstimateHardcore(WebDriver driver) {
        this.driver = driver;
    }

    public String getTotalEstimatedCost(){
            switchingToCalculatorFrame();
                //construction to get only Cost
                String CostLine = driver.findElement(TotalEstimatedCost).getText();
                String value = CostLine.substring(CostLine.indexOf("USD ") + 4, CostLine.indexOf(" per"));
            driver.switchTo().defaultContent();
        return value;
    }

    public void clickEmailEstimate(){
        switchingToCalculatorFrame();
            driver.findElement(EmailEstimateButton).click();
            waitForVisibility(EmailForm);
        driver.switchTo().defaultContent();
    }

    public void enterEmailInForm(String arg1){
        switchingToCalculatorFrame();
            driver.findElement(EmailInputInForm).sendKeys(arg1);
        driver.switchTo().defaultContent();
    }

    public void clickSendEmail(){
        switchingToCalculatorFrame();
            //check that Button is not disabled
            if (!Objects.equals(driver.findElement(SendEmailButton).getAttribute("disabled"), "disabled"))
                {
                    driver.findElement(SendEmailButton).click();
                }
        driver.switchTo().defaultContent();
    }

    //custom methods for waiting and switching
    private void switchingToCalculatorFrame() {
        driver.switchTo().frame(driver.findElement(By.xpath("//section[@id='gc-wrapper']//iframe")));
        driver.switchTo().frame("myFrame");
    }

    private void waitForVisibility(By arg12){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(arg12));
    }
}
