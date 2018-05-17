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
	var isDoubleChecked = false;
	
	function doubleCheckToFalse(){
		isDoubleChecked = false;
	}
	function checkPwd(f){
		var pwd = f.password.value;
		var pwdCheck= f.passwordCheck.value;
		if(pwd != pwdCheck){
			$('#pwdAlert').text("패스워드 일치하지 않음");
		}else{
			$("#pwdAlert").text("패스워드 일치")
		}
		
		if(pwdCheck == ""){
			$("#pwdAlert").text("")
		}
	}
	
	function doubleCheck(f){
		$.ajax({
			url:"/user/doubleCheck",
			data:"id="+f.id.value,
			type:"post",
			success:function(data){
				if(data=="y"){
					alert("중복된 아이디가 존재합니다.");
				}else{
					alert("사용할 수 있는 아이디 입니다.");
					isDoubleChecked = true;
				}
			}
		})
	}
	
	function send(f){
		if(!isDoubleChecked){
			alert("중복확인 체크해 주세요.");
			return;
		}
		
		var pwd = f.password.value;
		var pwdCheck= f.passwordCheck.value;
		if(pwd != pwdCheck){
			alert("패스워드가 일치하지 않습니다.");
			return;
		}
		
		//브라우저에서의 1차 데이터 검증
		
		/* if(!/^[a-zA-Z0-9]{5,30}$/.test(f.id.value)){
			alert("아이디는 5글자 이상 30글자 이내의 영문과 숫자의 조합이어야 합니다.");
			f.id.focus();
			return;
		} *//* if(!/^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,30}$/.test(f.password.value)){
			alert("비밀번호는 6글자 이상 30글자 이하의 영문 숫자 특수문자(#?!@$%^&*-)의 혼합이어야 합니다.")
			f.password.focus();
			return;
		}else if(!/^[가-힣]{2,10}$/.test(f.name.value)){
			alert("이름은 2글자 이상 10글자 이내의 한글이어야 합니다.");
			f.name.focus();
			return;
		} */
		
		f.submit();
	}
</script>
</head>
<body>
	<jsp:include page="/views/menu.jsp" />
	<br />
	<div class="container">
		<div class="row">
			<form class="form-horizontal" method="post" action="/user/signup">
				<div class="form-group">
					<label for="inputId" class="col-sm-2 control-label">아이디</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" name="id" required placeholder="아이디" maxlength="30" onkeyup="doubleCheckToFalse();">
					</div>
					<div class="col-sm-3">
						<button class="btn btn-sm btn-default" type="button" onclick="doubleCheck(this.form);">중복확인</button>
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
					<label for="inputPasswordCheck" class="col-sm-2 control-label">패스워드 확인</label>
					<div class="col-sm-7">
						<input type="password" class="form-control" name="passwordCheck"
							   placeholder="패스워드 확인" maxlength="30" onkeyup="checkPwd(this.form);">
					</div>
					<div class="col-sm-3">
						<span id="pwdAlert"></span>
					</div>
					
				</div>
				<div class="form-group">
					<label for="inputName" class="col-sm-2 control-label">이름</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" name="name"
							placeholder="이름" maxlength="15">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button" class="btn btn-default" onclick="send(this.form);">Sign Up</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>