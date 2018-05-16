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
	function fileCheck(obj){
		var extensionPoint = obj.value.lastIndexOf('.');
		var extension = obj.value.substring(extensionPoint+1, obj.value.length);
		//console.log(extension);
		extension = extension.toLowerCase();
		
		//이미지 이외의 파일선택
		if(extension != "jpg" && extension != "gif" && extension != "png" && extension != "jpeg"){
			
			alert("이미지 이외의 파일은 업로드 할 수 없습니다.");
			
			//인풋태그 초기화
			
			//obj.value = '';
			
			//obj.type = 'radio';
			//obj.type = 'file';
			
			var parent = obj.parentNode;
			var next = obj.nextSibling;
			var tmp = document.createElement('form');
			tmp.appendChild(obj);
			tmp.reset();
			parent.insertBefore(obj, next);
			
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
						       onchange="fileCheck(this);" />
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