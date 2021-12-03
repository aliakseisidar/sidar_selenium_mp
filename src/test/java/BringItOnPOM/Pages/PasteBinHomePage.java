package BringItOnPOM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PasteBinHomePage {
    WebDriver driver;
    Actions actions;

    //variables with selectors
    By PasteTextArea = By.id("postform-text");
    By OpenSyntaxHighlightingDropDown = By.xpath("//label[text()='Syntax Highlighting:']/..//span[@class='select2-selection__arrow']");
    By ElementMoveTo = By.xpath("//div[@class='notice -post-form']");
    By SyntaxHighlightingDropDown = By.xpath("//ul[@id='select2-postform-format-results']");
    By OpenPasteExpirationDropDown = By.xpath("//label[text()='Paste Expiration:']/..//span[@class='select2-selection__arrow']");
    By PasteExpirationDropDown = By.xpath("//ul[@id='select2-postform-expiration-results']");
    By PasteNameInput = By.id("postform-name");
    By CreateButton = By.xpath("//button[text()='Create New Paste']");

    public PasteBinHomePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public void fillingPasteTextArea(String arg1) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(PasteTextArea));
        driver.findElement(PasteTextArea).sendKeys(arg1);
    }

    public void selectSyntaxHighlighting(String arg2){
        //move to different element,
        //because SyntaxHighlightingDropDown is overlapped by banner
        WebElement moving = driver.findElement(ElementMoveTo);
        actions.moveToElement(moving);
        actions.perform();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(OpenSyntaxHighlightingDropDown));

        driver.findElement(OpenSyntaxHighlightingDropDown).click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(SyntaxHighlightingDropDown));

        driver.findElement(By.xpath("//li[text()='" + arg2 + "']")).click();
    }

    public void selectPasteExpiration(String arg3){
        //move to different element,
        //because SyntaxHighlightingDropDown is overlapped by banner
        WebElement moving = driver.findElement(ElementMoveTo);
        actions.moveToElement(moving);
        actions.perform();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(OpenPasteExpirationDropDown));

        driver.findElement(OpenPasteExpirationDropDown).click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(PasteExpirationDropDown));

        driver.findElement(By.xpath("//li[text()='" + arg3 + "']")).click();
    }

    public void fillingPasteName(String arg4){
        driver.findElement(PasteNameInput).sendKeys(arg4);
    }

    public void clickOnCreateButton() {
        driver.findElement(CreateButton).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='post-view']")));
    }
}
