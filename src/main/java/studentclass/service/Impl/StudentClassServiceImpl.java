package studentclass.service.Impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import classroom.model.classroomBean;
import studentclass.model.studentclassBean;
import studentclass.repository.StudentClassDao;
import studentclass.service.StudentClassService;
@Service
@Transactional
public class StudentClassServiceImpl implements StudentClassService {

	@Autowired
	StudentClassDao dao;

	@Override
	public Integer numofstudent(Integer SC_CI) {
		return dao.numofstudent(SC_CI);
	}

	@Override
	public Integer saveStudentClass(studentclassBean bean) {
		return dao.saveStudentClass(bean);
	}

	@Override
	public Integer deleteStudentClass(Integer SC_CI) {
		return dao.deleteStudentClass(SC_CI);
	}


	

}
