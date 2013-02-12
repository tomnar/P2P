package com.au.p2p;

import java.io.Serializable;

public class PeerData implements Serializable{
	private String name;
	private String address;
	private int port;
	private int capacity;
	
	public PeerData(String name, String address, int port, int capacity){
		this.name = name;
		this.address = address;
		this.port = port;
		this. capacity = capacity;
	}
	
	public String toString(){
		return "Peer: " + name;
	}
}
