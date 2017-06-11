package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by edzet on 11.06.2017.
 */
public class MainPage extends BasePage {

    @FindBy(id = "menuBar")
    private WebElement menuBar;
    @FindBy(className = "logout")
    private WebElement logOutButton;

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
}
