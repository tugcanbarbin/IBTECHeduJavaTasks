package net.javaguides.hibernate.dao;

import java.lang.reflect.Array;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import net.javaguides.hibernate.model.Command;
import net.javaguides.hibernate.model.IModel;
import net.javaguides.hibernate.util.HibernateUtil;

public class CommandExecuter {
	
	public CommandExecuter() {}
	
	public static List<Command> ReadAllCommands()
	{
		Transaction transaction = null; 
		List<Command> result = new ArrayList<Command>();
		try
		{
			System.out.println("reading commands started");
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			result = session.createQuery("from " +Command.class.getSimpleName() ).getResultList();
			transaction.commit();
			
			System.out.println("Commands are read successfully");
			System.out.println();
			System.out.println();
			System.out.println();
		}
		catch(Exception e)
		{
			System.out.println(e);
			if(transaction != null)
				transaction.rollback();
		}
		return result;
	}
	
	public static Command ReadCommand(String name)
	{
		Transaction transaction = null; 
		Command result = new Command();
		try
		{
			System.out.println("reading specific command started");
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			String hql = "FROM Command C WHERE C.name = :commandname";
			Query query = session.createQuery(hql);
			query.setParameter("commandname",name);
			List results = query.list();
			
			if(results.size()>0)
				result = (Command) results.get(0);
			
			transaction.commit();
			
			System.out.println("Commands are read successfully");
		}
		catch(Exception e)
		{
			System.out.println(e);
			if(transaction != null)
				transaction.rollback();
		}
		return result;
	}

	public static boolean ExecuteCommand(Command command)
	{
		String packageName = command.getPackageName();
		String methodName = command.getMethodName();
		String type = command.getParameterType();
		int id = command.getId();
		
		System.out.println(command.getAlias()+ " will be executed with method " + methodName + " in the " + packageName +  " with the type of " + type);
		
		try
		{
			// get dao class
			Class<?> classType = Class.forName(packageName);
			Class<?> dataType = Class.forName("net.javaguides.hibernate.model." + type);
			Constructor<?> constructor = classType.getDeclaredConstructor();
			
			Object obj = constructor.newInstance();
			
			// get given data type
			Method method = classType.getDeclaredMethod(methodName, IModel.class);

			List<Field> list = Arrays.asList(dataType.getDeclaredFields());
			Constructor<?> dataConstructor = dataType.getDeclaredConstructor();
			Object data = dataConstructor.newInstance();
			
			// assign data
			for(Field f : list)
			{
				f.setAccessible(true);
				if(f.getType() == String.class)
				{
					f.set(data, "This is string data"); 
				}
				else if(f.getType() == int.class)
				{
					if(f.toString().contains("id"))
						f.set(data, id);
					else
						f.set(data, 10000);
				}
				
				
			}
			System.out.println("With " + data.toString());

			// execute the dao with the data
			method.invoke(obj, data);
			System.out.println("Completed..");
			System.out.println();
			System.out.println();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return false;
	}
	
}
