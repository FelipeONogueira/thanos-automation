package br.com.wba.thanos.core.execution_report;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import br.com.wba.thanos.core.utils.ProjectSettings;

class SeleniumScreenShot {
	
	public static int printCount = 0;
	
	public SeleniumScreenShot() {}
	
	static int getEvidencePrint(WebDriver driver) {
		File scrFile;
		File evidenceFolder = new File(ExecutionEvidences.getExecutionEvidencePrintPath() + ProjectSettings.TEST_PRINT_NAME + printCount + ProjectSettings.JPEG_EXTENSION);
		try {
			scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, evidenceFolder);
			printCount = printCount + 1;
			System.out.println(printCount);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return printCount;
	}
	
	static void getFullPageEvidencePrint(WebDriver driver) {
		//JavascriptExecutor jexec
	}
}
