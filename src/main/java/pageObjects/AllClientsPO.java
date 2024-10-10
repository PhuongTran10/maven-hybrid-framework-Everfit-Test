package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.AllClientsPageUI;

public class AllClientsPO extends BasePage {
    private WebDriver driver;
    public AllClientsPO(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public boolean isClientsHeaderDisplayed() {
        waitForElementVisible(AllClientsPageUI.CLIENTS_HEADER);
        return isElementDisplayed(AllClientsPageUI.CLIENTS_HEADER);
    }

    public ClientPO clickToClientName(String clientName) {
        waitForElementVisible(AllClientsPageUI.CLIENT_NAME_BUTTON, clientName);
        clickOnElement(AllClientsPageUI.CLIENT_NAME_BUTTON, clientName);
        return PageGeneratorManager.getClientPage(driver);
    }
}
