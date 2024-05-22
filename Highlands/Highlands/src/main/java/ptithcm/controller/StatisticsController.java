package ptithcm.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.entity.ChiTietHoaDon;
import ptithcm.entity.HoaDon;
import ptithcm.entity.SanPham;

@Controller
@Transactional
public class StatisticsController {
	@Autowired
	SessionFactory factory;
	
	public List<HoaDon> getList_HD() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM HoaDon WHERE PHANLOAI <> 1");
		@SuppressWarnings("unchecked")
		List<HoaDon> list = query.list();
		return list;
	}
	
	public List<ChiTietHoaDon> getList_CTHD() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM ChiTietHoaDon");
		@SuppressWarnings("unchecked")
		List<ChiTietHoaDon> list = query.list();
		return list;
	}
	
	public List<SanPham> getList_SP() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM SanPham");
		@SuppressWarnings("unchecked")
		List<SanPham> list = query.list();
		return list;
	}
	
	@RequestMapping("/statistics")
	public String name(ModelMap model) {
		List<HoaDon> myList = getList_HD();
		List<ChiTietHoaDon> myList1 = getList_CTHD();
		List<SanPham> myList2 = getList_SP();
		
		model.addAttribute("listHD", myList);
		model.addAttribute("listCTHD", myList1);
		model.addAttribute("listSP", myList2);
		
;		return "views/thongke/view3";
	}
	
	
}
