package exceptions;

public class NoMoreMovesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoMoreMovesException(String msg){
		super(msg);
	}
}
