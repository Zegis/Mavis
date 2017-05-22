package pl.kofun.mavis;

import pl.kofun.mavis.Interfaces.MainTask;
import pl.kofun.mavis.tasks.DefaultTask;
import pl.kofun.mavis.tasks.Library;
import pl.kofun.mavis.tasks.MonthPlotter;
import pl.kofun.mavis.tasks.ProjectsSummary;
import pl.kofun.mavis.tasks.YearPlotter;

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
			else if (options.get("Task").equals("Ps"))
			{
				return new ProjectsSummary(options); 
			}
		}
		
		return new DefaultTask();
	}
}
