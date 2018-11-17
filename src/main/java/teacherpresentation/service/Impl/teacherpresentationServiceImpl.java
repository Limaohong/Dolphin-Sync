package teacherpresentation.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import company.model.companyBean;
import teacherpresentation.model.DemoTeacher;
import teacherpresentation.model.teacherpresentationBean;
import teacherpresentation.repository.teacherpresentationDao;
import teacherpresentation.service.teacherpresentationService;
import useraccount.model.userAccountBean;
@Service
@Transactional
public class teacherpresentationServiceImpl implements teacherpresentationService {

	@Autowired
	teacherpresentationDao dao;
	
	
	public teacherpresentationServiceImpl() {
	}

	@Override
	public List<DemoTeacher> loadteachercontent(List<teacherpresentationBean> teacher) {
		return dao.loadteachercontent(teacher);
	}

	@Override
	public List<DemoTeacher> loadteacher_boss(companyBean TP_CId) {
		return dao.loadteacher_boss(TP_CId);
	}

	@Override
	public List<DemoTeacher> loadteacher_teacher(companyBean TP_CId) {
		return dao.loadteacher_teacher(TP_CId);
	}

	@Override
	public List<DemoTeacher> loadteacher_parent(companyBean TP_CId) {
		return dao.loadteacher_parent(TP_CId);
	}

	@Override
	public teacherpresentationBean queryteacher(Integer TP_Id) {
		return dao.queryteacher(TP_Id);
	}

	@Override
	public Integer insertteacherpresentation(teacherpresentationBean tb) {
		return dao.insertteacherpresentation(tb);
	}

	@Override
	public DemoTeacher loadsingleteacher(teacherpresentationBean tb) {
		return dao.loadsingleteacher(tb);
	}

	@Override
	public Integer updateteacher(teacherpresentationBean ub, long sizeInBytes) {
		return dao.updateteacher(ub, sizeInBytes);
	}

	@Override
	public Integer updateteacher(teacherpresentationBean ub) {
		return dao.updateteacher(ub);
	}

	@Override
	public Integer deleteteacherpresentation(Integer TP_Id) {
		return dao.deleteteacherpresentation(TP_Id);
	}

}
