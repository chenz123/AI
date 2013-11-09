public class SyrianEdge extends BaseEdge<SyrianVertex> {

	private boolean hasTerrorists;

	public SyrianEdge(SyrianVertex v1, SyrianVertex v2) {
		super(v1, v2);
		this.hasTerrorists = false;
	}

	public SyrianEdge(SyrianVertex startVertex, SyrianVertex endVertex,
			int weight, boolean blocked) {
		super(startVertex, endVertex);
		this.hasTerrorists = blocked;
		this.setWeight(weight);
	}

	public boolean hasTerrorists() {
		return this.hasTerrorists;
	}

	public void clearTerrorists() {
		this.hasTerrorists = false;
	}

	public String toString() {
		return "E#" + this.getNumber() + " " + this.getV1().getNumber() + "<->"
				+ this.getV2().getNumber() + " BLOCKED:"
				+ (this.hasTerrorists() ? "YES" : "NO") + " WEIGHT:"
				+ this.getWeight();
	}

}
