<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<div class="row text-center">
			<h1>글 자세히 보기</h1>
		</div>
		${searchType} ,  ${keyword}
		<div class="row">
			<div class="form-group">
				<label for="title">제목</label>
				<input readonly class="form-control" value="${vo.title }">
			</div>		
			
			<div class="form-group">
				<label for="writer">작성자</label>
				<input readonly class="form-control" value="${vo.writer }">
			</div>
			
			<div class="form-group">
				<label for="content">내용</label>
				<textarea readonly class="form-control" rows="5">${vo.content }</textarea>
			</div>			
		</div>
		
		<div class="row">
			<div class="form-group">
				<button class="btn btn-warning" id="reply_form">댓글</button>
				<button class="btn btn-primary" id="update_btn">수정</button>
				<button class="btn btn-danger" id="delete_btn">삭제</button>
				<button class="btn btn-info" id="list_btn">목록</button>
			</div>
		</div><!-- class = row (btn) -->
		
		<div class="row">
			<div class="collapse" id="myCollapse">
				<div class="form-group">
					<label for="replyer">작성자</label>
					<input class="form-control" id="replyer">
				</div>
				<div class="form-group">
					<label for="replytext">내용</label>
					<input class="form-control" id="replytext">
				</div>
				
				<div class="form-group">
					<button class="btn btn-primary" id="replyInsert_btn">댓글 등록</button>
				</div>
			</div>
		</div><!-- class = row (clps) -->		
		
	</div>
	
	<script type="text/javascript">
		var bno = ${vo.bno};
	
		$(document).ready(function(){
			$("#replyInsert_btn").click(function(){
				var replyer = $("#replyer").val();
				var replytext = $("#replytext").val();
				console.log(replyer);				
				console.log(replytext);				
			});
		
			$("#reply_form").click(function(){
				$("#myCollapse").collapse("toggle");
			});
			
			$("#update_btn").click(function(){
				location.assign("/board/update/${vo.bno}");
			});
			$("#delete_btn").click(function(){
				location.assign("/board/delete/${vo.bno}");
			});
			$("#list_btn").click(function(){
				location.assign("/board/searchlist?searchType=${searchType}&keyword=${keyword}");
			});
		});
	</script>
	
</body>
</html>