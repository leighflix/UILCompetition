
import java.util.*;
import java.io.*;

class Wall {
	public static void main(String... args) throws FileNotFoundException {
		byte[] wall;
		long sizeOfWall = 100_000_000;
		Scanner file = new Scanner(new File("judgesdata\\wall.dat"));
		
		int dataSets = file.nextInt(); clearFollowingLine(file);
		
		for(int i = 0; i < dataSets; i++) {
			wall = new byte[sizeOfWall];

			buildWall(file, wall, sizeOfWall);
			checkWall(file, wall, sizeOfWall);
		}
	}
	
	public static void clearFollowingLine(Scanner file) {
		file.nextLine();	
	}
	
	// Intervals
	public static void buildWall(Scanner file, byte[] wall, long sizeOfWall) {
		int intervals = file.nextInt();
		for(int j = 0; j < intervals; j++) {
			int lower = file.nextInt(), upper = file.nextInt();
			
			for(; lower < upper; lower++) {
				wall[lower] = 1;
			}
		}	
	}
	
	// Queries
	public static void checkWall(Scaner file, byte[] wall, long sizeOfWall) {
		int queries = file.nextInt();
		
		for(int k = 0; k < queries; k++) {
			int lower = file.nextInt(), upper = file.nextInt();
			boolean isFired = false;

			for(; lower < upper; lower++) {

wall_checker:			if(wall[lower] != 1) {
					System.out.println("YA FIRED");
					isFired = true;
					break wall_checker;
				}
			}
			
			if(!isFired) System.out.println("I LOVE MEXICO");
		}
	}
}
