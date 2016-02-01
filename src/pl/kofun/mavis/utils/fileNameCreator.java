package pl.kofun.mavis.utils;

import java.nio.file.Path;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class fileNameCreator {

	public static String monthChart()
	{
		StringBuilder chartName = new StringBuilder();
		
		String folder = getFolder("monthly");
		
		if(!folder.isEmpty())
		{
			chartName.append(folder);
			chartName.append('\\');
		}
		
		chartName.append("chart");
		
		DateFormat monthFormat = new SimpleDateFormat("MM");
		DateFormat yearFormat = new SimpleDateFormat("YYYY");
		
		int yearOfFirstPlot = 2013;
		
		Calendar calendar = Calendar.getInstance();
		
		String monthString = monthFormat.format(calendar.getTime());
		String yearString = yearFormat.format(calendar.getTime());
		
		int monthNumber = Integer.parseInt(monthString);
		int seriesNumber = Integer.parseInt(yearString) - yearOfFirstPlot;
		
		monthString = romanNumbers.intToRoman(monthNumber);
		
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
		chartName.append("year");		
		chartName.append(yeartoPlot);		
		chartName.append(".jpg");
		
		return chartName.toString();
	}
	
}
