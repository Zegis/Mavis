package pl.kofun.mavis;

import pl.kofun.mavis.Interfaces.MainTask;

public class TaskFactory {

	public static MainTask CreateTask(Options options)
	{
		
		if(options.containsKey("Task"))
		{
			if(options.get("Task").equals("Ohil"))
			{
				return new Library(options);
			}
			else if(options.get("Task").equals("Mp"))
			{
				return new MonthPlotter(options);
			}
			else if (options.get("Task").equals("Yp"))
			{
				return new YearPlotter(options);
			}
		}
		
		return new DefaultTask();
	}
}
