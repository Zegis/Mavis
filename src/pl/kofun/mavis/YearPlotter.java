package pl.kofun.mavis;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;

public class YearPlotter implements MainTask{

	private LinesCounter booksFileCounter;
	private LinesCounter gamesFileCounter;
	int yearToPlot;
	
	public YearPlotter(Hashtable<String, String> options)
	{
		if(options.containsKey("booksfileName") && options.containsKey("gamesfileName"))
		{
			booksFileCounter = new LinesCounter(options.get("booksfileName"));
			gamesFileCounter = new LinesCounter(options.get("gamesfileName"));
		}
		
		if(options.containsKey("yeartoPlot"))
		{
			yearToPlot = Integer.parseInt(options.get("yeartoPlot"));
		}
		else
		{
			yearToPlot = Calendar.getInstance().get(Calendar.YEAR);
		}
	}
	
	@Override
	public void execute() {
		try
		{			
			TimeSeries books = new TimeSeries("Books");			
			TimeSeries games = new TimeSeries("Games");			
			TimeSeries posts = new TimeSeries("Posts");
			TimeSeries devposts = new TimeSeries("Dev blogs");
			
			FilterBuilder filterMaker = new FilterBuilder();
			
			for(int i=1; i<13; ++i)
			{
				Month currentMonth = new Month(i,yearToPlot);
				String filterForCurrentMonth = filterMaker.makeFilter(i-1,yearToPlot);
				
				books.add(createFileSeriesDataItem(currentMonth,filterForCurrentMonth, booksFileCounter));
				games.add(createFileSeriesDataItem(currentMonth, filterForCurrentMonth, gamesFileCounter));
				posts.add(createSeriesDataItem(currentMonth,"posts"));
				devposts.add(createSeriesDataItem(currentMonth, "devposts"));
			}
		
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(books);
			dataset.addSeries(games);
			dataset.addSeries(posts);
			dataset.addSeries(devposts);
			
			JFreeChart chart = ChartFactory.createTimeSeriesChart(
					"2015",
					"Month",
					"Quantity",
					dataset,
					true,
					true,
					false
					);
			
			ChartUtilities.saveChartAsJPEG(new File("Year.jpg"), chart, 500, 300);
			
		}catch(IOException e)
		{
			System.out.println(e);
		}
		
		System.out.println("All green");
	}
	
	private TimeSeriesDataItem createFileSeriesDataItem(Month currentMonth, String filterForCurrentMonth, LinesCounter counter)
	{
			counter.setFilter(filterForCurrentMonth);
			return new TimeSeriesDataItem(currentMonth, counter.countLinesWithFilter());
	}
	
	private TimeSeriesDataItem createSeriesDataItem(Month currentMonth, String name)
	{
		try
		{
			return new TimeSeriesDataItem(currentMonth,getData(name + " in " + currentMonth));
		}
		catch(IOException e)
		{
			System.out.println(e);
			return new TimeSeriesDataItem(currentMonth, 0.0);
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
