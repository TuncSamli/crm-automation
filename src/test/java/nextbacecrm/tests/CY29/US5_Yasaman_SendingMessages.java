package nextbacecrm.tests.CY29;

import nextbacecrm.utilities.BrowserUtils;
import nextbacecrm.utilities.CRM_Utilities;
import nextbacecrm.utilities.ConfigurationReader;
import nextbacecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class US5_Yasaman_SendingMessages {

    public WebDriver driver;

    @BeforeMethod
    public void setUpMethod(){

        String browserType = ConfigurationReader.getProperty("browser");

        driver = WebDriverFactory.getDriver(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.get(ConfigurationReader.getProperty("env"));

        CRM_Utilities.crm_login(driver);

    }

    @Test
    public void sendingMessagesTest(){

        //Users are on the homepage and click the message tab

        driver.findElement(By.id("feed-add-post-form-tab-message")).click();

        //Users write test message
        driver.switchTo().frame(driver.findElement(By.cssSelector(".bx-editor-iframe")));
        WebElement msgFrame=driver.findElement(By.tagName("body"));
        msgFrame.sendKeys("Good luck!");

        //Users click the SEND button
        driver.switchTo().defaultContent();
        driver.findElement(By.id("blog-submit-button-save")).click();

        //Verify the message is displayed on the feed
        WebElement feed= driver.findElement(By.xpath("//div[starts-with(@id,'blog_post_body')]"));
        String expectedText="Good luck!";
        String actualText=feed.getText();
        Assert.assertEquals(actualText,expectedText);

    }

    @Test
    public void WithoutContentTest(){

        //Users are on the homepage and click the message tab

        driver.findElement(By.id("feed-add-post-form-tab-message")).click();

        //Users click the SEND button
        driver.findElement(By.id("blog-submit-button-save")).click();

        //Verify “The message title is not specified” warning message is displayed on the page
        WebElement warningMsg=driver.findElement(By.xpath("//span[.='The message title is not specified']"));
        String expectedText="The message title is not specified";
        String actualText=warningMsg.getText();
        Assert.assertEquals(actualText,expectedText);

    }

    @AfterMethod
    public void tearDownMethod() {
        driver.quit();
    }
}
