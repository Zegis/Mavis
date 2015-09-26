
public class Apk {

	public static void main(String args[])
	{
		
		if(args.length > 0)
		{
			LinesCounter counter = new LinesCounter(args[0]);
			
			System.out.println("There are " + counter.countAllLines() + " lines in " + args[0] + " file."  );
		}
		else
		{
			System.out.println("Need filename!");
		}
	}
	
}
