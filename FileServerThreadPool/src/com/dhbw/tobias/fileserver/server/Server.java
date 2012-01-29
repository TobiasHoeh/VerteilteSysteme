package com.dhbw.tobias.fileserver.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {

	private static int DEFAULT_PORT = 5000;
	private static int MAX_PACKET_SIZE = 65507;

	public static void main(String[] args) {
		try {
			DatagramSocket server = new DatagramSocket(DEFAULT_PORT);
			System.out.println("Server up and running: Waiting for requests");
			while (true) {
				byte[] data = new byte[65507];
				DatagramPacket packet = new DatagramPacket(data,
						MAX_PACKET_SIZE);
				server.receive(packet);
				Worker worker = new Worker(packet);
				Thread workerThread = new Thread(worker);
				workerThread.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
