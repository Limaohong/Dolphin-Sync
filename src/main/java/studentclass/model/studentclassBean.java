 package studentclass.model;

import java.io.Serializable;
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

import classroom.model.classroomBean;
import student.model.studentBean;
@Entity
@Table(name="StudentClass")
public class studentclassBean implements Serializable {
	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SC_Id")
	Integer SC_Id;
	@ManyToOne(cascade= {CascadeType.PERSIST})
	@JoinColumn(name="SC_CI", nullable = false)
	classroomBean SC_CI;  		//ClassroomId
	String SC_CN;

	@ManyToOne(cascade= {CascadeType.PERSIST})
	@JoinColumn(name="SC_SI", nullable = false)
	studentBean SC_SI;  		//StudentId
	String SC_SN;
	@CreationTimestamp
	Timestamp SC_RD;
	@UpdateTimestamp
	Timestamp SC_LM;
	
	public studentclassBean() {
	}

	public studentclassBean(classroomBean sC_CI, String sC_CN, studentBean sC_SI, String sC_SN) {
		super();
		SC_CI = sC_CI;
		SC_CN = sC_CN;
		SC_SI = sC_SI;
		SC_SN = sC_SN;
	}
	public studentclassBean(classroomBean sC_CI, studentBean sC_SI) {
		super();
		SC_CI = sC_CI;
		SC_SI = sC_SI;
	}

	public Integer getSC_Id() {
		return SC_Id;
	}

	public void setSC_Id(Integer sC_Id) {
		SC_Id = sC_Id;
	}

	public classroomBean getSC_CI() {
		return SC_CI;
	}

	public void setSC_CI(classroomBean sC_CI) {
		SC_CI = sC_CI;
	}

	public String getSC_CN() {
		return SC_CN;
	}

	public void setSC_CN(String sC_CN) {
		SC_CN = sC_CN;
	}

	public String getSC_SN() {
		return SC_SN;
	}

	public void setSC_SN(String sC_SN) {
		SC_SN = sC_SN;
	}

	public studentBean getSC_SI() {
		return SC_SI;
	}

	public void setSC_SI(studentBean sC_SI) {
		SC_SI = sC_SI;
	}

	public Timestamp getSC_RD() {
		return SC_RD;
	}

	public void setSC_RD(Timestamp sC_RD) {
		SC_RD = sC_RD;
	}

	public Timestamp getSC_LM() {
		return SC_LM;
	}

	public void setSC_LM(Timestamp sC_LM) {
		SC_LM = sC_LM;
	}

	@Override
	public String toString() {
		return "studentclassBean [SC_Id=" + SC_Id + ", SC_CI=" + SC_CI + ", SC_SI=" + SC_SI + ", SC_RD=" + SC_RD
				+ ", SC_LM=" + SC_LM + "]";
	}
	
	
	
}
