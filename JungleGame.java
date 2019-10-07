
/*
 * Aditya Yadav
 * 11/28/2017
 * Psuedocode: Create world, ask for animals, put animals, move around
 * Program allows the user to move around in a jungle and do stuff like add and remove animals. Thats about it.
 */
import java.util.*;

public class JungleGame {
	// Creating all the variables
	static ArrayList<String> ar = new ArrayList<String>();
	static int length, width, yPos, xPos;
	static String name;
	static String[][] jungle;
	static String[][] animalLocation;
	static Boolean[][] bool = new Boolean[1][2];
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// Getting world length
		System.out.println("Enter length: ");
		length = sc.nextInt();
		// Getting world width
		System.out.println("Enter width: ");
		width = sc.nextInt();
		// Creating jungle using the inputed length and width
		jungle = new String[length][width];
		// Creating array to store animal locations
		animalLocation = new String[length][width];

		for (int i = 0; i < length; i++) {
			for (int c = 0; c < width; c++) {

				jungle[i][c] = "*";

				animalLocation[i][c] = "";

			}
		}
		// making jungle at 0, 0 X
		jungle[0][0] = "X";
		// repeat until user exits
		Boolean repeat = true;
		while (repeat) {
			menu();
			int choice = sc.nextInt();
			if (choice == 1) {
				insertAnimalWorld();
			}
			if (choice == 2) {
				removeAnimalWorld();
			}
			if (choice == 3) {
				System.out.println("Explore the jungle: ");
				exploreJungle(width, length, ar);
			}
			if (choice == 4) {
				System.out.println(
						"I hope you have a nice day. Unless you removed an animal, in which case, have a horrible day.");
				System.exit(0);
			}
		}
	}

	// method for moving around instruction
	static void moves() {
		System.out.println("W. Move Forward");
		System.out.println("A. Move Left");
		System.out.println("S. Move Backward");
		System.out.println("D. Move Right");
		System.out.println("I. Display observed animals");
		System.out.println("E. Exit");
	}

	// method for menu instructions
	static void menu() {
		System.out.println("1. Insert an animal into the jungle");
		System.out.println("2. Remove an animal from the jungle (Only evil people do this)");
		System.out.println("3. Explore the jungle");
		System.out.println("4. Exit");
	}

	static void makeWorld(String jungle[][], int posX, int posY) {
		jungle[posX][posY] = "X";
	}

	// method to print world
	static void printWorld(int length, int width, String[][] jungle) {
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(jungle[i][j]);
			}
			System.out.println("");

		}
	}

	// method for inserting animals into the world
	public static void insertAnimalWorld()

	{ // repeat until user exits
		boolean restart = true;
		while (restart) {
			System.out.println("Name your animal: ");
			name = sc.next();
			ar.add(name);
			System.out.println("Enter the X coordinate of your animal: ");
			int x = sc.nextInt();
			System.out.println("Enter the Y coordinate of your animal: ");
			int y = sc.nextInt();
			if (x < length && y < width && (animalLocation[x][y] == "")) {
				animalLocation[x][y] = name;
				System.out.println("Your animal is now in the jungle :)");
				restart = false;
			} else {
				System.out.println("You animal didn't make it to the jungle :(");
				System.out.println("Press enter to try again or type in EXIT to go back to the main screen: ");
				String repeat = sc.next();
				if (repeat.equalsIgnoreCase("exit")) {
					restart = false;
				} else
					restart = true;
			}
		}

	}

	// method for removing animals
	public static void removeAnimalWorld() {
		boolean repeat = true;
		// repeat until user exits
		while (repeat) {
			System.out.println("Enter the X coordinate of your animal : ");
			int x = sc.nextInt();
			System.out.println("Enter the Y coordinate of your animal : ");
			int y = sc.nextInt();
			if (x <= length && y <= width) {
				if (animalLocation[x][y] == "") {
					System.out.println("No animals to kill here you psychopath");
					System.out.println("Press enter to try again or type in EXIT to go back to the main screen: ");
					String restart = sc.next();
					if (restart.equalsIgnoreCase("exit")) {
						repeat = false;
					} else
						repeat = true;
				} else {
					animalLocation[x][y] = "";
					System.out.println(name + " has been terminated. I hope that gives you pleasure");
					repeat = false;
				}
			}
		}
	}

	// method for moving around the world
	public static void exploreJungle(int width, int length, ArrayList<String> ar) {
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(jungle[i][j]);
			}
			System.out.println();
		}

		Boolean repeat = true;

		while (repeat) {
			// import move method
			moves();
			// Get users choice on what move they want
			String choice = sc.next();

			if (choice.equalsIgnoreCase("W")) {
				xPos = xPos - 1;
				jungle[0][0] = "O";
				jungle[xPos + 1][yPos] = "O";
				jungle[xPos][yPos] = "X";

			} else if (choice.equalsIgnoreCase("S")) {
				xPos = xPos + 1;
				jungle[0][0] = "O";
				jungle[xPos - 1][yPos] = "O";
				jungle[xPos][yPos] = "X";

			} else if (choice.equalsIgnoreCase("A")) {
				yPos = yPos - 1;
				jungle[0][0] = "O";
				jungle[xPos][yPos + 1] = "O";
				jungle[xPos][yPos] = "X";

			} else if (choice.equalsIgnoreCase("D")) {
				yPos = yPos + 1;
				jungle[0][0] = "O";
				jungle[xPos][yPos - 1] = "O";
				jungle[xPos][yPos] = "X";

			} else if (choice.equalsIgnoreCase("I")) {
				System.out.println("Printing current observed Animals..");
				System.out.println(Arrays.toString(ar.toArray()));

				repeat = false;

				System.out.println("");

			} else if (choice.equalsIgnoreCase("E")) {
				System.exit(0);
			}

			printWorld(length, width, jungle);

		}

	}

}