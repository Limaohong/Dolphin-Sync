package studentclass.repository;


import studentclass.model.studentclassBean;

public interface StudentClassDao {
	public Integer numofstudent(Integer SC_CI);
	public Integer saveStudentClass(studentclassBean bean);
	public Integer deleteStudentClass(Integer SC_CI);
}
