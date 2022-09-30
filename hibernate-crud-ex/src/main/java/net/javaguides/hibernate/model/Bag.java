package net.javaguides.hibernate.model;

import java.util.HashMap;

public class Bag {
	private HashMap<String, Object> map;
	
	public Bag()
	{
		map = new HashMap<String, Object>();
	}
	
	public boolean put(String key, Object value)
	{
		if(!map.containsKey(key))
		{
			map.put(key, value);
			return true;
		}
		return false;
	}
	
	public Object get(String key)
	{
		if(contains(key))
		{
			Object result = map.get(key);
			return result;
		}
		return null;
	}
	
	public boolean contains(String key)
	{
		return map.containsKey(key);
	}

	@Override
	public String toString() {
		return "Bag [map=" + map + "]";
	}
	

}
