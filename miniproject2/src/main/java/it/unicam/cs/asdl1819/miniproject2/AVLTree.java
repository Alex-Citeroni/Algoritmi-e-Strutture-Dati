package it.unicam.cs.asdl1819.miniproject2;

import java.util.ArrayList;
import java.util.List;

/**
 * Un AVLTree è un albero binario di ricerca che si mantiene sempre bilanciato.
 * In questa particolare classe si possono inserire elementi ripetuti di tipo
 * {@code E} e non si possono inserire elementi {@code null}.
 * 
 * @author Luca Tesei
 * 
 * @param E il tipo degli elementi che possono essere inseriti in questo
 *          AVLTree. La classe {@code E} deve avere un ordinamento naturale
 *          definito tra gli elementi.
 *
 */
public class AVLTree<E extends Comparable<E>> {

	// puntatore al nodo radice, se questo puntatore è null allora questo
	// AVLTree è vuoto
	private AVLTreeNode root;

	// Numero di elementi inseriti in questo AVLTree, comprese le ripetizioni
	private int size;

	// Numero di nodi in questo AVLTree
	private int numberOfNodes;

	/**
	 * Costruisce un AVLTree vuoto.
	 */
	public AVLTree() {
		root = null;
		size = 0;
		numberOfNodes = 0;
	}

	/**
	 * Costruisce un AVLTree che consiste solo di un nodo radice.
	 * 
	 * @param rootElement l'informazione associata al nodo radice
	 * @throws NullPointerException se l'elemento passato è null
	 */
	public AVLTree(E rootElement) {
		if (rootElement.equals(null))
			throw new NullPointerException();
		setRoot(new AVLTreeNode(rootElement));
		numberOfNodes++;
		size++;
	}

	/**
	 * Determina se questo AVLTree è vuoto.
	 * 
	 * @return true, se questo AVLTree è vuoto.
	 */
	public boolean isEmpty() {
		return (this.root == null);
	}

	/**
	 * Restituisce il numero di elementi contenuti in questo AVLTree. In caso di
	 * elementi ripetuti essi vengono contati più volte.
	 * 
	 * @return il numero di elementi di tipo {@code E} presenti in questo AVLTree.
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Restituisce il numero di nodi in questo AVLTree.
	 * 
	 * @return il numero di nodi in questo AVLTree.
	 */
	public int getNumberOfNodes() {
		return this.numberOfNodes;
	}

	/**
	 * Restituisce l'altezza di questo AVLTree. Se questo AVLTree è vuoto viene
	 * restituito il valore -1.
	 * 
	 * @return l'altezza di questo AVLTree, -1 se questo AVLTree è vuoto.
	 */
	public int getHeight() {
		if (getRoot().equals(null))
			return -1;
		return getRoot().getHeight();
	}

	/**
	 * @return the root
	 */
	public AVLTreeNode getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(AVLTreeNode root) {
		this.root = root;
	}

	/**
	 * Determina se questo AVLTree è bilanciato. L'albero è bilanciato se tutti i
	 * nodi hanno un fattore di bilanciamento compreso tra -1 e +1.
	 * 
	 * @return true, se il fattore di bilanciamento di tutti i nodi dell'albero è
	 *         compreso tra -1 e +1.
	 */
	public boolean isBalanced() {
		return getRoot().isBalanced();
	}

	/**
	 * Inserisce un nuovo elemento in questo AVLTree. Se l'elemento è già presente
	 * viene incrementato il suo numero di occorrenze.
	 * 
	 * @param el l'elemento da inserire.
	 * @return il numero di confronti tra elementi della classe {@code E} effettuati
	 *         durante l'inserimento
	 * @throws NullPointerException se l'elemento {@code el} è null
	 */
	public int insert(E el) {
		if (el.equals(null))
			throw new NullPointerException();
		if (getRoot() == null) {
			setRoot(new AVLTreeNode(el));
			return 0;
		}
		return getRoot().insert(el);
	}

	/**
	 * Determina se questo AVLTree contiene un certo elemento.
	 * 
	 * @param el l'elemento da cercare
	 * @return true se l'elemento è presente in questo AVLTree, false altrimenti.
	 * @throws NullPointerException se l'elemento {@code el} è null
	 */
	public boolean contains(E el) {
		if (el.equals(null))
			throw new NullPointerException();
		if (getRoot().search(el) != null)
			return true;
		return false;
	}

