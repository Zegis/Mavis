package pl.kofun.mavis;

import java.util.Hashtable;

import pl.kofun.mavis.Interfaces.OptionsParser;
import pl.kofun.mavis.Interfaces.OptionsSaver;

public class Options {

	private Hashtable<String, String> options;
	private OptionsParser parser;
	private OptionsSaver saver;
	
	public Options(String[] args)
	{
		parser = new CommandLineParser();
		options = parser.load(args);
	}
	
	public String get(String key)
	{
		return options.get(key);
	}
	
	public boolean containsKey(String key)
	{
		return options.containsKey(key);		
	}
	
	public void save()
	{
		saver.save(options);
	}
}
