package useraccount.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import student.model.studentBean;
import studentclass.model.studentclassBean;
import useraccount.model.userAccountBean;
import useraccount.repository.userAccountDao;


@Repository("dao")
public class userAccountDaoImpl implements userAccountDao{
	
	@Autowired
	SessionFactory factory;
	
	public userAccountDaoImpl() {
		super();
	}



	@SuppressWarnings("unchecked")
	@Override
	public userAccountBean checkIDPassword(String userId, String password) {
		userAccountBean uab = null;
		String hql = "FROM userAccountBean u WHERE u.UA_Acu = :UA_Acu and u.UA_Psw = :UA_Psw";		
		Session session = factory.getCurrentSession();
			List<userAccountBean> list = session.createQuery(hql).setParameter("UA_Acu", userId).setParameter("UA_Psw", password)
					.getResultList();
			uab = (list.isEmpty() ? null : list.get(0));

		return uab;
	}
	@SuppressWarnings("unchecked")
	@Override
	public userAccountBean checkID(String UA_Acu) {
		userAccountBean uab = null;
		String hql = "FROM userAccountBean u WHERE u.UA_Acu = :UA_Acu ";		
		Session session = factory.getCurrentSession();
			List<userAccountBean> list = session.createQuery(hql)
					.setParameter("UA_Acu", UA_Acu)
					.getResultList();
			uab = (list.isEmpty() ? null : list.get(0));

		return uab;
	}
	@SuppressWarnings("unchecked")
	@Override
	public userAccountBean queryMember(String id) {
		userAccountBean mb = null;
		String hql = "FROM userAccountBean WHERE UA_Acu = :UA_Acu";
		Session session = factory.getCurrentSession();
			List<userAccountBean> list = session.createQuery(hql).setParameter("UA_Acu", id).getResultList();
			mb = (list.isEmpty() ? null : list.get(0));
		return mb;
	}



	@SuppressWarnings("unchecked")
	@Override
	public boolean idExists(String id) {
		boolean exist = false;
		String hql = "From userAccountBean Where UA_Acu = :acu";
		Session session = factory.getCurrentSession();
		List<userAccountBean> list = session.createQuery(hql)
									.setParameter("acu", id)
									.getResultList();
		exist = (list.isEmpty())?false:true;
		return exist;
	}



	@Override
	public Integer saveMember(userAccountBean ub) {
		Integer n = 0;
		Session session = factory.getCurrentSession();
		session.save(ub);
		n++;
		return n;
	}



	@Override
	public String VerificationCode(String UA_PL) {
		
		String who=UA_PL;
		String vc = "";
		String boss = "0";
		String teacher = "1";
		String parent = "2";
		if(who.equals(boss)) {
			vc+="B";
		}else if(who.equals(teacher)) {
			vc+="T";
		}else if(who.equals(parent)) {
			vc+="P";
		}
		for(int i=0;i<3;i++) {
			int n=(int)(Math.random()*26)+97;
			char c=(char)n;
			vc+=c;
			
		}
		for(int i=0;i<4;i++) {
			int n=(int)(Math.random()*10);
			vc+=Integer.toString(n);
		}
	return vc;
	}



	@SuppressWarnings("unchecked")
	@Override
	public userAccountBean checkVC(String UA_VC) {
		userAccountBean bean;
		Session session = factory.getCurrentSession();
		String hql = "FROM userAccountBean u WHERE u.UA_VC = :UA_vc";
		List<userAccountBean> list = session.createQuery(hql).setParameter("UA_vc", UA_VC).getResultList();
		bean = (list.isEmpty())?null:list.get(0);
		return bean;
	}

	@Override
	public int updatemem(userAccountBean ub, long sizeInBytes) {
		int n = 0;
		String hql = "UPDATE userAccountBean SET UA_Psw = :psw, UA_Name = :name, UA_Phone = :phone "
					+" ,UA_Avater = :ava WHERE UA_Acu = :acu";
		
		if(sizeInBytes ==0) {
			n = updatemem(ub);
			return n;
		}
		Session session = factory.getCurrentSession();
		n = session.createQuery(hql).setParameter("psw", ub.getUA_Psw())
								   .setParameter("name", ub.getUA_Name())
								   .setParameter("phone", ub.getUA_Phone())
								   .setParameter("ava", ub.getUA_Avater())
								   .setParameter("acu", ub.getUA_Acu())
								   .executeUpdate();
								   
		return n;
	}


