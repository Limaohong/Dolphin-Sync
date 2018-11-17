package studentclass.repository.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import classroom.model.classroomBean;
import studentclass.model.studentclassBean;
import studentclass.repository.StudentClassDao;
@Repository
public class StudentClassDaoImpl implements StudentClassDao {

	@Autowired
	SessionFactory factory;
	
	@Override
	public Integer numofstudent(Integer SC_CI) {
		Integer n = 0;
		Session session = factory.getCurrentSession();
		String hql = "Select count(SC_CI) FROM studentclassBean WHERE SC_CI = :scci";
		Query query = session.createQuery(hql).setParameter("scci", SC_CI);
		List listResult = query.list();
		Number number = (Number) listResult.get(0);
		n= number.intValue();
		return n;
	}

	@Override
	public Integer saveStudentClass(studentclassBean bean) {
		Integer n = 0;
		Session session = factory.getCurrentSession();
		session.save(bean);
		n++;
		return n;
	}

	@Override
	public Integer deleteStudentClass(Integer SC_CI) {
		Session session = factory.getCurrentSession();
		String hql ="DELETE studentclassBean WHERE SC_CI=:scci";
		Integer n = 0;
		session.createQuery(hql).setParameter("scci", SC_CI).executeUpdate();
		n++;
		
		return n;
	}

}
