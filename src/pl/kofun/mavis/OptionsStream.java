package pl.kofun.mavis;

import java.util.Hashtable;

interface OptionsStream {

	public Hashtable<String, String> load(String[] args);
	
	public boolean save(Hashtable<String, String> options);
	
}
