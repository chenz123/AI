package syriangraph;

import graph.BaseGraph;
import java.util.AbstractCollection;
import java.util.ArrayList;

public class SyrianGraph<V extends SyrianVertex<E, V>, E extends SyrianEdge<V, E>> extends
		BaseGraph<V, E> {

	public SyrianGraph(AbstractCollection<V> vertices,
			AbstractCollection<E> edges) {
		super(vertices, edges);
	}

	public AbstractCollection<E> getEdgesForVertex(SyrianVertex v) {

		AbstractCollection<E> res = new ArrayList<E>();
		// collect all edges for a given vertex
		for (E e : getEdges()) {
			if (e.getV1() == v || e.getV2() == v) {
				res.add(e);
			}
		}

		return res;
	}

}
