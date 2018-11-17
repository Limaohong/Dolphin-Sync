package init;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialClob;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import bulletinboard.model.bulletinboardBean;
import classroom.model.classroomBean;
import company.model.companyBean;
import contactbook.model.contactbookBean;
import fraction.model.fractionBean;
import init.util.HibernateUtil;
import init.util.SystemUtils2018;
import student.model.studentBean;
import studentclass.model.studentclassBean;
import teacherpresentation.model.teacherpresentationBean;
import useraccount.model.userAccountBean;

public class ImportData {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元

	public static void main(String[] args) {
		int n = 0;

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String line = "";
			List<userAccountBean> userAccounts = new ArrayList<>();
			List<companyBean> companys = new ArrayList<>();
			List<classroomBean> classrooms = new ArrayList<>();
			List<studentBean> students = new ArrayList<>();
			// 2-2 由"data/bookCompany.dat"逐筆讀入BookCompany表格內的初始資料，
			// 然後依序新增到BookCompany表格中
			try (FileReader fr = new FileReader("data/userAccount.dat"); BufferedReader br = new BufferedReader(fr);) {
				while ((line = br.readLine()) != null) {
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] token = line.split("\\|");
					Integer uA_PL = Integer.parseInt(token[0].trim());
					String uA_Acu = token[1].trim();
					String uA_Psw = token[2].trim();
					String uA_Name = token[3].trim();
					String uA_Phone = token[4].trim();
					String uA_VC = token[5].trim();
					companyBean UA_CVC =null;
					if (uA_PL != 0) {
						for (companyBean cb : companys) {
							if (cb.getC_VC().equals(token[6].trim())) {
								UA_CVC=cb;
								break;
							}
						}
					}

					Blob uA_Avater = SystemUtils2018.fileToBlob(token[7].trim());
					userAccountBean uab = new userAccountBean(uA_PL, uA_Acu, uA_Psw, uA_Name, uA_Phone, uA_VC, UA_CVC,
							uA_Avater);

					session.save(uab);
					userAccounts.add(uab);
				}
				System.out.println("UserAccount 資料新增成功");
			} catch (IOException e) {
				System.err.println("新建UserAccount表格時發生IO例外: " + e.getMessage());
				e.printStackTrace();
			}

