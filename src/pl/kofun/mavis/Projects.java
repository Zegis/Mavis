package pl.kofun.mavis;

import pl.kofun.mavis.Interfaces.MainTask;

public class Projects implements MainTask {

	@Override
	public void execute() {
		System.out.println("I'll generate projects summary!");
	}

	@Override
	public void usage() {
		System.out.println("For project you must define:");

	}

}
