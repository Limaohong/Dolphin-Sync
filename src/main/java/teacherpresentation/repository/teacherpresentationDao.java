package teacherpresentation.repository;

import java.util.List;

import company.model.companyBean;
import teacherpresentation.model.DemoTeacher;
import teacherpresentation.model.teacherpresentationBean;
import useraccount.model.userAccountBean;

public interface teacherpresentationDao {
	List<DemoTeacher> loadteachercontent(List<teacherpresentationBean> teacher);
	List<DemoTeacher> loadteacher_boss(companyBean TP_CId);
	List<DemoTeacher> loadteacher_teacher(companyBean TP_CId);
	List<DemoTeacher> loadteacher_parent(companyBean TP_CId);
	teacherpresentationBean queryteacher(Integer TP_Id);
	Integer insertteacherpresentation(teacherpresentationBean tb);
	Integer deleteteacherpresentation(Integer TP_Id);
	DemoTeacher loadsingleteacher(teacherpresentationBean tb);
	public Integer updateteacher(teacherpresentationBean ub, long sizeInBytes);//修改師資資料，含圖
	public Integer updateteacher(teacherpresentationBean ub);//修改師資資料，不含圖
}
