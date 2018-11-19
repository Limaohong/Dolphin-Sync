package bulletinboard.repository.Impl;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bulletinboard.model.DemoboardBean;
import bulletinboard.model.bulletinboardBean;
import bulletinboard.repository.bulletinboardDao;
import classroom.model.classroomBean;
import company.model.companyBean;
import useraccount.model.userAccountBean;
@Repository
public class bulletinboardDaoImpl implements bulletinboardDao {
	@Autowired
	SessionFactory factory;
	@SuppressWarnings({ "unchecked"})	
	@Override
	public List<DemoboardBean> loadbulletinboard_boss(companyBean UA_Id) {
		String hql = "FROM bulletinboardBean WHERE BB_CId=:uaid";
		Session session = factory.getCurrentSession();
		List<bulletinboardBean> list = null;
		list = session.createQuery(hql).setParameter("uaid", UA_Id).getResultList();
		List<DemoboardBean> DemoboardBean = loadBoard(list);
		DemoboardBean = (DemoboardBean.isEmpty())?null:DemoboardBean;
		return DemoboardBean;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DemoboardBean> loadbulletinboard_teacher(companyBean BB_CrId) {
		Session session = factory.getCurrentSession();
		String hql = "FROM bulletinboardBean WHERE BB_CId=:cid";
		List<bulletinboardBean> list = null;
		list = session.createQuery(hql).setParameter("cid", BB_CrId).getResultList();
		List<DemoboardBean> DemoboardBean = loadBoard(list); 
		DemoboardBean = (DemoboardBean.isEmpty())?null:DemoboardBean;
		return DemoboardBean;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<DemoboardBean> loadbulletinboard_parent(companyBean BB_CrId) {
		Session session = factory.getCurrentSession();
		String hql = "FROM bulletinboardBean WHERE BB_CId=:cid";
		List<bulletinboardBean> list = null;
		list = session.createQuery(hql).setParameter("cid", BB_CrId).getResultList();
		List<DemoboardBean> DemoboardBean = loadBoard(list); 
		DemoboardBean = (DemoboardBean.isEmpty())?null:DemoboardBean;
		return DemoboardBean;
	}
	
	@Override
	public List<DemoboardBean> loadBoard(List<bulletinboardBean> bulletinboardBean) {
		//給其他方法轉資料用
		String BB_B1 = "";
		String BB_B2 = "";
		String BB_B3 = "";
		String BB_B4 = "";
		String BB_B5 = "";
		Integer BB_CrId = null;
		List<DemoboardBean> Demolist = new ArrayList<DemoboardBean>();
		List<bulletinboardBean> bb = bulletinboardBean;
		for(int i=0;i<bb.size();i++) {
			Clob clob = bb.get(i).getBB_B1();
			Clob clob2 = bb.get(i).getBB_B2();
			Clob clob3 = bb.get(i).getBB_B3();
			Clob clob4 = bb.get(i).getBB_B4();
			Clob clob5 = bb.get(i).getBB_B5();
			String BB_SN = bb.get(i).getBB_SN();
			String BB_T1 = bb.get(i).getBB_T1();
			String BB_T2 = bb.get(i).getBB_T2();
			String BB_T3 = bb.get(i).getBB_T3();
			String BB_T4 = bb.get(i).getBB_T4();
			String BB_T5 = bb.get(i).getBB_T5();
			Integer BB_Id = bb.get(i).getBB_Id();
			classroomBean classroombean = bb.get(i).getBB_CrId();
			if(classroombean !=null) {
				BB_CrId = bb.get(i).getBB_CrId().getCr_Id();
			}else {
				BB_CrId = null;
			}
				try {
					BB_B1 = clob.getSubString((long)1,(int)clob.length());
					BB_B2 = clob2.getSubString((long)1,(int)clob2.length());
					BB_B3 = clob3.getSubString((long)1,(int)clob3.length());
					BB_B4 = clob4.getSubString((long)1,(int)clob4.length());
					BB_B5 = clob5.getSubString((long)1,(int)clob5.length());
					DemoboardBean demo = new DemoboardBean(BB_B1,BB_B2,BB_B3,BB_B4,BB_B5,BB_Id,BB_CrId,BB_SN,BB_T1,BB_T2,BB_T3,BB_T4,BB_T5);
					Demolist.add(demo);
				} catch (SQLException e) {
					e.printStackTrace();
				}								
		}	
		return Demolist;
	}
	@Override
	public Integer insertBoard(bulletinboardBean bean) {
		Integer n = 0;
		Session session = factory.getCurrentSession();
		session.save(bean);
		n++;
		return n;
	}
	@Override
	public int updateboard(bulletinboardBean bb, long sizeInBytes,classroomBean CR_ID) {
		Integer n = 0;
		bulletinboardBean bb2 = null;
		Session session = factory.getCurrentSession();
		String hql = "UPDATE bulletinboardBean  SET BB_CId = :cid ,BB_CrId = :crid ,"
				+ "BB_SN = :sn ,BB_T1 = :t1 ,BB_B1 = :b1 ,BB_F1 = :f1 ,BB_FN1 = :fn1,"
				+ "BB_T2 = :t2 ,BB_B2 = :b2 ,BB_F2 = :f2 ,BB_FN2 = :fn2,"
				+ "BB_T3 = :t3 ,BB_B3 = :b3 ,BB_F3 = :f3 ,BB_FN3 = :fn3,"
				+ "BB_T4 = :t4 ,BB_B4 = :b4 ,BB_F4 = :f4 ,BB_FN4 = :fn4,"
				+ "BB_T5 = :t5 ,BB_B5 = :b5 ,BB_F5 = :f5,BB_FN5 = :fn5 WHERE BB_CrId=:crid";
		if(sizeInBytes ==0) {
			n = updateboard(bb,CR_ID);
			return n;
		}
		Blob BB_F1 = null;
		String BB_FN1 = "";
		Blob BB_F2 = null;
		String BB_FN2 = "";
		Blob BB_F3 = null;
		String BB_FN3 = "";
		Blob BB_F4 = null;
		String BB_FN4 = "";
		Blob BB_F5 = null;
		String BB_FN5 = "";
		bb2 = querysingleclassboard(bb.getBB_CrId());
		if(bb2.getBB_F1() != null && bb.getBB_F1() == null) {	//代表原本DB有放圖片、後來沒放新圖片
			BB_F1 = bb2.getBB_F1(); 		//繼續塞原本的
			BB_FN1 = bb2.getBB_FN1();
		} else if(bb.getBB_F1() != null){   //後來有放新圖片
			BB_F1 = bb.getBB_F1();			//塞新圖片給他
			BB_FN1 = bb.getBB_FN1();
		}
		
		if(bb2.getBB_F2() != null && bb.getBB_F2() == null) {	
			BB_F2 = bb2.getBB_F2();
			BB_FN2 = bb2.getBB_FN2();
		} else if(bb.getBB_F2() != null){   
			BB_F2 = bb.getBB_F2();
			BB_FN2 = bb.getBB_FN2();
		}
		
		if(bb2.getBB_F3() != null && bb.getBB_F3() == null) {	
			BB_F3 = bb2.getBB_F3();
			BB_FN3 = bb2.getBB_FN3();
		} else if(bb.getBB_F3() != null){   
			BB_F3 = bb.getBB_F3();
			BB_FN3 = bb.getBB_FN3();
		}
		
		if(bb2.getBB_F4() != null && bb.getBB_F4() == null) {	
			BB_F4 = bb2.getBB_F4();
			BB_FN4 = bb2.getBB_FN4();
		} else if(bb.getBB_F4() != null){   
			BB_F4 = bb.getBB_F4();
			BB_FN4 = bb.getBB_FN4();
		}
		
		if(bb2.getBB_F5() != null && bb.getBB_F5() == null) {	
			BB_F5 = bb2.getBB_F5();
			BB_FN5 = bb2.getBB_FN5();
		} else if(bb.getBB_F5() != null){   
			BB_F5 = bb.getBB_F5();
			BB_FN5 = bb.getBB_FN5();
		}
		
		session.createQuery(hql).setParameter("cid",bb.getBB_CId())
									.setParameter("crid",bb.getBB_CrId())
									.setParameter("sn",bb.getBB_SN())
									.setParameter("t1",bb.getBB_T1())
									.setParameter("b1",bb.getBB_B1())
									.setParameter("f1",BB_F1)
									.setParameter("fn1", BB_FN1)
									.setParameter("t2",bb.getBB_T2())
									.setParameter("b2",bb.getBB_B2())
									.setParameter("f2",BB_F2)
									.setParameter("fn2", BB_FN2)
									.setParameter("t3",bb.getBB_T3())
									.setParameter("b3",bb.getBB_B3())
									.setParameter("f3",BB_F3)
									.setParameter("fn3", BB_FN3)
									.setParameter("t4",bb.getBB_T4())
									.setParameter("b4",bb.getBB_B4())
									.setParameter("f4",BB_F4)
									.setParameter("fn4", BB_FN4)
									.setParameter("t5",bb.getBB_T5())
									.setParameter("b5",bb.getBB_B5())
									.setParameter("f5",BB_F5)
									.setParameter("fn5", BB_FN5)
									.setParameter("crid", CR_ID)
									.executeUpdate();
		
		
		n++;							
		return n;
	}
	@Override
	public int updateboard(bulletinboardBean bb,classroomBean CR_ID) {
		Integer n = 0;
		Session session = factory.getCurrentSession();
		String hql = "UPDATE bulletinboardBean  SET "
				+ "BB_SN = :sn ,BB_T1 = :t1 ,BB_B1 = :b1 ,"
				+ "BB_T2 = :t2 ,BB_B2 = :b2 ,"
				+ "BB_T3 = :t3 ,BB_B3 = :b3 ,"
				+ "BB_T4 = :t4 ,BB_B4 = :b4 ,"
				+ "BB_T5 = :t5 ,BB_B5 = :b5 WHERE BB_CrId = :crid";
		session.createQuery(hql).setParameter("crid",bb.getBB_CrId())
									.setParameter("sn",bb.getBB_SN())
									.setParameter("t1",bb.getBB_T1())
									.setParameter("b1",bb.getBB_B1())
									.setParameter("t2",bb.getBB_T2())
									.setParameter("b2",bb.getBB_B2())
									.setParameter("t3",bb.getBB_T3())
									.setParameter("b3",bb.getBB_B3())
									.setParameter("t4",bb.getBB_T4())
									.setParameter("b4",bb.getBB_B4())
									.setParameter("t5",bb.getBB_T5())
									.setParameter("b5",bb.getBB_B5())
									.setParameter("crid",CR_ID)
									.executeUpdate();
		n++;
		return n;
	}
	@SuppressWarnings("unchecked")
	@Override
	public bulletinboardBean querysingleclassboard(classroomBean BB_CrId) {
		List<bulletinboardBean> bb = null;
		bulletinboardBean bbb = null;
		String hql = "FROM bulletinboardBean WHERE BB_CrId=:crid";
		Session session = factory.getCurrentSession();
		bb = session.createQuery(hql).setParameter("crid", BB_CrId).getResultList();
		bb = (bb.isEmpty())?null:bb;
		bbb = bb.get(0);
		return bbb;
	}
	@Override
	public DemoboardBean loadsingleboard(bulletinboardBean bb) {
		String BB_B1 = "";
		String BB_B2 = "";
		String BB_B3 = "";
		String BB_B4 = "";
		String BB_B5 = "";
		DemoboardBean db = null;
		
			Clob clob = bb.getBB_B1();
			Clob clob2 = bb.getBB_B2();
			Clob clob3 = bb.getBB_B3();
			Clob clob4 = bb.getBB_B4();
			Clob clob5 = bb.getBB_B5();
			String BB_SN = bb.getBB_SN();
			String BB_T1 = bb.getBB_T1();
			String BB_T2 = bb.getBB_T2();
			String BB_T3 = bb.getBB_T3();
			String BB_T4 = bb.getBB_T4();
			String BB_T5 = bb.getBB_T5();
			Integer BB_Id = bb.getBB_Id();
			Integer BB_CrId = bb.getBB_CrId().getCr_Id();
				try {
					BB_B1 = clob.getSubString((long)1,(int)clob.length());
					BB_B2 = clob2.getSubString((long)1,(int)clob2.length());
					BB_B3 = clob3.getSubString((long)1,(int)clob3.length());
					BB_B4 = clob4.getSubString((long)1,(int)clob4.length());
					BB_B5 = clob5.getSubString((long)1,(int)clob5.length());
					db = new DemoboardBean(BB_B1,BB_B2,BB_B3,BB_B4,BB_B5,BB_Id,BB_CrId,BB_SN,BB_T1,BB_T2,BB_T3,BB_T4,BB_T5);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}								
			
				return db;
	}


	@SuppressWarnings("unchecked")
	@Override
	public boolean ifclassexist(classroomBean cr_Id) {
		List<bulletinboardBean> bb = null;
		boolean ans = false;
		Session session = factory.getCurrentSession();
		String hql = "FROM bulletinboardBean Where BB_CrId=:crid";
		bb = session.createQuery(hql).setParameter("crid", cr_Id).getResultList();
		if(bb.isEmpty()) {
			ans = false;
		}else {
			ans = true;
		}
		return ans;
	}


	@Override
	public Integer deleteBoard(classroomBean BB_CrId) {
		Session session = factory.getCurrentSession();
		String hql = "DELETE bulletinboardBean WHERE BB_CrId=:crid";
		Integer n= 0;
		session.createQuery(hql).setParameter("crid", BB_CrId).executeUpdate();
		n++;
		return n;
	}


	@Override
	public Integer deletecomBoard(Integer BB_Id) {
		Session session = factory.getCurrentSession();
		String hql = "DELETE bulletinboardBean WHERE BB_Id=:id";
		Integer n= 0;
		session.createQuery(hql).setParameter("id", BB_Id).executeUpdate();
		n++;
		return n;
	}


	@SuppressWarnings("unchecked")
	@Override
	public bulletinboardBean querycompanyboard(String BB_SN) {
		List<bulletinboardBean> bblist = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM bulletinboardBean WHERE BB_SN=:sn";
		bulletinboardBean bb = null;
		bblist = session.createQuery(hql).setParameter("sn", BB_SN).getResultList();
		bb = bblist!=null?bblist.get(0):null;
		return bb;
	}


	@Override
	public int updatecomboard(bulletinboardBean bb, long sizeInBytes, String BB_SN) {
		Integer n = 0;
		bulletinboardBean bb2 = null;
		Session session = factory.getCurrentSession();
		String hql = "UPDATE bulletinboardBean  SET BB_CId = :cid ,BB_CrId = :crid ,"
				+ "BB_SN = :sn ,BB_T1 = :t1 ,BB_B1 = :b1 ,BB_F1 = :f1 ,BB_FN1 = :fn1,"
				+ "BB_T2 = :t2 ,BB_B2 = :b2 ,BB_F2 = :f2 ,BB_FN2 = :fn2,"
				+ "BB_T3 = :t3 ,BB_B3 = :b3 ,BB_F3 = :f3 ,BB_FN3 = :fn3,"
				+ "BB_T4 = :t4 ,BB_B4 = :b4 ,BB_F4 = :f4 ,BB_FN4 = :fn4,"
				+ "BB_T5 = :t5 ,BB_B5 = :b5 ,BB_F5 = :f5,BB_FN5 = :fn5 WHERE BB_SN=:snold";
		if(sizeInBytes ==0) {
			n = updatecomboard(bb,BB_SN);
			return n;
		}
		Blob BB_F1 = null;
		String BB_FN1 = "";
		Blob BB_F2 = null;
		String BB_FN2 = "";
		Blob BB_F3 = null;
		String BB_FN3 = "";
		Blob BB_F4 = null;
		String BB_FN4 = "";
		Blob BB_F5 = null;
		String BB_FN5 = "";
		bb2 = querysingleclassboard(bb.getBB_CrId());
		if(bb2.getBB_F1() != null && bb.getBB_F1() == null) {	//代表原本DB有放圖片、後來沒放新圖片
			BB_F1 = bb2.getBB_F1(); 		//繼續塞原本的
			BB_FN1 = bb2.getBB_FN1();
		} else if(bb.getBB_F1() != null){   //後來有放新圖片
			BB_F1 = bb.getBB_F1();			//塞新圖片給他
			BB_FN1 = bb.getBB_FN1();
		}
		
		if(bb2.getBB_F2() != null && bb.getBB_F2() == null) {	
			BB_F2 = bb2.getBB_F2();
			BB_FN2 = bb2.getBB_FN2();
		} else if(bb.getBB_F2() != null){   
			BB_F2 = bb.getBB_F2();
			BB_FN2 = bb.getBB_FN2();
		}
		
		if(bb2.getBB_F3() != null && bb.getBB_F3() == null) {	
			BB_F3 = bb2.getBB_F3();
			BB_FN3 = bb2.getBB_FN3();
		} else if(bb.getBB_F3() != null){   
			BB_F3 = bb.getBB_F3();
			BB_FN3 = bb.getBB_FN3();
		}
		
		if(bb2.getBB_F4() != null && bb.getBB_F4() == null) {	
			BB_F4 = bb2.getBB_F4();
			BB_FN4 = bb2.getBB_FN4();
		} else if(bb.getBB_F4() != null){   
			BB_F4 = bb.getBB_F4();
			BB_FN4 = bb.getBB_FN4();
		}
		
		if(bb2.getBB_F5() != null && bb.getBB_F5() == null) {	
			BB_F5 = bb2.getBB_F5();
			BB_FN5 = bb2.getBB_FN5();
		} else if(bb.getBB_F5() != null){   
			BB_F5 = bb.getBB_F5();
			BB_FN5 = bb.getBB_FN5();
		}
		
		session.createQuery(hql).setParameter("cid",bb.getBB_CId())
									.setParameter("crid",bb.getBB_CrId())
									.setParameter("sn",bb.getBB_SN())
									.setParameter("t1",bb.getBB_T1())
									.setParameter("b1",bb.getBB_B1())
									.setParameter("f1",BB_F1)
									.setParameter("fn1", BB_FN1)
									.setParameter("t2",bb.getBB_T2())
									.setParameter("b2",bb.getBB_B2())
									.setParameter("f2",BB_F2)
									.setParameter("fn2", BB_FN2)
									.setParameter("t3",bb.getBB_T3())
									.setParameter("b3",bb.getBB_B3())
									.setParameter("f3",BB_F3)
									.setParameter("fn3", BB_FN3)
									.setParameter("t4",bb.getBB_T4())
									.setParameter("b4",bb.getBB_B4())
									.setParameter("f4",BB_F4)
									.setParameter("fn4", BB_FN4)
									.setParameter("t5",bb.getBB_T5())
									.setParameter("b5",bb.getBB_B5())
									.setParameter("f5",BB_F5)
									.setParameter("fn5", BB_FN5)
									.setParameter("snold", BB_SN)
									.executeUpdate();
		
		
		n++;							
		return n;
	}


	@Override
	public int updatecomboard(bulletinboardBean bb, String BB_SN) {
		Integer n = 0;
		Session session = factory.getCurrentSession();
		String hql = "UPDATE bulletinboardBean  SET "
				+ "BB_SN = :sn ,BB_T1 = :t1 ,BB_B1 = :b1 ,"
				+ "BB_T2 = :t2 ,BB_B2 = :b2 ,"
				+ "BB_T3 = :t3 ,BB_B3 = :b3 ,"
				+ "BB_T4 = :t4 ,BB_B4 = :b4 ,"
				+ "BB_T5 = :t5 ,BB_B5 = :b5 WHERE BB_SN = :snold";
		session.createQuery(hql).setParameter("sn",bb.getBB_SN())
									.setParameter("t1",bb.getBB_T1())
									.setParameter("b1",bb.getBB_B1())
									.setParameter("t2",bb.getBB_T2())
									.setParameter("b2",bb.getBB_B2())
									.setParameter("t3",bb.getBB_T3())
									.setParameter("b3",bb.getBB_B3())
									.setParameter("t4",bb.getBB_T4())
									.setParameter("b4",bb.getBB_B4())
									.setParameter("t5",bb.getBB_T5())
									.setParameter("b5",bb.getBB_B5())
									.setParameter("snold",BB_SN)
									.executeUpdate();
		n++;
		return n;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
