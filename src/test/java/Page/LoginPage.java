package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by edzet on 01.06.2017.
 */
public class LoginPage extends BasePage {
    @FindBy(id = "login")
    private WebElement emailField;
    @FindBy(xpath ="passwd")
    private WebElement passwordField;
    @FindBy(className = "bnt")
    private WebElement loginButton;
    @FindBy(className = "error pt10 pb10")
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

        if (isElementDisplayed(loginButton, 3)) {
            return (T) this;
        }
        else {

            return (T) PageFactory.initElements(webDriver, MainPage.class);
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
