package pl.kofun.mavis;

import java.util.Hashtable;

public class ArgumentParser implements Parser{

	Hashtable<String, String> options;
	
	public ArgumentParser()
	{
		options = new Hashtable<String,String>();
	}
	
	public Hashtable<String,String> parse(String args[])
	{
		return parseArguments(args);
	}
	
	private Hashtable<String, String> parseArguments(String args[])
	{		
		if(args.length > 0)
		{
			options.put("Task", args[0]);
			
			switch(args[0])
			{
			case "Ohil": parseLibraryArguments(args); break;
			case "Mp": parseMonthPlotterArguments(args); break;
			case "Yp": parseYearPlotterArguments(args); break;
			default: incorrectTask(); break;
			}
		}
		
		return options;
	}

	public void incorrectTask()
	{
		options.clear();
		
		System.out.println("Incorrect task!");
		System.out.println("Run as:");
		System.out.println("Mavis [cmd] [args]");
		System.out.print("Available cmd are: ");
		System.out.print("Ohil ");
		System.out.print("Mp ");
		System.out.print("Yp ");
	}
	
	private void parseMonthPlotterArguments(String[] args) {
		if( (args.length-1)%2 == 0)
		{
			for(int i=1; i<args.length;)
			{
				if(args[i].equalsIgnoreCase("-b"))
				{
					options.put("booksfileName", args[i+1]);
					i+=2;
				}
				else if(args[i].equalsIgnoreCase("-g"))
				{
					options.put("gamesfileName", args[i+1]);
					i+=2;
				}
				else if(args[i].equalsIgnoreCase("-m"))
				{
					options.put("monthtoPlot", args[i+1]);
					i+=2;
				}
				else if(args[i].equalsIgnoreCase("-y"))
				{
					options.put("yeartoPlot", args[i+1]);
					i+=2;
				}
				else
				{
					++i;
				}
				
			}
		}
		else
		{
			incorrectMonthPlotterArguments();
		}
	}
	
	private void incorrectMonthPlotterArguments()
	{
		System.out.println("Avilable args for Month Plotter:");
		System.out.println("-b                       defines books filename");
		System.out.println("-g                       defines games filename");
		System.out.println("-m                       defines month");
		System.out.println("-y                       defines year");
	}
	
	private void parseYearPlotterArguments(String[] args) {
		if( (args.length-1)%2 == 0)
		{
			for(int i=1; i<args.length;)
			{
				if(args[i].equalsIgnoreCase("-b"))
				{
					options.put("booksfileName", args[i+1]);
					i+=2;
				}
				else if(args[i].equalsIgnoreCase("-g"))
				{
					options.put("gamesfileName", args[i+1]);
					i+=2;
				}
				else if(args[i].equalsIgnoreCase("-y"))
				{
					options.put("yeartoPlot", args[i+1]);
					i+=2;
				}
				else
				{
					++i;
				}
			}
	
		}
		else
		{
			incorrectYearPlotterArguments();
		}
		
	}
	
	private void incorrectYearPlotterArguments(){
		System.out.println("Avilable args for Month Plotter:");
		System.out.println("-b                       defines books filename");
		System.out.println("-g                       defines games filename");
		System.out.println("-y                       defines year for plotting");
	}
	
	private void parseLibraryArguments(String args[])
	{
		if( (args.length-1)%2 == 0)
		{
			for(int i=1; i<args.length;)
			{
				if(args[i].equalsIgnoreCase("-s"))
				{
					options.put("sourcefileName", args[i+1]);
					i+=2;
				}
				else if(args[i].equalsIgnoreCase("-t"))
				{
					options.put("targetfileName", args[i+1]);
					i+=2;
				}
				else if(args[i].equalsIgnoreCase("-f"))
				{
					options.put("filter", args[i+1]);
					i+=2;
				}
				else
				{
					++i;
				}
			}
			
		}
		else
		{
			incorrectLibraryArguments();
		}
	}
	
	private void incorrectLibraryArguments()
	{
		System.out.println("Avilable args for Library:");
		System.out.println("-s                       defines source filename");
		System.out.println("-t                       defines target filename");
		System.out.println("-f                       filter to use");
	}
	
}
