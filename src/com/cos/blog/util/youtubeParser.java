package com.cos.blog.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;


public class youtubeParser {
	
	@Test
	public void getPreview() {
		
		String content = "<p><a href=\"https://www.youtube.com/watch?v=kc_K2RQTxMQ\">https://www.youtube.com/watch?v=kc_K2RQTxMQ</a><br></p>";
		System.out.println("content: "+content);
		String content2 = "<p><a href=\"https://youtu.be/KjhbBz1rWSQ\" target=\"_blank\">https://youtu.be/KjhbBz1rWSQ</a></p>";
		
		Document doc = Jsoup.parse(content2);
		Elements els = doc.select("a");
		System.out.println(els.toString());
		String el = els.attr("href");
		System.out.println("el: "+el);
		
		String value = els.text();
		System.out.println("value : "+value);
		
		if(value.contains("https://www.youtube.com") || value.contains("https://youtu.be/")) {
			String [] yu2 = value.split("/");
			System.out.println("yu2:  "+ yu2[2]);
			
			
			
//			System.out.println("유튜브네?");
//			String[] sp = value.split("=");
//			System.out.println("sp : "+sp[1]);
//			String preview = "<iframe src='https://www.youtube.com/watch?v="+sp[1]+"' width='90%' height='300' frameborder='1' scrolling='yes'></iframe>";
//		    System.out.println("iframe: "+preview);
		}else {
			System.out.println("유튜브아니네?");
		}
		
	}
}
