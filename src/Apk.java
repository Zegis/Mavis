import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Hashtable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import pl.kofun.mavis.*;

public class Apk{

	public static void main(String args[]) throws IOException
	{		
		if(args.length > 0)
		{
			ArgumentParser parser = new ArgumentParser();
			Hashtable<String, String> options = parser.parseArguments(args);
			
			if(options.size() > 1)
			{
			if(options.get("Task").equals("Ohil"))
			{
				Library Ohil = new Library(options.get("sourcefileName"));
				
				Ohil.execute();
			}
			else if(options.get("Task").equals("Mp"))
			{
				MonthPlotter mp = new MonthPlotter();
				mp.execute();
				
				if(args.length > 1)
				{
					FilterBuilder builder = new FilterBuilder();
					
					LinesCounter count = new LinesCounter(args[1],builder.makeCurrentTimeFilter());
					
					DefaultCategoryDataset dataset = new DefaultCategoryDataset();
					dataset.setValue(count.countLinesWithFilter(), "Finished", "Books");
					
					count.setFileToAccess(args[1]);
					
					dataset.setValue(count.countLinesWithFilter(), "Finished", "Games");
					dataset.setValue(getData("Dev Posts"), "Finished", "Dev Posts");
					dataset.setValue(getData("Blog Posts"), "Finished", "Blog Posts");
					dataset.setValue(getData("Tasks"), "Finished", "Tasks");
					
					JFreeChart chart = ChartFactory.createBarChart("Month Plot", "Medium", "Finished", dataset, PlotOrientation.VERTICAL, false, true, false);
					try
					{
						ChartUtilities.saveChartAsJPEG(new File("chart.jpg"), chart, 500, 300);
						System.out.print("All green");
					}catch(IOException e)
					{
						System.out.println(e);
					}
				}
			}
			else if (options.get("Task").equals("Yp"))
			{
				YearPlotter yearp = new YearPlotter();
				yearp.execute();
			}
			else
			{
				IncorrectUsage();
			}
		}
		}
		else
		{
			IncorrectUsage();
		} 
	}
	
	public static int getData(String dataName) throws IOException
	{
		int ret = 0;
		 System.out.println(System.in.available());
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
	
	public static void IncorrectUsage(){
		
		System.out.println("Usage:");
		System.out.println("Mavis [cmd] (options)!");
		System.out.println("Available cmd: Ohil and Mp");
	}
}
