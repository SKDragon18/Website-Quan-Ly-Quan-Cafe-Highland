package ptithcm.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.entity.HoaDon;

@Controller
public class BillController {
	@Autowired
	SessionFactory factory;
	
	public List<HoaDon> getList_HD() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM HoaDon");
		@SuppressWarnings("unchecked")
		List<HoaDon> list = query.list();
		return list;
	}
	
	@RequestMapping("/bill")
	@Transactional
	public String bill(ModelMap model) {
		List<HoaDon> myList = getList_HD();
		model.addAttribute("listHD", myList);
		return "views/hoadon/view3";
	}
	
	public static String[] splitString(String input) {
	    String[] output = input.split(",");
	    return output;
	}

	
	@RequestMapping(value = "/bill", method = RequestMethod.POST)
	public String saveBill(@RequestParam("ID") String billId, @RequestParam("PHANLOAI") String pl) {
		if (billId.length() < 2) return "redirect:http://localhost:8080/Highlands/bill.htm";  
		
		Connection conn = null;
		  PreparedStatement pstmt = null;
		  try {
		    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		    String url = "jdbc:sqlserver://localhost:1433;databaseName=HIGHLANDS;";
		    String username = "sa";
		    String password = "123";
		    conn = DriverManager.getConnection(url, username, password);
		    
		    String[] idArr = splitString(billId);
		    String[] plArr = splitString(pl);
		    
		    String sql = "UPDATE HOADON SET PHANLOAI = CAST(0 AS bit) WHERE ID = ?";
		    String sql1 = "UPDATE HOADON SET PHANLOAI = CAST(1 AS bit) WHERE ID = ?";
			String id;
			String type;
			
			for (int i = 0; i < idArr.length; i++) {
				id = idArr[i];
				type = plArr[i];
				
				System.out.println(id + "-" + type);
				if (id != null && type != null) {
					if (type.equals("false")) {
						pstmt = conn.prepareStatement(sql1);
						System.out.println("MIT");
					}
					else pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					pstmt.close();
				}
			}
			
			conn.close();

		  } catch (ClassNotFoundException e) {
		    e.printStackTrace();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  } finally {
		    try {
		      if (pstmt != null) pstmt.close();
		      if (conn != null) conn.close();
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		  }
		  
		  return "redirect:http://localhost:8080/Highlands/bill.htm";
	}
}
