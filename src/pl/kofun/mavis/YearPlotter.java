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
		
		TimeSeries books = new TimeSeries("Books");
		
		books.add(new Month(1, 2015),1);
		books.add(new Month(2, 2015),2);
		books.add(new Month(3, 2015),1);
		books.add(new Month(4, 2015),1);
		books.add(new Month(5, 2015),1);
		books.add(new Month(6, 2015),3);
		books.add(new Month(7, 2015),1);
		books.add(new Month(8, 2015),1);
		books.add(new Month(9, 2015),1);
		books.add(new Month(10, 2015),1);
		books.add(new Month(11, 2015),1);
		books.add(new Month(12, 2015),1);
		
		TimeSeries games = new TimeSeries("Games");
		
		games.add(new Month(1, 2015),1);
		games.add(new Month(2, 2015),2);
		games.add(new Month(3, 2015),1);
		games.add(new Month(4, 2015),1);
		games.add(new Month(5, 2015),0);
		games.add(new Month(6, 2015),0);
		games.add(new Month(7, 2015),1);
		games.add(new Month(8, 2015),1);
		games.add(new Month(9, 2015),1);
		games.add(new Month(10, 2015),1);
		games.add(new Month(11, 2015),1);
		games.add(new Month(12, 2015),1);
		
		TimeSeries posts = new TimeSeries("Posts");
		
		posts.add(new Month(1, 2015),1);
		posts.add(new Month(2, 2015),2);
		posts.add(new Month(3, 2015),1);
		posts.add(new Month(4, 2015),1);
		posts.add(new Month(5, 2015),1);
		posts.add(new Month(6, 2015),3);
		posts.add(new Month(7, 2015),1);
		posts.add(new Month(8, 2015),1);
		posts.add(new Month(9, 2015),1);
		posts.add(new Month(10, 2015),1);
		posts.add(new Month(11, 2015),1);
		posts.add(new Month(12, 2015),1);
		
		TimeSeries devposts = new TimeSeries("Dev blogs");
		
		devposts.add(new Month(1, 2015),1);
		devposts.add(new Month(2, 2015),2);
		devposts.add(new Month(3, 2015),1);
		devposts.add(new Month(4, 2015),1);
		devposts.add(new Month(5, 2015),0);
		devposts.add(new Month(6, 2015),0);
		devposts.add(new Month(7, 2015),1);
		devposts.add(new Month(8, 2015),1);
		devposts.add(new Month(9, 2015),1);
		devposts.add(new Month(10, 2015),1);
		devposts.add(new Month(11, 2015),1);
		devposts.add(new Month(12, 2015),1);
		
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
		
		try
		{
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
