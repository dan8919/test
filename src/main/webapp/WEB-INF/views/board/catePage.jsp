<%@page import="kr.co.domain.CategoryDTO"%>
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
</head>
<script type="text/javascript">
	function managerdelete(bno) {
		var isOk = confirm("삭제하시겠습니까?");
		if (isOk == true) {
			location.href = "/board/delete/" + bno;
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
	.btn_bebe {
	 	border: none; /* Remove borders */
  		color: hotpink; /* Add a text color */
  		padding: 5px 10px; /* Add some padding */
  		cursor: pointer; /* Add a pointer cursor on mouse-over */
	
	}
	.pink {
		background-color: white;
		border: 1px solid; hotpink;
	}
	.pink:hover {
		background: pink;
	}
	.th-center{
		text-align: center;
	}
</style>
<body>
<jsp:include page="../includes/header.jsp" />
	<div class="container">
		<div class="row text-center">
			<h1 style="color:hotpink;">**${getCateData.cname }**</h1>
		</div>
		
		<div class="row">
			<c:if test="${!empty login }">
			<button class="btn_bebe pink" onclick="location.href='/board/insert'">글쓰기</button>
			</c:if>
		</div><br>
		<div class="row">
  		<input class="form-control" id="myInput" type="text" placeholder="키워드를 입력해주세요">
  		<br>
		</div>
		
		<div class="row">
			<table class="table table-borderless">
			<tbody id="myTable">

				<%
					int newLine = 0;
					int articleCount = 0;
					int cnt = 0;
				%>
				<c:forEach items="${list }" var="dto">

				<%
					if (newLine == 0) {
						out.print("<tr>");
					}
					newLine++;
					articleCount++;
				%>
				<td align="center" width="190">
					<input type="hidden" value="${dto.bno }" name="bno" />					
					<a href="/board/read/${dto.bno }">
						<c:if test="${!empty dto.fullName}">
						<img alt="" src="/displayfile?fileName=${dto.fullName }" width="200" height="200">
						</c:if>
						<c:if test="${empty dto.fullName}">
						<img alt="" src="/resources/default.png" width="200" height="200">
						</c:if>
						<br>${dto.title }
					</a><br>
					${dto.gname }<br>
					<b><fmt:formatNumber value="${dto.price}" type="number"/> 원</b><br>
					<c:if test="${login.authority.equals('marketer')|| login.authority.equals('admin')|| login.authority.equals('ceo')}">
					<button onclick="managerdelete('${dto.bno}')">삭제</button>
					</c:if>
				</td>
				<%
					if(newLine==4){
						out.print("</tr>");
						newLine = 0;
					}
				%>
				</c:forEach>

			</tbody>
			</table>
		</div><!-- class = row (table) -->
		<div class="row text-center">
		<nav aria-label="Page navigation">
  		<ul class="pagination">
    		<li>
      		<a href="/board/catePage/${getCateData.cno }?curPage=${to.curPage > 1 ? to.curPage-1 : 1 }" aria-label="Previous">
        		<span aria-hidden="true">&laquo;</span>
      		</a>
    		</li>
    		<c:forEach begin="${to.beginPageNum }" end="${to.stopPageNum }" var="page">
    			<li class="${to.curPage == page? 'active' : '' }">
    			<a href="/board/catePage/${getCateData.cno }?curPage=${page}">${page }</a>
    			</li>
    		</c:forEach>
			<li>
      		<a href="/board/catePage/${getCateData.cno }?curPage=${to.curPage < to.totalPage ? to.curPage+1 : to.curPage }" aria-label="Next">
        		<span aria-hidden="true">&raquo;</span>
      		</a>
    		</li>
  		</ul>
		</nav>
		</div><!-- class = row (page) -->
		
		<div class="row" style="padding:0% 25%">
			<!-- 검색 시, 별도의 탭에서 열리도록 _blank -->
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
		
		</div>
		
	</div><!-- class = container -->

	<script type="text/javascript">	
/*
	$(".cateData").append(22);
	*/
		$(document).ready(function(){

			var str = '<img src = "/resources/default.png"/>';

			$(".goodsImg").append(str);
			


		});
	/*
		var price = $("p.price").val();
		function numbeComma(price) {
	    	return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		}
*/


			
/*
			
				//맨첫번째 = 썸네일값만 불러올것


					//a 링크 클릭시 파일 열기 -> 해당게시글로 이동
					var str += '<a href="/board/read/' + ${dto.bno} + '">';

					//첫번째 이미지만 썸네일이미지 크기로 받아오기
					str += '<img src = "/displayfile?fileName='+ arr[1] +'"/>';
					str += arr[1]
					str += '아'
					str += '</a>'; //a링크 태그 종료

				
					//업로드된 파일 goodsImg로 받기
					$(".goodsImg").append(str);
*/

/*
		var name = $(".cateData").attr("name");
		$("b").val(name);*/


		/*
		var tbl = document.getElementById("table_id").rows.length;
		

		$(document).ready(function(){
				
			for(var i=0; i<tbl; i++){

			var cateRow = $(".cateData")[i]; //해당순번 카테의 값


			//cno를 받고 cname을 출력하는 맵
			$.getJSON("/getCateName/"+cateRow, function(result){
				if(cateRow == result){
					$("b").append(result);

				}


			
			});
		
			}
		});*/

		
/*	
		var cno = $(".cnoData").attr("name");
		$("b").val(cno);
		
		$(document).ready(function(){
			
			
			$.getJSON("/getCateName/"+cno, function(data){
				
				$("b").val(data);
			});
		});*/
	</script>

</body>
</html>