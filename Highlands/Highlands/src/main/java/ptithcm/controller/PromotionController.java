package ptithcm.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.entity.ChiTietKhuyenMai;
import ptithcm.entity.ChiTietSanPham;
import ptithcm.entity.KhuyenMai;
import ptithcm.entity.SanPham;
import ptithcm.entity.Size;

@Controller
@Transactional
@RequestMapping("/promotion/")
public class PromotionController {
	@Autowired
	SessionFactory factory;
	
	@ModelAttribute("cacchitietsanpham")
	public List<ChiTietSanPham> layDanhSachChiTietSanPham() {
		Session session = factory.getCurrentSession();
		String hql = "from ChiTietSanPham";
		Query query = session.createQuery(hql);
		List<ChiTietSanPham> list = query.list();
		return list;
	}
	public ChiTietSanPham getChiTietSanPham(String ma) {
		Session session = factory.getCurrentSession();
		String w[]=ma.split("\\|");
		String hql = "from ChiTietSanPham where pk.SANPHAM.MASP=:ma1 and pk.SIZE.MASIZE=:ma2";
		Query query = session.createQuery(hql);
		query.setParameter("ma1", w[0]);
		query.setParameter("ma2", w[1]);
		ChiTietSanPham list = (ChiTietSanPham) query.list().get(0);
		return list;
	}
	public List <ChiTietKhuyenMai> layDanhSachChiTietKhuyenMai(String khuyenmai) {
		Session session = factory.getCurrentSession();
		String hql = "from ChiTietKhuyenMai where pk.KHUYENMAI.MAKM=:khuyenmai ";
		Query query = session.createQuery(hql);
		query.setParameter("khuyenmai",khuyenmai);
		List<ChiTietKhuyenMai> list = query.list();
		return list;	
	}
	public ChiTietKhuyenMai layChiTietKhuyenMai(String masp,String masize, String makm) {
		Session session = factory.getCurrentSession();
		String hql = "from ChiTietKhuyenMai where pk.SANPHAM.MASP=:masp and pk.SIZE.MASIZE=:masize and pk.KHUYENMAI.MAKM=:makm ";
		Query query = session.createQuery(hql);
		query.setParameter("masp",masp);
		query.setParameter("masize",masize);
		query.setParameter("makm",makm);
		ChiTietKhuyenMai list = ( ChiTietKhuyenMai) query.list().get(0);
		return list;
	}
	public List<KhuyenMai> layDanhSachKhuyenMai() {
		Session session = factory.getCurrentSession();
		String hql = "from KhuyenMai";
		Query query = session.createQuery(hql);
		List<KhuyenMai> list = query.list();
		return list;
	}

