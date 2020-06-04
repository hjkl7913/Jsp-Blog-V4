package com.cos.blog.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Script {
	public static void back(String msg, HttpServletResponse response) {
		
		try {
			
			// 
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			
			//버퍼를 달아서 자바스크립트
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('"+msg+"');");
			out.println("history.back();");
			out.println("</script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void href(String msg, String uri, HttpServletResponse response) {
		
		try {
			
			// 
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			
			//버퍼를 달아서 자바스크립트
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('"+msg+"');"); //회원가입이 성공했는지 알려줌
			out.println("location.href='"+uri+"';");
			out.println("</script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void href(String uri, HttpServletResponse response) {
		
		try {
			
			// 
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			
			//버퍼를 달아서 자바스크립트
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("location.href='"+uri+"';");
			out.println("</script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
