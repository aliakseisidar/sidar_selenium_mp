package HardcorePOM.Tests;

import HardcorePOM.Pages.GCloudCalculatorHardcore;
import HardcorePOM.Pages.GCloudEstimateHardcore;
import HardcorePOM.Pages.GCloudHomeHardcore;
import HardcorePOM.Pages.YopmailHomeHardcore;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;


public class HardcorePOM {
    WebDriver driver;

    //variables for pages
    GCloudHomeHardcore objGCloudHome;
    GCloudCalculatorHardcore objGCloudCalculator;
    GCloudEstimateHardcore objGCloudEstimate;
    YopmailHomeHardcore objYopmailHome;

    //variables for filling calculator
    String MachineClass = "Regular";
    String MachineType = "n1-standard-8 (vCPUs: 8, RAM: 30GB)";
    String DataCenterLocation = "Frankfurt (europe-west3)";
    String LocalSSD = "2x375 GB";
    String CommittedUsage = "1 Year";
    String TypeOfProduct = "Compute Engine";
    String NumberOfInstances = "4";
    String OperatingSystem = "Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)";
    String Series = "N1";
    String GPUType = "NVIDIA Tesla V100";
    String NumberOfGPU = "1";

    String EmailAddress;
    String CostFromCalculator;
    String CostFromEmail;

    @BeforeTest
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://cloud.google.com/");
        objGCloudHome = new GCloudHomeHardcore(driver);
        objGCloudCalculator = new GCloudCalculatorHardcore(driver);
        objGCloudEstimate = new GCloudEstimateHardcore(driver);
        objYopmailHome = new YopmailHomeHardcore(driver);
    }

    @Test(description = "Calculating Costs using Google Cloud Pricing Calculator")
    public void comparingCostFromCalculatorAndEmail() {
        //open calculator and fill it
        objGCloudHome.openingCalculator();
        objGCloudCalculator.selectProduct(TypeOfProduct);
        objGCloudCalculator.fillingNumberOfInstances(NumberOfInstances);
        objGCloudCalculator.selectOperatingSystem(OperatingSystem);
        objGCloudCalculator.selectMachineClass(MachineClass);
        objGCloudCalculator.selectSeries(Series);
        objGCloudCalculator.selectMachineType(MachineType);
        objGCloudCalculator.addGPUs(GPUType, NumberOfGPU);
        objGCloudCalculator.selectLocalSSD(LocalSSD);
        objGCloudCalculator.selectDataCenterLocation(DataCenterLocation);
        objGCloudCalculator.selectCommittedUsage(CommittedUsage);
        objGCloudCalculator.clickAddToEstimate();

        //save Cost from calculator to variable
        CostFromCalculator = objGCloudEstimate.getTotalEstimatedCost();

        //opening Yopmail
        ((JavascriptExecutor) driver).executeScript("window.open('about:blank','_blank');"); //open new tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles()); //handling tabs
        driver.switchTo().window(tabs.get(1)); //switch to the new tab
        driver.get("https://yopmail.com/"); //open the Yopmail page

        //save Generated Email Address to variable
        EmailAddress = objYopmailHome.createAndReturnEmailAddress();

        //open, fill and send Estimation Email
        driver.switchTo().window(tabs.get(0)); //switch to the Google page
        objGCloudEstimate.clickEmailEstimate();
        objGCloudEstimate.enterEmailInForm(EmailAddress);
        objGCloudEstimate.clickSendEmail();

        //wait for Email and return Cost
        driver.switchTo().window(tabs.get(1)); //switch to the Yopmail page
        objYopmailHome.clickRefreshInboxButton();

        //Save Cost from email to variable
        CostFromEmail = objYopmailHome.getCostFromEmail();

        //Compare them
        Assert.assertEquals(CostFromEmail, CostFromCalculator, "Cost from Email is not equal to cost from Calculator");
    }

    @AfterTest
    public void browserQuit() {
        driver.quit();
        driver = null;
    }
}



//    script to generate random email - not used
//    private String generateEmail(){
//        Random randomGenerator = new Random();
//        int randomInt = randomGenerator.nextInt(1000);
//        return "AliakseiSidar" + randomInt;
//    }

