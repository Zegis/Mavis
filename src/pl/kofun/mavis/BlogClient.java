package pl.kofun.mavis;

import java.net.URL;
import java.util.Scanner;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;


public class BlogClient {

	public static void test()
	{
	
		try{
		
			
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL("http://blog.kofun.pl/xmlrpc.php"));
			
			XmlRpcClient client = new XmlRpcClient();
			client.setConfig(config);
			

			System.out.print("Enter login:");
			String log = getPassword();
			
			System.out.print("Enter password:");
			String pswd = getPassword();		
			
					
			Object[] params = new Object[]{log, pswd};
			
			Object[] result = (Object[]) client.execute("wp.getUsersBlogs",params);
			
			System.out.println(result[0].toString());
			
		}catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
	
	
	private static String getPassword()
	{
		String ret;
		
		Scanner input = new Scanner(System.in); // is not closed because it'd close System.in too. Let VM handle it.
		
		ret = input.nextLine();
		
		return ret;
	}
	
}
