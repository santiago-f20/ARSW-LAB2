package edu.eci.arsw.primefinder;

import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String in;
		Scanner scanner;
		Date startDate, endDate;
		int seconds = 0;
		startDate = new Date();
		LinkedList<PrimeFinderThread> threads = new LinkedList<>();

		PrimeFinderThread pft = new PrimeFinderThread(0, 10000000);
		PrimeFinderThread pft1 = new PrimeFinderThread(10000001, 20000000);
		PrimeFinderThread pft2 = new PrimeFinderThread(20000001, 30000000);
		threads.add(pft);
		threads.add(pft1);
		threads.add(pft2);

		for (PrimeFinderThread thread : threads) {
			thread.start();
		}

		while (seconds != 5) {
			endDate = new Date();
			seconds = (int) ((endDate.getTime() - startDate.getTime()) / 1000);
		}

		for (PrimeFinderThread thread : threads) {
			thread.setSuspend(true);
		}

		System.out.println("Numeros primos tras 5 segundos. \nPulse Enter para continuar");

		scanner = new Scanner(System.in);
		in = scanner.nextLine();

		if (in.equals("")) {
			for (PrimeFinderThread thread : threads) {
				thread.resumeThread();
			}
		}
	}
}
