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
			<h1>글 수정</h1>
		</div>
		
		<div class="row">
		<form action="/board/update" method="post">
			<input type="hidden" name="bno" value="${vo.bno }">
			<div class="form-group">
				<label for="title">제목</label>
				<input class="form-control" name="title" id="title" value="${vo.title }" required>
			</div>		

			<div class="form-group">
				<label for="gno"></label>
					<select name="gno" id="gno">
					<option value="${vo.gno }" selected>${vo.gname }</option>
					<option disabled="disabled">───────────────</option>

				<c:forEach items="${selectlist }" var="goods">
				
							<option value="${goods.gno }" name="${goods.cno }">${goods.gname }</option>

				</c:forEach>
					</select>
			</div>
						
					<input name="cno" id="cno" class="form-control" value="${vo.cno }" type="hidden">
		
			<div class="form-group">
				<label>업로드할 파일을 올려주세요</label>
				<div class="fileDrop"></div>
			</div>
			<div class="form-group">
				<label>첨부파일 목록</label>
				<ul class="uploadedList clearfix">
				</ul>
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
		var bno = ${vo.bno};	
		
		$(document).ready(function(){

			var cno;
			$("#gno").change(function(){
				cno = $("#gno option:selected").attr("name");
			
				$("#cno").val(cno);
			});
			
			
			//원본 첨부 파일 목록 불러오기
			$.getJSON("/getAttach/"+bno, function(arr){

				for(var i=0; i<arr.length; i++){
					//a 링크 클릭시 파일 열기
					var str = '<li class="col-xs-4">';
					str += '<a href="/displayfile?fileName=' + getImgLink(arr[i]) + '">';
					//썸네일이미지 삽입
					if(checkImage(arr[i])){
						str += '<img src = "/displayfile?fileName=' + arr[i] + '"/>';							
					}else{
						str += '<img src = "/resources/et.png"/>';
					}						
					str += '</a>';
					//첨부파일 삭제(삭제아이콘)
					str += '<p class="oriFileName">';
					str += '<a class="deletefile" href="' + arr[i] 
									+ '"><span class="glyphicon glyphicon-trash"></span></a>';
					str += getOriginalName(arr[i]);
					str += '</p>';
					str += '</li>';
				
					$(".uploadedList").append(str);
				}
			}); 

			//삭제아이콘 클릭시 리스트에서 삭제
			$(".uploadedList").on("click", ".deletefile", function(event){
				event.preventDefault();

				//삭제시 컨펌 띄우기
				var isOk = confirm("삭제시 복구되지 않습니다. 삭제 하시겠습니까?")

				if(isOk){
					var that = $(this); //지금 클릭하고있는 대상 (통신종료전 미리 지정)

					$.ajax({
						type: 'post',
						url: '/deletefile',
						dataType: 'text',
						data: {
							//result = 파일이름(이미지는 썸네일이름) 이므로
							fileName : that.attr("href") //href에 들어가는 result
						},
						success : function(result){
							that.parent("p").parent("li").remove();
						}
					});
				}
				
			}); 

			//수정 버튼에 파일업로드 DB 이벤트 추가
			$("#update_btn").click(function(event){
				event.preventDefault();

				var str = ''; //input 태그 넣고 form 태그에 append 할 예정

				//배열을 반복문으로
				$(".deletefile").each(function(index){
					str += "<input name='files["+index+"]' value='"+$(this).attr("href")+"' type='hidden'>";
																	//└반복문 실행하는 값
				});

				$("form").append(str);
				$("form").submit();
			}); 
			
			$("#list_btn").click(function(event){
				event.preventDefault();
				location.assign("/board/list");
			});		
			
			//웹의 기본적인 이벤트 막기 (화면에 파일 드롭하면 그대로 열림)
			$(".fileDrop").on("dragenter dragover", function(event){
				event.preventDefault();
			});

			$(".fileDrop").on("drop", function(event){
				event.preventDefault();
				
				//드래그해온 파일들의 정보 변수에 담기
				var files = event.originalEvent.dataTransfer.files;
				//드래그해온 여러 파일들중 하나
				var file = files[0];
				//폼태그 기능 만들기
				var formData = new FormData(); //자바스크립트 기본클래스
				formData.append("file", file);

				$.ajax({
					type: 'post',
					url: "/uploadajax",
					dataType: 'text',
					data: formData,
					processData: false,
					contentType: false,					
					success: function(result){
						//a 링크 클릭시 파일 열기
						var str = '<li class="col-xs-4">';
						str += '<a href="/displayfile?fileName=' + getImgLink(result) + '">';
						//썸네일이미지 삽입
						if(checkImage(result)){
							str += '<img src = "/displayfile?fileName=' + result + '"/>';							
						}else{
							str += '<img src = "/resources/et.png"/>';						}						
						str += '</a>';
						
						//첨부파일 삭제(삭제아이콘)
						str += '<p class="oriFileName">';
						str += '<a class="deletefile" href="' + result 
										+ '"><span class="glyphicon glyphicon-trash"></span></a>';
						str += getOriginalName(result);
						str += '</p>';
						str += '</li>';
						
						$(".uploadedList").append(str);
					},
					error: function(request, status, error){
						console.log(error);
					}
				});					
			}); 
			
		});
	</script>
	
</body>
</html>