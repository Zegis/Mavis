package pl.kofun.mavis.utils;

import java.net.MalformedURLException;
import java.net.URL;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;


public class BlogClient {

	private XmlRpcClientConfigImpl config;
	private XmlRpcClient client;
	
	public BlogClient()
	{
		config = new XmlRpcClientConfigImpl();
		client = new XmlRpcClient();
	}
	
	public void Configure(String blogUrl)
	{
	
		try		{	
			config.setServerURL(new URL(blogUrl));
			client.setConfig(config);
		}
		catch(MalformedURLException ex)		{
			System.out.println("Provide valid URL!");
		}
	}
	
	public int Call(String procedureName, Object[] params)
	{
		int result;
		try
		{			
			result = (int) client.execute(procedureName,params);
			
		}catch(Exception ex)
		{
			result = -1;
		}
		
		return result;
	}
	
}
