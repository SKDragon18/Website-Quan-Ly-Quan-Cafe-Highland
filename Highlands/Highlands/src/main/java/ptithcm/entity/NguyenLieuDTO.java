package ptithcm.entity;

import java.math.BigDecimal;
public class NguyenLieuDTO {
	private String MANL;
	private BigDecimal SOLUONG;
	private BigDecimal DONGIA;
	private String TEN;
	private String DONVI;
	private BigDecimal SLTON;
	public String getMANL() {
		return MANL;
	}
	public void setMANL(String mANL) {
		MANL = mANL;
	}
	public BigDecimal getSOLUONG() {
		return SOLUONG;
	}
	public void setSOLUONG(BigDecimal sOLUONG) {
		SOLUONG = sOLUONG;
	}
	public BigDecimal getDONGIA() {
		return DONGIA;
	}
	public void setDONGIA(BigDecimal dONGIA) {
		DONGIA = dONGIA;
	}
	public String getTEN() {
		return TEN;
	}
	public void setTEN(String tEN) {
		TEN = tEN;
	}
	public String getDONVI() {
		return DONVI;
	}
	public void setDONVI(String dONVI) {
		DONVI = dONVI;
	}
	public BigDecimal getSLTON() {
		return SLTON;
	}
	public void setSLTON(BigDecimal sLTON) {
		SLTON = sLTON;
	}
}
