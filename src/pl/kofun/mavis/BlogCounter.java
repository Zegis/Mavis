package pl.kofun.mavis;

public class BlogCounter {

	private BlogClient client;
	private static String countMonthFunction = "kofun.countForMonth";
	
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
	
	public int count(int yearNumber, int monthNumber)
	{
		int ret = 0;
					
		Object[] params = new Object[]{String.valueOf(yearNumber),String.valueOf(monthNumber)};
			
		ret = client.Call(countMonthFunction,params);
		
		return ret;
	}
}