			File file = new File("data/company.dat");
			// 1-2 由"data/book.dat"逐筆讀入Book表格內的初始資料，然後依序新增
			// 到Book表格中
			try (FileInputStream fis = new FileInputStream(file);
					InputStreamReader isr = new InputStreamReader(fis, "UTF8");
					BufferedReader br = new BufferedReader(isr);) {

				while ((line = br.readLine()) != null) {
					n = 0;
					// 去除 UTF8_BOM: \uFEFF
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] token = line.split("\\|");
					companyBean cb = new companyBean();
					cb.setC_CL(SystemUtils2018.fileToBlob(token[0].trim()));
					cb.setC_CN(token[1].trim());
					cb.setC_CP(token[2].trim());
					cb.setC_VC(token[3].trim());
					for (int i = 4; i <= 8; i++) {
						Blob Slide = SystemUtils2018.fileToBlob(token[i].trim());
						switch (i) {
						case 4:
							cb.setSlide_1(Slide);
							break;
						case 5:
							cb.setSlide_2(Slide);
							break;
						case 6:
							cb.setSlide_3(Slide);
							break;
						case 7:
							cb.setSlide_4(Slide);
							break;
						case 8:
							cb.setSlide_5(Slide);
							break;
						}
					}
					session.save(cb);
					companys.add(cb);
					n++;
					System.out.println("新增一筆company紀錄是否成功=" + n);
				}
				// 印出資料新增成功的訊息
				System.out.println("company資料新增成功");
			} catch (IOException e) {
				System.err.println("新建company表格時發生IO例外: " + e.getMessage());
			} catch (SQLException e) {
				System.err.println("新建company表格時發生SQL例外: " + e.getMessage());
			}
			line = "";
			try (FileInputStream fis = new FileInputStream("data/teacherPresentation.txt");
					InputStreamReader isr0 = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr0);) {
				while ((line = br.readLine()) != null) {
					n = 0;
					// 去除 UTF8_BOM: \uFEFF
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] sa = line.split("\\|");
					teacherpresentationBean bean = new teacherpresentationBean();
					for (companyBean cb : companys) {
						if (cb.getC_CN().equals(sa[0].trim())) {
							bean.setTP_CId(cb);
							break;
						}
					}
					bean.setTP_Name(sa[1].trim());
					bean.setTP_Avater(SystemUtils2018.fileToBlob(sa[2].trim()));
					Clob clob = new SerialClob(sa[3].trim().toCharArray());
					bean.setTP_TI(clob);
					session.save(bean);
					n++;
					System.out.println("新增" + n + "筆記錄:" + sa[1]);
				}
				System.out.println("teacherpresentationBean資料新增成功");
			} catch (IOException e) {
				System.err.println("新建teacherpresentationBean表格時發生IO例外: " + e.getMessage());
			} catch (SQLException e) {
				System.err.println("新建teacherpresentationBean表格時發生SQL例外: " + e.getMessage());
			}
			line = "";
			try (FileInputStream fis = new FileInputStream("data/classroom.txt");
					InputStreamReader isr0 = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr0);) {
				while ((line = br.readLine()) != null) {
					n = 0;
					// 去除 UTF8_BOM: \uFEFF
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] sa = line.split("\\|");
					classroomBean bean = new classroomBean();
					bean.setCr_Name(sa[0].trim());
					for (userAccountBean uab : userAccounts) {
						if (uab.getUA_VC().equals(sa[1].trim())) {
							bean.setCr_VC(uab);
							break;
						}
					}
					session.save(bean);
					classrooms.add(bean);
					n++;
					System.out.println("新增" + n + "筆記錄:" + sa[1]);
				}
				System.out.println("classroomBean資料新增成功");
			} catch (IOException e) {
				System.err.println("新建classroomBean表格時發生IO例外: " + e.getMessage());
			}
			line = "";
			try (FileInputStream fis = new FileInputStream("data/student.txt");
					InputStreamReader isr0 = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr0);) {
				while ((line = br.readLine()) != null) {
					n = 0;
					// 去除 UTF8_BOM: \uFEFF
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] sa = line.split("\\|");
					studentBean bean = new studentBean();
					bean.setS_Name(sa[0].trim());
					for (userAccountBean uab : userAccounts) {
						if (uab.getUA_Phone().equals(sa[1].trim())) {
							bean.setS_Phone(uab);
							break;
						}
					}
					session.save(bean);
					students.add(bean);
					n++;
					System.out.println("新增" + n + "筆記錄:" + sa[1]);
				}
				System.out.println("studentBean資料新增成功");
			} catch (IOException e) {
				System.err.println("新建studentBean表格時發生IO例外: " + e.getMessage());
			}
			line = "";
			try (FileInputStream fis = new FileInputStream("data/studentClass.txt");
					InputStreamReader isr0 = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr0);) {
				while ((line = br.readLine()) != null) {
					n = 0;
					// 去除 UTF8_BOM: \uFEFF
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] sa = line.split("\\|");
					studentclassBean bean = new studentclassBean();

					for (classroomBean cb : classrooms) {
						if (cb.getCr_Name().equals(sa[0].trim())) {
							bean.setSC_CI(cb.getCr_Id());
							break;
						}
					}
					bean.setSC_CN(sa[0].trim());
					for (studentBean sb : students) {
						if (sb.getS_Name().equals(sa[1].trim())) {
							bean.setSC_SI(sb.getS_Id());
							break;
						}
					}
					bean.setSC_SN(sa[1].trim());
					session.save(bean);
					n++;
					System.out.println("新增" + n + "筆記錄:" + sa[1]);
				}
				System.out.println("studentClass資料新增成功");
			} catch (IOException e) {
				System.err.println("新建studentClass表格時發生IO例外: " + e.getMessage());
			}
			line = "";
			try (FileInputStream fis = new FileInputStream("data/contactBook.txt");
					InputStreamReader isr0 = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr0);) {
				while ((line = br.readLine()) != null) {
					n = 0;
					// 去除 UTF8_BOM: \uFEFF
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] sa = line.split("\\|");
					contactbookBean bean = new contactbookBean();

					for (studentBean sb : students) {
						if (sb.getS_Name().equals(sa[0].trim())) {
							bean.setCB_SId(sb);
							bean.setCB_CIT(new SerialClob((sb.getS_Name() + "隔天注意事項").toCharArray()));
							bean.setCB_SIT(new SerialClob((sb.getS_Name() + "說您好").toCharArray()));
							bean.setCB_RIT(new SerialClob((sb.getS_Name() + "回您好").toCharArray()));
							break;
						}
					}
					bean.setCB_File(SystemUtils2018.fileToBlob(sa[1].trim()));
					bean.setCB_FN(sa[2].trim());
					session.save(bean);
					n++;
					System.out.println("新增" + n + "筆記錄:" + bean.getCB_SId() + "contactbook");
				}
				System.out.println("contactbookBean資料新增成功");
			} catch (Exception e) {
				System.err.println("新建contactbookBean表格時發生例外: " + e.getMessage());
			}
			line = "";
			try (FileInputStream fis = new FileInputStream("data/bulletinBoard.txt");
					InputStreamReader isr0 = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr0);) {
				while ((line = br.readLine()) != null) {
					n = 0;
					// 去除 UTF8_BOM: \uFEFF
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] sa = line.split("\\|");
					bulletinboardBean bean = new bulletinboardBean();
					for (companyBean cb : companys) {
						if (cb.getC_CN().equals(sa[0].trim())) {
							bean.setBB_CId(cb);
							break;
						}
					}
					for (classroomBean cb : classrooms) {
						if (cb.getCr_Name().equals(sa[1].trim())) {
							bean.setBB_CrId(cb);
							break;
						}
					}
					bean.setBB_SN(sa[2].trim());
					bean.setBB_T1(sa[3].trim());
					bean.setBB_B1(new SerialClob(sa[4].trim().toCharArray()));
					bean.setBB_F1(SystemUtils2018.fileToBlob(sa[5].trim()));
					bean.setBB_FN1(sa[6].trim());
					
					bean.setBB_T2(sa[7].trim());
					bean.setBB_B2(new SerialClob(sa[8].trim().toCharArray()));
					bean.setBB_F2(SystemUtils2018.fileToBlob(sa[9].trim()));
					bean.setBB_FN2(sa[10].trim());
					
					bean.setBB_T3(sa[11].trim());
					bean.setBB_B3(new SerialClob(sa[12].trim().toCharArray()));
					bean.setBB_F3(SystemUtils2018.fileToBlob(sa[13].trim()));
					bean.setBB_FN3(sa[14].trim());
					
					bean.setBB_T4(sa[15].trim());
					bean.setBB_B4(new SerialClob(sa[16].trim().toCharArray()));
					bean.setBB_F4(SystemUtils2018.fileToBlob(sa[17].trim()));
					bean.setBB_FN4(sa[18].trim());
					
					bean.setBB_T5(sa[19].trim());
					bean.setBB_B5(new SerialClob(sa[20].trim().toCharArray()));
					bean.setBB_F5(SystemUtils2018.fileToBlob(sa[21].trim()));
					bean.setBB_FN5(sa[22].trim());
					session.save(bean);
					n++;
					System.out.println("新增" + n + "筆記錄:" + bean.getBB_SN() + "bulletinboard");
				}
				System.out.println("bulletinboardBean資料新增成功");
			} catch (Exception e) {
				System.err.println("新建bulletinboardBean表格時發生例外: " + e.getMessage());
			}
			line = "";
			try (FileInputStream fis = new FileInputStream("data/fraction.txt");
					InputStreamReader isr0 = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr0);) {
				while ((line = br.readLine()) != null) {
					n = 0;
					// 去除 UTF8_BOM: \uFEFF
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] sa = line.split("\\|");
					fractionBean bean = new fractionBean();

					for (studentBean sb : students) {
						if (sb.getS_Name().equals(sa[0].trim())) {
							bean.setF_SId(sb);
							break;
						}
					}
					bean.setF_Fraction(new BigDecimal(Math.random()*100));
					bean.setF_Subject(Integer.parseInt(sa[1].trim()));
					session.save(bean);
					n++;
					System.out.println("新增" + n + "筆記錄:" + sa[1]);
				}
				System.out.println("fractionBean資料新增成功");
			} catch (IOException e) {
				System.err.println("新建fractionBean表格時發生IO例外: " + e.getMessage());
			}
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			if (tx != null)
				tx.rollback();
		}
		session.close();
		factory.close();
	}
}
