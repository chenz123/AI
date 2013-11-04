package syriangraph;

import graph.BaseEdge;

//public class SyrianEdge implements Edge{

public class SyrianEdge<V extends SyrianVertex<E, V>, E extends SyrianEdge<V, E>> extends BaseEdge<V, E>{

	public SyrianEdge(int number) {
		super(number);
	}

}
