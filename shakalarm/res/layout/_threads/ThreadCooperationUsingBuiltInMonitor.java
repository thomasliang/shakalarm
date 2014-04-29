package _threads;

import java.util.concurrent.*;

public class ThreadCooperationUsingBuiltInMonitor {
	private static Account account = new Account();

	public static void main(String[] args) {
		// Create a thread pool with three threads
		System.out.println("Deposit Thread\t\tTwo Withdraw Threads\tBalance");
		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.execute(new DepositTask());
		executor.execute(new WithdrawTask());
		executor.execute(new WithdrawTask());
		executor.shutdown();
	}

	// A task for adding an amount to the account
	public static class DepositTask implements Runnable {
		public void run() {
			try { // Purposely delay it to let the withdraw method proceed
				while (true) {
					account.deposit((int) (Math.random() * 10) + 1);
					Thread.sleep(1000);
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	// A task for subtracting an amount from the account
	public static class WithdrawTask implements Runnable {
		public void run() {
			while (true) {
				account.withdraw((int) (Math.random() * 20) + 1);
			}
		}
	}

	// An inner class for account
	private static class Account {

		private int balance = 0;

		public int getBalance() {
			return balance;
		}

		public synchronized void withdraw(int amount) {
			try {
				while (balance < amount) {
					System.out.println("\t\t\tWithdraw " + amount
							+ " pending... \t" + getBalance());
					// if (balance > 0) notify();
					wait();
				}
				balance -= amount;
				// if (balance > 0) notify();
				System.out.println("\t\t\tWithdraw " + amount + "\t\t"
						+ getBalance());
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		public synchronized void deposit(int amount) {
			balance += amount;
			System.out.println("Deposit " + amount + "\t\t\t\t\t"
					+ getBalance());

			// Notify thread waiting on the condition
			notifyAll();
		}
	}
}
