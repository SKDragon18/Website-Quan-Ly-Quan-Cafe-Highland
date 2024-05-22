<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.sql.*"%>

<!DOCTYPE html>
<html>
<c:set var="root" value="${pageContext.servletContext.contextPath}" />

<head>
<meta charset="ISO-8859-1">
	<link href="${root}/resources/hoadon/css/styles.css" rel="stylesheet"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>		

<title>Quản lý hóa đơn</title>
<link rel="icon" type="image/png" href="${root}/resources/img/highlands coffee red logo.png"/>
</head>
<body>
<form id="myForm" method="POST">
  <input type="hidden" name="ID" value="" />
  <input type="hidden" name="PHANLOAI" value="" />
</form>
<div class="show">
	<div class="confirm-btn">
	    <button class="btn-confirm">Xác nhận</button>
	</div>
	<br>
	<div class="table-container">
	  <table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Ngày lập</th>
				<th>Tổng tiền</th>
				<th>Mã nhân viên</th>
				<th>Mã khuyến mãi</th>
				<th>Trạng thái</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="hoadon" items="${listHD}">
				<tr class="each-bill" data-bill-id="${hoadon.ID}" data-bill-change="false">
					<td>${hoadon.ID}</td>
					<td>${hoadon.NGAYLAP}</td>
					<td>${hoadon.TONGTIEN}</td>
					<td>${hoadon.MANV.getMANV()}</td>
					<td>${hoadon.MAKM.getMAKM()}</td>
					<td>
					  <c:if test="${hoadon.PHANLOAI == 1}">
					  	<div class="toggle-button" data-bill-type="true">
						  <div class="circle"></div>
						</div>
					  </c:if>
					  <c:if test="${hoadon.PHANLOAI == 0}">
					  	<div class="toggle-button on" data-bill-type="false">
						  <div class="circle"></div>
						</div>
					  </c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</div>	
<script type="text/javascript">
function toggleCheck(event) {
	   const checkButton = event.target;
	   const billInfo = checkButton.closest(".each-bill");
	   let isChecked = checkButton.getAttribute("data-bill-type") === "true";

	   console.log(checkButton);
	   
	   if (isChecked) {
	      checkButton.classList.add("on");
	      isChecked = false;
	   } else {
	      checkButton.classList.remove("on");
	      isChecked = true;
	   }

	   checkButton.setAttribute("data-bill-type", isChecked);
	   if (billInfo.getAttribute("data-bill-change") == "false") billInfo.setAttribute("data-bill-change", "true");
	   else billInfo.setAttribute("data-bill-change", "false");
}

document.addEventListener("click", function (event) {
	   const target = event.target;
	   if (target.classList.contains("toggle-button") || target.classList.contains("toggle-button on")) {
	      toggleCheck(event);
	   }
});

const form = document.getElementById("myForm");
const confirmBtn = document.querySelector(".btn-confirm");
confirmBtn.addEventListener("click", () => {
	const billInfo = document.querySelectorAll(".each-bill");
	
	billInfo.forEach(bill => {
		const billId = bill.dataset.billId;
	    const billChange = bill.dataset.billChange;
	    const billType = bill.querySelector('.toggle-button').dataset.billType;
	    
	    if (billChange === 'true') {
	        const newBillType = (billType === 'true') ? 'false' : 'true';	                
		      form.elements.ID.value += billId + ',';
		      form.elements.PHANLOAI.value += newBillType + ',';
		      
		      console.log(form.elements.ID.value, form.elements.PHANLOAI.value);

		      form.submit();
		      console.log("back to server");
	    }
	});
	
  	alert("Thay đổi thành công!");

}); 
</script>
</body>
</html>