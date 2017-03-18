package pl.kofun.mavis;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import pl.kofun.mavis.Interfaces.MainTask;
import pl.kofun.mavis.utils.stringAdjuster;

public class ProjectsSummary implements MainTask {

	private String sourceFileName;
	private String targetFileName;
	
	public ProjectsSummary(Options options) {
		if(options.containsKey("projectsfileName") && options.containsKey("targetfileName"))
		{
			sourceFileName = options.get("projectsfileName");
			targetFileName = options.get("targetfileName");			
		}
	}

	@Override
	public void execute() {
		if(sourceFileName != null && targetFileName != null)
		{
			
			System.out.println("I'll generate projects summary!");
			Gson gson = new Gson();
			try {
				List<Project> projects = gson.fromJson(new FileReader(sourceFileName),
													new TypeToken<Collection<Project>>(){}.getType());
				
				RandomAccessFile saveFile = new RandomAccessFile(targetFileName, "rw");
				saveFile.setLength(0);
				
				saveFile.writeBytes(stringAdjuster.convertProjectToPost(projects.get(0)));
				
				saveFile.close();
				System.out.println("All green!");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			this.usage();
		}
	}

	@Override
	public void usage() {
		System.out.println("For project you must define:");
		System.out.println("Projects filename as -p (argument) or sourcefileName : (argument) inside txt file");
		System.out.println("Target filename as -t (argument) or targetfileName : (argument) inside txt file");
	}

}
