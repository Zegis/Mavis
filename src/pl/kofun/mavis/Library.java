package pl.kofun.mavis;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;

public class Library implements MainTask{
	
	private FilterLineReader reader;
	
	public Library(String filename)
	{
		reader = new FilterLineReader(filename, "-- 100 lat");
	}
	
	public void execute()
	{
		LinkedList<String> books = reader.getLinesAfterFilterAndMoveIt(20);
		
		RandomAccessFile savefile;
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
