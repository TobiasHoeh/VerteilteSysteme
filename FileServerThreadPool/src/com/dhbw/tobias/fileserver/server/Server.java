package com.dhbw.tobias.fileserver.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {

	private static int DEFAULT_PORT = 5000;
	private static int MAX_PACKET_SIZE = 65507;

	public static void main(String[] args) {
		Server server = new Server();
		server.run();
	}

	public void run() {
		try {
			DatagramSocket server = new DatagramSocket(DEFAULT_PORT);
			WorkerPoolMonitor monitor = new WorkerPoolMonitor(4, this);
			System.out.println("Server up and running: Waiting for requests");
			while (true) {
				byte[] data = new byte[65507];
				DatagramPacket packet = new DatagramPacket(data,
						MAX_PACKET_SIZE);
				server.receive(packet);
				Worker workerThread = monitor.getWorker();
				workerThread.setDatagramPacket(packet);
				workerThread.activateThread();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
