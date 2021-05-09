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
	
	public static enum PieceColor {
		empty,
		white,
		black
	}
	
	@SuppressWarnings("serial")
	private Map<PieceType, String> whitePieceStrings = new HashMap<PieceType, String>(){{
		put(PieceType.empty, " ");
		put(PieceType.pawn, "♙");
		put(PieceType.knight, "♘");
		put(PieceType.bishop, "♗");
		put(PieceType.rook, "♖");
		put(PieceType.queen, "♕");
		put(PieceType.king, "♔");
	}};
	
	@SuppressWarnings("serial")
	private Map<PieceType, String> blackPieceStrings = new HashMap<PieceType, String>(){{
		put(PieceType.empty, " ");
		put(PieceType.pawn, "♟︎");
		put(PieceType.knight, "♞");
		put(PieceType.bishop, "♝");
		put(PieceType.rook, "♜");
		put(PieceType.queen, "♛");
		put(PieceType.king, "♛");
	}};
	
	
	private PieceType pieceType;
	private PieceColor pieceColor;
	
	public Piece(PieceType pieceType) {
		this.pieceType = pieceType;
		this.pieceColor = PieceColor.empty;
		
	}
	
	public Piece(PieceType pieceType, PieceColor pieceColor) {
		this.pieceType = pieceType;
		this.pieceColor = pieceColor;
		
	}
	
	public PieceType getPeaceType() {
		return pieceType;
	}

	public PieceColor getPieceColor() {
		return pieceColor;
	}
	
	public String toString() {
		if (pieceColor == PieceColor.white) {
			return whitePieceStrings.get(pieceType);
		} else {
			return blackPieceStrings.get(pieceType);
		}
	}

}