	@Override
	public int updatemem(userAccountBean ub) {
		int n=0;
		Session session = factory.getCurrentSession();
		String hql = "UPDATE userAccountBean  SET UA_Psw = :psw, UA_Name = :name, UA_Phone = :phone "
					+" WHERE UA_Acu = :acu";
		n = session.createQuery(hql).setParameter("psw", ub.getUA_Psw())
									   .setParameter("name", ub.getUA_Name())
									   .setParameter("phone", ub.getUA_Phone())
									   .setParameter("acu", ub.getUA_Acu())
									   .executeUpdate();				             
		return n;
	}



	@SuppressWarnings("unchecked")
	@Override
	public boolean phoneExists(String phone) {
		boolean exist = false;
		String hql = "From userAccountBean Where UA_Phone = :phone";
		Session session = factory.getCurrentSession();
		List<userAccountBean> list = session.createQuery(hql)
									.setParameter("phone", phone)
									.getResultList();
		exist = (list.isEmpty())?false:true;
		return exist;
	}



	
	@Override
	public List<userAccountBean> findParentsName(List<studentBean> list) {
		
		Session session = factory.getCurrentSession();
		String hql = "FROM userAccountBean WHERE UA_Phone=:phone";
		userAccountBean ub = null;
		List<userAccountBean> ublist = new ArrayList<userAccountBean>();
		
		for(studentBean sb:list) {
			ub = (userAccountBean) session.createQuery(hql).setParameter("phone", sb.getS_Phone().getUA_Phone()).getSingleResult();
			ublist.add(ub);
		}
		
		
		
		
		
		
		
		
//		Session session = factory.getCurrentSession();
//		ArrayList<Object> list = new ArrayList<>();
//		Object[] ob = null;
//		List<userAccountBean> userbeanlist = new ArrayList<userAccountBean>();
//		userAccountBean userbean = new userAccountBean();
//		List<studentBean> studentbeanlist = new ArrayList<studentBean>();
//		studentBean studentbean = new studentBean();
//		String hql = "FROM userAccountBean ub Join studentBean sb on ub.UA_Phone=sb.S_Phone";
//		//找出家長(還未篩選班級)		
//		list = (ArrayList<Object>) session.createQuery(hql).getResultList();		
//		for(int i = 0;i<list.size();i++) {
//			ob = (Object[]) list.get(i);
//			for(int f = 0;f<ob.length;f++) {
//				if(ob[f].getClass()==userbean.getClass()) {
//					userbeanlist.add((userAccountBean) ob[f]);
//				}else if(ob[f].getClass()==studentbean.getClass()) {
//					studentbeanlist.add((studentBean) ob[f]);
//				}
//			}
//		}
////==========================================================================================		
//		//找出學生(有篩選班級)
//		List<studentBean> studentBeanlist2 = new ArrayList<studentBean>();
//		studentBean studentBean = new studentBean();
//		ArrayList<Object> list2 = new ArrayList<>();
//		List<studentclassBean> studentclassBeanlist = new ArrayList<studentclassBean>();
//		studentclassBean studentclassBean = new studentclassBean();
//		Object[] ob2 = null;
//		String hql2 = "FROM studentBean s join studentclassBean sc on s.S_Id=sc.SC_SI "
//				+ "WHERE sc.SC_CI=:scci";
//		list2 = (ArrayList<Object>) session.createQuery(hql2).setParameter("scci", Cr_Id).getResultList();
//		for(int i = 0; i<list2.size() ; i++) {
//			
//				ob2 =  (Object[]) list2.get(i);
//				for(int f = 0; f<ob2.length ;f++) {
//					if(ob2[f].getClass() == studentBean.getClass()) {
//						studentBeanlist2.add((student.model.studentBean) ob2[f]);
//					}else if (ob2[f].getClass() == studentclassBean.getClass()) {
//						studentclassBeanlist.add( (studentclass.model.studentclassBean) ob2[f]);
//					}				
//				}
//				
//			
//		}
////=================================================================================================
//		//找出班級內的學生其電話與家長相同者，取出放入新List
//		List<userAccountBean> userbeanlist2 = new ArrayList<userAccountBean>();
//		for(int i=0;i<userbeanlist.size();i++) {
//			for(int f = 0;f<studentBeanlist2.size();f++) {
//				if(studentBeanlist2.get(f).getS_Phone().equals(userbeanlist.get(i).getUA_Phone())){
//					userbeanlist2.add(userbeanlist.get(i));
//				}				
//			}
//		}
		
		
		
		
		return ublist;
	}







	
}
