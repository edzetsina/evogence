package test;

import Page.ChannelPage;
import Page.FirstLoginPage;
import Page.LoginPage;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ChannelPageTest {

    public WebDriver webDriver;
    public String username = "admin";
    public String password = "password";
    ChannelPage channelPage;

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
        channelPage = loginPage.login(username, password);
    }
    @AfterClass
    public void afterClass() {
        webDriver.quit();
    }

    @Test
    public void testEditChannelTitle() {
    channelPage.changeChannelTitle();
        Assert.assertTrue(channelPage.isChannelTitleChanged(), "Channel title does not display");
    }
    @Test
    public void testEditChannelLayout() {
    channelPage.changeChannelLayout();
    Assert.assertTrue(channelPage.isChannelResolutionDisplay(), "Resolution is not display");
    }

}
