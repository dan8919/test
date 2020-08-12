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
<jsp:include page="../includes/header.jsp" />
	<div class="container">
		<div class="row text-center">
			<h1>회원 목록</h1>
		</div><!-- /.row-text-center -->
		
		
		<div class="row">
			<table class="table">
				<thead>
					<tr>
						<th class="th-center">아이디</th>
						<th class="th-center">이름</th>
						<th class="th-center">권한</th>
						<c:if test="${login.authority.equals('admin')|| login.authority.equals('ceo')}">
						<th></th>
						<th></th>						
						</c:if>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach  items="${list }" var="dto">
					
						<tr>
							<td class="id">
								<a>${dto.id }</a>
							</td>
							<td>${dto.uname }</td>
							<td class="authority_change">
							<select name="authority" class="form-control authority" >
							<option value="${dto.authority }" selected>${dto.authority }</option>
							<option value="consumer">consumer</option>
							<option value="marketer">marketer</option>
							<option value="ceo">ceo</option>
							</select>
							</td>
						<c:if test="${login.grno == 3 || login.grno == 2}">
							<td>						
							<button class="change_btn" value =" ${dto.id}" }>권한변경</button>
							</td>
							<td><button onclick="location.href='/member/delete/${dto.id}'">탈퇴</button></td>
						</c:if>
						</tr>
					
					</c:forEach>
				</tbody>
			</table>
		</div><!-- /.row (table) -->
		
	</div><!-- /.container -->

	<script type="text/javascript">

	$(document).ready(function(){




			var auth = ""
			
			$(".authority").change(function(){
				auth = $(this).val();
				
			
			});
		  
            
			 $(".change_btn").click(function(){
				
				var id = $(this).val();
		
				 
				 $.ajax({
					 type:'put',
					 url:'/changeAuthority/'+id,
					 dataType : 'text',
					 headers:{
						 'Content-Type':'application/json','X-HTTP-Method-Override':'PUT'
						 },

					data:JSON.stringify({
						auth : auth
						}),
					success:function(result){
						if(result === "success"){

						alert(" 변경되었습니다.");
							}
						}


					 });
					
			});
		

	});

	
	
	</script>

</body>
</html>