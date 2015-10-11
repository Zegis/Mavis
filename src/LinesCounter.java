import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;


public class LinesCounter{
	
	private RandomAccessFile file;
	private String filter; 
	
	public LinesCounter(String fileToAccess){
		
		try{
			file = new RandomAccessFile(fileToAccess, "r");
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e);
		}
		
		filter = "";
	}
	
	public LinesCounter(String fileToAccess, String filter)
	{
		this(fileToAccess);
		
		this.filter = new String(filter);
	}
	
	public int countAllLines(){
		
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
	
	public LinkedList<String> getLinesWithFilter(){
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
	
	public int countLinesWithFilter(){
		if(filter.isEmpty())
			return countAllLines();
		else{
			int ret = 0;
			String currLine;
			try{
				file.seek(0);
				
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
	
	public LinkedList<String> getSomeLinesAfterFilter(int numberOfLines)
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
	
	public void setFilter(String filterToApply){
		if(!filterToApply.equals(filter))
			filter = "(" +	filterToApply + ")";
	}
	
}
