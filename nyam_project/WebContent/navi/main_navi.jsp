<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/navi_ceo.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/add.css">

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.js"></script>

	
<script>






$(function(){
	

	
	
	
	var usertype = '<%=(String)session.getAttribute("user")%>';
	
	if(usertype == "member"){
		$("#nonuser_navi").css({
			"display":"none"
		});
		$("#ceo_navi").css({
			"display":"none"
		});
		$("#user_navi").css({
			"display":"block"
		});
		$("#admin_navi").css({
			"display":"none"
		});
		$("#main_div").css({
			"display":"block"
		});
	}else if(usertype == "ceo"){
		$("#nonuser_navi").css({
			"display":"none"
		});
		$("#ceo_navi").css({
			"display":"block"
		});
		$("#user_navi").css({
			"display":"none"
		});
		$("#admin_navi").css({
			"display":"none"
		});
		$("#main_div").css({
			"display":"block"
		});
	}else if(usertype == "admin"){
		$("#nonuser_navi").css({
			"display":"none"
		});
		$("#ceo_navi").css({
			"display":"none"
		});
		$("#user_navi").css({
			"display":"none"
		});
		$("#admin_navi").css({
			"display":"block"
		});
		$("#main_div").css({
			"display":"none"
		});
	}else if(usertype == null){
		$("#nonuser_navi").css({
			"display":"block"
		});
		$("#ceo_navi").css({
			"display":"none"
		});
		$("#user_navi").css({
			"display":"none"
		});
		$("#admin_navi").css({
			"display":"none"
		});
		$("#main_div").css({
			"display":"block"
		});
	}
	
	
	
});
	
</script>

	


</head>
<body>

<style>
.searchInput{
	width: 250px;
	height: 30px;
	border-radius: 15px;
	border: 1px solid black;
	padding-left: 15px;
}

.pagination{justify-content: center;}
.tac{text-align:center}
.panel{margin-top:30px}
select{
	border: 1px solid #ccc;
	border-radius: 0px;
	-moz-border-radius: 0px;
	-webkit-border-radius: 0px;
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
	font: inherit;
	font-size: 1em;
	background: #fff url('/nyam_project/kimmin/image/select.png') 95% center no-repeat;
	padding: 0 15px 0 5px;
	height: 30px
}
select::-ms-expand {
    display: none;
}
.table{font-size:14px}
</style>


	<div class="wrapper">
		<div class="menubar">
			<div class="naviBox" id="main_div">
				<h2><a href="<%=request.getContextPath()%>/eunchae/view/main.jsp">NyamNyam ????</a></h2>
				<ul>
					
					<li><a href="<%=request.getContextPath()%>/story_main.do">??????
							?????????</a></li>
							<li><a href="<%=request.getContextPath()%>/list_main.do">??????
							?????????</a></li>
				</ul>
			</div>
			<div class="naviBox" id = "nonuser_navi">
				<!-- ??????????????? (?????????X)  -->
				<h3>???????????????</h3>
				<ul>
					<li><a href="#" data-bs-toggle="modal"
						data-bs-target="#staticBackdrop">?????????</a></li>
						
					
				</ul>
			</div>
			<div class="naviBox" id = "user_navi" style="display:none;">
				<h3>???????????????</h3>
				<!-- ???????????? -->
				<ul>
					<li><a href="<%=request.getContextPath()%>/review_mylist.do">??????
							????????? </a>
					<li><a href="<%=request.getContextPath()%>/reply_mylist.do">??????
							?????? </a>
					<!--  <li><a href="<%=request.getContextPath()%>/reply_mylist.do">????????????
					</a>-->
					<li><a
						href="<%=request.getContextPath() %>/member_cs.do?id=${id}">????????????</a></li>
					<li><a href="<%=request.getContextPath()%>/owner_logout.do">????????????</a></li>
				</ul>
			</div>
			<div class="naviBox" id = "ceo_navi" style="display:none;">
				<!-- ???????????????  -->
				<h3>???????????????</h3>
				<ul>
					<li><a
						href="<%=request.getContextPath() %>/owner_insert.do?id=${id}">??????
							?????? ??????</a></li>
					<li><a
						href="<%=request.getContextPath() %>/owner_reply.do?id=${id}">???
							?????? ????????? / ?????? ??????</a></li>
					<!-- <li><a
						href="<%=request.getContextPath() %>/owner_my_reply.do?id=${id}">??????
							??? ?????? ??????</a></li>-->
					<li><a
						href="<%=request.getContextPath() %>/owner_cs_review.do?id=${id}">?????????
							?????? / ?????? ??????</a></li>
					<li><a href="<%=request.getContextPath() %>/owner_mypage_pwd.do?id=${id}">?????? ??????</a></li>
					<li><a
						href="<%=request.getContextPath() %>/owner_cs.do?id=${id}">????????????</a></li>
					<li><a href="<%=request.getContextPath()%>/owner_logout.do">????????????</a></li>
				</ul>
			</div>
			<div class="naviBox" id = "admin_navi" style="display:none;">
				<!-- ???????????????  -->
				<h3>NyamNyam ????</h3>
				<h3>????????? ?????????</h3>
				<ul>
					<li><a
						href="<%=request.getContextPath()%>/admin_member_Management.do">????????????</a></li>

					<li><a
						href="<%=request.getContextPath()%>/admin_reply_Management.do">????????????</a></li>

					<li><a
						href="<%=request.getContextPath()%>/admin_review_Management.do">????????????</a></li>

					<li><a
						href="<%=request.getContextPath()%>/admin_ceo_Management.do">????????????</a></li>

					<li><a
						href="<%=request.getContextPath()%>/admin_service_Management.do">????????????</a></li>
					<li><a href="<%=request.getContextPath()%>/owner_logout.do">????????????</a></li>
				</ul>
			</div>
		</div>
		
		<jsp:include
						page="../ogj/login/login_main2.jsp"></jsp:include>

		<div class="center">
</body>
</html>



