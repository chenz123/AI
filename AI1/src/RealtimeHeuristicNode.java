public class RealtimeHeuristicNode extends BaseHeuristicNode<RealtimeHeuristicNode> {

	private int depth;

	public RealtimeHeuristicNode(SyrianVertex source, RealtimeHeuristicNode parent,
			SyrianEdge path, boolean hasChemicals, boolean hasEscort, int hn,
			int depth) {
		super(source, parent, path, hasChemicals, hasEscort, hn);
		this.depth = depth;
	}

	public int getDepth() {
		return this.depth;
	}

	public String toString() {

		return super.toString() + System.getProperty("line.separator")
				+ "Level: " + this.getDepth();

	}
}
