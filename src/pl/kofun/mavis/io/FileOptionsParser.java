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

public class FileOptionsParser implements OptionsParser {

	Hashtable<String, String> _options;
	FileNameCreator fileNameCreator;
	
	public FileOptionsParser()
	{
		this(new Hashtable<String,String>());
	}
	
	public FileOptionsParser(Hashtable<String, String> options)
	{
		_options = options;
		fileNameCreator = new saveFileNameCreator();
	}
	
	@Override
	public Hashtable<String, String> load(String... args) {

		
		String fileName = getOptionsFileName(args);
		
		try
		{
			createFileAndReadOptions(fileName);
		}
		catch(UnsupportedEncodingException ex)
		{
			System.out.print("Cant open file with UTF-8!");
		}
		catch(FileNotFoundException ex)
		{
			if(fileName.equals(getDefaultFileName()))
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
	
	private String getOptionsFileName(String... args)
	{
		Optional<String> fileNameTemplate = getFileNameTemplate(args);
		return fileNameCreator.createName(fileNameTemplate);
	}
	
	private Optional<String> getFileNameTemplate(String... args)
	{
		if(args.length == 2)
		{
			return Optional.of(args[1]);
		}
		else
		{
			return Optional.empty();
		}
	}
	
	private void createFileAndReadOptions(String fileName) throws IOException
	{
		BufferedReader fileReader = openFile(fileName);
		String currentLine;
		while( (currentLine = fileReader.readLine()) != null)
		{
			parseLine(currentLine);
		}
		fileReader.close();
	}
	
	private BufferedReader openFile(String fileName) throws FileNotFoundException, UnsupportedEncodingException
	{
		FileInputStream inputStream = new FileInputStream(fileName);
		return new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
	}
	
	private void parseLine(String currentLine){
		if(currentLine.contains(" : "))
		{
			String[] optionsString = currentLine.split(" : ", 2);
			_options.put(optionsString[0], optionsString[1]);
		}	
	}
	
	private String getDefaultFileName()
	{
		return fileNameCreator.createName(Optional.empty());
	}

}
