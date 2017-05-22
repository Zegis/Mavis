package pl.kofun.mavis.counters;

import pl.kofun.mavis.Interfaces.Counter;
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
	
	public void setPeriodToCount(PeriodToCount newPeriod)
	{
		this.yearNumber = newPeriod.Year;
		this.monthNumber = newPeriod.Month + 1; // There's difference in indexing between java and wodpress
	}
	
	public int count()
	{
		int ret = 0;
					
		Object[] params = new Object[]{String.valueOf(yearNumber),String.valueOf(monthNumber)};
			
		ret = client.Call(countMonthFunction,params);
		
		return ret;
	}
}
