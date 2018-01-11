package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ChannelPage extends BasePage {

    @FindBy(id = "header")
    private WebElement siteHeader;
    @FindBy(xpath = "//div[@id='header']//*[text()='Logout']")
    private WebElement logOutButton;
    @FindBy (xpath = "//*[@class='pr8']//a")
    private WebElement editChannel;
    @FindBy (id = "channel_title")
    private WebElement channelTitleField;
    @FindBy (xpath = "//*[@class='channel_name']")
    private WebElement channelTitle;
    @FindBy (xpath = "//button[@type='button' and text()='Save']")
    private WebElement saveChannelConfigButton;
    @FindBy (xpath = "//*[@class='template-chose-info-box']//a")
    private WebElement editChannelLayoutButton;
    @FindBy (xpath = ".//*[@id='radio_28']")
    private WebElement singlePortraitLayoutCheckbox;
    @FindBy (xpath = ".//*[@id='save-edit-layout-type']")
    private WebElement saveLayoutButton;
    @FindBy (xpath = "//*[@class='assigned_frames']")
    private WebElement assignFrameDropdown;
    @FindBy(xpath = "//*[@class='frame_cell_1' and @name='frame_1']")
    private WebElement frame1DropdownList;
    @FindBy (xpath = "//button[@name='Assign Frames']")
    private WebElement assignFramesButton;

    @FindBy (xpath = "/html/body/div[1]/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/div[2]/table/tbody/tr/td/table/tbody/tr[1]/td/table[2]/tbody/tr[2]/td[6]")
    private WebElement channelResolution;


    public ChannelPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
        waitUntilElementDisplayed(siteHeader, 10);
    }

    public LoginPage logOut() {
        waitUntilElementDisplayed(logOutButton, 5).click();
        return PageFactory.initElements(webDriver, LoginPage.class);
    }
    public boolean isPageLoaded() {
        return siteHeader.isDisplayed();
    }

    public void changeChannelTitle() {
        waitUntilElementDisplayed(editChannel, 5);
        editChannel.click();
        channelTitleField.clear();
        channelTitleField.sendKeys("Auto Test Channel");
        saveChannelConfigButton.click();
        waitUntilElementDisplayed(channelTitle, 5);

        }
    public boolean isChannelTitleChanged(){
        return channelTitle.isDisplayed();
    }
    public void changeChannelLayout() {
        editChannel.click();
        waitUntilElementDisplayed(editChannelLayoutButton, 5);
        editChannelLayoutButton.click();
        singlePortraitLayoutCheckbox.click();
        saveLayoutButton.click();

        webDriver.switchTo().frame("iFrameToAdjust");
        waitUntilElementDisplayed(assignFrameDropdown, 5);
        assignFrameDropdown.click();
        frame1DropdownList.click();
        assignFramesButton.click();
        webDriver.switchTo().defaultContent();

        waitUntilElementDisplayed(saveChannelConfigButton, 10);
        saveChannelConfigButton.click();
        saveChannelConfigButton.click();

        waitUntilElementDisplayed(channelTitle, 20);
    }
    public boolean isChannelResolutionDisplay(){
        return channelResolution.isDisplayed();
    }


}


