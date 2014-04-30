package _threads;

public class TaskThreadDemo {
	public static void main(String[] args) {
		// Create tasks
		Runnable printA = new PrintChar('a', 100);
		Runnable printB = new PrintChar('b', 100);
		//Runnable print100 = new PrintNum(100);
	    Runnable print100 = new PrintNumJoin(100);

		// Create threads
		Thread thread1 = new Thread(printA);
		Thread thread2 = new Thread(printB);
		Thread thread3 = new Thread(print100);

		// Start threads
		thread3.start();
		thread2.start();
		thread1.start();
	}
}

// The task for printing a specified character in specified times
class PrintChar implements Runnable {
	private char charToPrint; // The character to print
	private int times; // The times to repeat

	/**
	 * Construct a task with specified character and number of times to print
	 * the character
	 */
	public PrintChar(char c, int t) {
		charToPrint = c;
		times = t;
	}

	/**
	 * Override the run() method to tell the system what the task to perform
	 */
	public void run() {
		for (int i = 0; i < times; i++) {
			System.out.print(charToPrint);
		}
	}
}

// The task class for printing number from 1 to n for a given n
class PrintNum implements Runnable {
	int lastNum;

	/** Construct a task for printing 1, 2, ... i */
	PrintNum(int n) {
		lastNum = n;
	}

	/** Tell the thread how to run */
	public void run() {
		for (int i = 1; i <= lastNum; i++) {
			System.out.print(" " + i);
	 	    //Thread.yield();
		
			/* try { if (i >= 50) Thread.sleep(1); } 
			catch (InterruptedException ex) { } */
			
		}
	}
}

class PrintNumJoin extends PrintNum {
	PrintNumJoin(int n) {
		super(n);
	}

	/** Create a printC thread and join it at 50 */
	@Override
	public void run() {
		Thread printC = new Thread(new PrintChar('c', 500));
		printC.start();
		try {
			for (int i = 1; i <= lastNum; i++) {
				System.out.print(" " + i);
				if (i == 50)
					printC.join();
			}
		} catch (InterruptedException e) {
		}
	}
}
