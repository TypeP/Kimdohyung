<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="icon" href="favicon.ico">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/main.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/add.css">

<!-- 합쳐지고 최소화된 최신 CSS --> <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> 부가적인 테마 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"> 제이쿼리 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> 합쳐지고 최소화된 최신 자바스크립트 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script> --> 


<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dohyung/js/re.js"></script>
<script type="text/javascript">

/* function like_func(){
	  var frm_read = $('#frm_read');
	  var boardno = $('#boardno', frm_read).val(); */
	  //var mno = $('#mno', frm_read).val();
	  //console.log("boardno, mno : " + boardno +","+ mno);
	  






</script>



<style type="text/css">
.cont {
	margin-top:100px;
	padding-left:80px;
	}
	
#title{
	padding-left:200px;
}

</style>
</head>
<body>

<jsp:include page="../../navi/main_navi.jsp"/>
	<div class="cont">
	
		<c:set var="dto" value="${cont }"/>
	
		<hr width="65%" color="red">
		<div id="title">
			<h3>리뷰 게시물 상세 내역 페이지</h3>
		</div>
		<hr width="65%" color="red">
		<br>
		<br>
		
		 <div style="margin-top: 30px">
		<table class="table_search" border="0" cellspacing="0" width="450px">
         <tr>
         <td rowspan="6">
               <img src="<%=request.getContextPath()%>/image/userimage/${dto.getReview_image()}"
                     width="150" height="180" style="margin: 20px">
            <th colspan="4" style="font-size: 30px">${dto.getReview_title()}</th>
         </td>
         </tr>
         
         <tr>
         <th class="cont-item">작성자</th> <td>${dto.getReview_id() }</td>
         <th class="cont-item">작성일자</th> <td>${dto.getReview_date() }</td>
         </tr>
         
         <tr>
         <th class="cont-item">평점</th>   <td>${dto.getReview_rate() }</td>
         <th class="cont-item">다녀간날</th> <td>${dto.getReview_went().substring(0,10) }</td>
         </tr>
         
         <tr>
         <th colspan="4">
         <textarea class="cont-textarea" rows="10" cols="100">${dto.getReview_cont() }</textarea>
         
         </th>
         </tr>
         
         <tr>
				
				
			<form id="like_form" name="like_form" method="post">
  		    <input type="hidden" id="num" name="num" value="${dto.getReview_num() }"> 
  		    <td colspan="4" align="center">
  		    
  		    
  		    <img src='<%=request.getContextPath()%>/dohyung/upload/dislike.png' id='likes'
				width="67" height="67"> 
  		     <!--  <input type="button" value="좋아요" id="likes">  -->
  		    <div id="like_result">${dto.getReview_like()}</div>
  		    </td>
  		    </form> 
  		    
  		    
  		    
  		   
  		    </tr>
         
         
		
	
         
		
		
		<%-- 
		
		
			<table border="0" cellspacing="0" width="750">
			
			<tr>
				<th>작성자</th>
				<td>${dto.getReview_id() }</td>
			</tr>
			
			
			<tr>
				<th>글제목</th>
				<td>${dto.getReview_title() }</td>
			</tr>
			
			
			<tr>
				<th>글내용</th>
				<td>
					<textarea rows="7" cols="40" readonly>${dto.getReview_cont()}</textarea>
				</td>
			</tr>
			
			<tr>
				<th>리뷰이미지</th>
				<td>
						<img src="<%=request.getContextPath()%>/image/usermage/${dto.getReview_image()}"
						width="150" height="150"></td> 
			</tr>
			
			<tr>
				<th>평점</th>
				<td>${dto.getReview_rate() }</td>
			</tr>
			 --%>
			
			
			
  <%-- 		    
  		    <td>
  		    <c:if test="${isExistsFavoriteData }">
			<span id="favorite" syle="color:red;">♥</span>
			</c:if>
			<c:if test="${!isExistsFavoriteData }">
			<span id="favorite"  syle="color:red;">♡</span>
			</c:if><td> --%>
			
			

  		 
  		    
  <%-- 		    <a href='javascript: like_func();'><img src='<%=request.getContextPath()%>/upload/dislike.png' id='like_img'></a> --%>
  		    
 
				
   			

				
				
			<%-- 	
			</tr>
			
			
			<tr>
				<th>다녀간 날짜</th>
				<td>${dto.getReview_went().substring(0,10) }</td>
			</tr>
			 --%>
			

			

			<tr>
				<td colspan="4" align="center">
					<input type="button" value="목록으로" onclick="location.href='owner_contents.do?no=${dto.getReview_ceo_num()}'">	
				
				</td>
			
			</tr>

			
			</table>
			</div>
			
			
			
			
			<form name="reply_su" id="reply_su" method="post">
			<input type="hidden" id="r_c_num" name="r_c_num" value="${dto.getReview_ceo_num() }">
			<input type="hidden" id="r_num" name="r_num" value="${dto.getReview_num() }"> 
			
			<table border="0" cellspacing="0">
				<tr>
					<th colspan="3">댓글</th>
				</tr>
				<tr>
					
					<td>&nbsp;<input type="text" id = "cont" name="cont" size=100> </td>
					<td><input type="button" value="등록" id="btns"><input type="reset"value="취소"></td>
				</tr>
			</table>
		</form>	
		
			<table id="reply" cellspacing="0">
				<tr>
					<th>작성자</th><th width=650></th><th></th>
					
				</tr>
				
			</table>
			
			
			
			
		</div>
			
	</div>
			
			

</body>
</html>