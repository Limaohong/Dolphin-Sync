package student.service;

import java.util.List;

import classroom.model.classroomBean;
import student.model.studentBean;
import useraccount.model.userAccountBean;

public interface StudentService {
	public List<studentBean> findstudents(Integer SC_CI);
	public Integer insertStudent(studentBean bean);
	public studentBean queryStudent(userAccountBean S_Phone);
}
