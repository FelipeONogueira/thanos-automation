package br.com.wba.thanos.core.execution_report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import br.com.wba.thanos.core.utils.ProjectSettings;
import br.com.wba.thanos.core.utils.Utils;

public class TxtLog {

	private static PrintStream out;
	
	private TxtLog() {}
	
	static void startLog() {
		String path = ExecutionEvidences.getExecutionPath() + ProjectSettings.EXECUTION_LOG_NAME + "_"
				+ Utils.getActualDate().replace(".", "") + "_" + Utils.getActualTime().replace(":", "") + ProjectSettings.TXT_EXTENSION;
		FileOutputStream file;
		try {
			file = new FileOutputStream(path);
			out = new PrintStream(file);
			out.println("******************** Start Log ********************");
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo " + path + " não encontrado!");
			e.printStackTrace();
		}
	}
	
	static void log(String msg) {
		out.println(Utils.getActualDate() + " - " + Utils.getActualTime() + " - " + msg);
	}
}
