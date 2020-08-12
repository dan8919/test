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
<%@ include file="../includes/header.jsp" %>

	<div class="container">
		<div class="row text-center">
			<h1>주문이 완료되었습니다</h1>
		</div>
		<br>
		<br>
		<br>
		
		<div class="row" align="center">
			<div class="form-group">
				<button class="btn btn-info" id="mypur_btn">마이페이지</button>
				<button class="btn btn-info" id="list_btn">목록</button>
			</div>
		</div><!-- class = row (btn) -->
		
		
				
	</div><!-- class = container -->
	
	<script type="text/javascript">		

		$(document).ready(function(){
			
			$("#list_btn").click(function(){
				location.assign("/board/list");
			});
		});

	</script>
	
</body>
</html>