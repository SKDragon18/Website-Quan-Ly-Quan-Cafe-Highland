package ptithcm.controller;

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import ptithcm.entity.NhanVien;
import ptithcm.entity.TaiKhoan;

@Controller
@Transactional
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	SessionFactory factory;

	public List<NhanVien> LayDanhSachNhanVien() {
		Session session = factory.openSession();
		String hql = "FROM NhanVien";
		Query query = session.createQuery(hql);
		List<NhanVien> list = query.list();
		return list;
	}

	@RequestMapping("list")
	public String dsnhanvien(ModelMap model) {
		System.out.println("No Mapping");
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("nv", new NhanVien());
		List<NhanVien> DS = this.LayDanhSachNhanVien();
		model.addAttribute("dsnhanvien", DS);
		return "views/quanly/nhanvien";
	}

	public NhanVien getNVByMaNV(String manv) {
		Session session = factory.openSession();
		String hql = "FROM NhanVien WHERE MANV ='" + manv + "'";
		Query query = session.createQuery(hql);
		NhanVien list = (NhanVien) query.list().get(0);
		return list;
	}

	public TaiKhoan get1TaiKhoan(String ma) {
		Session session = factory.openSession();
		String hql = "FROM TaiKhoan where TENDANGNHAP = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", ma);
		TaiKhoan list = (TaiKhoan) query.list().get(0);
		return list;
	}
	
	public static String normalizeVietnamese(String name) {
	    // Normalize the name to NFD form
	    String nfdNormalizedString = Normalizer.normalize(name, Normalizer.Form.NFD);
	    // Remove all combining diacritical marks
	    String normalizedString = nfdNormalizedString.replaceAll("\\p{M}", "");
	    // Return the normalized string
	 // Replace Đ and đ with D and d
	    normalizedString = normalizedString.replaceAll("Đ", "D");
	    normalizedString = normalizedString.replaceAll("đ", "d");
	    return normalizedString;
	}
	
	public boolean kiemTraMaNV(String mANV) {
		Pattern pattern = Pattern.compile("^NV\\d+$", Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(mANV);
	   	return matcher.matches();
	}
	
	public boolean kiemTraChu(String chu) {
		chu=normalizeVietnamese(chu);
		Pattern pattern = Pattern.compile("^[A-Z][a-z]+(\\s[A-Z][a-z]+)*$");
		 Matcher matcher = pattern.matcher(chu);
		 return matcher.matches();
	}
	public boolean kiemTraSoDienThoai(String so) {
		Pattern pattern = Pattern.compile("^[0-9]{10}$");
	    Matcher matcher = pattern.matcher(so);
	   	return matcher.matches();
	}
	public boolean kiemTraEmail(String email) {
		Pattern pattern = Pattern.compile("^(.+)@(\\S+)$");
	    Matcher matcher = pattern.matcher(email);
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

	@RequestMapping(value = "/list/{MANV}.htm", params = "linkDelete")
	public String delete(ModelMap model, @ModelAttribute("nv") NhanVien nv) {
		System.out.println("linkDelete - MANV: " + nv.getMANV());
		NhanVien nvtemp = getNVByMaNV(nv.getMANV());
		int check = this.delete(nvtemp);
		if (check == 1) {
			model.addAttribute("message1", "Xóa nhân viên thành công!");
		} else {
			TaiKhoan temp = new TaiKhoan();
			temp = get1TaiKhoan(nv.getMANV());
			temp.setTRANGTHAI(false);
			delete(temp);
			int check1 = delete(nvtemp);
			if (check1 == 1)
				model.addAttribute("message1", "Xóa nhân viên thành công!");
			else
				model.addAttribute("message0",
						"Xóa nhân viên thất bại do nhân viên đã được lập tài khoản, tài khoản hiện có thể liên quan đến nhiều tác vụ");
		}
		List<NhanVien> DS = this.LayDanhSachNhanVien();
		model.addAttribute("dsnhanvien", DS);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("nv", new NhanVien());
		return "views/quanly/nhanvien";
	}

	@RequestMapping(value = "/list/{MANV}.htm", params = "linkEdit")
	public String editAccount(ModelMap model, @ModelAttribute("nv") NhanVien nv, @PathVariable("MANV") String MANV) {
		List<NhanVien> DS = this.LayDanhSachNhanVien();
		model.addAttribute("dsnhanvien", DS);
		model.addAttribute("nv", this.getNVByMaNV(MANV));
		model.addAttribute("btnStatus", "btnEdit");
		return "views/quanly/nhanvien";
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
	public String edit_Account(ModelMap model, @ModelAttribute("nv") NhanVien nv, BindingResult errors) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(nv.getHO().isBlank()) {
			errors.rejectValue("HO", "nv","Mời nhập họ");
		}
		else if(!kiemTraChu(nv.getHO())) {
			errors.rejectValue("HO", "nv","Họ có chữ cái đầu viết hoa và chữ thứ 2 trở đi viết thường (VD: Trần)");
		}
		if(nv.getTEN().isBlank()) {
			errors.rejectValue("TEN", "nv","Mời nhập tên");
		}
		else if(!kiemTraChu(nv.getTEN())) {
			errors.rejectValue("TEN", "nv","Tên có chữ cái đầu viết hoa và chữ thứ 2 trở đi viết thường (VD: Gia Long)");
		}
		
		if(nv.getNGAYSINH()==null) {
			Date newDate = dateFormat.parse("2000-01-01");
			java.util.Date tempDate = new java.util.Date(newDate.getTime());
			nv.setNGAYSINH(tempDate);
			errors.rejectValue("NGAYSINH", "nv","Vui lòng nhập ngày sinh");
		}
		
		if(nv.getDIACHI().isBlank()) {
			errors.rejectValue("DIACHI", "nv","Mời nhập địa chỉ");
		}
		if(nv.getEMAIL1().isBlank()) {
			errors.rejectValue("EMAIL1", "nv","Mời nhập email 1");
		}
		else if(!kiemTraEmail(nv.getEMAIL1())) {
			errors.rejectValue("EMAIL1", "nv","Vui lòng nhập đúng dạng của email (VD: example@gmail.com)");
		}
		if(!nv.getEMAIL2().isBlank()) {
			if(!kiemTraEmail(nv.getEMAIL2())) {
				errors.rejectValue("EMAIL2", "nv","Vui lòng nhập đúng dạng của email (VD: example@gmail.com)");
			}
			else if(!nv.getEMAIL1().isBlank()&&nv.getEMAIL1().equals(nv.getEMAIL2())) {
				nv.setEMAIL2(null);
				errors.rejectValue("EMAIL2", "nv","Vui lòng nhập khác email 1");
			}
		}
		if(nv.getSDT1().isBlank()) {
			errors.rejectValue("SDT1", "nv","Mời nhập số điện thoại 1");
		}
		else if(!kiemTraSoDienThoai(nv.getSDT1())) {
			errors.rejectValue("SDT1", "nv","Vui lòng nhập đúng dạng số điện thoại gồm 10 chữ số");
		}
		if(!nv.getSDT2().isBlank()) {
			if(!kiemTraSoDienThoai(nv.getSDT2())) {
				errors.rejectValue("SDT2", "nv","Vui lòng nhập đúng dạng số điện thoại gồm 10 chữ số");
			}
			else if(!nv.getSDT1().isBlank() && nv.getSDT1().equals(nv.getSDT2())) {
				nv.setSDT2(null);
				errors.rejectValue("SDT2", "nv","Vui lòng nhập khác số điện thoại 2");
			}
		}
		if(!errors.hasErrors()) {
			String dateStr = dateFormat.format(nv.getNGAYSINH());
			Date date = dateFormat.parse(dateStr);
			java.util.Date utilDate = new java.util.Date(date.getTime());
			nv.setNGAYSINH(date);
			NhanVien temp = new NhanVien();
			temp.setDIACHI(nv.getDIACHI());
			temp.setEMAIL1(nv.getEMAIL1());
			if (!nv.getEMAIL2().isBlank())
				temp.setEMAIL2(nv.getEMAIL2());
			else
				temp.setEMAIL2(null);
			temp.setGT(nv.getGT());
			temp.setHO(nv.getHO());
			temp.setMANV(nv.getMANV());
			temp.setNGAYSINH(utilDate);
			temp.setSDT1(nv.getSDT1());
			if (!nv.getSDT2().isBlank())
				temp.setSDT2(nv.getSDT2());
			else
				temp.setSDT2(null);
			temp.setTEN(nv.getTEN());
			System.out.print("Thông tin nhân viên sửa: " + temp.getMANV() + " " + temp.getDIACHI() + " " + temp.getEMAIL1()
					+ " " + temp.getGT() + " " + temp.getHO() + " " + temp.getTEN() + " " + temp.getSDT1() + " "
					+ temp.getNGAYSINH());
	
			int check = this.update(temp);
			if (check != 0) {
				model.addAttribute("message1", "Sửa nhân viên thành công!");
				model.addAttribute("btnStatus", "btnAdd");
				model.addAttribute("nv", new NhanVien());
			} else {
				model.addAttribute("message0", "Sửa nhân viên thất bại!");
				model.addAttribute("btnStatus", "btnEdit");
			}
		}
		else {
			model.addAttribute("message0", "Sửa nhân viên thất bại!");
			model.addAttribute("btnStatus", "btnEdit");
		}
		List<NhanVien> DS = this.LayDanhSachNhanVien();
		model.addAttribute("dsnhanvien", DS);
		return "views/quanly/nhanvien";
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
	public String addAccount(ModelMap model, @ModelAttribute("nv") NhanVien nv, BindingResult errors) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if(nv.getMANV().isBlank()) {
			errors.rejectValue("MANV", "nv","Mời nhập mã");
		}
		else if(!kiemTraMaNV(nv.getMANV())) {
			errors.rejectValue("MANV", "nv","Vui lòng nhập đúng dạng chuẩn mã: NV + số");
		}
		else nv.setMANV(nv.getMANV().toUpperCase());
		if(nv.getHO().isBlank()) {
			errors.rejectValue("HO", "nv","Mời nhập họ");
		}
		else if(!kiemTraChu(nv.getHO())) {
			errors.rejectValue("HO", "nv","Họ có chữ cái đầu viết hoa và chữ thứ 2 trở đi viết thường (VD: Trần)");
		}
		if(nv.getTEN().isBlank()) {
			errors.rejectValue("TEN", "nv","Mời nhập tên");
		}
		else if(!kiemTraChu(nv.getTEN())) {
			errors.rejectValue("TEN", "nv","Tên có chữ cái đầu viết hoa và chữ thứ 2 trở đi viết thường (VD: Gia Long)");
		}
		
		if(nv.getGT()==null) {
			nv.setGT("Nam");
		}
		
		if(nv.getNGAYSINH()==null) {
			Date newDate = dateFormat.parse("2000-01-01");
			java.util.Date tempDate = new java.util.Date(newDate.getTime());
			nv.setNGAYSINH(tempDate);
			errors.rejectValue("NGAYSINH", "nv","Vui lòng nhập ngày sinh");
		}
		
		if(nv.getDIACHI().isBlank()) {
			errors.rejectValue("DIACHI", "nv","Mời nhập địa chỉ");
		}
		if(nv.getEMAIL1().isBlank()) {
			errors.rejectValue("EMAIL1", "nv","Mời nhập email 1");
		}
		else if(!kiemTraEmail(nv.getEMAIL1())) {
			errors.rejectValue("EMAIL1", "nv","Vui lòng nhập đúng dạng của email (VD: example@gmail.com)");
		}
		if(!nv.getEMAIL2().isBlank()) {
			if(!kiemTraEmail(nv.getEMAIL2())) {
				errors.rejectValue("EMAIL2", "nv","Vui lòng nhập đúng dạng của email (VD: example@gmail.com)");
			}
			else if(!nv.getEMAIL1().isBlank()&&nv.getEMAIL1().equals(nv.getEMAIL2())) {
				nv.setEMAIL2(null);
				errors.rejectValue("EMAIL2", "nv","Vui lòng nhập khác email 1");
			}
		}
		if(nv.getSDT1().isBlank()) {
			errors.rejectValue("SDT1", "nv","Mời nhập số điện thoại 1");
		}
		else if(!kiemTraSoDienThoai(nv.getSDT1())) {
			errors.rejectValue("SDT1", "nv","Vui lòng nhập đúng dạng số điện thoại gồm 10 chữ số");
		}
		if(!nv.getSDT2().isBlank()) {
			if(!kiemTraSoDienThoai(nv.getSDT2())) {
				errors.rejectValue("SDT2", "nv","Vui lòng nhập đúng dạng số điện thoại gồm 10 chữ số");
			}
			else if(!nv.getSDT1().isBlank() && nv.getSDT1().equals(nv.getSDT2())) {
				nv.setSDT2(null);
				errors.rejectValue("SDT2", "nv","Vui lòng nhập khác số điện thoại 2");
			}
		}
		if(!errors.hasErrors()) {
			
			String dateStr = dateFormat.format(nv.getNGAYSINH());
			Date date = dateFormat.parse(dateStr);
			java.util.Date utilDate = new java.util.Date(date.getTime());
			nv.setNGAYSINH(utilDate);
		NhanVien temp = new NhanVien();
		temp.setDIACHI(nv.getDIACHI());
		temp.setEMAIL1(nv.getEMAIL1());
		if (!nv.getEMAIL2().isBlank())
			temp.setEMAIL2(nv.getEMAIL2());
		else
			temp.setEMAIL2(null);
		temp.setGT(nv.getGT());
		temp.setHO(nv.getHO());
		temp.setMANV(nv.getMANV());
		temp.setNGAYSINH(utilDate);
		temp.setSDT1(nv.getSDT1());
		if (!nv.getSDT2().isBlank())
			temp.setSDT2(nv.getSDT2());
		else
			temp.setSDT2(null);
		temp.setTEN(nv.getTEN());
		System.out.print("Thông tin nhân viên thêm: " + temp.getMANV() + " " + temp.getDIACHI() + " " + temp.getEMAIL1()
				+ " " + temp.getGT() + " " + temp.getHO() + " " + temp.getTEN() + " " + temp.getSDT1() + " "
				+ temp.getNGAYSINH());
		int check = this.insert(temp);
		if (check != 0) {
			model.addAttribute("message1", "Thêm nhân viên thành công!");
			model.addAttribute("nv", new NhanVien());
		} else {
			model.addAttribute("message0", "Thêm nhân viên thất bại!");
		}
		}
		model.addAttribute("btnStatus", "btnAdd");
		List<NhanVien> DS = this.LayDanhSachNhanVien();
		model.addAttribute("dsnhanvien", DS);
		return "views/quanly/nhanvien";
	}
}
