package it.unicam.cs.asdl1819.miniproject3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Implementazione dell'interfaccia {@code Graph<V,E>} per grafi diretti
 * utilizzando liste di adiacenza per la rappresentazione.
 * 
 * Questa classe non supporta le operazioni di rimozione di nodi e archi e le
 * operazioni indicizzate di ricerca di nodi e archi.
 * 
 * @author Luca Tesei
 *
 * @param <V> etichette dei nodi
 * @param <E> etichette degli archi
 */
public class AdjacentListDirectedGraph<V, E> implements Graph<V, E> {
	// Lista d'adiacenza
	private Map<GraphNode<V>, Set<GraphEdge<V, E>>> adjacentList;

	/**
	 * Crea un grafo vuoto.
	 */
	public AdjacentListDirectedGraph() {
		adjacentList = new HashMap<GraphNode<V>, Set<GraphEdge<V, E>>>();
	}

	/**
	 * Restituisce il numero di nodi in questo grafo.
	 * 
	 * @return il numero di nodi in questo grafo.
	 */
	public int nodeCount() {
		return adjacentList.keySet().size();
	}

	/**
	 * Restituisce il numero di archi in questo grafo.
	 * 
	 * @return il numero di archi in questo grafo.
	 */
	public int edgeCount() {
		return getEdges().size();
	}

	/**
	 * Restituisce la dimensione di questo grafo definita come il numero di nodi più
	 * il numero di archi.
	 * 
	 * @return la dimensione di questo grafo definita come il numero dei nodi più il
	 *         numero degli archi.
	 */
	public int size() {
		return nodeCount() + edgeCount();
	}

	/**
	 * Determina se questo grafo è vuoto, cioè senza nodi e senza archi.
	 * 
	 * @return se questo grafo è vuoto, false altrimenti.
	 */
	public boolean isEmpty() {
		return adjacentList.isEmpty();
	}

	/**
	 * Cancella tutti i nodi e gli archi di questo grafo portandolo ad essere un
	 * grafo vuoto.
	 */
	public void clear() {
		adjacentList.clear();
	}

	/**
	 * Determina se questo grafo è diretto oppure no.
	 * 
	 * @return true se questo grafo è diretto, false altrimenti.
	 */
	public boolean isDirected() {
		return true;
	}

	/**
	 * Restituisce l'insieme dei nodi di questo grafo.
	 * 
	 * @return l'insieme dei nodi di questo grafo, possibilmente vuoto.
	 */
	public Set<GraphNode<V>> getNodes() {
		return adjacentList.keySet();
	}

	/**
	 * Aggiunge un nodo a questo grafo.
	 * 
	 * @param node il nuovo nodo da aggiungere
	 * @return true se il nodo è stato aggiunto, false altrimenti cioè se il nodo è
	 *         già presente
	 * @throws NullPointerException se il nodo passato è null
	 */
	public boolean addNode(GraphNode<V> node) {
		if (node.equals(null))
			throw new NullPointerException();
		// Il nodo è già presente
		if (adjacentList.keySet().contains(node))
			return false;
		// Aggiungo il nodo con insieme degli archi associati vuoto
		adjacentList.put(node, new HashSet<GraphEdge<V, E>>());
		return true;
	}

	/**
	 * Questa operazione non è supportata.
	 * 
	 * @param node il nodo da rimuovere
	 * @throws NullPointerException se il nodo passato è null
	 * @return throws UnsupportedOperationException
	 */
	public boolean removeNode(GraphNode<V> node) {
		if (node.equals(null))
			throw new NullPointerException();
		throw new UnsupportedOperationException();
	}

	/**
	 * Determina se c'è un certo nodo in questo grafo.
	 * 
	 * @param node il nodo cercato
	 * @return true se il nodo esiste in questo grafo
	 * @throws NullPointerException se il nodo passato è null
	 */
	public boolean containsNode(GraphNode<V> node) {
		if (node.equals(null))
			throw new NullPointerException();
		return adjacentList.keySet().contains(node);
	}

	/**
	 * Questa operazione non è supportata.
	 * 
	 * @param label l'etichetta del nodo di cui restituire l'indice
	 * @throws NullPointerException se l'etichetta passata è null
	 * @return throws UnsupportedOperationException
	 */
	public int getNodeIndex(V label) {
		if (label.equals(null))
			throw new NullPointerException();
		throw new UnsupportedOperationException();
	}

