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
@Entity
@Table(name="Fraction")
public class fractionBean implements Serializable {
	private static final long serialVersionUID =1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer F_Id;
	@ManyToOne(cascade= {CascadeType.PERSIST})
	@JoinColumn(name="F_SId", nullable = false)
	studentBean F_SId;
	@Column(precision = 5, scale = 2)
	BigDecimal F_Fraction;
	String F_Exam;
	Integer F_Subject;
	@CreationTimestamp
	Timestamp F_RD;
	@UpdateTimestamp
	Timestamp F_LM;
	
	public fractionBean() {
		
	}

	

	public fractionBean(studentBean f_SId, BigDecimal f_Fraction, String f_Exam, Integer f_Subject) {
		super();
		F_SId = f_SId;
		F_Fraction = f_Fraction;
		F_Exam = f_Exam;
		F_Subject = f_Subject;
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

	public Integer getF_Subject() {
		return F_Subject;
	}

	public void setF_Subject(Integer f_Subject) {
		F_Subject = f_Subject;
	}

	public Timestamp getF_RD() {
		return F_RD;
	}

	public void setF_RD(Timestamp f_RD) {
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
		return "fractionBean [F_Id=" + F_Id + ", F_SId=" + F_SId + ", F_Fraction=" + F_Fraction + ", F_Exam=" + F_Exam
				+ ", F_Subject=" + F_Subject + ", F_RD=" + F_RD + ", F_LM=" + F_LM + "]";
	}

	

	
	
}
