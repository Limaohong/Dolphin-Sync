package classroom.service;

import java.util.List;

import classroom.model.classroomBean;
import useraccount.model.userAccountBean;

public interface classroomService {
	public List<classroomBean> loadclassroom_teacher(userAccountBean Cr_VC);
	public classroomBean loadoneclassroom(Integer Cr_Id);
	public String findClassroomName(Integer Cr_Id);
	public Integer classinsert(classroomBean clb);
	public Integer classUpdate(classroomBean clb);
	public Integer classDelete(Integer Cr_Id);
	
}
