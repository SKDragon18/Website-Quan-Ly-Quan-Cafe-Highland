package ptithcm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.entity.NhanVien;
import ptithcm.entity.TaiKhoan;

@Controller
@RequestMapping("home")
public class HomeController {
	@Autowired
	SessionFactory factory;
	public String p = "";
	@Autowired
	private HttpServletRequest request;

	@RequestMapping("/index")
	public String index(ModelMap model) {
		return "views/dangnhap";
	}

	@RequestMapping("/trangchuquanly")
	public String indexq(ModelMap model) {
		return "views/trangchuquanly";
	}
	
	@RequestMapping("/trangchunhanvien")
	public String indexn(ModelMap model) {
		return "views/trangchunhanvien";
	}
	
	@RequestMapping("/changepassword")
	public String doimk() {
		return "views/doimatkhau";
	}
	
	@RequestMapping("/quenmatkhau")
	public String quenmatkhau() {
		return "views/quenmatkhau";
	}

	public TaiKhoan get1TaiKhoan(String ma) {
		Session session = factory.openSession();
		String hql = "FROM TaiKhoan where TENDANGNHAP = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", ma);
		TaiKhoan list = (TaiKhoan) query.list().get(0);
		return list;
	}

	@RequestMapping("/changepasswordsuccess")
	public String quenmatkhau1(ModelMap model) {
//		model.addAttribute("tk", get1TaiKhoan(LoginController.manv));
		model.addAttribute("message1", "Thay đổi mật khẩu thành công!");
		return "views/doimatkhauthanhcong";
	}

	@RequestMapping("/password")
	public String doimatkhau(HttpServletRequest rq, ModelMap model, HttpSession ss) {
		String oldpassword = rq.getParameter("oldpassword");
		String newpassword = rq.getParameter("newpassword");
		System.out.println(LoginController.manv + " " + oldpassword + " " + newpassword);
		if (oldpassword.equals("") || newpassword.equals(""))
			model.addAttribute("message0", "Vui lòng nhập đủ mọi thông tin liên quan!");
		else if ((oldpassword.trim()).equals(newpassword.trim()))
			model.addAttribute("message0", "Mật khẩu cũ và mật khẩu mới trùng nhau. Mật khẩu của bạn vẫn giữ nguyên!");
		else {
			p = newpassword;
			Session session = factory.openSession();
			String hql = "FROM TaiKhoan WHERE TENDANGNHAP ='" + LoginController.manv + "'";
			Query query = session.createQuery(hql);
			TaiKhoan tk = (TaiKhoan) query.list().get(0);
			if (SupportController.descryptPassword(oldpassword.trim(), tk.getMATKHAU())) {
				TaiKhoan temp = new TaiKhoan();
				temp.setTENDANGNHAP(tk.getTENDANGNHAP());
				temp.setQuyen(tk.getQuyen());
				temp.setTRANGTHAI(tk.TRANGTHAI);
				temp.setMATKHAU(SupportController.encryptPassword(newpassword));
				int check = this.update(temp);
				if (check == 1) {
					return "redirect:http://localhost:8080/Highlands/home/changepasswordsuccess.htm";
				} else {
					model.addAttribute("message0", "Thay đổi mật khẩu thất bại!");
					return "views/doimatkhau";
				}
			} else {
				model.addAttribute("message0", "Mật khẩu cũ không đúng, vui lòng kiểm tra lại!");
			}
		}
		return "views/doimatkhau";
	}

	public NhanVien getNVByMaNV(String manv) {
		Session session = factory.openSession();
		String hql = "FROM NhanVien WHERE MANV ='" + manv + "'";
		Query query = session.createQuery(hql);
		NhanVien list = (NhanVien) query.list().get(0);
		return list;
	}

	@RequestMapping("/profile")
	public String trangcanhan(ModelMap model) {
		String referringUrl = request.getHeader("referer");
//		System.out.println("Previous link: " + referringUrl);
		model.addAttribute("link", referringUrl);
		TaiKhoan tk = get1TaiKhoan(LoginController.manv);
		model.addAttribute("tk", tk.getQuyen().getMAQUYEN());
		NhanVien temp = getNVByMaNV(LoginController.manv);
		model.addAttribute("nv", temp);
		model.addAttribute("hoten", temp.getHO() + " " + temp.getTEN());
		return "views/quanly/profile";
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

	@RequestMapping("/profile/edit")
	public String trangcanhan1(ModelMap model) {
		TaiKhoan tk = get1TaiKhoan(LoginController.manv);
		model.addAttribute("tk", tk.getQuyen().getMAQUYEN());
		NhanVien temp = getNVByMaNV(LoginController.manv);
		model.addAttribute("nv", temp);
		model.addAttribute("hoten", temp.getHO() + " " + temp.getTEN());
		System.out.print(get1TaiKhoan(temp.getMANV()).getQuyen().getMAQUYEN().equals("QL"));
		if(!get1TaiKhoan(temp.getMANV()).getQuyen().getMAQUYEN().equals("QL"))
			return "views/quanly/trangcanhannv";
		return "views/quanly/trangcanhan";
	}

	@RequestMapping(value = "/profile/edit", params = "luu")
	public String edit_Thongtin(ModelMap model, @ModelAttribute("nv") NhanVien nv) throws ParseException {
		NhanVien temp1 = getNVByMaNV(LoginController.manv);
		model.addAttribute("nv", temp1);
		model.addAttribute("hoten", temp1.getHO() + " " + temp1.getTEN());
		TaiKhoan tk = get1TaiKhoan(LoginController.manv);
		model.addAttribute("tk", tk.getQuyen().getMAQUYEN());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = dateFormat.format(nv.getNGAYSINH());
		Date date = dateFormat.parse(dateStr);
		java.util.Date utilDate = new java.util.Date(date.getTime());
		NhanVien temp = new NhanVien();
		temp.setDIACHI(nv.getDIACHI());
		temp.setEMAIL1(nv.getEMAIL1());
		if (nv.getEMAIL2() != null)
			temp.setEMAIL2(nv.getEMAIL2());
		temp.setGT(nv.getGT());
		temp.setHO(nv.getHO());
		temp.setMANV(LoginController.manv);
		temp.setNGAYSINH(utilDate);
		temp.setSDT1(nv.getSDT1());
		System.out.println("Thong tin Update: " + temp.getMANV() + " " + temp.getHO() + " " + temp.getTEN() + " " + " "
				+ temp.getGT() + " " + temp.getNGAYSINH() + " " + temp.getDIACHI() + " " + temp.getEMAIL1() + " "
				+ temp.getSDT1() + temp.getEMAIL2() + " " + temp.getSDT2());
		if (nv.getSDT2() != null)
			temp.setSDT2(nv.getSDT2());
		temp.setTEN(nv.getTEN());
		int check = this.update(temp);
		if (check != 0) {
			model.addAttribute("message1", "Sửa nhân viên thành công!");
		} else {
			model.addAttribute("message0", "Sửa nhân viên thất bại!");
		}
		model.addAttribute("nv", temp);
		if(!get1TaiKhoan(temp.getMANV()).getQuyen().getMAQUYEN().equals("QL"))
			return "views/quanly/trangcanhannv";
		return "views/quanly/trangcanhan";
	}

	@RequestMapping("/bieudo")
	public String charts() {
		return "views/quanly/charts";
	}
}
