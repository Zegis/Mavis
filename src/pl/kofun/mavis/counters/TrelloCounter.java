package pl.kofun.mavis.counters;

import java.util.List;

import pl.kofun.mavis.Interfaces.Counter;
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
		this.filter = trimFilter(filter);
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

	@Override
	public void setPeriodToCount(PeriodToCount newPeriod) {
		if(!newPeriod.Filter.equals(filter))
		{
			this.filter = trimFilter(newPeriod.Filter);
		}	
	}
	
	private String trimFilter(String filterToApply)
	{
		// We need to trim filter, as typical filter has parenthesis
		if(filterToApply.charAt(0) == '(' 
				&& filterToApply.charAt(filterToApply.length() -1) == ')')
			return filterToApply.substring(1, filterToApply.length()-1);
		else
			return filterToApply;
	}
}
