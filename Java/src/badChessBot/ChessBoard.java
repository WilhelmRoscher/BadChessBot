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
		
		//Tests
		//board[4][2] = new Piece(PieceType.bishop, Color.black);
		//board[4][4] = new Piece(PieceType.king, Color.white);
		
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
	
	private boolean isWithinBoard(int x, int y) {
		if ((x >= 0) && (x < boardX) && (y >= 0) && (y < boardY)) {
			return true;
		}
		
		return false;
	}
	
	private boolean isEmptyPosition(int x, int y) {
		if (board[x][y].getPieceType() == PieceType.empty) {
			return true;
		}
		
		return false;
	}
	
	private boolean isEnemyPosition(int x, int y) {
		if ((board[x][y].getPieceType() != PieceType.empty) &&
				(board[x][y].getPieceColor() != playerToMove)) {
			return true;
		}
		
		return false;
	}
	
	public int generateChildren() {
		children.clear();
		
		Piece piece;
		ChessBoard child;
		int pawnDirection;
		int tempX;
		int tempY;
		
		int[] bishopDirections = {-1, 1};
		int[][] knightDirections = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, 2}, {-2, -1}, {-2, 1}, {-1, -2}};
		int[][] rookDirections = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		int[] kingDirections = {-1, 0, 1};
		
		for (int x = 0; x < boardX; x++) {
			for (int y = 0; y < boardY; y++) {
				piece = board[x][y];
				
				if (piece.getPieceColor() == playerToMove) {
					
					if (piece.getPieceType() == PieceType.pawn) {
						
						if (piece.getPieceColor() == Color.white) {
							pawnDirection = 1;
						} else {
							pawnDirection = -1;
						}
						
						// normal 1x forward
						if (isWithinBoard(x, y+pawnDirection*1) && isEmptyPosition(x, y+pawnDirection*1)) {
							child = this.makeMove(x, y, x, y+pawnDirection*1);
							
							children.add(child);
							//System.out.println(child);
						}
						
						// 2x forward
						if (isWithinBoard(x, y+pawnDirection*2) && 
								piece.isUnmoved() &&
								isEmptyPosition(x, y+pawnDirection*1) &&
								isEmptyPosition(x, y+pawnDirection*2)) {
							child = this.makeMove(x, y, x, y+pawnDirection*2);
							
							children.add(child);
							//System.out.println(child);
						}
						
						// take front left
						if (isWithinBoard(x-1, y+pawnDirection*1) && isEnemyPosition(x-1, y+pawnDirection*1)) {
							
							child = this.makeMove(x, y, x-1, y+pawnDirection*1);
							
							children.add(child);
							//System.out.println(child);
						}
						
						// take front right
						if (isWithinBoard(x+1, y+pawnDirection*1) && isEnemyPosition(x+1, y+pawnDirection*1)) {
							
							child = this.makeMove(x, y, x+1, y+pawnDirection*1);
							
							children.add(child);
							//System.out.println(child);
						}
					}
					
					if ((piece.getPieceType() == PieceType.bishop) || (piece.getPieceType() == PieceType.queen)) {
						
						// going in all 4 directions
						for (int bishopDirectionX : bishopDirections) {
							for (int bishopDirectionY : bishopDirections) {
								tempX = x+bishopDirectionX;
								tempY = y+bishopDirectionY;
								
								// normal movement
								while (isWithinBoard(tempX, tempY) && isEmptyPosition(tempX, tempY)) {
									child = this.makeMove(x, y, tempX, tempY);
									
									children.add(child);
									//System.out.println(child);
									
									tempX+=bishopDirectionX;
									tempY+=bishopDirectionY;
								}
								
								// capturing
								if (isWithinBoard(tempX, tempY) && isEnemyPosition(tempX, tempY)) {
									child = this.makeMove(x, y, tempX, tempY);
									
									children.add(child);
									//System.out.println(child);
								}
							}
						}
					}
					
					if (piece.getPieceType() == PieceType.knight) {
						// going in all 8 directions
						for (int[] knightDirection : knightDirections) {
							tempX = x+knightDirection[0];
							tempY = y+knightDirection[1];
							
							if (isWithinBoard(tempX, tempY) && (isEnemyPosition(tempX, tempY) || isEmptyPosition(tempX, tempY))) {
								child = this.makeMove(x, y, tempX, tempY);
								
								children.add(child);
								//System.out.println(child);
							}
						}
					}
					
					if ((piece.getPieceType() == PieceType.rook) || (piece.getPieceType() == PieceType.queen)) {
						
						// going in all 4 directions
						for (int[] rookDirection : rookDirections) {
							tempX = x+rookDirection[0];
							tempY = y+rookDirection[1];
							
							// normal movement
							while (isWithinBoard(tempX, tempY) && isEmptyPosition(tempX, tempY)) {
								child = this.makeMove(x, y, tempX, tempY);
								
								children.add(child);
								//System.out.println(child);
								
								tempX+=rookDirection[0];
								tempY+=rookDirection[1];
							}
							
							// capturing
							if (isWithinBoard(tempX, tempY) && isEnemyPosition(tempX, tempY)) {
								child = this.makeMove(x, y, tempX, tempY);
								
								children.add(child);
								//System.out.println(child);
							}
						}	
					}
					
					if (piece.getPieceType() == PieceType.king) {
						// going in all directions
						for (int kingDirectionX : kingDirections) {
							for (int kingDirectionY : kingDirections) {
								tempX = x+kingDirectionX;
								tempY = y+kingDirectionY;
								
								if (isWithinBoard(tempX, tempY) && (isEnemyPosition(tempX, tempY) || isEmptyPosition(tempX, tempY))) {
									child = this.makeMove(x, y, tempX, tempY);
									
									children.add(child);
									//System.out.println(child);
								}
							}
						}
					}
				}
			}
		}
		
		return children.size();
	}
	
	public int generateChildrenRecursive(int generations) {
		if (generations == 1) {
			return generateChildren();
		}
		
		int size = 0;
		
		generateChildren();
		
		for (ChessBoard child: children) {
			size += child.generateChildrenRecursive(generations-1);
		}
		
		return size;
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
