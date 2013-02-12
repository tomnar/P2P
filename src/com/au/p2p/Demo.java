package com.au.p2p;

import java.io.IOException;
import java.net.URL;

import org.apache.xmlrpc.XmlRpcConfigImpl;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.webserver.WebServer;

public class Demo {
	public static void main(String [ ] args) throws XmlRpcException, IOException{
		Peer p = new Peer("1", "127.0.0.1", 8095, 5);
		Peer p2 = new Peer("1", "127.0.0.1", 8096, 5);

		//Creating a webserver on the defined port
		WebServer webServer = new WebServer(8090);		
		XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();

		//Setting the mapping to the classes up
		PropertyHandlerMapping phm = new PropertyHandlerMapping();
		phm.setVoidMethodEnabled(true);
		phm.addHandler("Peer", com.au.p2p.Peer.class);
		xmlRpcServer.setHandlerMapping(phm);

		//Configuring the server
		((XmlRpcConfigImpl) webServer.getXmlRpcServer().getConfig()).setEnabledForExtensions(true);
		((XmlRpcConfigImpl) webServer.getXmlRpcServer().getConfig()).setContentLengthOptional(true);
		
		webServer.start();

		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL("http://127.0.0.1:8090/xmlrpc"));
		config.setEnabledForExtensions(true);
		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);
		Object[] params = new Object[]{new String("127.0.0.1"), new Integer(8096)};
		String result = (String) client.execute("Peer.ping", params);
		System.out.println(result);

	}
}
