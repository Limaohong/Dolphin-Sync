package company.service;

import company.model.companyBean;
import useraccount.model.userAccountBean;

public interface companyService {

	Integer savecompany(companyBean cb);
	public companyBean loadcompany_boss(String UA_VC);
	public companyBean loadcompany_teacher(String UA_VC);
	public companyBean loadcompany_parent(String UA_VC);
	public int updatecom(companyBean cb, long sizeInBytes);//修改會員資料，含圖
	public int updatecom(companyBean cb);//修改會員資料，不含圖
	public boolean CNExists(String name);
	public boolean CPExists(String phone);
	public int updateslide(companyBean cb,userAccountBean ub);
}