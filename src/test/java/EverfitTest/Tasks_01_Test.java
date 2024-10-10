package EverfitTest;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.AllClientsPO;
import pageObjects.ClientPO;
import pageObjects.LoginPO;
import pageUIs.ClientPageUI;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

public class Tasks_01_Test extends BaseTest {
    private WebDriver driver;
    LoginPO loginPage;
    ClientPO clientPage;
    AllClientsPO allClientsPage;
    String email, password, clientName, taskName, fileName, newTaskName, taskNameMSG, link, videoLinkMSG;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void beforeClass(@Optional("chrome")String browserName) {
        driver = getBrowserDriver(browserName, GlobalConstants.DEV_URL);
        loginPage = PageGeneratorManager.getLoginPage(driver);

        email = "hanhle+test@everfit.io";
        password = "Test2024";
        clientName = "Client Test";
        taskName = "Yoga "+generateFakeNumber();
        newTaskName = "Swimming "+generateFakeNumber();
        fileName = "beach.jpg";
        taskNameMSG = "This is required field";
        link = "https://translate.google.com/?hl=vi&sl=vi&tl=en&op=translate";
        videoLinkMSG = "Invalid data";

        allClientsPage = loginPage.loginAsUser(email, password);
        Assert.assertTrue(allClientsPage.isClientsHeaderDisplayed());
        clientPage = allClientsPage.clickToClientName(clientName);
        Assert.assertTrue(clientPage.isTasksTabDisplayed());
        clientPage.clickOnTaskstab();
        Assert.assertTrue(clientPage.isTaskCreationIconDisplayed());
    }
    @Test()
    public void Tasks_01_Create_General_Task(Method method) {
        ExtentTestManager.startTest(method.getName(), "Create general task on current day");
        ExtentTestManager.getTest().log(Status.INFO, "Create general task - Step 01: Click on task creation icon on current day");
        clientPage.clickOnTaskCreationIconOnCurDay();
        Assert.assertTrue(clientPage.isTaskCreationPopupDisplayed());
        ExtentTestManager.getTest().log(Status.INFO, "Create general task - Step 02: Click on 'General' icon");
        clientPage.clickOnGeneralIcon();
        Assert.assertTrue(clientPage.isGeneralPopupDisplayed());
        ExtentTestManager.getTest().log(Status.INFO, "Create general task - Step 03: Enter valid data into 'TASK NAME' text box");
        clientPage.enterToTaskNameTextbox(taskName);
        ExtentTestManager.getTest().log(Status.INFO, "Create general task - Step 04: Click on 'Media' icon'");
        clientPage.clickOnMediaIcon();
        Assert.assertTrue(clientPage.isUploadFieldDisplayed());
        ExtentTestManager.getTest().log(Status.INFO, "Create general task - Step 05: Upload a media file");
        clientPage.uploadFiles(ClientPageUI.UPLOAD_FILE_FIELD, fileName);
        Assert.assertTrue(clientPage.isUploadedFileSizeDisplayed());
        Assert.assertEquals(clientPage.getUploadedFileName(), fileName);
        ExtentTestManager.getTest().log(Status.INFO, "Create general task - Step 06: Click on 'Save & Close' icon");
        clientPage.clickOnSaveAndCloseButton();
        ExtentTestManager.getTest().log(Status.INFO, "Create general task - Step 07: Verify newly created task is displayed on set date ");
        Assert.assertTrue(clientPage.isTaskOnCurDayDisplayed(taskName));
    }

