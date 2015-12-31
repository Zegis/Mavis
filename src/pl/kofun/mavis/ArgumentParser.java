package pl.kofun.mavis;

import java.util.Hashtable;

public class ArgumentParser {

	Hashtable<String, String> options;
	
	public ArgumentParser()
	{
		options = new Hashtable<String,String>();
	}
	
	public Hashtable<String, String> parseArguments(String args[])
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

	private void parseMonthPlotterArguments(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	private void parseYearPlotterArguments(String[] args) {
		// TODO Auto-generated method stub
		
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
