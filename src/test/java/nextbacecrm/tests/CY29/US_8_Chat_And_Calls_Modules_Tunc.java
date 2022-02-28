package nextbacecrm.tests.CY29;

import nextbacecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class US_8_Chat_And_Calls_Modules_Tunc {

    public WebDriver driver;
    //Test

    @BeforeMethod
    public void setupMethod() {
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://login2.nextbasecrm.com/");
    }



    @Test
    public void loginFunction() {
        WebElement userName = driver.findElement(By.xpath("//input[@name='USER_LOGIN']"));
        userName.sendKeys("hr86@cydeo.com");

        WebElement userPassword = driver.findElement(By.xpath("//input[@name='USER_PASSWORD']"));
        userPassword.sendKeys("UserUser");


        WebElement loginButton = driver.findElement(By.xpath("//input[@class='login-btn']"));
        loginButton.click();

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



}
