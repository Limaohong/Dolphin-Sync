package bulletinboard.model;

import java.io.Serializable;

public class DemoboardBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String BB_B1;
	String BB_B2;
	String BB_B3;
	String BB_B4;
	String BB_B5;
	Integer BB_Id;
	Integer BB_CrId;
	String BB_SN;
	String BB_T1;
	String BB_T2;
	String BB_T3;
	String BB_T4;
	String BB_T5;
	
	public DemoboardBean() {
	}


	


	public DemoboardBean(String bB_B1, String bB_B2, String bB_B3, String bB_B4, String bB_B5, Integer bB_Id, String bB_SN,
			String bB_T1, String bB_T2, String bB_T3, String bB_T4, String bB_T5) {
		super();
		BB_B1 = bB_B1;
		BB_B2 = bB_B2;
		BB_B3 = bB_B3;
		BB_B4 = bB_B4;
		BB_B5 = bB_B5;
		BB_Id = bB_Id;
		BB_SN = bB_SN;
		BB_T1 = bB_T1;
		BB_T2 = bB_T2;
		BB_T3 = bB_T3;
		BB_T4 = bB_T4;
		BB_T5 = bB_T5;
	}

	




	public DemoboardBean(String bB_B1, String bB_B2, String bB_B3, String bB_B4, String bB_B5, Integer bB_Id,
			Integer bB_CrId, String bB_SN, String bB_T1, String bB_T2, String bB_T3, String bB_T4, String bB_T5) {
		super();
		BB_B1 = bB_B1;
		BB_B2 = bB_B2;
		BB_B3 = bB_B3;
		BB_B4 = bB_B4;
		BB_B5 = bB_B5;
		BB_Id = bB_Id;
		BB_CrId = bB_CrId;
		BB_SN = bB_SN;
		BB_T1 = bB_T1;
		BB_T2 = bB_T2;
		BB_T3 = bB_T3;
		BB_T4 = bB_T4;
		BB_T5 = bB_T5;
	}





	public Integer getBB_CrId() {
		return BB_CrId;
	}


	public void setBB_CrId(Integer bB_CrId) {
		BB_CrId = bB_CrId;
	}



	public String getBB_B1() {
		return BB_B1;
	}


	public void setBB_B1(String bB_B1) {
		BB_B1 = bB_B1;
	}


	public String getBB_B2() {
		return BB_B2;
	}


	public void setBB_B2(String bB_B2) {
		BB_B2 = bB_B2;
	}


	public String getBB_B3() {
		return BB_B3;
	}


	public void setBB_B3(String bB_B3) {
		BB_B3 = bB_B3;
	}


	public String getBB_B4() {
		return BB_B4;
	}


	public void setBB_B4(String bB_B4) {
		BB_B4 = bB_B4;
	}


	public String getBB_B5() {
		return BB_B5;
	}


	public void setBB_B5(String bB_B5) {
		BB_B5 = bB_B5;
	}

	
	

	public Integer getBB_Id() {
		return BB_Id;
	}





	public void setBB_Id(Integer bB_Id) {
		BB_Id = bB_Id;
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





	public String getBB_T2() {
		return BB_T2;
	}





	public void setBB_T2(String bB_T2) {
		BB_T2 = bB_T2;
	}





	public String getBB_T3() {
		return BB_T3;
	}





	public void setBB_T3(String bB_T3) {
		BB_T3 = bB_T3;
	}





	public String getBB_T4() {
		return BB_T4;
	}





	public void setBB_T4(String bB_T4) {
		BB_T4 = bB_T4;
	}





	public String getBB_T5() {
		return BB_T5;
	}





	public void setBB_T5(String bB_T5) {
		BB_T5 = bB_T5;
	}





	@Override
	public String toString() {
		return "DemoboardBean [BB_B1=" + BB_B1 + ", BB_B2=" + BB_B2 + ", BB_B3=" + BB_B3 + ", BB_B4=" + BB_B4
				+ ", BB_B5=" + BB_B5 + ", BB_Id=" + BB_Id + ", BB_CrId=" + BB_CrId + ", BB_SN=" + BB_SN + ", BB_T1="
				+ BB_T1 + ", BB_T2=" + BB_T2 + ", BB_T3=" + BB_T3 + ", BB_T4=" + BB_T4 + ", BB_T5=" + BB_T5 + "]";
	}





	





	
	
	
	
}
