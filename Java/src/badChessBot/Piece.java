package badChessBot;

import java.util.HashMap;
import java.util.Map;

public class Piece {
	
	public static enum PieceType {
		empty,
		pawn,
		knight,
		bishop,
		rook,
		queen,
		king
	}
	
	public static enum Color {
		empty,
		white,
		black
	}
	
	private Map<PieceType, String> whitePieceStrings = new HashMap<PieceType, String>(){/**
		 * 
		 */
		private static final long serialVersionUID = 6376418731573248616L;

	{
		put(PieceType.empty, " ");
		put(PieceType.pawn, "♙");
		put(PieceType.knight, "♘");
		put(PieceType.bishop, "♗");
		put(PieceType.rook, "♖");
		put(PieceType.queen, "♕");
		put(PieceType.king, "♔");
	}};
	
	private Map<PieceType, String> blackPieceStrings = new HashMap<PieceType, String>(){/**
		 * 
		 */
		private static final long serialVersionUID = 2893093037065780424L;

	{
		put(PieceType.empty, " ");
		put(PieceType.pawn, "♟︎");
		put(PieceType.knight, "♞");
		put(PieceType.bishop, "♝");
		put(PieceType.rook, "♜");
		put(PieceType.queen, "♛");
		put(PieceType.king, "♚");
	}};
	
	private Map<PieceType, Integer> pieceValues = new HashMap<PieceType, Integer>(){/**
		 * 
		 */
		private static final long serialVersionUID = -9109287092948549660L;

	{
		put(PieceType.empty, 0);
		put(PieceType.pawn, 1);
		put(PieceType.knight, 3);
		put(PieceType.bishop, 3);
		put(PieceType.rook, 5);
		put(PieceType.queen, 12);
		put(PieceType.king, 100);
	}};
	
	
	private PieceType pieceType;
	private Color pieceColor;
	private boolean unmoved;
	
	public Piece(PieceType pieceType) {
		this.pieceType = pieceType;
		this.pieceColor = Color.empty;
		this.setUnmoved(true);
		
	}
	
	public Piece(PieceType pieceType, Color pieceColor) {
		this.pieceType = pieceType;
		this.pieceColor = pieceColor;
		this.setUnmoved(true);
	}
	
	public Piece(PieceType pieceType, Color pieceColor, boolean unmoved) {
		this.pieceType = pieceType;
		this.pieceColor = pieceColor;
		this.setUnmoved(unmoved);
	}
	
	public PieceType getPieceType() {
		return pieceType;
	}

	public Color getPieceColor() {
		return pieceColor;
	}
	
	public boolean isUnmoved() {
		return unmoved;
	}

	public void setUnmoved(boolean unmoved) {
		this.unmoved = unmoved;
	}
	
	public int getValue() {
		if (pieceColor == Color.white) {
			return pieceValues.get(pieceType);
		} else {
			return -1 * pieceValues.get(pieceType);
		}
	}
	
	public Piece getCopy() {
		return new Piece(pieceType, pieceColor, unmoved);
	}

	public String toString() {
		if (pieceColor == Color.white) {
			return whitePieceStrings.get(pieceType);
		} else {
			return blackPieceStrings.get(pieceType);
		}
	}

}
