package br.com.wba.thanos.core.execution_report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import br.com.wba.thanos.core.utils.ProjectSettings;
import br.com.wba.thanos.core.utils.Utils;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;

public class ExecutionEvidences {

	private static String executionPath;
	private static String executionEvidencePrintPath;
	private static String executionDateTime;
	
	private ExecutionEvidences() {}

	static String getExecutionDateTime() {
		return executionDateTime;
	}
	
	private static void setExecutionDateTime() {
		executionDateTime = Utils.getActualDate().replace(".", "") + "_" + Utils.getActualTime().replace(":", "");
	}
	
	static String getExecutionPath() {
		return executionPath;
	}
	
	private static void setExecutionPath(String path) {
		executionPath = path;
	}
	
	private static void setExecutionPath() {
		String path = ProjectSettings.EVIDENCE_PATH;
		setExecutionDateTime();
		String path_aux = path + "Exec_" + getExecutionDateTime();
		setExecutionPath(path_aux + "\\");
	}
	
	public static String getExecutionEvidencePrintPath() {
		return executionEvidencePrintPath;
	}
	
	private static void setExecutionEvidencePrintPath() {
		executionEvidencePrintPath = getExecutionPath() + ProjectSettings.PRINTS_FOLDER;
	}
	
	static void createExecutionPath(String path) {
		setExecutionDateTime();
		String path_aux = path + "\\Exec_" + getExecutionDateTime() + "\\";
		File dir = new File(path_aux);
		dir.mkdirs();
		setExecutionPath(path_aux + "\\");
		setExecutionEvidencePrintPath();
	}
	
	static void createExecutionPath() {
		setExecutionPath();
		File dir = new File(getExecutionPath());
		dir.mkdirs();
		setExecutionEvidencePrintPath();
		setExecutionDateTime();
	}
	
	static void generateEvidenceDocument() throws InvalidFormatException, IOException {
		String x;
		String y;
		SeleniumScreenShot.printCount=0;
		if (new File(getExecutionEvidencePrintPath()).exists()) {
			int size = new File(getExecutionEvidencePrintPath()).list().length;
			InputStream is = new FileInputStream(ProjectSettings.EVIDENCE_TEMPLATE_WORD_DOC_PATH);
			FileOutputStream out;
			XWPFDocument docx = new XWPFDocument(is);
			XWPFParagraph par = docx.createParagraph();
			XWPFRun run = par.createRun();
			for (int i = 0; i < size; i++) {
				InputStream pic = new FileInputStream(getExecutionEvidencePrintPath() + ProjectSettings.TEST_PRINT_NAME + i + ProjectSettings.JPEG_EXTENSION);
				run.addPicture(pic, Document.PICTURE_TYPE_JPEG, ProjectSettings.TEST_PRINT_NAME + i,Units.toEMU(500), Units.toEMU(200));
				run.addBreak();
				pic.close();
			}
			x = getExecutionPath() + ProjectSettings.EVIDENCE_DOC_NAME + "_" + getExecutionDateTime() + ProjectSettings.DOCX_EXTENSION;
			y = getExecutionPath() + "\\prints";
			out = new FileOutputStream(x);
			System.out.println(x);
			System.out.println(y);
			docx.write(out);
			docx.close();
			out.close();
		}
	}
	
	static void copyFileToDirectory(String srcFile, String destDir) {
		try {
			FileUtils.copyFileToDirectory(new File(srcFile), new File(destDir));
		} catch (IOException e) {
			e.printStackTrace();
//			ExecutionReportManager.log
		}
	}
	
	public void dOCXToPDFConverterSampleMin(String fileWord, String filePdf) throws IOException{
		InputStream in = new FileInputStream(new File(fileWord));
		XWPFDocument document = new XWPFDocument(in);
		PdfOptions options = PdfOptions.create();
		OutputStream out = new FileOutputStream(new File(filePdf));
		PdfConverter.getInstance().convert(document, out, options);
		
		document.close();
		out.close();
	}
}
