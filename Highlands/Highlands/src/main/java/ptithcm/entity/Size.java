package ptithcm.entity;

import javax.persistence.*;
@Entity
@Table(name="SIZE")
public class Size {
	@Id
	@Column(name="MASIZE")
	private String MASIZE;
	@Column(name="TENSIZE")
	private String TENSIZE;
	
	public Size() {
		
	}
	public String getMASIZE() {
		return MASIZE;
	}
	public void setMASIZE(String mASIZE) {
		this.MASIZE=mASIZE;
	}
	public String getTENSIZE() {
		return TENSIZE;
	}
	public void setTENSIZE(String tENSIZE) {
		this.TENSIZE=tENSIZE;
	}
}
