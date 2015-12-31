package pl.kofun.mavis;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class MonthPlotter implements MainTask{

	private FilterBuilder builder;
	private LinesCounter count;
	private String gamesFileName;
	
	public MonthPlotter(Hashtable<String, String> options)
	{
		builder = new FilterBuilder();
		
		if(options.containsKey("booksfileName") && options.containsKey("gamesfileName"))
		{
			count = new LinesCounter(options.get("booksfileName"), builder.makeCurrentTimeFilter());
			gamesFileName = new String(options.get("gamesfileName"));
		}
	}
	
	public void execute()
	{
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(count.countLinesWithFilter(), "Finished", "Books");
		
		count.setFileToAccess(gamesFileName);
		
		try
		{
			dataset.setValue(count.countLinesWithFilter(), "Finished", "Games");
			dataset.setValue(getData("Dev Posts"), "Finished", "Dev Posts");
			dataset.setValue(getData("Blog Posts"), "Finished", "Blog Posts");
			dataset.setValue(getData("Tasks"), "Finished", "Tasks");
			
			JFreeChart chart = ChartFactory.createBarChart("Month Plot", "Medium", "Finished", dataset, PlotOrientation.VERTICAL, false, true, false);
		
			ChartUtilities.saveChartAsJPEG(new File("chart.jpg"), chart, 500, 300);
			System.out.print("All green");
		}catch(IOException e)
		{
			System.out.println(e);
		}
	}
	
	private int getData(String dataName) throws IOException
	{
		int ret = 0;
		Scanner input = new Scanner(System.in); // is not closed because it'd close System.in too. Let VM handle it.
		
		String val;
		do
		{
			System.out.print("\nEnter number of " + dataName +": ");
			val = input.nextLine();
		}while(!val.matches("\\d+"));
		ret = Integer.parseInt(val);
		
		
		return ret;
	}
}
