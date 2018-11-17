package teacherpresentation.repository.Impl;


import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import company.model.companyBean;
import teacherpresentation.model.DemoTeacher;
import teacherpresentation.model.teacherpresentationBean;
import teacherpresentation.repository.teacherpresentationDao;
import useraccount.model.userAccountBean;
@Repository
public class teacherpresentationDaoImpl implements teacherpresentationDao {
	
	@Autowired
	SessionFactory factory;
	
	@SuppressWarnings("unused")
	@Override
	public List<DemoTeacher> loadteachercontent(List<teacherpresentationBean> teacher) {
		List<teacherpresentationBean> teacherBean = teacher;
		List<DemoTeacher> DemoTeacher = new ArrayList<DemoTeacher>();		
		Integer TP_Id;
		companyBean TP_CId;
		String TP_Name;
		String TP_TI;
		for(int i=0;i<teacher.size();i++) {
			TP_Id = teacher.get(i).getTP_Id();
			TP_CId = teacher.get(i).getTP_CId();
			TP_Name = teacher.get(i).getTP_Name();
			TP_Id = teacher.get(i).getTP_Id();
			Clob clob = teacher.get(i).getTP_TI();
			Blob blob = teacher.get(i).getTP_Avater();
			
			try {
				TP_TI = clob.getSubString((long)1,(int)clob.length());
				DemoTeacher demo = new DemoTeacher(TP_Id,TP_CId,TP_Name,TP_TI,blob);
				DemoTeacher.add(demo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return DemoTeacher;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DemoTeacher> loadteacher_boss(companyBean TP_CId) {
		List<teacherpresentationBean> teacher = null;
		List<DemoTeacher> DemoTeacher = null;
		String hql = "FROM teacherpresentationBean where TP_CId=:cid";
		Session session = factory.getCurrentSession();
		teacher = session.createQuery(hql).setParameter("cid", TP_CId).getResultList();
		DemoTeacher = loadteachercontent(teacher);
		return DemoTeacher;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DemoTeacher> loadteacher_teacher(companyBean TP_CId) {
		List<teacherpresentationBean> teacher = null;
		List<DemoTeacher> DemoTeacher = null;
		String hql = "FROM teacherpresentationBean where TP_CId=:cid";
		Session session = factory.getCurrentSession();		
		teacher = session.createQuery(hql).setParameter("cid", TP_CId).getResultList();
		DemoTeacher = loadteachercontent(teacher);
		return DemoTeacher;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DemoTeacher> loadteacher_parent(companyBean TP_CId) {
		List<teacherpresentationBean> teacher = null;
		List<DemoTeacher> DemoTeacher = null;
		String hql2 = "FROM teacherpresentationBean where TP_CId=:id";
		Session session = factory.getCurrentSession();		
		teacher = session.createQuery(hql2).setParameter("id", TP_CId).getResultList();
		DemoTeacher = loadteachercontent(teacher);
		return DemoTeacher;
	}

	@Override
	public teacherpresentationBean queryteacher(Integer TP_Id) {
		teacherpresentationBean tpb = null;
		String hql = "FROM teacherpresentationBean where TP_Id=:id";
		Session session = factory.getCurrentSession();
		tpb = (teacherpresentationBean) session.createQuery(hql).setParameter("id", TP_Id).getSingleResult();
		return tpb;
	}

	@Override
	public Integer insertteacherpresentation(teacherpresentationBean tb) {
		Integer n = 0;
		Session session = factory.getCurrentSession();
		session.save(tb);
		n++;
		return n;
	}

	@Override
	public DemoTeacher loadsingleteacher(teacherpresentationBean tb) {

		companyBean TP_CId = tb.getTP_CId();

		companyBean cb = tb.getTP_CId();

		Integer TP_Id = tb.getTP_Id();
		String TP_Name = tb.getTP_Name();
		Clob TP_TI = tb.getTP_TI();
		Blob TP_Avater = tb.getTP_Avater();
		String TP_TI_S = null;
		try {
			TP_TI_S = TP_TI.getSubString((long)1, (int)TP_TI.length());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DemoTeacher dt = new DemoTeacher(TP_Id,cb,TP_Name,TP_TI_S,TP_Avater);
		return dt;
	}

	@Override
	public Integer updateteacher(teacherpresentationBean ub, long sizeInBytes) {
		Integer n = 0;
		Session session = factory.getCurrentSession();
		String hql = "UPDATE teacherpresentationBean SET TP_CId =:cid,TP_Name =:name,"
					+ "TP_TI =:clob,TP_Avater =:ava WHERE TP_Id = :id";
		if(sizeInBytes==0) {
			n = updateteacher(ub);
			return n;
		}
		session.createQuery(hql).setParameter("cid", ub.getTP_CId())
								.setParameter("name", ub.getTP_Name())
								.setParameter("clob", ub.getTP_TI())
								.setParameter("ava", ub.getTP_Avater())
								.setParameter("id", ub.getTP_Id())
								.executeUpdate();
		n++;
		return n;
	}

	@Override
	public Integer updateteacher(teacherpresentationBean ub) {
		Integer n = 0;
		Session session = factory.getCurrentSession();
		String hql = "UPDATE teacherpresentationBean SET TP_CId =:cid,TP_Name =:name,"
					+ "TP_TI =:clob WHERE TP_Id = :id";
		
		session.createQuery(hql).setParameter("cid", ub.getTP_CId())
								.setParameter("name", ub.getTP_Name())
								.setParameter("clob", ub.getTP_TI())
								.setParameter("id", ub.getTP_Id())
								.executeUpdate();
		n++;
		return n;
	}

	@Override
	public Integer deleteteacherpresentation(Integer TP_Id) {
		Session session = factory.getCurrentSession();
		String hql = "DELETE teacherpresentationBean WHERE TP_Id=:id";
		Integer n = 0;
		session.createQuery(hql).setParameter("id", TP_Id).executeUpdate();
		n++;
		return n;
	}

}
