package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

import static java.lang.Thread.sleep;

/**
 * Created by edzet on 11.06.2017.
 */
public class MainPage extends BasePage {

    @FindBy(css = ".menu_bottom_bg>table>tbody>tr>td")
    private WebElement menuBar;
    @FindBy(xpath = ".//*[@id='header']/table/tbody/tr[1]/td[4]/div/a[7]")
    private WebElement logOutButton;
    @FindBy(xpath = "//div[@id='menuBar']//*[text()='Content Library']")
    private WebElement contentLibraryButton;

    //@FindBy(xpath = ".//*[@id='menuBar']/ul/li[2]/a")
    //private WebElement contentLibraryButton;

    @FindBy(xpath = ".//*[@id='menu_2_22']/a[1]")
    private WebElement manageClassesMenuButton;
    @FindBy(xpath = "//div[@id='site_content']//div//td//tbody//tr//td//a//img")
    private WebElement addClassIcon;
   // @FindBy(xpath = "//*[@id='adjustTable']//input[@class='text']")
  //  private WebElement addClassNameField;

    @FindBy(xpath = "//input[@class='text']")
    private WebElement addClassNameField;
    @FindBy (xpath = "//*[@id='adjustTable']//tr//*[@class='btn' and text()='Save']")
    private WebElement addClassSaveButton;
    @FindBy (xpath = ".//*[@id='ih_1']/nobr/img[1]")
    private WebElement addCategoryIcon;
    @FindBy (xpath = "//*[@id='site_content']//*[@class='tree']//li[2]//*[text()='Test class']")
    private WebElement testClass;


    public MainPage(WebDriver webDriver) {
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

    public  MainPage openManageClassesTab() {
        new Actions(webDriver).moveToElement(webDriver.findElement(By.xpath(".//*[@id='menuBar']/ul/li[2]/a"))).perform();
        waitUntilElementClickable(manageClassesMenuButton, 1).click();
        waitUntilElementDisplayed(addClassIcon);
        return this;
    }

    public MainPage createClassInContentLibrary() {

        addClassIcon.click();
        webDriver.switchTo().frame("iFrameToAdjust");
        waitUntilElementDisplayed(addClassNameField, 10).sendKeys("Test class");
        addClassSaveButton.click();
        webDriver.switchTo().defaultContent();
        waitUntilElementDisplayed(testClass, 10);
        return this;
    }

    public boolean isCreatedClassDisplay() {
        return testClass.isDisplayed();
    }


}


