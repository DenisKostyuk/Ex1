package ex1.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import ex1.src.WGraph_Algo;
import ex1.src.WGraph_DS;
import ex1.src.node_info;
import ex1.src.weighted_graph;
import ex1.src.weighted_graph_algorithms;

class WGraph_AlgoTest {

	@Test
	void isConnected() {
		weighted_graph g = buildBiggerGraph();
		weighted_graph_algorithms wga = new WGraph_Algo();
		wga.init(g);
		assertTrue(wga.isConnected());
		g.removeEdge(1, 2);
		g.removeEdge(2, 3);
		wga.init(g);
		assertFalse(wga.isConnected());
		weighted_graph g2 = new WGraph_DS();
		g2.addNode(0);
		g2.addNode(1);
		g2.addNode(2);
		g2.addNode(3);
		g2.addNode(4);
		weighted_graph_algorithms wga2 = new WGraph_Algo();
		wga2.init(g2);
		assertFalse(wga2.isConnected());
	}
	@Test
	void shortestPathDistance() {
		weighted_graph g = buildGraph();
		weighted_graph_algorithms wga = new WGraph_Algo();
		wga.init(g);
		assertEquals(19, wga.shortestPathDist(0, 3));
		g.removeNode(3);
		g.connect(5, 6, 0);
		wga.init(g);
		assertEquals(6, wga.shortestPathDist(0, 6));
		weighted_graph g2 = new WGraph_DS();
		g2.addNode(0);
		g2.addNode(1);
		g2.addNode(2);
		g2.addNode(3);
		g2.addNode(4);
		weighted_graph_algorithms wga2 = new WGraph_Algo();
		wga2.init(g2);
		assertFalse(wga2.isConnected());
		assertEquals(-1, wga2.shortestPathDist(0, 1));
	}
	@Test
	void shortestPath() {
		weighted_graph g = buildGraph();

		weighted_graph_algorithms wga = new WGraph_Algo();
		wga.init(g);
		List<node_info> lst = wga.shortestPath(0, 3);
		int [] path = {0,4,1,2,6,3};
		int i = 0;
		for(node_info n : lst) {
			assertEquals(n.getKey(), path[i]);
			i++;
		}
	}
	@Test
	void save_And_load() {
		weighted_graph g = buildBiggerGraph();
		weighted_graph_algorithms wga = new WGraph_Algo();
		wga.init(g);
		String str = "saved.obj";
		wga.save(str);
		weighted_graph copied = new WGraph_DS();
		weighted_graph_algorithms second = new WGraph_Algo();
		second.init(copied);
		second.load(str);
		assertEquals(g, second.getGraph());
		g.removeNode(0);
		assertNotEquals(g, second.getGraph());
	}
	private weighted_graph buildGraph() {
		weighted_graph g = new WGraph_DS();
		g.addNode(0);
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addNode(4);
		g.addNode(5);
		g.addNode(6);
		g.connect(0, 1, 70);
		g.connect(1, 2, 5);
		g.connect(2, 3, 16);
		g.connect(0, 4, 1);
		g.connect(4, 1, 2);
		g.connect(1, 5, 3);
		g.connect(5, 2, 4);
		g.connect(2, 6, 5);
		g.connect(6, 3, 6);
		return g;
	}
	private weighted_graph buildBiggerGraph() {
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
		return g;
	}
	
}
