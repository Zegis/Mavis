package pl.kofun.mavis.tasks;

import pl.kofun.mavis.Interfaces.MainTask;

public class DefaultTask implements MainTask {

	@Override
	public void execute() {
		System.out.println("Incorrect task:");
		System.out.println("Usage:");
		System.out.println("Mavis [cmd] (options)!");
		System.out.println("Available cmd: Ohil, Mp and Yp");
	}

	@Override
	public void usage() {
		this.execute();		
	}

}
