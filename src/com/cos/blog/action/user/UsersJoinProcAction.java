package com.cos.blog.action.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.Script;

public class UsersJoinProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 유효성 검사
		if
		(
				request.getParameter("username").equals("") ||
				request.getParameter("username") == null ||
				request.getParameter("password").equals("") ||
				request.getParameter("password") == null ||
				request.getParameter("email").equals("") ||
				request.getParameter("email") == null ||
				request.getParameter("address").equals("") ||
				request.getParameter("address") == null 
		) {
			return; //void 지만 return 하면 밑으로 가지않고 리턴됨
		}
		// 1. 파라메터 받기 (X-www-form-urlencoded 라는 MIME 타입 key=value)
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String userRole = RoleType.USER.toString();
		
		// 2. User 오브젝트 변환
		Users user = Users.builder()
				.username(username)
				.password(password)
				.email(email)
				.address(address)
				.userRole(userRole)
				.build();
		
		// 3. DB연결 - UsersRepository의 save() 호출
		UsersRepository usersRepository = UsersRepository.getInstance();
		int result = usersRepository.save(user);
		
		// 4. index.jsp 페이지로 이동
		if(result == 1) {
			//첫번째 방법 (잘안씀) 
			//response.sendRedirect("/blog/user?cmd=login");
			
			//두번째 방법 (자바스크립트로 이동 메시지창도 띄울수있음)
			Script.href("회원가입에 성공하였습니다.", "/blog/user?cmd=login", response);
			
//			RequestDispatcher dis = request.getRequestDispatcher("user/login.jsp");
//			dis.forward(request, response);
		}else {
			// 이렇게 해도되지만 정보가 남아있지않음 
			//RequestDispatcher dis = request.getRequestDispatcher("user?cmd=joinProc");
			//dis.forward(request, response);
			
			Script.back("회원가입에 실패하였습니다.", response);
		}
	}
}
