package pl.kofun.mavis;

import java.util.Hashtable;

import pl.kofun.mavis.Interfaces.OptionsParser;
import pl.kofun.mavis.Interfaces.OptionsSaver;
import pl.kofun.mavis.io.CommandLineOptionsParser;
import pl.kofun.mavis.io.FileOptionsParser;
import pl.kofun.mavis.io.FileOptionsSaver;

public class Options {

	private Hashtable<String, String> options;
	private OptionsParser parser;
	private OptionsSaver saver;
	
	public Options(String[] args)
	{
		options = loadDefaults();
		
		if(("-l").equalsIgnoreCase(args[0]))
		{
			parser = new FileOptionsParser(options);
		}
		else
			parser = new CommandLineOptionsParser(options);
		
		parser.load(args);
		
		saver = new FileOptionsSaver();
	}

	private Hashtable<String, String> loadDefaults()
	{
		parser = new FileOptionsParser();
		
		return parser.load();
	}
	
	public String get(String key)
	{
		return options.get(key);
	}
	
	public boolean containsKey(String key)
	{
		return options.containsKey(key);		
	}
	
	public void setTask(String value)
	{
		options.put("Task", value);
	}
	
	public void save()
	{
		saver.save(options);
	}
	
	public boolean validForPlot()
	{
		if( options.containsKey("booksfileName") &&
			options.containsKey("gamesfileName") &&
			options.containsKey("blogUrl") &&
			options.containsKey("devUrl") )
			return true;
		
		return false;
	}
}
