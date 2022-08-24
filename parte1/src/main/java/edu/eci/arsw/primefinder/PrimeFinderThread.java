package edu.eci.arsw.primefinder;

import java.util.LinkedList;
import java.util.List;

public class PrimeFinderThread extends Thread {

	int a, b;
	boolean stop;
	private List<Integer> primes = new LinkedList<Integer>();

	public PrimeFinderThread(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	public void run() {
		for (int i = a; i <= b; i++) {
			if (isPrime(i)) {
				primes.add(i);
				System.out.println(i);
			}

			synchronized (this) {
				while (stop) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						System.out.println("Error");
					}
				}
			}
		}
	}

	boolean isPrime(int n) {
		if (n % 2 == 0)
			return false;
		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	synchronized void resumeThread() {
		stop = false;
		notify();
	}

	public void setSuspend(boolean stop) {
		this.stop = stop;
	}

	public List<Integer> getPrimes() {
		return primes;
	}
}
