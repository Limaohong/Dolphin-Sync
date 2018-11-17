package init.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
  
public class HibernateUtil {

	private static SessionFactory sessionFactory ;

	static {
		try {
			
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
	                .configure("hibernateOnly.cfg.xml").build();
	        Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
	        sessionFactory  = metadata.getSessionFactoryBuilder().build();
	         
		} catch (Throwable ex) {
			System.err.println("新建SessionFactory失敗:" + ex.getMessage());
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	// 外界呼叫此靜態方法來取的 SessionFactory物件
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}

}