package classroom.model;

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
import org.hibernate.annotations.UpdateTimestamp;

import bulletinboard.model.bulletinboardBean;
import studentclass.model.studentclassBean;
import useraccount.model.userAccountBean;
@Entity
@Table(name="Classroom")
public class classroomBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer Cr_Id;

	String Cr_Name;
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="Cr_VC",referencedColumnName="UA_VC", nullable = false)
	userAccountBean Cr_VC;	
	@CreationTimestamp
	Timestamp Cr_RD;
	@UpdateTimestamp
	Timestamp Cr_LM;
	@OneToMany(mappedBy="SC_CI")
	Set<studentclassBean>  studentclasss = new HashSet<>();
	@OneToMany(mappedBy="BB_CrId")
	Set<bulletinboardBean>  bulletinboards = new HashSet<>();
	public classroomBean() {
	}
	
	public Set<bulletinboardBean> getBulletinboards() {
		return bulletinboards;
	}

	public void setBulletinboards(Set<bulletinboardBean> bulletinboards) {
		this.bulletinboards = bulletinboards;
	}

	public Set<studentclassBean> getStudentclasss() {
		return studentclasss;
	}

	public void setStudentclasss(Set<studentclassBean> studentclasss) {
		this.studentclasss = studentclasss;
	}
	public classroomBean(Integer cr_Id, String cr_Name, userAccountBean cr_VC) {
		super();
		Cr_Id = cr_Id;
		Cr_Name = cr_Name;
		Cr_VC = cr_VC;
	}
	
	
	public classroomBean(String cr_Name, userAccountBean cr_VC) {
		super();
		Cr_Name = cr_Name;
		Cr_VC = cr_VC;
	}

	public Integer getCr_Id() {
		return Cr_Id;
	}

	public void setCr_Id(Integer cr_Id) {
		Cr_Id = cr_Id;
	}

	public String getCr_Name() {
		return Cr_Name;
	}
	public void setCr_Name(String cr_Name) {
		Cr_Name = cr_Name;
	}
	public userAccountBean getCr_VC() {
		return Cr_VC;
	}
	public void setCr_VC(userAccountBean cr_VC) {
		Cr_VC = cr_VC;
	}	
	public Timestamp getCr_RD() {
		return Cr_RD;
	}
	public void setCr_RD(Timestamp cr_RD) {
		Cr_RD = cr_RD;
	}
	public Timestamp getCr_LM() {
		return Cr_LM;
	}
	public void setCr_LM(Timestamp cr_LM) {
		Cr_LM = cr_LM;
	}
	@Override
	public String toString() {
		return "classroomBean [Cr_Id=" + Cr_Id + ", Cr_Name=" + Cr_Name + ", Cr_VC=" + Cr_VC + ", Cr_RD=" + Cr_RD
				+ ", Cr_LM=" + Cr_LM + "]";
	}
	
	
}
