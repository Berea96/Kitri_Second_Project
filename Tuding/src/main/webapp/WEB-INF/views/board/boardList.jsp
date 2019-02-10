<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.currentPage {
		text-decoration: none;
		color: red;
	}
	.commentForm {
		width: 400px;
		heigth: 100px;
	}
	.commentForm > div {
		position: relative;
		float: left;
		border: 1px solid;
		width: 50px;
		height: 50px;
	}
	.commentForm > div:first-child {
	}
	.commentForm > div:nth-child(2) {
		width: 200px;
	}
	.commentForm > div:nth-child(3) {
		width: 100px;
		height: 25px;
	}
	.commentForm > div:nth-child(4) {
		width: 100px;
		height: 25px;
	}
	.commentForm > div:nth-child(5) {
		width: 50px;
		height: 25px;
	}
	.commentForm > div:nth-child(5) > button {
		width: 50px;
		height: 25px;
	}
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	var currentPage = 1;
	var redirect = 0;
	var maxPage = 0;
	
	$.showBoardList = function(data) {
		console.log(data);
		var result = JSON.parse(data);
		var str = "";
		str += "<tr heigh='30'>" +
	                "<td>글번호</td>" +
	                "<td>제목</td>" +
	               	"<td>작성자</td>" +
	                "<td>조회수</td>" +
	                "<td>좋아요</td>" +
	                "<td>카테고리</td>" +
	                "<td>작성일</td>" +
	            "</tr>"
		$.each(result, (index, item) => {
			str += "<tr>";
			str += "<td>" + item.boardNum + "</td>";
			str += "<td><a class='boardTitle' href='${pageContext.request.contextPath}/board/detail'>" + item.title + "</a></td>";
			str += "<td><a class='boardWriter' href='${pageContext.request.contextPath}/board/listByWriter'>" + item.writer + "</a></td>";
			str += "<td>" + item.readcount + "</td>";
			str += "<td>" + item.likecount + "</td>";
			str += "<td>" + item.category + "</td>";
			str += "<td>" + item.w_date + "</td>";
			str += "</tr>";
		});
		$("#bList").empty();
		$("#bList").append(str);
		
		$(".boardTitle").click((e) => {
			e.preventDefault();
			var target = $(e.target);
			console.log(target);
			var targetData = $($(target).parent().parent().children()[0]).html();
			var targetHref = $(target).attr("href");
			
			console.log(targetData);
			
			$.ajax({
				type: "GET",
				url: targetHref,
				data: {
					"roomname": "${roomname}",
					"boardNum": targetData
				},
				success: (data) => {
					console.log("도착");
					console.log(data);
					var result = eval('(' + data + ')');
					$("#boardListDiv").css("display", "none");
					$("#boardWriteDiv").css("display", "none");
					$("#boardDetailViewDiv").css("display", "block");
					$("#commentWriteForm").css("display", "block");
					$("#commentList").css("display", "block");
					$("#editButton").remove();
					$("#delButton").remove();
					$("#detailBoardNum").html(result.num);
					$("#detailTitle").html(result.title);
					$("#detailWriter").html(result.writer);
					$("#detailWdate").html(result.w_date);
					$("#detailLiked").html(result.likecount);
					$("#detailReadCount").html(result.readcount);
					$("#detailCategory").html(result.category);
					$("#detailContent").html(result.content);
					$("#commentWriter").val('${sessionScope.loginInfo.id}');					
					$("#commentWriteNum").val(result.num);					
					if('${sessionScope.loginInfo.id}' == result.writer) {
						$("#boardDetailViewDiv").append("<button id='editButton' val='" + result.num + "' class='btn btn-primary'>수정</button>");
						$("#boardDetailViewDiv").append("<button id='delButton' val='" + result.num + "' class='btn btn-primary'>삭제</button>");
					}
					
					$("#editButton").click((e) => {
						var num = $(e.target).attr("val");
						var content = $("#detailContent").html();
						if(content.includes("</textarea>") == 0) {
							$("#detailContent").html("<textarea id='editContent' rows='20' cols='72'>" + content + "</textarea>");
						} 
						
						$("#editButton").click(() => {
							console.log("하이");
							$.ajax({
								type: "GET",
								url: "${pageContext.request.contextPath}/board/edit",
								data: {
									"num": num,
									"content": $("#editContent").val()
								},
								success: (data) => {
									location.href = "${pageContext.request.contextPath}/board/home?roomname=${roomname}";							
								}
							});
						});
					});
					$("#delButton").click((e) => {
						var num = $(e.target).attr("val");
						console.log(num);
						
						$.ajax({
							type: "GET",
							url: "${pageContext.request.contextPath}/board/del",
							data: {
								"num": num
							},
							success: (data) => {
								location.href = "${pageContext.request.contextPath}/board/home?roomname=${roomname}";							
							}
						});
					});
					
					$.showCommentList(result.num);
				}
			});
		});
		$(".boardWriter").click((e) => {
			e.preventDefault();
			var target = $(e.target);
			var targetData = $(target).html();
			var targetHref = $(target).attr("href");
		});
	}
	
	$.showBoardPage = function(data) {
		var result = eval('('+data+')');
		var maxCount = Number(result.maxCount);
		maxPage = Math.ceil(maxCount/10);
		currentPage = Number(result.currentPage);
		console.log(currentPage);
		
		console.log(maxPage);
		
		$.getPage(maxPage);
		
		$(".a").click((e) => {
			e.preventDefault();
			var target = $(e.target);
			var targetPage = $(target).html();
			var targetHref = $(target).attr("href");
			console.log(target);
			console.log(targetPage);
			console.log(targetHref);
			console.log(currentPage);
			console.log(Math.ceil((currentPage / 10) - 1));
			
			$(".a").removeAttr("class");
			if(targetPage != '이전' && targetPage != '다음') {
				$(target).attr("class", "currentPage");
				targetPage = Number($(target).html());
				currentPage = targetPage;
			}
			else if(targetPage == '이전') {
				console.log("이전");
				if(currentPage - 10 > 0) {
					currentPage = Math.ceil((currentPage / 10) - 1) * 10;
				}
				else {
					currentPage = 1;
				}
				targetPage = currentPage;
			}
			else {
				console.log("다음");
				currentPage = Math.floor(((currentPage - 1) / 10) + 1) * 10 + 1;
				if(currentPage > maxPage) {
					currentPage = maxPage;
				}
				targetPage = currentPage;
			}
			console.log(maxPage);
			console.log(currentPage);
			console.log(targetPage);
			
			redirect = 1;
			
			$.ajax({
				type: "GET",
				url: targetHref,
				data: {
					"page" : targetPage,
					"roomname" : ${roomname}
				},
				success: (data) => {
					$.showBoardList(data);
					console.log(${sessionScope.backPage});
					$.chagePage();
				}
			});
		});
	}
	
	$.getPage = function(maxPage) {
		var str = "";
		
		var startPage = Math.floor(currentPage /10) * 10 + 1;
		var endPage = startPage + 9;
		
		if(endPage > maxPage) { 
			endPage = maxPage;
		}
		
		str += "<table id='pageTable' border='1px' width='300px'><tr>";
		if(startPage != currentPage) {
			str += "<td><a class='a' href='${pageContext.request.contextPath}/board/listAll'>이전</a></td>";
		}
		else {
			str += "<td><a class='a' href=''>이전</a></td>";
		}
		for(var i = startPage; i <= endPage; i++) {
			if(currentPage == i) {
				str += "<td><a class='a currentPage' href='${pageContext.request.contextPath}/board/listAll'>" + i + "</a></td>";
			}
			else {
				str += "<td><a class='a' href='${pageContext.request.contextPath}/board/listAll'>" + i + "</a></td>";
			}
		}
		if(endPage != maxPage) {
			str += "<td><a class='a disable' href='${pageContext.request.contextPath}/board/listAll'>다음</a></td>";
		}
		else {
			str += "<td><a class='a' href=''>다음</a></td>";
		}
		str += "</table></tr>";
		
		$("#pageForm").empty();
		$("#pageForm").append(str);
	}
	
	$.chagePage = function() {
		var table = $("#pageTable");
		var tdList = $(table).children().children().children();
		console.log(tdList);
		console.log($(tdList[0]));
		
		var startPage = Math.floor((currentPage - 1) /10) * 10 + 1;
		var endPage = startPage + 9;

		for(var i = 0; i < $(tdList).length; i++) {
			
			if(i == 0) {
				if(startPage != 1) {
					$(tdList[i]).children().attr("href", "${pageContext.request.contextPath}/board/listAll");
				}
				else {
					$(tdList[i]).children().attr("href", "");
				}
			}
			else if(i == 11) {
				if(endPage != maxPage) {
					$(tdList[i]).children().attr("href", "${pageContext.request.contextPath}/board/listAll");
				}
				else {
					$(tdList[i]).children().attr("href", "");
				}
			}
			else {
				if(startPage + i - 1 > maxPage) {
					$(tdList[i]).children().html('');
				}
				else {
					if(startPage + i - 1 == currentPage) {
						$(tdList[i]).children().attr('class', 'a currentPage').html(startPage + i - 1);
					}
					else {
						$(tdList[i]).children().attr('class', 'a').html(startPage + i - 1);
					}
				}
			}
		}
	}
	
	$.showCommentList = function(num) {
		$.ajax({
			type: "GET",
			url: "${pageContext.request.contextPath}/comment/listByBoardNum",
			data: {
				"num" : num
			},
			success: (data) => {
				var result = JSON.parse(data);
				
				var str = "";
				
				$("#commentList").empty();
				$.each(result, (index, item) => {
					str += "<div class='commentForm'>";
					str += "<div>" + item.num + "</div>";
					str += "<div>" + item.content + "</div>";
					str += "<div>" + item.w_date + "</div>";
					str += "<div>" + item.writer + "</div>";
					if(item.writer == '${sessionScope.loginInfo.id}'){
						str += "<div><button class='commentDelButton'>삭제</button></div>";
					}
				str += "</div>";
				});
				
				$("#commentList").append(str);
			}
		});
	}
	
	$(document).ready(() => {
		$(() => {
			$.ajax({
				type: "GET",
				url: "${pageContext.request.contextPath}/board/listAll",
				data: {
					"page": currentPage,
					"roomname": "${roomname}"
				},
				success: (data) => {
					$.showBoardList(data);
				}
			});
			$.ajax({
				type: "GET",
				url: "${pageContext.request.contextPath}/board/listCount",
				data: {
					"roomname": "${roomname}"
				},
				success: (data) => {
					$.showBoardPage(data);
				}
			});
		});
		
		$("#boardWriteFormButton").click(() => {
			$("#boardListDiv").css("display", "none");
			$("#boardWriteDiv").css("display", "block");
			$("#boardDetailViewDiv").css("display", "none");
			$("#commentList").css("display", "none");
			$("#commentWriteForm").css("display", "none");
		});
		$("#boardListButton, #backToList").click(() => {
			$("#boardListDiv").css("display", "block");
			$("#boardWriteDiv").css("display", "none");
			$("#boardDetailViewDiv").css("display", "none");
			$("#commentList").css("display", "none");
			$("#commentWriteForm").css("display", "none");
		});
		$("#writeButton").click(() => {
			var title = $("#writeTitle").val();
			var content = $("#writeContent").val();
			
			if(title == "" || content == "") {
				alert("빈칸없이");
			}
			else {g
				$("#writeForm").submit();
			}
		});
		$("#commentWriteButton").click(() => {
			var num = $("#commentWriteNum").val();
			var writer = $("#commentWriter").val();
			var content = $("#commentContent").val();
			
			if(content == '') {
				alert("빈칸없이");
			}
			else {
					$.ajax({
						type: "GET",
						url: "${pageContext.request.contextPath}/comment/write",
						data: {
							"board_num": num,
							"writer": writer,
							"content": content
						},
						success: (data) => {
							$.showCommentList(num);
						}
					})
			}
		});
	});