    @Test()
    public void Tasks_02_Edit_General_Task(Method method) {
        ExtentTestManager.startTest(method.getName(), "Edit general task on current day");
        ExtentTestManager.getTest().log(Status.INFO, "Edit general task - Step 01: Click on task on current day");
        clientPage.clickOnTaskOnCurDay(taskName);
        Assert.assertTrue(clientPage.isGeneralPopupDisplayed());
        ExtentTestManager.getTest().log(Status.INFO, "Edit general task - Step 02: Enter new valid data into 'TASK NAME' text box");
        clientPage.enterToTaskNameTextbox(newTaskName);
        ExtentTestManager.getTest().log(Status.INFO, "Edit general task - Step 03: Click on 'Save & Close' icon");
        clientPage.clickOnSaveAndCloseButton();
       ExtentTestManager.getTest().log(Status.INFO, "Edit general task - Step 04: Verify newly created task is displayed on set date ");
        Assert.assertTrue(clientPage.isTaskOnCurDayDisplayed(newTaskName));
    }

    @Test()
    public void Tasks_03_Delete_General_Task(Method method) {
        ExtentTestManager.startTest(method.getName(), "Delete general task on current day");
        ExtentTestManager.getTest().log(Status.INFO, "Delete general task - Step 01: Click on more icon in task");
        clientPage.clickOnMoreIcon(newTaskName);
        ExtentTestManager.getTest().log(Status.INFO, "Delete general task - Step 02: Click on 'Delete' item");
        clientPage.clickOnDeleteItem();
        Assert.assertTrue(clientPage.isDeleteConfirmPopupDisplayed());
        ExtentTestManager.getTest().log(Status.INFO, "Delete general task - Step 03: Click on 'OK' button");
        clientPage.clickOnOKButton();
        ExtentTestManager.getTest().log(Status.INFO, "Delete general task - Step 04: Verify task is deleted on set date ");
        Assert.assertTrue(clientPage.isTaskOnCurDayUndisplayed(newTaskName));
    }

    @Test()
    public void Tasks_04_Verify_Message_In_Task_Name_TextBox(Method method) {
        ExtentTestManager.startTest(method.getName(), "Verify message in 'TASK NAME' text box when no data is entered");
        ExtentTestManager.getTest().log(Status.INFO, "Verify message in 'TASK NAME' text box - Step 01: Click on task creation icon on current day");
        clientPage.clickOnTaskCreationIconOnCurDay();
        Assert.assertTrue(clientPage.isTaskCreationPopupDisplayed());
        ExtentTestManager.getTest().log(Status.INFO, "Verify message in 'TASK NAME' text box - Step 02: Click on 'General' icon");
        clientPage.clickOnGeneralIcon();
        Assert.assertTrue(clientPage.isGeneralPopupDisplayed());
        ExtentTestManager.getTest().log(Status.INFO, "Verify message in 'TASK NAME' text box - Step 03: Verify message in 'TASK NAME' text box when no data is entered");
        clientPage.clickOnSaveButton();
        Assert.assertEquals(clientPage.getTaskNameMessage(), taskNameMSG);
    }

    @Test()
    public void Tasks_05_Verify_Message_In_Video_Link_TextBox(Method method) {
        ExtentTestManager.startTest(method.getName(), "Verify message in 'Youtube / Vimeo Link' text box when no data is entered");
        ExtentTestManager.getTest().log(Status.INFO, "Verify message in 'Youtube / Vimeo Link' text box - Step 01: Click on 'Media' icon'");
        clientPage.clickOnMediaIcon();
        Assert.assertTrue(clientPage.isUploadFieldDisplayed());
        ExtentTestManager.getTest().log(Status.INFO, "Verify message in 'Youtube / Vimeo Link' text box - Step 02: Click on 'Add video link' hyperlink");
        clientPage.clickOnAddVideoLinkLNK();
        Assert.assertTrue(clientPage.isVideoLinkTextBoxDisplayed());
        ExtentTestManager.getTest().log(Status.INFO, "Verify message in 'Youtube / Vimeo Link' text box - Step 03: Verify message in 'Youtube / Vimeo Link' text box when no data is entered");
        clientPage.enterToVideoLinkTextBox(link);
        Assert.assertEquals(clientPage.getVideoLinkMessage(), videoLinkMSG);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }
}
