package net.javaguides.hibernate.util;

import java.util.List;

import net.javaguides.hibernate.dao.Service;
import net.javaguides.hibernate.model.BatchData;

public class BatchExecuter {
	private int batchLength;
	private int maxCommitCount;
	private int remainder;
	

    public void Execute(int threadCount, List<BatchData> batch)
	{
		batchLength = batch.size();
		
		maxCommitCount = batchLength / threadCount;
		
		remainder = batchLength % threadCount;
		
		
		 for(int i = 0; i < threadCount; i++) 
		 {
			 List<BatchData> tempList = batch.subList(i*maxCommitCount, ((i+1) * maxCommitCount));
			 
			 new Thread(new BatchThread(
					 tempList,
					 maxCommitCount
					 )).start(); 
		 }
		 if(remainder > 0)
		 {
			 List<BatchData> tempList = batch.subList(batch.size()-remainder,batch.size());
			 
			 new Thread(new BatchThread(
					 tempList,
					 remainder
					 )).start();  
		 }

		
		
	}
}
