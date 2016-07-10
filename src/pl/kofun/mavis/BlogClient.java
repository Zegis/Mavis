package pl.kofun.mavis;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

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
	
		try{
		
			
			config.setServerURL(new URL(blogUrl));
			
			client.setConfig(config);
		}catch(MalformedURLException ex)
		{
			System.out.println("Provide valid URL!");
		}
	}
	
	public void Call(String procedureName, Object[] params)
	{
		try
		{			
			int result = (int) client.execute(procedureName,params);
			
			System.out.println(result);
			
		}catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
	
}
