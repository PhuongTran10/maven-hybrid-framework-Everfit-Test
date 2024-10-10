package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.AllClientsPO;
import pageObjects.ClientPO;
import pageObjects.LoginPO;

public class PageGeneratorManager {
    public static LoginPO getLoginPage(WebDriver driver){
        return new LoginPO(driver);
    }

    public static AllClientsPO getAllClientsPage(WebDriver driver){
        return new AllClientsPO(driver);
    }

    public static ClientPO getClientPage(WebDriver driver){
        return new ClientPO(driver);
    }
}
