package pl.kofun.mavis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;

public class FilterLineReader {

	private String filter;
	private RandomAccessFile file;
	
	public FilterLineReader(String fileToRead)
	{
		filter = "";
		
		try{
			file = new RandomAccessFile(fileToRead, "rw");
		}catch (FileNotFoundException e)
		{
			System.out.println(e);
		}
	}
	
	public FilterLineReader(String fileToRead, String newfilter)
	{
		this(fileToRead);
		
		this.filter = new String(newfilter);
	}
	
	public void setFilter(String newFilter)
	{
		if(!filter.equals(newFilter))
			this.filter = newFilter;
	}
	
	public LinkedList<String> getLinewsWithFilter()
	{		
		LinkedList<String> ret = new LinkedList<>();
		
		if(!filter.isEmpty())
		{
			String currLine;
			try{
				file.seek(0);
				
				while( (currLine = file.readLine()) != null){
					if(currLine.contains(filter))
						ret.add(currLine);
					
				}
			}catch(IOException e){
				ret.clear();
			}
		}
				
		return ret;
	}
	
	public LinkedList<String> getLinesAfterFilterAndMoveIt(int numberOfLines)
	{
		LinkedList<String> ret = getSomeLinesAfterFilter(numberOfLines);
		
		if(ret.size() > 0)
		{
			moveFilterAfterString(ret.getLast());
		}
		
		return ret;
	}
	
	private LinkedList<String> getSomeLinesAfterFilter(int numberOfLines)
	{
		LinkedList<String> ret = new LinkedList<>();
		
		if(!filter.isEmpty())
		{
			String currLine;
			boolean found = false;
			
			try{
				file.seek(0);
				while( (currLine = file.readLine()) != null)
				{
					if(found && numberOfLines > 0){
						--numberOfLines;
						ret.add(currLine);
					}
					else if(currLine.contains(filter))
					{
						found = true;
					}
					else if(numberOfLines == 0)
					{
						break;
					}
				}
			}catch(IOException e){
				ret.clear();
			}
		}
		
		return ret;
	}
	
	private void moveFilterAfterString(String putFilterAfter)
	{
		if(!filter.isEmpty() && !putFilterAfter.isEmpty())
		{
			String currLine;
			String newContent = "";
			try{
				file.seek(0);
				while( (currLine = file.readLine()) != null)
				{
					if(currLine.contains(filter))
					{
						currLine = currLine.replace(filter, "");
						
					}
					if(currLine.equals(putFilterAfter))
					{
						currLine += " " + filter;
					}
					
					newContent += currLine + '\n';
				}
				file.setLength(0);
				file.seek(0);
				file.writeBytes(newContent);
				
			}catch(IOException e){
				System.out.println(e);
			}
		}
	}
}
