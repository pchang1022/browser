package com.medassets.browser.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.medassets.browser.service.BroswerUtils;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;

@RequestMapping("/browserUtils")
@Controller
public class BrowseUtilsController {

	@RequestMapping(value = "/detect", method = RequestMethod.GET, produces = "text/html")
	public String detect(HttpServletRequest request, HttpServletResponse response, Model model) {

		HttpSession session = request.getSession();
		removeIEtagAttributes(session);
		// System.out.println("Enter: x-ua-compatible-tag: " + session.getAttribute("x-ua-compatible-tag"));
		String userAgent = request.getHeader("User-Agent");
		UserAgent userAgentObj = BroswerUtils.parseUserAgent(userAgent);
		model.addAttribute("ua", userAgent);
		model.addAttribute("userAgent", userAgentObj);
		return "browserUtils/detect";
	}

	@RequestMapping(value = "/changeDocMode", method = RequestMethod.GET, produces = "text/html")
	public String changeDocMode(HttpServletRequest request, HttpServletResponse response, Model model) {
		HttpSession session = request.getSession();
		removeIEtagAttributes(session);
		String userAgent = request.getHeader("User-Agent");
		UserAgent userAgentObj = BroswerUtils.parseUserAgent(userAgent);
		Browser browser = userAgentObj.getBrowser();
		if (browser.toString().equalsIgnoreCase("ie8")) {
			// String metaTag = "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=7\"/>";
			// String metaTag = "http-equiv=\"X-UA-Compatible\" content=\"IE=7\"";
			session.setAttribute("xUaCompatibleIE7Tag", "yes");
			model.addAttribute("ua", userAgent);
			model.addAttribute("userAgent", userAgentObj);
		} else {
			model.addAttribute("ieMessage", "This will only work if you are on IE8.");
		}
		return "browserUtils/detect";
	}

	@RequestMapping(value = "/changeDocModeUseHeader", method = RequestMethod.GET, produces = "text/html")
	public String changeDocModeUseHeader(HttpServletRequest request, HttpServletResponse response, Model model) {
		HttpSession session = request.getSession();
		removeIEtagAttributes(session);
		String userAgent = request.getHeader("User-Agent");
		UserAgent userAgentObj = BroswerUtils.parseUserAgent(userAgent);
		Browser browser = userAgentObj.getBrowser();
		if (browser.toString().equalsIgnoreCase("ie8")) {
			response.addHeader("x-ua-compatible", "IE=7"); // "IE=EmulateIE7");
			model.addAttribute("ua", userAgent);
			model.addAttribute("userAgent", userAgentObj);
		} else {
			model.addAttribute("ieMessage", "This will only work if you are on IE8.");
		}

		return "browserUtils/detect";
	}

	@RequestMapping(value = "/resetcompview", method = RequestMethod.GET, produces = "text/html")
	public String resetcompview(HttpServletRequest request, HttpServletResponse response, Model model) {
		HttpSession session = request.getSession();
		removeIEtagAttributes(session);
		String userAgent = request.getHeader("User-Agent");
		UserAgent userAgentObj = BroswerUtils.parseUserAgent(userAgent);
		Browser browser = userAgentObj.getBrowser();
		String versionIE = browser.toString(); // IE9
		String versionTrident = null;
		// ua.toString() ; // WINDOWS_7-IE9
		Integer.parseInt(userAgentObj.getBrowserVersion().getMajorVersion()); // 9
		Integer.parseInt(userAgentObj.getBrowserVersion().getMinorVersion()); // 0
		
		model.addAttribute("ua", userAgent);
		model.addAttribute("userAgent", userAgentObj);
		
		// 4/29/13 - roll my own	
		if (versionIE.contains("IE")) {
			versionTrident = BroswerUtils.getTridentVersion(userAgent);

			// if versionIE = IE7, but tridentVersion = Trident/5.0 = in IE9 compat mode
			// if versionIE = IE7, but tridentVersion = Trident/4.0 = in IE8 compat mode
			// Check for know IE Compt view mode:
			if (versionIE.equalsIgnoreCase("IE7")) {
				if (versionTrident != null && versionTrident.equalsIgnoreCase("Trident/5.0")) {
					// force back to standard browser mode
					session.setAttribute("xUaCompatibleIE9Tag", "yes");
				} else if (versionTrident != null && versionTrident.equalsIgnoreCase("Trident/4.0")) {
					session.setAttribute("xUaCompatibleIE8Tag", "yes");
				} else {
					model.addAttribute("ieMessage", "You are using IE7.  Not compatibility view mode.");
				}
			} else {
				if (versionIE.equalsIgnoreCase("IE8")) {
					if ( versionTrident != null && versionTrident.equalsIgnoreCase("Trident/4.0")) {
						model.addAttribute("ieMessage", "You are already using IE8 Standard Browser/Document Mode");
					}
				} else if (versionIE.equalsIgnoreCase("IE9")) {
					if ( versionTrident != null && versionTrident.equalsIgnoreCase("Trident/5.0")) {
						model.addAttribute("ieMessage", "You are already using IE9 Standard Browser/Document Mode");
					}
				} else {
					model.addAttribute("ieMessage", "You not using IE 8 or 9.");
				}
			}
		} else {
			model.addAttribute("ieMessage", "You not using IE.");
		}
		return "browserUtils/detect";
	}

	private void removeIEtagAttributes(HttpSession session) {
		session.removeAttribute("xUaCompatibleIE7Tag");
		session.removeAttribute("xUaCompatibleIE8Tag");
		session.removeAttribute("xUaCompatibleIE9Tag");
		session.removeAttribute("ieMessage");

	}

	String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
		String enc = httpServletRequest.getCharacterEncoding();
		if (enc == null) {
			enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
		}
		try {
			pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
		} catch (UnsupportedEncodingException uee) {
		}
		return pathSegment;
	}
}
