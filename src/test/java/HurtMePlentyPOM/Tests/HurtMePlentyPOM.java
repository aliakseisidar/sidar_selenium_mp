package HurtMePlentyPOM.Tests;

import HurtMePlentyPOM.Pages.GCloudCalculator;
import HurtMePlentyPOM.Pages.GCloudEstimate;
import HurtMePlentyPOM.Pages.GCloudHome;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.Locale;

public class HurtMePlentyPOM {
    WebDriver driver;
    SoftAssert softAssertion = new SoftAssert();

    //variables for pages
    GCloudHome objGCloudHome;
    GCloudCalculator objGCloudCalculator;
    GCloudEstimate objGCloudEstimate;

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

    @BeforeTest
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://cloud.google.com/");
        objGCloudHome = new GCloudHome(driver);
        objGCloudCalculator = new GCloudCalculator(driver);
        objGCloudEstimate = new GCloudEstimate(driver);
    }

    @Test (description = "Calculating Costs using Google Cloud Pricing Calculator")
    public void checkingEstimateForComputeEngine() {
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

        //Comparing data from Estimation with desire values using Soft Assertions
        softAssertion.assertEquals(objGCloudEstimate.getVMClass(), "VM class: " + MachineClass.toLowerCase(Locale.ROOT),
                "VM class does not equal to selected Machine Class");
        softAssertion.assertEquals(objGCloudEstimate.getInstanceType(), "Instance type: " + MachineType.split(" ")[0],
                "Instance type does not equal to selected Machine Type");
        softAssertion.assertEquals(objGCloudEstimate.getRegion(), "Region: " + DataCenterLocation.split(" ")[0],
                "Region does not equal to selected Datacenter Location");
        softAssertion.assertEquals(objGCloudEstimate.getLocalSSD(), "Local SSD: " + LocalSSD.split(" ")[0] + " GiB",
                "Local SSD does not equal to selected");
        softAssertion.assertEquals(objGCloudEstimate.getCommitmentTerm(), "Commitment term: " + CommittedUsage,
                "Commitment term does not equal to selected Committed Usage");
        softAssertion.assertEquals(objGCloudEstimate.getTotalEstimatedCost(), "1,082.77",
                "Total Estimated Cost does not equal to Cost calculated manually");
        softAssertion.assertAll();


    }

    @AfterTest
    public void browserQuit() {
        driver.quit();
        driver=null;
    }
}
