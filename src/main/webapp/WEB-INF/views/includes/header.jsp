<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/8de8e34d54.js" crossorigin="anonymous"></script>
<style>
.jumbotron {
	background-color: #FFC0CB;
	color: #fff;
    padding: 50px 25px;
    font-family: Montserrat, sans-serif;
  }
.navbar {
    margin-bottom: 0;
    background-color: pink;
    z-index: 9999;
    border: 0;
    font-size: 12px !important;
    line-height: 1.42857143 !important;
    letter-spacing: 4px;
    border-radius: 0;
    font-family: Montserrat, sans-serif;
  }
  .navbar li a, .navbar .navbar-brand {
    color: #fff !important;
  }
  .navbar-nav li a:hover, .navbar-nav li.active a {
    color: pink !important;
    background-color: #fff !important;
  }
  .navbar-default .navbar-toggle {
    border-color: transparent;
    color: #fff !important;
  }
</style>

<header>


<!-- nav bar -->


  <div class="container-fluid">
<nav class="navbar navbar-default navbar-fixed-top">

    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Brand</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="/board/list">전체</a></li>
        <li><a href="/board/catePage/1">toy</a></li>
        <li><a href="/board/catePage/2">clothes</a></li>
        <li><a href="/board/catePage/3">stroller</a></li>
        <li><a href="/board/catePage/4">food</a></li>       
		<c:if test="${login.authority.equals('marketer') }">
        <li><a href="/goods/list">제품추가</a></li>
        </c:if>
      </ul>

      <ul class="nav navbar-nav navbar-right">
			<!-- 로그인 화면 -->
			<c:if test="${!empty login }">
			<li>			
				<a style="color: gray;">${login.id }님, 안녕하세요.</a>
			</li>
			<li>
				<a href="/member/logout"><i class="fas fa-sign-out-alt"></i></a>
			</li>
			<li>
				<a href="/member/read/${login.id}"><i class="fas fa-user-circle"></i></a>
			</li>
			<li>
				<a href="#"><i class="fas fa-shopping-cart"></i></a>
			</li>
				<!-- 관리 -->
				<c:if test="${login.authority.equals('admin')||login.authority.equals('ceo') }">
					<li>
					<a href="/member/list"><i class="fas fa-users-cog"></i></a>
					</li>
				</c:if>
			</c:if>
			<!-- 로그아웃 화면 -->
			<c:if test="${empty login }">
			<li>
				<a href="/member/login"><i class="fas fa-sign-in-alt"></i></a>
			</li>
			<li>
				<a href="/member/insert"><i class="fas fa-user-plus"></i></a>
			</li>
			</c:if>


      </ul>

    </div><!-- /.navbar-collapse -->
</nav>


  </div><!-- /.container-fluid -->
<div class="jumbotron container-fluid text-center">
	<h1>BeBe_MaMa</h1>
	<p>We specialize in blablabla</p>
</div>

</header>