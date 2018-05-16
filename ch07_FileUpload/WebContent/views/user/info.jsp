<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	function send(f){
		f.submit();
	};
</script>
</head>
<body>
	<jsp:include page="/views/menu.jsp" />
	<br />
	<div class="container">
		<div class="row">
			<form class="form-horizontal" method="post" action="/user/update">
				<div class="form-group">
					<label for="inputId" class="col-sm-2 control-label">아이디</label>
					<div class="col-sm-7">
						${sessionScope.vo.id }
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword" class="col-sm-2 control-label">패스워드</label>
					<div class="col-sm-7">
						<input type="password" class="form-control"
							   name="password" placeholder="패스워드" maxlength="30" required>
					</div>
				</div>
				<div class="form-group">
					<label for="inputName" class="col-sm-2 control-label">이름</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" name="name"
							placeholder="이름" maxlength="15" value="${sessionScope.vo.name }">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button" class="btn btn-default" onclick="send(this.form);">Edit</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>