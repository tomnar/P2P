package com.au.p2p;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

public class Peer {
	private String name;
	private String address;
	private int port;
	private int capacity;
	private XmlRpcClientConfigImpl clientConfig;
	private XmlRpcClient client;
	
	public Peer(){
		
	}
	
	public Peer(String name, String address, int port, int capacity){
		this.name = name;
		this.address = address;
		this.port = port;
		this. capacity = capacity;
		
//		setupServer();
//		setupClient();
	}
	
	public void ping(String address, int port){
		//return "boo";
		System.out.println("boo");
//		try {
//			clientConfig = new XmlRpcClientConfigImpl();
//			clientConfig.setServerURL(new URL("http://" + address + ":" + port));
//			clientConfig.setEnabledForExtensions(true);
//			client = new XmlRpcClient();
//			client.setConfig(clientConfig);
//			
//			Object[] params = new Object[]{new Integer(33), new Integer(9)};
//		    Integer result = (Integer) client.execute("Peer.add", params);
//		} 
//		catch (MalformedURLException e) { e.printStackTrace();
//		} 
//		catch (XmlRpcException e) { e.printStackTrace();
//		}
	}
	
	public int add(int i1, int i2) {
		return i1 + i2;
	}

	public void hello(String address, int port){
		System.out.println("hello called" + address + port);
	}
	
	private void setupServer(){
		try{
			//Creating a webserver on the defined port
			WebServer webServer = new WebServer(port);		
			XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();

			//Setting the mapping to the classes up
			PropertyHandlerMapping phm = new PropertyHandlerMapping();
			phm.setVoidMethodEnabled(true);
			phm.addHandler(Peer.class.getName(), this.getClass());
			xmlRpcServer.setHandlerMapping(phm);

			//Configuring the server
			XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
			serverConfig.setEnabledForExtensions(true);
			serverConfig.setContentLengthOptional(false);

			//Starting the webserver
			webServer.start();
		}
		catch(XmlRpcException e){ e.printStackTrace();
		}
		catch(IOException e){ e.printStackTrace();
		}
	}

	private void setupClient(){
		clientConfig = new XmlRpcClientConfigImpl();
		clientConfig.setEnabledForExtensions(true);
		client = new XmlRpcClient();
		client.setConfig(clientConfig);

	}
}
