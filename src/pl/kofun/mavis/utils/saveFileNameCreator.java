package pl.kofun.mavis.utils;

import java.util.Optional;

public class saveFileNameCreator extends FileNameCreator {

	@Override
	public String createName(int month, int year) {
		return createName(null);
	}

	@Override
	public String createName(int year) {
		return createName(null);
	}

	@Override
	public String createName(Optional<String> name) {
		
		StringBuilder fileName = new StringBuilder();
		
		String folder = getFolder("Saves");
		if(!folder.isEmpty())
		{
			fileName.append(folder);
			fileName.append("//");
		}
		
		if(name.isPresent())
		{
			fileName.append(name.get());
		}
		else
		{
			fileName.append("default");
		}
		fileName.append(".txt");
		
		return fileName.toString();
	}
}
