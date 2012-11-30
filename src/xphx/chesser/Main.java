package xphx.chesser;

import java.util.Scanner;

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
		System.out.println("Please enter full path to lessons.txt (sorry I can't auto detect this)");
		Scanner scan = new Scanner(System.in);
		Think.lessonslocation = scan.next();
		scan.close();
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
				compwin = Think.winner;
			}else{
				String cmove = thinker.think(board);
				board = AlterBoard(cmove, board);
			}
		}
	}

	private static String[] AlterBoard(String move, String[] board) {
		int[] moves = new int[2];
		String[] movear = move.split(":");
		for(int i = 0; i <= 1; i++){
			moves[i] = Integer.parseInt(movear[i]);
		}
		String movedpiece = board[moves[0]];
		System.out.println("Moving the " + movedpiece);
		board[moves[0]] = "empty";
		board[moves[1]] = movedpiece;
		System.out.println("There was a " + movedpiece + " at " + moves[0] + " which is now at " + moves[1] + ".");
		return board;
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
				}while(i <= 63);
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
					//Multiple pawns damnit. TODO
				}
			}else if(move.startsWith("b")){
				//Basically modified copy/paste.
				int i = 1; //Up'd one
				int j = 0;
				int k = 0;
				int l = 1;
				boolean pawnfound = false;
				do{
					if(board[i] == "white-pawn"){
						j++;
					}
					i = i + 8;
				}while(i <= 64);
				if(j == 0){
					IllegalMove("No pawn in this lane");
				}else if(j == 1){
					i = 0;
					for(pawnfound = false; pawnfound != true;){
						if(board[i] == "white-pawn"){
							pawnfound = true;
						}else{
							i = i + 8;
						}
					}
					if((i - 8) < 0){
						IllegalMove("You can't move the pawn off the board");
					}
					//Check that the pawn isn't moving more than a space
					//First find the row that the piece is moving to
					switch(i){
					case((8*5)+1):
						k = 3;
						break;
					case((8*4)+1):
						k = 4;
						break;
					case((8*3)+1):
						k = 5;
						break;
					case((8*2)+1):
						k = 6;
						break;
					case((8*1)+1):
						k = 7;
						break;
					case((8*0)+1):
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
			}else if(move.startsWith("c")){
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
					case((8*5)+2):
						k = 3;
						break;
					case((8*4)+2):
						k = 4;
						break;
					case((8*3)+2):
						k = 5;
						break;
					case((8*2)+2):
						k = 6;
						break;
					case((8*1)+2):
						k = 7;
						break;
					case((8*0)+2):
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
			}else if(move.startsWith("d")){
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
					case((8*5)+3):
						k = 3;
						break;
					case((8*4)+3):
						k = 4;
						break;
					case((8*3)+3):
						k = 5;
						break;
					case((8*2)+3):
						k = 6;
						break;
					case((8*1)+3):
						k = 7;
						break;
					case((8*0)+3):
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
			}else if(move.startsWith("e")){
				int i = 0;
				int j = 0;
				int k = 0;
				boolean pawnfound = false;
				do{
					if(board[i] == "white-pawn"){
						j++;
					}
					i = i + 8;
				}while(i <= 64);
				if(j == 0){
					IllegalMove("No pawn in this lane");
				}else if(j == 1){
					i = 0;
					for(pawnfound = false; pawnfound != true;){
						if(board[i] == "white-pawn"){
							pawnfound = true;
						}else{
							i = i + 8;
						}
					}
					if((i - 8) < 0){
						IllegalMove("You can't move the pawn off the board");
					}
					switch(i){
					case((8*5)+4):
						k = 3;
						break;
					case((8*4)+4):
						k = 4;
						break;
					case((8*3)+4):
						k = 5;
						break;
					case((8*2)+4):
						k = 6;
						break;
					case((8*1)+4):
						k = 7;
						break;
					case((8*0)+4):
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
			}else if(move.startsWith("f")){
				int i = 0;
				int j = 0;
				int k = 0;
				boolean pawnfound = false;
				do{
					if(board[i] == "white-pawn"){
						j++;
					}
					i = i + 8;
				}while(i <= 64);
				if(j == 0){
					IllegalMove("No pawn in this lane");
				}else if(j == 1){
					i = 0;
					for(pawnfound = false; pawnfound != true;){
						if(board[i] == "white-pawn"){
							pawnfound = true;
						}else{
							i = i + 8;
						}
					}
					if((i - 8) < 0){
						IllegalMove("You can't move the pawn off the board");
					}
					switch(i){
					case((8*5)+5):
						k = 3;
						break;
					case((8*4)+5):
						k = 4;
						break;
					case((8*3)+5):
						k = 5;
						break;
					case((8*2)+5):
						k = 6;
						break;
					case((8*1)+5):
						k = 7;
						break;
					case((8*0)+5):
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
				}else{
					//Multiple pawns damnit.
				}
			}else if(move.startsWith("g")){
				int i = 0;
				int j = 0;
				int k = 0;
				boolean pawnfound = false;
				do{
					if(board[i] == "white-pawn"){
						j++;
					}
					i = i + 8;
				}while(i <= 64);
				if(j == 0){
					IllegalMove("No pawn in this lane");
				}else if(j == 1){
					i = 0;
					for(pawnfound = false; pawnfound != true;){
						if(board[i] == "white-pawn"){
							pawnfound = true;
						}else{
							i = i + 8;
						}
					}
					if((i - 8) < 0){
						IllegalMove("You can't move the pawn off the board");
					}
					switch(i){
					case((8*5)+6):
						k = 3;
						break;
					case((8*4)+6):
						k = 4;
						break;
					case((8*3)+6):
						k = 5;
						break;
					case((8*2)+6):
						k = 6;
						break;
					case((8*1)+6):
						k = 7;
						break;
					case((8*0)+6):
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
				}else{
					//Multiple pawns damnit.
				}
			}else if(move.startsWith("h")){
				int i = 0;
				int j = 0;
				int k = 0;
				boolean pawnfound = false;
				do{
					if(board[i] == "white-pawn"){
						j++;
					}
					i = i + 8;
				}while(i <= 64);
				if(j == 0){
					IllegalMove("No pawn in this lane");
				}else if(j == 1){
					i = 0;
					for(pawnfound = false; pawnfound != true;){
						if(board[i] == "white-pawn"){
							pawnfound = true;
						}else{
							i = i + 8;
						}
					}
					if((i - 8) < 0){
						IllegalMove("You can't move the pawn off the board");
					}
					switch(i){
					case((8*5)+7):
						k = 3;
						break;
					case((8*4)+7):
						k = 4;
						break;
					case((8*3)+7):
						k = 5;
						break;
					case((8*2)+7):
						k = 6;
						break;
					case((8*1)+7):
						k = 7;
						break;
					case((8*0)+7):
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
				}else{
					//Multiple pawns damnit.
				}
			}
		}
		System.out.println(move);
		System.out.println(tsmove);
		return tsmove;
	}

	private static void IllegalMove(String why) {
		System.out.println("That move is illegal!");
		System.out.println(why);
	}

	private static String PromptForMove() {
		System.out.println("Please enter your move. Use algebraic chess move conventions.");
		Scanner movescan = new Scanner(System.in);
		return movescan.next();
	}

}
