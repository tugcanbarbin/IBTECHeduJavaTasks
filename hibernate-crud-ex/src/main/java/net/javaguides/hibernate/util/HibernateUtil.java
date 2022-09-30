package net.javaguides.hibernate.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import net.javaguides.hibernate.model.*;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory()
	{
		System.out.println("helo");
		if(sessionFactory == null)
		{
			try
			{
				Configuration configuration = new Configuration();
				
				//hibernate settings equivalent to hibernate.cfg.xml's properties
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "org.postgresql.Driver");
				settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
				settings.put(Environment.USER, "postgres");
				settings.put(Environment.PASS, "123123");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
				
				settings.put(Environment.SHOW_SQL, "false");
				
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				
				settings.put(Environment.HBM2DDL_AUTO, "update");
				
				configuration.setProperties(settings);
				
				configuration.addAnnotatedClass(Customer.class);
				configuration.addAnnotatedClass(Address.class);
				configuration.addAnnotatedClass(Phone.class);
				configuration.addAnnotatedClass(Command.class);
				configuration.addAnnotatedClass(Account.class);
				configuration.addAnnotatedClass(AccountTrx.class);
				configuration.addAnnotatedClass(BatchData.class);
				
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				System.out.println("session factory get successful");
			}
			catch (Exception e)
			{
				System.out.println("session factory get unsuccessful");

				e.printStackTrace();
			}
		}
		else
			System.out.println("session factory not null");
		return sessionFactory;

	}
}
