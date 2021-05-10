package badChessBot;

import badChessBot.Piece.PieceType;

import java.util.ArrayList;

import badChessBot.Piece.Color;

public class ChessBoard {
	
	private int boardX = 8;
	private int boardY = 8;
	
	private Piece[][] board = new Piece [boardX][boardY];
	
	private Color playerToMove;
	
	public ArrayList<ChessBoard> children = new ArrayList<ChessBoard>();;
	
	public ChessBoard() {
		// Creating Chess Starting Position
		
		playerToMove = Color.white;
		
		for (int x = 0; x < boardX; x++) {
			for (int y = 0; y < boardY; y++) {
				board[x][y] = new Piece(PieceType.empty);
			}
		}
		
		for (int x = 0; x < boardX; x++) {
			board[x][1] = new Piece(PieceType.pawn, Color.white);
		}
		
		board[0][0] = new Piece(PieceType.rook, Color.white);
		board[1][0] = new Piece(PieceType.knight, Color.white);
		board[2][0] = new Piece(PieceType.bishop, Color.white);
		board[3][0] = new Piece(PieceType.queen, Color.white);
		board[4][0] = new Piece(PieceType.king, Color.white);
		board[5][0] = new Piece(PieceType.bishop, Color.white);
		board[6][0] = new Piece(PieceType.knight, Color.white);
		board[7][0] = new Piece(PieceType.rook, Color.white);
		
		for (int x = 0; x < boardX; x++) {
			board[x][6] = new Piece(PieceType.pawn, Color.black);
		}
		
		board[0][7] = new Piece(PieceType.rook, Color.black);
		board[1][7] = new Piece(PieceType.knight, Color.black);
		board[2][7] = new Piece(PieceType.bishop, Color.black);
		board[3][7] = new Piece(PieceType.queen, Color.black);
		board[4][7] = new Piece(PieceType.king, Color.black);
		board[5][7] = new Piece(PieceType.bishop, Color.black);
		board[6][7] = new Piece(PieceType.knight, Color.black);
		board[7][7] = new Piece(PieceType.rook, Color.black);
		
	}
	
	public ChessBoard(Piece[][] board, Color playerToMove) {
		this.board = board;
		this.playerToMove = playerToMove;
	}
	
	private Piece[][] getBoardCopy() {
		Piece[][] newBoard = new Piece [boardX][boardY];
		
		for (int x = 0; x < boardX; x++) {
			for (int y = 0; y < boardY; y++) {
				newBoard[x][y] = board[x][y].getCopy();
			}
		}
		
		return newBoard;
	}
	
	private ChessBoard makeMove(int x1, int y1, int x2, int y2) {
		Piece[][] newBoard = getBoardCopy(); 
		Color newPlayerToMove;
		
		if (playerToMove == Color.white) {
			newPlayerToMove = Color.black;
		} else {
			newPlayerToMove = Color.white;
		}
		
		newBoard[x2][y2] = newBoard[x1][y1];
		newBoard[x1][y1] = new Piece(PieceType.empty);
		newBoard[x2][y2].setUnmoved(false);
		
		ChessBoard newChessBoard = new ChessBoard(newBoard, newPlayerToMove);
		
		return newChessBoard;
	}
	
	public void generateChildren() {
		children.clear();
		
		Piece piece;
		ChessBoard child;
		
		for (int x = 0; x < boardX; x++) {
			for (int y = 0; y < boardY; y++) {
				piece = board[x][y];
				
				if (piece.getPieceColor() == playerToMove) {
					
					if (piece.getPieceType() == PieceType.pawn) {
						
						if (piece.getPieceColor() == Color.white) {
							// normal 1x forward
							if (board[x][y+1].getPieceType() == PieceType.empty) {
								child = this.makeMove(x, y, x, y+1);
								
								children.add(child);
								System.out.println(child);
							}
							
							// 2x forward
							if ((piece.isUnmoved()) &&
									(board[x][y+1].getPieceType() == PieceType.empty) &&
									(board[x][y+2].getPieceType() == PieceType.empty)) {
								child = this.makeMove(x, y, x, y+2);
								
								children.add(child);
								
								System.out.println(child);
							}
							
							// take left
							
							// take right
						}
					}
				}
			}
		}
	}
	
	public Color getPlayerToMove() {
		return playerToMove;
	}
	
	public int getValue() {
		int value = 0;
		
		for (int x = 0; x < boardX; x++) {
			for (int y = 0; y < boardY; y++) {
				value += board[x][y].getValue();
			}
		}
		
		return value;
	}

	public String toString() {
		String out = ""; 
		Color colorIndicator = Color.white;
		
		for (int y = boardY - 1; y >= 0; y--) {
			for (int x = 0; x < boardX; x++) {
				if (colorIndicator == Color.white) {
					out += "▯";
					colorIndicator = Color.black;
				} else {
					out += "▮";
					colorIndicator = Color.white;
				}
				
				out += board[x][y];
			}
			out += "\n";
			
			if (colorIndicator == Color.white) {
				colorIndicator = Color.black;
			} else {
				colorIndicator = Color.white;
			}
		}
		
		return out;
	}

}
