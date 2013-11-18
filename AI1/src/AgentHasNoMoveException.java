
public class AgentHasNoMoveException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AgentHasNoMoveException(Agent<?, ?, ?> a){
		super(a.getName() + " has no moves left!");
	}
}
