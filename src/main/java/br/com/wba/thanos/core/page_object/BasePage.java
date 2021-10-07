package br.com.wba.thanos.core.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.wba.thanos.core.browser_factory.DriverFactory;
import br.com.wba.thanos.core.generic_methods.GenericMethods;

public abstract class BasePage {
	
	protected WebDriver driver;
	protected static WebDriverWait wait;
	public GenericMethods genericMethods;
	
	public BasePage() {
		this.driver = DriverFactory.getDriver();
		wait = new WebDriverWait(driver, 120);
		genericMethods = new GenericMethods();
		PageFactory.initElements(driver, this);
	}
	
	public static WebDriverWait getWait() {
		return wait;
	}
}
