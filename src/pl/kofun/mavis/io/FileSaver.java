package pl.kofun.mavis.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Optional;

import pl.kofun.mavis.Interfaces.OptionsSaver;
import pl.kofun.mavis.utils.FileNameCreator;
import pl.kofun.mavis.utils.saveFileNameCreator;

public class FileSaver implements OptionsSaver {

	@Override
	public void save(Hashtable<String, String> options) {
		
		FileNameCreator fileNamecreator = new saveFileNameCreator();
		
		String fileName = fileNamecreator.createName(Optional.of((options.get("save"))));
		RandomAccessFile savefile;
		try
		{
			savefile = new RandomAccessFile(fileName, "rw");

			savefile.setLength(0); 
			
			StringBuilder saveEntry = new StringBuilder();
			
			for(Entry<String,String> entry : options.entrySet())
			{
				if("save".equalsIgnoreCase(entry.getKey()))
					continue;
				
				saveEntry.setLength(0);
				saveEntry.append(entry.getKey());
				saveEntry.append(" : ");
				saveEntry.append(entry.getValue());
				saveEntry.append("\n");
				
				savefile.writeBytes(saveEntry.toString());
				
			}
			
			savefile.close();
		}catch(IOException e){
			System.out.println(e);
		}		
	}

}