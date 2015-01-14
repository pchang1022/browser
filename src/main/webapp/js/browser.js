/*
IE versions	Supported standard object
10+	window.atob
9+	document.addEventListener
8+	document.querySelector
7+	window.XMLHttpRequest
6+	document.compatMode
*/

$(document).ready(function($) { 
	
//	alert('in ready');
	$('#browserText').html("");
	$('#browserText').hide();
	
	
	var BrowserUtils = {};
	BrowserUtils.detectIEVersion = function() {
	
		
//		if (document.documentMode) {
//			alert ("document.documentMode: " + document.documentMode);
//		}
		var browserVersion = "";
		
		if (document.all && document.querySelector && document.addEventListener) {
			browserVersion = "IE9";
		}
		
		if (document.all && !document.querySelector) {
			// alert('IE7 or lower');
			browserVersion = "<br/>IE7 or lower";
		}

		if (document.all && document.querySelector && !document.addEventListener) {
			// alert('IE8');
			browserVersion += "<br/>IE8";
		}

//		if (document.all && document.documentMode && 8 === document.documentMode) {
//			// alert('IE8 or IE9+ in IE8 compatibility mode');
//			browserVersion += "<br/>IE8 or IE9+ in IE8 compatibility mode";
//		}
//
//		if (document.all && (!document.documentMode || (document.documentMode && document.documentMode < 8))) {
//			// alert('IE7 or lower or IE8+ in IE7 compatibility mode');
//			browserVersion += "<br/>IE7 or lower or IE8+ in IE7 compatibility mode";
//		}
		if (document.documentMode) {
			//alert ("document.documentMode: " + document.documentMode);
			browserVersion += "<br/>documentMode: " + document.documentMode;
		}
		if (navigator) {
			browserVersion += "<br/><br/>User Agent:" + navigator.userAgent;
			//browserVersion += "<br/>" + navigator.appName ;
			browserVersion += "<br/><br/>App Version: " + navigator.appVersion ;
			//browserVersion += "<br/><br/>" + navigator.platform ;
		}
		return browserVersion;
	} ;
		
	var broswerVerString =	BrowserUtils.detectIEVersion();
	
	$('#browserText').show();
	$('#browserText').html(broswerVerString);
});


