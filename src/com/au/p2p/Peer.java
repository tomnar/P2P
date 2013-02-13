package com.au.p2p;

public interface Peer {
	public void ping(String address, int port);
	public PeerData pong();
	public String toString();
}

