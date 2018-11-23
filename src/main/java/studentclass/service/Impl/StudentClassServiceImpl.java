package studentclass.service.Impl;



import java.util.List;

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
	public Integer numofstudent(classroomBean SC_CI) {
		return dao.numofstudent(SC_CI);
	}

	@Override
	public Integer saveStudentClass(studentclassBean bean) {
		return dao.saveStudentClass(bean);
	}

	@Override
	public Integer deleteStudentClass(classroomBean SC_CI) {
		return dao.deleteStudentClass(SC_CI);
	}

	@Override
	public List<studentclassBean> querystudentclass(classroomBean SC_CI) {
		return dao.querystudentclass(SC_CI);
	}

	


	

}
