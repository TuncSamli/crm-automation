package nextbacecrm.tests.CY29;

import com.cydeo.utilities.BrowserUtils;
import com.cydeo.utilities.CRM_Utilities;
import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class US_7_CheckPoll extends ConfigurationReader {
    WebDriver driver;
    @BeforeMethod
    public void setupMethod() {
      
        driver = WebDriverFactory.getDriver(getProperty("browser"));
        driver.manage().window().maximize();
        driver.get(getProperty("env"));
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
    }
    @AfterMethod
    public void teardown() {
        driver.close();
    }

    @DataProvider(name = "US-12-Credentials")
    public Object[][] credentialsAndMessages() {
     
        return new Object[][]{{getProperty("username"), getProperty("password")},
                {getProperty("username2"), getProperty("password")},
             //  {getProperty("username3"), getProperty("password")},
//                    {getProperty("username4"), getProperty("password")},
//                    {getProperty("username5"), getProperty("password")},
//                    {getProperty("username6"), getProperty("password")},
//                    {getProperty("username7"), getProperty("password")},
//                    {getProperty("username8"), getProperty("password")},
//                    {getProperty("username9"), getProperty("password")}
        };
    }
    @Test(dataProvider = "US-12-Credentials")
    public void pollChecker(String username, String password) {
        CRM_Utilities.crm_login(driver, username, password);
        driver.findElement(By.xpath("//*[@id=\"LIVEFEED_search\"]")).click(); //searchbox
        //type dropdown
        BrowserUtils.sleep(1);
        driver.findElement(By.xpath("//div[@class='main-ui-control main-ui-multi-select']")).click();
        //poll checkbox
        BrowserUtils.sleep(1);
        driver.findElement(By.xpath("//div[.='Polls']")).click();
        BrowserUtils.sleep(1);
        //click search
        driver.findElement(By.xpath("//*[@id=\"popup-window-content-LIVEFEED_search_container\"]/div/div/div[3]/div[2]/div/button")).click();
        BrowserUtils.sleep(1);
        if(driver.findElement(By.xpath("//button[.= 'Vote again'] ")).isDisplayed()){//check if vote again is displayed then click it for voting again
            driver.findElement(By.xpath("//button[.= 'Vote again'] ")).click();
        }
        driver.findElement(By.xpath("//span[@class= 'bx-vote-block-inp-substitute']")).click();
        BrowserUtils.sleep(1);
        driver.findElement(By.xpath("//button[contains(@class,'btn-primary')]")).click();
        BrowserUtils.sleep(1);
        assertTrue(driver.findElement(By.xpath("//button[.= 'Vote again'] ")).isDisplayed());
        BrowserUtils.sleep(1);
    }
}
