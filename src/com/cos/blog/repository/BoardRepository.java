package com.cos.blog.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog.db.DBConn;
import com.cos.blog.dto.DetailResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Users;

//싱글톤
//DAO
public class BoardRepository {
	
	private static final String TAG = "BoardRepository : ";
	private static BoardRepository instance = new BoardRepository();
	private BoardRepository() {}
	public static BoardRepository getInstance() {
		return instance;
	}
	
	private Connection conn =null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	public int findBoardCount(String keyword) {
		int result = 0;
		final String SQL ="SELECT count(*) FROM board WHERE title Like ? OR content Like ?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
			
			
			// while 돌려서 rs -> java 오브젝트에 집어넣기
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);  
				System.out.println("result2 : "+result);
			
			}
			
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"findBoard(keyword) : "+e.getMessage());
			
		} finally {
			DBConn.close(conn, pstmt ,rs);
		}
		
		return -1;
	}
	
	public List<Board> findAll(int page, String keyword) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT /*+ INDEX_DESC(BOARD SYS_C007666)*/id, ");
		sb.append("userId, title, content, readCount, createDate ");
		sb.append("FROM board ");
		sb.append("WHERE title like ? OR content like ? ");		
		sb.append("OFFSET ? ROWS FETCH FIRST 3 ROWS ONLY ");
		
		
		final String SQL = sb.toString();
		List<Board> boards = new ArrayList<>();
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			// 물음표 완성하기
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setInt(3, page*3);
			
			
			// while 돌려서 rs -> java 오브젝트에 집어넣기
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board(
						rs.getInt("id"),
						rs.getInt("userId"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getInt("readCount"),
						rs.getTimestamp("createDate")
				);
				boards.add(board);
			}
			
			return boards;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"findAll(page, keyword) : "+e.getMessage());
			
		} finally {
			DBConn.close(conn, pstmt ,rs);
		}
		
		return null;
	}
	
	
	
	public int addReadCount(int id) {

			final String SQL ="UPDATE board SET readCount = readCount+1  WHERE id = ?";
			
			//board 오브젝트와 users의 username을 DetailResponseDto오브젝트를 새로 만들어서 안에 넣어서 리턴함
		
			
			try {
				conn = DBConn.getConnection();
				pstmt = conn.prepareStatement(SQL);
				// 물음표 완성하기
				
				pstmt.setInt(1, id);
				
				
				// if 돌려서 rs -> java 오브젝트에 집어넣기
				
				return pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(TAG+"addReadCount : "+e.getMessage());
				
			} finally {
				DBConn.close(conn, pstmt ,rs);
			}
			
			return -1;
		}
	
	public int findBoardCount() {
		int result = 0;
		final String SQL ="SELECT count(*) FROM board";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			
			// while 돌려서 rs -> java 오브젝트에 집어넣기
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);  
				System.out.println("result : "+result);
			
			}
			
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"findBoard : "+e.getMessage());
			
		} finally {
			DBConn.close(conn, pstmt ,rs);
		}
		
		return -1;
	}
	
	public List<Board> findAll(int page) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT /*+ INDEX_DESC(BOARD SYS_C007666)*/id, ");
		sb.append("userId, title, content, readCount, createDate ");
		sb.append("FROM board ");
		sb.append("OFFSET ? ROWS FETCH FIRST 3 ROWS ONLY ");
		
		
		final String SQL = sb.toString();
		List<Board> boards = new ArrayList<>();
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			// 물음표 완성하기
			pstmt.setInt(1, page*3);
			
			
			// while 돌려서 rs -> java 오브젝트에 집어넣기
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board(
						rs.getInt("id"),
						rs.getInt("userId"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getInt("readCount"),
						rs.getTimestamp("createDate")
				);
				boards.add(board);
			}
			
			return boards;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"findAll(page) : "+e.getMessage());
			
		} finally {
			DBConn.close(conn, pstmt ,rs);
		}
		
		return null;
	}
	
	
	public int save(Board board) {
		final String SQL ="insert into board(id,userId,title,content,readCount,createDate) VALUES(BOARD_SEQ.nextval,?,?,?,?,sysdate)";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, board.getUserId());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getReadCount());
			
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"save : "+e.getMessage());
			
		} finally {
			DBConn.close(conn, pstmt);
		}
		
		return -1;
	}
	
	public int update(Board board) {
		final String SQL ="UPDATE board SET title = ?, content= ?  WHERE id = ?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getId());
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"update : "+e.getMessage());
			
		} finally {
			DBConn.close(conn, pstmt);
		}
		
		return -1;
	}
	
	public int deleteById(int id) {
		final String SQL ="DELETE FROM board WHERE id = ?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			
			pstmt.setInt(1, id);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"deleteById : "+e.getMessage());
			
		} finally {
			DBConn.close(conn, pstmt);
		}
		
		return -1;
	}
	
	public List<Board> findAll() {
		final String SQL ="SELECT * FROM board ORDER BY id DESC";
		List<Board> boards = new ArrayList<>();
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			
			// while 돌려서 rs -> java 오브젝트에 집어넣기
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board(
						rs.getInt("id"),
						rs.getInt("userId"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getInt("readCount"),
						rs.getTimestamp("createDate")
				);
				boards.add(board);
			}
			
			return boards;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"findAll : "+e.getMessage());
			
		} finally {
			DBConn.close(conn, pstmt ,rs);
		}
		
		return null;
	}
	
	public DetailResponseDto findById(int id) {
		//board 테이블에서 users 테이블의 id가 같은 유저의 네임을 가져와야함
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT b.id, b.userId, b.title, b.content, b.readcount, b.createDate, u.username ");
		sb.append("FROM board b INNER JOIN users u ");
		sb.append("ON b.userId = u.id ");
		sb.append("WHERE b.id = ?");
		final String SQL =sb.toString();
		
		//board 오브젝트와 users의 username을 DetailResponseDto오브젝트를 새로 만들어서 안에 넣어서 리턴함
	
		DetailResponseDto dto = null;
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			
			pstmt.setInt(1, id);
			
			rs=pstmt.executeQuery();
			// if 돌려서 rs -> java 오브젝트에 집어넣기
			if(rs.next()) {
				dto = new DetailResponseDto();
				Board board = Board.builder()
						.id(rs.getInt(1))
						.userId(rs.getInt(2))
						.title(rs.getString(3))
						.content(rs.getString(4))
						.readCount(rs.getInt(5))
						.createDate(rs.getTimestamp(6))
						.build();
				dto.setBoard(board);
				dto.setUsername(rs.getString(7));
			}
			
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"findById : "+e.getMessage());
			
		} finally {
			DBConn.close(conn, pstmt ,rs);
		}
		
		return null;
	}
}
