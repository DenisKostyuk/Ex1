package ex1.src;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;



public class WGraph_Algo implements weighted_graph_algorithms ,  Comparator<node_info> , Serializable{
	/**
	 * this class representing algorithms on a undirected weighted graph
	 * including implementation of :
	 * • init()
	 * • getGraph()
	 * • copy()
	 * • isConnected()
	 * • shortestPathDist()
	 * • shortestPath()
	 * • save()
	 * • load()
	 * • compare()
	 */
	private static final long serialVersionUID = 1L;
	private weighted_graph g;
	//private weighted_graph graph;
	/**
	 * creating an object of this class
	 *complexity -> O(1)
	 */
	public WGraph_Algo() {}
	/**
	 * initialize the object WGraph_Algo with the specified
	 * graph that we are going to do the written algorithms on
	 * complexity -> O(1)
	 * @param g
	 */
	@Override
	public void init(weighted_graph g) {
		this.g = g;

	}
	/**
	 * return the specified graph that this class is working on
	 * complexity -> O(1)
	 */
	@Override
	public weighted_graph getGraph() {
		return this.g;
	}
	/**
	 * makes a deep copy of the specified graph this class is working on
	 * complexity --> O(|v|) + O(|v|)*O(|e|) = O(|v|) * O(|e|)
	 */
	@Override
	public weighted_graph copy() {
		weighted_graph copied = new WGraph_DS();
		for(node_info node : g.getV()) { 
			copied.addNode(node.getKey());
		}
		for(node_info node : g.getV()) {
			for(node_info neighb : g.getV(node.getKey())) {
				copied.connect(node.getKey(), neighb.getKey(), g.getEdge(node.getKey(), neighb.getKey()));
			}
		}
		return copied;
	}
	/**
	 * return true or false if the graph is connected 
	 * by saying connect the meaning is that we can travel from any node 
	 * to any other node we wish to . in case there is a node that cannot be reached , the
	 * method will return false, other wise will return true
	 * complexity --> O(|v|+|e|) + O(|v|) = O(|v|+|e|)
	 * the data structures that are used in this algorithm are
	 * •LinkedList
	 * •Queue
	 */
	@Override
	public boolean isConnected() {
		if (g == null) return true;
		if(g.nodeSize() == 0) return true;
		Queue<node_info> q = new LinkedList<node_info>();
		Iterator <node_info> it =  g.getV().iterator();
		boolean flag = true;
		//if(g.nodeSize() == 0) return true;
		q.add(it.next());
		while(!q.isEmpty()) {
			node_info tmp = q.poll();
			if(tmp.getInfo() != "b") tmp.setInfo("b");
			for(node_info t : g.getV(tmp.getKey())) {
				if(t.getInfo()!="b" && t.getInfo()!="g") {
					q.add(t);
					t.setInfo("g");
				}
			}
		}
		for(node_info n : g.getV()) { //O(|v|)
			if (n.getInfo() != "b") flag = false;
			n.setInfo("");
		}
		return flag;
	}
	/**
	 * gets a source and a destination keys as arguments and return the shortest
	 * path distance between those two nodes with the specified keys uses Dijkstra's algorithm . 
	 * the return is represented by minimum weight
	 * sum between each possible path to get from the source node to the destination node.
	 * if there is no such path , the method will return -1
	 * complexity -->  O (|v|+ |e*logv|) 
	 * the data structures that are used in this algorithm are : 
	 * • PriorityQueue
	 * @param src
	 * @param dest
	 */
	@Override
	public double shortestPathDist(int src, int dest) {
		if(g.getV(dest).isEmpty() || g.getV(src).isEmpty()) return -1;
		for(node_info n : g.getV()) {  //------------>sets all the node TAGS to an infinit value;
			n.setTag(Double.POSITIVE_INFINITY);
		}
		g.getNode(src).setTag(0);
		Comparator<node_info> c = new Comparator<node_info>() {
			public int compare(node_info n1, node_info n2) {
				return Double.compare(n1.getTag() , n2.getTag());
			}
		};
		PriorityQueue<node_info> pq = new PriorityQueue<node_info>(c);
		pq.add(g.getNode(src));
		while(!pq.isEmpty()) {
			node_info tmp = pq.poll(); //first element got polled out of the Queue
			if(tmp.getInfo() != "visited") tmp.setInfo("visited");
			for(node_info n : g.getV(tmp.getKey())) {
				if(n.getInfo() != "visited") {
					if((tmp.getTag() + g.getEdge(tmp.getKey(), n.getKey())) < n.getTag()) {
						n.setTag(tmp.getTag() + g.getEdge(tmp.getKey(), n.getKey())); //adds the distance between the curr node to the curr neighbor
						pq.add(n);
					}
				}
			}
		}
		double dist = g.getNode(dest).getTag();
		for(node_info n : g.getV()) {
			n.setInfo(null);
			n.setTag(0);
		}
		return dist;
	}
	/**
	 * compares the tags between two nodes that their keys were given as arguments.
	 * complexity --> O(1)
	 * @param n1
	 * @param n2
	 */
	@Override
	public int compare(node_info n1, node_info n2) {
		if (n1.getTag() < n2.getTag())
			return -1;
		if (n1.getTag() > n2.getTag())
			return 1;
		return 0;
	}
	/**
	 * return a list of nodes with the shortest path between a source node to a destination node.
	 * uses Dijkstra's algorithm which returns us the shortest path by counting the minimum weight 
	 * between nodes , adds it to the sum and will return the minimum weight from the source node 
	 * to the destination node
	 * complexity -->  O (|v|+ |e*logv|) 
	 * the data structures that are used in this algorithm are : 
	 * • PriorityQueue
	 * • HashMap
	 * • LinkedList
	 * @param src
	 * @param dest
	 */
	@Override
	public List<node_info> shortestPath(int src, int dest) {
		for(node_info n : g.getV()) {  //------------>sets all the node TAGS to an infinit value;
			n.setTag(Double.POSITIVE_INFINITY);
		}
		g.getNode(src).setTag(0);
		Comparator<node_info> c = new Comparator<node_info>() {
			public int compare(node_info n1, node_info n2) {
				return Double.compare(n1.getTag() , n2.getTag());
			}
		};
		PriorityQueue<node_info> pq = new PriorityQueue<node_info>(c);
		HashMap<node_info, node_info> parents = new HashMap<node_info, node_info>();
		pq.add(g.getNode(src));
		while(!pq.isEmpty()) {
			node_info tmp = pq.poll(); //first element got polled out of the Queue
			if(tmp.getInfo() != "visited") tmp.setInfo("visited");
			for(node_info n : g.getV(tmp.getKey())) {
				if(n.getInfo() != "visited") {
					if((tmp.getTag() + g.getEdge(tmp.getKey(), n.getKey())) < n.getTag()) {
						n.setTag(tmp.getTag() + g.getEdge(tmp.getKey(), n.getKey())); //adds the distance between the curr node to the curr neighbor
						pq.add(n);
						parents.put(n, tmp);
					}
				}
			}
		}
		List<node_info> listOfThePath = new LinkedList<>();
		node_info tmp = g.getNode(dest);
		while(tmp != null) {
			listOfThePath.add(0, tmp); // O(distance(source , destination))
			tmp = parents.get(tmp);
		}
		for(node_info n : g.getV()) {
			n.setInfo(null);
			n.setTag(0);
		}
		return listOfThePath;
	}
	/**
	 * saves the graph to a file which the name of this file
	 * will be the String written as argument
	 * complexity -->  O (v + e) because it fills the graph with all the edges and vertices  
	 * @param file
	 */
	@Override
	public boolean save(String file) {
		try {

            FileOutputStream oos = new FileOutputStream(file); 
            ObjectOutputStream out = new ObjectOutputStream(oos); 
            out.writeObject(g);      
            oos.close(); 
            out.close(); 
            return true;
		}catch(IOException ex) {
			ex.printStackTrace();
			return false;
		}
	}
	/**
	 * loads the file with the given name as argument to a graph
	 * and initializes it with the information that is written in the file 
	 * complexity -->  O (v + e) because it fills the graph with all the edges and vertices 
	 * @param file
	 */
	@Override
	public boolean load(String file) {
		try {
			FileInputStream myFile = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(myFile);
			this.g = (weighted_graph)ois.readObject();
			ois.close();
			myFile.close();
			return true;
		}catch(Exception error) {
			error.printStackTrace();
			return false;
		}
	}

}
