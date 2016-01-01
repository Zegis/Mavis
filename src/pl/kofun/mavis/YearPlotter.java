package pl.kofun.mavis;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class YearPlotter implements MainTask{

	@Override
	public void execute() {
		try
		{
			
			TimeSeries books = new TimeSeries("Books");
			
			books.add(new Month(1, 2015),3);
			books.add(new Month(2, 2015),5);
			books.add(new Month(3, 2015),0);
			books.add(new Month(4, 2015),0);
			books.add(new Month(5, 2015),0);
			books.add(new Month(6, 2015),0);
			books.add(new Month(7, 2015),1);
			books.add(new Month(8, 2015),1);
			books.add(new Month(9, 2015),1);
			books.add(new Month(10, 2015),1);
			books.add(new Month(11, 2015),0);
			books.add(new Month(12, 2015),2);
			
			TimeSeries games = new TimeSeries("Games");
			
			games.add(new Month(1, 2015),3);
			games.add(new Month(2, 2015),0);
			games.add(new Month(3, 2015),0);
			games.add(new Month(4, 2015),0);
			games.add(new Month(5, 2015),2);
			games.add(new Month(6, 2015),0);
			games.add(new Month(7, 2015),2);
			games.add(new Month(8, 2015),0);
			games.add(new Month(9, 2015),0);
			games.add(new Month(10, 2015),1);
			games.add(new Month(11, 2015),0);
			games.add(new Month(12, 2015),1);
			
			TimeSeries posts = new TimeSeries("Posts");
			
			posts.add(new Month(1, 2015),getData("Posts in January:"));
			posts.add(new Month(2, 2015),getData("Posts in February:"));
			posts.add(new Month(3, 2015),getData("Posts in March:"));
			posts.add(new Month(4, 2015),getData("Posts in April:"));
			posts.add(new Month(5, 2015),getData("Posts in May:"));
			posts.add(new Month(6, 2015),getData("Posts in June:"));
			posts.add(new Month(7, 2015),getData("Posts in July:"));
			posts.add(new Month(8, 2015),getData("Posts in August:"));
			posts.add(new Month(9, 2015),getData("Posts in September:"));
			posts.add(new Month(10, 2015),getData("Posts in October:"));
			posts.add(new Month(11, 2015),getData("Posts in November:"));
			posts.add(new Month(12, 2015),getData("Posts in December:"));
			
			TimeSeries devposts = new TimeSeries("Dev blogs");
			
			devposts.add(new Month(1, 2015),getData("Dev posts in January:"));
			devposts.add(new Month(2, 2015),getData("Dev posts in February:"));
			devposts.add(new Month(3, 2015),getData("Dev posts in March:"));
			devposts.add(new Month(4, 2015),getData("Dev posts in April:"));
			devposts.add(new Month(5, 2015),getData("Dev posts in May:"));
			devposts.add(new Month(6, 2015),getData("Dev posts in June:"));
			devposts.add(new Month(7, 2015),getData("Dev posts in July:"));
			devposts.add(new Month(8, 2015),getData("Dev posts in August:"));
			devposts.add(new Month(9, 2015),getData("Dev posts in September:"));
			devposts.add(new Month(10, 2015),getData("Dev posts in October:"));
			devposts.add(new Month(11, 2015),getData("Dev posts in November:"));
			devposts.add(new Month(12, 2015),getData("Dev posts in December:"));
		
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
