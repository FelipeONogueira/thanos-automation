package br.com.wba.thanos.core.sikuli;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import br.com.wba.thanos.core.utils.ProjectSettings;

public class Sikuli {

	private Sikuli() {
	}

	private static final String mapPath = ProjectSettings.IMAGES_PATH;
	private static final Screen screen = new Screen();
	private static final float similar = 0.70f;
	
	static Screen getScreen() {
		return screen;
	}
	
	static Pattern getPattern(String img) {
		return new Pattern(mapPath + img).similar(similar);
	}
	
	static Region getRegion(String img) throws FindFailed {
		return screen.find(getPattern(img));
	}
	
	static boolean validateImage(String img, double timeout) {
		while (timeout != 0) {
			try {
				screen.wait(getPattern(img), 1);
				System.out.println("Objeto representado pela imagem "+ mapPath + img +" encontrado!");
				getRegion(img).highlight(2);
				return true;
			} catch (FindFailed e) {
				timeout--;
			}
		}
		System.out.println("Objeto representado pela imagem "+ mapPath + img +" não encontrado!");
		return false;
	}
	
	static boolean validateImage(Pattern pattern, double timeSeconds) {
		try {
			screen.wait(pattern, timeSeconds);
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			System.out.println("Imagem não encontrada!");
			return false;
		}
	}
	
	static boolean validateImage(Pattern pattern) {
		try {
			screen.wait(pattern, 25);
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			System.out.println("Imagem não encontrada!");
			return false;
		}
	}
	
	static boolean validateImage(String img) {
		try {
			screen.wait(getPattern(img), 25);
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			System.out.println("Imagem não encontrada!");
			return false;
		}
	}
	
	static boolean validateImageLowTime(Pattern pattern) {
		try {
			screen.wait(pattern, 5);
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			System.out.println("Imagem não encontrada!");
			return false;
		}
	}
	
	static void callClipperMenu(String... args) throws FindFailed, InterruptedException {
		int count = 0;
		for(String arg : args) {
			screen.type(arg);
			count++;
			
			if(args.length != (count - 1)) {
				screen.wait(2);
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			getRegion("userProxy.png").highlight(3);
			getRegion("userProxy.png").right(100).highlight(3);
			getRegion("userProxy.png").right(100).click(3);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}
}
