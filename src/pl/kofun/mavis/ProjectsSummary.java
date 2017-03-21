package pl.kofun.mavis;

import java.io.FileReader;
import java.io.RandomAccessFile;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import pl.kofun.mavis.Interfaces.MainTask;
import pl.kofun.mavis.utils.stringAdjuster;

public class ProjectsSummary implements MainTask {

	private String sourceFileName;
	private String targetFileName;
	private String projectToPrint;
	private String tagUrl;
	
	public ProjectsSummary(Options options) {
		if(options.containsKey("projectsfileName") && options.containsKey("targetfileName") && options.containsKey("projectName"))
		{
			sourceFileName = options.get("projectsfileName");
			targetFileName = options.get("targetfileName");
			projectToPrint = options.get("projectName");
			tagUrl = options.get("tagUrl");
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
				
				for (Project project : projects) {
					if(projectToPrint.equals("All") || projectToPrint.equals(project.getName()))
						saveFile.writeBytes(stringAdjuster.convertProjectToPost(project,tagUrl));
				}
				
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
		System.out.println("Project name to print as -n (argument) or -a for all ");
	}

}
