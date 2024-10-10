package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BasePage {
	private WebDriver driver;
	public long longTimeout = GlobalConstants.LONG_TIMEOUT;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public BasePage() {

	}

	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	public void openURL(String pageUrl) {
		driver.get(pageUrl);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode() {
		return driver.getPageSource();
	}

	public void backToPage() {
		driver.navigate().back();
	}

	public void forwardToPage() {
		driver.navigate().forward();
	}

	public void refreshCurrentPage() {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresence() {
		return new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert() {
		waitForAlertPresence().accept();
	}

	public void cancelAlert() {
		waitForAlertPresence().dismiss();
	}

	public String getTextAlert() {
		return waitForAlertPresence().getText();
	}

	public void sendkeyToAlert(String textValue) {
		waitForAlertPresence().sendKeys(textValue);
	}

	public void switchToWindowByID(String windowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	public void switchToWindowByTitle(String tabTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(tabTitle)) {
				break;
			}
		}
	}

	public void closeAllTabWithoutParent(String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);
		}
	}

	public By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=") || locatorType.startsWith("CLASS=") || locatorType.startsWith("Class=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=")) {
			by = By.xpath(locatorType.substring(6));
		} else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=") || locatorType.startsWith("Name=")) {
			by = By.name(locatorType.substring(5));
		} else {
			throw new RuntimeException("Locator type is not supported!");
		}
		return by;
	}

	public String getDynamicLocator(String dynamicLocator, String... dynamicValues) {
		return String.format(dynamicLocator, (Object[]) dynamicValues);
	}

	public WebElement getWebElement(String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	public WebElement getDynamicWebElement(String locatorType, String... dynamicValues) {
		return driver.findElement(getByLocator(getDynamicLocator(locatorType, dynamicValues)));
	}

	public List<WebElement> getListWebElement(String locatorType, String... dynamicValues) {
		return driver.findElements(getByLocator(getDynamicLocator(locatorType, dynamicValues)));
	}

	public void clickOnElement(String locatorType, String... dynamicValues) {
		getWebElement(getDynamicLocator(locatorType, dynamicValues)).click();
	}

	public void sendkeyToElementHaveClear(String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(getDynamicLocator(locatorType, dynamicValues));
		String os = GlobalConstants.OS_NAME.toLowerCase();
		System.out.println(os);
		if (os.contains("mac")) {
			element.sendKeys(Keys.chord(Keys.COMMAND, "a"));
			element.sendKeys(Keys.DELETE);
		} else {
			element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		}
		element.sendKeys(textValue);
	}

	public void sendkeyToElement(String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(getDynamicLocator(locatorType, dynamicValues));
		element.sendKeys(textValue);
	}

	public void clearAllTextInElement(String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(getDynamicLocator(locatorType, dynamicValues));
		String os = GlobalConstants.OS_NAME.toLowerCase();
		if (os.contains("mac")) {
			element.sendKeys(Keys.chord(Keys.COMMAND, "a"));
			element.sendKeys(Keys.DELETE);
		} else {
			element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
		}
	}

	public void selectItemInDefaultDropdown(String locatorType, String textItem, String... dynamicValues) {
		new Select(getWebElement(getDynamicLocator(locatorType, dynamicValues))).selectByVisibleText(textItem);
	}

	public String getSelectedItemDefaultDropdown(String locatorType, String... dynamicValues) {
		return new Select(getWebElement(getDynamicLocator(locatorType, dynamicValues))).getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(String locatorType) {
		return new Select(getWebElement(locatorType)).isMultiple();
	}


	/**
	 * Select item in dropdown list with @param parentLocator only get 1 @param dynamicValues passed in
	 * @param parentLocator
	 * @param childLocator
	 * @param expectedItem
	 * @param dynamicValues
	 */
	public void selectItemInCustomDropdownList(String parentLocator, String childLocator, String expectedItem, String... dynamicValues) {
		waitForElementVisible(parentLocator, dynamicValues);
		getWebElement(getDynamicLocator(parentLocator, dynamicValues)).click();
		sleepInSecond(1);
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}

	/**
	 * Select item in dropdown list with @param parentLocator only get 1 @param dynamicValues passed in
	 * @param parentLocator
	 * @param dropdownSearchLocator
	 * @param childLocator
	 * @param expectedItem
	 * @param dynamicValues
	 */
	public void enterAndSelectItemInDropDown(String parentLocator, String dropdownSearchLocator, String childLocator, String expectedItem, String... dynamicValues) {
		waitForElementVisible(parentLocator, dynamicValues);
		getWebElement(getDynamicLocator(parentLocator, dynamicValues)).click();
		sleepInSecond(2);
		waitForElementVisible(dropdownSearchLocator);
		getWebElement(dropdownSearchLocator).clear();
		getWebElement(dropdownSearchLocator).sendKeys(expectedItem);
		sleepInSecond(1);
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
		for (WebElement tempItem : allItems) {
			if (tempItem.getText().trim().equals(expectedItem)) {
				sleepInSecond(1);
				tempItem.click();
				break;
			}
		}
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getElementAttribute(String locatorType, String attributeName, String... dynamicValues) {
		return getWebElement(getDynamicLocator(locatorType, dynamicValues)).getAttribute(attributeName);
	}

	public String getElementText(String locatorType, String... dynamicValues) {
		return getWebElement(getDynamicLocator(locatorType, dynamicValues)).getText();
	}

	public String getElementCssValue(String locatorType, String propertyName, String... dynamicValues) {
		return getWebElement(getDynamicLocator(locatorType, dynamicValues)).getCssValue(propertyName);
	}

	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public int getSizeElement(String locatorType, String... dynamicValues) {
		return getListWebElement(getDynamicLocator(locatorType, dynamicValues)).size();
	}

	public void checkToDefaultRadioBox(String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(getDynamicLocator(locatorType, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void unCheckToDefaultCheckbox(String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(getDynamicLocator(locatorType, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}

	public void checkToCustomCheckbox(String locatorType, String... dynamicValues) {
		waitForElementVisible(locatorType, dynamicValues);
		sleepInSecond(2);
		String checkboxStatus = getElementAttribute(locatorType, "aria-checked", dynamicValues);
		if (checkboxStatus.equals("false")) {
			waitForElementVisible(locatorType, dynamicValues);
			clickOnElementByJS(locatorType, dynamicValues);
			waitForElementContainsAttribute(locatorType, "aria-checked", "true", dynamicValues);
		}
	}

	public void uncheckToCustomnCheckbox(String locatorType, String... dynamicValues) {
		waitForElementVisible(locatorType, dynamicValues);
		String checkboxStatus = getElementAttribute(locatorType, "aria-checked", dynamicValues);
		sleepInSecond(2);
		if (checkboxStatus.equals("true")) {
			waitForElementVisible(locatorType, dynamicValues);
			clickOnElementByJS(locatorType, dynamicValues);
			waitForElementContainsAttribute(locatorType, "aria-checked", "false", dynamicValues);
		}
	}

	public boolean isCheckboxChecked(String locatorType, String... dynamicValues) {
		String checkboxStatus = getElementAttribute(locatorType, "aria-checked", dynamicValues);
		waitForElementContainsAttribute(locatorType, "aria-checked", checkboxStatus, dynamicValues);
		if (checkboxStatus.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isElementDisplayed(String locatorType, String... dynamicValues) {
		return getWebElement(getDynamicLocator(locatorType, dynamicValues)).isDisplayed();
	}

	public boolean isElementUndisplayed(String locatorType, String... dynamicValues) {
		setImplicitWait(GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = getListWebElement(getDynamicLocator(locatorType, dynamicValues));
		setImplicitWait(GlobalConstants.LONG_TIMEOUT);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public void setImplicitWait(long TimeOut) {
		driver.manage().timeouts().implicitlyWait(TimeOut, TimeUnit.SECONDS);
	}

	public boolean isElementEnabled(String locatorType, String... dynamicValues) {
		return getWebElement(getDynamicLocator(locatorType, dynamicValues)).isEnabled();
	}

	public boolean isElementSelected( String locatorType, String... dynamicValues) {
		return getWebElement(getDynamicLocator(locatorType, dynamicValues)).isSelected();
	}

	public void waitForFrameAvailableAndSwitch( String locatorType, String... dynamicValues) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
	}

	public void switchToFrameIframe( String locatorType, String... dynamicValues) {
		driver.switchTo().frame(getWebElement(getDynamicLocator(locatorType, dynamicValues)));
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement( String locatorType, String... dynamicValues) {
		new Actions(driver).moveToElement(getWebElement(getDynamicLocator(locatorType, dynamicValues))).perform();
	}

	public void doubleClickToElement( String locatorType, String... dynamicValues) {
		new Actions(driver).doubleClick(getWebElement(getDynamicLocator(locatorType, dynamicValues))).perform();
	}

	public void sendKeyBoardToElement( String locatorType, Keys key, String... dynamicValues) {
		new Actions(driver).sendKeys(getWebElement(getDynamicLocator(locatorType, dynamicValues)), key).perform();
	}

	public void scrollToBottomPage() {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void clickOnElementByJS(String locatorType, String... dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(getDynamicLocator(locatorType, dynamicValues)));
	}

	public void enterToElementByJS( String locatorType, String dataToInput, String... dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', arguments[1]);", getWebElement(getDynamicLocator(locatorType, dynamicValues)), dataToInput);
	}

	public void scrollToUnderElement( String locatorType, String... dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(getDynamicLocator(locatorType, dynamicValues)));
	}

	public void scrollToAboveElement(String locatorType, String... dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(getDynamicLocator(locatorType, dynamicValues)));
	}

	public void scrollToElementIfNeeded(String locatorType, String... dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViewIfNeeded(false);", getWebElement(getDynamicLocator(locatorType, dynamicValues)));
	}

	public void removeAttributeInDOM(String locatorType, String attributeRemove, String... dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(getDynamicLocator(locatorType, dynamicValues)));
	}

	public void waitForElementUndisplayed(String locatorType, String... dynamicValues) {
		setImplicitWait(GlobalConstants.SHORT_TIMEOUT);
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
		setImplicitWait(GlobalConstants.LONG_TIMEOUT);
	}

	public void waitForElementVisible( String locatorType, String... dynamicValues) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
	}

	public void waitForListElementVisible(String locatorType, String... dynamicValues) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
	}

	public List<WebElement> getListElementPresence(String locatorType, String... dynamicValues) {
		return new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
	}

	public List<WebElement> getListElementVisible(String locatorType, String... dynamicValues) {
		return new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
	}

	public void waitForElementInvisible(String locatorType, String... dynamicValues) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
	}

	public void waitForListElementInvisible(String locatorType, String... dynamicValues) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(getDynamicLocator(locatorType, dynamicValues))));
	}

	public void waitForElementContainsAttribute(String locatorType, String attribute, String value, String... dynamicValues) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.attributeContains(getWebElement(getDynamicLocator(locatorType, dynamicValues)), attribute, value));
	}

	public void waitForElementRefresh(String locatorType, String... dynamicValues) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicLocator(locatorType, dynamicValues)))));
	}

	public void waitForElementClickable(String locatorType, String... dynamicValues) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
	}

	public void waitForNumberOfElements(String locatorType, int quantity, String... dynamicValues) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.numberOfElementsToBe(getByLocator(getDynamicLocator(locatorType, dynamicValues)), quantity));
	}

	public void waitForNumberOfElementsLessThan(String locatorType, int quantity, String... dynamicValues) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.numberOfElementsToBeLessThan(getByLocator(getDynamicLocator(locatorType, dynamicValues)), quantity));
	}

	public void waitForNumberOfElementsMoreThan(String locatorType, int quantity, String... dynamicValues) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.numberOfElementsToBeMoreThan(getByLocator(getDynamicLocator(locatorType, dynamicValues)), quantity));
	}

	public void waitForTextDisplay(String locatorType, String text, String... dynamicValues) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.textToBe(getByLocator(getDynamicLocator(locatorType, dynamicValues)), text));
	}

	public void uploadFiles(String locatorType, String... fileNames) {
		String filePath = GlobalConstants.UPLOAD_FILE;
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(locatorType).sendKeys(fullFileName);
	}
}
