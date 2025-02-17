package ptithcm.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="TAIKHOAN")
public class TaiKhoan {
	@Id
	@Column(name = "TENDANGNHAP", columnDefinition = "nvarchar(10)")
	private String TENDANGNHAP;
	
	@Column(name = "MATKHAU")
	private String MATKHAU;

	@Column(name = "TRANGTHAI")
	public boolean TRANGTHAI;
	
	@OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "TENDANGNHAP", referencedColumnName = "MANV")
    private NhanVien nhanvien;
	
	@ManyToOne
	@JoinColumn(name="MAQUYEN")
	Quyen quyen;
	
	public String getTENDANGNHAP() {
		return TENDANGNHAP;
	}

	public void setTENDANGNHAP(String tENDANGNHAP) {
		TENDANGNHAP = tENDANGNHAP;
	}

	public NhanVien getNhanvien() {
		return nhanvien;
	}

	public void setNhanvien(NhanVien nhanvien) {
		this.nhanvien = nhanvien;
	}

	public String getMATKHAU() {
		return MATKHAU;
	}

	public void setMATKHAU(String mATKHAU) {
		MATKHAU = mATKHAU;
	}

	public boolean isTRANGTHAI() {
		return TRANGTHAI;
	}

	public void setTRANGTHAI(boolean tRANGTHAI) {
		TRANGTHAI = tRANGTHAI;
	}
	public Quyen getQuyen() {
		return quyen;
	}

	public void setQuyen(Quyen quyen) {
		this.quyen = quyen;
	}

}
