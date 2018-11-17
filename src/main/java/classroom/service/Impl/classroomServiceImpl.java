package classroom.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import classroom.model.classroomBean;
import classroom.repository.classroomDao;
import classroom.service.classroomService;
import useraccount.model.userAccountBean;
@Service
@Transactional
public class classroomServiceImpl implements classroomService {
	@Autowired
	classroomDao cb;
	
	@Override
	public List<classroomBean> loadclassroom_teacher(userAccountBean Cr_VC) {
		
		return cb.loadclassroom_teacher(Cr_VC);
	}

	

	@Override
	public String findClassroomName(Integer Cr_Id) {
		return cb.findClassroomName(Cr_Id);
	}

	@Override
	public Integer classinsert(classroomBean clb) {
		return cb.classinsert(clb);
	}

	@Override
	public Integer classUpdate(classroomBean clb) {
		return cb.classUpdate(clb);
	}

	@Override
	public classroomBean loadoneclassroom(Integer Cr_Id) {
		return cb.loadoneclassroom(Cr_Id);
	}



	@Override
	public Integer classDelete(Integer Cr_Id) {
		return cb.classDelete(Cr_Id);
	}

}
