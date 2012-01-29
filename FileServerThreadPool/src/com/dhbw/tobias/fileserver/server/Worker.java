package com.dhbw.tobias.fileserver.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Worker implements Runnable {

	private DatagramPacket packet;
	private FileAccessMonitor monitor;

	public Worker(DatagramPacket packet) {
		this.packet = packet;
	}

	@Override
	public void run() {
		String message = new String(packet.getData(), 0, packet.getLength());
		System.out.println(message);
		String command = message.substring(0, message.indexOf(" "));
		String file = message.substring(message.indexOf(" ") + 1,
				message.indexOf(","));
		String antwort = null;

		if (command.equals("READ")) {
			int lineNo = Integer.parseInt(message.substring(message
					.indexOf(",") + 1));
			monitor = FileAccessMonitor.getHandle(file);
			monitor.startRead();
			try {
				Thread.sleep(4000);
				antwort = monitor.read(lineNo);
				if (antwort == null) {
					antwort = "Lesen fehlgeschlagen";
				}
				System.out.println("READ FILE");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			monitor.endRead();
		} else if (command.equals("WRITE")) {
			int lineNo = Integer.parseInt(message.substring(
					message.indexOf(",") + 1, message.lastIndexOf(",")));
			String text = message.substring(message.lastIndexOf(",") + 1);
			monitor = FileAccessMonitor.getHandle(file);
			monitor.startWrite();
			try {
				Thread.sleep(4000);
				if (monitor.write(lineNo, text)) {
					antwort = "Erfolgreich geschrieben";
				} else {
					antwort = "Schreiben fehlgeschlagen";
				}
				System.out.println("WRITE FILE");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			monitor.endWrite();
		} else {
			System.out.println("Unknown Command");
			antwort = "Unbekannter Befehl";
		}
		byte[] data = new byte[antwort.getBytes().length];
		data = antwort.getBytes();
		DatagramPacket outPacket = new DatagramPacket(data, data.length,
				packet.getAddress(), packet.getPort());
		try {
			DatagramSocket outSocket = new DatagramSocket();
			outSocket.send(outPacket);
			outSocket.close();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
