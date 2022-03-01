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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class US_11_Access_Profile {
    //start
    private final String[] usernames = {
            ConfigurationReader.getProperty("hr1UserName"),
            ConfigurationReader.getProperty("marketingUser1"),
            ConfigurationReader.getProperty("helpDeskUser1")
    };
    private final String password = ConfigurationReader.getProperty("password");
    public  WebDriver driver;

    @BeforeMethod
    public  void setup(){
    }


    @Test
    public void myProfileTabVerification(){

        for (int l=0;l<usernames.length;l++){

            driver  = WebDriverFactory.getDriver("chrome");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get(ConfigurationReader.getProperty("env"));
            CRM_Utilities.crm_login(driver, usernames[l],password);

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
            driver.close();

        }



    }

}