	/**
	 * Questa operazione non è supportata.
	 * 
	 * @param i l'indice del nodo.
	 * @return throws UnsupportedOperationException
	 */
	public GraphNode<V> getNodeAtIndex(int i) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Restituisce l'insieme di tutti i nodi collegati da un un arco uscente dal
	 * nodo passato.
	 * 
	 * @param node il nodo di cui cercare i nodi adiacenti
	 * @return l'insieme di tutti i nodi collegati da un un arco uscente dal nodo
	 *         passato.
	 * @throws IllegalArgumentException se il nodo passato non esiste
	 * @throws NullPointerException     se il nodo passato è nullo
	 */
	public Set<GraphNode<V>> getAdjacentNodes(GraphNode<V> node) {
		if (node.equals(null))
			throw new NullPointerException();
		if (!adjacentList.containsKey(node))
			throw new IllegalArgumentException();
		Set<GraphNode<V>> adjacentNodes = new HashSet<GraphNode<V>>();
		// Scorro l'insieme degli archi per restituire l'insieme dei nodi adiacenti a
		// questo
		for (GraphEdge<V, E> temp : adjacentList.get(node))
			if (temp.getNode1().equals(node))
				adjacentNodes.add(temp.getNode2());
		return adjacentNodes;
	}

	/**
	 * Restituisce l'insieme di tutti i nodi collegati tramite un arco entrante in
	 * un certo nodo.
	 * 
	 * @param node il nodo di cui cercare i nodi successori
	 * @return l'insieme di tutti i nodi collegati tramite un arco entrante al nodo
	 *         specificato
	 * @throws IllegalArgumentException se il nodo passato non esiste
	 * @throws NullPointerException     se il nodo passato è nullo
	 */
	public Set<GraphNode<V>> getPredecessorNodes(GraphNode<V> node) {
		if (node.equals(null))
			throw new NullPointerException();
		if (!adjacentList.containsKey(node))
			throw new IllegalArgumentException();
		Set<GraphNode<V>> predecessorNodes = new HashSet<GraphNode<V>>();
		// Scorro l'insieme dei nodi per restituire i nodi predecessori a questo
		for (GraphEdge<V, E> temp : adjacentList.get(node))
			if (temp.getNode2().equals(node))
				predecessorNodes.add(temp.getNode1());
		return predecessorNodes;
	}

	/**
	 * Restituisce l'insieme di tutti gli archi in questo grafo.
	 * 
	 * @return un insieme, possibilmente vuoto, contenente tutti gli archi di questo
	 *         grafo
	 */
	public Set<GraphEdge<V, E>> getEdges() {
		Set<GraphEdge<V, E>> edges = new HashSet<GraphEdge<V, E>>();
		// Scorro tutto l'insieme degli archi per poi restituirlo
		for (GraphNode<V> temp : adjacentList.keySet())
			edges.addAll(adjacentList.get(temp));
		return edges;
	}

	/**
	 * Aggiunge un arco a questo grafo.
	 * 
	 * @param edge l'arco da inserire
	 * @return true se l'arco è stato inserito, false se un arco esattamente uguale
	 *         già esiste
	 * @throws NullPointerException     se l'arco passato è nullo
	 * @throws IllegalArgumentException se almeno uno dei due nodi specificati
	 *                                  nell'arco non esiste
	 * @throws IllegalArgumentException se l'arco è diretto e questo grafo non è
	 *                                  diretto o viceversa
	 */
	public boolean addEdge(GraphEdge<V, E> edge) {
		if (edge.equals(null))
			throw new NullPointerException();
		if (!adjacentList.containsKey(edge.getNode1()) || !adjacentList.containsKey(edge.getNode2()) || !isDirected()
				|| !edge.isDirected())
			throw new IllegalArgumentException();
		if (containsEdge(edge))
			return false;
		return adjacentList.get(edge.getNode1()).add(edge) && adjacentList.get(edge.getNode2()).add(edge);
	}

	/**
	 * Questa operazione non è supportata.
	 * 
	 * @param edge l'arco da rimuovere
	 * @return throws UnsupportedOperationException
	 */
	public boolean removeEdge(GraphEdge<V, E> edge) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Cerca se c'è un certo arco in questo grafo.
	 * 
	 * @param edge l'arco da cercare
	 * @return true se in questo grafo c'è l'arco specificato, false altrimenti
	 * @throws NullPointerException     se l'arco passato è nullo
	 * @throws IllegalArgumentException se almeno uno dei due nodi specificati
	 *                                  nell'arco non esiste
	 */
	public boolean containsEdge(GraphEdge<V, E> edge) {
		if (edge.equals(null))
			throw new NullPointerException();
		if (!adjacentList.containsKey(edge.getNode1()) || !adjacentList.containsKey(edge.getNode2()))
			throw new IllegalArgumentException();
		// Scorro l'insieme degli archi alla ricerca dell'arco preso in considerazione
		for (GraphNode<V> containsEdge : adjacentList.keySet())
			if (getEdges(containsEdge).contains(edge))
				return true;
		return false;
	}

