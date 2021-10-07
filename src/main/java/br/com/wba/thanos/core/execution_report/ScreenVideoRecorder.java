package br.com.wba.thanos.core.execution_report;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_QUICKTIME;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.ENCODING_QUICKTIME_JPEG;
import static org.monte.media.VideoFormatKeys.QualityKey;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

public class ScreenVideoRecorder {

	private static boolean runningRecord;
	private static ScreenRecorder screenRecorder;
	
	private ScreenVideoRecorder() {}
	
	private static void configureScreenRecording(String evidencePath) {
		
		File file = new File(evidencePath);
		
		if (file.exists() && file.isDirectory() == false) {
			TxtLog.log("Destination '" + evidencePath + "' is not a directory");
			throw new IllegalArgumentException("Destination '" + evidencePath + "' is not a directory");
		}
		
		// Configure the dimensions of video recorder
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		
		Rectangle captureSize = new Rectangle(0, 0, width, height);
		
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		
		try {
			screenRecorder = new ScreenRecorder(gc, captureSize, new Format(MediaTypeKey, FormatKeys.MediaType.FILE, MimeTypeKey, MIME_QUICKTIME),
					new Format(MediaTypeKey, FormatKeys.MediaType.VIDEO,EncodingKey,
							ENCODING_QUICKTIME_JPEG, CompressorNameKey, ENCODING_QUICKTIME_JPEG,
							DepthKey, 24, FrameRateKey, Rational.valueOf(15), QualityKey, 0.5f, KeyFrameIntervalKey, 15 * 60),
					new Format(MediaTypeKey,FormatKeys.MediaType.VIDEO,EncodingKey,"black",FrameRateKey,
							Rational.valueOf(30)),
					null, file);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private static boolean isRecording() {
		return runningRecord;
	}
	
	private static void setRunningStatus(boolean status) {
		runningRecord = status;
	}
	
	static void startRecording(String evidencePath) throws IOException {
		
		if (isRecording() == false) {
			configureScreenRecording(evidencePath);
			screenRecorder.start();
			setRunningStatus(true);
		} else {
			stopRecording();
			startRecording(evidencePath);
		}
	}
	
	static void stopRecording() throws IOException {
		if(isRecording() == true) {
			screenRecorder.stop();
			setRunningStatus(false);
		}
	}
	
}
