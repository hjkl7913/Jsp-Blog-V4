package com.cos.blog.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.action.board.BoardDeleteAction;
import com.cos.blog.action.board.BoardDetailAction;
import com.cos.blog.action.board.BoardHomeAction;
import com.cos.blog.action.board.BoardSearchAction;
import com.cos.blog.action.board.BoardUpdateAction;
import com.cos.blog.action.board.BoardUpdateProcAction;
import com.cos.blog.action.board.BoardWriteAction;
import com.cos.blog.action.board.BoardWriteProcAction;
import com.cos.blog.action.reply.ReplyDeleteProcAction;
import com.cos.blog.action.reply.ReplyWriteProcAction;
import com.cos.blog.action.user.UsersJoinAction;
import com.cos.blog.action.user.UsersJoinProcAction;
import com.cos.blog.action.user.UsersLoginAction;

// http://localhost:8000/blog/board
@WebServlet("/reply")
public class ReplyController extends HttpServlet {
	private final static String TAG ="ReplyController : ";
	private static final long serialVersionUID = 1L;
       
    
    public ReplyController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://localhost:8000/blog/user?cmd=join
		String cmd = request.getParameter("cmd");
		System.out.println(TAG+"router : "+cmd);
		Action action = router(cmd);
		action.execute(request, response);
		
	}
	//분기
	public Action router(String cmd) {
		if(cmd.equals("writeProc")) {
			// Home 페이지로 이동
			return new ReplyWriteProcAction(); //Board 의 목록
		}else if(cmd.equals("deleteProc")) {
			// Home 페이지로 이동
			return new ReplyDeleteProcAction(); //Board 의 목록
		}
		return null;
	}
}
