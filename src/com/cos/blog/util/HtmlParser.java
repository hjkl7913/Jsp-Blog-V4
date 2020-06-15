package com.cos.blog.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.nhncorp.lucy.security.xss.XssPreventer;

public class HtmlParser {

	public static String getYoutubePreview(String content) {
		// https://youtu.be/KjhbBz1rWSQ

		Document doc = Jsoup.parse(content);
		// System.out.println("doc : "+doc.toString());
		Elements aTags = doc.select("a");
		// System.out.println(aTags.toString());
		String el = aTags.attr("href");
		// System.out.println("el: "+el);
		// String value = aTags.text();
		// System.out.println("value : "+value);

		String preview = null;

		for (Element aTag : aTags) {
			String value = aTag.attr("href");
			System.out.println("atag: "+aTag);
			System.out.println("value: "+value);
			
				if (value.contains("https://www.youtube.com/") && !aTag.attr("target").equals("_blank")) {
					String[] yu = value.split("=");
					// preview = "<br/><embed
					// src=\"http://www.youtube.com/v/"+yu[1]+"?version=3&amp;hl=ko_KR&amp;vq=hd720\"
					// type=\"application/x-shockwave-flash\" width=\"640\" height=\"360\"
					// =\"always\" allowfullscreen=\"true\"></embed>";
					preview = "<br/><iframe src='http://www.youtube.com/embed/" + yu[1]
							+ "' width='640px' height='360px' frameborder='0' allowfullscreen></iframe>";
					aTag.after(preview);

				} else if (value.contains("https://youtu.be/")) {
					String[] yu = value.split("/");
					// preview = "<br/><embed
					// src=\"http://www.youtube.com/v/"+yu[3]+"?version=3&amp;hl=ko_KR&amp;vq=hd720\"
					// type=\"application/x-shockwave-flash\" width=\"640\" height=\"360\"
					// =\"always\" allowfullscreen=\"true\"></embed>";
					preview = "<br/><iframe src='http://www.youtube.com/embed/" + yu[3]
							+ "' width='640px' height='360px' frameborder='0' allowfullscreen></iframe>";
					aTag.after(preview);
				} else {
					System.out.println("유튜브 아니네?");
				}
			}
		
		return doc.toString();

	}

	public static String getContentPreview(String content) {

		Document doc = Jsoup.parse(content);
		// System.out.println("받아온값"+doc);
		Elements pTags = doc.select("p");

		for (Element pTag : pTags) {
			String text = pTag.text();
			if (text.length() > 0) {
				text = XssPreventer.escape(text);
				if (text.length() < 11) {
					return text;
				} else {
					return text.substring(0, 10)+"...";
				}
			}
		}
		return "내용 없음...";
	}
}