package com.cos.blog.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.dto.BoardResponseDto;
import com.cos.blog.dto.DetailResponseDto;
import com.cos.blog.dto.ReplyResponseDto;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
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
		
		int BoardId = Integer.parseInt(request.getParameter("id")); // request.getparameter 는 string
		 BoardRepository boardRepository = BoardRepository.getInstance();
		 ReplyRepository replyRepository = ReplyRepository.getInstance();
		
		 
		 
		//새로고침 조회수 증가 쿠키로 막기
		 if (cookies != null) {
			 for (int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("|"+BoardId+"|")) {
					
					viewCookie = cookies[i];
				}
			}
		 }
		 
		 if(viewCookie == null) {
			 Cookie readCookie = new Cookie("|"+BoardId+"|","readCookie");
			 response.addCookie(readCookie);
			 
			//상세보기 클릭시 조회수 올리기
			 int result = boardRepository.addReadCount(BoardId);
		 }

		 
			//userid 로 검색해서 username , board 정보 담기 
			boardRepository = BoardRepository.getInstance();
			
			// Board, User (해당 게시물의 글과 작성자)
			BoardResponseDto boardDto = boardRepository.findById(BoardId);
			
			// Reply, User (해당 게시물의 댓글과 댓글의 작성자)
			List<ReplyResponseDto> replyDtos = replyRepository.findAll(BoardId);
			
			DetailResponseDto detailDto = DetailResponseDto.builder()
					.boardDto(boardDto)
					.replyDtos(replyDtos)
					.build();
			
			
			if (detailDto != null) {
				String content = boardDto.getBoard().getContent();
				preview = HtmlParser.getYoutubePreview(content);
				detailDto.getBoardDto().getBoard().setContent(preview);

				request.setAttribute("detailDto", detailDto);
				RequestDispatcher dis = request.getRequestDispatcher("board/detail.jsp");
				dis.forward(request, response);
			} else {
				Script.back("잘못된 접근입니다.", response);
			}
		
	}
}
