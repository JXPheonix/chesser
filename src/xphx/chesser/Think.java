package xphx.chesser;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Think {

	public static final boolean winner = false;
	public static final String move = "glitch:ity";
	final static Charset ENCODING = StandardCharsets.UTF_8;
	public static String lessonslocation = null;

	public String think(String[] board) {
		//First, refer to lessons.txt for any related situations.
		String[] related = null;
		try {
			related = remenisce();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//If there are enough related situations, refer to their outcome and stuff
		if(related[0] == "yes"){
			referAndMove(related);
		}else{
			//If there are no related situations, run through various scenarios.
			strategyAndMove();
		}
		return null;
	}
	
	private void strategyAndMove() {
		// TODO Auto-generated method stub
		
	}

	private void referAndMove(String[] related) {
		// TODO Auto-generated method stub
		
	}

	private String[] remenisce() throws IOException {
		//Doo do do. 
		Path path = Paths.get(lessonslocation);
	    try (Scanner scanner =  new Scanner(path, ENCODING.name())){
	      while (scanner.hasNextLine()){
	        System.out.println(scanner.next());
	      }      
	    }
	    
		return null;
	}

	public boolean endgame() {
		// TODO Auto-generated method stub
		return false;
	}

}
