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

public class US_09 {

    public WebDriver driver;


    @BeforeMethod
    public void setUp(){

        String browserType = ConfigurationReader.getProperty("browser");

        driver = WebDriverFactory.getDriver(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);



        driver.get(ConfigurationReader.getProperty("env"));

        CRM_Utilities.crm_login(driver,ConfigurationReader.getProperty("hr87"),ConfigurationReader.getProperty("password"));
    }

    @Test
    public void leftSideActivityStreamTest(){
        WebElement actStr = driver.findElement(By.xpath("//span[contains(.,'Activity Stream')]"));
        actStr.click();
        String expectedTitle="Portal";
        String actualTitle=driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle,"Activity Stream Title did not appear correctly!");

    }

    @Test
    public void leftSidebarTasksTest(){
        WebElement tasks = driver.findElement(By.xpath("//*[@id=\"bx_left_menu_menu_tasks\"]/a"));
        tasks.click();
        String expectedTitle="Site map"; // coming from requirement
        String actualTitle=driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle,"Tasks title did not appear correctly!");

    }

    @AfterMethod
    public void tearDown() {
        BrowserUtils.sleep(3);
        driver.quit();
    }

}
