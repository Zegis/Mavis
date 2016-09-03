package pl.kofun.mavis.trelloLite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class ApacheHttpClient {

	private CloseableHttpClient client;
	private String key;
	private String token;
	
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
		// https://api.trello.com/1//board/57bc03ee2d424a313f5f5d91/lists?fields=name,id&key=4ec0ac42a9bf44fae0460b41b94fb494&token=ae49934c875291b3649a5ba151076325630f6b41319299989a72a4eab8946b4e
		
		String url = "https://api.trello.com/1//board/57bc03ee2d424a313f5f5d91/lists?fields=name,id&key="
				+ key + "&token=" + token;
		
		HttpGet get = new HttpGet(url);
		try
		{
		CloseableHttpResponse response1 = client.execute(get);
		HttpEntity entity = response1.getEntity();
		
		String inputLine;
		BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
		while((inputLine = br.readLine()) != null)
		{
				System.out.println(inputLine);
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
