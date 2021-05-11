package badChessBot;

import badChessBot.Piece.Color;

public class BadChessBot {
	
	public static void main (String[] Args) {
		
		ChessBoard board = new ChessBoard();
		
		BotPlayer botWhite = new BotPlayer(Color.white);
		BotPlayer botBlack = new BotPlayer(Color.black);
		
		while (true) {
			System.out.println(board);
			
			board = botWhite.getNextMove(board);
			
			System.out.println(board);
			
			board = botBlack.getNextMove(board);
		}
	}

}
