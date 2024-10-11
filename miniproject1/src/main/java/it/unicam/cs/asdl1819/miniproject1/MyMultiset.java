package it.unicam.cs.asdl1819.miniproject1;

import java.util.ArrayList;
import java.util.HashSet; // Utilizzare questa classe per i set
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Questa classe, MyMultiset, utilizza due List: una per memorizzare gli elementi
 * distinti e un'altra per memorizzare i loro conteggi.
 * 
 * @author Luca Tesei (template) **Alex Citeroni** (implementazione)
 *
 * @param <E> il tipo degli elementi del multiset
 */
public class MyMultiset<E> implements Multiset<E> {
	// Elenco per memorizzare valori distinti
	private List<E> values;
	// Elenco per memorizzare la freuquenza dei valori
	private List<Integer> frequency;

	/**
	 * Crea un multiset vuoto.
	 */
	public MyMultiset() {
		this.values = new ArrayList<E>();
		this.frequency = new ArrayList<Integer>();
	}

	/*
	 * Restituisce il numero totale di elementi in questo multiset, contando tutte
	 * le occorrenze.
	 * 
	 * @return Numero totale di elementi in questo multiset
	 */
	public int size() {
		int numeroTotaleElementi = 0;
		for (Object obj : values)
			numeroTotaleElementi += frequency.get(values.indexOf(obj));
		return numeroTotaleElementi;
	}

	/*
	 * Restituisce il numero di occorrenze di un elemento in questo multiset.
	 * 
	 * @param element L'elemento da contare
	 * 
	 * @return La frequenza dell'elemento, che può essere zero, ma mai negativa
	 * 
	 * @throws NullPointerException Se element fosse nullo
	 */
	public int count(Object element) {
		// Verifico che element sia diverso da null
		if (element == null)
			throw new NullPointerException();
		return (values.indexOf(element) == -1) ? 0 : frequency.get(values.indexOf(element));
	}

	/*
	 * Aggiunge un numero di occorrenze di un elemento a questo multiset.
	 * 
	 * @param occurrences Il numero di volte che un elemento viene aggiunto, può
	 * essere zero e in quel caso non viene effettuato alcun cambiamento
	 * 
	 * @param element L'elemento da aggiungere
	 * 
	 * @return Le precedenti occorrenze di elementi, possibilmente anche zero
	 * 
	 * @throws IllegalArgumentException Se occurrences fosse negativo o se in questa
	 * operazione risultasse più di una occorrenza di questo elemento o di prevCount
	 * 
	 * @throws NullPointerException Se element fosse nullo
	 */
	public int add(E element, int occurrences) {
		// Verifico che element sia diverso da null
		if (element == null)
			throw new NullPointerException();
		// Verifico che occurrences sia maggiore di 0 e che element non sia già
		// contenuto nel count
		if (occurrences < 0 || occurrences > Integer.MAX_VALUE)
			throw new IllegalArgumentException();
		int prevCount = 0;
		if (values.indexOf(element) != -1) {
			prevCount = frequency.get(values.indexOf(element));
			if (prevCount == Integer.MAX_VALUE)
				throw new IllegalArgumentException();
			frequency.set(values.indexOf(element), prevCount + occurrences);
		} else if (occurrences != 0) {
			values.add(element);
			frequency.add(occurrences);
		}
		return prevCount;
	}

	/*
	 * Aggiunge una singola occorrenza dell'elemento specificato a questo multiset.
	 * 
	 * @param element L'elemento da aggiungere all'occorrenza
	 * 
	 * @throws NullPointerException Se element fosse nullo
	 */
	public void add(E element) {
		// Verifico che element non sia null
		if (element == null)
			throw new NullPointerException();
		add(element, 1);
	}

	/*
	 * Rimuove un numero di occorrenze dell'elemento specificato da questo multiset.
	 * Se il multiset contiene meno di questo numero di occorrenze, tutte le
	 * occorrenze verranno rimosse.
	 * 
	 * @param element L'elemento per rimuovere le occorrenze
	 * 
	 * @param occurrences Il numero di occorrenze dell'elemento da rimuovere.
	 * Possibilmente zero, in questo caso non vengono fatti cambiamenti
	 * 
	 * @return Il conteggio dell'elemento prima dell'operazione; possibilmente zero
	 * 
	 * @throws IllegalArgumentException Se occurrences fosse negativo
	 * 
	 * @throws NullPointerException Se element fosse nullo
	 */
	public int remove(Object element, int occurrences) {
		// Verifico che element non sia null
		if (element == null)
			throw new NullPointerException();
		// Verifico che occurrences sia maggiore di 0
		if (occurrences < 0 || occurrences > Integer.MAX_VALUE)
			throw new IllegalArgumentException();
		int index = values.indexOf(element);
		if (index == -1)
			return 0;
		int prevCount = frequency.get(index);
		if (prevCount > occurrences)
			frequency.set(index, prevCount - occurrences);
		else {
			values.remove(index);
			frequency.remove(index);
		}
		return prevCount;
	}

