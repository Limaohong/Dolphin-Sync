package studentclass.service;


import java.util.List;

import classroom.model.classroomBean;
import studentclass.model.studentclassBean;

public interface StudentClassService {
	public Integer numofstudent(classroomBean SC_CI);
	public Integer saveStudentClass(studentclassBean bean);
	public Integer deleteStudentClass(classroomBean SC_CI);
	public List<studentclassBean> querystudentclass(classroomBean SC_CI);
}
