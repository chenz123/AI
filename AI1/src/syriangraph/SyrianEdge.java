package syriangraph;

import graph.BaseEdge;
import graph.Blockable;
import graph.Weighted;

//public class SyrianEdge implements Edge{

public class SyrianEdge extends BaseEdge<SyrianVertex, SyrianEdge> implements Blockable, Weighted{

	private boolean blocked;
	private long weight;
	
	public SyrianEdge(SyrianVertex v1, SyrianVertex v2, long weight, boolean blocked) {
		super(v1, v2);
		this.weight = weight;
		this.blocked = blocked;
	}

	public SyrianEdge(SyrianVertex v1, SyrianVertex v2) {
		super(v1, v1);
		this.setWeight(0);
		this.blocked = false;
	}

	@Override
	public boolean isBlocked() {
		return this.blocked;
	}

	@Override
	public void clear() throws EdgeNotBlockedException {
		if (!this.isBlocked()){
			throw new EdgeNotBlockedException("Edge "+this.toString()+" was not blocked while clearing was attempted");
		}
		this.blocked = false;
	}
}
