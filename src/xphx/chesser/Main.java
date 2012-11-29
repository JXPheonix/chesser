package xphx.chesser;

public class Main {
	
	public static void main(String args[]) {
		System.out.println("Chesser by XphnX");
		System.out.println("Initializing");
		ArrayMake arrays = new ArrayMake();
		String[] board = arrays.board;
		/* For reference:
		 * 01 02 03 04 05 06 07 08
		 * 09 10 11 12 13 14 15 16
		 * 17 18 19 20 21 22 23 24
		 * 25 26 27 28 29 30 31 32
		 * 33 34 35 36 37 38 39 40
		 * 41 42 43 44 45 46 47 48
		 * 49 50 51 52 53 54 55 56
		 * 57 58 59 60 61 62 63 64
		 * 
		 * Since I'm lazy, take 1 away from each number.
		 */
		System.out.println("Board built");
		//PromptForMove --> TranslateIn --> AlterBoard --> Think --> AlterBoard --> Output
		System.out.println("As of this version the player is always white.");
		System.out.println("The possiblity of being black will be added in a later version.");
		System.out.println("Hint: Since there's no GUI, you might want to have a chessboard in front of you.");
		Think thinker = new Think();
		boolean compwin;
		for(boolean endgame = false; endgame != true;) {
			String move = PromptForMove();
			String tsmove = Translate(move);
			board = AlterBoard(tsmove, board);
			if(thinker.endgame()){
				endgame = true;
				compwin = thinker.winner;
			}else{
				String cmove = thinker.think(board);
				board = AlterBoard(cmove, board);
			}
		}
	}

	private static String[] AlterBoard(String move, String[] board) {
		// TODO Auto-generated method stub
		return null;
	}

	private static String Translate(String move) {
		// TODO Auto-generated method stub
		return null;
	}

	private static String PromptForMove() {
		System.out.println("Please enter your move. Use algebraic chess move conventions.");
		return null;
	}

}
