
import java.util.*;
import java.io.*;

public class Censorship {
	private static ArrayList<String> words;

	public static void main(String... args) throws FileNotFoundException {
		words = new ArrayList<String>();
		Scanner file = new Scanner(new File("judgesdata\\censorship.dat"));

		int size = file.nextInt(); clearFollowingLine(file);

		String[] msgs = new String[size];

		for(int i = 0; i < size; i++) {
			// Look through words to check what needs to be censored, and censor
			// only wordsCopy. Map the two arrays.
			String[] wordsToCensor = file.nextLine().split(" ");
			String[] words         = file.nextLine().split(" ");
			String[] wordsCopy     = copyArray(words); 

			words         = lowercaseAll(words);
			wordsToCensor = lowercaseAll(wordsToCensor);

			int sizeOfWords = words.length-1;
			for(int j = 0; j < sizeOfWords; j++) {
				// if `word` is a word to be censored:
				if(contains(wordsToCensor, words[j])) {
					// Overwrite the word with all *'s. "china" -> "*****"
					wordsCopy[j] = genStarMsg(words[j].toCharArray().length);
				}

				if(containsRosie(words[j], words[j+1])) {
					wordsCopy[j]   = genStarMsg(words[j].toCharArray().length);
					wordsCopy[j+1] = genStarMsg(words[j+1].toCharArray().length);
				}
			}

			if(contains(wordsToCensor, words[sizeOfWords]))
				wordsCopy[sizeOfWords] = genStarMsg(words[sizeOfWords].toCharArray().length);

			msgs[i] = join(wordsCopy);
		}

		for(String s: msgs)
			System.out.println(s);
	}

	public static String[] copyArray(String[] s) {
		String[] c = new String[s.length];

		for(int i = 0; i < s.length; i++)
			c[i] = s[i];

		return c;
	}
	
	public static void clearFollowingLine(Scanner scan) {
		scan.nextLine();
	}

	public static String[] lowercaseAll(String[] msg) {
		String[] s = new String[msg.length];

		for(int i = 0; i < msg.length; i++) {
			s[i] = msg[i].toLowerCase();
		}

		return s;
	}

	public static String join(String[] list) {
		String msg = "";
		for(int i = 0; i < list.length-1; i++)
			msg += list[i] + " ";
		return msg + list[list.length-1];
	}

	public static String genStarMsg(int size) {
		String msg = "";
		for(int i = 0; i < size; i++) msg += "*";
		return msg;
	}

	public static boolean contains(String[] list, String msg) {
		for(String s: list)
			if(msg.equals(s)) return true;
		return false;
	}

	public static boolean containsRosie(String msg, String msg2) {
		return msg.equals("rosie") && msg2.equals("o'donnell");
	}
}
