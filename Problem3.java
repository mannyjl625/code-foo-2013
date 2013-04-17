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
		//grabs the last line
		line  = line.toLowerCase();
		line = line.replaceAll("\\W", "");
		words[wordIndex] = line;

		//prints word grid and words to search for
		for(int i = 0; i < grid.length; i++){
			System.out.println(grid[i]);
		}
		//prints word list out
		for(int i = 0; i < words.length; i++){
			System.out.println(words[i]);
		}
		System.out.println("**********************");
		//for each word in words arrary, go letter by letter looking in 8 directions 	
		boolean found;
		int foundWords = 0;
		for(wordIndex = 0; wordIndex<words.length; wordIndex++){
			found = false;
			for(rows = 0; rows<grid.length; rows++){
				for(columns = 0; columns<grid[0].length(); columns++){	
					if(searchRight(rows, columns, words[wordIndex], grid)){
						found = true;
						break;
					}
					if(searchLeft(rows, columns, words[wordIndex], grid)){
						found = true;
						break;
					}
					if(searchUp(rows, columns, words[wordIndex], grid)){
						found = true;
						break;
					}
					if(searchDown(rows, columns, words[wordIndex], grid)){
						found = true;
						break;
					}
					if(searchUpRight(rows, columns, words[wordIndex], grid)){
						found = true;
						break;
					}
					if(searchDownRight(rows, columns, words[wordIndex], grid)){
						found = true;
						break;
					}
					if(searchUpLeft(rows, columns, words[wordIndex], grid)){
						found = true;
						break;
					}
					if(searchDownLeft(rows, columns, words[wordIndex], grid)){
						found = true;
						break;
					}
				}
				if(found){
					foundWords++;
					break;
				}
			}
		}
		System.out.println("words found: " + foundWords);
	}

	public static boolean searchRight(int row, int column , String word, String[] grid){
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
		//System.out.println("test Word: " + testWord);
		if(word.equals(testWord)){
			System.out.println("Found " + word + " starting at (row, coloumn) ("+row+", "+column+") going right");
			return true;
		}
		//System.out.println("Did not find " + word);
		return false;

	}
	public static boolean searchLeft(int row, int column , String word, String[] grid){
		int i = row;
		int j = column;
		String testWord = "";
		int nthLetter = 0;
		//conditions to stay within grid limits and only go as far as the length of word
		while(i>=0 && i<grid.length && j>=0 && j<grid[0].length() && nthLetter<word.length()){
			testWord = testWord + grid[i].charAt(j);
			j--;	//moves left
			nthLetter++; //how far the search can go
		}
		//System.out.println("test Word: " + testWord);
		if(word.equals(testWord)){
			System.out.println("Found " + word + " starting at (row, coloumn) ("+row+", "+column+") going left");
			return true;
		}
		//System.out.println("Did not find " + word);
		return false;
	}
	public static boolean searchUp(int row, int column , String word, String[] grid){
		int i = row;
		int j = column;
		String testWord = "";
		int nthLetter = 0;
		//conditions to stay within grid limits and only go as far as the length of word
		while(i>=0 && i<grid.length && j>=0 && j<grid[0].length() && nthLetter<word.length()){
			testWord = testWord + grid[i].charAt(j);
			i--;	//moves up
			nthLetter++; //how far the search can go
		}
		//System.out.println("test Word: " + testWord);
		if(word.equals(testWord)){
			System.out.println("Found " + word + " starting at (row, coloumn) ("+row+", "+column+") going up");
			return true;
		}
		//System.out.println("Did not find " + word);
		return false;
	}
	public static boolean searchDown(int row, int column , String word, String[] grid){
		int i = row;
		int j = column;
		String testWord = "";
		int nthLetter = 0;
		//conditions to stay within grid limits and only go as far as the length of word
		while(i>=0 && i<grid.length && j>=0 && j<grid[0].length() && nthLetter<word.length()){
			testWord = testWord + grid[i].charAt(j);
			i++;	//moves up
			nthLetter++; //how far the search can go
		}
		//System.out.println("test Word: " + testWord);
		if(word.equals(testWord)){
			System.out.println("Found " + word + " starting at (row, coloumn) ("+row+", "+column+") going down");
			return true;
		}
		//System.out.println("Did not find " + word);
		return false;
	}
	public static boolean searchUpRight(int row, int column , String word, String[] grid){
		int i = row;
		int j = column;
		String testWord = "";
		int nthLetter = 0;
		//conditions to stay within grid limits and only go as far as the length of word
		while(i>=0 && i<grid.length && j>=0 && j<grid[0].length() && nthLetter<word.length()){
			testWord = testWord + grid[i].charAt(j);
			i--;	//moves up
			j++; //moves right
			nthLetter++; //how far the search can go
		}
		//System.out.println("test Word: " + testWord);
		if(word.equals(testWord)){
			System.out.println("Found " + word + " starting at (row, coloumn) ("+row+", "+column+") going upright");
			return true;
		}
		//System.out.println("Did not find " + word);
		return false;
	}
	public static boolean searchDownRight(int row, int column , String word, String[] grid){
		int i = row;
		int j = column;
		String testWord = "";
		int nthLetter = 0;
		//conditions to stay within grid limits and only go as far as the length of word
		while(i>=0 && i<grid.length && j>=0 && j<grid[0].length() && nthLetter<word.length()){
			testWord = testWord + grid[i].charAt(j);
			i++;	//moves down
			j++; //moves right
			nthLetter++; //how far the search can go
		}
		//System.out.println("test Word: " + testWord);
		if(word.equals(testWord)){
			System.out.println("Found " + word + " starting at (row, coloumn) ("+row+", "+column+") going downright");
			return true;
		}
		//System.out.println("Did not find " + word);
		return false;
	}
	public static boolean searchUpLeft(int row, int column , String word, String[] grid){
		int i = row;
		int j = column;
		String testWord = "";
		int nthLetter = 0;
		//conditions to stay within grid limits and only go as far as the length of word
		while(i>=0 && i<grid.length && j>=0 && j<grid[0].length() && nthLetter<word.length()){
			testWord = testWord + grid[i].charAt(j);
			i--;	//moves up
			j--; //moves left
			nthLetter++; //how far the search can go
		}
		//System.out.println("test Word: " + testWord);
		if(word.equals(testWord)){
			System.out.println("Found " + word + " starting at (row, coloumn) ("+row+", "+column+") going upleft");
			return true;
		}
		//System.out.println("Did not find " + word);
		return false;
	}
	public static boolean searchDownLeft(int row, int column , String word, String[] grid){
		int i = row;
		int j = column;
		String testWord = "";
		int nthLetter = 0;
		//conditions to stay within grid limits and only go as far as the length of word
		while(i>=0 && i<grid.length && j>=0 && j<grid[0].length() && nthLetter<word.length()){
			testWord = testWord + grid[i].charAt(j);
			i++;	//moves down
			j--; //moves left
			nthLetter++; //how far the search can go
		}
		//System.out.println("test Word: " + testWord);
		if(word.equals(testWord)){
			System.out.println("Found " + word + " starting at (row, coloumn) ("+row+", "+column+") going downleft");
			return true;
		}
		//System.out.println("Did not find " + word);
		return false;
	}


}
