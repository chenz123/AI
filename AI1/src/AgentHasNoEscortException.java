
public class AgentHasNoEscortException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AgentHasNoEscortException(Agent<?, ?, ?> a){
		super("Agent "+a.getName() + " has no escort!");
	}
}
