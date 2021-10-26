package com.ss.jb.four;

/*
 * Assignment 2
 * Write a program to create a deadlock between two threads.
 */
public class Assignment2 extends Thread {
	public static void main(String args[]) {
		String lockA = "Lock A", lockB = "Lock B";
		
		System.out.println("Deadlock begins!");
		
		Runnable runnableA = new Runnable() {
			@Override
			public void run() {
				try {
					// Give runnable/thread A the key and exclusive access to lockA
					synchronized(lockA) {
						Thread.sleep(50);
						System.out.println("Thread A is waiting for lock B...");
						
						// Tries to give thread A the key and exclusive access to lockB, but at this point thread B has those rights
						synchronized(lockB) {
							System.out.println("Thread A is running");
						}
					}
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					System.out.println("Thread A was interrupted and lock A was released.");
				}
			}
		};
		
		Runnable runnableB = new Runnable() {
			@Override
			public void run() {
				try {
					// Give runnable/thread B the key and exclusive access to lockB
					synchronized(lockB) {
						Thread.sleep(50);
						System.out.println("Thread B is waiting for lock A...");
						
						// Tries to give thread B the key and exclusive access to lockA, but thread A has those rights
						synchronized(lockA) {
							System.out.println("Thread B is running");
						}
					}
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					System.out.println("Thread B was interrupted and lock B was released.");
				}
			}
		};
		
		Thread threadA = new Thread(runnableA);
		Thread threadB = new Thread(runnableB);
		threadA.start();
		threadB.start();
				
//		// Uncomment a line to set an "interrupt" flag in the specified thread; releasing the lock it has.
//		threadA.interrupt();
//		threadB.interrupt();
	}

}
