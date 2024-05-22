package ptithcm.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.entity.ChiTietKhuyenMai;
import ptithcm.entity.ChiTietSanPham;
import ptithcm.entity.CongThuc;
import ptithcm.entity.HoaDon;
import ptithcm.entity.KhuyenMai;
import ptithcm.entity.Loai;
import ptithcm.entity.SanPham;
import ptithcm.entity.NguyenLieu;
import ptithcm.entity.ChiTietNguyenLieu;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.ServletContext;

import org.hibernate.Transaction;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMethod;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@Controller
@Transactional
@RequestMapping("/product/")
public class ProductController {
	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext context;
	public List<HoaDon> getList_HD() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM HoaDon");
		@SuppressWarnings("unchecked")
		List<HoaDon> list = query.list();
		return list;
	}
	
	public List<SanPham> getList_SP() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM SanPham");
		@SuppressWarnings("unchecked")
		List<SanPham> list = query.list();
		return list;
	}
	
	public List<Loai> getList_L() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM Loai");
		@SuppressWarnings("unchecked")
		List<Loai> list = query.list();
		return list;
	}
	
	public List<ChiTietSanPham> getList_CTSP() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM ChiTietSanPham");
		@SuppressWarnings("unchecked")
		List<ChiTietSanPham> list = query.list();
		return list;
	}
	
	public List<ChiTietKhuyenMai> getList_CTKM() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM ChiTietKhuyenMai");
		@SuppressWarnings("unchecked")
		List<ChiTietKhuyenMai> list = query.list();
		return list;
	}
	
	public List<KhuyenMai> getList_KM() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM KhuyenMai");
		@SuppressWarnings("unchecked")
		List<KhuyenMai> list = query.list();
		return list;
	}
	public String getMaKhuyenMai() {
		Timestamp thoigian=Timestamp.valueOf(LocalDateTime.now());
		Session session = factory.getCurrentSession();
		String hql = "from KhuyenMai where TGBD <= :thoigian and TGKT >= :thoigian ";
		Query query = session.createQuery(hql);
		query.setParameter("thoigian",thoigian);
		
		if (query.list().size() == 0) return "";
		
		KhuyenMai list = ( KhuyenMai) query.list().get(0);
		return list.getMAKM();
	}
	public List<NguyenLieu> getList_NL() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM NguyenLieu");
		@SuppressWarnings("unchecked")
		List<NguyenLieu> list = query.list();
		return list;
	}
	
	public List<ChiTietNguyenLieu> getList_CTNL() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM ChiTietNguyenLieu");
		@SuppressWarnings("unchecked")
		List<ChiTietNguyenLieu> list = query.list();
		return list;
	}
	
	public List<CongThuc> getList_CT() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM CongThuc");
		@SuppressWarnings("unchecked")
		List<CongThuc> list = query.list();
		return list;
	}
	
	
	@RequestMapping("viewSP")
	@Transactional
	public String func1(ModelMap model) {
		List<SanPham> myList = getList_SP();
		List<Loai> myList1 = getList_L();
		List<ChiTietSanPham> myList2 = getList_CTSP();
		List<HoaDon> myList3 = getList_HD();
		List<ChiTietNguyenLieu> myList4 = getList_CTNL();
		List<ChiTietKhuyenMai> myList5 = getList_CTKM();
		List<NguyenLieu> myList6 = getList_NL();
		List<CongThuc> myList7 = getList_CT();
		boolean found;
		
		for (int i = 0; i < myList.size(); i++) {
			found = false;
			for (int j = 0; j < myList2.size(); j++) {
				if (myList.get(i).getMASP().equals(myList2.get(j).getPk().getSANPHAM().getMASP())) {
					found = true;
					break;
				}
			}
			if (found == false) {
				myList.remove(i);
				i--;
			}
			
		}
		
		
        String km = getMaKhuyenMai();
		
		model.addAttribute("listSP", myList);
		model.addAttribute("listLSP",myList1);
		model.addAttribute("listCTSP", myList2);
		model.addAttribute("listHD", myList3);
		model.addAttribute("nextID", myList3.size());
		model.addAttribute("manv", LoginController.manv);
		model.addAttribute("makm", km);
		model.addAttribute("listCTNL", myList4);
		model.addAttribute("listCTKM", myList5);
		model.addAttribute("listNL", myList6);
		model.addAttribute("listCT", myList7);
		return "views/sanpham/view3";
	}	
	
	public static String[] splitString(String input) {
	    String[] output = input.split(",");
	    return output;
	}
	
	public static int[] splitInt(String str) {
	    String[] strArr = str.split(",");
	    int[] intArr = new int[strArr.length];
	    for (int i = 0; i < strArr.length; i++) {
	        intArr[i] = Integer.parseInt(strArr[i]);
	    }
	    return intArr;
	}

	@RequestMapping(value = "viewSP", method = RequestMethod.POST)
	public String saveData(@RequestParam("MAHD") String MAHD, @RequestParam("MANV") String MANV,
							@RequestParam("TONGTIEN") String TONGTIEN, @RequestParam("KMAI") String KMAI,
							@RequestParam("MANL") String MANL, @RequestParam("SLT") String SLT,
							@RequestParam("MASP") String MASP, @RequestParam("MASIZE") String MASIZE,
							@RequestParam("SOLUONG") String SOLUONG) {
		Connection conn = null;
		  PreparedStatement pstmt = null;
		  try {
		    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		    String url = "jdbc:sqlserver://localhost:1433;databaseName=HIGHLANDS;";
		    String username = "sa";
		    String password = "123";
		    conn = DriverManager.getConnection(url, username, password);
		    
		    String sql = "INSERT INTO HOADON (ID, NGAYLAP, TONGTIEN, PHANLOAI, MANV, MAKM) VALUES (?, GETDATE(), ?, 0, ?, ?)";
			String sql1 = "INSERT INTO CT_HOADON (ID, MASP, MASIZE, SOLUONG) VALUES (?, ?, ?, ?)";
		    String sql2 = "UPDATE NGUYENLIEU SET SLTON = ? WHERE MANL = ?";
		    String sql3 = "INSERT INTO HOADON (ID, NGAYLAP, TONGTIEN, PHANLOAI, MANV, MAKM) VALUES (?, GETDATE(), ?, 0, ?, NULL)";
			
		    String id = MAHD;
			String tongtienStr = TONGTIEN;
			int total = 0;
			if (tongtienStr != null && !tongtienStr.isEmpty()) {
			    total = Integer.parseInt(tongtienStr);
			}	
			String manv = MANV;
			String km = KMAI;
			
			if (km != "") {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setInt(2, total);
				pstmt.setString(3, manv);
				pstmt.setString(4, km);
			} else {
				pstmt = conn.prepareStatement(sql3);
				pstmt.setString(1, id);
				pstmt.setInt(2, total);
				pstmt.setString(3, manv);
			}

			pstmt.executeUpdate();
			pstmt.close();
			
			String[] maspArr = splitString(MASP);
			String[] masizeArr = splitString(MASIZE);
			int[] soluongArr = splitInt(SOLUONG);
			String masp;
			String masize;
			int soluong;
			
			for (int i = 0; i < masizeArr.length; i++) {
				masp = maspArr[i];
				masize = masizeArr[i];
				soluong = soluongArr[i];
				
				System.out.println(id + " " + masp + " " + masize + " " + soluong);
				
				
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, id);
				pstmt.setString(2, masp);
				pstmt.setString(3, masize);
				pstmt.setInt(4, soluong);
				
				pstmt.executeUpdate();
				pstmt.close();
			}
			
			String[] manlArr = splitString(MANL);
			String[] sltArr = splitString(SLT);
			System.out.println(MANL);
			System.out.println(SLT);
			String maNL;
			String soluongton;
			
			for (int i = 0; i < manlArr.length; i++) {
				maNL = manlArr[i];
				soluongton = sltArr[i];
				
				System.out.println(maNL + " " + soluongton);
				
				float soluongFloat = 0;
				if (soluongton != null && !soluongton.isEmpty()) {
				    soluongFloat = Float.parseFloat(soluongton);
				}
				
				pstmt = conn.prepareStatement(sql2);
				pstmt.setFloat(1, soluongFloat);
				pstmt.setString(2, maNL);
				
				pstmt.executeUpdate();
				pstmt.close();
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
		
		  try {
			    Thread.sleep(10000); 
			} catch (InterruptedException e) {
				
			}
		  
		  
		return "redirect:http://localhost:8080/Highlands/product/viewSP.htm";
	}
	
	public List<SanPham> layDanhSachSanPham() {
		Session session = factory.getCurrentSession();
		String hql = "FROM SanPham";
		Query query = session.createQuery(hql);
		List<SanPham> list = query.list();
		return list;
	}

	@RequestMapping("list")
	public String dssanpham(ModelMap model) {
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("sanpham", new SanPham());
		List<SanPham> DS = layDanhSachSanPham();
		model.addAttribute("dssanpham", DS);
		return "views/quanly/sanpham";
	}
	@ModelAttribute("cacloai")
	public List<Loai>layDanhSachLoai(){
		Session session=factory.getCurrentSession();
		String hql="From Loai";
		Query query=session.createQuery(hql);
		List<Loai>list=query.list();
		return list;
	}
	
	public SanPham getSanPham(String ma) {
		Session session = factory.getCurrentSession();
		String hql = "from SanPham where MASP = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", ma);
		SanPham list = (SanPham) query.list().get(0);
		return list;
	}
	
	public boolean kiemTraMaSP(String mASP) {
		mASP=mASP.toUpperCase();
		Pattern pattern = Pattern.compile("^SP\\d+$");
	    Matcher matcher = pattern.matcher(mASP);
	   	return matcher.matches();
	}

	public boolean kiemTraTenSP(String ten) {
		Pattern pattern = Pattern.compile("^[^<>'\\\"/;`%@$#!&*().,:?~]*$");
		 Matcher matcher = pattern.matcher(ten);
		 return matcher.matches();
	}
	
	public int delete(Object object) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(object);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@RequestMapping(value = "/list/{MASP}.htm", params = "linkDelete")
	public String deleteSanPham(ModelMap model,@PathVariable("MASP")String MASP,SanPham sanpham) {
		System.out.println("linkDelete");
		SanPham sanphamtam=getSanPham(MASP);
		int check = this.delete(sanphamtam);
		if (check == 1) {
			model.addAttribute("message1", "Xóa sản phẩm thành công");
		} else {
			model.addAttribute("message0", "Xóa sản phẩm thất bại");
		}
		List<SanPham> DS = this.layDanhSachSanPham();
		model.addAttribute("dssanpham", DS);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("sanpham",sanpham);
		return "views/quanly/sanpham";
	}

	@RequestMapping(value = "/list/{MASP}.htm", params = "linkEdit")
	public String editSanPham(ModelMap model,
			@PathVariable("MASP") String MASP) {
		System.out.println("linkEdit");
		List<SanPham> DS = this.layDanhSachSanPham();
		model.addAttribute("dssanpham", DS);
		model.addAttribute("btnStatus", "btnEdit");
		model.addAttribute("sanpham", this.getSanPham(MASP));
		return "views/quanly/sanpham";
	}

	public int update(Object object) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(object);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	
	@RequestMapping(value = "/list", params = "btnEdit")
	public String edit_SanPham(ModelMap model, @ModelAttribute("sanpham") SanPham sanpham,
			@RequestParam("photo")MultipartFile photo, BindingResult errors) {
		System.out.println("btnEdit");
		if(sanpham.getMASP().isBlank()) {
			errors.rejectValue("MASP", "sanpham", "Vui lòng nhập mã sản phẩm");
		}
		if(sanpham.getTEN().isBlank()) {
			errors.rejectValue("TEN","sanpham","Vui lòng nhập tên sản phẩm");
		}
		else if(!kiemTraTenSP(sanpham.getTEN())) {
			errors.rejectValue("TEN","sanpham","Vui lòng không nhập kí tự đặc biệt");
		}
		if(!errors.hasErrors()) {
			if(!photo.isEmpty()) {
				try {
					String photoPath=context.getRealPath("/resources/img/sanpham/"+photo.getOriginalFilename());
					photo.transferTo(new File(photoPath));
					sanpham.setHINHANH(photo.getOriginalFilename());
				}
				catch(Exception e) {
					model.addAttribute("message","Lỗi lưu hình !");
				}
			}
			int check = this.update(sanpham);
			if (check != 0) {
				model.addAttribute("message1", "Sửa sản phẩm thành công!");
				model.addAttribute("btnStatus", "btnAdd");
				model.addAttribute("sanpham", new SanPham());
			} else {
				model.addAttribute("message0", "Sửa sản phẩm thât bại! Mã sản phẩm không tồn tại hoặc tên trùng");
				model.addAttribute("btnStatus", "btnEdit");
			}
		}
		else {
			model.addAttribute("btnStatus", "btnEdit");
		}
		List<SanPham> DS = this.layDanhSachSanPham();
		model.addAttribute("dssanpham", DS);
		
		return "views/quanly/sanpham";
	}

	public int insert(Object object) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(object);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	
	@RequestMapping(value = "/list", params = "btnAdd")
	public String addSanPham(ModelMap model,  @ModelAttribute("sanpham") SanPham sanpham, 
			@RequestParam("photo")MultipartFile photo, BindingResult errors) {
		System.out.println("btnAdd");
		if(sanpham.getMASP().isBlank()) {
			errors.rejectValue("MASP", "sanpham", "Vui lòng nhập mã sản phẩm");
		}
		else if(!kiemTraMaSP(sanpham.getMASP())) {
			errors.rejectValue("MASP", "sanpham", "Vui lòng nhập đúng dạng chuẩn của mã: SP + số");
		}
		else sanpham.setMASP(sanpham.getMASP().toUpperCase());
		if(sanpham.getTEN().isBlank()) {
			errors.rejectValue("TEN","sanpham","Vui lòng nhập tên sản phẩm");
		}
		else if(!kiemTraTenSP(sanpham.getTEN())) {
			errors.rejectValue("TEN","sanpham","Vui lòng không nhập kí tự đặc biệt");
		}
		if(!errors.hasErrors()) {
			try {
				String photoPath=context.getRealPath("/resources/img/sanpham/"+photo.getOriginalFilename());
					photo.transferTo(new File(photoPath));
					sanpham.setHINHANH(photo.getOriginalFilename());
				}
				catch(Exception e) {
					model.addAttribute("message","Lỗi lưu hình !");
				}
			int check = this.insert(sanpham);
			if (check != 0) {
				model.addAttribute("message1", "Thêm sản phẩm thành công!");
				model.addAttribute("sanpham", new SanPham());
			} else {
				model.addAttribute("message0", "Thêm sản phẩm thất bại! Trùng mã hoặc tên");
			}
		}
		model.addAttribute("btnStatus", "btnAdd");
		List<SanPham> DS = this.layDanhSachSanPham();
		model.addAttribute("dssanpham", DS);
		return "views/quanly/sanpham";
	}
}
