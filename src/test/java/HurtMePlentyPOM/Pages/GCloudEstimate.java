package HurtMePlentyPOM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GCloudEstimate {
    WebDriver driver;

    //variables with selectors
    By VMClassValue = By.xpath("//md-card-content[@id='resultBlock']//div[text()[contains(.,'VM class')]]");
    By InstanceTypeValue = By.xpath("//md-card-content[@id='resultBlock']//div[text()[contains(.,'Instance type')]]");
    By RegionValue = By.xpath("//md-card-content[@id='resultBlock']//div[text()[contains(.,'Region')]]");
    By LocalSSDValue = By.xpath("//md-card-content[@id='resultBlock']//div[text()[contains(.,'Local SSD')]]");
    By CommitmentTermValue = By.xpath("//md-card-content[@id='resultBlock']//div[text()[contains(.,'Commitment term')]]");
    By TotalEstimatedCost = By.xpath("//md-card-content[@id='resultBlock']//b[text()[contains(.,'Total Estimated Cost')]]");

    public GCloudEstimate(WebDriver driver) {
        this.driver = driver;
    }

    public String getVMClass() {
            switchingToCalculatorFrame();
             String value = driver.findElement(VMClassValue).getText();
            driver.switchTo().defaultContent();
        return value;
    }

    public String getInstanceType(){
            switchingToCalculatorFrame();
                //construction to get only 'Instance type' value
                String secondLine = driver.findElement(By.xpath("//md-card-content[@id='resultBlock']//div[text()[contains(.,'Instance type')]]/div")).getText();
                String value = driver.findElement(InstanceTypeValue).getText().replace(secondLine,"").trim();
            driver.switchTo().defaultContent();
        return value;
    }

    public String getRegion(){
            switchingToCalculatorFrame();
                String value = driver.findElement(RegionValue).getText();
            driver.switchTo().defaultContent();
        return value;
    }

    public String getLocalSSD(){
            switchingToCalculatorFrame();
                //construction to get only 'Instance type' value
                String secondLine = driver.findElement(By.xpath("//md-card-content[@id='resultBlock']//div[text()[contains(.,'Local SSD')]]/div")).getText();
                String value = driver.findElement(LocalSSDValue).getText().replace(secondLine,"").trim();
            driver.switchTo().defaultContent();
        return value;
    }

    public String getCommitmentTerm(){
            switchingToCalculatorFrame();
                String value = driver.findElement(CommitmentTermValue).getText();
            driver.switchTo().defaultContent();
        return value;
    }

    public String getTotalEstimatedCost(){
            switchingToCalculatorFrame();
                //construction to get only Cost
                String CostLine = driver.findElement(TotalEstimatedCost).getText();
                String value = CostLine.substring(CostLine.indexOf("USD ") + 4, CostLine.indexOf(" per"));
            driver.switchTo().defaultContent();
        return value;
    }

    //custom methods for switching
    private void switchingToCalculatorFrame() {
        driver.switchTo().frame(driver.findElement(By.xpath("//section[@id='gc-wrapper']//iframe")));
        driver.switchTo().frame("myFrame");
    }
}
