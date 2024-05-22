package ptithcm.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.bean.Mailer;
import ptithcm.entity.NhanVien;
import ptithcm.entity.Quyen;
import ptithcm.entity.TaiKhoan;

@Controller
@Transactional
@RequestMapping("/account")
public class AccountController {
	@Autowired
	SessionFactory factory;

	public List<TaiKhoan> LayDanhSachTaiKhoan() {
		Session session = factory.getCurrentSession();
		String hql = "FROM TaiKhoan";
		Query query = session.createQuery(hql);
		List<TaiKhoan> list = query.list();

		return list;
	}

	public String taoMatKhau() {
		Random generator = new Random();
		int value = generator.nextInt((999999 - 100000) + 1) + 100000;
		return value + "";
	}

	@ModelAttribute("aus")
	public List<Quyen> getQuyen() {// Chỉ 1 quản lý active
		Session session = factory.getCurrentSession();
		String hql = "FROM Quyen";
		Query query = session.createQuery(hql);
		List<Quyen> list = query.list();
		return list;
	}

	@ModelAttribute("trangthai")
	public List<TaiKhoan> getTrangThai() {
		Session session = factory.getCurrentSession();
		String hql = "select distinct p.TRANGTHAI from TaiKhoan p";
		Query query = session.createQuery(hql);
		List<TaiKhoan> list = query.list();
		return list;
	}

	@RequestMapping("list")
	public String dstaikhoan(ModelMap model) {
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("tk", new TaiKhoan());
		List<TaiKhoan> DS = this.LayDanhSachTaiKhoan();
		model.addAttribute("dstaikhoan", DS);
		return "views/quanly/taikhoanad";
	}

	@RequestMapping("admin")
	public String dstaikhoanad(ModelMap model) {
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("tk", new TaiKhoan());
		List<TaiKhoan> DS = this.LayDanhSachTaiKhoan();
		model.addAttribute("dstaikhoan", DS);
		return "views/quanly/taikhoanad";
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

	public TaiKhoan get1TaiKhoan(String ma) {
		Session session = factory.getCurrentSession();
		String hql = "FROM TaiKhoan where TENDANGNHAP = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", ma);
		TaiKhoan list = (TaiKhoan) query.list().get(0);
		return list;
	}

