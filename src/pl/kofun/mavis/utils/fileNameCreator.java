package pl.kofun.mavis.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class fileNameCreator {

	public static String monthChart()
	{
		StringBuilder chartName = new StringBuilder();
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
	
}
