package student.repository.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import classroom.model.classroomBean;
import student.model.studentBean;
import student.repository.Studentdao;
import studentclass.model.studentclassBean;
import useraccount.model.userAccountBean;
@Repository
public class StudentdaoImpl implements Studentdao {
	@Autowired
	SessionFactory factory;


	@SuppressWarnings("unchecked")
	@Override
	public List<studentBean> findstudents(classroomBean SC_CI) {
		Session session = factory.getCurrentSession();
		List<studentclassBean> studentclassBeanlist = null;
		String hql = "FROM studentclassBean WHERE SC_CI=:scci";
		studentclassBeanlist = session.createQuery(hql).setParameter("scci", SC_CI).getResultList();
		List<studentBean> studentBeanlist = new ArrayList<studentBean>();
		studentBean studentBean = null;
		String hql2 = "FROM studentBean WHERE S_Id=:id";
		for(studentclassBean sc:studentclassBeanlist) {
			studentBean = (student.model.studentBean) session.createQuery(hql2).setParameter("id", sc.getSC_SI().getS_Id()).getSingleResult();
			studentBeanlist.add(studentBean);
		}
		
		
//		ArrayList<Object> list = new ArrayList<>();
//		Object[] ob = null;
//		List<studentBean> studentBeanlist = new ArrayList<studentBean>();
//		studentBean studentBean = new studentBean();
//		List<studentclassBean> studentclassBeanlist = new ArrayList<studentclassBean>();
//		studentclassBean studentclassBean = new studentclassBean();
//		Session session = factory.getCurrentSession();
//		String hql = "FROM studentBean s join studentclassBean sc on s.S_Id=sc.SC_SI "
//				+ "WHERE sc.SC_CI=:scci";
//		list = (ArrayList<Object>) session.createQuery(hql).setParameter("scci", SC_CI).getResultList();
//		for(int i = 0; i<list.size() ; i++) {
//			
//				ob =  (Object[]) list.get(i);
//				for(int f = 0; f<ob.length ;f++) {
//					if(ob[f].getClass() == studentBean.getClass()) {
//						studentBeanlist.add((student.model.studentBean) ob[f]);
//					}else if (ob[f].getClass() == studentclassBean.getClass()) {
//						studentclassBeanlist.add( (studentclass.model.studentclassBean) ob[f]);
//					}				
//				}
				
//		}
		return studentBeanlist;
	}


	@Override
	public Integer insertStudent(studentBean bean) {
		Integer n = 0;
		Session session = factory.getCurrentSession();
		session.save(bean);
		n++;
		return n;
	}


	@SuppressWarnings("unchecked")
	@Override
	public studentBean queryStudent(userAccountBean S_Phone) {
		Session session = factory.getCurrentSession();
		List<studentBean> sb = null;
		studentBean studentbean = null;
		String hql = "FROM studentBean WHERE S_Phone=:s_Phone";
		sb = session.createQuery(hql).setParameter("s_Phone", S_Phone).getResultList();
		studentbean = (sb.isEmpty())?null:sb.get(0);
		return studentbean;
	}


	@SuppressWarnings("unchecked")
	@Override
	public studentBean queryStudent(Integer S_Id) {
		Session session = factory.getCurrentSession();
		List<studentBean> sb = null;
		studentBean studentbean = null;
		String hql = "FROM studentBean WHERE S_Id=:s_Id";
		sb = session.createQuery(hql).setParameter("s_Id", S_Id).getResultList();
		studentbean = (sb.isEmpty())?null:sb.get(0);
		return studentbean;
	}
}
