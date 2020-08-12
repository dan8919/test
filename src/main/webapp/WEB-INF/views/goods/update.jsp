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
	<script src="/resources/js/uploadfn.js" type="text/javascript"></script>

<title>Insert title here</title>
<style type="text/css">
	.fileDrop{
		width: 80%;
		height: 200px;
		border: 1px solid green;
		margin: autol
	}
	.uploadedList li{
		list-style: none;
		margin: 5px;
	}
	.oriFileName{
		overflow: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
		
	}
</style>
</head>
<body>
<jsp:include page="../includes/header.jsp" />
	<div class="container">
		<div class="row text-center">
			<h1>제품 수정</h1>
		</div>
		
		<div class="row">
		<form action="/goods/update" method="post">
			<input type="hidden" name="gno" value="${gooDto.gno }">
						
			<input name="cno" id="cno" class="form-control" value="${gooDto.cno }" type="hidden">
				
			<div class="form-group">
				<label for="cname">카테고리</label>
				<input name="cname" id="cname" value="${gooDto.cname }" class="form-control" readonly>
			</div>
			<div class="form-group">
				<label for="gname">상품명</label>
				<input name="gname" id="gname" value="${gooDto.gname }" class="form-control" required>
			</div>
			<div class="form-group">
				<label for="price">금액</label>
				<input name="price" id="price" type="number" value="${gooDto.price }" class="form-control" required>
			</div>
		
			
		</form>
		
		</div><!-- class = row 본문form 그룹 -->
		
		<div class="row">
			<div id="btn-group" class="form-group">
				<button class="btn btn-primary" id="update_btn">수정</button>
				<button class="btn btn-info" id="list_btn">목록</button>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">

		
		$(document).ready(function(){



			$("#update_btn").click(function(event){
				event.preventDefault();

				$("form").submit();
			}); 
			
			$("#list_btn").click(function(event){
				event.preventDefault();
				location.assign("/goods/list");
			});		
			

		});
	</script>
	
</body>
</html>