package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chessEntities.ChessMatch;
import chessEntities.ChessPiece;
import chessEntities.ChessPosition;
import chessEntities.Color;

public class Ui {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	
	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String s = sc.nextLine();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new ChessPosition(column, row);
		}
		catch (InputMismatchException e) {
			throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8.");
		}
	}
	
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void clearScreen() {  
		System.out.print("\033[H\033[2J"); System.out.flush();  
		}
	public static void printBoard(ChessPiece[][] pieces) {
		// metodo que cria um tabuleiro de xadrex 
		for(int i = 0; i<pieces.length; i++) {
			System.out.print((8-i) + " ");
			for(int j = 0; j<pieces.length; j++) {
				printPiece(pieces[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
		
		
	}
	//chama o printboard e adciona o turn na tela alem das peças capturadas
	public static void printMatch(ChessMatch chessmatch, List<ChessPiece> capturedPieces) {
		printBoard(chessmatch.getPiece());
		System.out.println();
		printCapturedPieces(capturedPieces);
		System.out.println("turn " + chessmatch.getTurn());
		if(!chessmatch.getCheckMate()) {
			System.out.println("waiting for the current player " + chessmatch.currentPlayer());
			if(chessmatch.getCheck()) {
				System.out.println("CHECK!! " + chessmatch.currentPlayer());
			}
			}
		else {
			System.out.println("CHECK MATE");
			System.out.println("WINNER " + chessmatch.currentPlayer());
		}
	}
	//metodo sobrecarregado printboard que imprime os possiveis mov de cada peça

	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		// metodo que cria um tabuleiro de xadrex 
		for(int i = 0; i<pieces.length; i++) {
			System.out.print((8-i) + " ");
			for(int j = 0; j<pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
		
		
	}
	private static void printPiece(ChessPiece piece, boolean background) {
		if(background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (piece == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            } 
        }
        System.out.print(" ");
	}
	private static void printCapturedPieces(List<ChessPiece> capturedPieces) {
		List<ChessPiece> white = (List<ChessPiece>) capturedPieces.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		List<ChessPiece> black = (List<ChessPiece>) capturedPieces.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		
		System.out.println("captured white pieces: ");
		System.out.println("White");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(white.toArray()));
		System.out.print(ANSI_RESET);
		System.out.println("captured black pieces: ");
		System.out.println("Black");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(black.toArray()));
		System.out.print(ANSI_RESET);
	} 
	


}
