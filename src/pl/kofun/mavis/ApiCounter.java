package pl.kofun.mavis;

import java.util.Scanner;

public class ApiCounter {

	private BlogClient client;
	private static String countMonthFunction = "posts.countByMonth";
	
	public ApiCounter()
	{
		client = new BlogClient();
	}
	
	public ApiCounter(String blogUrl)
	{
		this();
		client.Configure(blogUrl);
	}
	
	public void setBlogUrl(String blogUrl)
	{
		client.Configure(blogUrl);
	}
	
	public int countPostsByMonth(int monthNumber)
	{
		int ret = 0;
		
		// Because in java January is 0, and our API assumes that January is 1.
		++monthNumber;
		
		System.out.print("Enter login:");
		String log = getData();
			
		System.out.print("Enter password:");
		String pswd = getData();		
			
		String blogid = "1";
			
		String monthnum = String.valueOf(monthNumber);
					
		Object[] params = new Object[]{blogid, log, pswd, monthnum};
			
		ret = client.Call(countMonthFunction,params);
		
		return ret;
	}
	
	private static String getData()
	{
		String ret;
		
		Scanner input = new Scanner(System.in); // is not closed because it'd close System.in too. Let VM handle it.
		
		ret = input.nextLine();
		
		return ret;
	}
}
