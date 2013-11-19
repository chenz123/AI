
public interface HeuristicNode<H extends HeuristicNode<H>> {

	/**
	 * Get the root this heuristic state references
	 * @return
	 */
	public SyrianVertex getRoot();
	public void setRoot(SyrianVertex root);
	public H getParent();
	public void setParent(H parent);
	public SyrianEdge getPath();
	public void setPath(SyrianEdge path);
	public boolean hasChemicals();
	public void setHasChemicals(boolean agentHasChemicals);
	public boolean hasEscort();
	public void setHasEscort(boolean agentHasEscort);
	public int getHn();
	public void setHn(int hn);

}
