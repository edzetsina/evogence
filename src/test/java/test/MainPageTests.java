package test;

import Page.LoginPage;
import Page.MainPage;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class MainPageTests {

    public WebDriver webDriver;
    public String username="admin";
    public String password="password";
    MainPage mainPage;
    LoginPage loginPage;

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
        LoginPage loginPage = new LoginPage(webDriver);
        mainPage = loginPage.login(username, password);
    }


    @AfterClass
    public  void afterClass() {
        webDriver.quit();
    }


    @Test
    public void testAddClassToContentLibrary() {
        mainPage.openManageClassesTab();
        mainPage.createClassInContentLibrary();
        Assert.assertTrue(mainPage.isCreatedClassDisplay(), "New class is not created");
    }

}
