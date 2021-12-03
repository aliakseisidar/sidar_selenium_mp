package BringItOnPOM.Tests;

import BringItOnPOM.Pages.PasteBinHomePage;
import BringItOnPOM.Pages.PasteBinResultPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

public class BringItOnPOM {
    WebDriver driver;
    Actions actions;

    //variables for pages
    PasteBinHomePage objPasteBinHomePage;
    PasteBinResultPage objPasteBinResultPage;

    //variables for filling PasteBin
    String pasteNameText = "how to gain dominance among developers";
    String textToEnter = """
            git config --global user.name  "New Sheriff in Town"
            git reset $(git commit-tree HEAD^{tree} -m "Legacy code")
            git push origin master --force""";
    String syntaxHighlighting = "Bash";

    @BeforeTest
    public void browserSetup() {
        driver = new ChromeDriver();
        actions = new Actions(driver);
        driver.get("https://pastebin.com");
        objPasteBinHomePage = new PasteBinHomePage(driver);
            //use ESCAPE to stop page downloading,
            //because the page has a lot of banners
            // and sometimes the page can load permanently
            actions.sendKeys(Keys.ESCAPE);
        objPasteBinResultPage = new PasteBinResultPage(driver);
    }

    @Test (priority = 3, description = "Check that Page Title correspond to Paste Name")
    public void PasteNameIsInTitle() {
        objPasteBinHomePage.fillingPasteTextArea(textToEnter);
        objPasteBinHomePage.selectSyntaxHighlighting(syntaxHighlighting);
        objPasteBinHomePage.selectPasteExpiration("10 Minutes");
        objPasteBinHomePage.fillingPasteName(pasteNameText);
        objPasteBinHomePage.clickOnCreateButton();
        Assert.assertEquals(objPasteBinResultPage.getPageTitle(), pasteNameText + " - Pastebin.com", "Page Title is not correct");
    }

    @Test (priority = 3, description = "Check that Syntax Highlighting correspond to selected")
    public void SyntaxHighlightingIsCorrect() {
        Assert.assertEquals(objPasteBinResultPage.getSyntaxHighlighting(), syntaxHighlighting, "Syntax Highlighting is not correct");
    }

    @Test (priority = 3, description = "Check that Paste Text correspond to entered")
    public void PasteTextIsCorrect(){
        Assert.assertEquals(objPasteBinResultPage.getPasteText(), textToEnter, "Paste Text is not correct");
    }

    @AfterTest
    public void browserQuit() {
        driver.quit();
        driver=null;
    }
}
