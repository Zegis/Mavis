package pl.kofun.mavis;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Hashtable;
import java.util.LinkedList;

public class Library implements MainTask{
	
	private FilterLineReader reader;
	private RandomAccessFile savefile;
	
	public Library(Hashtable<String,String> options)
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
	
	public void execute(Hashtable<String,String> options)
	{
		LinkedList<String> books = reader.getLinesAfterFilterAndMoveIt(20);
		
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