	/**
	 * Determina se un elemento è presente in questo AVLTree e ne restituisce il
	 * relativo nodo.
	 * 
	 * @param el l'elemento da cercare
	 * @return il nodo di questo AVLTree che contiene l'elemento {@code el} e la sua
	 *         molteplicità, oppure {@code null} se l'elemento {@code el} non è
	 *         presente in questo AVLTree.
	 * @throws NullPointerException se l'elemento {@code el} è null
	 * 
	 */
	public AVLTreeNode getNodeOf(E el) {
		if (el.equals(null))
			throw new NullPointerException();
		return getRoot().search(el);
	}

	/**
	 * Determina il numero di occorrenze di un certo elemento in questo AVLTree.
	 * 
	 * @param el l'elemento di cui determinare il numero di occorrenze
	 * @return il numero di occorrenze dell'elemento {@code el} in questo AVLTree,
	 *         zero se non è presente.
	 * @throws NullPointerException se l'elemento {@code el} è null
	 */
	public int getCount(E el) {
		if (el.equals(null))
			throw new NullPointerException();
		return (getNodeOf(el) != null) ? (getNodeOf(el).getCount()) : 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String descr = "AVLTree [root=" + root.el.toString() + ", size=" + size + ", numberOfNodes=" + numberOfNodes
				+ "]\n";
		return descr + this.root.toString();
	}

	/**
	 * Restituisce la lista degli elementi contenuti in questo AVLTree secondo
	 * l'ordine determinato dalla visita in-order. Per le proprietà dell'albero AVL
	 * la lista ottenuta conterrà gli elementi in ordine crescente rispetto
	 * all'ordinamento naturale della classe {@code E}. Nel caso di elementi
	 * ripetuti, essi appaiono più volte nella lista consecutivamente.
	 * 
	 * @return la lista ordinata degli elementi contenuti in questo AVLTree, tenendo
	 *         conto della loro molteplicità.
	 */
	public List<E> inOrderVisit() {
		return getRoot().inOrderVisit();
	}

	/**
	 * Restituisce l'elemento minimo presente in questo AVLTree.
	 * 
	 * @return l'elemento minimo in questo AVLTree, {@code null} se questo AVLTree è
	 *         vuoto.
	 */
	public E getMinimum() {
		if (isEmpty())
			return null;
		return getRoot().getMinimum().getEl();
	}

	/**
	 * Restituisce l'elemento massimo presente in questo AVLTree.
	 * 
	 * @return l'elemento massimo in questo AVLTree, {@code null} se questo AVLTree
	 *         è vuoto.
	 */
	public E getMaximum() {
		if (isEmpty())
			return null;
		return getRoot().getMaximum().getEl();
	}

	/**
	 * Restituisce l'elemento <b>strettamente</b> successore, in questo AVLTree, di
	 * un dato elemento. Si richiede che l'elemento passato sia presente
	 * nell'albero.
	 * 
	 * @param el l'elemento di cui si chiede il successore
	 * @return l'elemento <b>strettamente</b> successore, rispetto all'ordinamento
	 *         naturale della classe {@code E}, di {@code el} in questo AVLTree,
	 *         oppure {@code null} se {@code el} è l'elemento massimo.
	 * @throws NullPointerException     se l'elemento {@code el} è null
	 * @throws IllegalArgumentException se l'elemento {@code el} non è presente in
	 *                                  questo AVLTree.
	 */
	public E getSuccessor(E el) {
		if (el.equals(null))
			throw new NullPointerException();
		if (!contains(el))
			throw new IllegalArgumentException();
		if (el.compareTo(getMaximum()) == 0)
			return null;
		return getNodeOf(el).getSuccessor().getEl();
	}

	/**
	 * Restituisce l'elemento <b>strettamente</b> predecessore, in questo AVLTree,
	 * di un dato elemento. Si richiede che l'elemento passato sia presente
	 * nell'albero.
	 * 
	 * @param el l'elemento di cui si chiede il predecessore
	 * @return l'elemento <b>strettamente</b> predecessore rispetto all'ordinamento
	 *         naturale della classe {@code E}, di {@code el} in questo AVLTree,
	 *         oppure {@code null} se {@code el} è l'elemento minimo.
	 * @throws NullPointerException     se l'elemento {@code el} è null
	 * @throws IllegalArgumentException se l'elemento {@code el} non è presente in
	 *                                  questo AVLTree.
	 */
	public E getPredecessor(E el) {
		if (el.equals(null))
			throw new NullPointerException();
		if (!contains(el))
			throw new IllegalArgumentException();
		if (el.compareTo(getMinimum()) == 0)
			return null;
		return getNodeOf(el).getPredecessor().getEl();
	}

