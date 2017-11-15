package com.nowcoder.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UploadUtil {

	public static boolean validateVedio(String fileName) {
		Pattern pattern = Pattern.compile(".+(.swf|.avi|.flv|.mpg|.rm|.mov|.wav|.asf|.3gp|.mkv|.rmvb)$");
		Matcher matcher = pattern.matcher(fileName);
		boolean result = matcher.matches();
		return result;
	}
	
	public static boolean validateImage(String fileName){
		Pattern pattern = Pattern.compile(".+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$");
		Matcher matcher = pattern.matcher(fileName);
		boolean result = matcher.matches();
		return result;
	}
	
}
