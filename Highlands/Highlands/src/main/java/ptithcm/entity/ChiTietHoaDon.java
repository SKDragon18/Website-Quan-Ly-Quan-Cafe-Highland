package ptithcm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CT_HOADON")
public class ChiTietHoaDon {
	@EmbeddedId
	private PK pk;
	
	@Column(name = "SOLUONG")
	private int SOLUONG;
	
	public PK getPk() {
		return pk;
	}

	public void setPk(PK pk) {
		this.pk = pk;
	}

	public int getSOLUONG() {
		return SOLUONG;
	}

	public void setSOLUONG(int sOLUONG) {
		SOLUONG = sOLUONG;
	}

	@Embeddable
	public static class PK implements Serializable {
		private static final long serialVersionUID = 1L;
		@ManyToOne
		@JoinColumn(name = "ID")
		private HoaDon HOADON;
		
		@ManyToOne
		@JoinColumn(name = "MASP")
		private SanPham SANPHAM;
		
		@ManyToOne
		@JoinColumn(name = "MASIZE")
		private Size SIZE;

		public HoaDon getHOADON() {
			return HOADON;
		}

		public void setHOADON(HoaDon hOADON) {
			HOADON = hOADON;
		}

		public SanPham getSANPHAM() {
			return SANPHAM;
		}

		public void setSANPHAM(SanPham sANPHAM) {
			SANPHAM = sANPHAM;
		}

		public Size getSIZE() {
			return SIZE;
		}

		public void setSIZE(Size sIZE) {
			SIZE = sIZE;
		}	
	}
}
