package com.au.p2p;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.util.ClientFactory;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

public class PeerImpl implements Peer{
	private String name;
	private String address;
	private int port;
	private int capacity;
	

	
	public PeerImpl(String name, String address, int port, int capacity){
		this.name = name;
		this.address = address;
		this.port = port;
		this. capacity = capacity;
		
		setupServer();
	}
	
	public void ping(String address, int port){
		try {
//			XmlRpcClientConfigImpl clientConfig = new XmlRpcClientConfigImpl();
//			clientConfig.setServerURL(new URL("http://" + address + ":" + port));
//			clientConfig.setEnabledForExtensions(true);
//			XmlRpcClient client = new XmlRpcClient();
//			client.setConfig(clientConfig);
//
//			Object[] params = new Object[]{};
//			PeerData result = (PeerData) client.execute("Peer.pong", params);
//			System.out.println(result);
		    
			XmlRpcClientConfigImpl clientConfig = new XmlRpcClientConfigImpl();
			XmlRpcClient client = new XmlRpcClient();
		    clientConfig.setServerURL(new URL("http://" + address + ":" + port));
		    clientConfig.setEnabledForExtensions(true);
		    client.setConfig(clientConfig);
			ClientFactory factory = new ClientFactory(client);
			Peer otherPeer = (Peer) factory.newInstance(Peer.class);
			System.out.println(otherPeer.pong());
			//otherPeer.toString();
		} 
		catch (MalformedURLException e) { e.printStackTrace();
		} 
	}
	
	public PeerData pong(){
		return new PeerData(name, address, port, capacity);
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
			phm.setRequestProcessorFactoryFactory(new PeerRequestProcessorFactoryFactory(this));
			phm.addHandler(Peer.class.getName(), this.getClass());
			xmlRpcServer.setHandlerMapping(phm);

			//Configuring the server
			XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
			serverConfig.setEnabledForExtensions(true);
			serverConfig.setContentLengthOptional(true);

			//Starting the webserver
			webServer.start();
		}
		catch(XmlRpcException e){ e.printStackTrace();
		}
		catch(IOException e){ e.printStackTrace();
		}
	}
	
	public String getAddress(){
		return address;
	}
	public int getPort(){
		return port;
	}
	
	public String toString(){
		return "hi";
	}
}
