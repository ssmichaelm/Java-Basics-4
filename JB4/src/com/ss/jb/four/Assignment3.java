package com.ss.jb.four;

import java.util.LinkedList;

public class Assignment3 {
	public static void main(String[] args) throws InterruptedException {
		// Object of a class that has both produce() and consume() methods
		final ProducerConsumer producerconsumer = new ProducerConsumer(5);

		// Create producer thread
		Thread producer = new Thread(new Runnable() {
			@Override
			public void run()
			{
				try {
					producerconsumer.produceItem();
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		// Create consumer thread
		Thread consumer = new Thread(new Runnable() {
			@Override
			public void run()
			{
				try {
					producerconsumer.consumeItem();
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		// Start both threads
		producer.start();
		consumer.start();

		// t1 finishes before t2
		try {
			producer.join();
			consumer.interrupt();			
		} catch (InterruptedException e ) {}

	}

	// This class has a list, producer (adds items to list and consumer (removes items).
	public static class ProducerConsumer {

		// Create a list shared by producer and consumer
		private LinkedList<Integer> boundedBuffer = new LinkedList<Integer>();
		private int capacity;
		
		public ProducerConsumer (int size ) {
			capacity = size;
		}

		// Function called by producer thread
		public void produceItem() throws InterruptedException {
			int upperBound = 10;
			int lowerBound = 1;
			int range = upperBound - lowerBound + 1;
			
			while (!Thread.currentThread().isInterrupted()) {
				synchronized (this)
				{
					// While the buffer is full, the producer can't make anything
					while (boundedBuffer.size() == capacity)
						wait();
					
					int x = (int)(Math.random() * range) + lowerBound;
					System.out.println("Producer made: "
									+ x);

					// Insert the newly made item in the buffer
					boundedBuffer.add(x);

					// Notifies the consumer thread that now it can start consuming and rest
					notify();
					Thread.sleep(450);
				}
			}
		}

		// Function called by consumer thread
		public void consumeItem() throws InterruptedException {
			
			while (!Thread.currentThread().isInterrupted()) {
				synchronized (this)
				{
					// While there is nothing made, the consumer can't eat anything
					while (boundedBuffer.size() == 0)
						wait();
					
					// Consumer takes the first item in the boundedBuffer
					int val = boundedBuffer.removeFirst();

					System.out.println("Consumer ate: "
									+ val);

					// Tells the producer thread it can start making more stuff and rest
					notify();
					Thread.sleep(450);
				}
			}
		}
	}
}
