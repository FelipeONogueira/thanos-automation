package br.com.wba.thanos.core.browser_factory;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class BrowserFactoryTest {

	WebDriver driver;
	
	@Before
	public void beforeMethod() {
		driver = DriverFactory.getDriver(DriverType.CHROME);
	}
	
	@After
	public void afterMethod() {
		DriverFactory.quitDriver();
	}
	
	@Test
	public void launchGloboTest() {
		driver.get("https://www.globo.com");
		assertEquals("globo.com - Absolutamente tudo sobre notícias, esportes e entretenimento", driver.getTitle());
	}
	
	@Test
	public void launchGoogleTest() {
		driver.get("https://www.google.com");
		assertEquals("Google", driver.getTitle());
	}
	
	@Test
	public void launchYahooTest() {
		driver.get("https://www.yahoo.com");
		assertEquals("Yahoo", driver.getTitle());
	}
}
