package Test;

import Page.LoginPage;
import Page.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



/**
 * Created by edzet on 01.06.2017.
 */
public class LoginTest {

    public WebDriver webDriver;
    public String username="admin";
    public String password="password";

    @BeforeMethod
    public void beforeMethod() throws InterruptedException {
        webDriver = new FirefoxDriver();
        webDriver.navigate().to("http://192.168.101.92");

    }
    @AfterMethod
    public  void afterMethod() {
        webDriver.quit();
    }

    @Test
    public void testLoginPositive() throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertEquals(loginPage.getPageTitle(), ":: RPN :: Login Page", "Main page title is wrong");
        Assert.assertEquals(loginPage.getPageURL(), "http://192.168.101.92/index.php", "Wrong URL on Login Page");
        MainPage mainPage = loginPage.login(username, password);
        Assert.assertEquals(mainPage.getPageTitle(), ":: RPN :: Channels", "Main page title is wrong");
        Assert.assertTrue(mainPage.isPageLoaded(), "Menu bar is not displayed");
    }

    @Test
    public void testLoginNegative() throws InterruptedException {
        String expectedErrorMsg = "Your login or password is incorrect!";
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertEquals(loginPage.getPageTitle(), ":: RPN :: Login Page", "Main page title is wrong");
        Assert.assertEquals(loginPage.getPageURL(), "http://192.168.101.92/index.php", "Wrong URL on Login Page");
        loginPage = loginPage.login(username, "Test");
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        Assert.assertTrue(loginPage.isInvalidCredentialMsg(), "Error message was not displayed on login page");
        Assert.assertEquals(loginPage.getErrorMsgText(), expectedErrorMsg, "Error msg has wrong test");

    }

    @Test
    public  void testLogOut(){
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertEquals(loginPage.getPageTitle(), ":: RPN :: Login Page", "Main page title is wrong");
        Assert.assertEquals(loginPage.getPageURL(), "http://192.168.101.92/index.php", "Wrong URL on Login Page");
        MainPage mainPage = loginPage.login(username, password);
        Assert.assertEquals(mainPage.getPageTitle(), ":: RPN :: Channels", "Main page title is wrong");
        Assert.assertTrue(mainPage.isPageLoaded(), "Menu bar is not displayed");

        loginPage = mainPage.logOut();
        Assert.assertEquals(loginPage.getPageTitle(), ":: RPN :: Login Page", "Main page title is wrong");
        Assert.assertEquals(loginPage.getPageURL(), "http://192.168.101.92/index.php", "Wrong URL on Login Page");
    }

}
