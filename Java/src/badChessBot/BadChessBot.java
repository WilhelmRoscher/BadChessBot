package badChessBot;

public class BadChessBot {
	
	public static void main (String[] Args) {
		
		ChessBoard b = new ChessBoard();
		
		System.out.println(b);
		System.out.println(b.getValue());
		
		System.out.println(b.generateChildrenRecursive(3));
	}

}
