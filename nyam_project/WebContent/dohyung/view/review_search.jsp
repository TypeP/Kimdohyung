<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" href="favicon.ico">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/main.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/add.css">

<!-- 합쳐지고 최소화된 최신 CSS --> <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> <!-- 부가적인 테마 --> <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"> <!-- 제이쿼리 --> <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> <!-- 합쳐지고 최소화된 최신 자바스크립트 --> <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>



<style type="text/css">
.title{

   
   width:400px;
   padding-left:200px;
   margin-top:30px;
   margin-left:50px;
   }
   
   .titls{
   
   width:500px;
 
   }
   
   .titls td{
   font-size:32px;
   
   
   }
   
   .cont {
   margin-top:30px;
   padding-left:200px;
   }
   
   .dd{
   font-size:25px;
   font-style: bold;
   }
   
   .cont-item{
      size: 10%;
      font-size: 20px;
   }
   
   .table_search td{
      font-size: 20px;
   }
   
    .cont-textarea {
   font-size:18px;
}
   
 
   
</style>
</head>
<body>


<jsp:include page="../../navi/main_navi.jsp"/>

<div class="main_title">
     <table class="titls" border="0" cellspacing="0">
     <c:set var="field" value="${search_field}"/>
         <tr>
         
         <c:if test="${field=='title_content'}">
         
            <td>내용 검색 리스트</td>
      </tr>
      
      </c:if>
      <c:if test="${field=='rate'}">
         
            <td> 별점 검색 리스트</td>
      </tr>
      
      </c:if>
  
       </table>
       
       
       
       
   </div>    
   
   <div style="margin-top: 30px">

      <c:set var="list" value="${List}"/>
         <c:if test="${!empty list }">
            <c:forEach items="${list }" var="dto">
            <table class="table_search" border="0" cellspacing="0" width="450px">
         <tr>
         <td rowspan="5">
            <a href="<%=request.getContextPath()%>/owner_contents.do?no=${dto.getReview_ceo_num()}&page=${page}">
               <img src="<%=request.getContextPath()%>/image/userimage/${dto.getReview_image()}"
                     width="150" height="180" style="margin: 20px"></a>
            <th colspan="4" style="font-size: 30px">${dto.getReview_title()}</th>
         </td>
         </tr>
         
         <tr>
         <th class="cont-item">식당이름</th> <td>${dto.getCeo_name() }</td>
         <th class="cont-item">위치</th> <td>${dto.getCeo_addr() }</td>
         </tr>
         
         <tr>
         <th class="cont-item">평점</th>   <td>${dto.getReview_rate() }(${dto.getCeo_avgrate()})</td>
         <th class="cont-item">다녀간날</th> <td>${dto.getReview_went().substring(0,10) }</td>
         </tr>
         
         <tr>
         <th colspan="4">
         <textarea class="cont-textarea" rows="5" cols="100">${dto.getReview_cont() }</textarea>
         <hr style="height: 3px">
         </th>
         </tr>
         </c:forEach>
      </c:if>
         
         
         <c:if test="${empty list }">
         
         <tr>
         <td colspan = "5" align="center">
         <h3>검색된 게시물 리스트가 없습니다..</h3>
         </td>
      
         </c:if>
         
         </table>
         </div>
         
            <%--페이징 --%>
   <nav class="tac">
      <ul class="pagination">
         <li>
         
         <c:if test="${page == 1 }">
               <a href="review_searchs.do?page=${startBlock }&search_field=${search_field }&search_keyword=${search_keyword }"
                  aria-label="Previous">
            </c:if> 
            
            <c:if test="${page != 1 }">
               <a href="review_searchs.do?page=${page -1 }&search_field=${search_field }&search_keyword=${search_keyword }"
                  aria-label="Previous">
            </c:if>
            
            <span aria-hidden="true">&laquo;</span> </a>
         </li>





         <li><c:forEach begin="${startBlock }" end="${endBlock }" var="i">
               <c:if test="${i == page }">
                  <a href="review_searchs.do?page=${i }&search_field=${search_field }&search_keyword=${search_keyword }">${i }</a>
               </c:if>

               <c:if test="${i != page }">
                  <a href="review_searchs.do?page=${i }&search_field=${search_field }&search_keyword=${search_keyword }">${i }</a>
               </c:if>
            </c:forEach></li>




         <li>
         <c:if test="${endBlock == allPage }">
            <a href="review_searchs.do?page=${endBlock }&search_field=${search_field }&search_keyword=${search_keyword }"
               aria-label="Next">
         </c:if> 
            
         <c:if test="${endBlock != allPage }">
            <a href="review_searchs.do?page=${page +1 }&search_field=${search_field }&search_keyword=${search_keyword }"
               aria-label="Next">
         </c:if> <span aria-hidden="true">&raquo;</span> </a>
         </li>


      </ul>

   </nav>

<%--페이징 --%>
      
         
         
   
   <%--    <c:if test="${page >block }">
         <a href="review_searchs.do?page=1&search_field=${search_field }&search_keyword=${search_keyword}">[맨처음]   </a>
         <a href="review_searchs.do?page=${startBlock -1 }&search_field=${search_field }&search_keyword=${search_keyword}">◀</a>
      </c:if>
      <c:forEach begin="${startBlock }" end="${endBlock }" var="i">
         <c:if test="${i==page }">
            <b><a href="review_searchs.do?page=${i }&search_field=${search_field }&search_keyword=${search_keyword}">[${i }]</a></b>
         
         </c:if>
         <c:if test="${i!=page }">
            <a href="review_searchs.do?page=${i }&search_field=${search_field }&search_keyword=${search_keyword}">[${i }]</a>
         
         </c:if>
      
      </c:forEach>
      <c:if test="${endBlock < allPage }">
         <a href="review_searchs.do?page=${endBlock +1 }&search_field=${search_field }&search_keyword=${search_keyword}">▶</a>
         <a href="review_searchs.do?page=${allPage }&search_field=${search_field }&search_keyword=${search_keyword}">[마지막]</a>
      
      </c:if>
      <br><br> --%>
   
   

       
       
   </div>
   
   </div>   






</div>

</body>
</html>