	public NhanVien getNVByTk(String manv) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVien WHERE MANV ='" + manv + "'";
		Query query = session.createQuery(hql);
		NhanVien list = (NhanVien) query.list().get(0);
		return list;
	}

	@RequestMapping(value = "/list/{TENDANGNHAP}.htm", params = "linkDelete")
	public String delete(ModelMap model, @ModelAttribute("tk") TaiKhoan tk) {
		model.addAttribute("btnStatus", "btnAdd");
		boolean flagAD = false;
		List<TaiKhoan> DS = this.LayDanhSachTaiKhoan();
		model.addAttribute("dstaikhoan", DS);
		int demQL = 0;
		int demAD = 0;
		for (int i = 0; i < DS.size(); i++) {
			if (DS.get(i).getQuyen().getMAQUYEN().equals("AD") && DS.get(i).TRANGTHAI == true) {
				demAD++;
			}
			if (DS.get(i).getQuyen().getMAQUYEN().equals("QL") && DS.get(i).TRANGTHAI == true) {
				demQL++;
			}
		}
		if (demAD > 1)
			flagAD = true;
		// khong sua mat khau
		TaiKhoan temp = new TaiKhoan();
		temp = get1TaiKhoan(tk.getTENDANGNHAP());
		if (temp.getQuyen().getMAQUYEN().equals("QL") && temp.TRANGTHAI == true && demQL == 1) {
			model.addAttribute("message0", temp.getTENDANGNHAP()
					+ " là tài khoản quản lý duy nhất còn hoạt động, bạn không thể xóa tài khoản nếu như bạn chưa cấp tài khoản cho nhân viên quản lý nào khác");
			model.addAttribute("dstaikhoan", DS);
		} else if (temp.getQuyen().getMAQUYEN().equals("AD") && temp.getTENDANGNHAP().equals(LoginController.manv)
				&& demAD == 1)

		{
			model.addAttribute("message0",
					"Bạn đang là Admin duy nhất, phải thêm Admin khác để quản trị trước khi xóa tài khoản!");
			model.addAttribute("dstaikhoan", DS);
		} else if (temp.getQuyen().getMAQUYEN().equals("AD") && temp.getTENDANGNHAP().equals(LoginController.manv)
				&& demAD > 1) {
			delete(tk);
			return "redirect:http://localhost:8080/Highlands/home/index.htm";
		} else if (temp.getQuyen().getMAQUYEN().equals("AD") && !tk.getTENDANGNHAP().equals(LoginController.manv)) {
			model.addAttribute("message0", "Bạn không có quyền xóa tài khoản của Admin khác!");
			model.addAttribute("dstaikhoan", DS);
		} else {
			int check = this.delete(tk);
			if (check == 1) {
				model.addAttribute("message1", "Xóa tài khoản thành công");
			} else {
				model.addAttribute("message0", "Xóa tài khoản thất bại");
			}
			List<TaiKhoan> DSM = this.LayDanhSachTaiKhoan();
			model.addAttribute("dstaikhoan", DSM);
			model.addAttribute("tk", new TaiKhoan());
		}
		return "views/quanly/taikhoanad";
	}

	@RequestMapping(value = "/list/{TENDANGNHAP}.htm", params = "linkEdit")
	public String editAccount(ModelMap model, @ModelAttribute("tk") TaiKhoan tk) {
		System.out.println("linkEdit");
		List<TaiKhoan> DS = this.LayDanhSachTaiKhoan();
		model.addAttribute("dstaikhoan", DS);
		model.addAttribute("tk", this.get1TaiKhoan(tk.getTENDANGNHAP()));
		model.addAttribute("btnStatus", "btnEdit");
		return "views/quanly/taikhoanad";
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
	public String edit_Account(ModelMap model, @ModelAttribute("tk") TaiKhoan tk) {
		// khong sua mat khau
		System.out.println("TENDN: " + tk.getTENDANGNHAP());
		TaiKhoan temp = new TaiKhoan();
		temp = get1TaiKhoan(tk.getTENDANGNHAP());
		List<TaiKhoan> DS = this.LayDanhSachTaiKhoan();
		model.addAttribute("dstaikhoan", DS);
		int demQL = 0;
		int demAD = 0;
		for (int i = 0; i < DS.size(); i++) {
			if (DS.get(i).getQuyen().getMAQUYEN().equals("AD") && DS.get(i).TRANGTHAI == true)
				demAD++;
			if (DS.get(i).getQuyen().getMAQUYEN().equals("QL") && DS.get(i).TRANGTHAI == true)
				demQL++;
		}
		if (temp.getQuyen().getMAQUYEN().equals("QL") && temp.TRANGTHAI == true && demQL == 1) {// DS có 1 quản lý nhưng
																								// lại chuyển nó về null
			model.addAttribute("message0",
					temp.getTENDANGNHAP() + " là tài khoản quản lý duy nhất còn hoạt động, không thể chuyển trạng thái hoạt động!");
			model.addAttribute("dstaikhoan", DS);
		} else if (temp.getQuyen().getMAQUYEN().equals("AD") && temp.getTENDANGNHAP().equals(LoginController.manv)
				&& demAD == 1) {
			model.addAttribute("message0",
					"Bạn đang là Admin duy nhất có quyền quản lý tài khoản, không được phép chuyển trạng thái hoạt động!");
			model.addAttribute("dstaikhoan", DS);
		} else if (temp.getQuyen().getMAQUYEN().equals("AD") && !temp.getTENDANGNHAP().equals(LoginController.manv)&&temp.TRANGTHAI==true) {
			model.addAttribute("message0", "Bạn không có quyền khóa tài khoản của Admin khác!");
			model.addAttribute("dstaikhoan", DS);
		} else {
			temp.setTRANGTHAI(tk.TRANGTHAI);
			boolean k = temp.getTENDANGNHAP().equals(LoginController.manv) && demAD > 1;
			int check = this.update(temp);
			System.out.print("dang nhap?: " + k);
			if (temp.getQuyen().getMAQUYEN().equals("AD") && temp.getTENDANGNHAP().equals(LoginController.manv)
					&& demAD > 1)
				return "redirect:http://localhost:8080/Highlands/login/logout.htm";
			if (check != 0) {
				model.addAttribute("message1",
						"Cập nhật trạng thái hoạt động cho tài khoản " + tk.getTENDANGNHAP() + " thành công!");
				model.addAttribute("btnStatus", "btnAdd");
				model.addAttribute("tk", new TaiKhoan());
			} else {
				model.addAttribute("message0",
						"Cập nhật trạng thái hoạt động cho tài khoản " + tk.getTENDANGNHAP() + " thất bại!");
				model.addAttribute("btnStatus", "btnEdit");
			}
		}
		List<TaiKhoan> DSM = this.LayDanhSachTaiKhoan();
		model.addAttribute("dstaikhoan", DSM);
		model.addAttribute("tk", new TaiKhoan());
		return "views/quanly/taikhoanad";
	}

	@RequestMapping(value = "/list/{TENDANGNHAP}.htm", params = "linkReset")
	public String reset_Account(ModelMap model, @ModelAttribute("tk") TaiKhoan tk) {
		// khong sua mat khau
		System.out.println("TENDN: " + tk.getTENDANGNHAP());
		TaiKhoan temp = new TaiKhoan();
		temp = get1TaiKhoan(tk.getTENDANGNHAP());
		List<TaiKhoan> DS = this.LayDanhSachTaiKhoan();
		model.addAttribute("dstaikhoan", DS);
			temp.setMATKHAU(SupportController.encryptPassword("12345678"));
			int check = this.update(temp);
			if (check != 0) {
				model.addAttribute("message1",
						"Tài khoản " + tk.getTENDANGNHAP() + " được reset mật khẩu thành công!");
				model.addAttribute("btnStatus", "btnAdd");
				model.addAttribute("tk", new TaiKhoan());
			} else {
				model.addAttribute("message0",
						"Tài khoản \" + tk.getTENDANGNHAP() + \" được reset mật khẩu thất bại!");
			}
		List<TaiKhoan> DSM = this.LayDanhSachTaiKhoan();
		model.addAttribute("dstaikhoan", DSM);
		model.addAttribute("tk", new TaiKhoan());
		return "views/quanly/taikhoanad";
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

	@Autowired
	Mailer mailer;

	@RequestMapping(value = "/list", params = "btnAdd")
	public String addAccount(ModelMap model, @ModelAttribute("tk") TaiKhoan tk, HttpServletRequest rq) {
		String matkhau = taoMatKhau();
		System.out.print("mk: " + matkhau);
		if (matkhau.equals("") || tk.getTENDANGNHAP().equals("")) {
			model.addAttribute("message0", "Vui lòng nhập đầy đủ thông tin");
		} else {
			tk.setMATKHAU(SupportController.encryptPassword(matkhau));
			int check = this.insert(tk);
			if (check != 0) {
				model.addAttribute("message1", "Thêm tài khoản thành công!");
				NhanVien nhanVien = getNVByTk(tk.getTENDANGNHAP());
				try {
					Session session2 = factory.openSession();
					Transaction t = session2.beginTransaction();
					mailer.send("tathuongthuong2516@gmail.com", nhanVien.getEMAIL1(), "Cấp mật khẩu tài khoản Highlands",
							"\nMật khẩu cho tài khoản Highlands của bạn là: " + matkhau);
					model.addAttribute("message1", "Mật khẩu đã được gửi đến email của nhân viên "+tk.getTENDANGNHAP());
				} catch (Exception e) {
					model.addAttribute("message0", "Gửi email lỗi!");
				}
//				if (tk.getQuyen().getMAQUYEN().equals("AD") && tk.TRANGTHAI == true) {
//					TaiKhoan temp = new TaiKhoan();
//					System.out.println("Khóa tài khoản " + tk.getTENDANGNHAP());
//					temp = get1TaiKhoan(LoginController.manv);
//					temp.setTRANGTHAI(false);
//					int check1 = this.update(temp);
//					tk.setTRANGTHAI(true);
//					update(tk);
//					return "redirect:http://localhost:8080/Highlands/login/logout.htm";
//				}
				model.addAttribute("tk", new TaiKhoan());
			} else {
				boolean flag = false;
				List<TaiKhoan> DS = this.LayDanhSachTaiKhoan();
				for (int i = 0; i < DS.size(); i++) {
					if (DS.get(i).getTENDANGNHAP().equals(tk.getTENDANGNHAP())) {
						flag = true;
					}
				}
				if (flag == false)
					model.addAttribute("message0", "Nhân viên mã " + tk.getTENDANGNHAP()
							+ " không tồn tại nên bạn không thể tạo tài khoản cho nhân viên này");
				else
					model.addAttribute("message0", "Tài khoản đã tồn tại trong hệ thống");
			}
		}
		List<TaiKhoan> DS = this.LayDanhSachTaiKhoan();
		model.addAttribute("dstaikhoan", DS);
		model.addAttribute("btnStatus", "btnAdd");
		return "views/quanly/taikhoanad";
	}
}
