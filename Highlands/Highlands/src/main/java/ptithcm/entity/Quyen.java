package ptithcm.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "QUYEN")
public class Quyen {
	@Id
	@Column(name = "MAQUYEN")
	private String MaQuyen;
	
	@OneToMany(mappedBy="quyen", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	Collection<TaiKhoan> taikhoan;

	@Column(name = "TENQUYEN")
	private String TENQUYEN;

	public String getMAQUYEN() {
		return MaQuyen;
	}

	public void setMAQUYEN(String mAQUYEN) {
		MaQuyen = mAQUYEN;
	}

	public String getTENQUYEN() {
		return TENQUYEN;
	}

	public void setTENQUYEN(String tENQUYEN) {
		TENQUYEN = tENQUYEN;
	}

}
