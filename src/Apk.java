import java.io.IOException;
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
}
