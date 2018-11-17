package teacherpresentation.model;

import java.io.Serializable;
import java.sql.Blob;

import company.model.companyBean;

public class DemoTeacher implements Serializable{
	private static final long serialVersionUID = 1L;
	Integer TP_Id;
	companyBean TP_CId;
	String TP_Name;
	String TP_TI;
	Blob TP_Avater;
	
	
	
	public DemoTeacher() {
	}
	
	public DemoTeacher(Integer tP_Id, companyBean tP_CId, String tP_Name, String tP_TI, Blob tP_Avater) {
		super();
		TP_Id = tP_Id;
		TP_CId = tP_CId;
		TP_Name = tP_Name;
		TP_TI = tP_TI;
		TP_Avater = tP_Avater;
	}

	public Integer getTP_Id() {
		return TP_Id;
	}
	public void setTP_Id(Integer tP_Id) {
		TP_Id = tP_Id;
	}
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
	public String getTP_TI() {
		return TP_TI;
	}
	public void setTP_TI(String tP_TI) {
		TP_TI = tP_TI;
	}
	
	public Blob getTP_Avater() {
		return TP_Avater;
	}

	public void setTP_Avater(Blob tP_Avater) {
		TP_Avater = tP_Avater;
	}

	@Override
	public String toString() {
		return "DemoTeacher [TP_Id=" + TP_Id + ", TP_CId=" + TP_CId + ", TP_Name=" + TP_Name 
				 + ", TP_TI=" + TP_TI + "]";
	}
	
	
}

