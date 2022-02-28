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
import java.util.concurrent.TimeUnit;


    public class AppreciationTest_Vu {

        public WebDriver driver;

        @BeforeMethod
        public void setupMethod(){
            String browserType = ConfigurationReader.getProperty("browser");
            driver = WebDriverFactory.getDriver(browserType);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        @AfterMethod
        public void teardownMethod() throws InterruptedException {
            Thread.sleep(5000);
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
        public void sendMessageWithContentOnAppreciation_Test(String username, String password) throws InterruptedException {
            //Step 1: users go to homepage and login
            String envType = ConfigurationReader.getProperty("env");
            driver.get(envType);
            CRM_Utilities.crm_login(driver, username, password);

            //Step 2: click on More tab
            WebElement moreTap = driver.findElement(By.xpath("//*[@id=\"feed-add-post-form-link-text\"]"));
            Assert.assertTrue(moreTap.isDisplayed(), "More tab is Not displayed on modules");
            moreTap.click();

            // Step 3: select Appreciation option
            // WebElement appreciationOption = driver.findElement(By.xpath("//span[@class='menu-popup-item-text'][.='Appreciation']"));
            WebElement appreciationOption = driver.findElement(By.xpath("//*[@id=\"popup-window-content-menu-popup-feed-add-post-form-popup\"]/div/div/span[2]/span[2]"));
            appreciationOption.click();
            WebElement selectedAppreciation = driver.findElement(By.xpath("//*[@id=\"feed-add-post-form-link-text\"]"));
            Assert.assertTrue(selectedAppreciation.isDisplayed(), "Appreciation tab is not displayed after clicked");


            // Step 4: write a message on text box
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='bx-editor-iframe']")));
            WebElement iframeMessageBox = driver.findElement(By.xpath("/html/body"));
            //  iframeMessageBox.sendKeys("1.hi, this is Anna\n" +
            //     "2.I would like to say thank you to my colleagues for always helping me at work.");
            iframeMessageBox.sendKeys("thank you all");

            driver.switchTo().defaultContent();

            WebElement sendButton = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
            sendButton.click();

            // Step 5: check on visual message posted on activity stream


        }

        @Test(dataProvider = "logInCredential")
        public void sendMessageWithoutContentOnAppreciation_Test(String username, String password){
            //Step 1: users go to homepage and login
            String browserType = ConfigurationReader.getProperty("browser");
            driver = WebDriverFactory.getDriver(browserType);
            CRM_Utilities.crm_login(driver, username, password);

            //Step 2: click on More tab
            WebElement moreTap = driver.findElement(By.xpath("//*[@id=\"feed-add-post-form-link-text\"]"));
            Assert.assertTrue(moreTap.isDisplayed(), "More tab is Not displayed on modules");
            moreTap.click();

            // Step 3: select Appreciation option
            // WebElement appreciationOption = driver.findElement(By.xpath("//span[@class='menu-popup-item-text'][.='Appreciation']"));
            WebElement appreciationOption = driver.findElement(By.xpath("//*[@id=\"popup-window-content-menu-popup-feed-add-post-form-popup\"]/div/div/span[2]/span[2]"));
            appreciationOption.click();
            WebElement selectedAppreciation = driver.findElement(By.xpath("//*[@id=\"feed-add-post-form-link-text\"]"));
            Assert.assertTrue(selectedAppreciation.isDisplayed(), "Appreciation tab is not displayed after clicked");

            // Step 4: click on Send button
            WebElement sendButton = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
            sendButton.click();

            // Step 5: verify the warning message
            WebElement warningMessage = driver.findElement(By.xpath("//span[@class='feed-add-info-text']"));
            String actualWarningMessageText = warningMessage.getText();
            String expectedWarningMessageText = "The message title is not specified";

            Assert.assertEquals(actualWarningMessageText,expectedWarningMessageText, "Warning message is not matched");







        }



    }


