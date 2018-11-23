package student.service.Impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import classroom.model.classroomBean;
import student.model.studentBean;
import student.repository.Studentdao;
import student.service.StudentService;
import useraccount.model.userAccountBean;
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	@Autowired
	Studentdao dao;

	@Override
	public List<studentBean> findstudents(classroomBean SC_CI) {
		return dao.findstudents(SC_CI);
	}

	@Override
	public Integer insertStudent(studentBean bean) {
		return dao.insertStudent(bean);
	}

	@Override
	public studentBean queryStudent(userAccountBean S_Phone) {
		return dao.queryStudent(S_Phone);
	}

	@Override
	public studentBean queryStudent(Integer S_Id) {
		return dao.queryStudent(S_Id);
	}
}
