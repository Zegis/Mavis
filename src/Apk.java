import java.io.IOException;
import java.util.Scanner;

import pl.kofun.mavis.BlogClient;
import pl.kofun.mavis.DefaultTask;
import pl.kofun.mavis.Library;
import pl.kofun.mavis.MonthPlotter;
import pl.kofun.mavis.YearPlotter;
import pl.kofun.mavis.Interfaces.MainTask;
import pl.kofun.mavis.Options;

public class Apk{

	public static void main(String args[]) throws IOException
	{		
		MainTask task;
		Options options = new Options(args);			
			
		if(options.containsKey("Task"))
		{
			if(options.get("Task").equals("tmp") && options.containsKey("blogUrl"))
			{
				BlogClient client = new BlogClient();
				client.Configure(options.get("blogUrl"));
				
				System.out.print("Enter login:");
				String log = getPassword();
				
				System.out.print("Enter password:");
				String pswd = getPassword();		
				
				String blogid = "1";
				
				String monthnum = "5";
						
				Object[] params = new Object[]{blogid, log, pswd, monthnum};
				
				client.Call("posts.countByMonth",params);
			}
			
			if(options.get("Task").equals("Ohil"))
			{
				task = new Library(options);
			}
			else if(options.get("Task").equals("Mp"))
			{
				task = new MonthPlotter(options);
			}
			else if (options.get("Task").equals("Yp"))
			{
				task = new YearPlotter(options);
			}
			else
			{
				task = new DefaultTask();
			}
		}
		else
		{
			task = new DefaultTask();
		}
		task.execute();
		
		if(options.containsKey("save"))
		{
			options.save();
		}
	}
	
	private static String getPassword()
	{
		String ret;
		
		Scanner input = new Scanner(System.in); // is not closed because it'd close System.in too. Let VM handle it.
		
		ret = input.nextLine();
		
		return ret;
	}
}