	/*
	 * Rimuove una singola occorrenza dell'elemento specificato da questo multiset,
	 * se presente.
	 * 
	 * @param element L'elemento per rimuovare l'occorrenza
	 * 
	 * @return true Quando viene trovata un'occorrenza e rimossa
	 * 
	 * @throws NullPointerException Se element fosse nullo
	 */
	public boolean remove(Object element) {
		// Verifico che element non sia null
		if (element == null)
			throw new NullPointerException();
		return remove(element, 1) > 0;
	}

	/*
	 * Aggiunge o rimuove le occorrenze necessarie di un elemento in modo tale che
	 * l'elemento raggiunga il conteggio desiderato.
	 * 
	 * @param element L'elemento per aggiungere o rimuovere occorrenze
	 * 
	 * @param count Il conteggio desiderato dall'elemento di questo multiset
	 * 
	 * @return Il conteggio degli elementi prima dell'operazione, possibilmente zero
	 * 
	 * @throws IllegalArgumentException Se count fosse negativo
	 * 
	 * @throws NullPointerException Se element fosse nullo
	 */
	public int setCount(E element, int count) {
		// Verifico che element non sia null
		if (element.equals(null))
			throw new NullPointerException();
		// Verifico che il count sia maggiore di 0
		if (count < 0)
			throw new IllegalArgumentException();
		if (values.indexOf(element) == -1)
			return add(element, count);
		else {
			if (count == 0)
				return remove(element, frequency.get(values.indexOf(element)));
			if (frequency.get(values.indexOf(element)) < count)
				return add(element, count - frequency.get(values.indexOf(element)));
			else if (frequency.get(values.indexOf(element)) > count)
				return remove(element, frequency.get(values.indexOf(element)) - count);
		}
		return 0;
	}

	/*
	 * Restituisce il set di elementi distinti contenuti in questo multiset.
	 * L'ordine degli elementi nel set di elementi non è specificato.
	 * 
	 * @return L'insieme di elementi distinti in questo multiset
	 */
	public Set<E> elementSet() {
		Set<E> risultato = new HashSet<E>();
		risultato.addAll(values);
		return risultato;
	}

	/*
	 * Restituisce un iteratore per il multiset.
	 * 
	 * @return Un iteratore per questo multiset
	 */
	public Iterator<E> iterator() {
		List<E> list = new ArrayList<E>();
		for (int i = 0; i < values.size(); i++)
			for (int j = 0; j < frequency.get(i); j++)
				list.add(values.get(i));
		return list.iterator();
	}

	/*
	 * Determina se questo multiset contiene l'elemento specificato.
	 * 
	 * @param element L'elemento da verificare
	 * 
	 * @return true Se questo multiset contiene almeno un'occorrenza dell'elemento
	 * 
	 * @throws NullPointerException Se element fosse nullo
	 */
	public boolean contains(Object element) {
		// Verifico che l'elemento non sia nullo
		if (element == null)
			throw new NullPointerException();
		return values.contains(element);
	}

    // Rimuove tutti gli elementi da questo multiset
	public void clear() {
		values.clear();
		frequency.clear();
	}

	// @return Vero quando questo multiset è vuoto
	public boolean isEmpty() {
		return values.size() == 0;
	}
	
	// @return Un hashCode per questo MultiSet
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((frequency == null) ? 0 : frequency.hashCode());
		result = prime * result + ((values == null) ? 0 : values.hashCode());
		return result;
	}

	/**
	 * Confronta l'oggetto specificato con questo multiset per l'uguaglianza.
	 * Restituisce true se l'oggetto dato è anche un multiset e contiene elementi
	 * uguali con conteggi uguali, indipendentemente dall'ordine.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		MyMultiset<?> other = (MyMultiset<?>) obj;
		if (values.containsAll(other.values))
			if (frequency.containsAll(other.frequency))
				return true;
		return false;
	}
}