</script>
</head>
<body>	
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand"
					href="${pageContext.request.contextPath}/member/home">Study
					Anywhere</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">

				<!--       <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">About</a></li>
        <li><a href="#">Gallery</a></li>
        <li><a href="#">Contact</a></li>
      </ul>
 -->
				<ul class="nav navbar-nav navbar-right">

					<c:choose>
						<c:when test="${empty sessionScope.loginInfo.id}">
							<li><a data-toggle="modal" data-target="#loginForm"><span
									class="glyphicon glyphicon-log-in"></span> Sign in</a></li>
							<li><a data-toggle="modal" data-target="#joinForm"><span
									class="glyphicon glyphicon-user"></span> Sign up</a>
						</c:when>

						<c:otherwise>
							<li><a href="${pageContext.request.contextPath}/member/info"><span
									class="glyphicon glyphicon-log-in"></span> My page</a></li>
							<li><a
								href="${pageContext.request.contextPath}/member/logout"><span
									class="glyphicon glyphicon-log-out"></span> Sign out</a>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>
	
	<div id="boardListDiv">
		<h3>${roomname}방의 게시판</h3>
		<button id="boardWriteFormButton" class="btn btn-primary">글작성</button>
        <table id="bList" width="600" border="3" bordercolor="lightgray">
        </table>
        <div id="pageForm">
   		</div>
    </div>
    <div id="boardWriteDiv" style="display: none;">
    	<h3>글작성</h3>
    	<button id="boardListButton">글목록</button>
    	<form id="writeForm" action="${pageContext.request.contextPath}/board/write" enctype="multipart/form-data">
    		<input type="hidden" name="roomname" value="${roomname}">
			<table width="600" border="3">
				<tr>
					<td>제목</td>
					<td>
						<input id="writeTitle" type="text" name="title">
					</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>
						<input type="text" name="writer" value="${sessionScope.loginInfo.id}" readonly> 
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea id="writeContent" name="content" rows="20"
						cols="72">
    					</textarea>
					</td>
				</tr>
				<tr>
					<td>파일</td>
					<td>
					</td>
				</tr>
				<tr>
					<td>카테고리</td>
					<td>
						<select id="writeCategory" name="category">
						<option value="공부" selected="selected">공부
						<option value="기타">기타
						</select>
					</td>
				</tr>
				<tr>
					<td>
					</td>
					<td>
						<input id="writeButton" type="button" value="작성하기">
					</td>
				</tr>
			</table>
    	</form>
    </div>
    
    <div id="boardDetailViewDiv" style="display:none;">
			<button id="backToList" class="btn btn-primary">뒤로가기</button>
    	<table width="500px" border="1px">
    		<tr height="30">
                <td>글번호</td>
                <td>제목</td>
                <td>작성자</td>
                <td>작성일</td>
                <td>조회수</td>
                <td>좋아요</td>
                <td>카테고리</td>
            </tr>
			<tr>
				<td id="detailBoardNum"></td>
				<td id="detailTitle"></td>
				<td id="detailWriter"></td>
				<td id="detailWdate"></td>
				<td id="detailReadCount"></td>
				<td id="detailLiked"></td>
				<td id="detailCategory"></td>
			</tr>    	
			<tr>
				<td>내용</td>
				<td id="detailContent" colspan ="6"></td>
			</tr>
			<tr height='20px'>
				<td>좋아요</td>
				<td><img class="btn-img" src="${pageContext.request.contextPath}/resources/img/like2.jpg"  width="20" height="20" /></td>
			</tr>
    	</table>
    	<h4>댓글</h4>
    	
    </div>
		<div id="commentWriteForm" style="display:none;">
			<form id="commentForm" action="" method="GET">
	    		<input type="hidden" id="commentWriteNum" name="board_num">
	    		<input type="hidden" id="commentWriter" name="writer">
	    		<input type="text" id="commentContent" name="content">
	    		<input id="commentWriteButton" type="button" value="작성">
	    	</form>
		</div>
		<div id="commentList" style="display:none;">
		</div>
</body>
</html>