package useraccount.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import classroom.model.classroomBean;
import company.model.companyBean;
import student.model.studentBean;
@Entity
@Table(name="UserAccount")
public class userAccountBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer UA_Id;
	Integer UA_PL;
	@Column(unique = true, nullable = false)
	String UA_Acu;
	String UA_Psw;
	String UA_Name;
	@Column(unique = true, nullable = false)
	String UA_Phone;
	@Column(unique = true, nullable = false)
	String UA_VC;
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="UA_CVC",referencedColumnName="C_VC",unique = true, nullable = false)
	companyBean UA_CVC;

	Blob UA_Avater;
	@CreationTimestamp
	Timestamp UA_RD;
	@UpdateTimestamp
	Timestamp UA_LM;
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="S_Phone")
	Set<studentBean>  students = new HashSet<>();
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="Cr_VC")
	Set<classroomBean>  classrooms = new HashSet<>();
	
	
			

	

	public userAccountBean() {

	}

	public userAccountBean(Integer uA_Id, Integer uA_PL, String uA_Acu, String uA_Psw, String uA_Name,
			 String uA_Phone, String uA_VC, Blob uA_Avater,
			Timestamp uA_RD, Timestamp uA_LM) {
		super();
		UA_Id = uA_Id;
		UA_PL = uA_PL;
		UA_Acu = uA_Acu;
		UA_Psw = uA_Psw;
		UA_Name = uA_Name;
		UA_Phone = uA_Phone;
		UA_VC = uA_VC;
		UA_Avater = uA_Avater;
		UA_RD = uA_RD;
		UA_LM = uA_LM;
	}
	//for all update
		public userAccountBean(String uA_Acu, String uA_Psw, String uA_Name, String uA_Phone,
				Blob uA_Avater) {
			super();
			UA_Acu = uA_Acu;
			UA_Psw = uA_Psw;
			UA_Name = uA_Name;
			UA_Phone = uA_Phone;
			UA_Avater = uA_Avater;
		}
//		for Boss/teacher register 
		public userAccountBean(Integer uA_PL, String uA_Acu, String uA_Psw, String uA_Name, String uA_Phone,
				String uA_VC,companyBean uA_CVC, Blob uA_Avater) {
			super();
			UA_PL = uA_PL;
			UA_Acu = uA_Acu;
			UA_Psw = uA_Psw;
			UA_Name = uA_Name;
			UA_Phone = uA_Phone;
			UA_VC = uA_VC;
			UA_CVC = uA_CVC;
			UA_Avater = uA_Avater;

		}		

	
	//	for parent register
	public userAccountBean(Integer uA_PL, String uA_Acu, String uA_Psw, String uA_Name, String uA_Phone, String uA_VC,
			companyBean uA_CVC) {
		super();
		UA_PL = uA_PL;
		UA_Acu = uA_Acu;
		UA_Psw = uA_Psw;
		UA_Name = uA_Name;
		UA_Phone = uA_Phone;
		UA_VC = uA_VC;
		UA_CVC = uA_CVC;
	}

	public Integer getUA_Id() {
		return UA_Id;
	}


	public void setUA_Id(Integer uA_Id) {
		UA_Id = uA_Id;
	}
	public Integer getUA_PL() {
		return UA_PL;
	}
	public void setUA_PL(Integer uA_PL) {
		UA_PL = uA_PL;
	}
	public String getUA_Acu() {
		return UA_Acu;
	}
	public void setUA_Acu(String uA_Acu) {
		UA_Acu = uA_Acu;
	}
	public String getUA_Psw() {
		return UA_Psw;
	}
	public void setUA_Psw(String uA_Psw) {
		UA_Psw = uA_Psw;
	}
	
	public companyBean getUA_CVC() {
		return UA_CVC;
	}

	public void setUA_CVC(companyBean uA_CVC) {
		UA_CVC = uA_CVC;
	}

	public String getUA_Name() {
		return UA_Name;
	}
	public void setUA_Name(String uA_Name) {
		UA_Name = uA_Name;
	}	
	
	public String getUA_Phone() {
		return UA_Phone;
	}
	public void setUA_Phone(String uA_Phone) {
		UA_Phone = uA_Phone;
	}
	public String getUA_VC() {
		return UA_VC;
	}
	public void setUA_VC(String uA_VC) {
		UA_VC = uA_VC;
	}
	
	

	public Blob getUA_Avater() {
		return UA_Avater;
	}
	public void setUA_Avater(Blob uA_Avater) {
		UA_Avater = uA_Avater;
	}
	public Timestamp getUA_RD() {
		return UA_RD;
	}
	
	public void setUA_RD(Timestamp uA_RD) {
		UA_RD = uA_RD;
	}
	public Timestamp getUA_LM() {
		return UA_LM;
	}
	
	public void setUA_LM(Timestamp uA_LM) {
		UA_LM = uA_LM;
	}
		

	public Set<studentBean> getStudents() {
		return students;
	}

	public void setStudents(Set<studentBean> students) {
		this.students = students;
	}

	public Set<classroomBean> getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(Set<classroomBean> classrooms) {
		this.classrooms = classrooms;
	}

	@Override
	public String toString() {
		return "userAccountBean [UA_Id=" + UA_Id + ", UA_PL=" + UA_PL + ", UA_Acu=" + UA_Acu + ", UA_Psw=" + UA_Psw
				+ ", UA_Name=" + UA_Name  + ", UA_Phone=" + UA_Phone
				+ ", UA_VC=" + UA_VC + ", UA_CVC=" + UA_CVC + ", UA_Avater=" + UA_Avater
				+ ", UA_RD=" + UA_RD + ", UA_LM=" + UA_LM + "]";
	}
	
	
	
}

