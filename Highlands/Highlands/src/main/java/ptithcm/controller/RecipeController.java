package ptithcm.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.entity.CongThuc;
@Controller
@Transactional
@RequestMapping("/product/recipe")
public class RecipeController {
	@Autowired
	SessionFactory factory;

	public List<CongThuc> layDanhSachCongThuc() {
		Session session = factory.getCurrentSession();
		String hql = "FROM CongThuc";
		Query query = session.createQuery(hql);
		List<CongThuc> list = query.list();
		return list;
	}

	@RequestMapping("list")
	public String dscongthuc(ModelMap model) {
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("congthuc", new CongThuc());
		List<CongThuc> DS = layDanhSachCongThuc();
		model.addAttribute("dscongthuc", DS);
		return "views/quanly/sanpham/congthuc";
	}
	public CongThuc getCongThuc(String ma) {
		Session session = factory.getCurrentSession();
		String hql = "from CongThuc where MACT = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", ma);
		CongThuc list = (CongThuc) query.list().get(0);
		return list;
	}

	public boolean kiemTraMaCT(String mACT) {
		Pattern pattern = Pattern.compile("^CT\\d+$", Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(mACT);
	   	return matcher.matches();
	}

	@RequestMapping(value = "/list/{MACT}.htm", params = "linkEdit")
	public String editCongThuc(ModelMap model,
			@PathVariable("MACT") String MACT) {
		System.out.println("linkEdit");
		List<CongThuc> DS = this.layDanhSachCongThuc();
		model.addAttribute("dscongthuc", DS);
		model.addAttribute("btnStatus", "btnEdit");
		model.addAttribute("congthuc", this.getCongThuc(MACT));
		return "views/quanly/sanpham/congthuc";
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
	public String edit_CongThuc(ModelMap model,@ModelAttribute("congthuc")  CongThuc congthuc, BindingResult errors) {
		System.out.println("btnEdit");
		if(congthuc.getMACT().isBlank()) {
			errors.rejectValue("MACT", "congthuc", "Vui lòng nhập mã");
		}
		if(congthuc.getCONGTHUC().isBlank()) {
			errors.rejectValue("CONGTHUC", "congthuc", "Vui lòng nhập công thức");
		}
		if(!errors.hasErrors()) {
		int check = this.update(congthuc);
			if (check != 0) {
				model.addAttribute("message1", "Sửa công thức thành công!");
				model.addAttribute("btnStatus", "btnAdd");
				model.addAttribute("congthuc", new CongThuc());
			} else {
				model.addAttribute("message0", "Sửa công thức thất bại! Mã công thức không tồn tại!");
				model.addAttribute("btnStatus", "btnEdit");
			}
		}else
		{
			model.addAttribute("btnStatus", "btnEdit");
		}
		List<CongThuc> DS = this.layDanhSachCongThuc();
		model.addAttribute("dscongthuc", DS);
		
		return "views/quanly/sanpham/congthuc";
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
	public String addCongThuc(ModelMap model,@ModelAttribute("congthuc") CongThuc congthuc, BindingResult errors) {
		if(congthuc.getMACT().isBlank()) {
			errors.rejectValue("MACT", "congthuc", "Vui lòng nhập mã");
		}
		else if(!kiemTraMaCT(congthuc.getMACT())) {
			errors.rejectValue("MACT", "congthuc", "Vui lòng nhập đúng dạng chuẩn của mã: CT + số");
		}
		else congthuc.setMACT(congthuc.getMACT().toUpperCase());
		if(congthuc.getCONGTHUC().isBlank()) {
			errors.rejectValue("CONGTHUC", "congthuc", "Vui lòng nhập công thức");
		}
		System.out.println("btnAdd");
		if(!errors.hasErrors()) {
			int check = this.insert(congthuc);
			
			if (check != 0) {
				model.addAttribute("message1", "Thêm công thức thành công!");
				model.addAttribute("congthuc", new CongThuc());
			} else {
				model.addAttribute("message0", "Thêm công thức thất bại! Mã công thức bị trùng!");
			}
		}
		model.addAttribute("btnStatus", "btnAdd");
		List<CongThuc> DS = this.layDanhSachCongThuc();
		model.addAttribute("dscongthuc", DS);
		
		return "views/quanly/sanpham/congthuc";
	}
}