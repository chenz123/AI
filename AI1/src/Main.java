import java.io.FileNotFoundException;

import syriangraph.SyrianEdge;
import syriangraph.SyrianGraph;
import syriangraph.SyrianVertex;

import graph.BaseEdge;
import graph.BaseGraph;
import graph.BaseVertex;
import graph.Edge;
import graph.Graph;
import graph.Vertex;

public class Main {

	public static void main(String[] args) {
		String testFile = "graph1.graph";
		try {
			SyrianGraph base = new SyrianGraph(testFile);
		} catch (FileNotFoundException e) {
			System.out.println("Didn't find file " + testFile);
			e.printStackTrace();
		}
	}
}
