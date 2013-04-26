import java.util.*;
import java.lang.*;
import java.io.*;
/*For this problem, I read in the wordsearch grid from the text file
 * and passed in into an array, and then passed the words to be search
 * in their own array.
 *
 * After the grid and words were stored, I iterated through the grid looking for 
 * the words in the list in all 8 directions. When a word was found, I stored it in 
 * a results arraylist to be displayed at the end of the program
 */


public class Wordsearch{
	public static void main(String[] args) throws FileNotFoundException{
		String fileName = "word-search.txt";
		Scanner sc;
		//throws error if 'word-search.txt' isnt in directory
		try{
			sc = new Scanner(new File(fileName));
		}catch(FileNotFoundException e){
			System.out.println("file not found");
			return;
		}
		//measuring dimension of word grid and length of word list
		int rows;
		int columns = 0;
		int numWords = 0;
		String line;
		for(rows = 0; sc.hasNext(); rows++){
			line = sc.nextLine();
			line = line.replaceAll("\\W", "");
			line = line.trim();
			//at end of grid, counts how many words are under word search
			if(line.length()<1 || line.equals("Words to find:")){
				line = sc.nextLine();
				while(sc.hasNext()){
					numWords++;
					line = sc.nextLine();
				}
				break;
			}else{
				columns = line.length();
			}
		}
		//System.out.println("rows: " + rows);
		//System.out.println("columns: " + columns);
		//System.out.println("words: " + numWords);

		String[] grid = new String[rows];
		String[] words = new String[numWords];

		//places contents of word-search.txt in grid array
		sc = new Scanner(new File(fileName));
		for(rows = 0; rows<grid.length; rows++){
			line = sc.nextLine();
			line = line.replaceAll("\\W", "");
			line = line.trim();
			grid[rows] = line;
		}
		//puts words to search for in words array
		int wordIndex = 0;
		while(sc.hasNext()){
			line = sc.nextLine();
			if(line.length()>0 && !line.equals("Words to find:")){
				words[wordIndex] = line;
				wordIndex++;
			}
		}

		//prints word grid and words to search for
		System.out.println("");
		for(int i = 0; i < grid.length; i++){
			System.out.println(grid[i]);
		}
		System.out.println("");
		//prints word list out
		System.out.println("**********************");
		for(int i = 0; i < words.length; i++){
			System.out.println(words[i]);
		}
		System.out.println("**********************");
		System.out.println("");

		//for each word in words arrary, go letter by letter looking in 8 directions 	
		boolean found;
		int foundWords = 0;
		for(wordIndex = 0; wordIndex<words.length; wordIndex++){
			found = false;
			for(rows = 0; rows<grid.length; rows++){
				for(columns = 0; columns<grid[0].length(); columns++){	
					if(search(rows, columns, words[wordIndex], grid,"up")){
						found = true;
					}else if(search(rows, columns, words[wordIndex], grid, "down")){
						found = true;
					}else if(search(rows, columns, words[wordIndex], grid, "left")){
						found = true;
					}else if(search(rows, columns, words[wordIndex], grid, "right")){
						found = true;
					}else if(search(rows, columns, words[wordIndex], grid, "upright")){
						found = true;
					}else if(search(rows, columns, words[wordIndex], grid, "upleft")){
						found = true;
					}else if(search(rows, columns, words[wordIndex], grid, "downright")){
						found = true;
					}else if(search(rows, columns, words[wordIndex], grid, "downleft")){
						found = true;
					}
					if(found){
						foundWords++;
						break;
					}
				}
				if(found){
					break;
				}
			}
			if(!found){
				System.out.println(words[wordIndex]+ " is not in the wordsearch");
			}
		}
		System.out.println("words found: " + foundWords + "/" +words.length);
	}
	

	/*Helper method that looks in specific directions to find the word passed in,
	 * starting at a specifed index in the wordsearch grid
	 *
	 */
	public static boolean search(int row, int column , String word, String[] grid, String direction){
		int directionX = 0;
		int directionY = 0;
		//based on input direction, lets counter modifier to travel in corresponding direction
		if(direction.contains("right")){
			directionX = 1;
		}else if(direction.contains("left")){
			directionX = -1;
		}

		if(direction.contains("up")){
			directionY = -1;
		}else if(direction.contains("down")){
			directionY = 1;
		}

		String word2 = word;
		word2  = word2.toLowerCase();
		word2 = word2.replaceAll("\\W", "");
		word2 = word2.trim();
		int i = row;
		int j = column;
		String testWord = "";
		int nthLetter = 0;
		//conditions to stay within grid limits and only go as far as the length of word
		while(i>=0 && i<grid.length && j>=0 && j<grid[0].length() && nthLetter<word2.length()){
			testWord = testWord + grid[i].charAt(j);
			i = i + directionY;
			j = j + directionX;	//moves left
			nthLetter++; //how far the search can go
		}
		if(word2.equals(testWord)){
			System.out.println("Found \"" + word + "\" starting at (row, coloumn) ("+row+", "+column+") going " + direction);
			return true;
		}
		return false;
	}
}
