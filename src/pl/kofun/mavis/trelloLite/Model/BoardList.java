package pl.kofun.mavis.trelloLite.Model;

public class BoardList {

	private String name;
	private String id;
	
	public BoardList(String name, String id)
	{
		this.name = name;
		this.id = id;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getId(){
		return this.id;
	}
	
}
