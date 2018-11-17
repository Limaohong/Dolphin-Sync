package company.repository.Impl;

import java.sql.Blob;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import company.model.companyBean;
import company.repository.companyDao;
import useraccount.model.userAccountBean;
@Repository
public class companyDapImpl implements companyDao {
	@Autowired
	SessionFactory factory;
	@Override
	public Integer savecompany(companyBean cb) {
		Integer n = 0;
		Session session = factory.getCurrentSession();
		session.save(cb);
		n++;
		return n;
	}
	
	@Override
	public companyBean loadcompany_boss(String UA_VC) {
		companyBean compBean = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM companyBean WHERE C_VC=:uvc";
		compBean = (companyBean) session.createQuery(hql).setParameter("uvc", UA_VC).getSingleResult();
		
		return compBean;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public companyBean loadcompany_teacher(String UA_VC) {
		companyBean compBean = null;
		Session session = factory.getCurrentSession();		
		String hql = "FROM companyBean WHERE C_VC=:uvc";
		String C_VC = UA_VC;
		List<companyBean> list = session.createQuery(hql).setParameter("uvc", C_VC).getResultList();
		compBean = (list.isEmpty()?null:list.get(0));
		return compBean;
	}
	@SuppressWarnings("unchecked")
	@Override
	public companyBean loadcompany_parent(String UA_VC) {
		companyBean compBean = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM companyBean WHERE C_VC=:uvc";
		List<companyBean> list = session.createQuery(hql).setParameter("uvc", UA_VC).getResultList();
		compBean = (list.isEmpty()?null:list.get(0));
		return compBean;
	}
	

	@Override
	public int updatecom(companyBean cb, long sizeInBytes) {
		int n = 0;
		
		String hql = "UPDATE companyBean  SET C_CL = :cl ,C_CN = :cn, C_CP = :cp "
					+" WHERE C_VC = :vc";
		if(sizeInBytes == 0) {
			n = updatecom(cb);
			return n;
		}
		Session session = factory.getCurrentSession();
		
		session.createQuery(hql).setParameter("cl",cb.getC_CL())
									.setParameter("cn", cb.getC_CN())
									.setParameter("cp", cb.getC_CP())
									.setParameter("vc", cb.getC_VC())
									.executeUpdate();
		n++;
		return n;
	}

	@Override
	public int updatecom(companyBean cb) {
		int n = 0;
		
		String hql = "UPDATE companyBean  SET C_CN = :cn, C_CP = :cp "
					+" WHERE C_VC = :vc";
		
		Session session = factory.getCurrentSession();
		

		session.createQuery(hql).setParameter("cn", cb.getC_CN())
								.setParameter("cp", cb.getC_CP())
								.setParameter("vc", cb.getC_VC())
								.executeUpdate();
		n++;
		return n;
	}
	
	@SuppressWarnings("unchecked")
	public boolean CNExists(String name) {
		boolean exist = false;
		String hql = "From companyBean Where C_CN = :name";
		Session session = factory.getCurrentSession();
		List<userAccountBean> list = session.createQuery(hql)
									.setParameter("name", name)
									.getResultList();
		exist = (list.isEmpty())?false:true;
		return exist;
	}
	@SuppressWarnings("unchecked")
	public boolean CPExists(String phone){
		boolean exist = false;
		String hql = "From companyBean Where C_CP = :phone";
		Session session = factory.getCurrentSession();
		List<userAccountBean> list = session.createQuery(hql)
									.setParameter("phone", phone)
									.getResultList();
		exist = (list.isEmpty())?false:true;
		return exist;
	}
	@Override
	public int updateslide(companyBean cb,userAccountBean ub) {
		Integer n = 0;
		Session session = factory.getCurrentSession();
		String hql = "UPDATE companyBean  SET Slide_1 = :s1 ,Slide_2 = :s2,"
				+ "Slide_3 = :s3,Slide_4 = :s4,Slide_5 = :s5";
		companyBean querybean = loadcompany_boss(ub.getUA_VC());
		Blob Slide_1 = null;
		Blob Slide_2 = null;
		Blob Slide_3 = null;
		Blob Slide_4 = null;
		Blob Slide_5 = null;
		if(querybean.getSlide_1()!=null && cb.getSlide_1()==null) {
			Slide_1 = querybean.getSlide_1();
		}else {
			Slide_1 = cb.getSlide_1();
		}
		if(querybean.getSlide_2()!=null && cb.getSlide_2()==null) {
			Slide_2 = querybean.getSlide_2();
		}else {
			Slide_2 = cb.getSlide_2();
		}
		if(querybean.getSlide_3()!=null && cb.getSlide_3()==null) {
			Slide_3 = querybean.getSlide_3();
		}else {
			Slide_3 = cb.getSlide_3();
		}
		if(querybean.getSlide_4()!=null && cb.getSlide_4()==null) {
			Slide_4 = querybean.getSlide_4();
		}else {
			Slide_4 = cb.getSlide_4();
		}
		if(querybean.getSlide_5()!=null && cb.getSlide_5()==null) {
			Slide_5 = querybean.getSlide_5();
		}else {
			Slide_5 = cb.getSlide_5();
		}
		session.createQuery(hql).setParameter("s1", Slide_1)
								.setParameter("s2", Slide_2)
								.setParameter("s3", Slide_3)
								.setParameter("s4", Slide_4)
								.setParameter("s5", Slide_5)
								.executeUpdate();
		n++;
		return n;
	}

}

