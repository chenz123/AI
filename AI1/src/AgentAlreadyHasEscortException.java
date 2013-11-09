
public class AgentAlreadyHasEscortException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AgentAlreadyHasEscortException(Agent<?,?,?> a){
		super("Agent "+a.getName()+" Already has ecort!");
	}
}
