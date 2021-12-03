package HurtMePlentyPOM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class GCloudCalculator {
    WebDriver driver;

    //variables with selectors
    By NumberOfInstancesInput = By.xpath("//label[text()[contains(.,'Number of instances')]]/following-sibling::input");
    By OperatingSystemDropDown = By.xpath("//label[text()[contains(.,'Operating System')]]/following-sibling::md-select");
    By MachineClassDropDown = By.xpath("//label[text()[contains(.,'Machine Class')]]/following-sibling::md-select");
    By SeriesDropDown = By.xpath("//label[text()[contains(.,'Series')]]/following-sibling::md-select");
    By MachineTypeDropDown = By.xpath("//label[text()[contains(.,'Machine type')]]/following-sibling::md-select");
    By AddGPUsCheckBox = By.xpath("//form[@name='ComputeEngineForm']//div[text()[contains(.,'Add GPUs.')]]/..");
    By GPUTypeDropDown = By.xpath("//form[@name='ComputeEngineForm']//label[text()[contains(.,'GPU type')]]/following-sibling::md-select");
    By GPUNumberDropdown = By.xpath("//form[@name='ComputeEngineForm']//label[text()[contains(.,'Number of GPUs')]]/following-sibling::md-select");
    By LocalSSDDropDown = By.xpath("//form[@name='ComputeEngineForm']//label[text()[contains(.,'Local SSD')]]/following-sibling::md-select"); //need if
    By DataCenterLocationDropDown = By.xpath("//form[@name='ComputeEngineForm']//label[text()[contains(.,'Datacenter location')]]/following-sibling::md-select");
    By CommittedUsageDropDown = By.xpath("//form[@name='ComputeEngineForm']//label[text()[contains(.,'Committed usage')]]/following-sibling::md-select");
    By AddToEstimateButton = By.xpath("//form[@name='ComputeEngineForm']//button[text()[contains(.,'Add to Estimate')]]");

    public GCloudCalculator(WebDriver driver) {
        this.driver = driver;
    }

    public void selectProduct(String arg1){
        switchingToCalculatorFrame();
            waitForVisibility(By.xpath("//div[@title='"+ arg1 +"']"));
            driver.findElement(By.xpath("//div[@title='"+ arg1 +"']")).click();
            waitForVisibility(By.xpath("//form[@name='ComputeEngineForm']"));
        driver.switchTo().defaultContent();
    }

    public void fillingNumberOfInstances(String arg2){
        switchingToCalculatorFrame();
            driver.findElement(NumberOfInstancesInput).sendKeys(arg2);
        driver.switchTo().defaultContent();
    }

    public void selectOperatingSystem(String arg3){
        switchingToCalculatorFrame();
            //check that pre-populated value is not equal to desired value
            if (!Objects.equals(driver.findElement(OperatingSystemDropDown).getText(), arg3))
                {
                    driver.findElement(OperatingSystemDropDown).click();
                    String idOfOperatingSystemDropDown = driver.findElement(OperatingSystemDropDown).getAttribute("aria-owns");
                    waitForClickableAndClick(By.xpath("//div[@id='" + idOfOperatingSystemDropDown + "']//div[text()[contains(.,'" + arg3 + "')]]/.."));
                }
        driver.switchTo().defaultContent();
    }

    public void selectMachineClass(String arg4){
        switchingToCalculatorFrame();
            //check that pre-populated value is not equal to desired value
            if (!Objects.equals(driver.findElement(MachineClassDropDown).getText(), arg4))
                {
                    driver.findElement(MachineClassDropDown).click();
                    String idOfMachineClassDropDown = driver.findElement(MachineClassDropDown).getAttribute("aria-owns");
                    waitForClickableAndClick(By.xpath("//div[@id='" + idOfMachineClassDropDown + "']//div[text()[contains(.,'" + arg4 + "')]]/.."));
                }
        driver.switchTo().defaultContent();
    }

    public void selectSeries(String arg5){
        switchingToCalculatorFrame();
            //check that pre-populated value is not equal to desired value
            if (!Objects.equals(driver.findElement(SeriesDropDown).getText(), arg5))
                {
                    driver.findElement(SeriesDropDown).click();
                    String idOfSeriesDropDown = driver.findElement(SeriesDropDown).getAttribute("aria-owns");
                    waitForClickableAndClick(By.xpath("//div[@id='" + idOfSeriesDropDown + "']//div[text()[contains(.,'" + arg5 + "')]]"));
                }
        driver.switchTo().defaultContent();
    }

    public void selectMachineType(String arg6){
        switchingToCalculatorFrame();
            //check that pre-populated value is not equal to desired value
            if (!Objects.equals(driver.findElement(MachineTypeDropDown).getText(), arg6))
                {
                    driver.findElement(MachineTypeDropDown).click();
                    String idOfMachineTypeDropDown = driver.findElement(MachineTypeDropDown).getAttribute("aria-owns");
                    waitForClickableAndClick(By.xpath("//div[@id='" + idOfMachineTypeDropDown + "']//div[text()[contains(.,'" + arg6 + "')]]"));
                }
        driver.switchTo().defaultContent();
    }

    public void addGPUs(String arg7, String arg8){
        switchingToCalculatorFrame();
            //check that GPUs can be added
            if (Objects.equals(driver.findElement(AddGPUsCheckBox).getAttribute("aria-disabled"), "false"))
                {
                    driver.findElement(AddGPUsCheckBox).click();
                    waitForVisibility(By.xpath("//label[text()[contains(.,'GPU type')]]/../../../../.."));
                    //select GPU type
                    driver.findElement(GPUTypeDropDown).click();
                    String idOfGPUTypeDropDown = driver.findElement(GPUTypeDropDown).getAttribute("aria-owns");
                    waitForClickableAndClick(By.xpath("//div[@id='" + idOfGPUTypeDropDown + "']//div[text()[contains(.,'" + arg7 + "')]]"));
                    //select GPU number
                    driver.findElement(GPUNumberDropdown).click();
                    String idOfGPUNumberDropDown = driver.findElement(GPUNumberDropdown).getAttribute("aria-owns");
                    waitForClickableAndClick(By.xpath("//div[@id='" + idOfGPUNumberDropDown + "']//div[text()[contains(.,'" + arg8 + "')]]"));
                }
        driver.switchTo().defaultContent();
    }

    public void selectLocalSSD(String arg9){
        switchingToCalculatorFrame();
            //check that it is possible to select Local SSD
            if (driver.findElements(LocalSSDDropDown).size() != 0)
                {
                    driver.findElement(LocalSSDDropDown).click();
                    String idOfLocalSSDDropDown = driver.findElement(LocalSSDDropDown).getAttribute("aria-owns");
                    waitForClickableAndClick(By.xpath("//div[@id='" + idOfLocalSSDDropDown + "']//div[text()[contains(.,'" + arg9 + "')]]"));
                }
        driver.switchTo().defaultContent();
    }

    public void selectDataCenterLocation(String arg10){
        switchingToCalculatorFrame();
            //check that pre-populated value is not equal to desired value
            if (!Objects.equals(driver.findElement(DataCenterLocationDropDown).getText(), arg10))
            {
                driver.findElement(DataCenterLocationDropDown).click();
                String idOfDataCenterLocationDropDown = driver.findElement(DataCenterLocationDropDown).getAttribute("aria-owns");
                waitForClickableAndClick(By.xpath("//div[@id='" + idOfDataCenterLocationDropDown + "']//div[text()[contains(.,'" + arg10 + "')]]"));
            }
        driver.switchTo().defaultContent();
    }

    public void selectCommittedUsage(String arg11){
        switchingToCalculatorFrame();
            //check that pre-populated value is not equal to desired value
            if (!Objects.equals(driver.findElement(CommittedUsageDropDown).getText(), arg11))
            {
                driver.findElement(CommittedUsageDropDown).click();
                String idOfCommittedUsageDropDown = driver.findElement(CommittedUsageDropDown).getAttribute("aria-owns");
                waitForClickableAndClick(By.xpath("//div[@id='" + idOfCommittedUsageDropDown + "']//div[text()[contains(.,'" + arg11 + "')]]"));
            }
        driver.switchTo().defaultContent();
    }

    public void clickAddToEstimate(){
        switchingToCalculatorFrame();
        //check that Button is not disabled
            if(!Objects.equals(driver.findElement(AddToEstimateButton).getAttribute("disabled"), "disabled"))
                {
                driver.findElement(AddToEstimateButton).click();
                }
            waitForVisibility(By.xpath("//md-card-content[@ng-if='appCtrl.CartData.hasItems()']"));
        driver.switchTo().defaultContent();
    }



//custom methods for waiting and switching
    private void switchingToCalculatorFrame(){
        driver.switchTo().frame(driver.findElement(By.xpath("//section[@id='gc-wrapper']//iframe")));
        driver.switchTo().frame("myFrame");
    }

    private void waitForVisibility(By arg12){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(arg12));
    }

    private void waitForClickableAndClick(By arg13){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(arg13)).click();
    }
}
