package _threads;

import java.util.concurrent.*;

public class ConsumerProducerUsingBlockingQueue {
  private static ArrayBlockingQueue<Integer> buffer =
    new ArrayBlockingQueue<Integer>(2);

  public static void main(String[] args) {
    // Create a thread pool with two threads
    ExecutorService executor = Executors.newFixedThreadPool(2);
    executor.execute(new ProducerTask());
    executor.execute(new ConsumerTask());
    executor.shutdown();
  }

  // A task for adding an int to the buffer
  private static class ProducerTask implements Runnable {
    public void run() {
      try {
        int i = 0;
        while (true) {
          System.out.println("Producer wants to write " + ++i);
          buffer.put(i); // Add any value to the buffer, say, 1
          System.out.println("Producer wrote " + i);
          // Put the thread into sleep
          Thread.sleep((int)(Math.random() * 1000));
        }
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    }
  }

  // A task for reading and deleting an int from the buffer
  private static class ConsumerTask implements Runnable {
    public void run() {
      try {
        while (true) {
          System.out.println("\t\t\t\tConsumer wants to read ");
          // Put the thread into sleep
          System.out.println("\t\t\t\tConsumer read " + buffer.take());
          Thread.sleep((int)(Math.random() * 1000));
        }
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    }
  }
}
