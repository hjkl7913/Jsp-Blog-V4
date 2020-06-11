package com.cos.blog.action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.dto.DetailResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.util.HtmlParser;
import com.cos.blog.util.Script;

public class BoardDetailAction implements Action {

	private String preview = null;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		Cookie viewCookie = null;
		
		if (request.getParameter("id") == null || request.getParameter("id").equals("")) {
			Script.back("잘못된 접근입니다.", response);
			return;
		}
		
		int id = Integer.parseInt(request.getParameter("id")); // request.getparameter 는 string
		 BoardRepository boardRepository = BoardRepository.getInstance();
		
		
		//새로고침 조회수 증가 쿠키로 막기
		 if (cookies != null) {
			 for (int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("|"+id+"|")) {
					
					viewCookie = cookies[i];
				}
			}
		 }
		 
		 if(viewCookie == null) {
			 Cookie readCookie = new Cookie("|"+id+"|","readCookie");
			 response.addCookie(readCookie);
			 
			//상세보기 클릭시 조회수 올리기
			 int result = boardRepository.addReadCount(id);
		 }
		 
		
			//userid 로 검색해서 username , board 정보 담기 
			boardRepository = BoardRepository.getInstance();
			DetailResponseDto dto = boardRepository.findById(id);

			if (dto != null) {

				preview = HtmlParser.getYoutubePreview(dto.getBoard().getContent());
				dto.getBoard().setContent(preview);

				request.setAttribute("dto", dto);
				RequestDispatcher dis = request.getRequestDispatcher("board/detail.jsp");
				dis.forward(request, response);
			} else {
				Script.back("잘못된 접근입니다.", response);
			}
		
	}
}
