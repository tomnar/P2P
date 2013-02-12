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
		
		p.ping(p2.getAddress(), p2.getPort());
	}
}
