
public class Apk {

	public static void main(String args[])
	{
		
		
		if(args.length > 0)
		{
			System.out.println(args[1]);
			
			LinesCounter counter = new LinesCounter(args[0],args[1]);
			
			System.out.println("There are " + counter.countLinesWithFilter() + " lines in " + args[0] + " file."  );
		}
		else
		{
			System.out.println("Need filename!");
		}
	}
	
}
