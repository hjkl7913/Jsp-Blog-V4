package com.cos.blog.action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.util.Script;

public class BoardWriteAction implements Action{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 되있는지 세션 체크
		
		HttpSession session = request.getSession();
		
		// if 를 탄다는건 공격당하고 있다는것 로그인없이 글쓰기에 들어온다는것
		if(session.getAttribute("principal") == null) { //principal 인증된 주체
			Script.getMessage("잘못된 접근입니다.", response);
		} else {
			RequestDispatcher dis = request.getRequestDispatcher("board/write.jsp");
			dis.forward(request, response);	
		}
		
	}
}
