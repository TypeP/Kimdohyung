package com.owner.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.owner.model.ServiceNyamDAO;
import com.owner.model.ServiceNyamDTO;

public class OwnerCsSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 검색 폼 페이지에서 넘어온 데이터를 가지고 검색어에 해당하는 게시물들을 db에서 조회하여 viwpage로 이동시키는 비즈니스 로직
		String search_field = request.getParameter("search_field").trim();
		String search_keyword = request.getParameter("search_keyword").trim();
		
		//세션으로 넘어온 아이디num
		HttpSession session = request.getSession(); 
		int num = (Integer)(session.getAttribute("num"));
	
		// 페이징 처리 작업
		int rowsize = 5;		//한 페이지당 보여질 게시물의 수
		int block = 3;			//아래에 보여질 페이지의 최대수 - 예) [1][2][3] / [4][5][6] ...
		int totalRecord = 0;	//db상의 게시물 전체 수
		int allPage = 0;		//전체 페이지 수
		
		int page = 0;			//현재 페이지를 나타내는 변수
		
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page").trim());
		}else {			//처음으로 전체 게시물 목록 클릭할 때(main.jsp의 a태그)
			page = 1;	//1페이지로 감
		}
		
		//해당 페이지에서 게시글 시작번호
		int startNo = (page * rowsize) - (rowsize - 1);
		
		//해당 페이지에서 게시글 끝번호
		int endNo = (page * rowsize);
		
		//해당 페이지에서 시작 블럭
		int startBlock = (((page - 1) / block) * block) + 1;
		
		//해당 페이지에서 끝 블럭	(123/456 씩 나타내니까)
		int endBlock = (((page - 1) / block) * block) + block;
		
		ServiceNyamDAO dao = ServiceNyamDAO.getInstance();
		
		//db상의 검색 게시물의 수를 확인하는 메소드 호출
		totalRecord = dao.searchListCount(search_field, search_keyword);

		//전체 게시물의 수를 한 페이지당 보여질 게시물의 수로 나누면 전체 페이지수 나오는데 나머지 수는 페이지수 + 1 해줘야함
		allPage = (int) Math.ceil(totalRecord / (double)rowsize);
		
		if (endBlock > allPage) {
			endBlock = allPage;
		}
		
		//현재 페이지에 해당하는 게시물을 가져오는 메소드 호출
		List<ServiceNyamDTO> searchList = dao.searchBoardList(search_field, search_keyword, page, rowsize);
		
		//지금까지 페이징 처리 시 작업했던 모든 값들을 view로 이동
		request.setAttribute("page", page);
		request.setAttribute("rowsize", rowsize);
		request.setAttribute("block", block);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("startBlock", startBlock);
		request.setAttribute("endBlock", endBlock);
		request.setAttribute("search_field", search_field);
		request.setAttribute("search_keyword", search_keyword);
		request.setAttribute("List", searchList);
		
		request.setAttribute("num", num);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("eunchae/view/owner_service_search.jsp");
		
		return forward;
	}

}
