package br.com.wba.thanos.core.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class Utils {

	private Utils() {
	}

	public static String getActualDate() {
		String var_fmt_date = "dd.MM.yyyy";
		String var_date;
		Date var_aux = new Date();
		SimpleDateFormat formata = new SimpleDateFormat(var_fmt_date);
		var_date = formata.format(var_aux);
		
		return var_date;
	}
	
	public static String getActualTime() {
		String var_fmt_time = "HH:mm:ss";
		String var_time;
		Date var_aux = new Date();
		SimpleDateFormat formata = new SimpleDateFormat(var_fmt_time);
		var_time = formata.format(var_aux);
		
		return var_time;
	}
	
	public static String getActualMinute() {
		String var_fmt_time = "HH:mm";
		String var_time;
		Date var_aux = new Date();
		SimpleDateFormat formata = new SimpleDateFormat(var_fmt_time);
		var_time = formata.format(var_aux);
		
		return var_time;
	}
	
	public static boolean isValidFile(String path) {
		File file = new File(path);
		return file.exists();
	}
	
	public String getData(){
		Date date = new Date();
		String formatDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
		System.out.println(formatDate);
		return formatDate;
	}
	
	public static String getOnlyNumbers(String string) {
		Pattern p = Pattern.compile("-?\\d+");
		Matcher m = p.matcher(string);
		while (m.find()) {
			string = m.group();
		}
		return string;
	}
	
	public static String getHour(String string) {
		Pattern p = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]");
		Matcher m = p.matcher(string);
		while (m.find()) {
			string = m.group();
		}
		return string;
	}
	
	public static LocalTime strToHour(String hour) {
		LocalTime t = LocalTime.parse(hour);
		return t;
	}
	
	public static String addCaracterHour(String hour) {
		return new StringBuilder(hour).insert(hour.length() - 2, ":").toString();
	}
	
	public static String addSeconds(String string) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		System.out.println(sdf.format(gc.getTime()));
		gc.add(Calendar.SECOND, 1);
		System.out.println(sdf.format(gc.getTime()));
		string = sdf.format(gc.getTime());
		return string;
	}
	
	public static boolean verifyPDFContent(String pathFile, String reqTextInPDF) throws IOException{
		PDDocument doc = PDDocument.load(new File(pathFile));
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String text = pdfStripper.getText(doc);
		doc.close();
		System.out.println(text);
		return text.contains(reqTextInPDF);
	}
	
	public static File lastFileModified(String dir) {
		File fl = new File(dir);
		File[] files = fl.listFiles();
		long lastMod = Long.MIN_VALUE;
		File choice = null;
		for (File file : files) {
			if (file.lastModified() > lastMod) {
				choice = file;
				lastMod = file.lastModified();
			}
		}
		return choice;
	}
}
