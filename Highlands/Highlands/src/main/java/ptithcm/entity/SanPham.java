package ptithcm.entity;

import javax.persistence.*;


@Entity
@Table(name="SANPHAM")
public class SanPham {
	@Id
	@Column(name = "MASP")
	private String MASP;
	
	@Column(name="TEN")
	private String TEN;
	
	@Column (name="HINHANH")
	private String HINHANH;
	
	@ManyToOne
	@JoinColumn(name="MALOAI")
	private Loai LOAISP; 
	
	public SanPham() {
		
	}
	public String getMASP() {
		return MASP;
	}
	public void setMASP(String MASP) {
		this.MASP=MASP;
	}
	public String getTEN() {
		return TEN;
	}
	public void setTEN(String TEN) {
		this.TEN=TEN;
	}
	public String getHINHANH() {
		return HINHANH;
	}
	public void setHINHANH(String hINHANH) {
		this.HINHANH=hINHANH;
	}
	public Loai getLOAISP() {
		return LOAISP;
	}
	public void setLOAISP(Loai lOAISP) {
		this.LOAISP=lOAISP;
	}
//	public Collection <ChiTietSanPham> getCacChiTietSanPham(){
//		return cacChiTietSanPham;
//	}
//	public void setCacChiTietSanPham(Collection<ChiTietSanPham> cacChiTietSanPham) {
//		this.cacChiTietSanPham=cacChiTietSanPham;
//	}
}
