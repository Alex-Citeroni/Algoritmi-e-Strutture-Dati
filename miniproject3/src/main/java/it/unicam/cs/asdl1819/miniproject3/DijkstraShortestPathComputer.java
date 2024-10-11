package it.unicam.cs.asdl1819.miniproject3;

import java.util.ArrayList;
import java.util.List;

/**
 * Gli oggetti di questa classe sono calcolatori di cammini minimi con sorgente
 * singola su un certo grafo diretto e pesato dato. Il grafo su cui lavorare
 * deve essere passato quando l'oggetto calcolatore viene costruito e non può
 * contenere archi con pesi negativi. Il calcolatore implementa il classico
 * algoritmo di Dijkstra per i cammini minimi con sorgente singola utilizzando
 * una coda con priorità che estrae il minimo in tempo lineare rispetto alla
 * lunghezza della coda. In questo caso il tempo di esecuzione dell'algoritmo di
 * Dijkstra è {@code O(n^2)} dove {@code n} è il numero dei nodi del grafo.
 * 
 * @author Luca Tesei
 *
 * @param <V> il tipo delle etichette dei nodi del grafo
 * @param <E> il tipo delle etichette degli archi del grafo
 */
public class DijkstraShortestPathComputer<V, E> {
	// Grafo
	private Graph<V, E> directedGraph;
	// Nodo sorgente specificato nell'ultima chiamata effettuata su
	// computeShortestPathsFrom
	private GraphNode<V> lastSource;
	// Variabile che serve a dire a dire se è stato eseguito o meno il
	// computeShortestPathsFrom
	private boolean isComputed;

	/**
	 * Crea un calcolatore di cammini minimi a sorgente singola per un grafo diretto
	 * e pesato privo di pesi negativi.
	 * 
	 * @param graph il grafo su cui opera il calcolatore di cammini minimi
	 * @throws NullPointerException     se il grafo passato è nullo
	 * 
	 * @throws IllegalArgumentException se il grafo passato è vuoto
	 * 
	 * @throws IllegalArgumentException se il grafo passato non è diretto
	 * 
	 * @throws IllegalArgumentException se il grafo passato non è pesato, cioè
	 *                                  esiste almeno un arco il cui peso è
	 *                                  {@code Double.NaN}.
	 * @throws IllegalArgumentException se il grafo passato contiene almeno un peso
	 *                                  negativo.
	 */
	public DijkstraShortestPathComputer(Graph<V, E> graph) {
		if (graph.equals(null))
			throw new NullPointerException();
		if (graph.isEmpty() || !graph.isDirected())
			throw new IllegalArgumentException();
		for (GraphEdge<V, E> edge : graph.getEdges())
			if (edge.getWeight() < 0 || Double.isNaN(edge.getWeight()))
				throw new IllegalArgumentException();
		directedGraph = graph;
	}

	/**
	 * Inizializza le informazioni necessarie associate ai nodi del grafo associato
	 * a questo calcolatore ed esegue l'algoritmo di Dijkstra sul grafo.
	 * 
	 * @param sourceNode il nodo sorgente da cui calcolare i cammini minimi verso
	 *                   tutti gli altri nodi del grafo
	 * @throws NullPointerException     se il nodo passato è nullo
	 * 
	 * @throws IllegalArgumentException se il nodo passato non esiste nel grafo
	 *                                  associato a questo calcolatore
	 */
	public void computeShortestPathsFrom(GraphNode<V> sourceNode) {
		if (sourceNode.equals(null))
			throw new NullPointerException();
		if (!getGraph().containsNode(sourceNode))
			throw new IllegalArgumentException();
		List<GraphNode<V>> queue = new ArrayList<GraphNode<V>>();
		for (GraphNode<V> temp : directedGraph.getNodes()) {
			if (temp.equals(sourceNode))
				queue.add(sourceNode);
			GraphNode<V> compute = queue.remove(0);
			for (GraphNode<V> node : getGraph().getAdjacentNodes(compute))
				if (node.getColor() != 2)
					for (GraphEdge<V, E> edge : getGraph().getEdgesBetween(compute, node)) {
						node.setFloatingPointDistance(edge.getWeight());
						node.setPrevious(compute);
						queue.add(node);
					}
			for (int i = queue.size() - 1; i - 1 >= 0
					&& queue.get(i).getFloatingPointDistance() < queue.get(i - 1).getFloatingPointDistance();) {
				GraphNode<V> getI = queue.get(i);
				queue.set(i, queue.get(i - 1));
				queue.set(--i, getI);
			}
			compute.setColor(GraphNode.COLOR_BLACK);
		}
		lastSource = sourceNode;
		isComputed = true;
	}

