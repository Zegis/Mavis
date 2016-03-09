package pl.kofun.mavis;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import pl.kofun.mavis.Interfaces.MainTask;
import pl.kofun.mavis.utils.FileNameCreator;
import pl.kofun.mavis.utils.FilterBuilder;
import pl.kofun.mavis.utils.monthFileNameCreator;

public class MonthPlotter implements MainTask{

	private FilterBuilder builder;
	private LinesCounter count;
	private String gamesFileName;
	
	private int month;
	private int year;
	
	private FileNameCreator fileNameCreator;
	
	public MonthPlotter(Options options)
	{
		builder = new FilterBuilder();
		
		if(options.containsKey("booksfileName") && options.containsKey("gamesfileName"))
		{
			String filter;
			if(options.containsKey("monthtoPlot"))
			{
				month =  Integer.parseInt(options.get("monthtoPlot")) -1;
			}
			else
			{
				month = Calendar.getInstance().get(Calendar.MONTH);
			}
			if(options.containsKey("yeartoPlot"))
			{
				year = Integer.parseInt(options.get("yeartoPlot"));
			}
			else
			{
				year = Calendar.getInstance().get(Calendar.YEAR);
			}
			
			filter = builder.makeFilter(month,year);
			
			count = new LinesCounter(options.get("booksfileName"), filter);
			gamesFileName = new String(options.get("gamesfileName"));
			
			fileNameCreator = new monthFileNameCreator();
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
			
			String chartName = fileNameCreator.createName(month, year);
			
			JFreeChart chart = ChartFactory.createBarChart("Month Plot", "Medium", "Finished", dataset, PlotOrientation.VERTICAL, false, false, true);
			
			CategoryPlot plot = chart.getCategoryPlot();
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();			
			rangeAxis.setTickUnit(new NumberTickUnit(1));
			
			ChartUtilities.saveChartAsJPEG(new File(chartName), chart, 500, 300);
			
			System.out.print("\nAll green");
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
