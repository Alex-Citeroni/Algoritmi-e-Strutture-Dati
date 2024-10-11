package it.unicam.cs.asdl1819.miniproject1;

/**
 * Un fattorizzatore è un agente che fattorizza un qualsiasi numero naturale nei
 * sui fattori primi.
 * 
 * @author Luca Tesei (template) **Alex Citeroni** (implementazione)
 *
 */
public class Factoriser {
	/**
	 * Fattorizza un numero restituendo il multinsieme dei suoi fattori primi. La
	 * molteplicità di ogni fattore primo esprime quante volte il fattore stesso
	 * divide il numero fattorizzato. Per convenzione non viene mai restituito il
	 * fattore 1. Il minimo numero fattorizzabile è 1. In questo caso viene
	 * restituito un multinsieme vuoto.
	 * 
	 * @param n un numero intero da fattorizzare
	 * @return il multinsieme dei fattori primi di n
	 * @throws IllegalArgumentException se si chiede di fattorizzare un numero
	 *                                  minore di 1.
	 */
	public Multiset<Integer> getFactors(int n) {
		if (n < 1)
			throw new IllegalArgumentException();
		MyMultiset<Integer> factors = new MyMultiset<Integer>();
		for (int i = 2; i <= n / i; i++)
			while (n % i == 0) {
				factors.add(i);
				n /= i;
			}
		if (n > 1)
			factors.add(n);
		return factors;
	}
}