	/**
	 * Gli elementi di questa classe sono i nodi di un AVLTree, che è la classe
	 * "involucro" della struttura dati.
	 * 
	 * @author Luca Tesei
	 *
	 */

	public class AVLTreeNode {

		// etichetta del nodo
		private E el;

		// molteplicità dell'elemento el
		private int count;

		// sottoalbero sinistro
		private AVLTreeNode left;

		// sottoalbero destro
		private AVLTreeNode right;

		// genitore del nodo, null se questo nodo è la radice dell'AVLTree
		private AVLTreeNode parent;

		// altezza del sottoalbero avente questo nodo come radice
		private int height;

		/**
		 * Create an AVLTreeNode as a root leaf
		 * 
		 * @param el the element
		 */
		public AVLTreeNode(E el) {
			this.el = el;
			this.count = 1;
			this.left = null;
			this.right = null;
			this.parent = null;
			this.height = 0;
		}

		/**
		 * Create an AVLTreeNode node containing one element to be considered child of
		 * the given parent.
		 * 
		 * @param el     the element
		 * @param parent the parent of the node
		 */
		public AVLTreeNode(E el, AVLTreeNode parent) {
			this.el = el;
			this.count = 1;
			this.left = null;
			this.right = null;
			this.parent = parent;
			this.height = 0;
		}

		/**
		 * Restituisce il nodo predecessore di questo nodo. Si suppone che esista un
		 * nodo predecessore, cioè che questo nodo non contenga l'elemento minimo del
		 * sottoalbero di cui è radice.
		 * 
		 * @return il nodo predecessore
		 */
		public AVLTreeNode getPredecessor() {
			if (getLeft() != null && getLeft().getMaximum() != null)
				return getLeft().getMaximum();
			for (AVLTreeNode node = this; node.getParent() != null; node = node.getParent())
				if (node != node.getParent().getLeft())
					return node.getParent();
			return null;
		}

		/**
		 * Restituisce il nodo successore di questo nodo. Si suppone che esista un nodo
		 * successore, cioè che questo nodo non contenga l'elemento massimo del
		 * sottoalbero di cui è radice.
		 * 
		 * @return il nodo successore
		 */
		public AVLTreeNode getSuccessor() {
			if (getRight() != null && getRight().getMinimum() != null)
				return getRight().getMinimum();
			for (AVLTreeNode node = this; node.getParent() != null; node = node.getParent())
				if (node != node.getParent().getRight())
					return node.getParent();
			return null;
		}

		/**
		 * Restituisce il nodo contenente l'elemento massimo del sottoalbero di cui
		 * questo nodo è radice.
		 * 
		 * @return il nodo contenente l'elemento massimo del sottoalbero di cui questo
		 *         nodo è radice.
		 */
		public AVLTreeNode getMaximum() {
			AVLTreeNode temp = this;
			while (temp.getRight() != null)
				temp = temp.getRight();
			return temp;
		}

		/**
		 * Restituisce il nodo contenente l'elemento minimo del sottoalbero di cui
		 * questo nodo è radice.
		 * 
		 * @return il nodo contenente l'elemento minimo del sottoalbero di cui questo
		 *         nodo è radice.
		 */
		public AVLTreeNode getMinimum() {
			AVLTreeNode temp = this;
			while (temp.getLeft() != null)
				temp = temp.getLeft();
			return temp;
		}

		/**
		 * Determina se questo è un nodo foglia.
		 * 
		 * @return true se questo nodo non ha figli, false altrimenti.
		 */
		public boolean isLeaf() {
			if (getLeft() != null || getRight() != null)
				return false;
			return true;
		}

		/**
		 * Restituisce l'altezza del sottoalbero la cui radice è questo nodo.
		 * 
		 * @return l'altezza del sotto albero la cui radice è questo nodo.
		 */
		public int getHeight() {
			return this.height;
		}

		/**
		 * Aggiorna l'altezza del sottoalbero la cui radice è questo nodo supponendo che
		 * l'altezza dei nodi figli sia già stata aggiornata.
		 */
		public void updateHeight() {
			if (isLeaf() || getParent() == null && getRight() == null && getLeft() == null)
				height = 0;
			else {
				if (getLeft() == null)
					height = getRight().getHeight() + 1;
				else if (getRight() == null)
					height = getLeft().getHeight() + 1;
				else
					height = Math.max(getLeft().getHeight(), getRight().getHeight()) + 1;
			}
		}

