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
<title>Quản lý loại sản phẩm</title>
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
						style="font-size: 20px; color: #800000">QUẢN LÝ LOẠI SẢN PHẨM</label>
				</div>
				<div class="card-header">
					<form:form class="row g-3" modelAttribute="loai"
						action="/Highlands/product/type/list.htm" rel="stylesheet">
						<c:if test="${btnStatus=='btnAdd'}">
							<div class="col-md-4">
								<label class="font-weight-bold">Mã loại</label>
								<form:input path="MALOAI" type="text" maxlength="10" placeholder="Chỉ nhập chữ" class="form-control"
									id="maloai" />
								<p style="color: red; font-style: oblique">
									<form:errors path="MALOAI" />
								</p>
							</div>
						</c:if>
						<c:if test="${btnStatus=='btnEdit'}">
							<div class="col-md-4" hidden="true">
								<label class="font-weight-bold">Mã loại</label>
								<form:input path="MALOAI" type="text" class="form-control"
									id="maloai" />
								<p style="color: red; font-style: oblique">
									<form:errors path="MALOAI" />
								</p>
							</div>
							<div class="col-md-4">
								<label class="font-weight-bold">Mã loại</label>
								<form:input path="MALOAI" type="text" class="form-control"
									disabled="true" id="maloai" />
								<p style="color: red; font-style: oblique">
									<form:errors path="MALOAI" />
								</p>
							</div>
						</c:if>
						<div class="col-md-4">
							<label class="font-weight-bold">Tên loại</label>
							<form:input path="TENLOAI" type="text" maxlength="30" placeholder="Chỉ nhập chữ" class="form-control"
								id="exampleFormControlInput1" />
							<p style="color: red; font-style: oblique">
								<form:errors path="TENLOAI" />
							</p>
						</div>
						<div class='parent' class="col-md-2">
							<hr>
							<div class='child float-left-child' style="margin-left: 2.5em">
								<button name="${btnStatus}" class="btn btn-success">Lưu</button>
								<button
									onclick="location.href='http://localhost:8080/Highlands/product/type/list.htm'"
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
						<i class="fas fa-table me-1"></i> Bảng loại
					</div>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th>Mã loại sản phẩm</th>
									<th>Tên loại sản phẩm</th>
									<th>Sửa</th>
									<th>Xóa</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>Mã loại sản phẩm</th>
									<th>Tên loại sản phẩm</th>
									<th>Sửa</th>
									<th>Xóa</th>
								</tr>
							</tfoot>
							<tbody>
								<c:forEach var="loai" items="${dsloai}">
									<tr>
										<td>${loai.MALOAI}</td>
										<td>${loai.TENLOAI}</td>
										<td><a
											href="/Highlands/product/type/list/${loai.MALOAI}.htm?linkEdit"
											rel="stylesheet"><img width="31" height="31"
												src="<c:url value='/resources/img/edit.png'/>" /></a></td>
										<td><a name="btnDelete"
											href="/Highlands/product/type/list/${loai.MALOAI}.htm?linkDelete"
											rel="stylesheet" role="button"><img width="31"
												height="31" src="<c:url value='/resources/img/delete.png'/>" /></a>
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