package Page;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



/**
 * Created by edzet on 11.06.2017.
 */
public class ContentLibraryPage extends BasePage {

    @FindBy(id = "header")
    private WebElement siteHeader;
    @FindBy(xpath = "//div[@id='menuBar']//*[text()='Content Library']")
    private WebElement contentLibraryButton;
    @FindBy(xpath = "//div[@id='menu_2_22']//a[1]")
    private WebElement manageClassesMenuButton;
    @FindBy(xpath = "//*[@title='Add new Class']")
    private WebElement addClassIcon;
    @FindBy(xpath = "//input[@class='text']")
    private WebElement iFrameNameField;
    @FindBy(xpath = "//button[@title='Save']")
    private WebElement iFarameSaveButton;
    @FindBy(xpath = "//span[@class='pointer' and text()='Test class']")
    private WebElement testClass;
    @FindBy(xpath = "//div[@class='roundedBox']//span[4]//*[@title='Add Category']")
    private WebElement addCategoryIcon;
    @FindBy(xpath = "//span[@class='pointer' and text()='Test Category']")
    private WebElement testCategory;
    @FindBy(xpath = "//div[@class='roundedBox']//span[5]//*[@title='Add Sub-Category']")
    private WebElement addSubCategoryIcon;
    @FindBy(xpath = "//span[@class='pointer' and text()='Test SubCategory']")
    private WebElement testSubCategory;
    @FindBy(xpath = "//div[@class='roundedBox']//span[6]//*[@title='View campaigns']")
    private WebElement viewCampaignIcon;
    @FindBy(xpath = "//*[@title='Add campaign']")
    private WebElement addCampaignIcon;
    @FindBy(xpath = "//*[text()='Test Campaign']")
    private WebElement testCampaign;
    @FindBy(xpath = "//tr[@class='row1']//*[@title='Manage Campaign Items']")
    private WebElement manageCampaignItemsIcon;
    @FindBy(xpath = "//div[@class='mb5']//*[@title='Add new Item']")
    private WebElement addNewItemIcon;
    @FindBy(xpath = "//input[@id='file1']")
    private WebElement browseButtonField;
    @FindBy(xpath = "//input[@id='title1']")
    private WebElement iFrameTitleField;
    @FindBy(xpath = "//div[@id='8']")
    private WebElement createdItem;
    @FindBy (id = "_error_subwindow")
    private WebElement errorIFrameMessage;


    public ContentLibraryPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
        waitUntilElementDisplayed(siteHeader, 20);
    }
    public boolean isPageLoaded() {
        return siteHeader.isDisplayed();
    }

    public ContentLibraryPage openManageClassesTab() {
        new Actions(webDriver).moveToElement(webDriver.findElement(By.xpath("//div[@id='menuBar']//*[text()='Content Library']"))).perform();
        waitUntilElementClickable(manageClassesMenuButton, 1).click();
        waitUntilElementDisplayed(addClassIcon);
        return this;
    }

    public ContentLibraryPage createClassInContentLibrary() {

        addClassIcon.click();
        webDriver.switchTo().frame("iFrameToAdjust");
        waitUntilElementDisplayed(iFrameNameField, 10).sendKeys("Test class");
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

    public boolean isAddedItemDisplay() {
        return createdItem.isDisplayed();
    }


    public ContentLibraryPage openClassMenuIcons() {
        new Actions(webDriver).moveToElement(webDriver.findElement(By.xpath("//span[@class='pointer' and text()='Test class']"))).perform();
        return this;
    }

    public ContentLibraryPage openCategoryMenuIcons() {
        new Actions(webDriver).moveToElement(webDriver.findElement(By.xpath("//span[@class='pointer' and text()='Test Category']"))).perform();
        return this;
    }

    public ContentLibraryPage openSubCategoryMenuIcons() {
        new Actions(webDriver).moveToElement(webDriver.findElement(By.xpath("//span[@class='pointer' and text()='Test SubCategory']"))).perform();
        return this;
    }

    public void createCategoryInContentLibrary() {
        addCategoryIcon.click();
        webDriver.switchTo().frame("iFrameToAdjust");
        waitUntilElementDisplayed(iFrameNameField, 10).sendKeys("Test Category");
        iFarameSaveButton.click();
        webDriver.switchTo().defaultContent();
        waitUntilElementDisplayed(testCategory, 10);
    }

    public void createSubCategoryInContentLibrary() {
        addSubCategoryIcon.click();
        webDriver.switchTo().frame("iFrameToAdjust");
        waitUntilElementDisplayed(iFrameNameField, 10).sendKeys("Test SubCategory");
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
        waitUntilElementDisplayed(iFrameNameField, 10).sendKeys("Test Campaign");
        iFarameSaveButton.click();
        webDriver.switchTo().defaultContent();
        waitUntilElementDisplayed(testCampaign, 10);
    }

    public void uploadImageToContentLibraryCampaign() {
        waitUntilElementDisplayed(manageCampaignItemsIcon, 10).click();
        addNewItemIcon.click();
        webDriver.switchTo().frame("iFrameToAdjust");
        waitUntilElementDisplayed(browseButtonField, 10);
        webDriver.findElement(By.id("file1")).sendKeys("C:\\Users\\edzet\\projects\\evogence\\src\\test\\resources\\Raccoon.jpg");
        webDriver.findElement(By.id("timeplay1")).clear();
        webDriver.findElement(By.id("timeplay1")).sendKeys("00:00:20");
        iFarameSaveButton.click();
        webDriver.switchTo().defaultContent();
        waitUntilElementDisplayed(createdItem, 10);
    }
    public void uploadVideoToContentLibraryCampaign() {
        waitUntilElementDisplayed(manageCampaignItemsIcon, 10).click();
        addNewItemIcon.click();
        webDriver.switchTo().frame("iFrameToAdjust");
        waitUntilElementDisplayed(browseButtonField, 10);
        webDriver.findElement(By.id("file1")).sendKeys("C:\\Users\\edzet\\Desktop\\Content\\Video types\\Trailers\\Deadpool2_HD.mp4");
        iFarameSaveButton.click();
        webDriver.switchTo().defaultContent();
        waitUntilElementDisplayed(createdItem, 20);
    }
}


