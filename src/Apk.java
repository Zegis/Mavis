import java.io.IOException;
import java.util.Hashtable;

import pl.kofun.mavis.ArgumentParser;
import pl.kofun.mavis.DefaultTask;
import pl.kofun.mavis.Library;
import pl.kofun.mavis.MainTask;
import pl.kofun.mavis.MonthPlotter;
import pl.kofun.mavis.YearPlotter;

public class Apk{

	public static void main(String args[]) throws IOException
	{		
		MainTask task;
		if(args.length > 0)
		{
			ArgumentParser parser = new ArgumentParser();
			Hashtable<String, String> options = parser.parseArguments(args);
			
			
			
			if(options.containsKey("Task"))
			{
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
		}
		else
		{
			task = new DefaultTask();
			task.execute();
		} 
	}
}
