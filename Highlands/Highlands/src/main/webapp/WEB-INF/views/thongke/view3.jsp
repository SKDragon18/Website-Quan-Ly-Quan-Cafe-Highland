<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="ptithcm.entity.ChiTietHoaDon" %>
<%@ page import="ptithcm.entity.SanPham" %>

<!DOCTYPE html>
<html>
	<c:set var="root" value="${pageContext.servletContext.contextPath}" />
	<link href="${root}/resources/thongke/css/styles.css" rel="stylesheet"/>
<head>
<meta charset="ISO-8859-1">
<link rel="icon" type="image/png" href="${root}/resources/img/highlands coffee red logo.png"/>
<title>Thống kê</title>
<script>
    var cthdList = [];
    <% 
        @SuppressWarnings("unchecked")
        List<ChiTietHoaDon> listCTHD = (List<ChiTietHoaDon>) request.getAttribute("listCTHD");
        for (ChiTietHoaDon cthd : listCTHD) { 
    %>
    cthdList.push({
        id: <%= cthd.getPk().getHOADON().getID() %>,
        maSP: '<%= cthd.getPk().getSANPHAM().getMASP() %>',
        maSize: '<%= cthd.getPk().getSIZE().getMASIZE() %>',
        soLuong: <%= cthd.getSOLUONG() %>
    });
    <% } %>
</script>
<script>
    var spList = [];
    <% 
        @SuppressWarnings("unchecked")
        List<SanPham> listSP = (List<SanPham>) request.getAttribute("listSP");
        for (SanPham sp : listSP) { 
    %>
    spList.push({
        maSP: '<%= sp.getMASP() %>',
        ten: '<%= sp.getTEN() %>'
    });
    <% } %>
</script>

</head>
<body>

<div class="container">
		<div class="left">
			<div>
			  <button class="time-btn" data-time="1">24 giờ qua</button>
			  <button class="time-btn" data-time="7">7 ngày</button>
			  <button class="time-btn" data-time="31">31 ngày</button>
			  <button class="time-btn" data-time="90">3 tháng</button>
			  <button class="time-btn" data-time="365">12 tháng</button>
			  <button class="time-btn" data-time="20000">Toàn thời gian</button>
			</div>
			<br>
			<table id="my-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Ngày Lập</th>
						<th>Tổng Tiền</th>
					</tr>
				</thead>
				<tbody id="bill-list">
					<c:forEach var="hd" items="${listHD}">
						<tr>
							<td class="id-list" data-bill-id = "${hd.ID}">${hd.ID}</td>
							<td>${hd.NGAYLAP}</td>
							<td>${hd.TONGTIEN}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="right">
			<div class="total">
			  <span class="total-label">Doanh thu:</span>
			  <span class="total-amount" id="total-amount"></span>
			</div>
			<div class="top5">Top 5 sản phẩm</div>
			<br>
			<div class="product-container"></div>
		</div>
	</div>
<script type="text/javascript">
const table = document.getElementById("my-table");
const tbody = table.getElementsByTagName("tbody")[0];
const rows = tbody.getElementsByTagName("tr");
const originalRows = [...rows];
let a = true;

function topProduct() {
	let newList = [];
	let idToWatch = [];
	const idList = document.querySelectorAll('.id-list');
	const i = 1;
	
	idList.forEach(id => {
		idToWatch.push(id.dataset.billId);
	});
	
	for (let i = 0; i < cthdList.length; i++) {
		  let cthd = cthdList[i];
		  let found = false;
		  let inWL = false;
		  		  
		  for (let j = 0; j < idToWatch.length; j++) {
			  if (cthd.id == idToWatch[j]) {
				  inWL = true;
				  break;
			  }
		  }

		  if (inWL) {
			  for (let j = 0; j < newList.length; j++) {
				    if (cthd.maSP === newList[j].maSP && cthd.maSize === newList[j].maSize) {
				      newList[j].soLuong += cthd.soLuong;
				      found = true;
				      break;
				    }
				  }

				  if (!found) {
				    newList.push({
				      maSP: cthd.maSP,
				      maSize: cthd.maSize,
				      soLuong: cthd.soLuong
				    });
				  }
		  }
		}
	
	newList.sort((a, b) => b.soLuong - a.soLuong);
	const newList5 = newList.slice(0,5);
	
	// Get the container for the products
	const productContainer = document.querySelector('.product-container');

	// Clear any existing products
	productContainer.innerHTML = '';

	for (const product of newList5) {
	  // Create a new <div> element to hold the product information
		const productDiv = document.createElement('div');
		productDiv.classList.add('product');

		const tenSpan = document.createElement('span');
		tenSpan.textContent = product.maSP;
		for (let j = 0; j < spList.length; j++) {
			if (product.maSP === spList[j].maSP) {
				tenSpan.textContent = spList[j].ten;
				break;
			}
		}
		productDiv.appendChild(tenSpan);

		const maSizeSpan = document.createElement('span');
		if (product.maSize === "K") {
			maSizeSpan.textContent = "";
		} else maSizeSpan.textContent = product.maSize;
		productDiv.appendChild(maSizeSpan);

		const soLuongSpan = document.createElement('span');
		soLuongSpan.textContent = product.soLuong;
		productDiv.appendChild(soLuongSpan);

		productContainer.appendChild(productDiv);

	}

}

	
function formatMoney(amount, decimalCount = 0, decimal = ".", thousands = ",") {
	  try {
	    decimalCount = Math.abs(decimalCount);
	    decimalCount = isNaN(decimalCount) ? 2 : decimalCount;

	    const negativeSign = amount < 0 ? "-" : "";

	    let i = parseInt(amount = Math.abs(Number(amount) || 0).toFixed(decimalCount)).toString();
	    let j = (i.length > 3) ? i.length % 3 : 0;

	    return negativeSign + (j ? i.substr(0, j) + thousands : '') + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousands) + (decimalCount ? decimal + Math.abs(amount - i).toFixed(decimalCount).slice(2) : "");
	  } catch (e) {
	  }
	}


function filterRowsByDate(days) {
  const currentDate = new Date();
  const filteredRows = originalRows.filter(row => {
    const rowDate = new Date(row.cells[1].textContent);
    const timeDiff = Math.abs(currentDate.getTime() - rowDate.getTime());
    const diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
    return diffDays <= days;
  });
  
  let total = 0;
  filteredRows.forEach(row => {
    total += parseFloat(row.cells[2].textContent);
  });
  
  const totalAmount = document.getElementById('total-amount');
  
  totalAmount.textContent = formatMoney(total);

  while (tbody.firstChild) {
    tbody.removeChild(tbody.firstChild);
  }
  for (const row of filteredRows) {
    tbody.appendChild(row);
  }
}

if (a == true) {
	filterRowsByDate(20000);
	topProduct();
	a = false;
}

document.querySelectorAll(".time-btn").forEach(button => {
  const days = button.getAttribute("data-time");
  button.addEventListener("click", function() {
		filterRowsByDate(days);
		topProduct();
  });
});

</script>

</body>
</html>