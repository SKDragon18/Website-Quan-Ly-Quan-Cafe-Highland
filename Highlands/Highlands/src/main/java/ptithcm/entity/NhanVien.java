package ptithcm.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="NHANVIEN")
public class NhanVien {
	@Id
	@Column(name = "MANV", columnDefinition = "nvarchar(10)")
	private String MANV;
	
	@OneToOne(mappedBy = "nhanvien", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    private TaiKhoan taikhoan;

//	@OneToMany(mappedBy="nhanvien", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//	Collection<PhieuNhapHang> phieunhaphang;
	
	@Column(name = "HO", columnDefinition = "nvarchar(50)")
	private String HO;
	
	@Column(name = "TEN", columnDefinition = "nvarchar(30)")
	private String TEN;
	
	@Column(name = "GT", columnDefinition = "nvarchar(4)")
	private String GT;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "NGAYSINH")
	private Date NGAYSINH;
	
	@Column(name = "DIACHI", columnDefinition = "nvarchar(100)")
	private String DIACHI;

	@Column(name = "EMAIL1", columnDefinition = "nvarchar(40)")
	private String EMAIL1;

	@Column(name = "EMAIL2", columnDefinition = "nvarchar(40)")
	private String EMAIL2;

	@Column(name = "SDT1", columnDefinition = "nchar(10)")
	private String SDT1;
	
	@Column(name = "SDT2", columnDefinition = "nchar(10)")
	private String SDT2;

	public String getMANV() {
		return MANV;
	}

	public void setMANV(String mANV) {
		MANV = mANV;
	}

	public TaiKhoan getTaikhoan() {
		return taikhoan;
	}

	public void setTaikhoan(TaiKhoan taikhoan) {
		this.taikhoan = taikhoan;
	}

	public String getHO() {
		return HO;
	}

	public void setHO(String hO) {
		HO = hO;
	}

	public String getTEN() {
		return TEN;
	}

	public void setTEN(String tEN) {
		TEN = tEN;
	}

	public String getGT() {
		return GT;
	}

	public void setGT(String gT) {
		GT = gT;
	}

	public Date getNGAYSINH() {
		return NGAYSINH;
	}

	public void setNGAYSINH(Date nGAYSINH) {
		NGAYSINH = nGAYSINH;
	}

	public String getDIACHI() {
		return DIACHI;
	}

	public void setDIACHI(String dIACHI) {
		DIACHI = dIACHI;
	}

	public String getEMAIL1() {
		return EMAIL1;
	}

	public void setEMAIL1(String eMAIL1) {
		EMAIL1 = eMAIL1;
	}

	public String getEMAIL2() {
		return EMAIL2;
	}

	public void setEMAIL2(String eMAIL2) {
		EMAIL2 = eMAIL2;
	}

	public String getSDT1() {
		return SDT1;
	}

	public void setSDT1(String sDT1) {
		SDT1 = sDT1;
	}

	public String getSDT2() {
		return SDT2;
	}

	public void setSDT2(String sDT2) {
		SDT2 = sDT2;
	}

}
