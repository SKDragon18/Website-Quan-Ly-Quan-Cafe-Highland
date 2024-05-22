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

import ptithcm.entity.Loai;
import ptithcm.entity.NguyenLieu;

@Controller
@Transactional
@RequestMapping("/product/type")
public class TypeController {
	@Autowired
	SessionFactory factory;

	public List<Loai> layDanhSachLoai() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Loai";
		Query query = session.createQuery(hql);
		List<Loai> list = query.list();
		return list;
	}

	@RequestMapping("list")
	public String dsloai(ModelMap model) {
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("loai",new Loai());
		List<Loai> DS = layDanhSachLoai();
		model.addAttribute("dsloai", DS);
		return "views/quanly/sanpham/loaisanpham";
	}
	public Loai getLoai(String ma) {
		Session session = factory.getCurrentSession();
		String hql = "from Loai where MALOAI = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", ma);
		Loai list = (Loai) query.list().get(0);
		return list;
	}

	public boolean kiemTraChu(String chuoi) {
		Pattern pattern = Pattern.compile("^[^<>'\\\"/;`%@$#!&*().,:?~0-9]*$");
	    Matcher matcher = pattern.matcher(chuoi);
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

	@RequestMapping(value = "/list/{MALOAI}.htm", params = "linkDelete")
	public String deleteLoai(ModelMap model,@PathVariable("MALOAI")String MALOAI,Loai loai) {
		System.out.println("linkDelete");
		Loai loaitam=getLoai(MALOAI);
		int check = this.delete(loaitam);
		if (check == 1) {
			model.addAttribute("message1", "Xóa loại thành công");
		} else {
			model.addAttribute("message0", "Xóa loại thất bại");
		}
		List<Loai> DS = this.layDanhSachLoai();
		model.addAttribute("dsloai", DS);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("loai",loai);
		return "views/quanly/sanpham/loaisanpham";
	}

	@RequestMapping(value = "/list/{MALOAI}.htm", params = "linkEdit")
	public String editLoai(ModelMap model, @PathVariable("MALOAI") String MALOAI) {
		System.out.println("linkEdit");
		List<Loai> DS = this.layDanhSachLoai();
		model.addAttribute("dsloai", DS);
		model.addAttribute("btnStatus", "btnEdit");
		model.addAttribute("loai", this.getLoai(MALOAI));
		return "views/quanly/sanpham/loaisanpham";
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
	public String edit_Loai(ModelMap model,   @ModelAttribute("loai") Loai loai, BindingResult errors) {
		System.out.println("btnEdit");
		if(loai.getMALOAI().isBlank()) {
			errors.rejectValue("MALOAI","loai","Vui lòng nhập mã");
		}
		if(loai.getTENLOAI().isBlank()) {
			errors.rejectValue("TENLOAI","loai","Vui lòng nhập tên");
		}
		else if(!kiemTraChu(loai.getTENLOAI())) {
			errors.rejectValue("TENLOAI","loai","Vui lòng chỉ nhập chữ");
		}
		if(!errors.hasErrors()) {
		int check = this.update(loai);
			if (check != 0) {
				model.addAttribute("message1", "Sửa loại thành công!");
				model.addAttribute("btnStatus", "btnAdd");
				model.addAttribute("loai", new Loai());
			} else {
				model.addAttribute("message0", "Sửa loại thất bại! Không tồn tại mã hoặc trùng tên");
				model.addAttribute("btnStatus", "btnEdit");
			}
		}
		else {
			model.addAttribute("btnStatus", "btnEdit");
		}
		List<Loai> DS = this.layDanhSachLoai();
		model.addAttribute("dsloai", DS);
		
		return "views/quanly/sanpham/loaisanpham";
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
	public String addLoai(ModelMap model,  @ModelAttribute("loai") Loai loai, BindingResult errors) {
		System.out.println("btnAdd");
		if(loai.getMALOAI().isBlank()) {
			errors.rejectValue("MALOAI","loai","Vui lòng nhập mã");
		}
		else if(!kiemTraChu(loai.getMALOAI())) {
			errors.rejectValue("MALOAI","loai","Vui lòng chỉ nhập chữ");
		}
		else loai.setMALOAI(loai.getMALOAI().toUpperCase());
		if(loai.getTENLOAI().isBlank()) {
			errors.rejectValue("TENLOAI","loai","Vui lòng nhập tên");
		}
		else if(!kiemTraChu(loai.getTENLOAI())) {
			errors.rejectValue("TENLOAI","loai","Vui lòng chỉ nhập chữ");
		}
		if(!errors.hasErrors()) {
			int check = this.insert(loai);
			if (check != 0) {
				model.addAttribute("message1", "Thêm loại thành công!");
				model.addAttribute("loai", new Loai());
			} else {
				model.addAttribute("message0", "Thêm loại thất bại! Trùng mã hoặc tên");
			}
		}
		model.addAttribute("btnStatus", "btnAdd");
		List<Loai> DS = this.layDanhSachLoai();
		model.addAttribute("dsloai", DS);
		
		return "views/quanly/sanpham/loaisanpham";
	}

}