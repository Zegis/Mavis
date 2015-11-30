import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Apk{

	public static void main(String args[]) throws IOException
	{
				
		if(args.length > 0)
		{			
			FilterLineReader reader = new FilterLineReader(args[0],"-- 100 lat");
			
			RandomAccessFile save = new RandomAccessFile(args[2], "rw");
			
			LinkedList<String> tmp = reader.getLinesAfterFilterAndMoveIt(20);
			
			FilterBuilder builder = new FilterBuilder();
			
			System.out.println("Filtr to: " + builder.makeCurrentTimeFilter());
			
			LinesCounter count = new LinesCounter(args[0],builder.makeCurrentTimeFilter());
			
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			dataset.setValue(count.countLinesWithFilter(), "Finished", "Books");
			
			count.setFileToAccess(args[1]);
			
			dataset.setValue(count.countLinesWithFilter(), "Finished", "Games");
			dataset.setValue(getData("Dev Posts"), "Finished", "Dev Posts");
			dataset.setValue(getData("Blog Posts"), "Finished", "Blog Posts");
			dataset.setValue(getData("Commits"), "Finished", "Commits");
			
			JFreeChart chart = ChartFactory.createBarChart("Month Plot", "Medium", "Finished", dataset, PlotOrientation.VERTICAL, false, true, false);
			try
			{
				ChartUtilities.saveChartAsJPEG(new File("chart.jpg"), chart, 500, 300);
			}catch(IOException e)
			{
				System.out.println(e);
			}
			
			try
			{
				save.setLength(0);
				for(int i = 0; i < tmp.size(); ++i)
				{
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
	
	public static int getData(String dataName) throws IOException
	{
		int ret = 0;
		 System.out.println(System.in.available());
		Scanner input = new Scanner(System.in); // is not closed because it'd close System.in too. Let VM handle it.
		
		String val;
		do
		{
			System.out.print("\nEnter number of " + dataName +": ");
			val = input.nextLine();
		}while(!val.matches("\\d+"));
		ret = Integer.parseInt(val);
		
		
		return ret;
	}
}
