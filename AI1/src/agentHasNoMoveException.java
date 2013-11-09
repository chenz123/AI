
public class agentHasNoMoveException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public agentHasNoMoveException(Agent<?, ?, ?> a){
		super(a.getName() + " has no moves left!");
	}
}
