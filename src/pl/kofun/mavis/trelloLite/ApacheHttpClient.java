package pl.kofun.mavis.trelloLite;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

class ApacheHttpClient {

	private CloseableHttpClient client;
	
	
	public ApacheHttpClient()
	{
		client = HttpClients.createMinimal();
	}
	
	public String get(String url){		
		
		String ret;
		HttpGet get = new HttpGet(url);
		try
		{
		CloseableHttpResponse response1 = client.execute(get);
		HttpEntity entity = response1.getEntity();
		
		ret = EntityUtils.toString(entity); 
		
		}
		catch(Exception e)
		{
			ret = "";
		}
		finally{
			get.releaseConnection();
		}
		
		return ret;
	}
	
	public void finalize()
	{
		try {
			client.close();
		} catch (IOException e) {
		}
	}
	
}
