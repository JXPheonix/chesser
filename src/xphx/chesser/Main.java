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
		 * 00 01 02 03 04 05 06 07
		 * 08 09 10 11 12 13 14 15
		 * 16 17 18 19 20 21 22 23
		 * 24 25 26 27 28 29 30 31
		 * 32 33 34 35 36 37 38 39
		 * 40 41 42 43 44 45 46 47
		 * 48 49 50 51 52 53 54 55
		 * 56 57 58 59 60 61 62 63
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
		boolean illegal = false;
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
					//Check we aren't moving onto another piece
					if(board[i - 8] != "empty"){
						IllegalMove("You can't move the pawn onto another piece!");
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
						//since this seems to be broken i'm not setting as illegal
					}
					if(board[i - 8] != "empty"){
						IllegalMove("You can't move the pawn onto another piece!");
					}
					tsmove = Integer.toString(i);
					tsmove += ":";
					tsmove += Integer.toString(i - 8);
					//ok we're done here move out
				}else{
					//Multiple pawns damnit.
				}
			}else if(move.startsWith("c")){
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
					if(board[i - 8] != "empty"){
						IllegalMove("You can't move the pawn onto another piece!");
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
					if(board[i - 8] != "empty"){
						IllegalMove("You can't move the pawn onto another piece!");
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
					if(board[i - 8] != "empty"){
						IllegalMove("You can't move the pawn onto another piece!");
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
					if(board[i - 8] != "empty"){
						IllegalMove("You can't move the pawn onto another piece!");
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
					if(board[i - 8] != "empty"){
						IllegalMove("You can't move the pawn onto another piece!");
					}
					tsmove = Integer.toString(i);
					tsmove += ":";
					tsmove += Integer.toString(i - 8);
				}else{
					//Multiple pawns damnit.
				}
			}
		}else if(move.length() == 3){
			//A normal move.
			//First check what piece is being moved (the letter at the beginning)
			int moveto;
			int movefrom = 0;
			int a = 0, b = 0, c = 0, d = 0, e;
			String movetoalg;
			//Moving the bishop
			//Translate the last two squares into a number
			movetoalg = move.substring(1);
			if(movetoalg.startsWith("a")){
				a = 0;
			}else if(movetoalg.startsWith("b")){
				a = 1;
			}else if(movetoalg.startsWith("c")){
				a = 2;
			}else if(movetoalg.startsWith("d")){
				a = 3;
			}else if(movetoalg.startsWith("e")){
				a = 4;
			}else if(movetoalg.startsWith("f")){
				a = 5;
			}else if(movetoalg.startsWith("g")){
				a = 6;
			}else if(movetoalg.startsWith("h")){
				a = 7;
			}
			if(movetoalg.endsWith("1")){
				b = 0;
			}else if(movetoalg.endsWith("2")){
				b = 1;
			}else if(movetoalg.endsWith("3")){
				b = 2;
			}else if(movetoalg.endsWith("4")){
				b = 3;
			}else if(movetoalg.endsWith("5")){
				b = 4;
			}else if(movetoalg.endsWith("6")){
				b = 5;
			}else if(movetoalg.endsWith("7")){
				b = 6;
			}else if(movetoalg.endsWith("8")){
				b = 7;
			}
			moveto = (8*b)+a;
			if(move.startsWith("B")){		
				//now that we have that, we need to locate where bishops could move here
				//Bishops can move in increments of 9 or 7. We need to locate
				//bishops on squares moveto+(9*-5 to 5) and moveto+(7*-5 to 5).
				//Not that hard.
				int movepossible;
				for(int i = -10; i < 11; i++){
					movepossible = moveto+(9*i);
					if(movepossible >= 0){
						if(movepossible < 64){
							if(board[movepossible] == "white-bishop"){
								c++;
								d = movepossible;
							}
						}
					}
					movepossible = moveto+(7*i);
					if(movepossible >= 0){
						if(movepossible < 64){
							if(board[movepossible] == "white-bishop"){
								c++;
								d = movepossible;
							}
						}
					}
				}
				//Now, we hope that there is only one possible bishop.
				if(c == 0){
					IllegalMove("No bishops to move there");
				}else if(c == 1){
					//Oh thank god
					//Make sure no pieces are in the way
					e = moveto - d;
					if((e % 9) == 0){
						if(moveto > d){
							for(int i = moveto; i > d; i = i - 9){
								if(board[i] != "empty"){
									IllegalMove("The path between the bishop and the space is not clear");
								}
							}
						}else{
							for(int i = d; i > moveto; i = i - 9){
								if(board[i] != "empty"){
									IllegalMove("The path between the bishop and the location is not clear");
								}
							}
						}
					}
					//The bishop's at d, so...
					tsmove = Integer.toString(d);
					tsmove += ":";
					tsmove += Integer.toString(moveto);
				}else{
					//Great.
				}
			}else if(move.startsWith("N")){
				//Knight moves, which are somewhat complicated.
				//Say the place you move to is x.
				//The places you could have moved from are:
				//x +- 17, 15, 10, 6.
				//8 different locations. You have to test them individually.
				if(board[moveto + 17] == "white-knight"){
					movefrom = moveto + 17;
				}else if(board[moveto + 15] == "white-knight"){
					movefrom = moveto + 15;
				}else if(board[moveto + 10] == "white-knight"){
					movefrom = moveto + 10;
				}else if(board[moveto + 6] == "white-knight"){
					movefrom = moveto + 6;
				}else if(board[moveto - 6] == "white-knight"){
					movefrom = moveto - 6;
				}else if(board[moveto - 10] == "white-knight"){
					movefrom = moveto - 10;
				}else if(board[moveto - 15] == "white-knight"){
					movefrom = moveto - 15;
				}else if(board[moveto - 17] == "white-knight"){
					movefrom = moveto - 15;
				}else{
					IllegalMove("No knights to move there");
				}
				tsmove = Integer.toString(movefrom);
				tsmove += ":";
				tsmove += Integer.toString(moveto);
				//That wasn't too hard.
			}else if(move.startsWith("R")){
				//Rooks are probably one of the harder pieces.
				//They move in increments of 1 or 8, like the bishops 7 or 9.
			}else if(move.startsWith("Q")){
				//
			}else if(move.startsWith("K")){
				//
			}else{
				IllegalMove("Illegal Algebraic Notation");
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
