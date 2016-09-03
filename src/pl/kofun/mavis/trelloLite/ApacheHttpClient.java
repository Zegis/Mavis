package pl.kofun.mavis.trelloLite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;

import pl.kofun.mavis.trelloLite.Model.BoardList;

public class ApacheHttpClient {

	private CloseableHttpClient client;
	private String key;
	private String token;
	
	private static String baseUrl = "https://api.trello.com/1/";
	
	public ApacheHttpClient()
	{
		client = HttpClients.createMinimal();
	}
	
	public void configure(String key, String token)
	{
		this.key = key;
		this.token = token;
	}
	
	public void get(){		
		String url = baseUrl + "board/57bc03ee2d424a313f5f5d91/lists?fields=name,id&key="
				+ key + "&token=" + token;
		
		HttpGet get = new HttpGet(url);
		try
		{
		CloseableHttpResponse response1 = client.execute(get);
		HttpEntity entity = response1.getEntity();
		
		String json;
		BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
		json = br.readLine();
		Gson gson = new Gson();
		if(json != null)
		{
			List<BoardList> lists = Arrays.asList(gson.fromJson(json, BoardList[].class));
			for(BoardList list : lists)
			{
				if(list.getName().equals("TO DO"))
					System.out.print(list.getName() + " " + list.getId() + "\n");
			}
		}
		br.close();
		
		}
		catch(Exception e)
		{
			
		}
		finally{
			get.releaseConnection();
		}
	}
	
	public void finalize()
	{
		try {
			client.close();
		} catch (IOException e) {
		}
	}
	
}
