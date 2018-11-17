package contactbook.model;

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

import student.model.studentBean;
@Entity
@Table(name="ContactBook")
public class contactbookBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer CB_Id;
	@ManyToOne(cascade= {CascadeType.PERSIST})
	@JoinColumn(name="CB_SId", nullable = false)
	studentBean CB_SId;
	Clob CB_CIT;
	Clob CB_SIT;
	Clob CB_RIT;
	Blob CB_File;
	String CB_FN;
	@CreationTimestamp
	Timestamp CB_RD;
	
	public contactbookBean() {
	}

	public contactbookBean(studentBean cB_SId, Clob cB_SIT, Clob cB_RIT, Blob cB_File, String cB_FN) {
		super();
		CB_SId = cB_SId;
		CB_SIT = cB_SIT;
		CB_RIT = cB_RIT;
		CB_File = cB_File;
		CB_FN = cB_FN;
	}

	public Integer getCB_Id() {
		return CB_Id;
	}

	public void setCB_Id(Integer cB_Id) {
		CB_Id = cB_Id;
	}

	public studentBean getCB_SId() {
		return CB_SId;
	}

	public void setCB_SId(studentBean cB_SId) {
		CB_SId = cB_SId;
	}
	public Clob getCB_CIT() {
		return CB_CIT;
	}

	public void setCB_CIT(Clob cB_CIT) {
		CB_CIT = cB_CIT;
	}
	public Clob getCB_SIT() {
		return CB_SIT;
	}

	public void setCB_SIT(Clob cB_SIT) {
		CB_SIT = cB_SIT;
	}

	public Clob getCB_RIT() {
		return CB_RIT;
	}

	public void setCB_RIT(Clob cB_RIT) {
		CB_RIT = cB_RIT;
	}

	public Blob getCB_File() {
		return CB_File;
	}

	public void setCB_File(Blob cB_File) {
		CB_File = cB_File;
	}

	public String getCB_FN() {
		return CB_FN;
	}

	public void setCB_FN(String cB_FN) {
		CB_FN = cB_FN;
	}

	public Timestamp getCB_RD() {
		return CB_RD;
	}

	public void setCB_RD(Timestamp cB_RD) {
		CB_RD = cB_RD;
	}

	@Override
	public String toString() {
		return "contactbookBean [CB_Id=" + CB_Id + ", CB_SId=" + CB_SId + ", CB_SIT=" + CB_SIT + ", CB_RIT=" + CB_RIT
				+ ", CB_File=" + CB_File + ", CB_FN=" + CB_FN + ", CB_RD=" + CB_RD + "]";
	}
	
	
	
}
