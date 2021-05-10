package badChessBot;

import java.util.ArrayList;

import badChessBot.Piece.Color;

public class BotPlayer {
	private Color botColor;
	private int foresight = 3;
	
	private ArrayList<ChessBoard> possibleMoves = new ArrayList<ChessBoard>();
	
	public BotPlayer(Color botColor) {
		this.botColor = botColor;
	}
	
	public ChessBoard getNextMove(ChessBoard chessBoard) {
		setPossibleMoves(chessBoard);
		
		// White wants Max Value; Black wants Min Value
		int tmpValue;
		int value = possibleMoves.get(0).getValueMiniMax();
		int bestMoveIndex = 0;
		
		for (int i = 0; i < possibleMoves.size(); i++) {
			tmpValue = possibleMoves.get(i).getValueMiniMax();
			if (botColor == Color.white) {
				if (tmpValue >= value) {
					value = tmpValue;
					bestMoveIndex = i;
				}
			} else {
				if (tmpValue < value) {
					value = tmpValue;
					bestMoveIndex = i;
				}
			}
		}
		
		return possibleMoves.get(bestMoveIndex);
	}
	
	private void setPossibleMoves(ChessBoard chessBoard) {
		chessBoard.generateChildrenRecursive(foresight);
		possibleMoves = chessBoard.getChildren();
	}

}
