package pl.kofun.mavis.tasks;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

import pl.kofun.mavis.Options;
import pl.kofun.mavis.Interfaces.Counter;
import pl.kofun.mavis.Interfaces.MainTask;
import pl.kofun.mavis.counters.BlogCounter;
import pl.kofun.mavis.counters.LinesCounter;
import pl.kofun.mavis.counters.PeriodToCount;
import pl.kofun.mavis.counters.TrelloCounter;
import pl.kofun.mavis.utils.FileNameCreator;
import pl.kofun.mavis.utils.FilterBuilder;
import pl.kofun.mavis.utils.yearFileNameCreator;

public class YearPlotter implements MainTask{

	private LinesCounter booksFileCounter;
	private LinesCounter gamesFileCounter;
	private BlogCounter blogCounter;
	private BlogCounter devCounter;
	private TrelloCounter tasksCounter;
	private int yearToPlot;
	
	private FileNameCreator fileNameCreator;
	
	public YearPlotter(Options options)
	{
		if(options.validForPlot())
		{
			booksFileCounter = new LinesCounter(options.get("booksfileName"));
			gamesFileCounter = new LinesCounter(options.get("gamesfileName"));
			blogCounter = new BlogCounter(options.get("blogUrl"));
			devCounter = new BlogCounter(options.get("devUrl"));
			tasksCounter = new TrelloCounter(options.get("apiKey"), options.get("apiToken"));
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
				TimeSeriesCollection dataset = prepareDataset();
				
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
	
	private TimeSeriesCollection prepareDataset()
	{
		TimeSeries books = new TimeSeries("Books");			
		TimeSeries games = new TimeSeries("Games");			
		TimeSeries posts = new TimeSeries("Posts");
		TimeSeries devposts = new TimeSeries("Dev posts");
		TimeSeries tasks = new TimeSeries("Tasks");
		
		FilterBuilder filterMaker = new FilterBuilder();
		
		Month currentMonth;
		PeriodToCount currentPeriod = new PeriodToCount();
		currentPeriod.Year = yearToPlot;
		
		for(int i=1; i<13; ++i)
		{
			currentMonth = new Month(i,yearToPlot);
			currentPeriod.Filter = filterMaker.makeFilter(i-1,yearToPlot);
			currentPeriod.Month = i-1;
			
			books.add(createFileSeriesDataItem(currentMonth,currentPeriod, booksFileCounter));
			games.add(createFileSeriesDataItem(currentMonth, currentPeriod, gamesFileCounter));
			posts.add(createFileSeriesDataItem(currentMonth,currentPeriod,blogCounter));
			devposts.add(createFileSeriesDataItem(currentMonth,currentPeriod, devCounter));
			tasks.add(createFileSeriesDataItem(currentMonth,currentPeriod,tasksCounter));
		}
	
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(books);
		dataset.addSeries(games);
		dataset.addSeries(posts);
		dataset.addSeries(devposts);
		dataset.addSeries(tasks);
		
		return dataset;
	}
	
	private TimeSeriesDataItem createFileSeriesDataItem(Month currentMonth, PeriodToCount currentPeriod, Counter counter)
	{
			counter.setPeriodToCount(currentPeriod);
			return new TimeSeriesDataItem(currentMonth, counter.count());
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
