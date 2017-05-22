package pl.kofun.mavis.io;

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
			
			for(int i=1; i<args.length;)
			{
				if(args[i].equalsIgnoreCase("-m") && i+1 < args.length)
				{
					options.put("monthtoPlot", args[i+1]);
					i+=2;
				}
				else if(args[i].equalsIgnoreCase("-b") && i+1 < args.length)
				{
					options.put("booksfileName", args[i+1]);
					i+=2;
				}
				else if(args[i].equalsIgnoreCase("-g") && i+1 < args.length)
				{
					options.put("gamesfileName", args[i+1]);
					i+=2;
				}
				else if(args[i].equalsIgnoreCase("-y") && i+1 < args.length)
				{
					options.put("yeartoPlot", args[i+1]);
					i+=2;
				}
				else if(args[i].equalsIgnoreCase("-s") && i+1 < args.length)
				{
					options.put("sourcefileName", args[i+1]);
					i+=2;
				}
				else if(args[i].equalsIgnoreCase("-t") && i+1 < args.length)
				{
					options.put("targetfileName", args[i+1]);
					i+=2;
				}
				else if(args[i].equalsIgnoreCase("-f") && i+1 < args.length)
				{
					options.put("filter", args[i+1]);
					i+=2;
				}
				else if(args[i].equalsIgnoreCase("-sf") && i+1 < args.length)
				{
					options.put("save", args[i+1]);
					i+=2;
				}
				else if(args[i].equalsIgnoreCase("-d"))
				{
					options.put("save", "default");
					++i;
				}
				else if(args[i].equalsIgnoreCase("-Burl") && i+1 < args.length)
				{
					options.put("blogUrl",args[i+1]);
					i+=2;
				}
				else if(args[i].equalsIgnoreCase("-Durl") && i+1 < args.length)
				{
					options.put("devUrl",args[i+1]);
					i+=2;
				}
				else if(args[i].equalsIgnoreCase("-p") && i+1 < args.length)
				{
					options.put("projectsfileName", args[i+1]);
					i+=2;
				}
				else if(args[i].equals("-n") && i+1 < args.length)
				{
					options.put("projectName", args[i+1]);
					i+=2;
				}
				else if(args[i].equals("-a"))
				{
					options.put("projectName", "All");
					++i;
				}
				else
				{
					++i;
				}
			}
		}
		
		return options;
	}
}
