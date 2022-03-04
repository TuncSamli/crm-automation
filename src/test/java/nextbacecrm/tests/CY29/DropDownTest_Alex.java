package nextbacecrm.tests.CY29;


import nextbacecrm.utilities.CRM_Utilities;
import nextbacecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.annotations.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static nextbacecrm.utilities.ConfigurationReader.getProperty;

public class DropDownTest_Alex {
    WebDriver driver;
    @BeforeMethod
    public void  setUpMethod(){
        driver= WebDriverFactory.getDriver(getProperty("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(getProperty("env"));
    }
    @DataProvider(name="provider")
    public Object [][] dpMthd() {
        return new Object[][]{
                {"username", "password"},
                {"username2", "password"},
                {"username3", "password"},
                {"username4", "password"},
                {"username5", "password"},
                {"username6", "password"},
                {"username7", "password"},
                {"username8", "password"},
                {"username9", "password"}
        };
    }

    @Test(dataProvider = "provider")
    public void hr85Login(String name,String password) {
        CRM_Utilities.crm_login(driver, getProperty(name),getProperty(password));

        WebElement find = driver.findElement(By.xpath("//span[@class='user-name']"));
        find.click();

        List<WebElement> all = driver.findElements(By.xpath("//div[@id='menu-popup-user-menu']"));
        for (WebElement each : all) {
            System.out.println(each.getText());
        }
    }
    @AfterMethod
    public void after(){
        driver.close();
    }
}