		/**
		 * Determina il fattore di bilanciamento di questo nodo. Se il nodo è una foglia
		 * il fattore di bilanciamento è 0. Se il nodo ha solo il figlio sinistro allora
		 * il fattore di bilanciamento è l'altezza del figlio sinistro + 1. Se il nodo
		 * ha solo il figlio destro allora il fattore di bilanciamento è l'altezza del
		 * figlio destro + 1, moltiplicata per -1. Se il nodo ha entrambi i figli il
		 * fattore di bilanciamento è l'altezza del figlio sinistro meno l'altezza del
		 * figlio destro.
		 * 
		 * @return il fattore di bilanciamento di questo nodo.
		 */
		public int getBalanceFactor() {
			int balance = 0;
			if (isLeaf())
				return balance;
			if (getLeft() != null && getRight() == null)
				balance = getLeft().getHeight() + 1;
			if (getRight() != null && getLeft() == null)
				balance = -(getRight().getHeight() + 1);
			if (getLeft() != null && getRight() != null)
				balance = getLeft().getHeight() - getRight().getHeight();
			return balance;
		}

		/**
		 * Determina se questo nodo e tutti i suoi discendenti hanno un fattore di
		 * bilanciamento compreso tra -1 e 1.
		 * 
		 * @return true se questo nodo e tutti i suoi discendenti sono bilanciati, false
		 *         altrimenti.
		 */
		public boolean isBalanced() {
			if (getBalanceFactor() <= 1 && getBalanceFactor() >= -1)
				return true;
			return false;
		}

		/**
		 * @return the el
		 */
		public E getEl() {
			return el;
		}

		/**
		 * @param el the el to set
		 */
		public void setEl(E el) {
			this.el = el;
		}

		/**
		 * @return the count
		 */
		public int getCount() {
			return count;
		}

		/**
		 * @param count the count to set
		 */
		public void setCount(int count) {
			this.count = count;
		}

		/**
		 * @return the left
		 */
		public AVLTreeNode getLeft() {
			return left;
		}

		/**
		 * @param left the left to set
		 */
		public void setLeft(AVLTreeNode left) {
			this.left = left;
		}

		/**
		 * @return the right
		 */
		public AVLTreeNode getRight() {
			return right;
		}

		/**
		 * @param right the right to set
		 */
		public void setRight(AVLTreeNode right) {
			this.right = right;
		}

		/**
		 * @return the parent
		 */
		public AVLTreeNode getParent() {
			return parent;
		}

		/**
		 * @param parent the parent to set
		 */
		public void setParent(AVLTreeNode parent) {
			this.parent = parent;
		}

		/**
		 * @param height the height to set
		 */
		public void setHeight(int height) {
			this.height = height;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuffer s = new StringBuffer();
			s.append("(");
			s.append(this.el);
			s.append(", ");
			if (this.left == null)
				s.append("()");
			else
				s.append(this.left.toString());
			s.append(", ");
			if (this.right == null)
				s.append("()");
			else
				s.append(this.right.toString());
			s.append(")");
			return s.toString();
		}

		/**
		 * Restituisce la lista dei nodi contenuti in questo AVLTreeNode secondo
		 * l'ordine determinato dalla visita in-order. Per le proprietà dell'albero AVL
		 * la lista ottenuta conterrà gli elementi in ordine crescente rispetto
		 * all'ordinamento naturale della classe {@code E}. Nel caso di elementi
		 * ripetuti, essi appaiono più volte nella lista consecutivamente.
		 * 
		 * @return la lista ordinata degli elementi contenuti in questo AVLTreeNode,
		 *         tenendo conto della loro molteplicità.
		 */
		private List<E> inOrderVisit() {
			List<E> temp = new ArrayList<E>();
			if (getLeft() != null)
				temp.addAll(getLeft().inOrderVisit());
			for (int x = 0; x < getCount(); x++)
				temp.add(getEl());
			if (getRight() != null)
				temp.addAll(getRight().inOrderVisit());
			return temp;
		}

		/**
		 * Ricerca un elemento a partire da questo nodo.
		 * 
		 * @param el the element to search for
		 * 
		 * @return the node containing the element or null if the element is not found
		 */
		public AVLTreeNode search(E el) {
			AVLTreeNode temp = getRoot();
			while (temp != null) {
				int compare = el.compareTo(temp.getEl());
				if (compare == 0)
					return temp;
				if (compare < 0)
					temp = temp.getLeft();
				else
					temp = temp.getRight();
			}
			return null;
		}

