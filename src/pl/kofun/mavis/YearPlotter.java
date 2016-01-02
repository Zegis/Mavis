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
import org.jfree.data.time.TimeSeriesDataItem;

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
			TimeSeries devposts = new TimeSeries("Dev blogs");
			
			for(int i=1; i<13; ++i)
			{
				posts.add(createSeriesDataItem(i,"posts"));
				devposts.add(createSeriesDataItem(i, "devposts"));
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
	
	private TimeSeriesDataItem createSeriesDataItem(int monthN, String name)
	{
		try
		{
		return new TimeSeriesDataItem(new Month(monthN, 2015),getData(name));
		}
		catch(IOException e)
		{
			System.out.println(e);
			return new TimeSeriesDataItem(new Month(monthN,2015), 0.0);
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
