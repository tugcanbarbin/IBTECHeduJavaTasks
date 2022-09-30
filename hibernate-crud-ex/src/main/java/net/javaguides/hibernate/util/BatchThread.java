package net.javaguides.hibernate.util;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import net.javaguides.hibernate.dao.Service;
import net.javaguides.hibernate.model.AccountTrx;
import net.javaguides.hibernate.model.BatchData;

public class BatchThread  implements Runnable {
	private static ReentrantLock lock = new ReentrantLock();
	private List<BatchData> batchlist;
	private int commitCount;
	
	public BatchThread(List<BatchData> batchlist, int commitCount) {
		super();
		this.batchlist = batchlist;
	}

	public void run()
	{
		for(BatchData b : batchlist)
		{
			int id = b.getId();
			String type = b.getTransactionType();
			String accountnr = b.getAccountNr();
			
			/////TODO: REMOVE COMMENT FOR UPDATING THE STATUS OF BATCH
			//b.setStatus(1);
			
			lock.lock();
			try
			{
				int amount = b.getAmount();
				
				if(updateAccount(accountnr,type,amount))
				{
					updateBatch(b);
				}
			}
			catch(Exception e)
			{
				System.out.println("lock in thread failed");
				System.out.println(e);
			}
			lock.unlock();
		}
	}

	private void updateBatch(BatchData b) {
		
		try
		{
			Service.updateData(b);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private boolean updateAccount(String id, String type, int amount) {
		
		try {
			AccountTrx acc = new AccountTrx();
			acc = Service.getDataById(AccountTrx.class, id);
			
			if(type.equals("A")) // alacak
			{
				acc.setBalance(acc.getBalance() + amount );
			}
			else if(type.equals("B")) // borc
			{
				acc.setBalance(acc.getBalance() - amount );
			}
			
			Service.updateData(acc);
			
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
