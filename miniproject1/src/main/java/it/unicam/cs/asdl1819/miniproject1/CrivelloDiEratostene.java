package it.unicam.cs.asdl1819.miniproject1;

import java.util.TreeSet; // Utilizzare questa classe per il SortedSet
import java.util.SortedSet;

/**
 * Il crivello di Eratostene è un modo per determinare tutti i numeri primi da
 * {@code 1} a un certo intero {@code n} assegnato.
 * 
 * @author Luca Tesei (template), ** Alex Citeroni** (implementazione)
 *
 */
public class CrivelloDiEratostene {
	private int capacity;
	private TreeSet<Integer> primes;
	private Integer biggestNumberPrime;

	/**
	 * Costruisce il crivello di Eratostene fino a un certo numero. Il numero deve
	 * essere almeno 2.
	 * 
	 * @param n numero di entrate nel crivello
	 * 
	 * @throws IllegalArgumentException se il numero {@code n} è minore di {@code 2}
	 */
	public CrivelloDiEratostene(int n) {
		// Verifico che n sia maggiore di 2
		if (n < 2)
			throw new IllegalArgumentException();
		// Indico la capacità
		this.capacity = n;
		// Riempio il crivello di Eratostene
		this.primes = riempimento(n);
	}

	/**
	 * Cerca nel crivello l'indice del numero primo successivo a un numero dato.
	 * 
	 * @param n il numero da cui partire
	 * @return il numero primo successivo a {@code n} in questo crivello oppure -1
	 *         se in questo crivello non ci sono numeri primi maggiori di {@code n}
	 * @throws IllegalArgumentException se il numero passato {@code n} eccede la
	 *                                  capacità di questo crivello o se è un numero
	 *                                  minore di 1.
	 */
	public int nextPrime(int n) {
		// Verifico che n sia maggiore di 1 e che n sia minore di capacity
		if (n < 1 || n > capacity)
			throw new IllegalArgumentException();
		// Se non ci sono numeri primi maggiori di capacity
		if (n >= biggestNumberPrime)
			return -1;
		return primes.higher(n);
	}

	/**
	 * Restituisce l'insieme dei numeri primi calcolati attraverso questo crivello.
	 * Per convenzione il numero primo {@code 1} non viene incluso nel risultato.
	 * 
	 * @return l'insieme dei numeri primi calcolati attraverso questo crivello.
	 */
	public SortedSet<Integer> getPrimes() {
		return primes;
	}

	/**
	 * Restituisce la capacità di questo crivello, cioè il numero massimo di
	 * entrate.
	 * 
	 * @return la capacità di questo crivello
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Controlla se un numero è primo. Può rispondere solo se il numero passato come
	 * parametro è minore o uguale alla capacità di questo crivello.
	 * 
	 * @param n il numero da controllare
	 * @return true se il numero passato è primo
	 * @throws IllegalArgumentException se il numero passato {@code n} eccede la
	 *                                  capacità di questo crivello o se è un numero
	 *                                  minore di 1.
	 */
	public boolean isPrime(int n) {
		// Verifico che n sia maggiore di 1 e che n sia minore di capacity
		if (n < 1 || n > capacity)
			throw new IllegalArgumentException();
		// 1, 2 e 3 sono numeri primi
		if (n == 1 || n == 2 || n == 3)
			return true;
		// Verifico che n non sia divisibile per 2 e per 3
		if (n % 2 == 0 || n % 3 == 0)
			return false;
		// Creo un ciclo per verificare quali sono gli altri numeri primi
		for (int i = 5; i <= Math.sqrt(n); i += 2)
			// Verifico che n sia un numero primo
			if (n % i == 0)
				return false;
		// Se non è divisibile per nessun altro numero apparte 1 e se stesso è primo
		return true;
	}

// Creo un metodo per riempire il crivello
	private TreeSet<Integer> riempimento(int n) {
		// Creo un nuovo TreeSet
		TreeSet<Integer> riempi = new TreeSet<Integer>();
		for (int i = 2; i <= n; i++) {
			// Se i è primo viene aggiunto al crivello
			if (isPrime(i))
				riempi.add(i);
			// i è uguale al numero primo più grande nel crivello
			if (i == n)
				biggestNumberPrime = i;
		}
		return riempi;
	}
}