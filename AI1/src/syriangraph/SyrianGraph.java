package syriangraph;

import graph.Graph;

import java.util.AbstractCollection;
import java.util.ArrayList;

public class SyrianGraph extends Graph<SyrianVertex, SyrianEdge>{

	
	
	public SyrianGraph(AbstractCollection<SyrianVertex> vertices,
			AbstractCollection<SyrianEdge> edges) {
		super(vertices, edges);
	}

	public AbstractCollection<SyrianEdge> getEdgesForVertex(SyrianVertex v){
		
		AbstractCollection<SyrianEdge> res = new ArrayList<SyrianEdge>();
		// collect all edges for a given vertex
		for (SyrianEdge e : getEdges()){
			if (e.getV1() == v || e.getV2() == v){
				res.add(e);
			}
		}
		
		return res;
	}

}
