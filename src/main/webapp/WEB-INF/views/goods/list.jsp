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
<script type="text/javascript">
	function managerdelete(bno) {
		var isOk = confirm("삭제하시겠습니까?");
		if (isOk == true) {
			location.href = "/goods/delete/" + gno;
		}
	}
	$(document).ready(function(){
		  $("#myInput").on("keyup", function() {
		    var value = $(this).val().toLowerCase();
		    $("#myTable tr").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		    });
		  });
		});

</script>
<style>
	.th-center{
		text-align: center;
	}
</style>
<body>
<jsp:include page="../includes/header.jsp" />
	<div class="container">
		<div class="row text-center">
			<h1>재고목록</h1>
		</div>
		
		<div class="row">
			<c:if test="${login.authority.equals('marketer')}">
			<button class="btn btn-primary" onclick="location.href='/goods/insert'">글쓰기</button>
			</c:if>
		</div><br>
		<div class="row">
  		<input class="form-control" id="myInput" type="text" placeholder="키워드를 입력해주세요">
  		<br>
		</div>
		<div class="row">
			<table class="table">
				<thead>
					<tr>
						<th class="th-center">gno</th>
						<th class="th-center">카테고리</th>
						<th class="th-center" style="width: 50%">상품명</th>
						<c:if test="${login.authority.equals('marketer')}">
						<th class="th-center">금액</th>
						<th class="th-center">삭제</th>
						</c:if>
					</tr>
				</thead>
				
				<tbody id="myTable">
					<c:forEach items="${list }" var="dto">
						<tr>
							<td>${dto.gno }</td>
							<td>${dto.cname }</td>
							<td>
								<a href="/goods/update/${dto.gno }">${dto.gname }</a>
							</td>
							<td><fmt:formatNumber value="${dto.price}" type="number"/> 원</td>
							<c:if test="${login.authority.equals('marketer')}">
							<td><button onclick="managerdelete('${dto.gno}')">삭제</button></td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div><!-- class = row (table) -->
		<div class="row text-center">
		<nav aria-label="Page navigation">
  		<ul class="pagination">
    		<li>
      		<a href="/goods/list?curPage=${to.curPage > 1 ? to.curPage-1 : 1 }" aria-label="Previous">
        		<span aria-hidden="true">&laquo;</span>
      		</a>
    		</li>
    		<c:forEach begin="${to.beginPageNum }" end="${to.stopPageNum }" var="page">
    			<li class="${to.curPage == page? 'active' : '' }">
    			<a href="/goods/list?curPage=${page}">${page }</a>
    			</li>
    		</c:forEach>
			<li>
      		<a href="/goods/list?curPage=${to.curPage < to.totalPage ? to.curPage+1 : to.curPage }" aria-label="Next">
        		<span aria-hidden="true">&raquo;</span>
      		</a>
    		</li>
  		</ul>
		</nav>
		</div><!-- class = row (page) -->

		<div class="row" style="padding:0% 25%">
			<!-- 검색 시, 별도의 탭에서 열리도록 _blank -->
		<!-- 검색
			<form target="_blank" action="/board/searchlist" method="get">
				<div class="input-group">
					<span class="input-group-addon">
						<select name="searchType">
							<option disabled>검색 기준</option>
							<option value="title">제목</option>
							<option value="writer">글쓴이</option>
							<option value="content">내용</option>
						</select>
					</span>					
					<input class="form-control" name="keyword">
					<span class="input-group-btn">
						<button class="btn btn-info">검색</button>
					</span>
				</div>
			</form>
		
		-->		
		</div>
		
	</div><!-- class = container -->

</body>
</html>