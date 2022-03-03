package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.CRM_Utilities;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class US_4_DropdownFunctionality_Aleksandr {
    WebDriver driver;
@BeforeMethod
    public void  setUpMethod(){
        driver=WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}
    @Test
    public void hr85Login(){
    CRM_Utilities.crm_login(driver,"hr85@cydeo.com","UserUser");
    CRM_Utilities.crm_proFileClick(driver);
        WebElement find = driver.findElement(By.xpath("//span[@class='user-name']"));
        Assert.assertTrue(find.isDisplayed());
        List<WebElement>all=driver.findElements(By.xpath("//div[@id='menu-popup-user-menu']"));
        for(WebElement each:all){
            System.out.println(each.getText());
        }
}
    @Test
    public void helpdesk85Login() {
        CRM_Utilities.crm_login(driver, "helpdesk85@cydeo.com", "UserUser");
        CRM_Utilities.crm_proFileClick(driver);
        WebElement find = driver.findElement(By.xpath("//span[@class='user-name']"));
        Assert.assertTrue(find.isDisplayed());
        List<WebElement> all = driver.findElements(By.xpath("//div[@id='menu-popup-user-menu']"));
        for (WebElement each : all) {
            System.out.println(each.getText());
        }
        }
//
        @Test
        public void marketing85Login () {
            CRM_Utilities.crm_login(driver, "marketing85@cydeo.com", "UserUser");
            CRM_Utilities.crm_proFileClick(driver);
            WebElement find = driver.findElement(By.xpath("//span[@class='user-name']"));
            Assert.assertTrue(find.isDisplayed());
            List<WebElement> all = driver.findElements(By.xpath("//div[@id='menu-popup-user-menu']"));
            for (WebElement each : all) {
                System.out.println(each.getText());
            }
        }
            @AfterMethod
            public void afterMethod () {
                driver.close();
            }
        }

