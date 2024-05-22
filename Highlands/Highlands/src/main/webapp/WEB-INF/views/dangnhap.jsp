<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<c:set var="root" value="${pageContext.servletContext.contextPath}" />
<head>
<meta charset="ISO-8859-1">
<title>ĐĂNG NHẬP</title>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="${root}/resources/account/fonts/icomoon/style.css">

<link rel="stylesheet"
	href="${root}/resources/account/css/owl.carousel.min.css">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="${root}/resources/account/css/bootstrap.min.css">

<!-- Style -->
<link rel="stylesheet" href="${root}/resources/account/css/style.css">

<title>Đăng Nhập</title>
</head>
<body>
	<div class="half">
		<div class="bg order-1 order-md-2"
			style="background-image: url('${root}/resources/account/images/bg_1.jpg');"></div>
		<div class="contents order-2 order-md-1">
			<div class="container">
				<div class="row align-items-center justify-content-center">
					<div class="col-md-6">
						<div class="form-block">
							<div class="text-center mb-5">
								<h3>
									Đăng nhập vào hệ thống <strong>Highlands</strong>
								</h3>
							</div>
							<form
								action="http://localhost:8080/Highlands/login/logininfo.htm"
								method="post">
								<div class="form-group first">
									<label for="username">Tên Đăng Nhập</label> <input type="text"
										class="form-control" placeholder="tên đăng nhập của bạn"
										name="username">
								</div>
								<div class="form-group last mb-3">
									<label for="password">Mật Khẩu</label> <input type="password"
										class="form-control" placeholder="mật khẩu của bạn"
										name="password">
								</div>
								<div class="d-sm-flex mb-5 align-items-center">

									<div class="control__indicator"></div>
									<span class="ml-auto"><a href="../home/quenmatkhau.htm"
										class="forgot-pass">Quên Mật Khẩu</a></span>
								</div>
								<input type="submit" value="ĐĂNG NHẬP"
									class="btn btn-block btn-primary">
								<div class="form-group last mb-3">
									<p5 class="text-success">${message1}</p5>
									<p5 class="text-danger">${message0}</p5>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${root}/resources/account/js/jquery-3.3.1.min.js"></script>
	<script src="${root}/resources/account/js/popper.min.js"></script>
	<script src="${root}/resources/account/js/bootstrap.min.js"></script>
	<script src="${root}/resources/account/js/main.js"></script>
</body>
</html>