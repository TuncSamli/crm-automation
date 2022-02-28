package nextbacecrm.tests.CY29;

import nextbacecrm.utilities.BrowserUtils;
import nextbacecrm.utilities.CRM_Utilities;
import nextbacecrm.utilities.ConfigurationReader;
import nextbacecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class US_12_MakeAnnouncement extends ConfigurationReader {
    int test =431;
    WebDriver driver;
    @BeforeMethod
    public void setupMethod(){
        driver = WebDriverFactory.getDriver(getProperty("browser"));
        driver.manage().window().maximize();
        driver.get(getProperty("env"));
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
    }
    @AfterMethod
    public void teardown() {
        driver.close();
    }
    @DataProvider(name = "US-12-CredentialsAndMessages")
    public Object[][] credentialsAndMessages(Method CRM){
        if (CRM.getName().equals("Step3SendAnnWithMessage")){
            return new Object[][]{{getProperty("username"),getProperty("password"),getProperty("text") + test++},
                        {getProperty("username2"), getProperty("password"), getProperty("text") + test++},
                      //  {getProperty("username3"),getProperty("password"), getProperty("text") + test++},
//                        {getProperty("username4"), getProperty("password"),getProperty("text")+ test++},
//                        {getProperty("username5"), getProperty("password"),getProperty("text")+ test++},
//                        {getProperty("username6"), getProperty("password"),getProperty("text")+ test++},
//                        {getProperty("username7"),getProperty("password"),getProperty("text")+ test++},
//                        {getProperty("username8"),getProperty("password"),getProperty("text")+ test++},
//                        {getProperty("username9"),getProperty("password"),getProperty("text")+ test++}
                    };}else
            return new Object[][]{{getProperty("username"),getProperty("password")},
                    {getProperty("username2"), getProperty("password")},
                   // {getProperty("username3"), getProperty("password")},
                    //{getProperty("username4"), getProperty("password")},
//                    {getProperty("username5"), getProperty("password)},
//                    {getProperty("username6"), getProperty("password")},
//                    {getProperty("username7"), getProperty("password")},
//                    {getProperty("username8"), getProperty("password")},
//                    {getProperty("username9"), getProperty("password")}
            };
    }

    @Test(dataProvider = "US-12-CredentialsAndMessages", priority = 1)
    public void Step1Login(String username, String password) {
        WebElement usernameSlot = driver.findElement(By.xpath("//input[@name='USER_LOGIN']"));//login space
        usernameSlot.clear();//clear space
        usernameSlot.sendKeys(username);//write username
        WebElement passwordSlot = driver.findElement(By.xpath("//input[@type='password']"));//password space
        passwordSlot.clear();//clear space
        passwordSlot.sendKeys(password);//write password
        WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));//submit button
        loginButton.click();
        assertEquals(driver.getTitle(), "Portal", "Titles does not match!");//verification
        BrowserUtils.sleep(2);
    }
    @Test(dataProvider = "US-12-CredentialsAndMessages",priority = 2)
    public void Step2CheckDropdownAndAnnWindow(String username, String password) {
        CRM_Utilities.crm_login(driver, username, password);//login process
        assertTrue(driver.findElement(By.xpath("//*[@id=\"feed-add-post-form-link-text\"]")).isDisplayed(), "More button is not visible");//more button visibility
        driver.findElement(By.xpath("//*[@id=\"feed-add-post-form-link-text\"]")).click();
        BrowserUtils.sleep(2);
        assertTrue(driver.findElement(By.xpath("//*[@id=\"popup-window-content-menu-popup-feed-add-post-form-popup\"]")).isDisplayed(), "Announcement button is not visible");//announcement button visibility
        driver.findElement(By.xpath("//*[@id=\"popup-window-content-menu-popup-feed-add-post-form-popup\"]")).click();
        assertTrue(driver.findElement(By.xpath("/html/body")).isDisplayed());//announcement writing space visibility
        BrowserUtils.sleep(2);
    }
    @Test(dataProvider = "US-12-CredentialsAndMessages",priority = 3)
    public void Step3SendAnnWithMessage(String username, String password, String message) {
        CRM_Utilities.crm_login(driver, username, password);//login process
        driver.findElement(By.xpath("//*[@id=\"feed-add-post-form-link-text\"]")).click();
        BrowserUtils.sleep(2);
        driver.findElement(By.xpath("//*[@id=\"popup-window-content-menu-popup-feed-add-post-form-popup\"]")).click();
        BrowserUtils.sleep(2);
        driver.switchTo().frame(0);//change of frame to announcement writing frame
        driver.findElement(By.xpath("/html/body")).sendKeys(message);//sending announcement message to frame
        BrowserUtils.sleep(2);
        driver.switchTo().parentFrame();//switching back to parent frame
        WebElement sendBtn = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        sendBtn.click();//send
        BrowserUtils.sleep(2);
        driver.findElement(By.xpath("//*[@id=\"LIVEFEED_search\"]")).click();
        BrowserUtils.sleep(2);
        driver.findElement(By.xpath("//span[@class='ui-btn ui-btn-light-border main-ui-filter-field-button main-ui-filter-reset']")).click();
        BrowserUtils.sleep(2);
        driver.findElement(By.xpath("//*[@id=\"LIVEFEED_search\"]")).sendKeys(message, Keys.ENTER);//searching for announcement message
        BrowserUtils.sleep(2);
        assertTrue(driver.findElement(By.xpath("//div[text()='" + message + "']")).isDisplayed());//checking if that message has appeared
        BrowserUtils.sleep(2);
    }
        @Test(dataProvider = "US-12-CredentialsAndMessages",priority = 4)
        public void step4SendAnnWithoutMessage(String username,String password)  {
        CRM_Utilities.crm_login(driver,username,password);
            driver.findElement(By.xpath("//*[@id=\"feed-add-post-form-link-text\"]")).click();
            BrowserUtils.sleep(1);
            driver.findElement(By.xpath("//*[@id=\"popup-window-content-menu-popup-feed-add-post-form-popup\"]")).click();
            BrowserUtils.sleep(1);
            driver.findElement(By.xpath("//button[@id='blog-submit-button-save']")).click();
            BrowserUtils.sleep(1);
            assertTrue(driver.findElement(By.xpath("//div/span[text()='The message title is not specified']")).isDisplayed());
            BrowserUtils.sleep(1);
        }
}






