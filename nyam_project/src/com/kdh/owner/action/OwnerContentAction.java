package com.kdh.owner.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.kdh.review.model.Ceo_NyamDAO;
import com.kdh.review.model.Ceo_NyamDTO;
import com.kdh.review.model.ReviewDAO;
import com.kdh.review.model.ReviewDTO;

public class OwnerContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		
		int no = Integer.parseInt(request.getParameter("no").trim());
		
		Ceo_NyamDAO dao = Ceo_NyamDAO.getInstance();
		Ceo_NyamDTO dto = dao.ceoContent(no);
		
		System.out.println("??");

		request.setAttribute("cont", dto);

		// �럹�씠吏� 泥섎━ �옉�뾽
		int rowsize = 5; // �븳 �럹�씠吏��떦 蹂댁뿬吏� 寃뚯떆臾쇱쓽 �닔
		int block = 3; // �븘�옒�뿉 蹂댁뿬吏� �럹�씠吏��쓽 理쒕��닔 - �삁) [1][2][3] / [4][5][6] ...
		int totalRecord = 0; // db�긽�쓽 寃뚯떆臾� �쟾泥� �닔
		int allPage = 0; // �쟾泥� �럹�씠吏� �닔

		int page = 0; // �쁽�옱 �럹�씠吏�瑜� �굹���궡�뒗 蹂��닔

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page").trim());
		} else { // 泥섏쓬�쑝濡� �쟾泥� 寃뚯떆臾� 紐⑸줉 �겢由��븷 �븣(main.jsp�쓽 a�깭洹�)
			page = 1; // 1�럹�씠吏�濡� 媛�
		}
		System.out.println("�럹�씠吏��뒗"+page);

		// �빐�떦 �럹�씠吏��뿉�꽌 寃뚯떆湲� �떆�옉踰덊샇
		int startNo = (page * rowsize) - (rowsize - 1);

		// �빐�떦 �럹�씠吏��뿉�꽌 寃뚯떆湲� �걹踰덊샇
		int endNo = (page * rowsize);

		// �빐�떦 �럹�씠吏��뿉�꽌 �떆�옉 釉붾윮
		int startBlock = (((page - 1) / block) * block) + 1;

		// �빐�떦 �럹�씠吏��뿉�꽌 �걹 釉붾윮 (123/456 �뵫 �굹���궡�땲源�)
		int endBlock = (((page - 1) / block) * block) + block;

		ReviewDAO dao2 = ReviewDAO.getInstance();

		// db�긽�쓽 �쟾泥� 寃뚯떆臾쇱쓽 �닔瑜� �솗�씤�븯�뒗 硫붿냼�뱶 �샇異�
		totalRecord = dao2.getReviewCount(no);

		// �쟾泥� 寃뚯떆臾쇱쓽 �닔瑜� �븳 �럹�씠吏��떦 蹂댁뿬吏� 寃뚯떆臾쇱쓽 �닔濡� �굹�늻硫� �쟾泥� �럹�씠吏��닔 �굹�삤�뒗�뜲 �굹癒몄� �닔�뒗 �럹�씠吏��닔 + 1 �빐以섏빞�븿
		allPage = (int) Math.ceil(totalRecord / (double) rowsize);

		if (endBlock > allPage) {
			endBlock = allPage;
		}

		// �쁽�옱 �럹�씠吏��뿉 �빐�떦�븯�뒗 寃뚯떆臾쇱쓣 媛��졇�삤�뒗 硫붿냼�뱶 �샇異�
		List<ReviewDTO> pageList = dao2.getReviewLists(no, page, rowsize);
		
		

		// 吏�湲덇퉴吏� �럹�씠吏� 泥섎━ �떆 �옉�뾽�뻽�뜕 紐⑤뱺 媛믩뱾�쓣 view濡� �씠�룞
		request.setAttribute("page", page);
		request.setAttribute("rowsize", rowsize);
		request.setAttribute("block", block);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("startBlock", startBlock);
		request.setAttribute("endBlock", endBlock);
		request.setAttribute("List", pageList);
		request.setAttribute("ceoNum", no);

		ActionForward forward = new ActionForward();

		forward.setRedirect(false);
		forward.setPath("dohyung/view/owner_content.jsp");
		return forward;
	}

}
