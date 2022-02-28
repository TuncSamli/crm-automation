package nextbacecrm.tests.CY29;

import nextbacecrm.utilities.CRM_Utilities;
import nextbacecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class US_10_Task_Create_Kati {

    public WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(" https://login2.nextbasecrm.com");

    }

    @Test
    public void hr85Login() throws InterruptedException {
        //1 Login
        CRM_Utilities.crm_login(driver,"hr86@cydeo.com","UserUser");
        //locate Task button and click
        WebElement taskBtn = driver.findElement(By.xpath("//span[@id='feed-add-post-form-tab-tasks']"));

        Thread.sleep(1000);
        taskBtn.click();


          /*  WebElement fieldToWrite =driver.findElement(By.xpath("//div[@id='bx-html-editor-bitrix_tasks_task_1']"));
            fieldToWrite.sendKeys("Something");
            Thread.sleep(2000);*/

        WebElement sendBtn = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        Thread.sleep(1000);
        sendBtn.click();


        WebElement warningSign = driver.findElement(By.xpath("//div[text()='The task name is not specified.'] "));
        System.out.println("warningSign.isDisplayed() = " + warningSign.isDisplayed());
        Thread.sleep(1000);



    }

    @Test

    public void helpdesk85Login() throws InterruptedException {

        CRM_Utilities.crm_login(driver, "helpdesk86@cydeo.com", "UserUser");

        WebElement taskBtn = driver.findElement(By.xpath("//span[@id='feed-add-post-form-tab-tasks']"));
        taskBtn.click();

        Thread.sleep(1000);

        WebElement fieldToWrite =driver.findElement(By.xpath("/html/body"));

        fieldToWrite.sendKeys("Something");
        Thread.sleep(1000);


        WebElement sendBtn = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        sendBtn.click();

        Thread.sleep(1000);

        WebElement warningSign = driver.findElement(By.xpath("//div[text()='The task name is not specified.'] "));
        System.out.println("warningSign.isDisplayed() = " + warningSign.isDisplayed());
        Thread.sleep(3000);


    }
    @Test
    public void marketing85Login() throws InterruptedException {
        CRM_Utilities.crm_login(driver, "marketing86@cydeo.com", "UserUser");

        WebElement taskBtn = driver.findElement(By.xpath("//span[@id='feed-add-post-form-tab-tasks']"));
        taskBtn.click();


        WebElement sendBtn = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        sendBtn.click();

        Thread.sleep(3000);

        WebElement warningSign = driver.findElement(By.xpath("//div[text()='The task name is not specified.'] "));
        System.out.println("warningSign.isDisplayed() = " + warningSign.isDisplayed());
        Thread.sleep(1000);

    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }

}



