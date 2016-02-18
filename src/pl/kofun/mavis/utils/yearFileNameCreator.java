package pl.kofun.mavis.utils;

public class yearFileNameCreator extends FileNameCreator {
	
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

