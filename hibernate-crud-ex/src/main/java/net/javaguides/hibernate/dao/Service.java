package net.javaguides.hibernate.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import net.javaguides.hibernate.model.BatchData;
import net.javaguides.hibernate.model.IModel;
import net.javaguides.hibernate.util.HibernateUtil;

public class Service{
	public static ReentrantLock lock = new ReentrantLock();
	public static <T extends IModel> boolean saveData(T data)
	{
		Transaction transaction = null; 
		
		try
		{
			System.out.println("save started123");
			SessionFactory sesFactory = HibernateUtil.getSessionFactory();
			System.out.println("factory is taken");
			Session session = sesFactory.openSession();
			System.out.println("session started");
			transaction = session.beginTransaction();
			System.out.println("transaction started");
			session.save(data);
			System.out.println("commit started");
			transaction.commit();
			
			System.out.println(data.getClass().getSimpleName() +" is saved succesfully");
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e);
			if(transaction != null)
				transaction.rollback();
		}
		return false;
	}
	
	public static <T extends IModel> boolean updateData(T data)
	{
		
		Transaction transaction = null; 
		try
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			session.saveOrUpdate(data);
			
			transaction.commit();
			System.out.println(data.getClass().getSimpleName() +" is updated succesfully");
			return true;
		}
		catch(Exception e)
		{
			if(transaction != null)
				transaction.rollback();
		}

		
		return false;
	}
	public static <T> T getDataById(Class<T> obj,String id) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		Transaction transaction = null; 
		
		T instance = null;
		
		try
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			System.out.println("Getting " + obj.getSimpleName() + " by id: " + id);
			instance =  (T) session.get(obj, id);
			transaction.commit();
		}
		catch(Exception e)
		{
			System.out.println("exception occured");
			if(transaction != null)
				transaction.rollback();
		}
		
		return instance;
	}
	
	public static <T> T getDataById(Class<T> obj,int id) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		Transaction transaction = null; 
		
		T instance = null;
		
		try
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			System.out.println("Getting " + obj.getSimpleName() + " by id: " + id);
			instance =  (T) session.get(obj, id);
			transaction.commit();
		}
		catch(Exception e)
		{
			System.out.println("exception occured");
			if(transaction != null)
				transaction.rollback();
		}
		
		return instance;
	}
	
	public static <T> List<T> getAllData(Class<T> obj) throws NoSuchMethodException, SecurityException
	{
		Transaction transaction = null; 
		List<T> resultList = null;
		
		try
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			System.out.println("Getting List " + obj.getSimpleName());
			
			resultList =  (List<T>) session.createQuery("from " + obj.getSimpleName(),obj).getResultList();
			transaction.commit();
		}
		catch(Exception e)
		{
			System.out.println("exception occured");
			if(transaction != null)
				transaction.rollback();
		}
			
		return resultList;
	}
	
	public static List<BatchData> getAllBatchData() throws NoSuchMethodException, SecurityException
	{
		Transaction transaction = null; 
		List<BatchData> resultList = null;
		try
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			resultList = session.createQuery("from BatchData where status = :status").setParameter("status", 0).getResultList();
			transaction.commit();
		}
		catch(Exception e)
		{
			System.out.println("exception occured");
			if(transaction != null)
				transaction.rollback();
		}
		return resultList;
	}

	public static <T> boolean deleteDataById(Class<T> obj,int id)
	{
		Transaction transaction = null; 
		T data = null;
		try
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			System.out.println("Deleting data of type " + obj.getSimpleName() + " with the id: " + id);
			
			data =  session.get(obj, id);
			session.delete(data);
			
			transaction.commit();
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception occured");
			if(transaction != null)
				transaction.rollback();
		}
		return false;
	}
}
