package ptithcm.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.bean.Mailer;
import ptithcm.entity.NhanVien;
import ptithcm.entity.TaiKhoan;

@Controller
@Transactional
@RequestMapping("/login")
public class LoginController {
	public static String manv = "";
	@Autowired
	Mailer mailer;

	@Autowired
	SessionFactory factory;

	@RequestMapping("forget")
	public String forget() {
		return "Authority/forget";
	}

	@RequestMapping("logininfo")
	public String login(HttpServletRequest rq, ModelMap model, HttpSession ss) {
		String username = rq.getParameter("username");
		String password = rq.getParameter("password");
		if (username.equals("") || password.equals("")) {
			model.addAttribute("message0", "Vui lòng nhập đủ thông tin");
			return "views/dangnhap";
		} else {
			Session session = factory.getCurrentSession();
			String hql = "FROM TaiKhoan WHERE TENDANGNHAP ='" + username + "'";
			Query query = session.createQuery(hql);
			List<TaiKhoan> list = query.list();
			if (list.size() > 0) {
				if (list.get(0).TRANGTHAI == false) {
					model.addAttribute("message0", "Tài khoản hiện đang bị khóa, bạn không thể đăng nhập");
					return "views/dangnhap";
				} else {
					String hql1 = "FROM TaiKhoan WHERE TENDANGNHAP ='" + username.trim() + "' AND MATKHAU = '"
							+ SupportController.encryptPassword(password.trim()) + "'";
					Query query1 = session.createQuery(hql1);
					List<TaiKhoan> list1 = query1.list();
					if (list1.size() == 1) {
						manv = username;
						ss.setAttribute("ma", manv);
						TaiKhoan temp = list1.get(0);
						ss.setAttribute("quyen", temp.getQuyen().getMAQUYEN());
						if (temp.getQuyen().getMAQUYEN().equals("QL"))
							return "views/trangchuquanly";
						else if (temp.getQuyen().getMAQUYEN().equals("AD"))
							return "redirect:http://localhost:8080/Highlands/account/admin.htm";
						else
							return "views/trangchunhanvien";
					} else {
						model.addAttribute("message0", "Đăng nhập thất bại, sai mật khẩu");
						return "views/dangnhap";
					}
				}
			}
			model.addAttribute("message0", "Đăng nhập thất bại, tài khoản không tồn tại");
			return "views/dangnhap";
		}
	}

	@RequestMapping("logout")
	public String logout(HttpSession ss, ModelMap model) {
		manv = "";
		ss.removeAttribute("ma");
		return "views/dangnhap";
	}

	public String taoMatKhau() {
		Random generator = new Random();
		int value = generator.nextInt((999999 - 100000) + 1) + 100000;
		return value + "";
	}

	@RequestMapping("reset")
	public String datLaiMK(HttpServletRequest rq, ModelMap model) {
		String email = rq.getParameter("email");
		String tendangnhap = rq.getParameter("tendangnhap");
		if (email.equals("")||tendangnhap.equals("")) {
			model.addAttribute("message0", "Vui lòng nhập đầy đủ thông tin");
			return "views/quenmatkhau";
		}
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVien WHERE MANV='"+ tendangnhap.trim() +"'";
		Query query = session.createQuery(hql);
		List<NhanVien> list = query.list();
		if (list.size() == 0) {
			model.addAttribute("message0", "Email hoặc tên đăng nhập không hợp lệ, vui lòng kiểm tra lại thông tin");
			return "views/quenmatkhau";
		} else {
			String manv = list.get(0).getMANV();
			String email1 = list.get(0).getEMAIL1();
			String email2 = list.get(0).getEMAIL2();
			if(email.equals(email1)||email.equals(email2)) {
				System.out.println("MANV: " + manv);
				String mk = taoMatKhau();
				String hql1 = "FROM TaiKhoan WHERE TENDANGNHAP = '" + manv + "'";
				Query query1 = session.createQuery(hql1);
				List<TaiKhoan> list1 = query1.list();
				TaiKhoan temp = list1.get(0);
				temp.setMATKHAU(mk);
				try {
					Session session2 = factory.openSession();
					Transaction t = session2.beginTransaction();
					try {
						temp.setMATKHAU(SupportController.encryptPassword(mk));
						session2.update(temp);
						t.commit();

					} catch (Exception e) {
						t.rollback();
						model.addAttribute("message1", e);
					} finally {
						session2.close();
					}
					mailer.send("tathuongthuong2516@gmail.com", email, "Đặt lại mật khẩu tài khoản Highlands",
							"\nMật khẩu mới của bạn là: " + mk);
					model.addAttribute("message1", "Mật khẩu mới đã được gửi đến email của bạn!");
				} catch (Exception e) {
					model.addAttribute("message0", "Gửi email lỗi!");
				}
			}
			else {
				model.addAttribute("message0", "Email không chính xác, vui lòng dùng email đã đăng ký");
				return "views/quenmatkhau";
			}
		}
		return "views/quenmatkhau";
	}
}
