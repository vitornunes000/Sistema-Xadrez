package boardgame;

public class BoardException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	
	public BoardException(String msg) {
		this.msg = msg;
	}

}
