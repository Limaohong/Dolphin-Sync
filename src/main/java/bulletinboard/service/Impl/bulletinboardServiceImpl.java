package bulletinboard.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bulletinboard.model.DemoboardBean;
import bulletinboard.model.bulletinboardBean;
import bulletinboard.repository.bulletinboardDao;
import bulletinboard.service.bulletinboardService;
import classroom.model.classroomBean;
import company.model.companyBean;
import useraccount.model.userAccountBean;
@Service
@Transactional
public class bulletinboardServiceImpl implements bulletinboardService {
	@Autowired
	bulletinboardDao bd;
	@Override
	public List<DemoboardBean> loadbulletinboard_boss(companyBean UA_Id) {
		return bd.loadbulletinboard_boss(UA_Id);
	}
	@Override
	public List<DemoboardBean> loadbulletinboard_teacher(companyBean BB_CrId) {
		return bd.loadbulletinboard_teacher(BB_CrId);
	}
	@Override
	public List<DemoboardBean> loadbulletinboard_parent(companyBean BB_CrId) {
		return bd.loadbulletinboard_parent(BB_CrId);
	}
	@Override
	public List<DemoboardBean> loadBoard(List<bulletinboardBean> bulletinboardBean) {		
		return bd.loadBoard(bulletinboardBean);
	}
	@Override
	public Integer insertBoard(bulletinboardBean bean) {	
		
		return bd.insertBoard(bean);		
	}
	@Override
	public int updateboard(bulletinboardBean bb, long sizeInBytes,classroomBean CR_ID) {
		return bd.updateboard(bb, sizeInBytes,CR_ID);
	}
	@Override
	public int updateboard(bulletinboardBean bb,classroomBean CR_ID) {
		return bd.updateboard(bb,CR_ID);
	}
	@Override
	public bulletinboardBean querysingleclassboard(classroomBean BB_CrId) {
		return bd.querysingleclassboard(BB_CrId);
	}
	@Override
	public DemoboardBean loadsingleboard(bulletinboardBean bb) {
		return bd.loadsingleboard(bb);
	}
	@Override
	public boolean ifclassexist(classroomBean cr_Id) {
		return bd.ifclassexist(cr_Id);
	}
	@Override
	public Integer deleteBoard(classroomBean BB_CrId) {
		return bd.deleteBoard(BB_CrId);
	}
	@Override
	public Integer deletecomBoard(Integer BB_Id) {
		return bd.deletecomBoard(BB_Id);
	}
	@Override
	public bulletinboardBean querycompanyboard(String BB_SN) {
		return bd.querycompanyboard(BB_SN);
	}
	@Override
	public int updatecomboard(bulletinboardBean bb, long sizeInBytes, String BB_SN) {
		return bd.updatecomboard(bb, sizeInBytes, BB_SN);
	}
	@Override
	public int updatecomboard(bulletinboardBean bb, String BB_SN) {
		return bd.updatecomboard(bb, BB_SN);
	}
	

}
