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
					Library Ohil = new Library(options);
					
					Ohil.execute();
				}
				else if(options.get("Task").equals("Mp"))
				{
					MonthPlotter mp = new MonthPlotter(options);
					mp.execute();
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
	
	public static void IncorrectUsage(){
		
		System.out.println("Usage:");
		System.out.println("Mavis [cmd] (options)!");
		System.out.println("Available cmd: Ohil and Mp");
	}
}
