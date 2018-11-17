package teacherpresentation.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import company.model.companyBean;

@Entity
@Table(name="TeacherPresentation")
public class teacherpresentationBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Integer TP_Id;

	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="TP_CId")

	companyBean TP_CId;
	String TP_Name;
	Blob TP_Avater;
	Clob TP_TI;
	@CreationTimestamp
	Timestamp TP_RD;
	@UpdateTimestamp
	Timestamp TP_LM;
	public teacherpresentationBean() {
	}
	

	public teacherpresentationBean(companyBean tP_CId, String tP_Name, Blob tP_Avater, Clob tP_TI) {
		super();
		TP_CId = tP_CId;
		TP_Name = tP_Name;
		TP_Avater = tP_Avater;
		TP_TI = tP_TI;
	}

	
	public teacherpresentationBean(Integer tP_Id, companyBean tP_CId, String tP_Name, Blob tP_Avater, Clob tP_TI) {
		super();
		TP_Id = tP_Id;
		TP_CId = tP_CId;
		TP_Name = tP_Name;
		TP_Avater = tP_Avater;
		TP_TI = tP_TI;
	}


	public Integer getTP_Id() {
		return TP_Id;
	}
	public void setTP_Id(Integer tP_Id) {
		TP_Id = tP_Id;
	}
	

	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="TP_CId",referencedColumnName="C_Id")

	public companyBean getTP_CId() {
		return TP_CId;
	}


	public void setTP_CId(companyBean tP_CId) {
		TP_CId = tP_CId;
	}


	public String getTP_Name() {
		return TP_Name;
	}

	public void setTP_Name(String tP_Name) {
		TP_Name = tP_Name;
	}

	public Blob getTP_Avater() {
		return TP_Avater;
	}
	public void setTP_Avater(Blob tP_Avater) {
		TP_Avater = tP_Avater;
	}
	public Clob getTP_TI() {
		return TP_TI;
	}
	public void setTP_TI(Clob tP_TI) {
		TP_TI = tP_TI;
	}
	public Timestamp getTP_RD() {
		return TP_RD;
	}
	public void setTP_RD(Timestamp tP_RD) {
		TP_RD = tP_RD;
	}
	public Timestamp getTP_LM() {
		return TP_LM;
	}
	public void setTP_LM(Timestamp tP_LM) {
		TP_LM = tP_LM;
	}


	@Override
	public String toString() {
		return "teacherpresentationBean [TP_Id=" + TP_Id + ", TP_CId=" + TP_CId + ", TP_Name=" + TP_Name
				+ ", TP_Avater=" + TP_Avater + ", TP_TI=" + TP_TI + ", TP_RD=" + TP_RD + ", TP_LM=" + TP_LM + "]";
	}

	
	
	
}
