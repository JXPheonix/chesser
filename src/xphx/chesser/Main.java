package xphx.chesser;

import java.util.Scanner;

public class Main {
	
	//I am totally redoing this because the old chesser code is a clusterfuck mess and I don't want it that way.
	//This time, I'm taking more cues from Medicore Chess (http://mediocrechess.blogspot.com/)
	//Making more unique classes, 0x88, etc.
	
	static Board board = new Board();
	
	public static void main(String[] args){
		System.out.println("Chesser by XphnX");
		Scanner scan = new Scanner(System.in);
		//System.out.println("I use glicko-2 to calculate ratings.");
		//System.out.println("What's your ID? (It's a 7 digit number. If you don't have one, refer to newPlayer.txt for a new ID.)");
		//int opponent = scan.nextInt();
		//Not doing anything with the above, yet
		System.out.println("Am I white or black?");
		String position = scan.next();
		boolean start = false;
		if(position == "black"){
			System.out.println("Ok, cool, you can start.");
			start = false;
		}else if(position == "white"){
			System.out.println("Sure! I'll go then.");
			start = true;
		}else{
			System.out.println("Excuse me? Um, I'll just assume you go first.");
		}
		//Reset board
		board.reset();
		board.log("GAMEBREAK");
		if(start){
			board.update(new Move(board));
		}
		for(board.mate = board.mate; board.mate = false;){
			PlayerMove();
			board.log(null);
			board.update(new Move(board));
		}
	}

	private static void createPlayers() {
		//Nothing yet; for ratings management
	}

	private static void PlayerMove() {
		System.out.println("Make your move!");
		board.update(new Move(new Scanner(System.in).next(), board));
	}

}
