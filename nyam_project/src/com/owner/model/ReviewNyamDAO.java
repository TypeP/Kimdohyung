package com.owner.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReviewNyamDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = null;
	
	//싱글턴 방식
	//instance : 참조변수(주소값)
	private static ReviewNyamDAO instance;
	
	public ReviewNyamDAO() {
	}

	//기본생성자 대신 싱글턴 객체를 return해주는 getInstance()라는 메소드를 만들어서 그 메소드에 외부에서 접근할수 있도록 함
	//어디서 생성하든 항상 동일한 주소값
	public static ReviewNyamDAO getInstance() {
		if (instance == null) {
			instance = new ReviewNyamDAO();
		}
		return instance;
	}	//getInstance() 끝
	
	//db연동 메소드 - dbcp방식
	public void openCon() {
		try {
			//jndi 서버 객체 생성
			Context ctx = new InitialContext();
			
			//lookup 메소드 이용하여 매칭되는 커넥션 찾기
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
			
			//DataSource 객체를 이용하여 커넥션 객체를 하나 가져온다
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	//opencon() 끝
	
	//db에 연결된 자원 종료하는 메소드
		public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			}catch (SQLException e) {
					e.printStackTrace();
				}
		}	//closeconn() 끝
		
	//가게 번호에 해당하는 전체 리뷰 수를 확인하는 메소드
	public int getReviewCount(int no) {
		int count = 0;
		
		try {
			openCon();
			
			sql = "select count(*) from review_nyam where review_ceo_num = ?";
		
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return count;
	}
	
	//review_nyam 테이블에서 현재 페이지에 해당하는 게시물을 조회하는 메소드
	public List<ReviewNyamDTO> getReviewList(int page, int rowsize, int no) {
		
		System.out.println("no="+no);
		List<ReviewNyamDTO> list = new ArrayList<ReviewNyamDTO>();
		
		int startNo = (page * rowsize) - (rowsize - 1);
		
		//해당 페이지에서 끝번호
		int endNo = (page * rowsize);
		
		try {
			openCon();
			
			sql = "select * from (select row_number() over(order by review_num desc) rnum, b.* from review_nyam b where review_ceo_num=?) where (rnum >= ? and rnum <= ?) ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setInt(2, startNo);
			pstmt.setInt(3, endNo);
		
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ReviewNyamDTO dto = new ReviewNyamDTO();
				
				dto.setReview_ceo_num(rs.getInt("review_ceo_num"));
				dto.setReview_num(rs.getInt("review_num"));
				dto.setReview_title(rs.getString("review_title"));
				dto.setReview_cont(rs.getString("review_cont"));
				dto.setReview_id(rs.getString("review_id"));
				dto.setReview_image(rs.getString("review_image"));
				dto.setReview_rate(rs.getInt("review_rate"));
				dto.setReview_went(rs.getString("review_went"));
				dto.setReview_date(rs.getString("review_date"));
				dto.setReview_like(rs.getInt("review_like"));
				dto.setReview_bad(rs.getInt("review_bad"));
				System.out.println("리뷰 테스트 dao test"+rs.getInt("review_ceo_num"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}	
	
	//신고하는 메소드
	public int reviewReport(String user_id, int ceo_no, int review_no) {
		int result = 0;
		
		ReviewNyamDTO dto = new ReviewNyamDTO();
		
		try {
			openCon();
			
			sql = "update review_nyam set review_bad = 1 where review_ceo_num = ? and review_num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ceo_no);
			pstmt.setInt(2, review_no);
			
			pstmt.executeUpdate();
			
			sql = "select * from review_nyam where review_ceo_num = ? and review_num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ceo_no);
			pstmt.setInt(2, review_no);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto.setReview_ceo_num(rs.getInt("review_ceo_num"));
				dto.setReview_num(rs.getInt("review_num"));
				dto.setReview_title(rs.getString("review_title"));
				dto.setReview_cont(rs.getString("review_cont"));
				dto.setReview_id(rs.getString("review_id"));
				dto.setReview_image(rs.getString("review_image"));
				dto.setReview_rate(rs.getInt("review_rate"));
				dto.setReview_went(rs.getString("review_went"));
				dto.setReview_date(rs.getString("review_date"));
				dto.setReview_like(rs.getInt("review_like"));
				dto.setReview_bad(rs.getInt("review_bad"));
			}
			
			sql = "insert into bad_nyam values(?, ?, ?, ?, 0, sysdate)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, dto.getReview_id());
			pstmt.setInt(3, ceo_no);
			pstmt.setInt(4, review_no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
	
	//신고한 리뷰 수 카운트하는 메소드
	public int getReportedReviewCount(String id) {
		int count = 0;
		
		try {
			openCon();
			
			sql = "select count(*) from bad_nyam where bad_user_id = ? and bad_reply_num = 0";
		
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return count;
	}
	
	//review_nyam 테이블에서 현재 페이지에 해당하는 신고된 게시물을 조회하는 메소드
	public List<ReviewNyamDTO> getReportedReviewList(int page, int rowsize, String id) {
		List<ReviewNyamDTO> list = new ArrayList<ReviewNyamDTO>();
		
		int startNo = (page * rowsize) - (rowsize - 1);
		
		//해당 페이지에서 끝번호
		int endNo = (page * rowsize);
		
		try {
			openCon();
			
			sql = "select * from (select row_number() over(order by review_date desc) rnum, b.* from review_nyam b inner join bad_nyam a on b.review_num = a.bad_review_num where a.bad_user_id = ? and a.bad_reply_num = 0) where (rnum >= ? and rnum <= ?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setInt(2, startNo);
			pstmt.setInt(3, endNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				ReviewNyamDTO dto = new ReviewNyamDTO();
				
				dto.setReview_ceo_num(rs.getInt("review_ceo_num"));
				dto.setReview_num(rs.getInt("review_num"));
				dto.setReview_title(rs.getString("review_title"));
				dto.setReview_cont(rs.getString("review_cont"));
				dto.setReview_id(rs.getString("review_id"));
				dto.setReview_image(rs.getString("review_image"));
				dto.setReview_rate(rs.getInt("review_rate"));
				dto.setReview_went(rs.getString("review_went"));
				dto.setReview_date(rs.getString("review_date"));
				dto.setReview_like(rs.getInt("review_like"));
				dto.setReview_bad(rs.getInt("review_bad"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}
}

