package pl.kofun.mavis;


import pl.kofun.mavis.Interfaces.MainTask;
import pl.kofun.mavis.utils.stringAdjuster;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;

public class Library implements MainTask{
	
	private FilterLineReader reader;
	private RandomAccessFile savefile;
	
	public Library(Options options)
	{
		
		if(options.containsKey("sourcefileName") && options.containsKey("targetfileName") && options.containsKey("filter"))
		{
		
			reader = new FilterLineReader(options.get("sourcefileName"), options.get("filter"));
			
			try
			{
			savefile = new RandomAccessFile(options.get("targetfileName"), "rw");
			}
			catch(IOException e)
			{
				System.out.println(e);
			}
		}
	}
	
	public void execute()
	{
		if(reader != null)
		{
			LinkedList<String> books = reader.getLinesAfterFilterAndMoveIt(20);
			books = stringAdjuster.AdjustToBookList(books);
			
			try
			{
				savefile = new RandomAccessFile("LibraryPost.txt", "rw");
	
				savefile.setLength(0);
				for(int i = 0; i < books.size(); ++i)
				{
					savefile.writeBytes(books.get(i));
				}
				savefile.close();
				System.out.println("All green");
			}catch(IOException e){
				System.out.println(e);
			}
			
			
		}
		else
		{
			this.usage();
		}
	}

	@Override
	public void usage() {
		System.out.println("For Library you must define:");
		System.out.println("Source filename as -s (argument) or sourcefileName : (argument) inside txt file");
		System.out.println("Target filename as -t (argument) or targetfileName : (argument) inside txt file");
		System.out.println("Filter to use as -f (argument) or filter : (argument) inside txt file");		
	}

}
