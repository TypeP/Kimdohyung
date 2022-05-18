package com.kdh.review.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.kdh.review.model.Ceo_NyamDAO;
import com.kdh.review.model.ReviewDAO;
import com.kdh.review.model.ReviewDTO;

public class ReviewInputAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		
		String saveFolder = request.getServletContext().getRealPath("image\\userimage");
		

		// ?���? ?��??경로�? ?��?��?��?��?��?��. 
		// webcontent ?��?�� ???��?��?��?��?��. 

		
		
		HttpSession session = request.getSession(); 
		String userId = (String)session.getAttribute("id");
		int fileSize=10*1024*1024;
		
		
		
		

		
		MultipartRequest multi = new MultipartRequest(
				request,
				saveFolder,
				fileSize,
				"UTF-8",
				new DefaultFileRenamePolicy()
				
				);
		
		
		int cnum=Integer.parseInt(multi.getParameter("cnum").trim());
		
		String rtitle=multi.getParameter("rtitle").trim();
		String rcont=multi.getParameter("rcont").trim();
		//String rcont=multi.getParameter("rcont").replace("\r\n","<br>");
		
		int rate=Integer.parseInt(multi.getParameter("rate").trim());
		String went=multi.getParameter("went").trim();
		
		String rimage=multi.getFilesystemName("rimage");
		
		
		System.out.println(went);
		
		ReviewDTO dto = new ReviewDTO();
		
		dto.setReview_ceo_num(cnum);
		dto.setReview_title(rtitle);
		dto.setReview_cont(rcont);
		dto.setReview_id(userId);
		dto.setReview_rate(rate);
		dto.setReview_went(went);
		dto.setReview_image(rimage);
		
		ReviewDAO dao= ReviewDAO.getInstance();
		
		
		
		int check=dao.insertReview(dto);
		
		double rat = dao.getAvgSelect(cnum);
		
		String drate = String.format("%.2f", rat);
		
		System.out.println("cnum"+cnum);
		
		Ceo_NyamDAO dar = Ceo_NyamDAO.getInstance();
		dar.getInsertAvg(drate, cnum);
		
		
		ActionForward forward= new ActionForward();
		PrintWriter out = response.getWriter();
		
	
		
		//System.out.println(uploadPath);
	
		
		/*
		 * double rat = dao.getAvgSelect(cnum);
		 * 
		 * String drate = String.format("%.2f", rat);
		 * 
		 * System.out.println(drate);
		 * 
		 * Ceo_NyamDAO dar = Ceo_NyamDAO.getInstance(); dar.getInsertAvg(drate, cnum);
		 */
		
		
		if(check>0) {
			forward.setRedirect(true);
			forward.setPath("owner_contents.do?no="+cnum);
			
		}else {
			out.println("<script>");
			out.println("alert('�����Ͽ� �����ϼ̽��ϴ�.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
				
		
		
		
		return forward;
	}

}
