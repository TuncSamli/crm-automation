package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.CRM_Utilities;
import com.nextbasecrm.utilities.WebDriverFactory;
import com.nextbasecrm.utilities.configurationReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class US_4_DropdownFunctionality_Aleksandr extends configurationReader {
    WebDriver driver;
@BeforeMethod
    public void  setUpMethod(){
        driver=WebDriverFactory.getDriver(getProperty("browser"));
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
        CRM_Utilities.crm_proFileClick(driver);
        WebElement find = driver.findElement(By.xpath("//span[@class='user-name']"));
        assertTrue(find.isDisplayed());
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

