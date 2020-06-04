package com.cos.blog.action.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.util.Script;

public class UsersLogoutAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();  //세션접근
		session.invalidate(); //세션 무효화 다날라감
		
		//session.removeAttribute("principal"); //principal 만 날림  보통은 모두날림
		// 액션이 있음으로 controller로
		//index.jsp 에 /blog/board?cmd=home로 home으로 가도록 만들어 놓았음으로 index.jsp 로 가면됨
		Script.href("로그아웃 성공", "index.jsp", response);
		
	}
	
	// 컨트롤러가 할일 을 위임함 팩토리패턴
	
}
