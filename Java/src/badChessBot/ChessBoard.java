package badChessBot;

import badChessBot.Piece.PieceType;
import badChessBot.Piece.PieceColor;

public class ChessBoard {
	
	private int boardX = 8;
	private int boardY = 8;
	
	private Piece[][] board = new Piece [8][8];
	
	public ChessBoard() {
		// Creating Chess Starting Position
		
		for (int x = 0; x < boardX; x++) {
			for (int y = 0; y < boardY; y++) {
				board[x][y] = new Piece(PieceType.empty);
			}
		}
		
		for (int x = 0; x < boardX; x++) {
			board[x][1] = new Piece(PieceType.pawn, PieceColor.white);
		}
		
		board[0][0] = new Piece(PieceType.rook, PieceColor.white);
		board[1][0] = new Piece(PieceType.knight, PieceColor.white);
		board[2][0] = new Piece(PieceType.bishop, PieceColor.white);
		board[3][0] = new Piece(PieceType.queen, PieceColor.white);
		board[4][0] = new Piece(PieceType.king, PieceColor.white);
		board[5][0] = new Piece(PieceType.bishop, PieceColor.white);
		board[6][0] = new Piece(PieceType.knight, PieceColor.white);
		board[7][0] = new Piece(PieceType.rook, PieceColor.white);
		
		for (int x = 0; x < boardX; x++) {
			board[x][6] = new Piece(PieceType.pawn, PieceColor.black);
		}
		
		board[0][7] = new Piece(PieceType.rook, PieceColor.black);
		board[1][7] = new Piece(PieceType.knight, PieceColor.black);
		board[2][7] = new Piece(PieceType.bishop, PieceColor.black);
		board[3][7] = new Piece(PieceType.queen, PieceColor.black);
		board[4][7] = new Piece(PieceType.king, PieceColor.black);
		board[5][7] = new Piece(PieceType.bishop, PieceColor.black);
		board[6][7] = new Piece(PieceType.knight, PieceColor.black);
		board[7][7] = new Piece(PieceType.rook, PieceColor.black);
		
	}
	
	public String toString() {
		String out = ""; 
		PieceColor colorIndicator = PieceColor.white;
		
		for (int y = boardY - 1; y >= 0; y--) {
			for (int x = 0; x < boardX; x++) {
				if (colorIndicator == PieceColor.white) {
					out += "▯";
					colorIndicator = PieceColor.black;
				} else {
					out += "▮";
					colorIndicator = PieceColor.white;
				}
				
				out += board[x][y];
				
			}
			
			out += "\n";
		}

		
		return out;
	}

}
