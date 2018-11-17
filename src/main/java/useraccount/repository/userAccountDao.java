package useraccount.repository;

import java.util.List;

import student.model.studentBean;
import useraccount.model.userAccountBean;



public interface userAccountDao {
	public userAccountBean checkIDPassword(String UA_Acu,String UA_Psw);
	public userAccountBean checkID(String UA_Acu);
	public userAccountBean queryMember(String UA_Id);	
	boolean idExists(String id);	
	Integer saveMember(userAccountBean ub);	
	public String VerificationCode(String UA_PL);
	public userAccountBean checkVC(String UA_VC);
	public int updatemem(userAccountBean ub, long sizeInBytes);//修改會員資料，含圖
	public int updatemem(userAccountBean ub);//修改會員資料，不含圖
	boolean phoneExists(String phone);
	public List<userAccountBean> findParentsName(List<studentBean> list);
}
