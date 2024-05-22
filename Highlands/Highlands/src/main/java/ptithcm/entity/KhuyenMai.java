package ptithcm.entity;


import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "KHUYENMAI")
public class KhuyenMai {
	@Id
	@Column(name = "MAKM")
	private String MAKM;
	
	@Column(name="TGBD")
	@DateTimeFormat (pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp TGBD;
	
	
	
	@Column(name="TGKT")
	@DateTimeFormat (pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp TGKT;
	public KhuyenMai() {
		
	}
	public String getMAKM() {
		return MAKM;
	}

	public void setMAKM(String mAKM) {
		MAKM = mAKM;
	}

	public Timestamp getTGBD() {
		return TGBD;
	}

	public void setTGBD(String tGBD) {
		if(tGBD.isBlank()) {
			TGBD=null;
			return;
		}
		// Chuyển đổi chuỗi thành LocalDate
		LocalDate localDate = LocalDate.parse(tGBD);

		// Chuyển đổi LocalDate thành LocalDateTime với giờ là 07:00:00
		LocalDateTime localDateTime = localDate.atStartOfDay();

		// Chuyển đổi LocalDateTime thành Instant với múi giờ UTC
		Instant instant = localDateTime.toInstant(ZoneOffset.UTC);

		// Chuyển đổi Instant thành java.sql.Timestamp
		TGBD = new java.sql.Timestamp(instant.toEpochMilli());
	}

	public Timestamp getTGKT() {
		return TGKT;
	}


	public void setTGKT(String tGKT) {
		// Chuyển đổi chuỗi thành LocalDate
		if(tGKT.isBlank()) {
			TGKT=null;
			return;
		}
				LocalDate localDate = LocalDate.parse(tGKT);

				// Chuyển đổi LocalDate thành LocalDateTime với giờ là 07:00:00
				LocalDateTime localDateTime = localDate.atStartOfDay();

				// Chuyển đổi LocalDateTime thành Instant với múi giờ UTC
				Instant instant = localDateTime.toInstant(ZoneOffset.UTC);

				// Chuyển đổi Instant thành java.sql.Timestamp
				TGKT = new java.sql.Timestamp(instant.toEpochMilli());
	}
}
