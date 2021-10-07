package br.com.wba.thanos.core.sikuli;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

public class SikuliManager {

	private SikuliManager() {}
	
	public static Screen getScreen() {
		return Sikuli.getScreen();
	}
	
	public static Pattern getPattern(String img) {
		return Sikuli.getPattern(img);
	}
	
	public static Region getRegion(String img) throws FindFailed{
		return Sikuli.getRegion(img);
	}
	
	public static boolean validateImage(String img, double timeout) {
		return Sikuli.validateImage(img, timeout);
	}
	
	public static boolean validateImage(Pattern pattern, double timeSeconds) {
		return Sikuli.validateImage(pattern, timeSeconds);
	}
	
	public static boolean validateImage(Pattern pattern) {
		return Sikuli.validateImage(pattern);
	}
	
	public static boolean validateImage(String img) {
		return Sikuli.validateImage(img);
	}
	
	public static boolean validateImageLowTime(Pattern pattern) {
		return Sikuli.validateImageLowTime(pattern);
	}
	
}
