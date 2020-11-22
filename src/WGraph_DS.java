package ex1.src;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;



public class WGraph_DS implements weighted_graph , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * class that represents an undirected weighted graph.
	 * Including the implementation of the following methods :
	 * • getNode()
	 * • hasEdge()
	 * • getEdge()
	 * • addNode()
	 * • connect()
	 * • getV()
	 * • getV(key)
	 * • removeNode()
	 * • removeEdge()
	 * • nodeSize()
	 * • edgeSize()
	 * • getMC()
	 * • equals()
	 */
	private HashMap <Integer , node_info> nodes;
	private HashMap<Integer , HashMap<Integer , Double>> graph;
	private int MC;
	private int edgeSizeOfG;
	private int nodeSizeOfG;

	private class NodeInfo implements node_info , Serializable {
		/**
		 * class that represents a node in a undirected weighted graph
		 * Including the implementation of the following methods :
		 * • NodeInfo()
		 * • getKey()
		 * • getInfo()
		 * • setInfo()
		 * • getTag()
		 * • setTag()
		 * • equals()
		 * • toString()
		 */
		private static final long serialVersionUID = 1L;
		private int key = 0;
		private String info;
		private double tag;
		//private static int id = 0;
		/**
		 * implementation of Node object which gets a key
		 * as argument and makes a new node type object
		 * complexity -> O(1)
		 * @param key
		 */
		public NodeInfo(int key) {
			this.key = key;
			this.info = null;
			this.tag = 0;
		}
		/**
		 * return a key of a specified node
		 * complexity -> O(1)
		 */
		@Override
		public int getKey() {
			return this.key;
		}
		/**
		 * return the info of a specified node
		 * complexity -> O(1)
		 */
		@Override
		public String getInfo() {
			return this.info;
		}
		/**
		 * sets the info of a specified node
		 * complexity -> O(1)
		 * @param s
		 */
		@Override
		public void setInfo(String s) {
			this.info = s;
		}
		/**
		 * return the tag of a specified node.
		 * complexity -> O(1)
		 */
		@Override
		public double getTag() {
			return this.tag;
		}
		/**
		 * sets the tag of a specified node
		 * complexity -> O(1)
		 * @param t
		 */
		@Override
		public void setTag(double t) {
			this.tag = t;		
		}
		/**
		 * return true or false if there is an equation between
		 * two nodes.
		 * complexity -> O(8) = O(1)
		 * @param obj
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) // complexity -> O(1)
				return true;
			if (obj == null) //complexity -> O(1)
				return false;
			if (getClass() != obj.getClass()) //complexity -> O(1)
				return false;
			NodeInfo other = (NodeInfo) obj; //complexity -> O(1)
			if (info == null) { //complexity -> O(1)
				if (other.info != null)
					return false;
			} else if (!info.equals(other.info)) //complexity -> O(1)
				return false;
			if (key != other.key) //complexity -> O(1)
				return false;
			if (Double.doubleToLongBits(tag) != Double.doubleToLongBits(other.tag)) //complexity -> O(1)
				return false;
			return true;
		}
		/**
		 * toString method that prints the whole node data's
		 * complexity -> O(1)
		 */
		public String toString() {
			return "Key : " + this.key +" " +"Info : " + this.info +" Tag : " + this.tag;
		}
	}
	/**
	 * implementation of the object WGraph_DS
	 * which will represent an undirected weighted graph
	 * complexity -> O(1)
	 * using the following data structures:
	 * •HashMap
	 */
	public WGraph_DS() {
		this.nodes = new HashMap<>();
		this.graph = new HashMap<>();
	}
	/**
	 * return a specified node with the key that is given
	 * in the argument
	 * complexity -> O(1)
	 * @param key
	 */
	@Override
	public node_info getNode(int key) {
		return nodes.get(key);
	}
	/**
	 * return true or false if there is an edge between two nodes
	 * that their keys were given in the argument
	 * complexity -> O(1)
	 * @param node1
	 * @param node2
	 */
	@Override
	public boolean hasEdge(int node1, int node2) { 
		if(graph.get(node1) == null || graph.get(node2) == null &&  graph.get(node1).get(node2) == null ||graph.get(node2).get(node1) == null ) return false;
		return true;
	}
	/**
	 * return the weight between two nodes that their keys 
	 * were given in the arguments
	 * complexity -> O(1)
	 * @param node1
	 * @param node2
	 */
	@Override
	public double getEdge(int node1, int node2) {
		if(hasEdge(node1, node2) ==  true) return graph.get(node1).get(node2);
		else return -1;
	}
	/**
	 * adds a node to the graph .
	 * the key of the new node that is about to get added 
	 * is given as argument
	 * complexity -> O(1)
	 * @param key
	 */
	@Override
	public void addNode(int key) {
		if(nodes.containsKey(key) != true) {
			nodes.put(key, new NodeInfo(key));
			graph.put(key, new HashMap<>());
			MC++;
			nodeSizeOfG++;
		}
	}
	/**
	 * connects two nodes that their keys are given
	 * as arguments and initializes the weight between them with
	 * the weight that is given as argument 
	 * the weight must be >= 0
	 * complexity -> O(1)
	 * @param node1
	 * @param node2
	 * @param w
	 */
	@Override
	public void connect(int node1, int node2, double w) {
		if(w < 0) return; 
		if(hasEdge(node1, node2) == false && node1 != node2) {
			graph.get(node1).put(node2,w);
			graph.get(node2).put(node1,w);
			edgeSizeOfG++;
		}
		MC++;
	}
	/**
	 * return all the nodes of the graph
	 * complexity -> O(1)
	 */
	@Override
	public Collection<node_info> getV() {
		return nodes.values();
	}
	/**
	 * returns all the neighbors of a specified node
	 * that his key was given as argument
	 * complexity -> O(1)
	 * @param node_id
	 */
	@Override
	public Collection<node_info> getV(int node_id) {
		Collection <node_info>cln = new ArrayList<>();
		if(graph.get(node_id) == null) return cln;
		for(Integer i : graph.get(node_id).keySet()) {
			cln.add(getNode(i));
		}
		return cln;
	}
	/**
	 * removes a node from a graph which his key is given
	 * as argument
	 * complexity -> O(1)
	 * @param key
	 */
	@Override
	public node_info removeNode(int key) {
		if(nodes.containsKey(key) != true) return null;
		for(Integer i : graph.get(key).keySet()) {
			graph.get(i).remove(key);
			edgeSizeOfG--;
		}
		node_info n = nodes.remove(key);
		graph.remove(key);
		MC++;
		nodeSizeOfG--;
		return n;
	}
	/**
	 * removes the edge between two nodes which their keys
	 * are given as arguments
	 * complexity -> O(1)
	 */
	@Override
	public void removeEdge(int node1, int node2) {
		if(graph.get(node1).get(node2) != null) {
			graph.get(node1).remove(node2);
			graph.get(node2).remove(node1);
			MC++;
			edgeSizeOfG--;
		}

	}
	/**
	 * returns the number of the nodes in the graph
	 * complexity -> O(1)
	 */
	@Override
	public int nodeSize() {
		return nodeSizeOfG;
	}
	/**
	 * returns the number of edges in the graph
	 * complexity -> O(1)
	 */
	@Override
	public int edgeSize() {
		return edgeSizeOfG;
	}
	/**
	 * returns the number of changes that were done on 
	 * a specified graph
	 * complexity -> O(1)
	 */
	@Override
	public int getMC() {
		return this.MC;
	}
	/**
	 * gets an object as argument and will return true or false
	 * if they are equal.
	 * complexity -> O(n) 
	 * @param obj
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WGraph_DS other = (WGraph_DS) obj;
		if (MC != other.MC)
			return false;
		if (edgeSizeOfG != other.edgeSizeOfG)
			return false;
		if (graph == null) {
			if (other.graph != null)
				return false;
		} else if (!graph.equals(other.graph))
			return false;
		if (nodeSizeOfG != other.nodeSizeOfG)
			return false;
		if (nodes == null) {
			if (other.nodes != null)
				return false;
		} else if (!nodes.equals(other.nodes))
			return false;
		return true;
	}

}
