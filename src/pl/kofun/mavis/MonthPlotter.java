package pl.kofun.mavis;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import pl.kofun.mavis.Interfaces.MainTask;
import pl.kofun.mavis.counters.BlogCounter;
import pl.kofun.mavis.counters.Counter;
import pl.kofun.mavis.counters.LinesCounter;
import pl.kofun.mavis.counters.PeriodToCount;
import pl.kofun.mavis.counters.TrelloCounter;
import pl.kofun.mavis.utils.FileNameCreator;
import pl.kofun.mavis.utils.FilterBuilder;
import pl.kofun.mavis.utils.monthFileNameCreator;

public class MonthPlotter implements MainTask{

	private LinesCounter count;
	private String gamesFileName;
	
	private BlogCounter blogCount;
	private Counter trelloCount;
	private String blogUrl;
	private String devUrl;
	
	private PeriodToCount monthToCount;
	
	private FileNameCreator fileNameCreator;
	
	private DefaultCategoryDataset dataset;
	
	public MonthPlotter(Options options)
	{
		if(options.validForPlot())
		{
			int month, year;
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
			
			FilterBuilder builder = new FilterBuilder();
			
			String filter = builder.makeFilter(month,year);
			monthToCount = new PeriodToCount(year, month, filter);
			
			count = new LinesCounter(options.get("booksfileName"), filter);
			blogCount = new BlogCounter();
			trelloCount = new TrelloCounter(options.get("apiKey"), options.get("apiToken"),filter);
			blogUrl = new String(options.get("blogUrl"));
			devUrl = new String(options.get("devUrl"));
			
			gamesFileName = new String(options.get("gamesfileName"));
			
			fileNameCreator = new monthFileNameCreator();
			
			dataset = new DefaultCategoryDataset();
		}
	}
	
	public void execute()
	{
		if(count != null)
		{
			prepareDataset();
			
			try
			{
				String chartName = fileNameCreator.createName(monthToCount.Month, monthToCount.Year);
				
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
		else
		{
			this.usage();
		}
	}
	
	private void prepareDataset(){
		dataset.setValue(count.count(), "Finished", "Books");
		
		count.setFileToAccess(gamesFileName);
		dataset.setValue(count.count(), "Finished", "Games");
		
		blogCount.setBlogUrl(devUrl);
		blogCount.setPeriodToCount(monthToCount);
		dataset.setValue(blogCount.count(),"Finished","Dev Posts");
		
		blogCount.setBlogUrl(blogUrl);
		dataset.setValue(blogCount.count(),"Finished","Blog Posts");
		
		dataset.setValue(trelloCount.count(), "Finished", "Tasks");
	}

	@Override
	public void usage() {
		System.out.println("For Month Plotter you must define:");
		System.out.println("books filename as -b (argument) or booksfileName : (argument) inside txt file");
		System.out.println("games filename as -g (argument) or gamesfileName : (argument) inside txt file");
		System.out.println("blog url as -Burl (argument) or blogUrl : (argument) inside txt file");
		System.out.println("devblog url as -Burl (argument) or devUrl : (argument) inside txt file");
		System.out.println("You may want to specify");
		System.out.println("Month to plot by -m (number) in command line");
		System.out.println("Year to plot by -y (year) in command line");
		
	}
}
