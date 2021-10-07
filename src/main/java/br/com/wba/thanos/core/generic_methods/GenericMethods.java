package br.com.wba.thanos.core.generic_methods;

import java.awt.Robot;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import br.com.wba.thanos.core.browser_factory.DriverFactory;
import br.com.wba.thanos.core.execution_report.ExecutionReportManager;
import br.com.wba.thanos.core.page_object.BasePage;

public class GenericMethods {
	
	private WebDriver driver;
	private WebDriverWait wait;
	private Actions action;
	private Robot robot;
	
	public GenericMethods() {
		driver = DriverFactory.getDriver();
		wait = BasePage.getWait();
		action = new Actions(driver);
		
		try {
			robot = new Robot();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// ********************* DAILY METHODS *****************************
	// *****************************************************************
	
	public void openUrl(String url) {
		driver.get(url);
	}
	
	public void navigteTo(String url) {
		driver.navigate().to(url);
	}
	
	public void refresh() {
		driver.navigate().refresh();
	}
	
	public void validatePageTitle(String pageTitle) {
		wait.until(ExpectedConditions.titleIs(pageTitle));
	}
	
	public void clickElement(WebElement element) {
		element.click();
	}
	
	public void clear(WebElement element) {
		element.clear();
	}
	
	public void clickClickableElement(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}
	
	public void clickVisibleElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element)).click();
	}
	
	public void selectByVisibleText(WebElement combobox, String... value) {
		Select sel = new Select(combobox);
		for (int i = 0; i < value.length; i++) {
			sel.selectByVisibleText(value[i]);
		}
	}
	
	public boolean validateContains(String element, String message) {
		boolean assertive = false;
		System.out.println(element + "\n" + message);
		if (element.contains(message)) {
			assertive = true;
		}
		return assertive;
	}
	
	public void sendKeys(WebElement element, String value) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(value);
	}
	
	public String getText(WebElement element) {
		return element.getText();
	}
	
	public String getElementAttribute(WebElement element, String attribute) {
		return element.getAttribute(attribute);
	}
	
	public void mouseOverElementsAndClick(WebElement... element) {
		for (int i = 0; i < element.length; i++) {
			action.moveToElement(element[i]).perform();
		}
		getPrint();
		action.click().perform();
	}
	
	public void validateVisibilityOfElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void validateIvisibilityOfElement(WebElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void validateClickableElement(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void validatePresenceOfElement(WebElement element) {
		element.isDisplayed();
	}
	
	
	// ********************* SCREENSHOTS METHODS *****************************
	// *****************************************************************
	public void getPrint() {
		ExecutionReportManager.getEvidencePrint(driver);
	}
	
	public void getFullPagePrint() {
		ExecutionReportManager.getFullPageEvidencePrint(driver);
	}
	
	// ********************* LOGS METHODS *****************************
	// *****************************************************************
	
	public void log(String msg) {
		ExecutionReportManager.logInTxt(msg);
	}
	
	// ********************* JS METHODS *****************************
	// *****************************************************************
	
	public Object executeJavaScript(String cmd, Object... param) {
		return ((JavascriptExecutor) driver).executeScript(cmd,param);
	}
	
	public void highlight(WebElement element) {
		executeJavaScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}
	
	public WebElement findElementByXpathJS(String xpath) {
		return (WebElement) executeJavaScript("document.evaluate(arguments[0], document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue",xpath);
	}
	
	public void scrollDown() {
		executeJavaScript("scroll(0, 250);");
	}
	
	// ********************* WAIT METHODS *****************************
	// *****************************************************************
	public void sleep(Integer miliseconds) {
		try {
			TimeUnit.MILLISECONDS.sleep(miliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void waitElementByTextPresentInElement(WebElement element, String text) {
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}
	
	
	// ********************* ROBOT METHODS *****************************
	// *****************************************************************
	
	public void robotType(String str) {
		robot.delay(2000);
		for (int i = 0; i < str.length(); i++) {
			robotTypeCharacter("" + str.charAt(i));
		}
	}
	
	private void robotTypeCharacter(String str) {

		try {
			// System.out.println("letra? " + Character.isLetter(str.charAt(0)));
			// System.out.println("dígito? " + Character.isDigit(str.charAt(0)));
			if ((!Character.isLetter(str.charAt(0)) && !Character.isDigit(str.charAt(0))) || str.charAt(0) == 'ª'
					|| str.charAt(0) == 'º' || str.charAt(0) == 'ç' || str.charAt(0) == 'Ç' || str.charAt(0) == 'ã'
					|| str.charAt(0) == 'Ã' || str.charAt(0) == 'õ' || str.charAt(0) == 'Õ' || str.charAt(0) == 'á'
					|| str.charAt(0) == 'Á' || str.charAt(0) == 'à' || str.charAt(0) == 'À' || str.charAt(0) == 'é'
					|| str.charAt(0) == 'É' || str.charAt(0) == 'í' || str.charAt(0) == 'Í' || str.charAt(0) == 'ó'
					|| str.charAt(0) == 'Ó' || str.charAt(0) == 'ú' || str.charAt(0) == 'Ú' || str.charAt(0) == '	') {

				// System.out.println(str.charAt(0) + " é um caracter especial");
				typeSpecialCharacter(str.charAt(0));

			} else {
				boolean upperCase = Character.isUpperCase(str.charAt(0));
				String variableName = "VK_" + str.toUpperCase();
				Class<KeyEvent> c = KeyEvent.class;
				Field field;

				field = c.getField(variableName);

				int keyCode = field.getInt(null);

				if (upperCase) {
					robotType(KeyEvent.VK_SHIFT, keyCode);
				} else {
					robotType(keyCode);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void typeSpecialCharacter(char character) { // ABNT2 KeyBoard

		StringSelection selection;

		switch (character) {
		case '\'':
			robotType(KeyEvent.VK_QUOTE);
			break;
		case '"':
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_QUOTE);
			break;
		case '!':
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_1);
			break;
		case '@':
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_2);
			break;
		case '#':
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_3);
			break;
		case '$':
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_4);
			break;
		case '%':
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_5);
			break;
		case '¨':
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_6);
			break;
		case '&':
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_7);
			break;
		case '*':
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_8);
			break;
		case '(':
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_9);
			break;
		case ')':
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_0);
			break;
		case '-':
			robotType(KeyEvent.VK_MINUS);
			break;
		case '_':
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_MINUS);
			break;
		case '=':
			robotType(KeyEvent.VK_EQUALS);
			break;
		case '+':
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_EQUALS);
			break;
		case '´':
			robotType(KeyEvent.VK_DEAD_ACUTE);
			break;
		case '`':
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_DEAD_ACUTE);
			break;
		case '[':
			robotType(KeyEvent.VK_OPEN_BRACKET);
			break;
		case '{':
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_OPEN_BRACKET);
			break;
		case 'ª':
			robotType(KeyEvent.VK_CONTROL, KeyEvent.VK_ALT, KeyEvent.VK_OPEN_BRACKET);
			break;
		case '~':
			robotType(KeyEvent.VK_DEAD_TILDE);
			break;
		case '^':
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_DEAD_TILDE);
			break;
		case ']':
			robotType(KeyEvent.VK_CLOSE_BRACKET);
			break;
		case '}':
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_CLOSE_BRACKET);
			break;
		case 'º':
			robotType(KeyEvent.VK_CONTROL, KeyEvent.VK_ALT, KeyEvent.VK_CLOSE_BRACKET);
			break;
		case '\\':
			robotType(KeyEvent.VK_BACK_SLASH);
			break;
		case '|':
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_SLASH);
			break;
		case ',':
			robotType(KeyEvent.VK_COMMA);
			break;
		case '<':
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_COMMA);
			break;
		case '.':
			robotType(KeyEvent.VK_PERIOD);
			break;
		case '>':
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_PERIOD);
			break;
		case ';':
			robotType(KeyEvent.VK_SEMICOLON);
			break;
		case ':':
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_SEMICOLON);
			break;
		case '	':
			robotType(KeyEvent.VK_TAB);
			break;
		case '/':
			selection = new StringSelection(String.valueOf('/'));
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
			robotType(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
			break;
		case '?':
			selection = new StringSelection(String.valueOf('?'));
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
			robotType(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
			break;
		case '°':
			selection = new StringSelection(String.valueOf('°'));
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
			robotType(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
			break;
		case 'ç':
			selection = new StringSelection(String.valueOf('ç'));
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
			robotType(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
			break;
		case 'Ç':
			selection = new StringSelection(String.valueOf('Ç'));
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
			robotType(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
			break;
		case 'ã':
			selection = new StringSelection(String.valueOf('ã'));
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
			robotType(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
			break;
		case 'Ã':
			selection = new StringSelection(String.valueOf('Ã'));
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
			robotType(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
			break;
		case 'õ':
			selection = new StringSelection(String.valueOf('õ'));
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
			robotType(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
			break;
		case 'Õ':
			selection = new StringSelection(String.valueOf('Õ'));
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
			robotType(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
			break;
		case 'á':
			robotType(KeyEvent.VK_DEAD_ACUTE, KeyEvent.VK_A);
			break;
		case 'Á':
			robotType(KeyEvent.VK_DEAD_ACUTE);
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_A);
			break;
		case 'à':
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_DEAD_ACUTE);
			robotType(KeyEvent.VK_A);
			break;
		case 'À':
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_DEAD_ACUTE);
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_A);
			break;
		case 'é':
			robotType(KeyEvent.VK_DEAD_ACUTE, KeyEvent.VK_E);
			break;
		case 'É':
			robotType(KeyEvent.VK_DEAD_ACUTE);
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_E);
			break;
		case 'í':
			robotType(KeyEvent.VK_DEAD_ACUTE, KeyEvent.VK_I);
			break;
		case 'Í':
			robotType(KeyEvent.VK_DEAD_ACUTE);
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_I);
			break;
		case 'ó':
			robotType(KeyEvent.VK_DEAD_ACUTE, KeyEvent.VK_O);
			break;
		case 'Ó':
			robotType(KeyEvent.VK_DEAD_ACUTE);
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_O);
			break;
		case 'ú':
			robotType(KeyEvent.VK_DEAD_ACUTE, KeyEvent.VK_U);
			break;
		case 'Ú':
			robotType(KeyEvent.VK_DEAD_ACUTE);
			robotType(KeyEvent.VK_SHIFT, KeyEvent.VK_U);
			break;
		case ' ':
			robotType(KeyEvent.VK_SPACE);
			break;
		default:
			throw new IllegalArgumentException("Cannot type character " + character);
		}
	}

	public void robotType(int... keyCodes) {

		for (int i = 0; i < keyCodes.length; i++) {
			robot.keyPress(keyCodes[i]);
			robot.delay(500);
		}

		for (int i = keyCodes.length; i > 0; i--) {
			robot.keyRelease(keyCodes[i - 1]);
			robot.delay(500);
		}
	}

	public void robotTypeEnter() {
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	// ******************************************** Robot METHODS
	// **********************************************

	// ******************************************** Action METHODS
	// **********************************************
	public void action(Object obj, Keys action) {
		Actions a = new Actions(driver);
		if (obj instanceof String) {
			a.sendKeys(obj.toString()).build().perform();
		} else {
			a.sendKeys(action).build().perform();
		}
	}

	public void actionSendKey(WebElement element, String value) {
		action.sendKeys(element, value).build().perform();
	}

	public void actionSendKey(WebElement element, Keys key) {
		action.sendKeys(element, key);
	}
	
	public void actionSendEnter() {
		action.sendKeys(Keys.ENTER).build().perform();
	}
	// ******************************************** Action METHODS
	// **********************************************

	public void validationAssertEquals(String expected, WebElement actual) {
		Assert.assertEquals(expected, actual.getText());
	}

	public void validationAssertEqualsBetweenStrings(WebElement expected, WebElement actual) {
		Assert.assertEquals(expected.getText(), actual.getText());
	}
	
	public void validationAssertEqualsBetweenStringsWords(String expected, String actual) {
		Assert.assertEquals(expected, actual);
	}

	public void validationAssertStartsWith(Boolean expected, WebElement actual, String message) {
		Assert.assertEquals(expected, actual.getText().startsWith(message));
	}

	public void validationAssertIsNotEmpty(WebElement actual) {
		Assert.assertEquals(false, actual.getAttribute("value").isEmpty());
	}
	
	public String getAtttibuteValue(WebElement actual) {
		return actual.getAttribute("value").toString();
	}

	public void validationAssertEqualsValue(String expected, WebElement actual) {
		Assert.assertEquals(expected, actual.getAttribute("value"));
	}

	public void validationAssertContains(String expected, String actual) {
		if (actual.toUpperCase().contains(expected.toUpperCase())) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue("expected contains:<[" + expected + "]> but was:<[" + actual + "]>", false);
		}
	}

	public void validationAssertElementsContains(String expected, List<WebElement> elements) {
		for (int i = 0; i < elements.size(); i++) {
			if (this.getText(elements.get(i)).toUpperCase().contains(expected.toUpperCase())) {
				Assert.assertTrue(true);
				return;
			}
		}
		Assert.assertTrue("WebElement Array does not have:<[" + expected + "]>", false);
	}

	public void validationAssertIsNotEquals(String expected, WebElement actual) {
		Assert.assertNotEquals(expected, actual.getText());
	}
	
	public void validationAssertIsNotEqualsStrings(String expected, String actual) {
		Assert.assertNotEquals(expected, actual);
	}

	public void validationIsDisplayed(WebElement expected) {
		Assert.assertEquals(true, expected.isDisplayed());
	}

	public void validationIsEnable(WebElement expected) {
		Assert.assertEquals(true, expected.isEnabled());
	}
	

	
	public String getValue(WebElement actual, String attribute) {
		return actual.getAttribute(attribute);
	}

	public void validationAssertBoolean(Boolean expected, Boolean actual) {
		Assert.assertEquals(expected, actual);
	}
	
	public void validationAssertStrings(String expected, String actual) {
		Assert.assertEquals(expected, actual);
	}
	
	public void close() {
		driver.quit();	
	}
}
