package student.repository;

import java.util.List;

import classroom.model.classroomBean;
import student.model.studentBean;
import useraccount.model.userAccountBean;

public interface Studentdao {
	public List<studentBean> findstudents(classroomBean SC_CI);
	public Integer insertStudent(studentBean bean);
	public studentBean queryStudent(userAccountBean S_Phone);
	public studentBean queryStudent(Integer S_Id);
}
