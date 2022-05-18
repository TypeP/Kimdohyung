package com.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.MemberDAO;
import com.admin.model.MemberDTO;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;

public class MemberManagenetSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		// 리스트 페이지에서 넘어온 데이터를 가지고 db에서 조회하여 view page로 이동
		
		String search_field = request.getParameter("search_field").trim();
		String search_keyword = request.getParameter("search_keyword").trim();
		
		
		//페이징처리
		int rowsize = 8;      // 한 페이지당 보여질 게시물의 수.
		int block = 3;        // 아래에 보여질 페이지의 최대 수. 예) [1][2][3] / [4][5][6]...
		int totalRecord = 0;  // DB 상의 게시물의 전체 수
		int allPage = 0;      // 전체 페이지 수
		int page = 0;         // 현재 페이지 변수
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page").trim());
			
		}else {	// 처음으로 "전체 게시물 목록" a 태그를 선택한 경우
			page = 1; // 1페이지나옴
		}
		
		int startNo = (page*rowsize) - (rowsize-1);			// 해당 페이지에서 시작 번호
		int endNo = (page*rowsize);							// 해당 페이지에서 끝번호
		int startBlock = (((page -1) / block)*block)+1;		//해당 페이지에서 시작 블럭
		int endBlock = (((page -1) / block)*block)+block;	//해당 페이지에서 끝 블럭
		
		
		MemberDAO dao = MemberDAO.getInstance();
		
		totalRecord = dao.getSearchMemberCount(search_field, search_keyword);
		
		allPage =(int)Math.ceil(totalRecord / (double)rowsize);   //전체게시물수 / 한페이지당 보여질 게시물수
		
		if(endBlock > allPage) {
			endBlock = allPage; //endBlock 블럭이 남아있을경우 삭제작업
		}
		
		List<MemberDTO> searchList = dao.getSearchMemberList(search_field,search_keyword,page,rowsize);
		
		request.setAttribute("List", searchList);

		request.setAttribute("page", page);
		
		request.setAttribute("block", block);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);

		
		request.setAttribute("startBlock", startBlock);
		request.setAttribute("endBlock", endBlock);

		request.setAttribute("search_field", search_field);
		request.setAttribute("search_keyword", search_keyword);


		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("kimmin/admin/admin_search_list.jsp");
		
		return forward;
	}

}
