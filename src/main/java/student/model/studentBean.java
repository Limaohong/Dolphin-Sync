package student.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import contactbook.model.contactbookBean;
import fraction.model.fractionBean;
import studentclass.model.studentclassBean;
import useraccount.model.userAccountBean;

@Entity
@Table(name="Student")
public class studentBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer S_Id;
	String S_Name;
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="S_Phone",referencedColumnName="UA_Phone", nullable = false)
	userAccountBean S_Phone;
	@CreationTimestamp
	Timestamp S_RD;
	@CreationTimestamp
	Timestamp S_LM;
	
	@OneToMany(mappedBy="SC_SI")
	Set<studentclassBean>  studentclasss = new HashSet<>();
	@OneToMany(mappedBy="CB_SId")
	Set<contactbookBean>  contactbooks = new HashSet<>();
	@OneToMany(mappedBy="F_SId")
	Set<fractionBean>  fractions = new HashSet<>();
	public studentBean() {
	}
	public Set<studentclassBean> getStudentclasss() {
		return studentclasss;
	}
	public void setStudentclasss(Set<studentclassBean> studentclasss) {
		this.studentclasss = studentclasss;
	}
	public Set<contactbookBean> getContactbooks() {
		return contactbooks;
	}
	public void setContactbooks(Set<contactbookBean> contactbooks) {
		this.contactbooks = contactbooks;
	}
	public Set<fractionBean> getFractions() {
		return fractions;
	}
	public void setFractions(Set<fractionBean> fractions) {
		this.fractions = fractions;
	}
	public studentBean(Integer s_Id, String s_Name, userAccountBean s_Phone) {
		super();
		S_Id = s_Id;
		S_Name = s_Name;
		S_Phone = s_Phone;
	}
	
	public studentBean(String s_Name, userAccountBean s_Phone) {
		super();
		S_Name = s_Name;
		S_Phone = s_Phone;
	}
	public Integer getS_Id() {
		return S_Id;
	}
	public void setS_Id(Integer s_Id) {
		S_Id = s_Id;
	}
	public String getS_Name() {
		return S_Name;
	}
	public void setS_Name(String s_Name) {
		S_Name = s_Name;
	}
	public userAccountBean getS_Phone() {
		return S_Phone;
	}
	public void setS_Phone(userAccountBean s_Phone) {
		S_Phone = s_Phone;
	}
	public Timestamp getS_RD() {
		return S_RD;
	}
	public void setS_RD(Timestamp s_RD) {
		S_RD = s_RD;
	}
	public Timestamp getS_LM() {
		return S_LM;
	}
	public void setS_LM(Timestamp s_LM) {
		S_LM = s_LM;
	}
	@Override
	public String toString() {
		return "studentBean [S_Id=" + S_Id + ", S_Name=" + S_Name + ", S_Phone=" + S_Phone + ", S_RD=" + S_RD
				+ ", S_LM=" + S_LM + "]";
	}
	
	
}
