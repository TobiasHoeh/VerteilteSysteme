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

				String message = new String(packet.getData(), 0,
						packet.getLength());
				packet.setLength(MAX_PACKET_SIZE);
				System.out.println(message);
				if (message.equals("READ")) {
					System.out.println("READ FILE");
				} else if (message.equals("WRITE")) {
					System.out.println("WRITE FILE");
				} else {
					System.out.println("Unknown Command");
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
