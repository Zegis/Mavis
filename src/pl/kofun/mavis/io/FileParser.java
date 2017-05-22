package pl.kofun.mavis.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Optional;

import pl.kofun.mavis.Interfaces.OptionsParser;
import pl.kofun.mavis.utils.FileNameCreator;
import pl.kofun.mavis.utils.saveFileNameCreator;

public class FileParser implements OptionsParser {

	Hashtable<String, String> _options;
	
	public FileParser()
	{
		_options = new Hashtable<String,String>();
	}
	
	public FileParser(Hashtable<String, String> options)
	{
		_options = options;
	}
	
	@Override
	public Hashtable<String, String> load(String... args) {

		FileNameCreator fileNameCreator = new saveFileNameCreator();
		String fileName;
		if(args.length == 2)
		{
			
			fileName = fileNameCreator.createName(Optional.of(args[1]));
		}
		else
		{
			fileName = fileNameCreator.createName(Optional.empty());
		}
		
		try
		{
			FileInputStream inputStream = new FileInputStream(fileName);
			BufferedReader filereader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			String currentLine;
			while( (currentLine = filereader.readLine()) != null)
			{
				if(currentLine.contains(" : "))
				{
					String[] optionsString = currentLine.split(" : ", 2);
					_options.put(optionsString[0], optionsString[1]);
				}
					
			}
			filereader.close();
		}
		catch(UnsupportedEncodingException ex)
		{
			System.out.print("Cant open file with UTF-8!");
		}
		catch(FileNotFoundException ex)
		{
			if(fileName.equals(fileNameCreator.createName(Optional.empty())))
				System.out.println("Can't find default config!");
			else
				System.out.println("Unable to open given save file:" + fileName);
		}
		catch(IOException ex)
		{
			System.out.println(ex.getMessage());
		}
		
		return _options;
	}

}
