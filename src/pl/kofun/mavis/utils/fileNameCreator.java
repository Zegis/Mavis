package pl.kofun.mavis.utils;

import java.nio.file.Path;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class fileNameCreator {

	public static String monthChart(int monthToplot, int yearToplot)
	{
		StringBuilder chartName = new StringBuilder();
		
		String folder = getFolder("monthly");
		
		if(!folder.isEmpty())
		{
			chartName.append(folder);
			chartName.append('\\');
		}
		
		chartName.append("chart");
		
		int yearOfFirstPlot = 2013;
		
		
		int seriesNumber = yearToplot - yearOfFirstPlot;
		
		String monthString = romanNumbers.intToRoman(monthToplot);
		
		chartName.append(seriesNumber);
		chartName.append(monthString);
		
		chartName.append(".jpg");
		
		return chartName.toString();
	}
	
	public static String getFolder(String folderName)
	{
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
	
	public static String yearChart(int yeartoPlot)
	{
		StringBuilder chartName = new StringBuilder();
		
		String folder = getFolder("yearly");
		
		if(!folder.isEmpty())
		{
			chartName.append(folder);
			chartName.append('\\');
		}
		
		chartName.append("year");		
		chartName.append(yeartoPlot);		
		chartName.append(".jpg");
		
		return chartName.toString();
	}
	
}
