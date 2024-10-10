package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginPageUI;

public class LoginPO extends BasePage {
    private WebDriver driver;
    public LoginPO(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public AllClientsPO loginAsUser(String email, String password) {
        enterToEmailTextbox(email);
        enterToPasswordTextbox(password);
        clickToLoginButton();
        return PageGeneratorManager.getAllClientsPage(driver);
    }

    private void enterToEmailTextbox(String email) {
        waitForElementVisible(LoginPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(LoginPageUI.EMAIL_TEXTBOX, email);
    }

    private void enterToPasswordTextbox(String password) {
        waitForElementVisible(LoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    private void clickToLoginButton() {
        waitForElementVisible(LoginPageUI.LOGIN_BUTTON);
        clickOnElement(LoginPageUI.LOGIN_BUTTON);
    }
}
