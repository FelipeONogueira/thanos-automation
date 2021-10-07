package br.com.wba.thanos.core.execution_report;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;

public class ExecutionReportManager {
	
	private static String testDataSheetPath;
	
	private ExecutionReportManager() {}
	
	public static String getTestDataSheetPath() {
		return testDataSheetPath;
	}
	
	private static void setTestDataSheetPath(String testDataSheetPath) {
		ExecutionReportManager.testDataSheetPath = testDataSheetPath;
	}
	
	public static void startReportAndCopyDataSheetsToExecutionFolder(boolean withVideoEvidence, String testDataSheetPath) throws IOException {
		ExecutionReportManager.setTestDataSheetPath(testDataSheetPath);
		ExecutionReportManager.createExecutionPath();
		ExecutionReportManager.startTxtLog();
		
		if(withVideoEvidence) {
			ExecutionReportManager.startRecording();
		}
	}
	
	public static void startReport(String path, boolean withVideoEvidence) throws IOException, AWTException {
		ExecutionReportManager.createExecutionPath(path);
		ExecutionReportManager.startTxtLog();
		
		if (withVideoEvidence) {
			ExecutionReportManager.startRecording(getExecutionPath());
		}
	}
	
	public static void closeReport() throws IOException, InvalidFormatException {
		ExecutionReportManager.startRecording();
		ExecutionReportManager.generateEvidenceDocument();
		ExecutionReportManager.logInTxt("*************** END OF REPORT **************");
	}
	
	public static void closeReport(Map<String, String> textDataMap, WebDriver driver) throws IOException, InvalidFormatException {
		ExecutionReportManager.stopRecording();
		int x = getEvidencePrint(driver);
		ExecutionReportManager.generateEvidenceDocument();
		//String executionPathDataSheet = getExe
		ExecutionReportManager.logInTxt("*************** END OF REPORT **************");
	}
	
	// *************************** ExecutionEvidencesManager *******************************
	public static String getExecutionDateTime() {
		return ExecutionEvidences.getExecutionDateTime();
	}
	
	public static String getExecutionPath() {
		return ExecutionEvidences.getExecutionPath();
	}
	
	public static String getExecutionEvidencePrintPath() {
		return ExecutionEvidences.getExecutionEvidencePrintPath();
	}
	
	public static void createExecutionPath() {
		ExecutionEvidences.createExecutionPath();
	}
	
	public static void createExecutionPath(String path) {
		ExecutionEvidences.createExecutionPath(path);
	}
	
	public static void generateEvidenceDocument() throws InvalidFormatException, IOException {
		ExecutionEvidences.generateEvidenceDocument();
	}
	// *************************** ExecutionEvidencesManager *******************************
	
	// *************************** SeleniumVideoRecorder *******************************
	public static void startRecording() throws IOException {
		ScreenVideoRecorder.startRecording(getExecutionPath());
	}
	
	public static void startRecording(String evidencePath) throws IOException, AWTException {
		ScreenVideoRecorder.startRecording(evidencePath);
	}
	
	public static void stopRecording() throws IOException {
		ScreenVideoRecorder.stopRecording();
	}
	// *************************** SeleniumVideoRecorder *******************************
	
	// *************************** SeleniumScreenShot *******************************
	public static int getEvidencePrint(WebDriver driver) {
		return SeleniumScreenShot.getEvidencePrint(driver);
	}
	
	public static void getFullPageEvidencePrint(WebDriver driver) {
		SeleniumScreenShot.getFullPageEvidencePrint(driver);
	}
	// *************************** SeleniumScreenShot *******************************
	
	
	
	// *************************** TxtLog *******************************
	public static void startTxtLog() {
		TxtLog.startLog();
	}
	
	public static void logInTxt(String msg) {
		TxtLog.log(msg);
	}
	// *************************** TxtLog *******************************
	
}
