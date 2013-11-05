package syriangraph;

import graph.BaseGraph;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SyrianGraph extends BaseGraph<SyrianVertex, SyrianEdge> {

	public SyrianGraph(AbstractCollection<SyrianVertex> vertices,
			AbstractCollection<SyrianEdge> edges) {
		super(vertices, edges);
	}

	public SyrianGraph(String filename) throws FileNotFoundException {
		super();
		Scanner sc = new Scanner(new File(filename));
		System.out.println("Reading file to syrian graph!");
		while (sc.hasNext())
			this.processInitCMD(sc.next());
		// TODO CONTINUE

	}

	private void processInitCMD(String cmd){
		System.out.println("Processing cmd:" + cmd);
			switch (cmd){
				case 'V': // number of vertices in graph
					for (int i = 0; i < Integer.parseInt(line.substring(2)); i++)
						this.getVertices().add(new SyrianVertex(i, false, false, false));
					break;
				case 'E': // edge definition
					this.getEdges().add(new SyrianEdge())
			}
		
	}

	private Readable File(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	public AbstractCollection<SyrianEdge> getEdgesForVertex(SyrianVertex v) {

		AbstractCollection<SyrianEdge> res = new ArrayList<SyrianEdge>();
		// collect all edges for a given vertex
		for (SyrianEdge e : getEdges()) {
			if (e.getV1() == v || e.getV2() == v) {
				res.add(e);
			}
		}

		return res;
	}

}
