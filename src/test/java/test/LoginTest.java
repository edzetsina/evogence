package test;

import Page.ChannelPage;
import Page.LoginPage;
import Page.ContentLibraryPage;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class LoginTest {

    public WebDriver webDriver;
    public String username="admin";
    public String password="password";


    @Parameters({"BrowserType"})
    @BeforeTest
    public void beforeTest(@Optional("firefox") String BrowserType) throws InterruptedException {
        if (BrowserType.toLowerCase().equals("chrome")) {
            ChromeDriverManager.getInstance().setup();
            webDriver = new ChromeDriver();
        }
        if (BrowserType.toLowerCase().equals("firefox")) {
            FirefoxDriverManager.getInstance().setup();
            webDriver = new FirefoxDriver();
        }
            webDriver.navigate().to("http://192.168.101.92");
        }


    @AfterTest
    public  void afterTest() {
        webDriver.quit();
    }

    @Test (priority = 1)
    public void testLoginPositive()  {
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertEquals(loginPage.getPageTitle(), ":: RPN :: Login Page", "Main page title is wrong");
        Assert.assertEquals(loginPage.getPageURL(), "http://192.168.101.92/", "Wrong URL on Login Page");
        ChannelPage channelPage = loginPage.login(username, password);
        Assert.assertTrue(channelPage.isPageLoaded(), "Menu bar is not displayed");
        Assert.assertEquals(channelPage.getPageTitle(), ":: RPN :: Channels", "Main page title is wrong");
    }


    @DataProvider
    public  static Object [] []  negativeTestEnvironment() {
        return new Object[][] {
                {"", "", "Your login or password is incorrect!"},
                {"admin", "", "Your login or password is incorrect!"},
                {"admin", "p@ssword", "Your login or password is incorrect!"},
                {"adm1n", "password", "Your login or password is incorrect!"},
        };
    }
    @Test(dataProvider = "negativeTestEnvironment")
    public void testLoginNegative(String username, String password, String expectedErrorMsg) throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertEquals(loginPage.getPageTitle(), ":: RPN :: Login Page", "Main page title is wrong");
        Assert.assertEquals(loginPage.getPageURL(), "http://192.168.101.92/", "Wrong URL on Login Page");
        loginPage = loginPage.login(username, password);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        Assert.assertTrue(loginPage.isInvalidCredentialMsg(), "Error message was not displayed on login page");
        Assert.assertEquals(loginPage.getErrorMsgText(), expectedErrorMsg, "Error msg has wrong test");
    }

    @Test (priority = 3)
    public  void testLogOut(){
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertEquals(loginPage.getPageTitle(), ":: RPN :: Login Page", "Main page title is wrong");
        Assert.assertEquals(loginPage.getPageURL(), "http://192.168.101.92/", "Wrong URL on Login Page");
        ChannelPage channelPage = loginPage.login(username, password);
        Assert.assertTrue(channelPage.isPageLoaded(), "Menu bar is not displayed");
        Assert.assertEquals(channelPage.getPageTitle(), ":: RPN :: Channels", "Main page title is wrong");
        loginPage = channelPage.logOut();
        Assert.assertEquals(loginPage.getPageTitle(), ":: RPN :: Login Page", "Main page title is wrong");
        Assert.assertEquals(loginPage.getPageURL(), "http://192.168.101.92/index.php", "Wrong URL on Login Page");
    }
}
