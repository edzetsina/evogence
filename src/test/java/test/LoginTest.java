package test;

import Page.LoginPage;
import Page.ContentLibraryPage;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import static java.lang.Thread.sleep;

public class LoginTest {

    public WebDriver webDriver;
    public String username="admin";
    public String password="password";


    @Parameters({"BrowserType"})
    @BeforeClass
    public void beforeClass(@Optional("firefox") String BrowserType) throws InterruptedException {
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


    @AfterClass
    public  void afterClass() {
        webDriver.quit();
    }

    @Test
    public void testLoginPositive()  {
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertEquals(loginPage.getPageTitle(), ":: RPN :: Login Page", "Main page title is wrong");
        Assert.assertEquals(loginPage.getPageURL(), "http://192.168.101.92/", "Wrong URL on Login Page");
        ContentLibraryPage mainPage = loginPage.login(username, password);
        Assert.assertTrue(mainPage.isPageLoaded(), "Menu bar is not displayed");
        Assert.assertEquals(mainPage.getPageTitle(), ":: RPN :: Channels", "Main page title is wrong");
    }


    /**
     * Can not make any tests, because cannot highlight element!!! Need developer to fix it!
     */
 /*   @DataProvider
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
    }*/

    @Test
    public  void testLogOut(){
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertEquals(loginPage.getPageTitle(), ":: RPN :: Login Page", "Main page title is wrong");
        Assert.assertEquals(loginPage.getPageURL(), "http://192.168.101.92/", "Wrong URL on Login Page");
        ContentLibraryPage mainPage = loginPage.login(username, password);
        Assert.assertTrue(mainPage.isPageLoaded(), "Menu bar is not displayed");
        Assert.assertEquals(mainPage.getPageTitle(), ":: RPN :: Channels", "Main page title is wrong");
        loginPage = mainPage.logOut();
        Assert.assertEquals(loginPage.getPageTitle(), ":: RPN :: Login Page", "Main page title is wrong");
        Assert.assertEquals(loginPage.getPageURL(), "http://192.168.101.92/index.php", "Wrong URL on Login Page");
    }
}
