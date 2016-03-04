package pl.kofun.mavis;

import java.util.Hashtable;

interface OptionsParser {

	public Hashtable<String, String> load(String[] args);	
}
