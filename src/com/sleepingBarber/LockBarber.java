package com.sleepingBarber;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class MyBlockingQueue {
	private int max;
	public Queue<Integer> queue = new LinkedList<Integer>();
	private ReentrantLock lock = new ReentrantLock(true);
	private final Condition notEmpty = lock.newCondition();
	private final Condition notFull = lock.newCondition();
	
	public MyBlockingQueue(int size) {
		queue = new LinkedList<Integer>();
		max = size;
	}
	
	public void put(int item) throws InterruptedException {
		lock.lock();
		try {
			while(queue.size() == max) {
				System.out.println("Queue is Full. Customer "+item+" has left the shop.");
				notEmpty.await();
				break;
			}
		queue.offer(item);
		System.out.println("Added "+item+" into queue");
		notFull.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	public int get() {
		int item = 0;
		lock.lock();
		try {
			while(queue.size() == 0) {
				System.out.println("Queue is Empty");
				notFull.await();
			}
			item = queue.poll();
			System.out.println("Removed "+item+" from queue");
			notEmpty.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return item;
	}
}

class Customer extends Thread {
    private int custID;
    
	MyBlockingQueue queue;
	public Customer(int custID, MyBlockingQueue queue) {
		super("CUSTOMER");
		this.queue = queue;
		this.custID = custID;
	}
	
	public void run() {
			try {
				queue.put(custID);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}

class Barber extends Thread {
	MyBlockingQueue queue;
	public Barber(MyBlockingQueue queue) {
		super("BARBER");
		this.queue = queue;
	}
	
	public void run() {
		int custID;
		while(true) {
			custID = queue.get();
			hairCut();
		}
	}
	
	public void hairCut() {
		try {
			System.out.println("");
			Thread.sleep(2000);
			System.out.println("");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}

public class LockBarber {
	public static void main(String args[]) throws InterruptedException {
		MyBlockingQueue q = new MyBlockingQueue(5);
		//BlockingQueue<Integer> q = new ArrayBlockingQueue<Integer>(5);
		
		Barber c = new Barber(q);
		c.start();
		int iD = 1;
		while(true) {
			Customer p = new Customer(iD++,q);
			p.start();
			Thread.sleep(500);
		}
	}
}
