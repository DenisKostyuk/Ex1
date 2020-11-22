package ex1.tests;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;


import org.junit.jupiter.api.Test;

import ex1.src.WGraph_DS;
import ex1.src.node_info;
import ex1.src.weighted_graph;



class WGraph_DSTest {

	@Test
	void connect() {
		weighted_graph g = new WGraph_DS();
		g.addNode(0);
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addNode(4);
		g.addNode(5);
		g.addNode(6);
		g.addNode(7);
		g.connect(0, 1, 13);
		g.connect(1, 2, 7);
		g.connect(2, 3, 15);
		g.connect(3, 4, 4);
		g.connect(4, 5, 5);
		g.connect(5, 6, 1);
		g.connect(6, 7, 18);
		g.connect(7, 0, 37);
		assertTrue(g.hasEdge(0, 1));
		g.removeEdge(0, 1);
		assertFalse(g.hasEdge(0, 1));
		g.addNode(10);
		assertFalse(g.hasEdge(1, 10));
		g.connect(1, 10, 8);
		assertTrue(g.hasEdge(1, 10));
		g.removeEdge(1, 10);
		assertFalse(g.hasEdge(1, 10));
		double weight = g.getEdge(1, 2);
		assertNotEquals(10, weight);
		assertEquals(7, weight);
	}
	@Test
	void NodeSize() {
		weighted_graph g = new WGraph_DS();
		g.addNode(0);
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addNode(4);
		g.addNode(5);
		g.addNode(6);
		g.addNode(7);
		g.addNode(8);
		g.addNode(9);
		assertEquals(10, g.nodeSize());
		g.addNode(1);
		assertNotEquals(11, g.nodeSize());
		g.addNode(10);
		assertEquals(11, g.nodeSize());
		g.removeNode(10);
		assertEquals(10, g.nodeSize());
		g.removeNode(10);
		assertEquals(10, g.nodeSize());
	}
	@Test
	void EdgeSize() {
		weighted_graph g = new WGraph_DS();
		g.addNode(0);
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addNode(4);
		g.addNode(5);
		g.addNode(6);
		g.addNode(7);
		g.connect(0, 1, 13);
		g.connect(1, 2, 7);
		g.connect(2, 3, 15);
		g.connect(3, 4, 4);
		g.connect(4, 5, 5);
		g.connect(5, 6, 1);
		g.connect(6, 7, 18);
		g.connect(7, 0, 37);
		assertEquals(7, g.getEdge(1, 2));
		assertEquals(g.getEdge(1, 2), 7);
		g.removeEdge(1, 2);
		assertEquals(-1, g.getEdge(1, 2));
		assertNotEquals(10, g.getEdge(7, 0));
		assertEquals(37, g.getEdge(7, 0));
	}
	@Test
	void getV1() {
		weighted_graph g = new WGraph_DS();
		g.addNode(0);
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addNode(4);
		g.addNode(5);
		g.addNode(6);
		g.addNode(7);
		g.connect(0, 1, 13);
		g.connect(1, 2, 7);
		g.connect(2, 3, 15);
		g.connect(3, 4, 4);
		g.connect(4, 5, 5);
		g.connect(5, 6, 1);
		g.connect(6, 7, 18);
		g.connect(7, 0, 37);
		g.connect(1, 3, 0);
		g.connect(1, 5, 11);
		g.connect(1, 7, 105);
		Collection <node_info> cl = g.getV(1);
		Iterator<node_info> it = cl.iterator();
		while(it.hasNext()) {
			node_info n = it.next();
			assertNotNull(n);
		}
	}
	@Test
	void getV2() {
		weighted_graph g = new WGraph_DS();
		g.addNode(0);
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addNode(4);
		g.addNode(5);
		g.addNode(6);
		g.addNode(7);
		g.connect(0, 1, 13);
		g.connect(1, 2, 7);
		g.connect(2, 3, 15);
		g.connect(3, 4, 4);
		g.connect(4, 5, 5);
		g.connect(5, 6, 1);
		g.connect(6, 7, 18);
		g.connect(7, 0, 37);
		g.connect(1, 3, 0);
		g.connect(1, 5, 11);
		g.connect(1, 7, 105);
		Collection <node_info> cl = g.getV(1);
		Iterator<node_info> it = cl.iterator();
		while(it.hasNext()) {
			assertTrue(g.hasEdge(1, it.next().getKey()));
		}
		g.removeNode(1);
		assertFalse(g.hasEdge(1, 3));
		assertTrue(g.hasEdge(3, 2));
		assertTrue(g.hasEdge(4, 5));
		assertTrue(g.hasEdge(7, 0));
		g.addNode(1);
		g.connect(1, 2, 7);
		g.connect(1, 0, 13);
		g.connect(1, 3, 0);
		g.connect(1, 5, 11);
		g.connect(1, 7, 105);
		assertTrue(g.hasEdge(1, 5));
	}
	@Test
	void RemoveNode() {
		weighted_graph g = new WGraph_DS();
		g.addNode(0);
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addNode(4);
		g.addNode(5);
		g.addNode(6);
		g.addNode(7);
		g.connect(0, 1, 13);
		g.connect(1, 2, 7);
		g.connect(2, 3, 15);
		g.connect(3, 4, 4);
		g.connect(4, 5, 5);
		g.connect(5, 6, 1);
		g.connect(6, 7, 18);
		g.connect(7, 0, 37);
		g.connect(1, 3, 0);
		g.connect(1, 5, 11);
		g.connect(1, 7, 105);
		g.connect(1, 6, 4);
		g.connect(0,4 , 8);
		g.connect(3, 7, 90);
		g.removeNode(1);
		assertFalse(g.hasEdge(1, 5));
		assertFalse(g.hasEdge(1, 0));
		assertFalse(g.hasEdge(1, 2));
		assertFalse(g.hasEdge(1, 3));
		assertFalse(g.hasEdge(1, 6));
		assertTrue(g.hasEdge(2, 3));
		g.addNode(1);
		g.connect(1, 0, 13);
		assertTrue(g.hasEdge(0, 1));
		g.connect(1, 2, 7);
		assertTrue(g.hasEdge(2, 1));
	}
	@Test
	void RemoveEdge() {
		weighted_graph g = new WGraph_DS();
		g.addNode(0);
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addNode(4);
		g.addNode(5);
		g.addNode(6);
		g.addNode(7);
		g.connect(0, 1, 13);
		g.connect(1, 2, 7);
		g.connect(2, 3, 15);
		g.connect(3, 4, 4);
		g.connect(4, 5, 5);
		g.connect(5, 6, 1);
		g.connect(6, 7, 18);
		g.connect(7, 0, 37);
		g.connect(1, 3, 0);
		g.connect(1, 5, 11);
		g.connect(1, 7, 105);
		g.connect(1, 6, 4);
		g.connect(0,4 , 8);
		g.connect(3, 7, 90);
		int numOfEdges = g.edgeSize();
		assertEquals(numOfEdges, 14);
		g.removeEdge(0, 1);
		assertFalse(g.hasEdge(0, 1));
		assertFalse(g.hasEdge(200, 1));
		g.removeEdge(3, 7);
		assertFalse(g.hasEdge(7, 3));
		g.addNode(300);
		g.connect(300, 5, 20000);
		assertTrue(g.hasEdge(300, 5));
	}
	@Test 
	void getMC(){
		weighted_graph g = new WGraph_DS();
		g.addNode(0);
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addNode(4);
		g.addNode(5);
		g.addNode(6);
		g.addNode(7);
		g.connect(0, 1, 13);
		g.connect(1, 2, 7);
		g.connect(2, 3, 15);
		g.connect(3, 4, 4);
		g.connect(4, 5, 5);
		g.connect(5, 6, 1);
		g.connect(6, 7, 18);
		g.connect(7, 0, 37);
		g.connect(1, 3, 0);
		g.connect(1, 5, 11);
		g.connect(1, 7, 105);
		g.connect(1, 6, 4);
		g.connect(0,4 , 8);
		g.connect(3, 7, 90);
		assertEquals(g.getMC(), 22);
		g.addNode(0);
		assertEquals(g.getMC(), 22);
		g.removeNode(1);
		assertEquals(g.getMC(), 23);	
	}
	@Test
	void AddNode(){
		weighted_graph g = new WGraph_DS();
		g.addNode(0);
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addNode(4);
		g.addNode(5);
		g.addNode(6);
		g.addNode(7);
		g.addNode(8);
		g.addNode(9);
		g.addNode(10);
		g.addNode(11);
		assertEquals(12, g.nodeSize());
		g.addNode(0);
		assertEquals(12, g.nodeSize());
		g.removeNode(2);
		assertEquals(11, g.nodeSize());
		assertEquals(13, g.getMC());
	}
	@Test
	void getEdge(){
		weighted_graph g = new WGraph_DS();
		g.addNode(0);
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addNode(4);
		g.addNode(5);
		g.addNode(6);
		g.addNode(7);
		g.connect(0, 1, 13);
		g.connect(1, 2, 7);
		g.connect(2, 3, 15);
		g.connect(3, 4, 4);
		g.connect(4, 5, 5);
		g.connect(5, 6, 1);
		g.connect(6, 7, 18);
		g.connect(7, 0, 37);
		g.connect(1, 3, 0);
		g.connect(1, 5, 11);
		g.connect(1, 7, 105);
		g.connect(1, 6, 4);
		g.connect(0,4 , 8);
		g.connect(3, 7, 90);
		assertEquals(90, g.getEdge(3, 7));
		g.removeEdge(3, 7);
		assertNotEquals(g.getEdge(3, 7), 90);
		assertNotEquals(30, g.getEdge(100, 1000));
	}
	@Test
	void hasEdge() {
		weighted_graph g = new WGraph_DS();
		g.addNode(0);
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addNode(4);
		g.addNode(5);
		g.addNode(6);
		g.addNode(7);
		g.connect(0, 1, 13);
		g.connect(1, 2, 7);
		g.connect(2, 3, 15);
		g.connect(3, 4, 4);
		g.connect(4, 5, 5);
		g.connect(5, 6, 1);
		g.connect(6, 7, 18);
		g.connect(7, 0, 37);
		g.connect(1, 3, 0);
		g.connect(1, 5, 11);
		g.connect(1, 7, 105);
		g.connect(1, 6, 4);
		g.connect(0,4 , 8);
		g.connect(3, 7, 90);
		assertTrue(g.hasEdge(1, 5));
		g.removeEdge(1, 5);
		assertFalse(g.hasEdge(1, 5));
		assertTrue(g.hasEdge(4, 5));
		assertFalse(g.hasEdge(100000, 2));
	}
	@Test
	void checkMillion() {
		weighted_graph milG = new WGraph_DS();
		int i = 0;
		while(i<1000000) {
			milG.addNode(i);
			i++;
		}
		for(int z = 0 ; z < 1000000 ; z++) {
			for(int k=0;k < 10 ; k++) { 
				milG.connect(z, k, k);
				
			}
		}
		assertEquals(milG.nodeSize(), 1000000);
	}
}
