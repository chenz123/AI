package syriangraph;

import graph.BaseEdge;

//public class SyrianEdge implements Edge{

public class SyrianEdge extends BaseEdge<SyrianVertex, SyrianEdge>{

	public SyrianEdge(int number, SyrianVertex v1, SyrianVertex v2) {
		super(number, v1, v2);
	}

}
