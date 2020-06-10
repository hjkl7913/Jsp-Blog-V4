package com.cos.blog.action.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.SHA256;
import com.cos.blog.util.Script;

public class UsersLoginProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 0. 유효성 검사
				if
				(
						request.getParameter("username").equals("") ||
						request.getParameter("username") == null ||
						request.getParameter("password").equals("") ||
						request.getParameter("password") == null 

				) {
					return; //void 지만 return 하면 밑으로 가지않고 리턴됨
				}
				
				
				String username = request.getParameter("username");
				String rawPassword = request.getParameter("password");
				String password = SHA256.encodeSha256(rawPassword);
				
				UsersRepository usersRepository = UsersRepository.getInstance();
				Users user = usersRepository.findByUsernameAndPassword(username,password);				
				
				if(user != null) {
					HttpSession session = request.getSession(); //만들어진 세션 접근 ,접근하려면 request로 접근
					session.setAttribute("principal", user); //스레드1개로 관리, JsessionId 로 구분 , value 값은 오브젝트타입
					
					if(request.getParameter("remember") != null) {
						Cookie cookie = new Cookie("remember", user.getUsername());
						response.addCookie(cookie);
						
						//response.setHeader("Set-Cookie", "remember=ssar"); //header 에 Set-Cookie 키-벨류 remember=ssar 로 담김
					} else {
						Cookie cookie = new Cookie("remember", ""); //공백을 넣어준다.
						cookie.setMaxAge(0); //쿠키삭제
						response.addCookie(cookie);		
					}
					
					Script.href("로그인 성공", "/blog/index.jsp", response);
				} else {
					Script.back("로그인 실패", response);
				}
	}
	
}
