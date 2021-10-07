package br.com.wba.thanos.core.browser_factory;

import java.io.FileReader;

import org.json.simple.parser.JSONParser;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class LocalStorage {
	private JavascriptExecutor js;
	
	public LocalStorage(WebDriver webDriver) {
		this.js = (JavascriptExecutor) webDriver; 
	}
	
	public void removeItemFromLocalStorage(String item) {
		js.executeScript(String.format(
				"window.localStorage.removeItem('%s');", item));
	}
	
	public boolean isItemPresentInLocalStorage(String item) {
		return !(js.executeScript(String.format(
				"return window.localStorage.getItem('%s');", item)) == null);
	}
	
	public String getItemFromLocalStorage(String key) {
		return (String) js.executeScript(String.format(
				"return window.localStorage.getItem('%s');", key));
	}

	public static Object readJsonSimpleDemo(String fileName) throws Exception{
		FileReader reader = new FileReader(fileName);
		JSONParser jsonParser = new JSONParser();
		return jsonParser.parse(reader);
	}
	
	public String getKeyFromLocalStorage(int key) {
		return (String) js.executeScript(String.format(
				"return window.localStorage.key('%s');", key));
	}
	
	public Long getLocalStorageLenght()	{
		return (Long) js.executeScript("return window.localStorage.lenght;");
	}
	
//	public void setItemInLocalStorage() throws Exception {
//		JSONObject jsonObject = (JSONObject) readJsonSimpleDemo("***DIR***");
//	}
	
	public void clearLocalStorage() {
		js.executeScript(String.format("window.localStorage.clear();"));
	}
}
