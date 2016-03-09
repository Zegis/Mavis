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
		else
		{
			System.out.println("Usage: Mavis ohil -f <filter> -t <filename> -s <filename>");
		}
	}
	
	public void execute()
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
		}catch(IOException e){
			System.out.println(e);
		}
	}

}
