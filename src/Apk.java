import pl.kofun.mavis.Options;
import pl.kofun.mavis.Interfaces.MainTask;
import pl.kofun.mavis.TaskFactory;


public class Apk{

	public static void main(String args[])
	{		
		Options options = new Options(args);			
		
		MainTask task = TaskFactory.CreateTask(options);
		task.execute();
		
		if(options.containsKey("save"))
		{
			options.save();
		}
	}
}