	/**
	 * Viene restituito un insieme contenente gli archi uscenti.
	 * 
	 * @param node il nodo di cui sono richiesti gli archi connessi
	 * @return un insieme contenente gli archi uscenti
	 * @throws IllegalArgumentException se il nodo passato non esiste
	 * @throws NullPointerException     se il nodo passato è nullo
	 */
	public Set<GraphEdge<V, E>> getEdges(GraphNode<V> node) {
		if (node.equals(null))
			throw new NullPointerException();
		if (!adjacentList.containsKey(node))
			throw new IllegalArgumentException();
		Set<GraphEdge<V, E>> edges = new HashSet<GraphEdge<V, E>>();
		// Scorro l'insieme degli archi e cerco gli archi uscenti
		for (GraphEdge<V, E> temp : getEdges())
			if (temp.getNode1().equals(node))
				edges.add(temp);
		return edges;
	}

	/**
	 * Restituisce l'insieme di tutti gli archi entranti in un certo nodo in un
	 * grafo diretto.
	 * 
	 * @param node il nodo di cui determinare tutti gli archi entranti
	 * @return un insieme contenente tutti gli archi entranti nel nodo con etichetta
	 *         label in questo grafo diretto.
	 * @throws IllegalArgumentException se il nodo passato non esiste
	 * @throws NullPointerException     se il nodo passato è nullo
	 */
	public Set<GraphEdge<V, E>> getIngoingEdges(GraphNode<V> node) {
		if (node.equals(null))
			throw new NullPointerException();
		if (!adjacentList.containsKey(node))
			throw new IllegalArgumentException();
		Set<GraphEdge<V, E>> ingoingEdges = new HashSet<GraphEdge<V, E>>();
		// Scorro l'insieme degli archi e cerco gli archi entranti
		for (GraphEdge<V, E> temp : adjacentList.get(node))
			if (temp.getNode2().equals(node))
				ingoingEdges.add(temp);
		return ingoingEdges;
	}

	/**
	 * Restituisce il grado di un nodo, cioè la somma del numero di archi in entrata
	 * e del numero di archi in uscita.
	 * 
	 * @param node il nodo di cui calcolare il grado
	 * @return il grado del nodo passato
	 * @throws IllegalArgumentException se il nodo passato non esiste
	 * @throws NullPointerException     se il nodo passato è nullo
	 */
	public int getDegree(GraphNode<V> node) {
		if (node.equals(null))
			throw new NullPointerException();
		if (!adjacentList.containsKey(node))
			throw new IllegalArgumentException();
		return getEdges(node).size() + getIngoingEdges(node).size();
	}

	/**
	 * Questa operazione non è supportata.
	 * 
	 * @param index1 valore del nodo sorgente
	 * @param index2 valore del nodo di destinazione
	 * @return throws UnsupportedOperationException
	 */
	public Set<GraphEdge<V, E>> getEdgesBetween(int index1, int index2) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Vengono restituiti gli archi che hanno come sorgente il nodo corrispondente
	 * al primo indice e come destinazione il nodo corrispondente al secondo indice.
	 * 
	 * @param node1 nodo sorgente
	 * @param node2 nodo di destinazione
	 * @return l'insieme, possibilmente vuoto, degli archi che collegano i due nodi
	 *         passati
	 * @throws IllegalArgumentException se almeno uno dei due nodi passati non
	 *                                  esiste
	 * @throws NullPointerException     se almeno uno dei due nodi passati è nullo
	 */
	public Set<GraphEdge<V, E>> getEdgesBetween(GraphNode<V> node1, GraphNode<V> node2) {
		if (node1.equals(null) || node2.equals(null))
			throw new NullPointerException();
		if (!adjacentList.containsKey(node1) || !adjacentList.containsKey(node2))
			throw new IllegalArgumentException();
		Set<GraphEdge<V, E>> edgesBetween = new HashSet<GraphEdge<V, E>>();
		// Scorro l'insieme degli archi e cerco quello uguale a node1 o a node2
		for (GraphEdge<V, E> temp : getEdges())
			if (temp.getNode1().equals(node1) && temp.getNode2().equals(node2))
				edgesBetween.add(temp);
		return edgesBetween;
	}

	/**
	 * Restituisce il nodo di questo grafo avente l'etichetta passata.
	 * 
	 * @param label l'etichetta del nodo da restituire
	 * @return il nodo di questo grafo che ha l'etichetta uguale a label
	 * @throws NullPointerException     se l'etichetta è nulla
	 * @throws IllegalArgumentException se non esiste un nodo di questo grafo avente
	 *                                  l'etichetta uguale a label
	 */
	public GraphNode<V> getNode(V label) {
		if (label.equals(null))
			throw new NullPointerException();
		// Scorro l'insieme dei nodi e cerco quello con etichetta equals a label
		for (GraphNode<V> node : adjacentList.keySet())
			if (node.getLabel().equals(label))
				return node;
		// Il grafo non contiene nessun nodo con l'etichetta label
		throw new IllegalArgumentException();
	}
}