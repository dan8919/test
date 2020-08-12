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
			<h1>주문서 작성</h1>
		</div>
		
		<form action="/order/complete" method="post">
		<div class="row">
			<div class="form-group">
				<input readonly class="form-control" name="id" value="${sessionScope.login.id}">
			</div>		
			<div class="form-group" hidden>
				<label for="title">제목</label>
				<input readonly class="form-control" value="${vo.title }">
			</div>		
			<div class="form-group" hidden>
				<label for="cno">카테코드</label>
				<input readonly class="form-control" name="cno" value="${vo.cno }">
			</div>		
			<div class="form-group">
				<label for="cname">카테고리</label>
				<input readonly class="form-control" name="cno" value="${getCateData.cname }">
			</div>		
			<div class="form-group" hidden>
				<label for="gno">상품코드</label>
				<input readonly class="form-control" name="gno" value="${getGoodsData.gno }">
			</div>		
			<div class="form-group">
				<label for="gname">상품명</label>
				<input readonly class="form-control" name="gname" value="${getGoodsData.gname }">
			</div>		
			<div class="form-group">
				<label for="cno">금액</label>
				<input readonly class="form-control" value="${getGoodsData.price }">
			</div>		
			

			<!-- 
			<div class="form-group">
				<label>상품이미지</label>
				<ul class="uploadedList clearfix">
				
				</ul>
			</div>
			 -->
			
			
				
		</div><!-- class = row 본문form 그룹 -->
		<div class="row">
			<div class="form-group">
				<button class="btn btn-success" id="order_btn" >구매확정</button>
				<button class="btn btn-info" id="list_btn">목록</button>
			</div>
		</div><!-- class = row (btn) -->
		</form>
		
		
		
				
	</div><!-- class = container -->
	
	<script type="text/javascript">		
	
		var bno = ${vo.bno};

		$(document).ready(function(){

			/*
			$.getJSON("/getAttach/"+bno, function(arr){

				for(var i=0; i<arr.length; i++){
					//a 링크 클릭시 파일 열기
					var str = '<li class="col-xs-4">';
					str += '<a href="/displayfile?fileName=' + getImgLink(arr[i]) + '">';
					//썸네일이미지 삽입
					if(checkImage(arr[i])){
						//이미지파일이면: result = 썸네일이미지 (원본의 50*50)
						str += '<img src = "/displayfile?fileName=' + arr[i] + '"/>';							
					}else{
						//이미지파일이 아니면
						str += '<img src = "/resources/et.png"/>';
					}						
					str += '</a>'; //a링크 태그 종료
					str += '<p class="oriFileName">';
					str += getOriginalName(arr[i]);
					str += '</p>';
					str += '</li>';
				
					//업로드된 파일 uploadList로 쌓이도록
					$(".uploadedList").append(str);

				}
			});*/
			
			$("#list_btn").click(function(event){
				event.preventDefault();
				location.assign("/board/list");
			});
		});

	</script>
	
</body>
</html>