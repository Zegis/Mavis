import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class LinesCounter {
	
	private RandomAccessFile file;
	
	public LinesCounter(String fileToAccess){
		
		try{
			file = new RandomAccessFile(fileToAccess, "r");
			}
			catch(FileNotFoundException e)
			{
				System.out.println(e);
			}
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
			return 0;
		}
	}
	
}
