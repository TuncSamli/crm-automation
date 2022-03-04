package nextbacecrm.tests.CY29;
import nextbacecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.internal.IConfiguration;

import java.util.concurrent.TimeUnit;

public class US3_UserShould_Login_Logout_Mohammed {

   public WebDriver driver;
String url ="https://login2.nextbasecrm.com/";



   @BeforeMethod
    public void setUpMethod (){
    driver = WebDriverFactory.getDriver("chrome");
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.get(url);

   }

   @Test
    public void login1 (){

       WebElement userName = driver.findElement(By.xpath("//input[@name='USER_LOGIN']"));
userName.sendKeys("hr85@cydeo.com");

WebElement passWord = driver.findElement(By.xpath("//input[@name='USER_PASSWORD']"));
passWord.sendKeys("UserUser");

WebElement LogInButton = driver.findElement(By.xpath("//input[@class='login-btn']"));
LogInButton.click();

WebElement userBlock =driver.findElement(By.xpath("//div[@id='user-block']"));

userBlock.click();

WebElement LogOutButton =driver.findElement(By.xpath("//span[.='Log out']"));
LogOutButton.click();

String expected = "Authorization";
String actual = driver.getTitle();
Assert.assertEquals(actual,expected,"Test Failed !!!!");

   }


@Test


public void login2 (){

    WebElement userName = driver.findElement(By.xpath("//input[@name='USER_LOGIN']"));
    userName.sendKeys("helpdesk1@cydeo.com");

    WebElement passWord = driver.findElement(By.xpath("//input[@name='USER_PASSWORD']"));
    passWord.sendKeys("UserUser");

    WebElement LogInButton = driver.findElement(By.xpath("//input[@class='login-btn']"));
    LogInButton.click();

    WebElement userBlock =driver.findElement(By.xpath("//div[@id='user-block']"));

    userBlock.click();

    WebElement LogOutButton =driver.findElement(By.xpath("//span[.='Log out']"));
    LogOutButton.click();


    String expected = "Authorization";
    String actual = driver.getTitle();
    Assert.assertEquals(actual,expected);
}

@Test

public void login3 (){

    WebElement userName = driver.findElement(By.xpath("//input[@name='USER_LOGIN']"));
    userName.sendKeys("marketing1@cydeo.com");

    WebElement passWord = driver.findElement(By.xpath("//input[@name='USER_PASSWORD']"));
    passWord.sendKeys("UserUser");

    WebElement LogInButton = driver.findElement(By.xpath("//input[@class='login-btn']"));
    LogInButton.click();

    WebElement userBlock =driver.findElement(By.xpath("//div[@id='user-block']"));

    userBlock.click();

    WebElement LogOutButton =driver.findElement(By.xpath("//span[.='Log out']"));
    LogOutButton.click();

    String expected = "Authorization";
    String actual = driver.getTitle();
    Assert.assertEquals(actual,expected);

}



@AfterMethod
    public void tearDown(){
       driver.close();
}
































    }



