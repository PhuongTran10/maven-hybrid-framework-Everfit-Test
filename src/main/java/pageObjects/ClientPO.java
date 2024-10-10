package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.ClientPageUI;

public class ClientPO extends BasePage {
    private WebDriver driver;
    public ClientPO(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public boolean isTasksTabDisplayed() {
        waitForElementVisible(ClientPageUI.TASKS_TAB);
        return isElementDisplayed(ClientPageUI.TASKS_TAB);
    }

    public void clickOnTaskstab() {
        waitForElementClickable(ClientPageUI.TASKS_TAB);
        clickOnElement(ClientPageUI.TASKS_TAB);
    }

    public boolean isTaskCreationIconDisplayed() {
        waitForElementVisible(ClientPageUI.TODAY_BUTTON);
        return isElementDisplayed(ClientPageUI.TODAY_BUTTON);
    }

    public void clickOnCurDay() {
        waitForElementClickable(ClientPageUI.CUR_DAY_ON_CALENDAR);
        clickOnElement(ClientPageUI.CUR_DAY_ON_CALENDAR);
    }

    public void clickOnTaskCreationIconOnCurDay() {
        clickOnCurDay();
        waitForElementClickable(ClientPageUI.TASK_CREATION_ICON_ON_CUR_DAY);
        clickOnElement(ClientPageUI.TASK_CREATION_ICON_ON_CUR_DAY);
    }

    public boolean isTaskCreationPopupDisplayed() {
        waitForElementVisible(ClientPageUI.GENERAL_ICON);
        return isElementDisplayed(ClientPageUI.GENERAL_ICON);
    }

    public void clickOnGeneralIcon() {
        waitForElementClickable(ClientPageUI.GENERAL_ICON);
        clickOnElement(ClientPageUI.GENERAL_ICON);
    }

    public boolean isGeneralPopupDisplayed() {
        waitForElementVisible(ClientPageUI.TASK_NAME_TEXTBOX);
        return isElementDisplayed(ClientPageUI.TASK_NAME_TEXTBOX);
    }

    public void enterToTaskNameTextbox(String taskName) {
        waitForElementVisible(ClientPageUI.TASK_NAME_TEXTBOX);
        sendkeyToElementHaveClear(ClientPageUI.TASK_NAME_TEXTBOX, taskName);
    }

    public void clickOnMediaIcon() {
        waitForElementClickable(ClientPageUI.MEDIA_ICON);
        clickOnElement(ClientPageUI.MEDIA_ICON);
    }

    public boolean isUploadedFileSizeDisplayed() {
        waitForElementVisible(ClientPageUI.UPLOAD_FILE_SIZE);
        return isElementDisplayed(ClientPageUI.UPLOAD_FILE_SIZE);
    }

    public boolean isUploadFieldDisplayed() {
        waitForElementVisible(ClientPageUI.ADD_VIDEO_LINK_HYPERLINK);
        return isElementDisplayed(ClientPageUI.ADD_VIDEO_LINK_HYPERLINK);
    }

    public String getUploadedFileName() {
        waitForElementVisible(ClientPageUI.UPLOADED_FILE_NAME);
        return getElementText(ClientPageUI.UPLOADED_FILE_NAME);
    }

    public void clickOnSaveAndCloseButton() {
        waitForElementClickable(ClientPageUI.SAVE_AND_CLOSE_BUTTON);
        clickOnElement(ClientPageUI.SAVE_AND_CLOSE_BUTTON);
        waitForElementUndisplayed(ClientPageUI.TASK_NAME_TEXTBOX);
    }

    public void clickOnSaveButton() {
        waitForElementClickable(ClientPageUI.SAVE_BUTTON);
        clickOnElement(ClientPageUI.SAVE_BUTTON);
    }

    public boolean isTaskCreationPopupUndisplayed() {
        return isElementUndisplayed(ClientPageUI.TASK_NAME_TEXTBOX);
    }

    public boolean isTaskOnCurDayDisplayed(String taskName) {
        waitForElementVisible(ClientPageUI.TASK_TITLE_ON_CUR_DAY, taskName);
        return isElementDisplayed(ClientPageUI.TASK_TITLE_ON_CUR_DAY, taskName);
    }

    public void clickOnTaskOnCurDay(String taskName) {
        waitForElementClickable(ClientPageUI.TASK_TITLE_ON_CUR_DAY,taskName);
        clickOnElement(ClientPageUI.TASK_TITLE_ON_CUR_DAY,taskName);
    }

    public void clickOnMoreIcon(String newTaskName) {
        waitForElementClickable(ClientPageUI.MORE_ICON_IN_TASK_ON_CUR_DAY, newTaskName);
        clickOnElement(ClientPageUI.MORE_ICON_IN_TASK_ON_CUR_DAY, newTaskName);
    }

    public void clickOnDeleteItem() {
        waitForElementClickable(ClientPageUI.TASK_DELETE_ITEM);
        clickOnElement(ClientPageUI.TASK_DELETE_ITEM);
    }

    public boolean isDeleteConfirmPopupDisplayed() {
        waitForElementVisible(ClientPageUI.DELETE_POPUP_HEADER);
        return isElementDisplayed(ClientPageUI.DELETE_POPUP_HEADER);
    }

    public void clickOnOKButton() {
        waitForElementClickable(ClientPageUI.OK_BUTTON);
        clickOnElement(ClientPageUI.OK_BUTTON);
        waitForElementUndisplayed(ClientPageUI.DELETE_POPUP_HEADER);
    }

    public boolean isTaskOnCurDayUndisplayed(String newTaskName) {
        return isElementUndisplayed(ClientPageUI.TASK_TITLE_ON_CUR_DAY, newTaskName);
    }

    public String getTaskNameMessage() {
        waitForElementVisible(ClientPageUI.ERROR_MSG_TASK_NAME_TXT);
        return getElementText(ClientPageUI.ERROR_MSG_TASK_NAME_TXT);
    }

    public void clickOnAddVideoLinkLNK() {
        waitForElementClickable(ClientPageUI.ADD_VIDEO_LINK_HYPERLINK);
        clickOnElement(ClientPageUI.ADD_VIDEO_LINK_HYPERLINK);
    }

    public boolean isVideoLinkTextBoxDisplayed() {
        waitForElementVisible(ClientPageUI.VIDEO_LINK_TEXTBOX);
        return isElementDisplayed(ClientPageUI.VIDEO_LINK_TEXTBOX);
    }

    public void enterToVideoLinkTextBox(String link) {
        waitForElementVisible(ClientPageUI.VIDEO_LINK_TEXTBOX);
        sendkeyToElement(ClientPageUI.VIDEO_LINK_TEXTBOX, link);
    }

    public String getVideoLinkMessage() {
        waitForElementVisible(ClientPageUI.ERROR_MSG_VIDEO_LINK_TXT);
        return ClientPageUI.ERROR_MSG_VIDEO_LINK_TXT;
    }
}
