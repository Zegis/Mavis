import java.util.LinkedList;

public class Apk {

	public static void main(String args[])
	{
				
		if(args.length > 0)
		{			
			LinesCounter counter = new LinesCounter(args[0],args[1]);
			
			
			LinkedList<String> tmp = counter.getSomeLinesAfterFilter(20);
			tmp = stringAdjuster.AdjustToBookList(tmp);
			
			for(int i = 0; i < tmp.size(); ++i)
			{
				System.out.println(tmp.get(i));
			}
		}
		else
		{
			System.out.println("Need filename!");
		} 
	}	
}
