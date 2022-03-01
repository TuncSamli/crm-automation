package nextbacecrm.tests.CY29;

import nextbacecrm.utilities.BrowserUtils;
import nextbacecrm.utilities.CRM_Utilities;
import nextbacecrm.utilities.ConfigurationReader;
import nextbacecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class US_11_Access_Profile {

    public  WebDriver driver;

    @BeforeMethod
    public void setupMethod(){
        String browserType = ConfigurationReader.getProperty("browser");
        driver = WebDriverFactory.getDriver(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.getProperty("env"));
    }

    @AfterMethod
    public void teardownMethod()  {
        BrowserUtils.sleep(1);
        driver.quit();
    }

    @DataProvider(name = "logInCredential")
    public Object[][] provideData(){
        return new Object[][]{
                {"hr85@cydeo.com","UserUser"},
                {"hr86@cydeo.com","UserUser"},
                {"hr87@cydeo.com","UserUser"},
                {"helpdesk85@cydeo.com","UserUser"},
                {"helpdesk86@cydeo.com","UserUser"},
                {"helpdesk87@cydeo.com","UserUser"},
                {"marketing85@cydeo.com","UserUser"},
                {"marketing86@cydeo.com","UserUser"},
                {"marketing87@cydeo.com","UserUser"}
        };
    }


    @Test (dataProvider = "logInCredential")
    public void myProfileTabVerification(String username, String password){

            CRM_Utilities.crm_login(driver, username,password);

            // finding 'My Profile' tab
            WebElement userNameProfileTab = driver.findElement(By.id("user-name"));
            userNameProfileTab.click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            //finding 'My Profile' option
            WebElement myProfileOptionLink = driver.findElement(By.xpath("//span[.='My Profile']"));
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            BrowserUtils.sleep(1);
            myProfileOptionLink.click();
            BrowserUtils.sleep(1);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            //locating 5 tabs in the List
            List<WebElement> profileMenuTabs = driver.findElements(By.xpath("//div[@id='profile-menu-filter']/a"));

            //Storing 5 tab's names in the List as an Actual Data to be Verified
            List<String> actualText = new ArrayList<>();
            for (int k=0; k<profileMenuTabs.size(); k++){
                actualText.add(profileMenuTabs.get(k).getText());
            }
            //Creating expected data
            List<String> expectedText = new ArrayList<>();
            expectedText.addAll(Arrays.asList("General","Drive","Tasks","Calendar","Conversations"));

            //Assertion of displayed tabs count equal to 5
            Assert.assertEquals(actualText.size(), 5);

            //Assertion of expected and actual data
            Assert.assertEquals(expectedText,actualText);
    }

}
