package com.medassets.browser.service;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.RenderingEngine;
import eu.bitwalker.useragentutils.UserAgent;

public class BroswerUtils {

	public static UserAgent parseUserAgent (String ua) {
		UserAgent userAgent = UserAgent.parseUserAgentString(ua);
//		 userAgent.getOperatingSystem();
//		 Browser browser = userAgent.getBrowser();
//		 System.out.println ("browser: " + browser.getName());
//		 RenderingEngine engine = browser.getRenderingEngine();
		 //System.out.println ("rendering engine: " );
		 return userAgent;
	}
	
	public static String getTridentVersion (String ua) {
		int start = ua.indexOf("(");
		String temp = ua.substring(start);
		String [] props = temp.split(";");
		String tridentString = null;
		// look for Trident
		for (int i=0; i < props.length; i++) {
			if (props[i].contains("Trident") ){
				tridentString = props[i].trim();
				break;
			}
		}
//		String [] tridentInfo = null;
//		if (tridentString != null && tridentString.contains("/") ) {
//			tridentInfo = tridentString.split("\\/");			
//		}
		return tridentString;
		
	}
	
	
	
}
