package fraction.service;

import java.util.List;

import fraction.model.fractionBean;
import fraction.model.fractionDemo;
import student.model.studentBean;
import studentclass.model.studentclassBean;

public interface FractionService {
	List<fractionDemo> loadFraction(studentBean F_SId);
	Integer FractionInsert(fractionBean bean);
	List<fractionDemo> loadFraction_teacher(List<studentclassBean> bean);
	List<fractionDemo> loadFractionlook_teacher(List<studentclassBean> bean,String exam);
	public studentBean queryStudent(Integer S_Id);
	
}
