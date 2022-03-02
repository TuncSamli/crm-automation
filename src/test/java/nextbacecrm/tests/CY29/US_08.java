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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class US_08 {

    public WebDriver driver;


    @BeforeMethod
    public void setUp(){

        String browserType = ConfigurationReader.getProperty("browser");

        driver = WebDriverFactory.getDriver(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);



        driver.get(ConfigurationReader.getProperty("env"));

        CRM_Utilities.crm_login(driver,ConfigurationReader.getProperty("hlp86"),ConfigurationReader.getProperty("password"));
    }




    @Test
    public void loginFunction() {


        //Chat and Calls
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement chatAndCallsButton = driver.findElement(By.xpath("//a[@title='Chat and Calls']"));
        chatAndCallsButton.click();


        List<WebElement> chatAndCallModuleButtons = driver.findElements(By.xpath("//div[@class='bx-desktop-appearance-tab']/div[contains(@id,'bx-desktop-tab')]"));





        List<String> expectedTitleNames = new ArrayList<>(Arrays.asList("Message(s) ", "Notifications", "Settings", "Activity Stream "));

        List<String> actualTitleNames = new ArrayList<>();

        for (WebElement each : chatAndCallModuleButtons) {
            actualTitleNames.add(each.getAttribute("title"));

        }
        Assert.assertEquals(actualTitleNames, expectedTitleNames);



        WebElement messageOption = driver.findElement(By.xpath("//*[@id=\"bx-desktop-tab-im\"]/div"));
        Assert.assertTrue(messageOption.isDisplayed(), "The message option is not displayed on webpage");


    }

    @AfterMethod
    public void tearDown() {
        BrowserUtils.sleep(3);
        driver.quit();
    }

}
