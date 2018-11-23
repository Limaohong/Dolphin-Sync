package fraction.service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fraction.model.fractionBean;
import fraction.model.fractionDemo;
import fraction.repository.FractionDao;
import fraction.service.FractionService;
import student.model.studentBean;
import studentclass.model.studentclassBean;
@Service
@Transactional
public class FractionServiceImpl implements FractionService {

	@Autowired
	FractionDao dao;
	
	@Override
	public List<fractionDemo> loadFraction(studentBean F_SId) {
		return dao.loadFraction(F_SId);
	}

	@Override
	public Integer FractionInsert(fractionBean bean) {
		return dao.FractionInsert(bean);
	}

	@Override
	public List<fractionDemo> loadFraction_teacher(List<studentclassBean> bean) {
		return dao.loadFraction_teacher(bean);
	}

	@Override
	public studentBean queryStudent(Integer S_Id) {
		return dao.queryStudent(S_Id);
	}

	@Override
	public List<fractionDemo> loadFractionlook_teacher(List<studentclassBean> bean, String exam) {
		return dao.loadFractionlook_teacher(bean, exam);
	}

}
