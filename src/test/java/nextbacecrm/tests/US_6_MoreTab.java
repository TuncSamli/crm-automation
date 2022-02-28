package nextbacecrm.tests;



import nextbacecrm.utilities.CRM_Utilities;
import nextbacecrm.utilities.ConfigurationReader;
import nextbacecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class US_6_MoreTab {
    public WebDriver driver;

    @BeforeMethod
    public void setupMethod() {
        String browserType = ConfigurationReader.getProperty("browser");
        driver = WebDriverFactory.getDriver(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @AfterMethod
    public void teardownMethod() {
        driver.quit();
    }

    @DataProvider(name = "US-6_Credentials")
    public Object[][] credentials() {
        return new Object[][] {{"hr85@cydeo.com", "UserUser"},
                {"hr86@cydeo.com", "UserUser"},
                {"hr87@cydeo.com", "UserUser"},
                {"helpdesk85@cydeo.com", "UserUser"},
                {"helpdesk86@cydeo.com", "UserUser"},
                {"helpdesk87@cydeo.com", "UserUser"},
                {"marketing85@cydeo.com", "UserUser"},
                {"marketing86@cydeo.com", "UserUser"},
                {"marketing87@cydeo.com", "UserUser"}};
    }


    @Test(dataProvider = "US-6_Credentials")
    public void moreTab(String usernames, String passwords) {

        //1 step: go to login page
        driver.get(ConfigurationReader.getProperty("env"));

        //2: find web element User and give keys
        //3: find web element password and give keys
        //4: find web element login button

        CRM_Utilities.crm_login(driver, usernames, passwords);

        //5: Find more tab and click
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebElement moreButton = driver.findElement(By.xpath("//span[@id='feed-add-post-form-link-text']"));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        moreButton.click();

        List<WebElement> fourButtons = driver.findElements(By.xpath("//div[@class='menu-popup-items']//span//span[@class='menu-popup-item-text']"));
        for (WebElement fourButton : fourButtons) {
            Assert.assertTrue(fourButton.isDisplayed());
            System.out.println("fourButton.getText() = " + fourButton.getText());
        }

    }
}
