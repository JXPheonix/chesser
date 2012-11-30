package xphx.chesser;

import java.util.Scanner;

public class Main {
	
	//I am totally redoing this because the old chesser code is a clusterfuck mess and I don't want it that way.
	//This time, I'm taking more cues from Medicore Chess (http://mediocrechess.blogspot.com/)
	//Making more unique classes, 0x88, etc.
	
	public static void main(String[] args){
		System.out.println("Chesser by XphnX");
		Board board = new Board();
		Scanner scan = new Scanner(System.in);
		System.out.println("I use glicko-2 to calculate ratings.");
		System.out.println("What's your ID? (It's a 7 digit number. If you don't have one, refer to newPlayer.txt for a new ID.)");
		int opponent = scan.nextInt();
		//not doing anything with that yet, for later
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
	}

	private static void createPlayers() {
		// TODO Auto-generated method stub
		
	}

	private static void PlayerMove() {
		// TODO Auto-generated method stub
		
	}

}
