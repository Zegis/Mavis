import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class LinesCounter{
	
	private BufferedReader file;
	private String filter; 
	
	public LinesCounter(String fileToAccess){
		
		try{
			file = new BufferedReader(new InputStreamReader(new FileInputStream(fileToAccess), "UTF8"));
		}
		catch(UnsupportedEncodingException e)
		{
			System.out.println(e);
		}
		catch(FileNotFoundException e)
		{
			System.out.print(e);
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
	
	public int countLinesWithFilter(){
		if(filter.isEmpty())
			return countAllLines();
		else{
			System.out.println(filter);
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
			filter = "(" +	filterToApply + ")";
	}
	
}
