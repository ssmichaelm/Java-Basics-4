package com.ss.jb.four;

/*
 * Assignment 1
 * Implement a Singleton with double checked locking.
 */
public class Assignment1 {
	public static void main(String args[]) {
		Singleton singleton1 = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();

		System.out.println("Singleton 1 has hashcode: " + singleton1.hashCode());
		System.out.println("Singleton 2 has hashcode: " + singleton2.hashCode());
		System.out.println();
		System.out.println("Because the singletons have the same hashcode, we can observe only one singleton can exist.");
	}
}
