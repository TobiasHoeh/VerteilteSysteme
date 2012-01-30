package com.dhbw.tobias.fileserver.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileAccessMonitor {
	private String fileName;
	private static ArrayList<FileAccessMonitor> fileList = new ArrayList<FileAccessMonitor>();
	private int readerCount;
	private int writerCount;
	private boolean activeWriter;

	private FileAccessMonitor(String fileName) {
		File file = new File(fileName);
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.fileName = fileName;
		fileList.add(this);
	}

	public static FileAccessMonitor getHandle(String fileName) {
		for (int i = 0; i < fileList.size(); i++) {
			FileAccessMonitor monitor = fileList.get(i);
			if (monitor.fileName.equals(fileName)) {
				return monitor;
			}
		}
		return new FileAccessMonitor(fileName);
	}

	public synchronized void startRead() {
		while (writerCount > 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		readerCount++;
	}

	public synchronized void endRead() {
		readerCount--;
		if (readerCount == 0) {
			notifyAll();
		}
	}

	public synchronized void startWrite() {
		writerCount++;
		while (activeWriter || readerCount > 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		activeWriter = true;
	}

	public synchronized void endWrite() {
		writerCount--;
		activeWriter = false;
		notifyAll();
	}

	public String read(int lineNo) {
		File file = new File(fileName);
		String line = null;
		try {
			BufferedReader fileReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(file)));
			int counter = 0;
			while ((line = fileReader.readLine()) != null || counter <= lineNo) {
				counter++;
				if (counter == lineNo) {
					break;
				}
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}

	public boolean write(int lineNo, String text) {
		File file = new File(fileName);
		File tmpFile = new File(fileName + ".tmp");
		try {
			String line = null;
			int counter = 0;
			BufferedReader fileReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(file)));
			PrintWriter fileWriter = new PrintWriter(tmpFile);
			while ((line = fileReader.readLine()) != null) {
				counter++;
				if (counter != lineNo) {
					fileWriter.println(line);
				} else {
					fileWriter.println(text);
				}
				fileWriter.flush();
			}
			if (counter < lineNo) {
				fileWriter.println(text);
				fileWriter.flush();
			}
			fileWriter.close();
			fileReader.close();
			if (file.delete()) {
				tmpFile.renameTo(file);
				tmpFile.delete();
			}
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}