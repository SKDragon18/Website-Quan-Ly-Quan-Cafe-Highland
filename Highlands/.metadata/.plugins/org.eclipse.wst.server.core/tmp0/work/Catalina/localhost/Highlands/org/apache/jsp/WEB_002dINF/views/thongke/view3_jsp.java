/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.71
 * Generated at: 2023-06-23 13:17:21 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.thongke;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.tagplugins.jstl.core.Import;
import java.util.List;
import ptithcm.entity.ChiTietHoaDon;
import ptithcm.entity.SanPham;

public final class view3_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("jar:file:/D:/Workspace2/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Highlands/WEB-INF/lib/jstl-impl.jar!/META-INF/c.tld", Long.valueOf(1343794618000L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-impl.jar", Long.valueOf(1679084736000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("org.apache.jasper.tagplugins.jstl.core.Import");
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("ptithcm.entity.SanPham");
    _jspx_imports_classes.add("ptithcm.entity.ChiTietHoaDon");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("	");
      if (_jspx_meth_c_005fset_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("	<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${root}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/resources/thongke/css/styles.css\" rel=\"stylesheet\"/>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"ISO-8859-1\">\r\n");
      out.write("<link rel=\"icon\" type=\"image/png\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${root}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/resources/img/highlands coffee red logo.png\"/>\r\n");
      out.write("<title>Thống kê</title>\r\n");
      out.write("<script>\r\n");
      out.write("    var cthdList = [];\r\n");
      out.write("    ");
 
        @SuppressWarnings("unchecked")
        List<ChiTietHoaDon> listCTHD = (List<ChiTietHoaDon>) request.getAttribute("listCTHD");
        for (ChiTietHoaDon cthd : listCTHD) { 
    
      out.write("\r\n");
      out.write("    cthdList.push({\r\n");
      out.write("        id: ");
      out.print( cthd.getPk().getHOADON().getID() );
      out.write(",\r\n");
      out.write("        maSP: '");
      out.print( cthd.getPk().getSANPHAM().getMASP() );
      out.write("',\r\n");
      out.write("        maSize: '");
      out.print( cthd.getPk().getSIZE().getMASIZE() );
      out.write("',\r\n");
      out.write("        soLuong: ");
      out.print( cthd.getSOLUONG() );
      out.write("\r\n");
      out.write("    });\r\n");
      out.write("    ");
 } 
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("<script>\r\n");
      out.write("    var spList = [];\r\n");
      out.write("    ");
 
        @SuppressWarnings("unchecked")
        List<SanPham> listSP = (List<SanPham>) request.getAttribute("listSP");
        for (SanPham sp : listSP) { 
    
      out.write("\r\n");
      out.write("    spList.push({\r\n");
      out.write("        maSP: '");
      out.print( sp.getMASP() );
      out.write("',\r\n");
      out.write("        ten: '");
      out.print( sp.getTEN() );
      out.write("'\r\n");
      out.write("    });\r\n");
      out.write("    ");
 } 
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<div class=\"container\">\r\n");
      out.write("		<div class=\"left\">\r\n");
      out.write("			<div>\r\n");
      out.write("			  <button class=\"time-btn\" data-time=\"1\">24 giờ qua</button>\r\n");
      out.write("			  <button class=\"time-btn\" data-time=\"7\">7 ngày</button>\r\n");
      out.write("			  <button class=\"time-btn\" data-time=\"31\">31 ngày</button>\r\n");
      out.write("			  <button class=\"time-btn\" data-time=\"90\">3 tháng</button>\r\n");
      out.write("			  <button class=\"time-btn\" data-time=\"365\">12 tháng</button>\r\n");
      out.write("			  <button class=\"time-btn\" data-time=\"20000\">Toàn thời gian</button>\r\n");
      out.write("			</div>\r\n");
      out.write("			<br>\r\n");
      out.write("			<table id=\"my-table\">\r\n");
      out.write("				<thead>\r\n");
      out.write("					<tr>\r\n");
      out.write("						<th>ID</th>\r\n");
      out.write("						<th>Ngày Lập</th>\r\n");
      out.write("						<th>Tổng Tiền</th>\r\n");
      out.write("					</tr>\r\n");
      out.write("				</thead>\r\n");
      out.write("				<tbody id=\"bill-list\">\r\n");
      out.write("					");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("				</tbody>\r\n");
      out.write("			</table>\r\n");
      out.write("		</div>\r\n");
      out.write("		<div class=\"right\">\r\n");
      out.write("			<div class=\"total\">\r\n");
      out.write("			  <span class=\"total-label\">Doanh thu:</span>\r\n");
      out.write("			  <span class=\"total-amount\" id=\"total-amount\"></span>\r\n");
      out.write("			</div>\r\n");
      out.write("			<div class=\"top5\">Top 5 sản phẩm</div>\r\n");
      out.write("			<br>\r\n");
      out.write("			<div class=\"product-container\"></div>\r\n");
      out.write("		</div>\r\n");
      out.write("	</div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("const table = document.getElementById(\"my-table\");\r\n");
      out.write("const tbody = table.getElementsByTagName(\"tbody\")[0];\r\n");
      out.write("const rows = tbody.getElementsByTagName(\"tr\");\r\n");
      out.write("const originalRows = [...rows];\r\n");
      out.write("let a = true;\r\n");
      out.write("\r\n");
      out.write("function topProduct() {\r\n");
      out.write("	let newList = [];\r\n");
      out.write("	let idToWatch = [];\r\n");
      out.write("	const idList = document.querySelectorAll('.id-list');\r\n");
      out.write("	const i = 1;\r\n");
      out.write("	\r\n");
      out.write("	idList.forEach(id => {\r\n");
      out.write("		idToWatch.push(id.dataset.billId);\r\n");
      out.write("	});\r\n");
      out.write("	\r\n");
      out.write("	for (let i = 0; i < cthdList.length; i++) {\r\n");
      out.write("		  let cthd = cthdList[i];\r\n");
      out.write("		  let found = false;\r\n");
      out.write("		  let inWL = false;\r\n");
      out.write("		  		  \r\n");
      out.write("		  for (let j = 0; j < idToWatch.length; j++) {\r\n");
      out.write("			  if (cthd.id == idToWatch[j]) {\r\n");
      out.write("				  inWL = true;\r\n");
      out.write("				  break;\r\n");
      out.write("			  }\r\n");
      out.write("		  }\r\n");
      out.write("\r\n");
      out.write("		  if (inWL) {\r\n");
      out.write("			  for (let j = 0; j < newList.length; j++) {\r\n");
      out.write("				    if (cthd.maSP === newList[j].maSP && cthd.maSize === newList[j].maSize) {\r\n");
      out.write("				      newList[j].soLuong += cthd.soLuong;\r\n");
      out.write("				      found = true;\r\n");
      out.write("				      break;\r\n");
      out.write("				    }\r\n");
      out.write("				  }\r\n");
      out.write("\r\n");
      out.write("				  if (!found) {\r\n");
      out.write("				    newList.push({\r\n");
      out.write("				      maSP: cthd.maSP,\r\n");
      out.write("				      maSize: cthd.maSize,\r\n");
      out.write("				      soLuong: cthd.soLuong\r\n");
      out.write("				    });\r\n");
      out.write("				  }\r\n");
      out.write("		  }\r\n");
      out.write("		}\r\n");
      out.write("	\r\n");
      out.write("	newList.sort((a, b) => b.soLuong - a.soLuong);\r\n");
      out.write("	const newList5 = newList.slice(0,5);\r\n");
      out.write("	\r\n");
      out.write("	// Get the container for the products\r\n");
      out.write("	const productContainer = document.querySelector('.product-container');\r\n");
      out.write("\r\n");
      out.write("	// Clear any existing products\r\n");
      out.write("	productContainer.innerHTML = '';\r\n");
      out.write("\r\n");
      out.write("	for (const product of newList5) {\r\n");
      out.write("	  // Create a new <div> element to hold the product information\r\n");
      out.write("		const productDiv = document.createElement('div');\r\n");
      out.write("		productDiv.classList.add('product');\r\n");
      out.write("\r\n");
      out.write("		const tenSpan = document.createElement('span');\r\n");
      out.write("		tenSpan.textContent = product.maSP;\r\n");
      out.write("		for (let j = 0; j < spList.length; j++) {\r\n");
      out.write("			if (product.maSP === spList[j].maSP) {\r\n");
      out.write("				tenSpan.textContent = spList[j].ten;\r\n");
      out.write("				break;\r\n");
      out.write("			}\r\n");
      out.write("		}\r\n");
      out.write("		productDiv.appendChild(tenSpan);\r\n");
      out.write("\r\n");
      out.write("		const maSizeSpan = document.createElement('span');\r\n");
      out.write("		if (product.maSize === \"K\") {\r\n");
      out.write("			maSizeSpan.textContent = \"\";\r\n");
      out.write("		} else maSizeSpan.textContent = product.maSize;\r\n");
      out.write("		productDiv.appendChild(maSizeSpan);\r\n");
      out.write("\r\n");
      out.write("		const soLuongSpan = document.createElement('span');\r\n");
      out.write("		soLuongSpan.textContent = product.soLuong;\r\n");
      out.write("		productDiv.appendChild(soLuongSpan);\r\n");
      out.write("\r\n");
      out.write("		productContainer.appendChild(productDiv);\r\n");
      out.write("\r\n");
      out.write("	}\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("	\r\n");
      out.write("function formatMoney(amount, decimalCount = 0, decimal = \".\", thousands = \",\") {\r\n");
      out.write("	  try {\r\n");
      out.write("	    decimalCount = Math.abs(decimalCount);\r\n");
      out.write("	    decimalCount = isNaN(decimalCount) ? 2 : decimalCount;\r\n");
      out.write("\r\n");
      out.write("	    const negativeSign = amount < 0 ? \"-\" : \"\";\r\n");
      out.write("\r\n");
      out.write("	    let i = parseInt(amount = Math.abs(Number(amount) || 0).toFixed(decimalCount)).toString();\r\n");
      out.write("	    let j = (i.length > 3) ? i.length % 3 : 0;\r\n");
      out.write("\r\n");
      out.write("	    return negativeSign + (j ? i.substr(0, j) + thousands : '') + i.substr(j).replace(/(\\d{3})(?=\\d)/g, \"$1\" + thousands) + (decimalCount ? decimal + Math.abs(amount - i).toFixed(decimalCount).slice(2) : \"\");\r\n");
      out.write("	  } catch (e) {\r\n");
      out.write("	  }\r\n");
      out.write("	}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("function filterRowsByDate(days) {\r\n");
      out.write("  const currentDate = new Date();\r\n");
      out.write("  const filteredRows = originalRows.filter(row => {\r\n");
      out.write("    const rowDate = new Date(row.cells[1].textContent);\r\n");
      out.write("    const timeDiff = Math.abs(currentDate.getTime() - rowDate.getTime());\r\n");
      out.write("    const diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));\r\n");
      out.write("    return diffDays <= days;\r\n");
      out.write("  });\r\n");
      out.write("  \r\n");
      out.write("  let total = 0;\r\n");
      out.write("  filteredRows.forEach(row => {\r\n");
      out.write("    total += parseFloat(row.cells[2].textContent);\r\n");
      out.write("  });\r\n");
      out.write("  \r\n");
      out.write("  const totalAmount = document.getElementById('total-amount');\r\n");
      out.write("  \r\n");
      out.write("  totalAmount.textContent = formatMoney(total);\r\n");
      out.write("\r\n");
      out.write("  while (tbody.firstChild) {\r\n");
      out.write("    tbody.removeChild(tbody.firstChild);\r\n");
      out.write("  }\r\n");
      out.write("  for (const row of filteredRows) {\r\n");
      out.write("    tbody.appendChild(row);\r\n");
      out.write("  }\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("if (a == true) {\r\n");
      out.write("	filterRowsByDate(20000);\r\n");
      out.write("	topProduct();\r\n");
      out.write("	a = false;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("document.querySelectorAll(\".time-btn\").forEach(button => {\r\n");
      out.write("  const days = button.getAttribute(\"data-time\");\r\n");
      out.write("  button.addEventListener(\"click\", function() {\r\n");
      out.write("		filterRowsByDate(days);\r\n");
      out.write("		topProduct();\r\n");
      out.write("  });\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fset_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    boolean _jspx_th_c_005fset_005f0_reused = false;
    try {
      _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fset_005f0.setParent(null);
      // /WEB-INF/views/thongke/view3.jsp(11,1) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fset_005f0.setVar("root");
      // /WEB-INF/views/thongke/view3.jsp(11,1) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
      _jspx_th_c_005fset_005f0.setValue(new org.apache.jasper.el.JspValueExpression("/WEB-INF/views/thongke/view3.jsp(11,1) '${pageContext.servletContext.contextPath}'",_jsp_getExpressionFactory().createValueExpression(_jspx_page_context.getELContext(),"${pageContext.servletContext.contextPath}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
      int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
      if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      _jspx_th_c_005fset_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fset_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fset_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    boolean _jspx_th_c_005fforEach_005f0_reused = false;
    try {
      _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f0.setParent(null);
      // /WEB-INF/views/thongke/view3.jsp(69,5) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setVar("hd");
      // /WEB-INF/views/thongke/view3.jsp(69,5) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/views/thongke/view3.jsp(69,5) '${listHD}'",_jsp_getExpressionFactory().createValueExpression(_jspx_page_context.getELContext(),"${listHD}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
      int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
        if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("						<tr>\r\n");
            out.write("							<td class=\"id-list\" data-bill-id = \"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${hd.ID}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write('"');
            out.write('>');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${hd.ID}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("</td>\r\n");
            out.write("							<td>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${hd.NGAYLAP}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("</td>\r\n");
            out.write("							<td>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${hd.TONGTIEN}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("</td>\r\n");
            out.write("						</tr>\r\n");
            out.write("					");
            int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_005fforEach_005f0.doFinally();
      }
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
      _jspx_th_c_005fforEach_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fforEach_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fforEach_005f0_reused);
    }
    return false;
  }
}
