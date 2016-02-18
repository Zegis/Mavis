package pl.kofun.mavis.utils;

public class monthFileNameCreator extends FileNameCreator{

	public static String monthChart(int monthToplot, int yearToplot)
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
}
