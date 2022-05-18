<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/eunchae/cssCeo/title.css">
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800&amp;subset=korean" rel="stylesheet">
</head>

<link rel="stylesheet" href="<%=request.getContextPath()%>/eunchae/cssCeo/table.css">

<link rel="stylesheet" href="/css/bootstrap.css">
<script type="text/javascript" src="/js/bootstrap.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> <!-- 부가적인 테마 --> <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<body>
<jsp:include page="../../navi/main_navi.jsp"/>
	  <section class="right_container">
	<h1 class="title"> 리뷰리스트</h1>
     
     <div class="panel panel-primary">
		<div class="panel-heading">리뷰목록</div>
		<table class="table">
			<tr class="user-info-header">
				<th>글번호</th>
				<th>리뷰제목</th>
				<th>작성자</th>
				<th>작성일</th>
				
			</tr>
			
	
	
	
		<!-- <div class="review">
			<h1> 리뷰리스트 </h1>
			<br>
			
			<th>처리상태</th>
			<br>
			</div>
			<table border="0" cellspacing="0"width="800">
				<tr>
					<th>글번호</th>
					<th>리뷰제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>처리상태</th>
				</tr> -->
				
				<c:set var="list" value="${List }"/>
				<c:if test="${!empty list }">
					<c:forEach items="${list }" var="dto">
					
						<tr>
							<td>${dto.getReview_num() }</td>
							<td>	<!-- 제목 클릭시 리뷰페이지로 이동 -->
							<a href="<%=request.getContextPath() %>/review_content.do?no=${dto.getReview_num()}">${dto.getReview_title() }</a>	
							</td>
							<td>${dto.getReview_id() }</td>
							<td>${dto.getReview_date().substring(0, 10) }</td>
							
					</c:forEach>
				</c:if>
				
				<c:if test="${empty list }">
					<tr>
						<td colspan="5" align="center">
							<h3>작성한 스토리가 없습니다.</h3>
						</td>
					</tr>
				</c:if>
			</table>
			</div>
			<br>
			<nav class="tac">
			<c:if test="${page > block}">
					<a href="review_mylist.do?num=${num }&page=1">◀◀</a>
					<a href="review_mylist.do?num=${num }&page=${startBlock - 1 }">◀</a>
				</c:if>
			
				<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
					<c:if test="${(i == page)}">
						<b><a href="review_mylist.do?num=${num }&page=${i }">[${i }]</a></b>
					</c:if>
					
					<c:if test="${i != page }">
						<a href="review_mylist.do?num=${num }&page=${i }">[${i }]</a>
					</c:if>
				</c:forEach>
			
				<c:if test="${endBlock < allPage }">
					<a href="review_mylist.do?num=${num }&page=${endBlock + 1 }">▶</a>
					<a href="review_mylist.do?num=${num }&page=${allPage }">▶▶</a>
				</c:if>
				</nav>
				</section>
				<jsp:include page="../../navi/footer.jsp"/>
		</div>
		<br><br><br>
		</div>

</body>
</html>