import java.util.*;
import java.lang.*;
import java.io.*;

public class Problem3{
	public static void main(String[] args) throws FileNotFoundException{
		String fileName = "word-search.txt";
		Scanner sc;

		try{
			sc = new Scanner(new File(fileName));
		}catch(FileNotFoundException e){
			System.out.println("file not found");
			return;
		}

		int rows;
		int columns = 0;
		int numWords = 0;
		//counts rows and columns of grid
		//counts words to find
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
		System.out.println("rows: " + rows);
		System.out.println("columns: " + columns);
		System.out.println("words: " + numWords);

		String[] grid = new String[rows];
		String[] words = new String[numWords-1];

		//places contents of word-search.txt in grid array
		sc = new Scanner(new File(fileName));
		for(rows = 0; rows<grid.length; rows++){
			line = sc.nextLine();
			line = line.replaceAll("\\W", "");
			line = line.trim();
			grid[rows] = line;
		}
		//puts words to search for in words array
		line = sc.nextLine();
		int wordIndex = 0;
		while(sc.hasNext()){
			if(line.length()>0 && !line.equals("Words to find:")){
				line  = line.toLowerCase();
				line = line.replaceAll("\\W", "");
				words[wordIndex] = line;
				wordIndex++;
			}
			line = sc.nextLine();
		}
		//prints word grid and words to search for
		for(int i = 0; i < grid.length; i++){
			System.out.println(grid[i]);
		}
		for(int i = 0; i < words.length; i++){
			System.out.println(words[i]);
		}
		//System.out.println(words.length);
		System.out.println(searchRight(17, 2, "mushroom", grid));
	}

	public static String searchRight(int row, int column , String word, String[] grid){
		int[] location = new int[2];
		int i = row;
		int j = column;
		String testWord = "";
		int nthLetter = 0;
		//conditions to stay within grid limits and only go as far as the length of word
		while(i>=0 && i<grid.length && j>=0 && j<grid[0].length() && nthLetter<word.length()){
			testWord = testWord + grid[i].charAt(j);
			j++;	//moves right
			nthLetter++; //how far the search can go
		}
		System.out.println("test Word: " + testWord);
		if(word.equals(testWord)){
			return "TRUE";
		}
		return "FALSE";

	}

}
