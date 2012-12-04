package xphx.chesser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Board {
	
	//-6: Black pawn
	//-5: Black king
	//-4: Black queen
	//-3: Black bishop
	//-2: Black knight
	//-1: Black rook
	//0: Empty
	//1-5: Inverse of above
	
	static int[] board = new int[128];
	boolean whitecancastle = true;
	boolean blackcancastle = true;
	public boolean mate = false;
	
	public boolean testOnBoard(int loc){
		boolean banana;
		if((loc & 0x88) != 0){
			banana = false;
		}else{
			banana = true;
		}
		return banana;
	}

	public void inputNotation(String string) {
		//Nothing here yet
	}

	public void reset() {
		for(int i = 0; i < 128; i++){
			//Prepare for really fucking long switch statement!
			switch(i){
			case 0:
				board[i] = 1;
				break;
			case 1:
				board[i] = 2;
				break;
			case 2:
				board[i] = 3;
				break;
			case 3:
				board[i] = 4;
				break;
			case 4:
				board[i] = 5;
				break;
			case 5:
				board[i] = 3;
				break;
			case 6:
				board[i] = 2;
				break;
			case 7:
				board[i] = 1;
				break;
			case 16:
				board[i] = 6;
				break;
			case 17:
				board[i] = 6;
				break;
			case 18:
				board[i] = 6;
				break;
			case 19:
				board[i] = 6;
				break;
			case 20:
				board[i] = 6;
				break;
			case 21:
				board[i] = 6;
				break;
			case 22:
				board[i] = 6;
				break;
			case 23:
				board[i] = 6;
				break;
			case 96:
				board[i] = -6;
				break;
			case 97:
				board[i] = -6;
				break;
			case 98:
				board[i] = -6;
				break;
			case 99:
				board[i] = -6;
				break;
			case 100:
				board[i] = -6;
				break;
			case 101:
				board[i] = -6;
				break;
			case 102:
				board[i] = -6;
				break;
			case 103:
				board[i] = -6;
				break;
			case 112:
				board[i] = -1;
				break;
			case 113:
				board[i] = -2;
				break;
			case 114:
				board[i] = -3;
				break;
			case 115:
				board[i] = -4;
				break;
			case 116:
				board[i] = -5;
				break;
			case 117:
				board[i] = -3;
				break;
			case 118:
				board[i] = -2;
				break;
			case 119:
				board[i] = -1;
				break;
			default:
				board[i] = -0;
				break;
			}
		}
		
	}

	public static void update(Move move) {
		int piece = 0;
		piece = board[move.oldloc];
		board[move.oldloc] = 0;
		board[move.newloc] = piece;
	}

	public void log(String tolog) {
		//This logs the current situation to log.txt
		//Whenever a move is made by the computer, it
		//refers to log.txt to see if that situation has
		//happened before
		//First convert the array into a string
		String boardstring = "";
		for(int i = 0; i < 128; i++){
			boardstring += Integer.toString(board[i]);
			boardstring += ":";
		}
		if(tolog != null){
			boardstring = tolog;
		}
		FileOutputStream logout;
		try{ //yay for trying
			logout = new FileOutputStream("log.txt");
			new PrintStream(logout).println(boardstring);
		}catch(IOException e){
			System.out.println(e);
		}
	}

}
