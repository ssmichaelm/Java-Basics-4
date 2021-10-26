package com.ss.jb.four;

/*
 * Assignment 1
 * Implement a Singleton with double checked locking.
 */
public class Singleton {

	private volatile static Singleton instance = null; // Apply volatile to the instance to support thread-safety
	
	// Private constructor so it cannot be accessed from outside the Singleton class
	private Singleton() { }
	
	public static Singleton getInstance() {
		// Single checked
		if(instance == null) {
			synchronized (Singleton.class) {
				// Double checked
				if(instance == null) {
					instance = new Singleton();
				}
			}
		}
		
		return instance;
	}
	
}
