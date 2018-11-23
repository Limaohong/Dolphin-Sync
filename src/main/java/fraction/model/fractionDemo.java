package fraction.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import student.model.studentBean;

public class fractionDemo implements Serializable {
	private static final long serialVersionUID =1L;
	
	Integer F_Id;	
	studentBean F_SId;
	String F_Exam;
	BigDecimal F_Fraction;
	String F_Subject;	
	String F_RD;	
	Timestamp F_LM;
	
	public fractionDemo() {
		
	}
	
	

	
	public fractionDemo(studentBean f_SId, String f_Exam, BigDecimal f_Fraction, String f_Subject, String f_RD) {
		super();
		F_SId = f_SId;
		F_Exam = f_Exam;
		F_Fraction = f_Fraction;
		F_Subject = f_Subject;
		F_RD = f_RD;
	}




	public String getF_Exam() {
		return F_Exam;
	}




	public void setF_Exam(String f_Exam) {
		F_Exam = f_Exam;
	}




	public Integer getF_Id() {
		return F_Id;
	}

	public void setF_Id(Integer f_Id) {
		F_Id = f_Id;
	}

	public studentBean getF_SId() {
		return F_SId;
	}

	public void setF_SId(studentBean f_SId) {
		F_SId = f_SId;
	}

	public BigDecimal getF_Fraction() {
		return F_Fraction;
	}

	public void setF_Fraction(BigDecimal f_Fraction) {
		F_Fraction = f_Fraction;
	}

	public String getF_Subject() {
		return F_Subject;
	}

	public void setF_Subject(String f_Subject) {
		F_Subject = f_Subject;
	}

	public String getF_RD() {
		return F_RD;
	}

	public void setF_RD(String f_RD) {
		F_RD = f_RD;
	}

	public Timestamp getF_LM() {
		return F_LM;
	}

	public void setF_LM(Timestamp f_LM) {
		F_LM = f_LM;
	}

	@Override
	public String toString() {
		return "fractionBean [F_Id=" + F_Id + ", F_SId=" + F_SId + ", F_Fraction=" + F_Fraction + ", F_Subject="
				+ F_Subject + ", F_RD=" + F_RD + ", F_LM=" + F_LM + "]";
	}

	
	
}
