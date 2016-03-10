package pl.kofun.mavis;

import java.util.Hashtable;
import java.util.Optional;

import pl.kofun.mavis.Interfaces.OptionsParser;
import pl.kofun.mavis.utils.FileNameCreator;
import pl.kofun.mavis.utils.saveFileNameCreator;

public class FileParser implements OptionsParser {

	Hashtable<String, String> _options;
	
	FileParser()
	{
		_options = new Hashtable<String,String>();
	}
	
	FileParser(Hashtable<String, String> options)
	{
		_options = options;
	}
	
	@Override
	public Hashtable<String, String> load(String... args) {

		FileNameCreator fileNameCreator = new saveFileNameCreator();
		String fileName;
		if(args.length == 1)
		{
			
			fileName = fileNameCreator.createName(Optional.of(args[0]));
		}
		else
		{
			fileName = fileNameCreator.createName(Optional.empty());
		}
		
		return _options;
	}

}
