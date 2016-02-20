package pl.kofun.mavis.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class FileNameCreator {

	protected static String getFolder(String folderName) {
		Path path = Paths.get(folderName);
		if(Files.notExists(path))
		{
			File dir = new File(path.toString());
			dir.mkdir();
		}
		if(Files.exists(path))
		{
			return folderName;
		}
		else
		{
			return "";
		}
	}

	public abstract String createName(int month, int year);
	
	public abstract String createName(int year);
	
	
	
}