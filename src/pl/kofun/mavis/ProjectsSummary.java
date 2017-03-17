package pl.kofun.mavis;

import java.io.FileReader;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import pl.kofun.mavis.Interfaces.MainTask;

public class ProjectsSummary implements MainTask {

	private Options options;
	
	public ProjectsSummary(Options options) {
		this.options = options; 
	}

	@Override
	public void execute() {
		try
		{
		System.out.println("I'll generate projects summary!");
		Gson gson = new Gson();
		List<Project> projects = gson.fromJson(new FileReader(options.get("projectsfileName")),
												new TypeToken<Collection<Project>>(){}.getType());
		System.out.println("Loaded!");
		}
		catch(Exception ex)
		{
			System.out.println("Error!");
		}
	}

	@Override
	public void usage() {
		System.out.println("For project you must define:");

	}

}
