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
<script>
	function fileCheck(i){
		var point = i.value.lastIndexOf('.');
		var extension = i.value.substring(point + 1, i.value.length);
		//console.log(extension);
		if(extension != "jpg" && extension != "gif" && extension != "png"){
			alert("이미지 파일만 선택 가능합니다.");
			
			var parent = i.parentNode; // 폼태그
			var next = i.nextSibling;
			var tmp = document.createElement("form");
			tmp.appendChild(i);
			tmp.reset();
			parent.insertBefore(i, next);
		}
		
	}
</script>
</head>
<body>
	<jsp:include page="/views/menu.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<form class="form-inline text-right" action="/photo/upload" method="post" enctype="multipart/form-data">
					<div class="form-group">
						<label for="desc">Description : </label>
						<input type="text" class="form-control" id="desc" name="description" required />&nbsp; &nbsp;
					</div>
					<div class="form-group">
						<label for="photo">Image Upload :</label>
						<input type="file" class="form-control" id="photo" name="photo" required 
						accept="image/gif, image/jpeg, image/png" 
						onchange="fileCheck(this);"/>
					</div>
					<button class="btn btn-default" type="submit">업로드</button>
				</form>
			</div>
		</div>
		<hr />
		<div class="row">
			<c:forEach var="vo" items="${list }">
				<div class="col-sm-4">
					<div class="panel panel-default">
						<div class="panel-heading">${vo.description }</div>
						<div class="panel-body">
							<img src="${vo.url }" style="width:100%" />
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>