package company.model;

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

import bulletinboard.model.bulletinboardBean;
import teacherpresentation.model.teacherpresentationBean;
import useraccount.model.userAccountBean;

@Entity
@Table(name = "Company")
public class companyBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer C_Id;
	@Column(unique = true, nullable = false)
	Blob C_CL;
	@Column(unique = true, nullable = false)
	String C_CN;
	@Column(unique = true, nullable = false)
	String C_CP;
	
	String C_VC;
	Blob Slide_1;
	Blob Slide_2;
	Blob Slide_3;
	Blob Slide_4;
	Blob Slide_5;
	@CreationTimestamp
	Timestamp C_RD;
	@UpdateTimestamp
	Timestamp C_LM;
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="TP_CId")
	Set<teacherpresentationBean>  tp = new HashSet<>();
	@OneToMany(mappedBy="BB_CId")
	Set<bulletinboardBean>  bulletinboards = new HashSet<>();
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="UA_CVC")
	Set<userAccountBean>  userAccounts = new HashSet<>();


	public companyBean() {
		
	}


	public companyBean(Integer c_Id, Blob c_CL, String c_CN, String c_CP, String c_VC, Blob slide_1, Blob slide_2,
			Blob slide_3, Blob slide_4, Blob slide_5, Timestamp c_RD, Timestamp c_LM) {
		super();
		C_Id = c_Id;
		C_CL = c_CL;
		C_CN = c_CN;
		C_CP = c_CP;
		C_VC = c_VC;
		Slide_1 = slide_1;
		Slide_2 = slide_2;
		Slide_3 = slide_3;
		Slide_4 = slide_4;
		Slide_5 = slide_5;
		C_RD = c_RD;
		C_LM = c_LM;
	}
	//for register
	public companyBean(Blob c_CL, String c_CN, String c_CP, String c_VC) {
		super();
		C_CL = c_CL;
		C_CN = c_CN;
		C_CP = c_CP;
		C_VC = c_VC;
	}


	//for boss update
	public companyBean(Blob c_CL, String c_CN, String c_CP) {
		super();
		C_CL = c_CL;
		C_CN = c_CN;
		C_CP = c_CP;
	}


	
	public companyBean(Blob slide_1, Blob slide_2, Blob slide_3, Blob slide_4, Blob slide_5) {
		super();
		Slide_1 = slide_1;
		Slide_2 = slide_2;
		Slide_3 = slide_3;
		Slide_4 = slide_4;
		Slide_5 = slide_5;

	}
	
	public Set<teacherpresentationBean> getTp() {
		return tp;
	}
	
	public void setTp(Set<teacherpresentationBean> tp) {
		this.tp = tp;
	}
	
	public Integer getC_Id() {
		return C_Id;
	}

	public void setC_Id(Integer c_Id) {
		C_Id = c_Id;
	}

	public Blob getC_CL() {
		return C_CL;
	}

	public void setC_CL(Blob c_CL) {
		C_CL = c_CL;
	}

	public String getC_CN() {
		return C_CN;
	}

	public void setC_CN(String c_CN) {
		C_CN = c_CN;
	}

	public String getC_CP() {
		return C_CP;
	}

	public void setC_CP(String c_CP) {
		C_CP = c_CP;
	}

	public String getC_VC() {
		return C_VC;
	}

	public void setC_VC(String c_VC) {
		C_VC = c_VC;
	}

	public Blob getSlide_1() {
		return Slide_1;
	}

	public void setSlide_1(Blob slide_1) {
		Slide_1 = slide_1;
	}

	public Blob getSlide_2() {
		return Slide_2;
	}

	public void setSlide_2(Blob slide_2) {
		Slide_2 = slide_2;
	}

	public Blob getSlide_3() {
		return Slide_3;
	}

	public void setSlide_3(Blob slide_3) {
		Slide_3 = slide_3;
	}

	public Blob getSlide_4() {
		return Slide_4;
	}

	public void setSlide_4(Blob slide_4) {
		Slide_4 = slide_4;
	}

	public Blob getSlide_5() {
		return Slide_5;
	}

	public void setSlide_5(Blob slide_5) {
		Slide_5 = slide_5;
	}	

	public Timestamp getC_RD() {
		return C_RD;
	}

	public void setC_RD(Timestamp c_RD) {
		C_RD = c_RD;
	}

	public Timestamp getC_LM() {
		return C_LM;
	}

	public void setC_LM(Timestamp c_LM) {
		C_LM = c_LM;
	}


	@Override
	public String toString() {
		return "companyBean [C_Id=" + C_Id + ", C_CL=" + C_CL + ", C_CN=" + C_CN + ", C_CP=" + C_CP + ", C_VC=" + C_VC
				+ ", C_RD=" + C_RD + ", C_LM=" + C_LM + "]";
	}

}
