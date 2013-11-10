public class AgentIsDoneException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AgentIsDoneException(Agent<?, ?, ?> a) {
		super("Agent " + a.getName()
				+ " Has no more moves to make, stopping agent. Score: "
				+ a.getScore());
	}
}
