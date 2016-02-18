package pl.kofun.mavis;

public class DefaultTask implements MainTask {

	@Override
	public void execute() {
		System.out.println("Usage:");
		System.out.println("Mavis [cmd] (options)!");
		System.out.println("Available cmd: Ohil, Mp and Yp");
	}

}
