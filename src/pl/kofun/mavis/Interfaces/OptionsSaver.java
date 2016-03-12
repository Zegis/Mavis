package pl.kofun.mavis.Interfaces;

import java.util.Hashtable;

public interface OptionsSaver {
	public Hashtable<String, String> save(Hashtable<String, String> options);
}
