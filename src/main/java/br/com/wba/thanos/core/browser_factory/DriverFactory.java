package br.com.wba.thanos.core.browser_factory;

import java.io.FileReader;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import br.com.wba.thanos.core.utils.ProjectSettings;
import netscape.javascript.JSObject;

public class DriverFactory {

	private static WebDriver driver;
	
	private DriverFactory() {}
	
	public static WebDriver getDriver(DriverType type) {
		if (null == driver) {
			switch (type) {
			case CHROME: 
				try {
					getChromeDriver();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case FIREFOX:
				break;
			default:
				break;
			}
		}
		return driver;
	}
	
	public static WebDriver getDriver() {
		if (null == driver) {
			switch (ProjectSettings.DEFAULT_BROWSER) {
			case ProjectSettings.BROWSER_CHROME: 
				try {
					getChromeDriver();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case ProjectSettings.BROWSER_FIREFOX:
				break;
			default:
				try {
					getChromeDriver();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				break;
			}
		}
		return driver;
	}
	
	public static void quitDriver() {
		if(null != driver) {
			driver.quit();
			driver = null;
		}
	}
	
	public static void test() throws Exception {
		JSObject jsonObject = (JSObject) readJsonSimpleDemo("example.demo");
		System.out.println(jsonObject);
	}
	
	public static Object readJsonSimpleDemo(String fileName) throws Exception{
		FileReader reader = new FileReader(fileName);
		JSONParser jsonParser = new JSONParser();
		return jsonParser.parse(reader);
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	public class Search{
		String title;
		List<Book> rows;
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	public class Book{
		String title;
		String description;
		String imageHref;
	}
	
	private static void getChromeDriver() throws Exception{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		System.setProperty("webdriver.chrome.driver", ProjectSettings.CHROMEDRIVER_PATH);
		System.out.println();
		System.out.println(System.getProperty("webdriver.chrome.driver"));
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(capabilities);
		driver = new ChromeDriver(options);
	}
}
