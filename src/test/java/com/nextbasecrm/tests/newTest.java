package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.CRM_Utilities;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class newTest {
    WebDriver driver;
@BeforeMethod
    public void  setUpMethod(){
        driver=WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}
    @Test
    public void hr85Login()throws InterruptedException{
    CRM_Utilities.crm_login(driver,"hr85@cydeo.com","UserUser");
    CRM_Utilities.crm_proFileClick(driver);
}
    @Test
    public void helpdesk85Login(){
    CRM_Utilities.crm_login(driver,"helpdesk85@cydeo.com","UserUser");
    CRM_Utilities.crm_proFileClick(driver);
}
    @Test
    public void marketing85Login() {
    CRM_Utilities.crm_login(driver,"marketing85@cydeo.com","UserUser");
    CRM_Utilities.crm_proFileClick(driver);
}
@AfterMethod
    public void afterMethod(){
    driver.close();
}
}
