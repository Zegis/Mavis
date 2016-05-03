package pl.kofun.mavis;

import java.util.Hashtable;

import pl.kofun.mavis.Interfaces.OptionsParser;

public class CommandLineParser implements OptionsParser{

	Hashtable<String, String> options;
	
	public CommandLineParser()
	{
		options = new Hashtable<String,String>();
	}
	
	public CommandLineParser(Hashtable<String,String> opt)
	{
		options = opt;
	}
	
	public Hashtable<String,String> load(String... args)
	{
		return parseArguments(args);
	}
	
	private Hashtable<String, String> parseArguments(String args[])
	{		
		if(args.length > 0)
		{
			options.put("Task", args[0]);
			
			if( (args.length-1)%2 == 0)
			{
				for(int i=1; i<args.length;)
				{
					if(args[i].equalsIgnoreCase("-m"))
					{
						options.put("monthtoPlot", args[i+1]);
						i+=2;
					}
					else if(args[i].equalsIgnoreCase("-b"))
					{
						options.put("booksfileName", args[i+1]);
						i+=2;
					}
					else if(args[i].equalsIgnoreCase("-g"))
					{
						options.put("gamesfileName", args[i+1]);
						i+=2;
					}
					else if(args[i].equalsIgnoreCase("-y"))
					{
						options.put("yeartoPlot", args[i+1]);
						i+=2;
					}
					else if(args[i].equalsIgnoreCase("-s"))
					{
						options.put("sourcefileName", args[i+1]);
						i+=2;
					}
					else if(args[i].equalsIgnoreCase("-t"))
					{
						options.put("targetfileName", args[i+1]);
						i+=2;
					}
					else if(args[i].equalsIgnoreCase("-f"))
					{
						options.put("filter", args[i+1]);
						i+=2;
					}
					else if(args[i].equalsIgnoreCase("-sf"))
					{
						options.put("save", args[i+1]);
						i+=2;
					}
					else if(args[i].equalsIgnoreCase("-d"))
					{
						options.put("save", "default");
						++i;
					}
					else
					{
						++i;
					}
				}
			}
		}
		
		return options;
	}
}
