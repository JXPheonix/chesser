package xphx.chesser;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Move {
	
	public int oldloc;
	public int newloc;

	public Move(Board board){
		//If there is no argument that means the computer has to make the move
		//First, we input the current situation from the board.
		int[] pieces = Board.board;
		//Now we look through the log to see if there are any matching situations
		//If there are, we analyze and move
		//If there aren't, we randomly move
		//Hopefully once the log is full enough there should be a situation for everything
		//and we can remove the randoming
		if(identicalSituation(pieces)){
			//It will have saves the next moves
		}
	}

	private boolean identicalSituation(int[] pieces) {
		boolean banana = (Boolean) null;
		//Ok get to work
		//First convert the current state into a string
		String piecestate = "";
		for(int i = 0; i < 128; i++){
			piecestate += Integer.toString(pieces[i]);
			piecestate += ":";
		}
		FileInputStream login;
		try{ //and if it fails try again
			login = new FileInputStream("log.txt");
			Path pathtolog = Paths.get("log.txt");
			try (Scanner scanner =  new Scanner(pathtolog)){
			      while (scanner.hasNextLine()){
			        //ok now lets check for this situation
			    	  if(piecestate == scanner.nextLine()){
			    		  //Hurrah!
			    		  banana = true;
			    	  }
			      }      
			    }
		}catch(IOException e){
			System.out.println(e);
		}
		if(banana != true){
			banana = false; //stupidest 3 lines ever, but this is just so that banana is not null
		}
		return banana;
	}

	public Move(String algm, Board board) {
		//If there is an argument, the humans already made the move
	}

}
