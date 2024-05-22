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
<title>Nhập hàng</title>
<style>
label {
	display: block;
	font: 1rem 'Fira Sans', sans-serif;
}

input, label {
	margin: 0.4rem 0;
}

td:nth-child(n+3):nth-child(-n+6) {
	text-align: center;
}

td:nth-child(4) {
	background-color: #C8E2B1;
	text-align: center;
}

td:nth-child(5) {
	background-color: #C8E2B1;
	text-align: center;
}
</style>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="${root}/resources/quanly/css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
	crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
	<%@ include file="/WEB-INF/common/nhanvien/header.jsp"%>
	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid px-4">
				<div class="card-header">
					<label class="font-weight-bold"
						style="font-size: 20px; color: red; font-weight: bold;">THÔNG
						TIN NHẬP HÀNG</label>
				</div>
				<div class="card-header">
					<form class="row g-3"
						action="http://localhost:8080/Highlands/import/list.htm"
						method="post">
						<div class="form-group last mb-3">
							<label style="font-size: 18px; color: black; font-weight: bold;">Nhân
								Viên Nhập: </label> <label
								style="font-size: 18px; color: darkred; font-weight: bold;">${nv}</label>
						</div>

						<div class="col-md-2">
							<label for="manhaphang">Mã Nhập Hàng</label> <input
								class="form-control" type="text" value="${manhaphang}" disabled>
						</div>

						<div class="col-md-2">
							<label>Ngày Nhập</label> <input class="form-control" type="text"
								value="${thoigiannhaphang}" disabled>
						</div>

						<div class="col-md-3">
							<label>Chọn Nguyên Liệu</label><br> <select id="mySelect"
								class="form-control" name="chonnguyenlieu">
								<c:forEach items="${dsnl}" var="item">
									<option value="${item.MANL}">${item.TEN}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-2">
							<label for="soluong">Nhập Số Lượng</label> <input type="number"
								min="1" values="1" class="form-control"
								placeholder="${item.DONVI}" name="soluong">
						</div>

						<div class="col-md-2">
							<label for="dongia">Nhập Đơn Giá</label> <input type="number"
								min="0" class="form-control" values="0" placeholder="VNĐ"
								min="0" step="100" name="dongia">
						</div>

						<div class='parent' class="col-md-2">
							<div class='child float-left-child' style="margin-left: 28em">
								<c:if test="${btnStatus=='1'}">
									<button name="btnAdd" class="btn btn-success">Nhập mặt
										hàng</button>
									<button
										onclick="location.href='http://localhost:8080/Highlands/import/reload.htm'"
										class="btn btn-outline-dark" type="button">Xóa phiếu
										nhập</button>
								</c:if>
								<p5 class="text-success">${message1}</p5>
								<p5 class="text-danger">${message0}</p5>
							</div>
						</div>
						<hr
							style="box-sizing: border-box; height: 1.5px; width: 100%; border: 1px solid black;">
						<c:if test="${btnStatus=='1'}">
							<div>
								<label for="btnSave" style="font-size: 17px; color: black;">Lưu
									thông tin phiếu nhập: </label>
								<button class="btn btn-danger" name="btnSave">
									<i class="glyphicon glyphicon-ok-sign"></i> LƯU
								</button>
								<br> <br> <label for="btnHuy"
									style="font-size: 17px; color: black;">Hủy thông tin
									phiếu nhập: </label>
								<button
									onclick="location.href='http://localhost:8080/Highlands/import/defineId.htm'"
									class="btn btn-warning" name="btnHuy" type="button">HỦY</button>
							</div>
						</c:if>
						<c:if test="${btnStatus=='0'}">
							<div>
								<button
									onclick="location.href='http://localhost:8080/Highlands/import/defineId.htm'"
									class="btn btn-success" type="button">Trở về trang
									danh sách phiếu nhập</button>
							</div>
						</c:if>
						<div class="form-inline col-5">
							<span id="result1"></span>
						</div>
					</form>
				</div>
				<div class="card mb-4">
					<div class="card-header"
						style="font-size: 20px; color: black; font-weight: bold;">
						DANH SÁCH NGUYÊN LIỆU NHẬP</div>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th>Tên Hàng Hóa</th>
									<th>Đơn Vị</th>
									<th>Số Lượng Tồn</th>
									<th>Số Lượng Nhập</th>
									<th>Đơn Giá</th>
									<th>Tổng Số Lượng Tồn Mới</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>Tên Hàng Hóa</th>
									<th>Đơn Vị</th>
									<th>Số Lượng Tồn Cũ</th>
									<th>Số Lượng Nhập</th>
									<th>Đơn Giá</th>
									<th>Tổng Số Lượng Tồn Mới</th>
								</tr>
							</tfoot>
							<tbody>
								<c:forEach items="${nguyenlieu}" var="item" varStatus="loop">
									<tr>
										<td>${item.TEN}</td>
										<td>${item.DONVI}</td>
										<td>${item.SLTON}</td>
										<td>${item.SOLUONG}</td>
										<td>${item.DONGIA}</td>
										<td>${item.SLTON + item.SOLUONG}</td>
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

	<script>
		$(document).ready(function() {
			// Initialize the DataTable
			var dataTable = $('datatablesSimple').DataTable();

			// Catch the change event of the select element
			$('mySelect').on('change', function() {
				var selectedValue = $(this).val(); // Get the selected value

				// Set the search input value and trigger the search
				dataTable.search(selectedValue).draw();
			});
		});
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="${root}/resources/quanly/js/scripts.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
		crossorigin="anonymous"></script>
	<script src="${root}/resources/quanly/js/datatables-simple-demo.js"></script>
</body>
</html>