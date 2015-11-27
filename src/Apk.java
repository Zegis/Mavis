import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Apk {

	public static void main(String args[]) throws FileNotFoundException
	{
				
		if(args.length > 0)
		{			
			FilterLineReader reader = new FilterLineReader(args[0],args[1]);
			
			RandomAccessFile save = new RandomAccessFile(args[2], "rw");
			
			LinkedList<String> tmp = reader.getLinesAfterFilterAndMoveIt(20);
			
			FilterBuilder builder = new FilterBuilder();
			
			System.out.println("Filtr to: " + builder.makeCurrentTimeFilter());
			
			LinesCounter count = new LinesCounter(args[0],builder.makeFilter(1));
			
			System.out.println("Ksiazki przeczytane: " + count.countLinesWithFilter());
			
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			dataset.setValue(count.countLinesWithFilter(), "Finished", "Books");
			dataset.setValue(1, "Finished", "Games");
			dataset.setValue(1, "Finished", "Dev Posts");
			dataset.setValue(0, "Finished", "Blog Posts");
			dataset.setValue(6, "Finished", "Commits");
			
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
}
