package pl.kofun.mavis;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
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

import pl.kofun.mavis.utils.FileNameCreator;
import pl.kofun.mavis.utils.FilterBuilder;
import pl.kofun.mavis.utils.yearFileNameCreator;

public class YearPlotter implements MainTask{

	private LinesCounter booksFileCounter;
	private LinesCounter gamesFileCounter;
	private int yearToPlot;
	
	private FileNameCreator fileNameCreator;
	
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
		
		fileNameCreator = new yearFileNameCreator();
	}
	
	@Override
	public void execute() {
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
				posts.add(createSeriesDataItem(currentMonth,"posts"));
				devposts.add(createSeriesDataItem(currentMonth, "devposts"));
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
