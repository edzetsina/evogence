package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LoginPage extends BasePage {
    @FindBy(id = "login")
    private WebElement emailField;
    @FindBy(id ="passwd")
    private WebElement passwordField;
    @FindBy(xpath = "//button[@name='LOGIN']")
    private WebElement loginButton;
    @FindBy(xpath = "//*[text()='Your login or password is incorrect!']")
    private WebElement invalidCredentialsErrorMsg;

    public LoginPage (WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
        waitUntilElementDisplayed(loginButton, 20);
    }

    public <T> T login(String userEmail, String userPassword) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        loginButton.click();
        if (isElementDisplayed(loginButton, 4)) {
            return (T) this;
        }
        else {

            return (T) PageFactory.initElements(webDriver, ContentLibraryPage.class);
        }
    }

    public boolean isInvalidCredentialMsg() {
        return invalidCredentialsErrorMsg.isDisplayed();
    }

    public String getErrorMsgText() {
        return  invalidCredentialsErrorMsg.getText();
    }

    public boolean isPageLoaded() {
        return emailField.isDisplayed();
    }
}