	public KhuyenMai getKhuyenMai(String ma) {
		Session session = factory.getCurrentSession();
		String hql = "from KhuyenMai where MAKM = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", ma);
		KhuyenMai list = (KhuyenMai) query.list().get(0);
		return list;
	}
	
	@RequestMapping("list")
	public String dskhuyenmai(ModelMap model) {
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("khuyenmai",new KhuyenMai());
		List<KhuyenMai> DS = layDanhSachKhuyenMai();
		model.addAttribute("dskhuyenmai", DS);
		return "views/quanly/khuyenmai";
	}
	
	public boolean kiemTraMaKM(String mAKM) {
		 Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		 Matcher matcher = pattern.matcher(mAKM);
		 return matcher.find();
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
	public String addKM(ModelMap model,  @ModelAttribute("khuyenmai") KhuyenMai khuyenmai, BindingResult errors) {
		int key=0;
		
		System.out.println("btnAdd");
		
		if(khuyenmai.getMAKM().isBlank()) {
			errors.rejectValue("MAKM", "khuyenmai","Vui lòng nhập mã");
		}
		else if(kiemTraMaKM(khuyenmai.getMAKM())) {
			errors.rejectValue("MAKM", "khuyenmai","Vui lòng nhập mã bằng chữ và số");
		}
		else khuyenmai.setMAKM(khuyenmai.getMAKM().toUpperCase());
		if(khuyenmai.getTGBD()==null) {
			errors.rejectValue("TGBD", "khuyenmai","Vui lòng nhập thời gian bắt đầu");
			key=key+1;
		}
		if(khuyenmai.getTGKT()==null) {
			errors.rejectValue("TGKT", "khuyenmai","Vui lòng nhập thời gian kết thúc");
			key=key+1;
		}
		if(key==0 && khuyenmai.getTGBD().compareTo(khuyenmai.getTGKT())>0) {
			khuyenmai.setTGBD("");
			khuyenmai.setTGKT("");
			errors.rejectValue("TGBD", "khuyenmai","Thời gian bắt đầu nhỏ hơn thời gian kết thúc");
		}
		if(key==1) {
			khuyenmai.setTGBD("");
			khuyenmai.setTGKT("");
			model.addAttribute("message0", "Vui lòng nhập đầy đủ khoảng thời gian");
		}
		if(!errors.hasErrors()) {
			int check = this.insert(khuyenmai);
			if (check != 0) {
				model.addAttribute("message1", "Thêm thông tin khuyến mãi thành công!");
				model.addAttribute("khuyenmai", new KhuyenMai());
			} else {
				model.addAttribute("message0", "Thêm thông tin khuyến mãi thất bại! Mã khuyến mãi bị trùng");
			}
		}
		model.addAttribute("btnStatus", "btnAdd");
		List<KhuyenMai> DS = this.layDanhSachKhuyenMai();
		model.addAttribute("dskhuyenmai", DS);
		return "views/quanly/khuyenmai";
	}
	@RequestMapping(value = "/list/{MAKM}.htm", params = "linkDelete")
	public String deleteKM(ModelMap model,@PathVariable("MAKM")String MAKM,
			KhuyenMai khuyenmai) {
		System.out.println("linkDelete");
		KhuyenMai khuyenmaitam=getKhuyenMai(MAKM);
		int check = this.delete(khuyenmaitam);
		if (check == 1) {
			model.addAttribute("message1", "Xóa thông tin khuyến mãi thành công");
		} else {
			model.addAttribute("message0", "Xóa thông tin khuyến mãi thất bại");
		}
		List<KhuyenMai> DS = this.layDanhSachKhuyenMai();
		model.addAttribute("dskhuyenmai", DS);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("khuyenmai",khuyenmai);
		return "views/quanly/khuyenmai";
	}

	@RequestMapping(value = "/list/{MAKM}.htm", params = "linkEdit")
	public String editKM(ModelMap model,
			@PathVariable("MAKM") String MAKM) {
		System.out.println("linkEdit");
		List<ChiTietKhuyenMai> DS = layDanhSachChiTietKhuyenMai(MAKM);
		model.addAttribute("dschitiet", DS);
		KhuyenMai khuyenmai=getKhuyenMai(MAKM);
		ChiTietKhuyenMai chitiet=new ChiTietKhuyenMai();
		chitiet.setKHUYENMAI(khuyenmai);
		model.addAttribute("chitiet",chitiet);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("khuyenmai", khuyenmai );
		return "views/quanly/khuyenmaichitiet";
	}
	@RequestMapping(value = "/list/{MAKM}/list.htm")
	public String listCT_KM(ModelMap model,
			@PathVariable("MAKM") String MAKM) {
		List<ChiTietKhuyenMai> DS = this.layDanhSachChiTietKhuyenMai(MAKM);
		model.addAttribute("dschitiet", DS);
		KhuyenMai khuyenmai=getKhuyenMai(MAKM);
		ChiTietKhuyenMai chitiet=new ChiTietKhuyenMai();
		chitiet.setKHUYENMAI(khuyenmai);
		model.addAttribute("chitiet",chitiet);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("khuyenmai", khuyenmai );
		return "views/quanly/khuyenmaichitiet";
	}
	@RequestMapping(value = "/list/{MAKM}/list.htm", params="btnAdd")
	public String editKhuyenMai(ModelMap model, @PathVariable("MAKM") String MAKM, 
			String ct_sanpham,@ModelAttribute("chitiet")ChiTietKhuyenMai chitiet) {
		System.out.println("btnAdd");
		KhuyenMai khuyenmai=getKhuyenMai(MAKM);
		ChiTietSanPham temp=getChiTietSanPham(ct_sanpham);
		chitiet.setSANPHAM(temp.getSANPHAM());
		chitiet.setSIZE(temp.getSIZE());
		int check = this.insert(chitiet);
		if (check != 0) {
			model.addAttribute("message1", "Thêm chi tiết khuyến mãi thành công!");
			ChiTietKhuyenMai chitiettam=new ChiTietKhuyenMai();
			chitiettam.setKHUYENMAI(khuyenmai);
			model.addAttribute("chitiet", chitiettam);
		} else {
			model.addAttribute("message0", "Thêm chi tiết khuyến mãi thất bại!");
			model.addAttribute("ct_sanpham",ct_sanpham);
		}
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("khuyenmai",khuyenmai);
		List<ChiTietKhuyenMai> DS = this.layDanhSachChiTietKhuyenMai(MAKM);
		model.addAttribute("dschitiet", DS);
		return "views/quanly/khuyenmaichitiet";
	}
	@RequestMapping(value = "/list/{MAKM}/{MASP}/{MASIZE}.htm", params = "linkDelete")
	public String deleteKhuyenMai(ModelMap model, 
			@PathVariable("MAKM") String MAKM, @PathVariable("MASP") String MASP, @PathVariable("MASIZE") String MASIZE) {
		System.out.println("linkDelete");
		ChiTietKhuyenMai tam=layChiTietKhuyenMai(MASP,MASIZE,MAKM);
		int check = this.delete(tam);
		if (check == 1) {
			model.addAttribute("message1", "Xóa chi tiết khuyến mãi thành công");
		} else {
			model.addAttribute("message0", "Xóa chi tiết khuyến mãi thất bại");
		}
		List<ChiTietKhuyenMai> DS = this.layDanhSachChiTietKhuyenMai(MAKM);
		KhuyenMai khuyenmai=getKhuyenMai(MAKM);
		ChiTietKhuyenMai chitiet=new ChiTietKhuyenMai();
		chitiet.setKHUYENMAI(khuyenmai);
		model.addAttribute("dschitiet", DS);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("khuyenmai",khuyenmai);
		model.addAttribute("chitiet",chitiet);
		return "views/quanly/khuyenmaichitiet";
	}
}
