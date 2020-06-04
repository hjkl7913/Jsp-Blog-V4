package com.cos.blog.action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.model.Board;
import com.cos.blog.model.Users;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.util.Script;

public class BoardWriteProcAction implements Action{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0번 인증확인
		HttpSession session = request.getSession();
		if(session.getAttribute("principal") ==null) {
			Script.getMessage("잘못된 접근입니다.", response);
			return; // 여기서 return 이 었으면 코드를 아래를 타고 내려간다.
		}
		
		Users principal = (Users) session.getAttribute("principal");
		
		// 1번 request에 title 값과 conent값 null 인지 공백인지 확인
		
		if
		(
				request.getParameter("title").equals("") ||
				request.getParameter("title") == null ||
				request.getParameter("content").equals("") ||
				request.getParameter("content") == null 
		) {
			return;
		}
		// 2번 requset에 title 값과 content 값 받기
		
			String title = request.getParameter("title");
			String content = request.getParameter("content");
		
		// 3번 title값과 content, principal.getId() 값을 Board 오브젝트에 담기
			
			
		
			Board board = Board.builder()
					.userId(principal.getId())
					.title(title)
					.content(content)
					.readCount(0)
					.build();
			
			
		// 4번 BoardRepository연결해서 save(board) 함수 호출
		BoardRepository boardRepository = BoardRepository.getInstance();
		int result = boardRepository.save(board);
		// 5번 result == 1이면 성공로직(index.jsp 로 이동)
		
		if(result == 1) {
		Script.href("글쓰기 등록 완료", "/blog/board?cmd=home", response);
		}
		// 6번 result != 1이면 실패로직(history.back())
		else { 
			Script.back("글쓰기 실패", response);
		}
	}
}
