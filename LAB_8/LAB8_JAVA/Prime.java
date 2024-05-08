package LAB_8.LAB8_JAVA;

import java.util.List;
import java.util.Scanner;

interface ISimplePrimeFinder {
    String findPrimes(int limit);
}

class SimplePrimeFinder implements ISimplePrimeFinder{

    SievePrime sievePrime;

    public String findPrimes(int limit) {
        List<Integer> primesList = sievePrime.sievePrimes(limit);
        StringBuilder primes = new StringBuilder();
        for (Integer prime : primesList) {
            primes.append(prime).append(" ");
        }
        return primes.toString().trim(); // Trim to remove the last space
    }

    public SimplePrimeFinder() {
        sievePrime = new SievePrime();
    }
}

// Testing the prime finders
public class Prime {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        SimplePrimeFinder simplePrimeFinder = new SimplePrimeFinder();
        System.out.println("Please enter a number to find prime numbers from 0 to your number: ");
        int limit = scan.nextInt();
        System.out.println("Primes found by Simple Prime Finder: " + simplePrimeFinder.findPrimes(limit));
        scan.close();
    }
}