package bulletinboard.model;

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

import classroom.model.classroomBean;
import company.model.companyBean;
@Entity
@Table(name="BulletinBoard")
public class bulletinboardBean implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer BB_Id;
	@ManyToOne(cascade= {CascadeType.PERSIST})
	@JoinColumn(name="BB_CId", nullable = false)
	companyBean BB_CId;
	@ManyToOne(cascade= {CascadeType.PERSIST})
	@JoinColumn(name="BB_CrId", nullable = true)
	classroomBean BB_CrId;
	String BB_SN;
	String BB_T1;
	Clob BB_B1;
	Blob BB_F1;
	String BB_FN1;
	String BB_T2;
	Clob BB_B2;
	Blob BB_F2;
	String BB_FN2;
	String BB_T3;
	Clob BB_B3;
	Blob BB_F3;
	String BB_FN3;
	String BB_T4;
	Clob BB_B4;
	Blob BB_F4;
	String BB_FN4;
	String BB_T5;
	Clob BB_B5;
	Blob BB_F5;
	String BB_FN5;
	@CreationTimestamp
	Timestamp BB_RD;
	@UpdateTimestamp
	Timestamp BB_LM;
	
	public bulletinboardBean() {
	}
	
	public bulletinboardBean(companyBean bB_CId, classroomBean bB_CrId, String bB_SN, String bB_T1,
			Clob bB_B1, Blob bB_F1, String bB_FN1, String bB_T2, Clob bB_B2, Blob bB_F2, String bB_FN2, String bB_T3,
			Clob bB_B3, Blob bB_F3, String bB_FN3, String bB_T4, Clob bB_B4, Blob bB_F4, String bB_FN4, String bB_T5,
			Clob bB_B5, Blob bB_F5, String bB_FN5) {
		super();
		BB_CId = bB_CId;
		BB_CrId = bB_CrId;
		BB_SN = bB_SN;
		BB_T1 = bB_T1;
		BB_B1 = bB_B1;
		BB_F1 = bB_F1;
		BB_FN1 = bB_FN1;
		BB_T2 = bB_T2;
		BB_B2 = bB_B2;
		BB_F2 = bB_F2;
		BB_FN2 = bB_FN2;
		BB_T3 = bB_T3;
		BB_B3 = bB_B3;
		BB_F3 = bB_F3;
		BB_FN3 = bB_FN3;
		BB_T4 = bB_T4;
		BB_B4 = bB_B4;
		BB_F4 = bB_F4;
		BB_FN4 = bB_FN4;
		BB_T5 = bB_T5;
		BB_B5 = bB_B5;
		BB_F5 = bB_F5;
		BB_FN5 = bB_FN5;
	}

	public bulletinboardBean(companyBean bB_CId, classroomBean bB_CrId, String bB_SN, String bB_T1, Clob bB_B1,
			Blob bB_F1, String bB_FN1, String bB_T2, Clob bB_B2, Blob bB_F2, String bB_FN2, String bB_T3, Clob bB_B3,
			Blob bB_F3, String bB_FN3, String bB_T4, Clob bB_B4, Blob bB_F4, String bB_FN4, String bB_T5, Clob bB_B5,
			Blob bB_F5, String bB_FN5, Timestamp bB_RD, Timestamp bB_LM) {
		super();
		BB_CId = bB_CId;
		BB_CrId = bB_CrId;
		BB_SN = bB_SN;
		BB_T1 = bB_T1;
		BB_B1 = bB_B1;
		BB_F1 = bB_F1;
		BB_FN1 = bB_FN1;
		BB_T2 = bB_T2;
		BB_B2 = bB_B2;
		BB_F2 = bB_F2;
		BB_FN2 = bB_FN2;
		BB_T3 = bB_T3;
		BB_B3 = bB_B3;
		BB_F3 = bB_F3;
		BB_FN3 = bB_FN3;
		BB_T4 = bB_T4;
		BB_B4 = bB_B4;
		BB_F4 = bB_F4;
		BB_FN4 = bB_FN4;
		BB_T5 = bB_T5;
		BB_B5 = bB_B5;
		BB_F5 = bB_F5;
		BB_FN5 = bB_FN5;
		BB_RD = bB_RD;
		BB_LM = bB_LM;
	}

	public Integer getBB_Id() {
		return BB_Id;
	}

	public void setBB_Id(Integer bB_Id) {
		BB_Id = bB_Id;
	}

	public companyBean getBB_CId() {
		return BB_CId;
	}

	public void setBB_CId(companyBean bB_CId) {
		BB_CId = bB_CId;
	}

	public classroomBean getBB_CrId() {
		return BB_CrId;
	}

	public void setBB_CrId(classroomBean bB_CrId) {
		BB_CrId = bB_CrId;
	}

	public String getBB_SN() {
		return BB_SN;
	}

	public void setBB_SN(String bB_SN) {
		BB_SN = bB_SN;
	}

	public String getBB_T1() {
		return BB_T1;
	}

	public void setBB_T1(String bB_T1) {
		BB_T1 = bB_T1;
	}

	public Clob getBB_B1() {
		return BB_B1;
	}

	public void setBB_B1(Clob bB_B1) {
		BB_B1 = bB_B1;
	}

	public Blob getBB_F1() {
		return BB_F1;
	}

	public void setBB_F1(Blob bB_F1) {
		BB_F1 = bB_F1;
	}

	public String getBB_FN1() {
		return BB_FN1;
	}

	public void setBB_FN1(String bB_FN1) {
		BB_FN1 = bB_FN1;
	}

	public String getBB_T2() {
		return BB_T2;
	}

	public void setBB_T2(String bB_T2) {
		BB_T2 = bB_T2;
	}

	public Clob getBB_B2() {
		return BB_B2;
	}

	public void setBB_B2(Clob bB_B2) {
		BB_B2 = bB_B2;
	}

	public Blob getBB_F2() {
		return BB_F2;
	}

	public void setBB_F2(Blob bB_F2) {
		BB_F2 = bB_F2;
	}

	public String getBB_FN2() {
		return BB_FN2;
	}

	public void setBB_FN2(String bB_FN2) {
		BB_FN2 = bB_FN2;
	}

	public String getBB_T3() {
		return BB_T3;
	}

	public void setBB_T3(String bB_T3) {
		BB_T3 = bB_T3;
	}

	public Clob getBB_B3() {
		return BB_B3;
	}

	public void setBB_B3(Clob bB_B3) {
		BB_B3 = bB_B3;
	}

	public Blob getBB_F3() {
		return BB_F3;
	}

	public void setBB_F3(Blob bB_F3) {
		BB_F3 = bB_F3;
	}

	public String getBB_FN3() {
		return BB_FN3;
	}

	public void setBB_FN3(String bB_FN3) {
		BB_FN3 = bB_FN3;
	}

	public String getBB_T4() {
		return BB_T4;
	}

	public void setBB_T4(String bB_T4) {
		BB_T4 = bB_T4;
	}

	public Clob getBB_B4() {
		return BB_B4;
	}

	public void setBB_B4(Clob bB_B4) {
		BB_B4 = bB_B4;
	}

	public Blob getBB_F4() {
		return BB_F4;
	}

	public void setBB_F4(Blob bB_F4) {
		BB_F4 = bB_F4;
	}

	public String getBB_FN4() {
		return BB_FN4;
	}

	public void setBB_FN4(String bB_FN4) {
		BB_FN4 = bB_FN4;
	}

	public String getBB_T5() {
		return BB_T5;
	}

	public void setBB_T5(String bB_T5) {
		BB_T5 = bB_T5;
	}

	public Clob getBB_B5() {
		return BB_B5;
	}

	public void setBB_B5(Clob bB_B5) {
		BB_B5 = bB_B5;
	}

	public Blob getBB_F5() {
		return BB_F5;
	}

	public void setBB_F5(Blob bB_F5) {
		BB_F5 = bB_F5;
	}

	public String getBB_FN5() {
		return BB_FN5;
	}

	public void setBB_FN5(String bB_FN5) {
		BB_FN5 = bB_FN5;
	}

	public Timestamp getBB_RD() {
		return BB_RD;
	}

	public void setBB_RD(Timestamp bB_RD) {
		BB_RD = bB_RD;
	}

	public Timestamp getBB_LM() {
		return BB_LM;
	}

	public void setBB_LM(Timestamp bB_LM) {
		BB_LM = bB_LM;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	

	



	
}
