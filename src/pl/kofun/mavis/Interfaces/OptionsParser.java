package pl.kofun.mavis.Interfaces;

import java.util.Hashtable;

public interface OptionsParser {

	public Hashtable<String, String> load(String[] args);	
}
