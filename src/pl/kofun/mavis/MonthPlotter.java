package pl.kofun.mavis;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import pl.kofun.mavis.utils.fileNameCreator;
import pl.kofun.mavis.utils.romanNumbers;

public class MonthPlotter implements MainTask{

	private FilterBuilder builder;
	private LinesCounter count;
	private String gamesFileName;
	
	public MonthPlotter(Hashtable<String, String> options)
	{
		builder = new FilterBuilder();
		
		if(options.containsKey("booksfileName") && options.containsKey("gamesfileName"))
		{
			String filter;
			if(options.containsKey("monthtoPlot"))
			{
				int month =  Integer.parseInt(options.get("monthtoPlot")) -1;
				if(options.containsKey("yeartoPlot"))
				{
					int year = Integer.parseInt(options.get("yeartoPlot"));
					filter = builder.makeFilter(month,year);
				}
				else
				{
					filter = builder.makeFilter(month);
				}
			}
			else
			{
				filter = builder.makeCurrentTimeFilter();
			}
			
			count = new LinesCounter(options.get("booksfileName"), filter);
			gamesFileName = new String(options.get("gamesfileName"));
		}
	}
	
	public void execute()
	{
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(count.countLinesWithFilter(), "Finished", "Books");
		
		count.setFileToAccess(gamesFileName);
		dataset.setValue(count.countLinesWithFilter(), "Finished", "Games");
		
		try
		{
			dataset.setValue(getData("Dev Posts"), "Finished", "Dev Posts");
			dataset.setValue(getData("Blog Posts"), "Finished", "Blog Posts");
			dataset.setValue(getData("Tasks"), "Finished", "Tasks");
			
			String chartName = fileNameCreator.monthChart();
			
			JFreeChart chart = ChartFactory.createBarChart("Month Plot", "Medium", "Finished", dataset, PlotOrientation.VERTICAL, false, true, false);
			ChartUtilities.saveChartAsJPEG(new File(chartName), chart, 500, 300);
			
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
