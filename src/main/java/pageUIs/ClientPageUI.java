package pageUIs;

public class ClientPageUI {
    public static final String TASKS_TAB = "xpath=//div[@class='client-menu-wrap']//a/span[text()='Tasks']";
    public static final String TODAY_BUTTON = "xpath=//button[contains(@class,'today-button')and text()='TODAY']";
    public static final String CUR_DAY_ON_CALENDAR = "xpath=//div[contains(@class,'day-label active')]";
    public static final String TASK_CREATION_ICON_ON_CUR_DAY = "xpath=//div[contains(@class,'day-label active')]/following-sibling::div/div[@class='add-new']";
    public static final String GENERAL_ICON = "xpath=//div[@class='content-addnew-task content']//div[@class='item']/p[text()='General']";
    public static final String TASK_NAME_TEXTBOX = "xpath=//div[contains(@class,'content-add-metric')]//label[text()='Task Name']/parent::div/following-sibling::input";
    public static final String MEDIA_ICON = "xpath=//label[text()='Attachment']/following-sibling::div//div[text()='Media']";
    public static final String ADD_VIDEO_LINK_HYPERLINK = "xpath=//button[contains(text(),'Add video link')]";
    public static final String UPLOAD_FILE_FIELD = "css=input[type='file']";
    public static final String UPLOADED_FILE_NAME = "xpath=//div[@class='file__name']";
    public static final String UPLOAD_FILE_SIZE = "css=div.download-file-container>div.file__size";
    public static final String SAVE_AND_CLOSE_BUTTON = "xpath=//button[text()='Save & Close']";
    public static final String SAVE_BUTTON = "xpath=//button[text()='Save']";
    public static final String TASK_TITLE_ON_CUR_DAY = "xpath=//div[contains(@class,'day-label active')]/ancestor::div[@class='calendar-cell__content']//div[@class='title' and text()='%s']";
    public static final String MORE_ICON_IN_TASK_ON_CUR_DAY = "xpath=//div[contains(@class,'day-label active')]/ancestor::div[@class='calendar-cell__content']//div[@class='title' and text()='%s']/parent::div/following-sibling::div/div[@class='dots']";
    public static final String TASK_DELETE_ITEM = "xpath=//li//div[contains(text(),'Delete')]";
    public static final String DELETE_POPUP_HEADER = "xpath=//p[@class='confirm-header-label' and text()='Delete task']";
    public static final String OK_BUTTON = "xpath=//button[text()='OK']";
    public static final String ERROR_MSG_TASK_NAME_TXT = "xpath=//div[contains(@class,'task--error-message')]";
    public static final String VIDEO_LINK_TEXTBOX = "xpath=//input[@placeholder='Youtube / Vimeo Link']";
    public static final String ERROR_MSG_VIDEO_LINK_TXT = "xpath=//input[@placeholder='Youtube / Vimeo Link']/parent::div/following-sibling::div";
}

