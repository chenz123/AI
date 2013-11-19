/**
 * this is the equivalent of a "State" class
 * but is restricted on a per-node basis
 * @author Reut Sharabani & Chen Zrubavel
 *
 * @param <H> - the HeuristicNode this class
 * 				 is basing on to store edges,
 * 				 resources (chemicals, escort)
 * 				 and vertices
 */
public interface HeuristicNode<H extends HeuristicNode<H>> {

	/**
	 * Get the root (vertex) of the heuristic
	 * trees this node can reference
	 * @return
	 * 			the root (vertex) of this node's states
	 */
	public SyrianVertex getRoot();
	
	/**
	 * Set the root (vertex) of the heuristic
	 * trees this node can reference
	 * @param root - the new root (vertex)
	 */
	public void setRoot(SyrianVertex root);
	
	/**
	 * get the parent heuristic node that
	 * was expanded to this heuristic node
	 * @return
	 * 			the parent state of this node
	 */
	public H getParent();

	/**
	 * set the parent heuristic node that was
	 * supposedly expanded to this heuristic node
	 * @param parent - the new parent state of this node
	 */
	public void setParent(H parent);
	
	/**
	 * get the path to drive in order to
	 * reach this state from it's parent
	 * @return
	 * 			the path to drive in order
	 * 			to reach this state from
	 *			it's parent
	 */
	public SyrianEdge getPath();

	/**
	 * set the path to drive in order to
	 * reach this state from it's parent
	 * @param
	 * 			the path to drive in order
	 * 			to reach this state from
	 *			it's parent
	 */
	public void setPath(SyrianEdge path);
	
	/**
	 * use in order to check if this state has chemicals
	 * @return
	 * 			true - if the state's vertex has chemicals available
	 * 			false - otherwise
	 */
	public boolean hasChemicals();

	/**
	 * use in order to set this state's chemicals availability
	 * @param
	 * 			true - if the state's vertex has chemicals available
	 * 			false - otherwise
	 */
	public void setHasChemicals(boolean agentHasChemicals);
	
	/**
	 * use in order to check if this state's vertex
	 * has escort available
	 * @return
	 * 			true - if there's an escort in this
	 * 			state's root 
	 * 			false - otherwise
	 */
	public boolean hasEscort();
	
	/**
	 * use in order to set if this state's vertex
	 * has escort available
	 * @param
	 * 			true - if there's an escort in this
	 * 			state's root 
	 * 			false - otherwise
	 */
	public void setHasEscort(boolean agentHasEscort);
	
	
	/**
	 * get this state's heuristic value estimate
	 * @return
	 * 			this state's heuristic value
	 */
	public int getHn();
	
	/**
	 * set this state's heuristic value estimate
	 * @param hn
	 * 			this state's new heuristic value
	 */
	public void setHn(int hn);

}
