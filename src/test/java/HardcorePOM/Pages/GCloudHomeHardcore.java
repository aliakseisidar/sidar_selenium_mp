package HardcorePOM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GCloudHomeHardcore {
    WebDriver driver;

    //variables with selectors
    By OpenPricingDropDown = By.xpath("//a[text()[contains(.,'Pricing')]]");
    By PricingDropDown = By.xpath("//a[text()[contains(.,'Pricing')]]/..//div[@class='devsite-tabs-dropdown-content']");
    By PricingCalculatorButton = By.xpath("//div[text()[contains(.,'Pricing calculator')]]/..");

    public GCloudHomeHardcore(WebDriver driver) {
        this.driver = driver;
    }

    public void openingCalculator(){
        waitForClickable(OpenPricingDropDown);
        driver.findElement(OpenPricingDropDown).click();
        waitForVisibility(PricingDropDown);
        driver.findElement(PricingCalculatorButton).click();
        waitForVisibility(By.id("gc-wrapper"));
    }


    //custom methods for waitings
    private void waitForVisibility(By arg1){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(arg1));
    }

    private void waitForClickable(By arg2){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(arg2));
    }
}