	/**
	 * Determina se è stata invocata almeno una volta la procedura di calcolo dei
	 * cammini minimi a partire da un certo nodo sorgente specificato.
	 * 
	 * @return true, se i cammini minimi da un certo nodo sorgente sono stati
	 *         calcolati almeno una volta da questo calcolatore
	 */
	public boolean isComputed() {
		return isComputed;
	}

	/**
	 * Restituisce il nodo sorgente specificato nell'ultima chiamata effettuata a
	 * {@code computeShortestPathsFrom(GraphNode<V>)}.
	 * 
	 * @return il nodo sorgente specificato nell'ultimo calcolo dei cammini minimi
	 *         effettuato
	 * 
	 * @throws IllegalStateException se non è stato eseguito nemmeno una volta il
	 *                               calcolo dei cammini minimi a partire da un nodo
	 *                               sorgente
	 */
	public GraphNode<V> getLastSource() {
		if (isComputed())
			return lastSource;
		throw new IllegalStateException();
	}

	/**
	 * Restituisce il grafo su cui opera questo calcolatore.
	 * 
	 * @return il grafo su cui opera questo calcolatore
	 */
	public Graph<V, E> getGraph() {
		return directedGraph;
	}

	/**
	 * Restituisce una lista di archi dal nodo sorgente dell'ultimo calcolo di
	 * cammini minimi al nodo passato. Tale lista corrisponde a un cammino minimo
	 * tra il nodo sorgente e il nodo target passato.
	 * 
	 * @param targetNode il nodo verso cui restituire il cammino minimo dalla
	 *                   sorgente
	 * @return la lista di archi corrispondente al cammino minimo; la lista è vuota
	 *         se il nodo passato è il nodo sorgente. Viene restituito {@code null}
	 *         se il nodo passato non è raggiungibile dalla sorgente
	 * 
	 * @throws NullPointerException     se il nodo passato è nullo
	 * 
	 * @throws IllegalArgumentException se il nodo passato non esiste
	 * 
	 * @throws IllegalStateException    se non è stato eseguito nemmeno una volta il
	 *                                  calcolo dei cammini minimi a partire da un
	 *                                  nodo sorgente
	 * 
	 */
	public List<GraphEdge<V, E>> getShortestPathTo(GraphNode<V> targetNode) {
		if (targetNode.equals(null))
			throw new NullPointerException();
		if (!getGraph().containsNode(targetNode))
			throw new IllegalArgumentException();
		if (!isComputed())
			throw new IllegalStateException();
		List<GraphEdge<V, E>> path = new ArrayList<GraphEdge<V, E>>();
		if (targetNode.equals(getLastSource()))
			return path;
		for (GraphNode<V> node : getGraph().getNodes())
			if (node.equals(targetNode))
				targetNode = node;
		for (GraphNode<V> temp = targetNode; temp != getLastSource(); temp = temp.getPrevious())
			if (temp.getPrevious() == null)
				return null;
			else
				path.addAll(getGraph().getEdgesBetween(temp.getPrevious(), temp));
		for (int i = 0; i < path.size() - 1; i++)
			path.add(i, path.remove(path.size() - 1));
		return path;
	}

	/**
	 * Genera una stringa di descrizione di un path riportando i nodi attraversati e
	 * i pesi degli archi. Nel caso di cammino vuoto genera solo la stringa
	 * {@code "[ ]"}.
	 * 
	 * @param path un cammino minimo
	 * @return una stringa di descrizione del cammino minimo
	 * @throws NullPointerException se il cammino passato è nullo
	 */
	public String printPath(List<GraphEdge<V, E>> path) {
		if (path == null)
			throw new NullPointerException("Richiesta di stampare un path nullo");
		if (path.isEmpty())
			return "[ ]";
		// Costruisco la stringa
		StringBuffer s = new StringBuffer();
		s.append("[ " + path.get(0).getNode1().toString());
		for (int i = 0; i < path.size(); i++)
			s.append(" -- " + path.get(i).getWeight() + " --> " + path.get(i).getNode2().toString());
		s.append(" ]");
		return s.toString();
	}
}