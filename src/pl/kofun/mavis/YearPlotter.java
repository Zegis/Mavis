package pl.kofun.mavis;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;

import pl.kofun.mavis.Interfaces.MainTask;
import pl.kofun.mavis.utils.FileNameCreator;
import pl.kofun.mavis.utils.FilterBuilder;
import pl.kofun.mavis.utils.yearFileNameCreator;

public class YearPlotter implements MainTask{

	private LinesCounter booksFileCounter;
	private LinesCounter gamesFileCounter;
	private ApiCounter blogCounter;
	private ApiCounter devCounter;
	private int yearToPlot;
	
	private FileNameCreator fileNameCreator;
	
	public YearPlotter(Options options)
	{
		if(options.validForPlot())
		{
			booksFileCounter = new LinesCounter(options.get("booksfileName"));
			gamesFileCounter = new LinesCounter(options.get("gamesfileName"));
			blogCounter = new ApiCounter(options.get("blogUrl"));
			devCounter = new ApiCounter(options.get("devUrl"));
		}
		
		if(options.containsKey("yeartoPlot"))
		{
			yearToPlot = Integer.parseInt(options.get("yeartoPlot"));
		}
		else
		{
			yearToPlot = Calendar.getInstance().get(Calendar.YEAR);
		}
		
		fileNameCreator = new yearFileNameCreator();
	}
	
	@Override
	public void execute() {
		if(booksFileCounter != null || gamesFileCounter != null)
		{
			try
			{			
				TimeSeries books = new TimeSeries("Books");			
				TimeSeries games = new TimeSeries("Games");			
				TimeSeries posts = new TimeSeries("Posts");
				TimeSeries devposts = new TimeSeries("Dev posts");
				
				FilterBuilder filterMaker = new FilterBuilder();
				
				for(int i=1; i<13; ++i)
				{
					Month currentMonth = new Month(i,yearToPlot);
					String filterForCurrentMonth = filterMaker.makeFilter(i-1,yearToPlot);
					
					books.add(createFileSeriesDataItem(currentMonth,filterForCurrentMonth, booksFileCounter));
					games.add(createFileSeriesDataItem(currentMonth, filterForCurrentMonth, gamesFileCounter));
					posts.add(createSeriesDataItem(currentMonth,"posts",blogCounter));
					devposts.add(createSeriesDataItem(currentMonth, "devposts",devCounter));
				}
			
				TimeSeriesCollection dataset = new TimeSeriesCollection();
				dataset.addSeries(books);
				dataset.addSeries(games);
				dataset.addSeries(posts);
				dataset.addSeries(devposts);
				
				JFreeChart chart = ChartFactory.createTimeSeriesChart(
						String.valueOf(yearToPlot),
						"Month",
						"Quantity",
						dataset,
						true,
						true,
						false
						);
				
				String chartFilename = fileNameCreator.createName(yearToPlot);
				
				XYPlot plot = chart.getXYPlot();
				NumberAxis yaxis = (NumberAxis) plot.getRangeAxis();
				yaxis.setTickUnit(new NumberTickUnit(1));
				
				DateAxis xaxis = (DateAxis) plot.getDomainAxis();
				
				xaxis.setTickUnit(new DateTickUnit(DateTickUnitType.MONTH, 1));
				xaxis.setDateFormatOverride(new SimpleDateFormat("MMM"));
				
				
				ChartUtilities.saveChartAsJPEG(new File(chartFilename), chart, 500, 300);
				
			}catch(IOException e)
			{
				System.out.println(e);
			}
			
			System.out.println("All green");
		}
		else
		{
			this.usage();
		}
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
	
	private TimeSeriesDataItem createSeriesDataItem(Month currentMonth, String name, ApiCounter counter)
	{
		return new TimeSeriesDataItem(currentMonth, counter.count(yearToPlot, currentMonth.getMonth()));
		
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

	@Override
	public void usage() {
		System.out.println("For Year Plotter you must define:");
		System.out.println("books filename as -b (argument) or booksfileName : (argument) inside txt file");
		System.out.println("games filename as -g (argument) or gamesfileName : (argument) inside txt file");
		System.out.println("You may want to specify");
		System.out.println("Year to plot by -y (year) in command line");		
	}
}
