<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<c:set var="root" value="${pageContext.servletContext.contextPath}" />

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Công thức pha chế</title>
</style>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="${root}/resources/quanly/css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
	crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
	<%@ include file="/WEB-INF/common/quanly/header.jsp"%>
	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid px-4">
				<div class="card-header">
					<label class="font-weight-bold"
						style="font-size: 20px; color: #800000">QUẢN LÝ CÔNG THỨC
						SẢN PHẨM</label>
				</div>
				<div class="card-header">
					<form:form class="row g-3" modelAttribute="congthuc"
						action="/Highlands/product/recipe/list.htm" rel="stylesheet">
						
						<c:if test="${btnStatus=='btnAdd'}">
							<div class="col-md-4">
								<label class="font-weight-bold">Mã công thức</label>
								<form:input path="MACT" type="text" maxlength="10" placeholder="vd: CT1" class="form-control"
									id="mact" />
								<p style="color: red; font-style: oblique">
									<form:errors path="MACT" />
								</p>
							</div>
						</c:if>
						<c:if test="${btnStatus=='btnEdit'}">
							<div class="col-md-4" hidden="true">
								<label class="font-weight-bold">Mã công thức</label>
								<form:input path="MACT" type="text" class="form-control"
									id="mact" />
								<p style="color: red; font-style: oblique">
									<form:errors path="MACT" />
								</p>
							</div>
							<div class="col-md-4">
								<label class="font-weight-bold">Mã công thức</label>
								<form:input path="MACT" type="text" class="form-control"
									disabled="true" id="mact" />
								<p style="color: red; font-style: oblique">
									<form:errors path="MACT" />
								</p>
							</div>
						</c:if>
						<div class="col-md-8">
							<label class="font-weight-bold">Công thức</label>
							<form:input path="CONGTHUC" type="text" class="form-control"
								id="exampleFormControlInput1" />
							<p style="color: red; font-style: oblique">
								<form:errors path="CONGTHUC" />
							</p>
						</div>
							<form:hidden path="NGAYLAP" class="form-control" />
						<div class='parent' class="col-md-2">
							<hr>
							<div class='child float-left-child' style="margin-left: 2.5em">
								<button name="${btnStatus}" class="btn btn-success">Lưu</button>
								<button
									onclick="location.href='http://localhost:8080/Highlands/product/recipe/list.htm'"
									class="btn btn-outline-dark" type="button">Tải Lại
									Trang</button>
								<p5 class="text-success">${message1}</p5>
								<p5 class="text-danger">${message0}</p5>
							</div>
						</div>
						<div class="form-inline col-5">
							<hr>
							<span id="result1"></span>
						</div>
					</form:form>
				</div>
				<div class="card mb-4">
					<div class="card-header">
						<i class="fas fa-table me-1"></i> Bảng công thức
					</div>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th>Mã công thức</th>
									<th>Ngày lập</th>
									<th>Công thức</th>
									<th>Sửa</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>Mã công thức</th>
									<th>Ngày lập</th>
									<th>Công thức</th>
									<th>Sửa</th>
								</tr>
							</tfoot>
							<tbody>
								<c:forEach var="congthuc" items="${dscongthuc}">
									<tr>
										<td>${congthuc.MACT}</td>
										<td>${congthuc.NGAYLAP}</td>
										<td>${congthuc.CONGTHUC}</td>
										<td><a
											href="/Highlands/product/recipe/list/${congthuc.MACT}.htm?linkEdit"
											rel="stylesheet"><img width="31" height="31"
												src="<c:url value='/resources/img/edit.png'/>" /></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</main>
		<footer class="py-4 bg-light mt-auto">
			<div class="container-fluid px-4">
				<div class="d-flex align-items-center justify-content-between small">
					<div class="text-muted">Copyright &copy; Your Website 2022</div>
					<div>
						<a href="#">Privacy Policy</a> &middot; <a href="#">Terms
							&amp; Conditions</a>
					</div>
				</div>
			</div>
		</footer>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="${root}/resources/quanly/js/scripts.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
		crossorigin="anonymous"></script>
	<script src="${root}/resources/quanly/js/datatables-simple-demo.js"></script>
</body>
</html>