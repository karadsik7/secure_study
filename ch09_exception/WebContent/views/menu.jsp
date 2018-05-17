<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default">
	<div class="container">
		<div class="navbar-header">
			<a href="/main" class="navbar-brand">
			<span class="glyphicon glyphicon-home"></span> Secure</a>
		</div>
		<ul class="nav navbar-nav navbar-right">
			<c:if test="${!empty sessionScope.vo }">
				<li><a href="/photo/list"><span class="glyphicon glyphicon-picture"></span> photo galary</a></li>
				<li><a href="/sayHello?name=${sessionScope.vo.name }"><span class="glyphicon glyphicon-heart"></span> welcome</a></li>
				<li><a href="/comment/list"><span class="glyphicon glyphicon-pencil"></span> comment</a></li>
				<li><a href="/user/signout">
				    <span class="glyphicon glyphicon-log-out"></span> sign out</a></li>
				<li>
					<a href="/user/info">
						<span class="glyphicon glyphicon-user"></span>my page
					</a>
				</li>
			</c:if>
			<c:if test="${empty sessionScope.vo }">
				<li><a href="/user/signup"><span class="glyphicon glyphicon-user"></span> sign up</a></li>
				<li><a data-toggle="modal" data-target="#signinModal"><span
					   class="glyphicon glyphicon-log-in"></span> sign in</a></li>
			</c:if>
		</ul>
		<div class="modal fade" id="signinModal">
			<div class="modal-dialog modal-sm">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title text-center">Sign In</h4>
					</div>
					<div class="modal-body">
					    <form action="/user/signin" method="post">       
					      <input type="text" class="form-control" name="id" placeholder="ID" required autofocus/>
					      <br />
					      <input type="password" class="form-control" name="password" placeholder="Password" required/>
					      <br />      
					      <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
					    </form>
					</div>
				</div>
			</div>
		</div>
	</div>
</nav>