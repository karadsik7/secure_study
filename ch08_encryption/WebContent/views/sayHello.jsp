<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<div class="row text-center">
			<div class="jumbotron">
				<h1>${msg }</h1>
			</div>
		</div>
	</div>
</body>
</html>