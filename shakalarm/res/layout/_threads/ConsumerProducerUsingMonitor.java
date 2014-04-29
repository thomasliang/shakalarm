package _threads;

import java.util.concurrent.*;

public class ConsumerProducerUsingMonitor {
  private static Buffer buffer = new Buffer();

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
          buffer.write(i); // Add a value to the buffer
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
          System.out.println("\t\t\t\tConsumer wants to read a value"); 
          System.out.println("\t\t\t\tConsumer read " + buffer.read());
          // Put the thread into sleep
          Thread.sleep((int)(Math.random() * 1000));
        }
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    }
  }

  // An inner class for buffer
  private static class Buffer {
    private static final int CAPACITY = 2; // buffer size
    private java.util.LinkedList<Integer> queue =
      new java.util.LinkedList<Integer>();

    synchronized public void write(int value) {
      try {
        while (queue.size() == CAPACITY) {
          System.out.println("Wait for notFull condition");
          wait();
        }

        queue.offer(value);
        if (queue.size() == 1) 
        	notifyAll(); // notify a queuing consumer
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    }

    synchronized public int read() {
      int value = 0;
      try {
        while (queue.isEmpty()) {
          System.out.println("\t\t\t\tWait for notEmpty condition");
          wait();
        }

        value = queue.remove();
        if (queue.size() == CAPACITY - 1)
        	notifyAll(); // notify a queuing producer
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
      return value;
    }
  }
}



































// This trick cannot work when the buffer capacity is 1 and more than one producer or consumer.