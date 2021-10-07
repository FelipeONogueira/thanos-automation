package br.com.wba.thanos.core.utils;


public class ProjectSettings {

//	FRAMEWORK
	public static final String BROWSER_CHROME = "Chrome";
	public static final String BROWSER_IE = "IE";
	public static final String BROWSER_FIREFOX = "Firefox";
	public static final String DEFAULT_BROWSER= BROWSER_CHROME;

//	EXTENSIONS
	public static final String JPEG_EXTENSION = ".jpeg";
	public static final String DOCX_EXTENSION = ".docx";
	public static final String TXT_EXTENSION = ".txt";
	public static final String XLSX_EXTENSION = ".xlsx";
	public static final String EXE_EXTENSION = ".exe";
	public static final String CSV_EXTENSION = ".csv";
	
//	UTIL CONSTANTS
	public static final String STATUS_PASSED = "PASSED";
	public static final String STATUS_FAILED = "FAILED";
	public static final String SIM = "SIM";
	public static final String NAO = "NÃO";
	public static final String CORRETO = "CORRETO";
	public static final String TESTES_AUTOMATIZADOS = "Testes automatizados";

//	FILE NAME
	public static final String EVIDENCE_DOC_NAME = "Doc de Evidências";
	public static final String EVIDENCE_PDF = "EVIDENCE_PDF";
	public static final String EXECUTION_LOG_NAME = "Execution Log";
	public static final String TEST_PRINT_NAME = "test";
	public static final String ENVIRONMENT_DATA_SHEET_NAME = "Environment Settings";
	public static final String EVIDENCE_TEMPLATE_WORD_DOC_NAME = "Template de Evidências";
	public static final String CHROMEDRIVER_NAME = "chromedriver";

//	FOLDERS
	public static final String DATA_SHEETS_FOLDER = "data_sheets\\";
	public static final String EVIDENCES_FOLDER = "evidences\\";
	public static final String SRC_FOLDER = "src\\";
	public static final String MAIN_FOLDER = "main\\";
	public static final String PRINTS_FOLDER = "prints\\";
	public static final String RESOURCES_FOLDER = "resources\\";
	public static final String WEBDRIVERS_FOLDER = "webdrivers\\";
	public static final String LIBS_FOLDER = "libs\\";
	public static final String TEMPLATE_DOCS_FOLDER = "template_docs\\";
	public static final String IMAGES_FOLDER = "images\\";
	
//	FILES
	public static final String ENVIRONMENT_DATA_SHEET = ENVIRONMENT_DATA_SHEET_NAME + XLSX_EXTENSION;
	public static final String CHROMEDRIVER_EXE = CHROMEDRIVER_NAME + EXE_EXTENSION;
	public static final String EVIDENCE_TEMPLATE_WORD_DOC = EVIDENCE_TEMPLATE_WORD_DOC_NAME + DOCX_EXTENSION;
	
// 	PATHS
	public static final String PROJECT_PATH = System.getProperty("user.dir") + "\\";
	public static final String EVIDENCE_PATH = PROJECT_PATH + EVIDENCES_FOLDER;
	public static final String DATA_SHEETS_PATH = PROJECT_PATH + DATA_SHEETS_FOLDER;
	public static final String RESOURCES_PATH = PROJECT_PATH + SRC_FOLDER + MAIN_FOLDER + RESOURCES_FOLDER;
	public static final String WEBDRIVERS_PATH = RESOURCES_PATH + WEBDRIVERS_FOLDER;
	public static final String IMAGES_PATH = RESOURCES_PATH + IMAGES_FOLDER;
	public static final String EVIDENCE_TEMPLATE_DOCS_PATH = RESOURCES_PATH + TEMPLATE_DOCS_FOLDER;
	public static final String EVIDENCE_TEMPLATE_WORD_DOC_PATH = EVIDENCE_TEMPLATE_DOCS_PATH + EVIDENCE_TEMPLATE_WORD_DOC;
	public static final String CHROMEDRIVER_PATH = WEBDRIVERS_PATH + CHROMEDRIVER_EXE;
	public static final String ENVIRONMENT_SHEET_PATH = DATA_SHEETS_PATH + ENVIRONMENT_DATA_SHEET;
	
//	ELEMENTS PROPERTIES
	public static final String ELEMENT_PROPERTY_VALUE = "value";
	
}
