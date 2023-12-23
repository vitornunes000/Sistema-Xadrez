package chessEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.BoardException;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class ChessMatch {
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private boolean checkMate;
	private ChessPiece enPassantVulnerable;
	private ChessPiece promoted;
	private Board board;
	List<ChessPiece> piecesOntheBoard = new ArrayList<>();
	List<ChessPiece> capturedPieces = new ArrayList<>();
	
	
	public ChessMatch() {
		board = new Board(8,8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	//cria a matriz das peças que é usada para printar o tabuleiro
	public ChessPiece[][] getPiece(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for(int i = 0; i<board.getRows(); i++) {
			for(int j = 0; j<board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i , j);
			}
		}
		return mat;
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color currentPlayer() {
		return currentPlayer;
	}
	private void setCurrentColor(Color currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	// metodo que coloca uma peça no tabuleiro de acordo com sua cordenada no xadrez
	private void placeNewPiece(char column, int row , ChessPiece chessPiece) {
		board.placePiece(chessPiece, new ChessPosition(column, row).toPosition());
		piecesOntheBoard.add(chessPiece);
	}
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	//metodo auxiliar que retorna o rei pela cor
	//esse metodo é utilizado pelo testCheck 
	private ChessPiece king(Color color){
		List<Piece> list= piecesOntheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for(Piece p: list) {
			if(p instanceof King) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("There is no" + color + "king on the board");
	}
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces= piecesOntheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		for(Piece p: opponentPieces) {
			if(p.possibleMove(kingPosition) ) {
				return true;
			}
	}
		return false;
	}
	
	// metodo auxiliar que verifica se o rei esta em checkmate
	private boolean testCheckMate(Color color) {
		if(!testCheck(color)) {
			return false;
		}
		//recebe a lista de peças do mesmo time e verifica se o mov de alguma pode desfazer o check
		List<Piece> list = piecesOntheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		// para cada peça p na lista vamos receber a lista de possiveis movimentos
		for(Piece p: list) {
			boolean[][] matMoves = p.possibleMoves();
			//verifica cada posição de possivel mov e faz uma jogada
			for(int i = 0; i<board.getRows(); i++) {
				for(int j = 0; j<board.getColumns(); j++) {
					if(matMoves[i][j]) {
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);
						boolean testcheck = testCheck(color) ;
						//se o rei não estiver em check retorna falso
						// mas se estiver, o loop termina e o metodo retorna true
						undoMove(source, target, capturedPiece);
						if(!testCheck(color)) {
							return false;
						}
					}
				}
			}
		} 	return true;
	}
	
	public boolean getCheck() {
		return check;
	}
	public boolean getCheckMate() {
		return checkMate;
	}
	// metodo auxiliar que passa a vez do jogador
	private void nextTurn() {
		turn++;
		//operador ternario que troca o valor da variavel
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	//coloca as peças no tabuleiro
	private void initialSetup() {
		//usa metodo do board que coloca peças passando como argumento o construtor da peça
		// e sua posição
		
		//peças brancas
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE, this));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('d', 1, new Queen(board, Color.WHITE));
        placeNewPiece('b', 1, new Knight(board, Color.WHITE));
        placeNewPiece('g', 1, new Knight(board, Color.WHITE));
        
        //peças pretas
        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK, this));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('d', 8, new Queen(board, Color.BLACK));
        placeNewPiece('b', 8, new Knight(board, Color.BLACK));
        placeNewPiece('g', 8, new Knight(board, Color.BLACK));
	}
	//movimenta a peça e efetua uma captura
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
		Position source = sourcePosition.toPosition();
		validateSourcePosition(source);
		Position target = targetPosition.toPosition();
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		//logica que verifica se o seu proprio rei esta em check
		if(testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("player can´t put your own king in check!!");
		}
		//operador ternario que verifica se o oponente esta em check e então muda a variavel 
		//da partida
		check = (testCheck(opponent(currentPlayer)) ? true : false);
		//testa se o movimento da peça causou checkmate
		if(testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		}
		else {
			nextTurn();
		}
		
		return (ChessPiece) capturedPiece;
		
	}
	// validar se a posição de origem informada é valida
	private void validateSourcePosition(Position source) {
	    if (!board.thereIsAPiece(source)) {
	        throw new ChessException("There is no piece on source position!!");
	    }
	    // verifica se o jogador escolheu a peça adversaria 
	    if(currentPlayer != ((ChessPiece)board.piece(source)).getColor()) {
			throw new ChessException("is not your turn !!");
		}
	    //validar se existem movimentos que a peça possa realizar
	    if (!board.piece(source).isThereAnyPossibleMove()) {
	    	throw new ChessException("there is not possible moves for the chosen piece!!");
	    }
	}
	private void validateTargetPosition(Position source, Position target) {
		 //validar se existem movimentos que a peça possa realizar
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can´t move to target position");
		}
	}
	//remover a peça da origem e uma possivel peça do destino
	private Piece makeMove(Position source, Position target) {
		ChessPiece p = (ChessPiece)board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		
		if(capturedPiece != null) {
			piecesOntheBoard.remove(capturedPiece);
			capturedPieces.add((ChessPiece)capturedPiece);
			}
		board.placePiece(p, target);
		p.increaseMoveCount();
		return capturedPiece;
	}
	
	private void undoMove(Position source, Position target, Piece capturedPiece ) {
		ChessPiece p = (ChessPiece)board.removePiece(target);
		board.placePiece(p, source);
		
		if(capturedPiece != null) {
			piecesOntheBoard.add((ChessPiece)capturedPiece);
			capturedPieces.remove(capturedPiece);
			board.placePiece(capturedPiece, target);
		}
		p.increaseMoveCount();
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}
	
	
}
