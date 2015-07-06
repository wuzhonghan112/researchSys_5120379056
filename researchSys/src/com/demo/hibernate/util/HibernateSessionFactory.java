package com.demo.hibernate.util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
    
	private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	private static final Configuration cfg = new Configuration();
	private static org.hibernate.SessionFactory sessionFactory;
	//ȡ��session
	public static Session currentSession() throws HibernateException{
		Session session = (Session) threadLocal.get();
		if(session == null){
			if(sessionFactory == null){
				try{
					cfg.configure(CONFIG_FILE_LOCATION);
					sessionFactory = cfg.buildSessionFactory();
				}
				catch(Exception e){
					System.err.println("%%%% Error Creating SessionFactory %%%%");
					e.printStackTrace();
				}
			}
			session = sessionFactory.openSession();
			threadLocal.set(session);
		}
		return session;
	}
	//�ر�Session����
	public static void closeSession() throws HibernateException{
		Session session = (Session)threadLocal.get();
		threadLocal.set(null);
		if(session != null){
			session.close();
		}
	}
}
