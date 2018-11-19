package bulletinboard.service;

import java.util.List;

import bulletinboard.model.DemoboardBean;
import bulletinboard.model.bulletinboardBean;
import classroom.model.classroomBean;
import company.model.companyBean;
import useraccount.model.userAccountBean;

public interface bulletinboardService {
	public List<DemoboardBean> loadbulletinboard_boss(companyBean UA_Id);
	public List<DemoboardBean> loadbulletinboard_teacher(companyBean BB_CrId);
	public List<DemoboardBean> loadbulletinboard_parent(companyBean BB_CrId);
	public List<DemoboardBean> loadBoard(List<bulletinboardBean> bulletinboardBean);
	public Integer insertBoard(bulletinboardBean bean);
	public Integer deleteBoard(classroomBean BB_CrId);
	public Integer deletecomBoard(Integer BB_Id);
	public int updateboard(bulletinboardBean bb, long sizeInBytes,classroomBean CR_ID);//修改佈告欄資料，含圖
	public int updateboard(bulletinboardBean bb,classroomBean CR_ID);//修改佈告欄資料，不含圖
	public int updatecomboard(bulletinboardBean bb, long sizeInBytes,String BB_SN);
	public int updatecomboard(bulletinboardBean bb, String BB_SN);
	public bulletinboardBean querysingleclassboard(classroomBean BB_CrId);
	public bulletinboardBean querycompanyboard(String BB_SN);
	public DemoboardBean loadsingleboard(bulletinboardBean bb);
	public boolean ifclassexist(classroomBean cr_Id);
}
