package test;

import Page.LoginPage;
import Page.ContentLibraryPage;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;



public class ContentLibraryPageTests {

    public WebDriver webDriver;
    public String username = "admin";
    public String password = "password";
    ContentLibraryPage contentLibraryPage;
    LoginPage loginPage;

    @Parameters({"BrowserType"})
    @BeforeClass
    public void beforeClass(@Optional("firefox") String BrowserType) throws InterruptedException {
        if (BrowserType.toLowerCase().equals("chrome")) {
            ChromeDriverManager.getInstance().setup();
            webDriver = new ChromeDriver();
        }
        if (BrowserType.toLowerCase().equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
            webDriver = new FirefoxDriver();
        }
        webDriver.navigate().to("http://192.168.101.92");
        LoginPage loginPage = new LoginPage(webDriver);
        contentLibraryPage = loginPage.login(username, password);
    }


    @AfterClass
    public void afterClass() {
        webDriver.quit();
    }


    @Test(priority = 1)
    public void testAddClassToContentLibrary() {
        contentLibraryPage.openManageClassesTab();
        contentLibraryPage.createClassInContentLibrary();
        Assert.assertTrue(contentLibraryPage.isCreatedClassDisplay(), "New class is not created");
        Assert.assertTrue(contentLibraryPage.isPageLoaded(), "Page is not loaded");
    }

    @Test(priority = 2)
    public void testAddCategoryToContentLibrary() {
        contentLibraryPage.openClassMenuIcons();
        contentLibraryPage.createCategoryInContentLibrary();
        Assert.assertTrue(contentLibraryPage.isCreatedCategoryDisplay(), "New category is not created");
        Assert.assertTrue(contentLibraryPage.isPageLoaded(), "Page is not loaded");
    }

    @Test(priority = 3)
    public void testAddSubCategoryToContentLibrary() {
        contentLibraryPage.openCategoryMenuIcons();
        contentLibraryPage.createSubCategoryInContentLibrary();
        Assert.assertTrue(contentLibraryPage.isCreatedSubCategoryDisplay(), "New SubCategory is not created");
        Assert.assertTrue(contentLibraryPage.isPageLoaded(), "Page is not loaded");
    }

    @Test(priority = 4)
    public void testAddCampaign() {
        contentLibraryPage.openSubCategoryMenuIcons();
        contentLibraryPage.openCampaignPage();
        contentLibraryPage.createNewCampaign();
        Assert.assertTrue(contentLibraryPage.isCreatedCampaignDisplay(), "New Campaign is not created");
        Assert.assertTrue(contentLibraryPage.isPageLoaded(), "Page is not loaded");
    }

    @Test(priority = 5)
    public void testAddItemToCampaign() {
        contentLibraryPage.uploadFileToContentLibraryCampaign();
        Assert.assertTrue(contentLibraryPage.isAddedItemDisplay(), "Item is not added");
        Assert.assertTrue(contentLibraryPage.isPageLoaded(), "Page is not loaded");
    }
}
