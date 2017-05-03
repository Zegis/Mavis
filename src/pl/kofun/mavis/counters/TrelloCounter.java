package pl.kofun.mavis.counters;

import java.util.List;

import pl.kofun.mavis.trelloLite.TrelloLite;
import pl.kofun.mavis.trelloLite.Model.Card;
import pl.kofun.mavis.trelloLite.Model.BoardList;

public class TrelloCounter implements Counter {

	TrelloLite client;
	private String filter;
	
	public TrelloCounter(String key, String token)
	{
		client = new TrelloLite(key, token);
	}
	
	public TrelloCounter(String key, String token, String filter)
	{
		client = new TrelloLite(key, token);
		// We need to trim filter, as typical filter has parenthesis
		this.filter = filter.substring(1, filter.length()-1);
	}
	
	public void setFilter(String newFilter)
	{
		if(!newFilter.equals(filter))
		{
			// We need to trim filter, as typical filter has parenthesis
			this.filter = newFilter.substring(1,newFilter.length()-1);
		}
	}
	
	@Override
	public int count()
	{
		int ret = 0;
		List<Card> cards = null;
		
		List<BoardList> lists = client.getListsFromBoard();
		
		for(BoardList current : lists)
		{
			if(current.getName().toLowerCase().equals(filter))
			{
				cards = client.getCardsFromList(current.getId());
				ret = cards.size();
			}
		}			
		
		return ret;
	}
}
