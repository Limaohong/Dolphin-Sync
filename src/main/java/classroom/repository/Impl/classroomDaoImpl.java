package classroom.repository.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import classroom.model.classroomBean;
import classroom.repository.classroomDao;
import useraccount.model.userAccountBean;
@Repository
public class classroomDaoImpl implements classroomDao {
	@Autowired
	SessionFactory factory;
	
	
	@SuppressWarnings({ "unchecked"})
	@Override
	public List<classroomBean> loadclassroom_teacher(userAccountBean Cr_VC) {
		List<classroomBean> cb;
		String hql = "From classroomBean Where Cr_VC=:cvc";
		Session session = factory.getCurrentSession();		
		cb  = session.createQuery(hql).setParameter("cvc", Cr_VC).getResultList();
		
		return cb;
	}


	

	@Override
	public String findClassroomName(Integer Cr_Id) {
		classroomBean cb = null;
		String hql = "FROM classroomBean WHERE Cr_Id =:id";
		Session session = factory.getCurrentSession();
		cb = (classroomBean) session.createQuery(hql).setParameter("id", Cr_Id).getSingleResult();
		String Cr_Name = cb.getCr_Name();
		return Cr_Name;
	}


	@Override
	public Integer classinsert(classroomBean clb) {
		Integer n = 0;
		Session session = factory.getCurrentSession();
		session.save(clb);
		n++;
		return n;
	}


	@Override
	public Integer classUpdate(classroomBean clb) {
		Integer n = 0;
		Session session = factory.getCurrentSession();
		String hql = "UPDATE classroomBean SET Cr_Name=:name WHERE Cr_Id=:id";
		Integer Cr_Id = clb.getCr_Id();
		String Cr_Name = clb.getCr_Name();
		session.createQuery(hql).setParameter("name", Cr_Name)
								.setParameter("id", Cr_Id)
								.executeUpdate();
		n++;
		return n;
	}


	@Override
	public classroomBean loadoneclassroom(Integer Cr_Id) {
		classroomBean cb = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM classroomBean WHERE Cr_Id =:id";
		cb = (classroomBean) session.createQuery(hql).setParameter("id", Cr_Id).getSingleResult();
		return cb;
	}




	@Override
	public Integer classDelete(Integer Cr_Id) {
		Integer n = 0;
		Session session = factory.getCurrentSession();
		String hql = "DELETE classroomBean WHERE Cr_Id =:id";
		session.createQuery(hql).setParameter("id", Cr_Id).executeUpdate();
		n++;
		return n;
	}

}
