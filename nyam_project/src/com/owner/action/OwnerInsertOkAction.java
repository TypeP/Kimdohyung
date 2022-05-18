package com.owner.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.owner.model.CeoNyamDAO;
import com.owner.model.CeoNyamDTO;

public class OwnerInsertOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CeoNyamDTO dto = new CeoNyamDTO();
		
		String saveFolder = request.getServletContext().getRealPath("image\\ceoimage");
		

		// �넱罹� �젅��寃쎈줈濡� �닔�젙�뻽�뒿�땲�떎. 
		// webcontent �뿉�뒗 ���옣�븞�맗�땲�떎. 
		
		//泥⑤��뙆�씪 理쒕� �겕湲� 吏��젙
		int fileSize = 10 * 1024 * 1024;	//10mb

		MultipartRequest multi = new MultipartRequest(
				request,						//�씪諛섏쟻�씤 request媛앹껜 
				saveFolder,						//泥⑤��뙆�씪�씠 ���옣�맆 寃쎈줈
				fileSize,						//�뾽濡쒕뱶�븷 �뙆�씪�쓽 理쒕� �겕湲�
				"UTF-8",						//臾몄옄 �씤肄붾뵫 諛⑹떇
				new DefaultFileRenamePolicy()	//�뙆�씪 �씠由꾩씠 媛숈� 寃쎌슦 以묐났 �븞�릺寃� �꽕�젙
				);
		
		int ceo_num = Integer.parseInt(multi.getParameter("ceo_num").trim());
		String ceo_cont = multi.getParameter("cont").trim();
		String ceo_phone = multi.getParameter("phone").trim();
		String ceo_addr = multi.getParameter("addr").trim();
		String ceo_car = multi.getParameter("car").trim();
		//吏��룄
		double lat = Double.parseDouble(multi.getParameter("lat").trim());
		double lng = Double.parseDouble(multi.getParameter("lng").trim());
		
		File upload_file = multi.getFile("upload_file");
		
		if (upload_file != null) {	//泥⑤��뙆�씪�씠 �엳�쑝硫�
			//getName() : 泥⑤��뙆�씪�쓽 �씠由꾩쓣 臾몄옄�뿴濡� 諛섑솚�빐二쇰뒗 硫붿냼�뱶
			//泥⑤��뙆�씪�쓽 �씠由� 諛쏆븘�삤湲�
			String fileName = upload_file.getName();
			
			//�궇吏쒕퀎濡� 援щ텇�븯�뿬 ���옣�븷寃껋씠湲� �븣臾몄뿉 �궇吏� 媛앹껜 留뚮뱾湲�
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			
			//	.../upload/2022-04-18
			String homedir = saveFolder + "/" + year + "-" + month + "-" + day;
		
			//�궇吏� �뤃�뜑 留뚮뱾湲�
			File path1 = new File(homedir);
			
			if (!path1.exists()) {	//�뤃�뜑媛� 議댁옱�븯吏� �븡�쑝硫�
				path1.mkdir();		//�뤃�뜑瑜� 留뚮뱾�뼱以�
			}
			
			//�뙆�씪 留뚮뱾湲� -> �삁) �솉湲몃룞_�뙆�씪紐�
			//.../upload/2022-04-18/�솉湲몃룞_�뙆�씪紐�
			String reFileName = ceo_num + "_" + fileName;
			
			upload_file.renameTo(new File(homedir + "/" + reFileName));
			
			// �떎�젣 db�뿉 ���옣�릺�뒗 �뙆�씪 �씠由� => "/2022-04-18/�솉湲몃룞_�뙆�씪紐�"
			String fileDBName = "/" + year + "-" + month + "-" + day + "/" + reFileName;
			
			dto.setCeo_image(fileDBName);
		}
		
		dto.setCeo_num(ceo_num);
		dto.setCeo_cont(ceo_cont);
		dto.setCeo_phone(ceo_phone);
		dto.setCeo_addr(ceo_addr);
		dto.setCeo_car(ceo_car);
		dto.setCeo_lat(lat);
		dto.setCeo_lng(lng);

		CeoNyamDAO dao = CeoNyamDAO.getInstance();
		
		int check = dao.updateCeo(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if (check > 0) {
			forward.setRedirect(true);
			forward.setPath("eunchae/view/main.jsp");
		}else if (check == -1) {
			out.println("<script>");
			out.println("alert('�빐�떦 媛�寃� �궗�옣�떂�씠 �븘�떃�땲�떎.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('湲� �벑濡앹쨷�뿉 �삤瑜섍� 諛쒖깮�뻽�뒿�땲�떎.')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
