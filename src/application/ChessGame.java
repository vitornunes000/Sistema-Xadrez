package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import boardgame.BoardException;
import chessEntities.ChessException;
import chessEntities.ChessMatch;
import chessEntities.ChessPiece;
import chessEntities.ChessPosition;

public class ChessGame {

	public static void main(String[] args) {
		// Sistema jogo de xadrez
		ChessMatch chessmatch = new ChessMatch();
		Scanner sc = new Scanner(System.in);
		List<ChessPiece> capturedPieces = new ArrayList<>();

		while(!chessmatch.getCheckMate()) {
		try {
			Ui.clearScreen();
			Ui.printMatch(chessmatch, capturedPieces);
			System.out.println();
			System.out.println("digite uma posição da peça que deseja mover:");
			System.out.println();
			ChessPosition sourcePosition = Ui.readChessPosition(sc);
			
			boolean[][] possibleMoves = chessmatch.possibleMoves(sourcePosition);
			Ui.clearScreen();
			
			Ui.printBoard(chessmatch.getPiece(), possibleMoves);
			System.out.println("digite a posição de destino da peça!!");
			ChessPosition target = Ui.readChessPosition(sc);
			ChessPiece chesspiece = chessmatch.performChessMove(sourcePosition, target);
			System.out.println("fodase");
			if(chesspiece != null) {
				capturedPieces.add(chesspiece);
			}
			//testa se alguma peça foi promovida e então o usuario pode escolher
			if(chessmatch.getPromotedPiece() != null) {
				System.out.println("Digite a letra de peça que deseja promover\n[B] bispo\n[N]cavaleiro\n[R]torre\n[Q]rainha");
				String type = sc.nextLine().toUpperCase();
				while(!type.equals("Q") && !type.equals("R") && !type.equals("K") && !type.equals("N")) {
					System.out.println(" invalid string!! Digite a letra de peça que deseja promover\n[B] bispo\n[N]cavaleiro\n[R]torre\n[Q]rainha");
					type = sc.nextLine();
				}
				chessmatch.replacePromotedPiece(type);
				
			}
			Ui.printBoard(chessmatch.getPiece());
		}
		catch(ChessException e) {
			System.out.println( e.getMessage());
			sc.nextLine();
		}
		catch(InputMismatchException e) {
			System.out.println( e.getMessage());
			sc.nextLine();
		}
		catch(BoardException e) {
			System.out.println(e.getMessage());
			sc.nextLine();
		}
 		}
		Ui.clearScreen();
		Ui.printMatch(chessmatch, capturedPieces);
		
	}

}
