import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;

public class Apk {

	public static void main(String args[]) throws FileNotFoundException
	{
				
		if(args.length > 0)
		{			
			LinesCounter counter = new LinesCounter(args[0],args[1]);
			
			RandomAccessFile save = new RandomAccessFile(args[2], "rw");
			
			LinkedList<String> tmp = counter.getSomeLinesAfterFilter(20);
			tmp = stringAdjuster.AdjustToBookList(tmp);
			
			try
			{
				save.setLength(0);
				for(int i = 0; i < tmp.size(); ++i)
				{
					System.out.print(tmp.get(i));
					save.writeBytes(tmp.get(i));
				}
			}catch(IOException e){
				System.out.println(e);
			}
			
			try {
				save.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		else
		{
			System.out.println("Need filename!");
		} 
	}	
}
