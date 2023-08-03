package boardgame;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board() {
		
	}

	public Board(int rows, int columns) throws BoardException {
		if(rows < 1 || columns <1) {
			throw new BoardException("error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}


	public int getColumns() {
		return columns;
	}


	public Piece[][] getPieces() {
		return pieces;
	}

	public Piece piece(int row, int column) {
		if(!positionExists(row, column)) {
			throw new BoardException("Position not on the board!!!");
		}
		return pieces[row][column];
	}
	public Piece piece(Position position){
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board!!!");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position){
		if(thereIsAPiece(position)) {
			throw new BoardException("there is a already piece on position!!!");
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	//metodo que verifica se uma dada linha e coluna estao dentro dos parametros
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	//metodo public que utiliza o anterior
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position){
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board!!!");
		}
		return piece(position) != null;
	}
	public Piece removePiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board!!!");
		}
		if(piece(position) == null) {
			return null;
		}
		Piece piece = piece(position);
		piece.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return piece;
	}
}
