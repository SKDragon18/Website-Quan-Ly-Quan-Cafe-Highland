package ptithcm.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="LOAISP")
public class Loai {
	@Id
	@Column(name = "MALOAI")
	private String MALOAI;
	@Column(name = "TENLOAI")
	private String TENLOAI;
//	@OneToMany(mappedBy="LOAISP",fetch=FetchType.LAZY)
//	private Collection<SanPham> cacSanPham;
	
	public Loai() {
		
	}
	public String getMALOAI() {
		return MALOAI;
	}

	public void setMALOAI(String mALOAI) {
		MALOAI = mALOAI;
	}
	public String getTENLOAI() {
		return TENLOAI;
	}

	public void setTENLOAI(String tENLOAI) {
		TENLOAI = tENLOAI;
	}
//	public Collection<SanPham> getCacSanPham(){
//		return cacSanPham;
//	}
//	public void setCacSanPham(Collection<SanPham>cacSanPham) {
//		this.cacSanPham=cacSanPham;
//	}
}