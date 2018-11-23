package fraction.repository.Impl;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fraction.model.fractionBean;
import fraction.model.fractionDemo;
import fraction.repository.FractionDao;
import student.model.studentBean;
import student.service.StudentService;
import student.service.Impl.StudentServiceImpl;
import studentclass.model.studentclassBean;
@Repository
public class FractionDaoImpl implements FractionDao {

	@Autowired
	SessionFactory factory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<fractionDemo> loadFraction(studentBean F_SId) {
		Session session = factory.getCurrentSession();
		String hql = "FROM fractionBean WHERE F_SId=:sid";
		List<fractionBean> list = null;
		List<fractionDemo> listdemo = new ArrayList<fractionDemo>();		
		Timestamp ts = null;
		String date = null;
		list = session.createQuery(hql).setParameter("sid", F_SId).getResultList();
		list = (list!=null)?list:null;
		
		for(fractionBean f:list) {
			fractionDemo fd = new fractionDemo();
			ts = f.getF_RD();
			DateFormat sdf = new SimpleDateFormat("yyyy'年 'MM'月 'dd'日'");
			date = sdf.format(ts);
			fd.setF_RD(date);
			Integer F_Fraction_num = f.getF_Subject();
			String F_Fraction = null;
			if(F_Fraction_num.equals(1)) {
				F_Fraction = "國文";
			}else if(F_Fraction_num.equals(2)) {
				F_Fraction = "英文";
			}else if(F_Fraction_num.equals(3)) {
				F_Fraction = "數學";
			}
			fd.setF_Subject(F_Fraction);
			fd.setF_Exam(f.getF_Exam());
			fd.setF_Fraction(f.getF_Fraction());
			fd.setF_SId(f.getF_SId());
			listdemo.add(fd);
		}
		return listdemo;
	}

	@Override
	public Integer FractionInsert(fractionBean bean) {
		Integer n =0;
		Session session = factory.getCurrentSession();
		session.save(bean);
		n++;
		return n;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<fractionDemo> loadFraction_teacher(List<studentclassBean> bean) {
		Session session = factory.getCurrentSession();
		String hql = "FROM fractionBean WHERE F_SId=:sid";
		List<fractionBean> list = null;
		List<fractionDemo> listdemo = new ArrayList<fractionDemo>();
		
		Timestamp ts = null;
		String date = null;
		for(studentclassBean scb:bean) {
			studentBean studentbean = null;			
			studentbean = queryStudent(scb.getSC_SI().getS_Id());
			list = session.createQuery(hql).setParameter("sid", studentbean).getResultList();
			list = (list!=null)?list:null;		
		
			for(fractionBean f:list) {
				fractionDemo fd = new fractionDemo();
				ts = f.getF_RD();
				DateFormat sdf = new SimpleDateFormat("yyyy'年 'MM'月 'dd'日'");
				date = sdf.format(ts);
				fd.setF_RD(date);
				Integer F_Fraction_num = f.getF_Subject();
				String F_Fraction = null;
				if(F_Fraction_num.equals(1)) {
					F_Fraction = "國文";
				}else if(F_Fraction_num.equals(2)) {
					F_Fraction = "英文";
				}else if(F_Fraction_num.equals(3)) {
					F_Fraction = "數學";
				}
				fd.setF_Subject(F_Fraction);
				fd.setF_Exam(f.getF_Exam());
				fd.setF_Fraction(f.getF_Fraction());
				fd.setF_SId(f.getF_SId());
				listdemo.add(fd);
			}
		}
		return listdemo;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<fractionDemo> loadFractionlook_teacher(List<studentclassBean> bean, String exam) {
		Session session = factory.getCurrentSession();
		String hql = "FROM fractionBean WHERE F_SId=:sid AND F_Exam=:exam";
		List<fractionBean> list = null;
		List<fractionDemo> listdemo = new ArrayList<fractionDemo>();
		
		Timestamp ts = null;
		String date = null;
		for(studentclassBean scb:bean) {
			studentBean studentbean = null;			
			studentbean = queryStudent(scb.getSC_SI().getS_Id());
			list = session.createQuery(hql).setParameter("sid", studentbean).setParameter("exam", exam).getResultList();
			list = (list!=null)?list:null;		
		
			for(fractionBean f:list) {
				fractionDemo fd = new fractionDemo();
				ts = f.getF_RD();
				DateFormat sdf = new SimpleDateFormat("yyyy'年 'MM'月 'dd'日'");
				date = sdf.format(ts);
				fd.setF_RD(date);
				Integer F_Fraction_num = f.getF_Subject();
				String F_Fraction = null;
				if(F_Fraction_num.equals(1)) {
					F_Fraction = "國文";
				}else if(F_Fraction_num.equals(2)) {
					F_Fraction = "英文";
				}else if(F_Fraction_num.equals(3)) {
					F_Fraction = "數學";
				}
				fd.setF_Subject(F_Fraction);
				fd.setF_Exam(f.getF_Exam());
				fd.setF_Fraction(f.getF_Fraction());
				fd.setF_SId(f.getF_SId());
				listdemo.add(fd);
			}
		}
		return listdemo;
	}

}
