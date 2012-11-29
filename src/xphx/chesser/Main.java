package xphx.chesser;

public class Main {
	
	static String[] board;
	
	public static void main(String args[]) {
		System.out.println("Chesser by XphnX");
		System.out.println("Initializing");
		ArrayMake arrays = new ArrayMake();
		board = arrays.board;
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
		String tsmove = "glitch";
		//Move translated looks something like this:
		//original location:new location
		//25:17 would be whatever is on 25 moving to 17
		if(move.length() == 2){
			//They're moving the pawn in that row
			if(move.startsWith("a")){
				//The thing that makes this confusing
				//Is that sometimes (when a pawn captures a piece)
				//Then there are two pawns in a lane
				//So let's make sure that there aren't two pawns in this lane
				int i = 0;
				int j = 0;
				int k = 0;
				boolean pawnfound = false;
				//Since Translate() only translate's whites moves,
				//we're looking for "white-pawn".
				do{
					if(board[i] == "white-pawn"){
						j++;
					}
					i = i + 8;
				}while(i <= 64);
				if(j == 0){
					//Wait, there's no pawn in this lane, moron.
					IllegalMove("No pawn in this lane");
				}else if(j == 1){
					i = 0;
					//Ok only one pawn which makes life easy.
					for(pawnfound = false; pawnfound != true;){
						if(board[i] == "white-pawn"){
							pawnfound = true;
						}else{
							i = i + 8;
						}
					}
					//Now that we've located the pawn (i), move it forward one ( - 8)
					//Check that you aren't moving the piece off the board
					if((i - 8) < 0){
						IllegalMove("You can't move the pawn off the board");
					}
					//Check that the pawn isn't moving more than a space
					//First find the row that the piece is moving to
					switch(i){
					case(40):
						k = 3;
						break;
					case(32):
						k = 4;
						break;
					case(24):
						k = 5;
						break;
					case(16):
						k = 6;
						break;
					case(8):
						k = 7;
						break;
					case(0):
						k = 8;
						break;
					default:
						IllegalMove("Exception 1; Not your fault, refer to the bug guide");
					}
					if(move.endsWith(Integer.toString(k - 1)) != true){
						IllegalMove("You're moving the pawn way too far, dude.");
					}
					tsmove = Integer.toString(i);
					tsmove += ":";
					tsmove += Integer.toString(i - 8);
					//ok we're done here move out
				}else{
					//Multiple pawns damnit.
				}
			}else if(move.startsWith("b")){
				
			}else if(move.startsWith("c")){
				
			}else if(move.startsWith("d")){
				
			}else if(move.startsWith("e")){
				
			}else if(move.startsWith("f")){
				
			}else if(move.startsWith("g")){
				
			}else if(move.startsWith("h")){
				
			}
		}
		return tsmove;
	}

	private static void IllegalMove(String why) {
		System.out.println("That move is illegal!");
		System.out.println(why);
	}

	private static String PromptForMove() {
		System.out.println("Please enter your move. Use algebraic chess move conventions.");
		return null;
	}

}