		/**
		 * Inserisce un elemento nell'albero AVL a partire da questo nodo. Se l'elemento
		 * è già presente ne aumenta semplicemente la molteplicità di uno. Se l'elemento
		 * non è presente aggiunge un nodo nella opportuna posizione e poi procede al
		 * ribilanciamento dell'albero se l'inserimento del nuovo nodo provoca uno
		 * sbilanciamento in almeno un nodo.
		 * 
		 * @param el l'elemento da inserire
		 * 
		 * @return il numero di confronti tra elementi della classe {@code E} effettuati
		 *         durante l'inserimento.
		 */
		public int insert(E el) {
			AVLTreeNode temp = null;
			int contatore = 1, compare = getEl().compareTo(el);
			size++;
			if (compare == 0) {
				count++;
				return contatore;
			}
			numberOfNodes++;
			if (compare > 0) {
				if (getLeft() != null)
					contatore += getLeft().insert(el);
				else {
					setLeft(new AVLTreeNode(el, this));
					updateHeightParent();
					for (temp = this; temp != null;)
						if (temp.getBalanceFactor() == 2 || temp.getBalanceFactor() == -2)
							break;
						else
							temp = temp.getParent();
				}
				if (temp != null) {
					if (temp.getBalanceFactor() == 2) {
						if (temp.getLeft() != null && temp.getLeft().getBalanceFactor() == 1)
							temp.leftRotation();
						if (temp.getLeft() != null && temp.getLeft().getBalanceFactor() == -1)
							temp.leftRightRotation();
					} else if (temp.getBalanceFactor() == -2 && temp.getRight() != null
							&& temp.getRight().getBalanceFactor() == 1)
						temp.rightLeftRotation();
				}
			} else {
				if (getRight() != null)
					contatore += getRight().insert(el);
				else {
					setRight(new AVLTreeNode(el, this));
					updateHeightParent();
					for (temp = this; temp != null;)
						if (temp.getBalanceFactor() == 2 || temp.getBalanceFactor() == -2)
							break;
						else
							temp = temp.getParent();
				}
				if (temp != null) {
					if (temp.getBalanceFactor() == -2) {
						if (temp.getRight() != null && temp.getRight().getBalanceFactor() == 1)
							temp.rightLeftRotation();
						if (temp.getRight() != null && temp.getRight().getBalanceFactor() == -1)
							temp.rightRotation();
					} else if (temp.getBalanceFactor() == 2 && temp.getLeft() != null
							&& temp.getLeft().getBalanceFactor() == -1)
						temp.leftRightRotation();
				}
			}
			updateHeightParent();
			return contatore;
		}

		// Metodo per aggiornare l'altezza nell'insert
		private void updateHeightParent() {
			updateHeight();
			if (getParent() != null)
				getParent().updateHeightParent();
		}

		// Metodo per la rotazione sinistra-sinistra
		private void leftRotation() {
			AVLTreeNode temp = this, temp2 = temp.getLeft();
			temp.setLeft(temp2.getRight());
			if (temp2.getRight() != null)
				temp2.getRight().setParent(temp);
			temp2.setParent(temp.getParent());
			temp2.setRight(temp);
			temp.setParent(temp2);
			if (temp2.getParent() != null)
				if (temp2.getParent().getLeft() != null && temp2.getParent().getLeft().getEl() == temp.getEl())
					temp2.getParent().setLeft(temp2);
				else
					temp2.getParent().setRight(temp2);
			while (temp2.getParent() != null)
				temp2 = temp2.getParent();
			setRoot(temp2);
			updateHeight();
		}

		// Metodo per la rotazione destra-destra
		private void rightRotation() {
			AVLTreeNode temp = this, temp2 = temp.getRight();
			temp.setRight(temp2.getLeft());
			if (temp2.getLeft() != null)
				temp2.getLeft().setParent(temp);
			temp2.setParent(temp.getParent());
			temp2.setLeft(temp);
			temp.setParent(temp2);
			if (temp2.getParent() != null)
				if (temp2.getParent().getRight() != null && temp2.getParent().getRight().getEl() == temp.getEl())
					temp2.getParent().setRight(temp2);
				else
					temp2.getParent().setLeft(temp2);
			while (temp2.getParent() != null)
				temp2 = temp2.getParent();
			setRoot(temp2);
			updateHeight();
		}

		// Metodo per la rotazione sinistra-destra
		private void leftRightRotation() {
			getLeft().rightRotation();
			leftRotation();
		}

		// Metodo per la rotazione destra-sinistra
		private void rightLeftRotation() {
			getRight().leftRotation();
			rightRotation();
		}
	}
}