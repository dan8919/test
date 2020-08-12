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

</style>
</head>
<body>
<jsp:include page="../includes/header.jsp" />
	 
	 <div class="container">
	 	<div class="row text-center">
	 		<h1>제품추가</h1>
	 	</div>
	 
	 	<div class="row">
			<form action="/goods/insert" method="post">
				<div class="form-group">
					<label for="ccno"></label>
						<select name="ccno" id="ccno">
						<option>-[필수]카테고리를 선택하세요</option>
						<option disabled="disabled">───────────────</option>

					<c:forEach items="${selectCateList }" var="category">
				
							<option value="${category.cname }" name="${category.cno }">${category.cname }</option>

					</c:forEach>
						</select>
				</div>
			
					<input id="cno" name="cno" class="form-control" >

				<div class="form-group">
					<label for="gname">상품명</label>
					<input name="gname" id="gname" class="form-control" required>
				</div>
				<div class="form-group">
					<label for="price">금액</label>
					<input name="price" id="price" class="form-control" required>
				</div>

			
			
			</form>
						
			<div class="form-group">
				<button class="btn btn-primary" id="insert_btn">등록</button>
				<button class="btn btn-info" id="listbtn" onclick="location.href='/goods/list'">목록</button>
			</div>
	 	</div>
	 </div>
	 
	 <script type="text/javascript">
		$(document).ready(function(){

			var cno;
			
			$("#ccno").change(function(){
				cno = $("#ccno option:selected").attr("name");
			
				$("#cno").val(cno);
				
			});


			
			$("#insert_btn").click(function(event){
				event.preventDefault();

				if(cno != null) {
				$("form").submit();
				} else {
					alert("카테고리를 선택해주세요");
				}
				
			});

		});
			
	 </script>
	 
</body>
</html>