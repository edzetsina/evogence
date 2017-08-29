package Page;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

import static java.lang.Thread.sleep;


/**
 * Created by edzet on 11.06.2017.
 */
public class ContentLibraryPage extends BasePage {

    @FindBy(css = ".menu_bottom_bg>table>tbody>tr>td")
    private WebElement menuBar;
    @FindBy(xpath = ".//*[@id='header']/table/tbody/tr[1]/td[4]/div/a[7]")
    private WebElement logOutButton;
    @FindBy(xpath = "//div[@id='menuBar']//*[text()='Content Library']")
    private WebElement contentLibraryButton;
    @FindBy(xpath = ".//*[@id='menu_2_22']/a[1]")
    private WebElement manageClassesMenuButton;
    @FindBy(xpath = "//div[@id='site_content']//div//td//tbody//tr//td//a//img")
    private WebElement addClassIcon;
    @FindBy(xpath = "//input[@class='text']")
    private WebElement addClassNameField;
    @FindBy (xpath = "//*[@id='adjustTable']//tr//*[@class='btn' and text()='Save']")
    private WebElement iFarameSaveButton;
    @FindBy (xpath = "//*[@id='site_content']//*[@class='tree']//li[2]//*[text()='Test class']")
    private WebElement testClass;
    @FindBy (xpath = "//*[@id='site_content']//*[@class='roundedBox']//span[4]//nobr//img[1]")
    private WebElement addCategoryIcon;
    @FindBy (xpath = "//*[@id='site_content']//*[@class='tree']//li[2]//*[text()='Test Category']")
    private WebElement testCategory;
    @FindBy (xpath = "//*[@id='site_content']//*[@class='roundedBox']//span[5]//nobr//img[1]")
    private WebElement addSubCategoryIcon;
    @FindBy (xpath = "//*[@id='site_content']//*[@class='tree']//li[2]//*[text()='Test SubCategory']")
    private WebElement testSubCategory;
    @FindBy (xpath = "//*[@id='site_content']//*[@class='roundedBox']//span[6]//nobr//img[3]")
    private WebElement viewCampaignIcon;
    @FindBy (xpath = "//*[@title='Add campaign']")
    private WebElement addCampaignIcon;
    @FindBy (xpath = ".//*[@id='second']//*[text()='Test Campaign']")
    private WebElement testCampaign;
    @FindBy (xpath = "//*[@title='Manage Campaign Items']")
    private WebElement manageCampaignItemsIcon;

    @FindBy (xpath = "//*[@title='Add new Item']")
    private WebElement addNewItemIcon;
    @FindBy (xpath = "//input[@id='file1']")
    private WebElement browseButtonField;

    @FindBy (xpath = "//input[@id='title1']")
    private WebElement iFrameTitleField;




    public ContentLibraryPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
        waitUntilElementDisplayed(menuBar, 20);
    }

    public LoginPage logOut() {
        waitUntilElementDisplayed(logOutButton, 5).click();
        return PageFactory.initElements(webDriver, LoginPage.class);
    }

    public boolean isPageLoaded() {
        return menuBar.isDisplayed();
    }

    public ContentLibraryPage openManageClassesTab() {
        new Actions(webDriver).moveToElement(webDriver.findElement(By.xpath(".//*[@id='menuBar']/ul/li[2]/a"))).perform();
        waitUntilElementClickable(manageClassesMenuButton, 1).click();
        waitUntilElementDisplayed(addClassIcon);
        return this;
    }

    public ContentLibraryPage createClassInContentLibrary() {

        addClassIcon.click();
        webDriver.switchTo().frame("iFrameToAdjust");
        waitUntilElementDisplayed(addClassNameField, 10).sendKeys("Test class");
        iFarameSaveButton.click();
        webDriver.switchTo().defaultContent();
        waitUntilElementDisplayed(testClass, 10);
        return this;
    }

    public boolean isCreatedClassDisplay() {
        return testClass.isDisplayed();
    }
    public boolean isCreatedCategoryDisplay() {
        return testCategory.isDisplayed();
    }
    public boolean isCreatedSubCategoryDisplay() {
        return testSubCategory.isDisplayed();
    }
    public boolean isCreatedCampaignDisplay() {
        return testCampaign.isDisplayed();
    }


    public ContentLibraryPage openClassMenuIcons() {
        new Actions(webDriver).moveToElement(webDriver.findElement(By.xpath(".//*[@id='site_content']//*[@class='tree']//li[2]//*[text()='Test class']"))).perform();
        return this;
    }
    public ContentLibraryPage openCategoryMenuIcons() {
        new Actions(webDriver).moveToElement(webDriver.findElement(By.xpath("//*[@id='site_content']//*[@class='tree']//li[2]//*[text()='Test Category']"))).perform();
        return this;
    }
    public ContentLibraryPage openSubCategoryMenuIcons() {
        new Actions(webDriver).moveToElement(webDriver.findElement(By.xpath("//*[@id='site_content']//*[@class='tree']//li[2]//*[text()='Test SubCategory']"))).perform();
        return this;
    }

    public void createCategoryInContentLibrary() {
        addCategoryIcon.click();
        webDriver.switchTo().frame("iFrameToAdjust");
        waitUntilElementDisplayed(addClassNameField, 10).sendKeys("Test Category");
        iFarameSaveButton.click();
        webDriver.switchTo().defaultContent();
        waitUntilElementDisplayed(testCategory, 10);
    }

    public void createSubCategoryInContentLibrary() {
        addSubCategoryIcon.click();
        webDriver.switchTo().frame("iFrameToAdjust");
        waitUntilElementDisplayed(addClassNameField, 10).sendKeys("Test SubCategory");
        iFarameSaveButton.click();
        webDriver.switchTo().defaultContent();
        waitUntilElementDisplayed(testSubCategory, 10);
    }

    public void openCampaignPage() {
        viewCampaignIcon.click();
        Alert alert = webDriver.switchTo().alert();
        alert.accept();

    }

    public void createNewCampaign() {
        waitUntilElementDisplayed(addCampaignIcon, 10).click();
        webDriver.switchTo().frame("iFrameToAdjust");
        waitUntilElementDisplayed(addClassNameField, 10).sendKeys("Test Campaign");
        iFarameSaveButton.click();
        webDriver.switchTo().defaultContent();
        waitUntilElementDisplayed(testCampaign, 10);
    }

    public void uploadFileToContentLibraryCampaign() throws InterruptedException {
        waitUntilElementDisplayed(manageCampaignItemsIcon, 10).click();
        addNewItemIcon.click();
        webDriver.switchTo().frame("iFrameToAdjust");
        waitUntilElementDisplayed(browseButtonField, 10);
        webDriver.findElement(By.id("file1")).sendKeys("C:\\Users\\edzet\\projects\\evogence\\src\\test\\resources\\Raccoon.jpg");
        webDriver.findElement(By.id("timeplay1")).sendKeys("00:00:20");
        iFarameSaveButton.click();
        webDriver.switchTo().defaultContent();
    }
}


