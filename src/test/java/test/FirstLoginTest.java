package test;

import Page.FirstLoginPage;
import Page.LoginPage;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class FirstLoginTest {
    public WebDriver webDriver;
    public String username = "admin";
    public String password = "password";
    LoginPage loginPage;
    FirstLoginPage firstLoginPage;


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
        firstLoginPage = loginPage.login(username, password);
    }


    @AfterClass
    public void afterClass() {
        webDriver.quit();
    }

    @Test (priority = 1)
    public void testFirstLoginPositive() {

        firstLoginPage.identityInitials();
        Assert.assertTrue(firstLoginPage.isAccountInformationTabDisplayed(), "Account information page is not opened");
    }
    @Test (priority = 2)
    public  void testAccountInformation() {
        firstLoginPage.enterAccountInfo();
        Assert.assertTrue(firstLoginPage.isSystemBackupTabDisplayed(), "System backup page is not opened");
    }

    @Test (priority = 3)
    public  void testSystemBackupCreationNo() {
        firstLoginPage.systemBackupNoCreating();
        Assert.assertTrue(firstLoginPage.isMailServerConfigurationTabDisplayed(), "SMTP Server page is not loaded");
    }
    @Test (priority = 4)
    public void testFirstLoginConfirmation() {
        firstLoginPage.firstLoginConfirmation();
        String expectedAppliance = "Ei Node Auto Test";
        String expectedCompany = "Evogence";
        String expectedLocation = "USA";
        String expectedLogin = "admin";
        String expectedMail = "admin@evogence.com";
        String expectedSysbackup = "No";
        String expectedMailStatus = "\n" +
                "                                                Mail Server was not configured\n" +
                "                                            ";
        Assert.assertEquals(firstLoginPage.isApplianceNameEqualsTo(expectedAppliance), "Appliance name does not match");
        Assert.assertEquals(firstLoginPage.isCompanyNameEqualsTo(expectedCompany), "Company name does not match");
        Assert.assertEquals(firstLoginPage.isLocationNameEqualsTo(expectedLocation), "Location does not match");
        Assert.assertEquals(firstLoginPage.isLoginNameEqualsTo(expectedLogin), "Login does not match");
        Assert.assertEquals(firstLoginPage.isEmailNameEqualsTo(expectedMail),"Email does not match");
        Assert.assertEquals(firstLoginPage.isSysBackupEqualsTo(expectedSysbackup), "System backup does not match");
        Assert.assertEquals(firstLoginPage.isMailServerStatusEqualsTo(expectedMailStatus), "Mail server configuration status does not match");

    }

    @Test (priority = 5)
    public void testFistLoginConfirmationClose() {
        firstLoginPage.firstLoginConfirmationClose();

    }


}
