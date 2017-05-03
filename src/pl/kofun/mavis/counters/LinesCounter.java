package pl.kofun.mavis.counters;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class LinesCounter implements Counter{
	
	private FileInputStream inputHandle;
	private BufferedReader file;
	private String filter; 
	
	public LinesCounter(String fileToAccess){
		
		setFileToAccess(fileToAccess);
		
		filter = "";
	}
	
	public void setFileToAccess(String fileToAccess)
	{
		try{
			inputHandle = new FileInputStream(fileToAccess);
			file = new BufferedReader(new InputStreamReader(inputHandle, "UTF8"));
		}
		catch(UnsupportedEncodingException e)
		{
			System.out.println(e);
		}
		catch(FileNotFoundException e)
		{
			System.out.print(e);
		}
	}
	
	public LinesCounter(String fileToAccess, String filter)
	{
		this(fileToAccess);
		
		this.filter = new String(filter);
	}
	
	private int countAllLines(){
		
		this.resetFile();
		
		int ret = 0;
		
		try
		{
			while(file.readLine() != null)
			{
				ret++;
			}
			
			return ret;
		}
		catch(IOException e)
		{
			ret = 0;
			return ret;
		}
	}
	
	public int count(){
		if(filter.isEmpty())
			return countAllLines();
		else{
			this.resetFile();
			
			int ret = 0;
			String currLine;
			try{				
				while( (currLine = file.readLine()) != null)
					if(currLine.contains(filter))
						++ret;
				
			}
			catch(IOException e){
				ret = 0;
			}
			
			return ret;
		}
	}
	
	public void setFilter(String filterToApply){
		if(!filterToApply.equals(filter))
			filter = filterToApply;
	}
	
	private void resetFile()
	{
		try
		{
			inputHandle.getChannel().position(0);
			file = new BufferedReader(new InputStreamReader(inputHandle, "UTF8"));
		}
		catch(IOException e)
		{
			System.out.print(e);
		}
	}
	
}
