package pl.kofun.mavis.utils;

import java.util.Calendar;
import java.util.Optional;

public class monthFileNameCreator extends FileNameCreator{

	@Override
	public String createName(int monthToplot, int yearToplot)
	{
		// Because MonthPlotter passes value from range 0-11
		++monthToplot;
		
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
	
	@Override
	public String createName(int monthToPlot)
	{
		return createName(monthToPlot, Calendar.getInstance().get(Calendar.YEAR));
	}

	@Override
	public String createName(Optional<String> name) {
		return createName(Calendar.getInstance().get(Calendar.MONTH));
	}
}
