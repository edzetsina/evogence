package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;


public class FirstLoginPage extends BasePage {

    String applianceName = "Ei Node Auto Test";
    String companyName = "Evogence";
    String locationName = "USA";
    String userLogin = "admin";
    String userPassword = "password";
    String userMail = "admin@evogence.com";

    @FindBy(id = "appliance_name")
    private WebElement applianceNameField;
    @FindBy(id = "appliance_company")
    private WebElement companyNameField;
    @FindBy(id = "appliance_location")
    private WebElement locationNameField;
    @FindBy(xpath = "//button[@title='Next']")
    private WebElement nextButton;
    @FindBy(id = "userlogin")
    private WebElement userLoginField;
    @FindBy(id = "userpass")
    private WebElement userPasswordField;
    @FindBy(id = "usercpass")
    private WebElement userConfirmPasswordField;
    @FindBy(id = "useremail")
    private WebElement userMailField;
    @FindBy(xpath = ".//*[@id='config_user']//button[@title='Next']")
    private WebElement nextAccountButton;
    @FindBy(id = "no")
    private WebElement sysBackupCheckboxNo;
    @FindBy(xpath = ".//*[@id='config_backup']//button[@title='Next']")
    private WebElement nextSysbackupButton;
    @FindBy(xpath = ".//*[@id='config_smtp']//button[@title='Save']")
    private WebElement saveButton;
    @FindBy(xpath = "//button[@name='Confirm']")
    private WebElement frameConfirmButton;
    @FindBy(css = "#confirm_appliance_name")
    private List<WebElement> frameApplianceName;
    @FindBy(css = "#confirm_appliance_company")
    private List<WebElement> frameCompanyName;
    @FindBy(css = "#confirm_appliance_location")
    private List<WebElement> frameLocationName;
    @FindBy(css = "#confirm_userlogin")
    private List<WebElement> frameLoginName;
    @FindBy(css = "#confirm_useremail")
    private List<WebElement> frameEmailName;
    @FindBy(css = "#confirm_backup")
    private List<WebElement> frameSysBackup;
    @FindBy(css = "#smtp_not_configured")
    private List<WebElement> frameMailServerConfig;
    @FindBy(xpath = ".//*[@id='popup_content_container']//button[@title='Confirm']")
    private WebElement confirmButton;


    public FirstLoginPage(WebDriver webDriver) {
        super (webDriver);
        PageFactory.initElements(webDriver, this);
        waitUntilElementDisplayed(applianceNameField, 10);
    }

    public FirstLoginPage identityInitials() {
        waitUntilElementDisplayed(applianceNameField, 5);
        applianceNameField.clear();
        applianceNameField.sendKeys(applianceName);
        companyNameField.sendKeys(companyName);
        locationNameField.sendKeys(locationName);
        nextButton.click();
        return this;
    }

    public FirstLoginPage enterAccountInfo() {
        waitUntilElementDisplayed(userLoginField, 2);
        userLoginField.sendKeys(userLogin);
        userPasswordField.sendKeys(userPassword);
        userConfirmPasswordField.sendKeys(userPassword);
        userMailField.sendKeys(userMail);
        nextAccountButton.click();
        return this;
    }

    public void systemBackupNoCreating() {
        waitUntilElementDisplayed(sysBackupCheckboxNo, 2);
        sysBackupCheckboxNo.click();
        nextSysbackupButton.click();
    }

    public void firstLoginConfirmation() {
        waitUntilElementDisplayed(saveButton, 3);
        saveButton.click();
    }

    public boolean isAccountInformationTabDisplayed() {
        return userLoginField.isDisplayed();
    }

    public boolean isSystemBackupTabDisplayed() {
        return sysBackupCheckboxNo.isDisplayed();
    }

    public boolean isMailServerConfigurationTabDisplayed() {
        return saveButton.isDisplayed();
    }

    public boolean isApplianceNameEqualsTo(String ApplianceNameText) {
        for (WebElement appliance : frameApplianceName) {
            if (!appliance.getText().equalsIgnoreCase(ApplianceNameText)) {
                return false;
            }
        }
        return true;
    }

    public boolean isCompanyNameEqualsTo(String companyText) {
        for (WebElement company : frameCompanyName) {
            if (!company.getText().equalsIgnoreCase(companyText)) {
                return false;
            }
        }
        return true;
    }

    public boolean isLocationNameEqualsTo(String locationText) {
        for (WebElement location : frameLocationName) {
            if (!location.getText().equalsIgnoreCase(locationText)) {
                return false;
            }
        }
        return true;
    }

    public boolean isLoginNameEqualsTo(String loginText) {
        for (WebElement login : frameLoginName) {
            if (!login.getText().equalsIgnoreCase(loginText)) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmailNameEqualsTo(String emailText) {
        for (WebElement email : frameEmailName) {
            if (!email.getText().equalsIgnoreCase(emailText)) {
                return false;
            }
        }
        return true;
    }

    public boolean isSysBackupEqualsTo(String sysBackupText) {
        for (WebElement sysBackup : frameSysBackup) {
            if (!sysBackup.getText().equalsIgnoreCase(sysBackupText)) {
                return false;
            }
        }
        return true;
    }

    public boolean isMailServerStatusEqualsTo(String mailServerStatusText) {
        for (WebElement mailServerStatus : frameMailServerConfig) {
            if (!mailServerStatus.getText().equalsIgnoreCase(mailServerStatusText)) {
                return false;
            }
        }
        return true;
    }

    public void firstLoginConfirmationClose() {
        waitUntilElementDisplayed(confirmButton, 2);
        confirmButton.click();
    }

    public FirstLoginPage ifApplianceNameCompare(String element){
        webDriver.findElement(By.cssSelector("#confirm_appliance_name")).getText();

        return this;
    }
}
