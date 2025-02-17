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
<title>Quản lý sản phẩm</title>
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
						style="font-size: 20px; color: #800000">QUẢN LÝ SẢN PHẨM</label>
				</div>
				<div class="card-header">
					<form:form class="row g-3" modelAttribute="sanpham"
						action="/Highlands/product/list.htm?${btnStatus}" rel="stylesheet" method="POST"
						enctype="multipart/form-data">
						
						<c:if test="${btnStatus=='btnAdd'}">
							<div class="col-md-4">
								<label class="font-weight-bold">Mã sản phẩm</label>
								<form:input path="MASP" type="text" maxlength="10" placeholder="vd: SP1" class="form-control"
									id="masp" />
								<p style="color: red; font-style: oblique">
									<form:errors path="MASP" />
								</p>
							</div>
						</c:if>
						<c:if test="${btnStatus=='btnEdit'}">
							<div class="col-md-4" hidden="true">
								<label class="font-weight-bold">Mã sản phẩm</label>
								<form:input path="MASP" type="text" class="form-control"
									id="manv" />
								<p style="color: red; font-style: oblique">
									<form:errors path="MASP" />
								</p>
							</div>
							<div class="col-md-4">
								<label class="font-weight-bold">Mã sản phẩm</label>
								<form:input path="MASP" type="text" class="form-control"
									disabled="true" id="masp" />
								<p style="color: red; font-style: oblique">
									<form:errors path="MASP" />
								</p>
							</div>
						</c:if>
						<div class="col-md-4">
							<label class="font-weight-bold">Tên sản phẩm</label>
							<form:input path="TEN" type="text" maxlength="50" placeholder="Chỉ chữ và số" class="form-control"
								id="exampleFormControlInput1" />
							<p style="color: red; font-style: oblique">
								<form:errors path="TEN" />
							</p>
						</div>
						<div class="col-md-4">
							<label class="font-weight-bold">Loại</label><br>
							<form:select path="LOAISP.MALOAI" items="${cacloai}" itemValue="MALOAI" itemLabel="TENLOAI"  class="form-control"
								aria-label=".form-select-Ig example"/>
							<p style="color: red; font-style: oblique">
								<form:errors path="LOAISP.MALOAI" />
							</p>
							
						</div>
						<div class="col-md-4">
							<label class="font-weight-bold">Hình ảnh</label>
							<input type="file" name="photo">
							<form:hidden path="HINHANH"/>
							<p style="color: red; font-style: oblique">
								<form:errors path="HINHANH" />
							</p>
						</div>
						
						<div class='parent' class="col-md-2">
							<hr>
							<div class='child float-left-child' style="margin-left: 2.5em">
								<button class="btn btn-success">Lưu</button>
								<button onclick="location.href='http://localhost:8080/Highlands/product/list.htm'"
									class="btn btn-outline-dark" type="button">Tải Lại Trang</button>
								<p5 class="text-success">${message1}</p5>
								<p5 class="text-danger">${message0}</p5>
								<p5 class="text-danger">${message}</p5>
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
							<i class="fas fa-table me-1"></i> DANH SÁCH SẢN PHẨM
						</div>
						<div class="card-body">
							<table id="datatablesSimple">
								<thead>
									<tr>
										<th>Mã sản phẩm</th>
										<th>Tên sản phẩm</th>
										<th>Hình ảnh</th>
										<th>Loại</th>
										<th>Sửa</th>
										<th>Xóa</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th>Mã sản phẩm</th>
										<th>Tên sản phẩm</th>
										<th>Hình ảnh</th>
										<th>Loại</th>
										<th>Sửa</th>
										<th>Xóa</th>
									</tr>
								</tfoot>
								<tbody>
									<c:forEach var="sp" items="${dssanpham}">
										<tr>
											<td>${sp.MASP}</td>
											<td>${sp.TEN}</td>
											<td>${sp.HINHANH}</td>
											<td>${sp.LOAISP.TENLOAI}</td>
											<td><a
												href="/Highlands/product/list/${sp.MASP}.htm?linkEdit"
												rel="stylesheet"><img width="31" height="31"
													src="<c:url value='/resources/img/edit.png'/>" /></a></td>
											<td><a name="btnDelete"
												href="/Highlands/product/list/${sp.MASP}.htm?linkDelete"
												rel="stylesheet" role="button"><img width="31"
													height="31"
													src="<c:url value='/resources/img/delete.png'/>" /></a>
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
					<div
						class="d-flex align-items-center justify-content-between small">
						<div class="text-muted">Copyright &copy; Your Website 2022</div>
						<div>
							<a href="#">Privacy Policy</a> &middot; <a href="#">Terms
								&amp; Conditions</a>
						</div>
					</div>
				</div>
			</footer>
		</div>
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