package App;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.hibernate.dao.CommandExecuter;
import net.javaguides.hibernate.dao.CommandExecuterWBag;
import net.javaguides.hibernate.dao.Service;
import net.javaguides.hibernate.model.*;
import net.javaguides.hibernate.util.BatchExecuter;

public class Main {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException 
	{
		//Task1();
		
		//Task2();
		
		//Task3();
		
		//TASK 4 is requires to run the ServletWebTask project
		
		Task5();
		
	}
	
	public static void Task1() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		Customer customer = new Customer(10,"Tugcan", "Barbin", "10.07.1999", "Denizli");
		Address address = new Address(10,"Turkey" , "Istanbul", "Aydinevler");
		Phone phone= new Phone(10,"+90" , "5079750543");
		Account account= new Account(10, "" + ( 10* 101) , 10200);
		
		Service.saveData(customer);
		Service.saveData(address);
		Service.saveData(phone);
		Service.saveData(account);
		
		Customer customer2 = new Customer(11,"T", "B", "10.07.1999", "Denizli");
		Address address2 = new Address(customer2.getId(),"Turkey" , "Istanbul","Aydinevler"); 
		Phone phone2= new Phone(customer.getId(),"+90" ,"5079750543"); 
		Account account2= new Account(customer2.getId(), "" + (customer2.getId()* 101) , 10200); 
		Service.saveData(customer2);
		Service.saveData(address2); Service.saveData(phone2);
		Service.saveData(account2);
		  
		// Get customer
		Customer c = Service.getDataById(Customer.class,customer2.getId());
		System.out.println(c.getName());
		
		// Update customer
		customer2.setName("Tugcan2");
		Service.updateData(customer2);
		System.out.println("customer2's name changed to " +
		Service.getDataById(Customer.class,customer2.getId()).getName());
	
		// Get All Customers
	    System.out.println("Our current customers are : "); 
	    List<Customer> customers = Service.getAllData(Customer.class);
	    List<Address> addresses = Service.getAllData(Address.class);
	    if(addresses == null || customers == null)
	    {
		    System.out.println("list is empty"); 
	    } 
	    for(int i = 0; i < customers.size() && i < addresses.size(); i++) 
	    {
		    System.out.println(customers.get(i).toString());
		    System.out.println(addresses.get(i).toString()); 
	    }
	}
	
	public static void Task2()
	{
		List<Command> commandList = new ArrayList<Command>(); 
		commandList = CommandExecuter.ReadAllCommands();
		
		for(Command c : commandList) 
		{
			CommandExecuter.ExecuteCommand(c);
		}
	}
	
	public static void Task3()
	{
		List<Command> commandList = new ArrayList<Command>();
		commandList = CommandExecuter.ReadAllCommands();
		
		for(Command c : commandList) 
		{
			Bag bag = new Bag();

			if(c.getAlias().contains("Customer"))
			{
				bag.put("id", c.getId());
				bag.put("name","bagInput");
				bag.put("surname", "example");
				bag.put("bdate", "1.1.1.1");
				bag.put("bcountry", "ist");
			}
			else if(c.getAlias().contains("Account"))
			{
				bag.put("cust_id", c.getId());
				bag.put("accountNr","1231223");
				bag.put("balance", 101010);
			}
			else if(c.getAlias().contains("Address"))
			{
				bag.put("cust_id", c.getId());
				bag.put("country","turkey");
				bag.put("city", "istanbul");
				bag.put("street", "aydinevler");
			}
			else if(c.getAlias().contains("Phone"))
			{
				bag.put("cust_id", c.getId());
				bag.put("countryCode", "+090");
				bag.put("phoneNumber", "1111111111");
			}
			
			CommandExecuterWBag.ExecuteCommandWBag(c, bag);

		}
		
		
		
		
	}

	public static void Task5() throws NoSuchMethodException, SecurityException
	{
		List<BatchData> batchList = new ArrayList<BatchData>();
		batchList = Service.getAllBatchData();
		
		BatchExecuter batchExecuter = new BatchExecuter();
		int threadCount = 4;
		batchExecuter.Execute(threadCount, batchList);
		
		
	}
}
