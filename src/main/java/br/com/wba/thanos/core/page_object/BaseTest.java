package br.com.wba.thanos.core.page_object;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import br.com.wba.thanos.core.browser_factory.DriverType;
import br.com.wba.thanos.core.utils.ProjectSettings;

public class BaseTest {

	protected WebDriver driver;
	protected DriverType browser;
	protected static Map<String, String> testDataMap;
	protected static Map<String, Map<String, String>> environmentDataMap;
	protected String testDataSheetPath;
	protected String environmentDataSheetPath = ProjectSettings.ENVIRONMENT_SHEET_PATH;
	
}
