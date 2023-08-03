package boardgame;

public abstract class  Piece {
	protected Position position;
	private Board board;
	
	public Piece() {
		
	}
	public Piece(Board board) {
		this.board = board;
	}
	public Board getBoard() {
		return board;
	}
	//metodo abstrato utilizado nas subclasses que recebe os possiveis movimentos da peça
	//recebe uma matrix de posições e verifica se são movimentos possiveis
	public abstract boolean[][] possibleMoves();
	
	//metodo auxiliar que verifica se uma posição tal é um possivel movimento
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		for(int i = 0; i<mat.length; i++) {
			for(int j = 0; j<mat.length; j++) {
				if(mat[i][j] == true) {
					return true;
				}
			}
		} return false;
 	}
	
}
