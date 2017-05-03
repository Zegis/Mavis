package pl.kofun.mavis.counters;

import pl.kofun.mavis.utils.BlogClient;

public class BlogCounter implements Counter {

	private BlogClient client;
	private static String countMonthFunction = "kofun.countForMonth";
	int yearNumber;
	int monthNumber;
	
	public BlogCounter()
	{
		client = new BlogClient();
	}
	
	public BlogCounter(String blogUrl)
	{
		this();
		client.Configure(blogUrl);
	}
	
	public void setBlogUrl(String blogUrl)
	{
		client.Configure(blogUrl);
	}
	
	public void setPeriodToCount(int yearNumber, int monthNumber)
	{
		this.yearNumber = yearNumber;
		this.monthNumber = monthNumber;
	}
	
	public int count()
	{
		int ret = 0;
					
		Object[] params = new Object[]{String.valueOf(yearNumber),String.valueOf(monthNumber)};
			
		ret = client.Call(countMonthFunction,params);
		
		return ret;
	}
}
