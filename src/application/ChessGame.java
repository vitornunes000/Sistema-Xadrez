package application;

import java.util.Scanner;

import chessEntities.ChessMatch;
import chessEntities.ChessPiece;
import chessEntities.ChessPosition;

public class ChessGame {

	public static void main(String[] args) {
		// Sistema jogo de xadrez
		ChessMatch chessmatch = new ChessMatch();
		Scanner sc = new Scanner(System.in);
		while(true) {
		Ui.printBoard(chessmatch.getPiece());
		System.out.println();
		System.out.println("digite uma posição da peça que deseja mover");
		System.out.println();
		ChessPosition source = Ui.readChessPosition(sc);
		System.out.println();
		System.out.println("digite a posição de destino da peça!!");
		ChessPosition target = Ui.readChessPosition(sc);
		ChessPiece chesspiece = chessmatch.performChessMove(source, target);
 		}
	}

}
