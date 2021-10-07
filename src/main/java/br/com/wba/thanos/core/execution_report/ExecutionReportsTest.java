package br.com.wba.thanos.core.execution_report;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import br.com.wba.thanos.core.browser_factory.DriverFactory;
import br.com.wba.thanos.core.browser_factory.DriverType;

public class ExecutionReportsTest {
	
	WebDriver driver;
	
	@Before
	public void beforeTest() throws IOException, AWTException {
		driver = DriverFactory.getDriver(DriverType.CHROME);
		
		// Caminho padrão do projeto
		ExecutionReportManager.createExecutionPath();
		
		// Caminho diferente do padrão do projeto
		// ExecutionReportFacade
		
		ExecutionReportManager.startTxtLog();
		
		// Caminho de evidências padrão do projeto
		ExecutionReportManager.startRecording();
	}
	
	@After
	public void afterMethod() throws IOException, InvalidFormatException {
		ExecutionReportManager.stopRecording();
		DriverFactory.quitDriver();
		ExecutionReportManager.generateEvidenceDocument();
		ExecutionReportManager.logInTxt("*************** END OF REPORT **************");
	}
	
	@Test
	public void testExecutionReport() {
		System.out.println("ExecutionDateTime: "+ExecutionReportManager.getExecutionDateTime());
		System.out.println("ExecutionPath: "+ExecutionReportManager.getExecutionPath());
		System.out.println("ExecutionEvidencePrintPath: "+ExecutionReportManager.getExecutionEvidencePrintPath());
		driver.get("https://www.globo.com");
		
		for (int i = 0; i < 10; i++) {
			ExecutionReportManager.getEvidencePrint(driver);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		ExecutionReportManager.getFullPageEvidencePrint(driver);
		assertEquals("globo.com - Absolutamente tudo sobre notícias, esportes e entretenimento", driver.getTitle());
	}
	
}
