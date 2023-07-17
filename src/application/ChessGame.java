package application;

import chessEntities.ChessMatch;

public class ChessGame {

	public static void main(String[] args) {
		// Sistema jogo de xadrez
		ChessMatch chessmatch = new ChessMatch();
		Ui.printBoard(chessmatch.getPiece());

	}

}
