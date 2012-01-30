package com.dhbw.tobias.fileserver.server;

import java.util.Vector;

public class WorkerPoolMonitor {

	private Vector<Worker> threadPool = new Vector<Worker>();

	public WorkerPoolMonitor(int workerCount, Server server) {
		for (int i = 0; i < workerCount; i++) {
			Worker worker = new Worker(this);
			worker.start();
		}
	}

	public synchronized Worker getWorker() {
		if (threadPool.isEmpty()) {
			System.out
					.println("**************** Server ist waiting for new Worker **********************");
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// System.out.println("Server ist waiting for new Worker");
		Worker worker = threadPool.get(0);
		System.out.println("** Returning Worker: " + worker.getId());
		threadPool.remove(worker);
		return worker;
	}

	public synchronized void returnWorker(Worker worker) {
		threadPool.add(worker);
		notify();
	}
}
