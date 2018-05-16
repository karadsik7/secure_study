<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	console.log(document.cookie);
</script>
</head>
<body>
	<jsp:include page="/views/menu.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3 panel panel-primary">
				<div class="panel-body">
					<form action="/comment/write" method="post">
						<input type="hidden" name="u_id" value="${sessionScope.vo.id }"/>
						<textarea class="form-control" name="content" required autofocus style="resize: none"></textarea>
						<br />
						<button class="btn btn-lg btn-primary btn-block" type="submit">Send</button>
					</form>
				</div>
			</div>
		</div>
		<c:forEach var="vo" items="${list }">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3 panel panel-default">
				<div class="panel-body">
					<h4>${vo.u_id } <small>Sep 29, 2017, 9:12 PM</small></h4>
					<p>${vo.content }</p>
				</div>
			</div>
		</div>		
		</c:forEach>
	</div>
</body>
</html>