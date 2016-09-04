package pl.kofun.mavis.trelloLite;

import pl.kofun.mavis.trelloLite.Model.BoardList;
import pl.kofun.mavis.trelloLite.Model.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;


public class TrelloLite {

	private ApacheHttpClient client;
	private String key;
	private String token;
	
	private static String baseUrl = "https://api.trello.com/1/";
	private static String listsUrl = "/board/57bc03ee2d424a313f5f5d91/lists?fields=name,id";
	
	private static String cardsUrl_prefix = "/list/";
	private static String cardsUrl_suffix = "/cards?fields=name";
	
	public TrelloLite(String key, String token){
		this.key = key;
		this.token = token;
		
		client = new ApacheHttpClient();
	}
	
	public List<BoardList> getListsFromBoard()
	{
		List<BoardList> ret = new ArrayList<BoardList>();
		
		String url = createUrl();
		
		String json = client.get(url);
		
		if(json != "")
		{
			Gson gson = new Gson();			
			ret = Arrays.asList(gson.fromJson(json, BoardList[].class));
		}
		
		return ret;
	}
	
	public List<Card> getCardsFromList(String listId)
	{
		List<Card> ret = new ArrayList<Card>();
		
		String url = createUrl(listId);
		
		String json = client.get(url);
		
		if(json != "")
		{
			Gson gson = new Gson();
			ret = Arrays.asList(gson.fromJson(json, Card[].class));
		}
		
		return ret;
	}
	
	private String createUrl()
	{
		StringBuilder url = new StringBuilder(baseUrl);
		
		url.append(listsUrl);
		url.append(prepareKeyToken());
		
		return url.toString();
	}
	
	private String createUrl(String listId)
	{
		StringBuilder url = new StringBuilder(baseUrl);
		
		url.append(cardsUrl_prefix);
		url.append(listId);
		url.append(cardsUrl_suffix);
		url.append(prepareKeyToken());
		
		return url.toString();
	}
	
	private String prepareKeyToken()
	{
		return "&key=" + key + "&token=" + token;
	}
}
