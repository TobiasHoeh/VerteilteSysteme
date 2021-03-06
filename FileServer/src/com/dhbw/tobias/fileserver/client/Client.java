package com.dhbw.tobias.fileserver.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client {
	private static int SERVER_PORT = 5000;

	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket();
			InetAddress server = InetAddress.getByName("localhost");

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.print("Type command to send!");
			System.out.println("(''READ file,lineNo'' or 'x' to exit)");
			String command = null;
			System.out.print("> "); // print user prompt
			while ((command = reader.readLine()) != null) {
				if (command.equals("ENDE")) {
					System.exit(1);
				} else {
					DatagramPacket packet = new DatagramPacket(
							command.getBytes(), command.length(), server,
							SERVER_PORT);
					socket.send(packet);
				}
			}

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
