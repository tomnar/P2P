package com.au.p2p;


import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.server.RequestProcessorFactoryFactory;

public class PeerRequestProcessorFactoryFactory implements RequestProcessorFactoryFactory {

	private RequestProcessorFactory factory;
	private Peer mPeer;

	public PeerRequestProcessorFactoryFactory(Peer peer){
		this.mPeer = peer;
		factory = new PeerRequestProcessorFactory();
	}

	@Override
	public RequestProcessorFactory getRequestProcessorFactory(@SuppressWarnings("rawtypes") Class aClass) throws XmlRpcException {
		return factory;
	}

	private class PeerRequestProcessorFactory implements RequestProcessorFactory{
		@Override
		public Object getRequestProcessor(XmlRpcRequest arg0) throws XmlRpcException {
			return mPeer;
		}

	}


}