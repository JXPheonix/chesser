package xphx.chesser;

public class ArrayMake {
	
	String[] board = new String[64];
	
	public ArrayMake() {
		int i = 0;
		do{
			switch(i){
			case(0):
				board[i] = "black-rook";
				break;
			case(1):
				board[i] = "black-knight";
				break;
			case(2):
				board[i] = "black-bishop";
				break;
			case(3):
				board[i] = "black-queen";
				break;
			case(4):
				board[i] = "black-king";
				break;
			case(5):
				board[i] = "black-bishop";
				break;
			case(6):
				board[i] = "black-knight";
				break;
			case(7):
				board[i] = "black-rook";
				break;
			case(8):
				board[i] = "black-pawn";
				break;
			case(9):
				board[i] = "black-pawn";
				break;
			case(10):
				board[i] = "black-pawn";
				break;
			case(11):
				board[i] = "black-pawn";
				break;
			case(12):
				board[i] = "black-pawn";
				break;
			case(13):
				board[i] = "black-pawn";
				break;
			case(14):
				board[i] = "black-pawn";
				break;
			case(15):
				board[i] = "black-pawn";
				break;
			case(48):
				board[i] = "white-pawn";
				break;
			case(49):
				board[i] = "white-pawn";
				break;
			case(50):
				board[i] = "white-pawn";
				break;
			case(51):
				board[i] = "white-pawn";
				break;
			case(52):
				board[i] = "white-pawn";
				break;
			case(53):
				board[i] = "white-pawn";
				break;
			case(54):
				board[i] = "white-pawn";
				break;
			case(55):
				board[i] = "white-pawn";
				break;
			case(56):
				board[i] = "white-rook";
				break;
			case(57):
				board[i] = "white-knight";
				break;
			case(58):
				board[i] = "white-bishop";
				break;
			case(59):
				board[i] = "white-queen";
				break;
			case(60):
				board[i] = "white-king";
				break;
			case(61):
				board[i] = "white-bishop";
				break;
			case(62):
				board[i] = "white-knight";
				break;
			case(63):
				board[i] = "white-rook";
				break;
			default:
				board[i] = "empty";
				break;
			}
			i++;
		}while(i <= 64);
	}

}
