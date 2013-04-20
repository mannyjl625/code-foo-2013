import java.util.*;
import java.lang.*;
import java.io.*;

class ThreeLetterWord{
	String word;
	ArrayList<ThreeLetterWord> moves;
	
	public ThreeLetterWord(){
		this.moves = new ArrayList<ThreeLetterWord>();
	}
	
	public ThreeLetterWord(String word){
		this.word = word;
		this.moves = new ArrayList<ThreeLetterWord>();
	}
}


public class Problem3{
	public static void main(String[] args) throws FileNotFoundException{
		ArrayList<ThreeLetterWord> list = new ArrayList<ThreeLetterWord>();
		String fileName = "three-letter-words.txt";
		Scanner sc;
		//throws error if 'three-letter-words.txt' isnt in directory
		try{
			sc = new Scanner(new File(fileName));
		}catch(FileNotFoundException e){
			System.out.println("file not found");
			return;
		}
		String word;
		//makes list of word objects
		while(sc.hasNext()){
			word = sc.nextLine();
			ThreeLetterWord w = new ThreeLetterWord(word);
			list.add(w);
		}
		list.trimToSize();
		for(int i = 0; i<list.size(); i++){
			//System.out.println(list.get(i).word);
		}
		String start;
		String end;
		//takes in user input, rejects invalid words
		do{
			System.out.println("Enter starting three letter word \n");
			Scanner input = new Scanner(System.in);
			start = input.nextLine();
			start = start.toUpperCase();
			boolean found = false;
			for(int i = 0; i<list.size(); i++){
				if(list.get(i).word.equals(start)){
					found = true;
					break;
				}
			}
			if(found){
				break;
			}
			System.out.println("word is not in list. try again");
		}while(true);
		do{
			System.out.println("Enter ending three letter word \n");
			Scanner input = new Scanner(System.in);
			end = input.nextLine();
			end = end.toUpperCase();
			boolean found = false;
			for(int i = 0; i<list.size(); i++){
				if(list.get(i).word.equals(end)){
					found = true;
					break;
				}
			}
			if(found){
				break;
			}
			System.out.println("word is not in list. try again");
		}while(true);
		
		//make graph
		ThreeLetterWord startWord;
		ThreeLetterWord endWord;

		for(int i = 0; i<list.size(); i++){
			ThreeLetterWord currentWord = list.get(i);
			if(start.equals(currentWord.word)){
				startWord = currentWord;
			}
			if(end.equals(currentWord.word)){
				endWord = currentWord;
			}
			for(int j = 0; j<list.size(); j++){
				ThreeLetterWord moveWord = list.get(j);
				if(moveWord.word.equals(currentWord.word)){
					continue;
				}

				if(currentWord.word.charAt(0)!=moveWord.word.charAt(0)){
					String change1 = replace(currentWord.word, moveWord.word, 'l');
					if(change1.equals(moveWord.word)){
						currentWord.moves.add(moveWord);
					}
				}
				if(currentWord.word.charAt(1)!=moveWord.word.charAt(1)){
					String change2 = replace(currentWord.word, moveWord.word, 'm');
					if(change2.equals(moveWord.word)){
						currentWord.moves.add(moveWord);
					}
				}
				if(currentWord.word.charAt(2)!=moveWord.word.charAt(2)){
					String change3 = replace(currentWord.word, moveWord.word, 'r');
					if(change3.equals(moveWord.word)){
						currentWord.moves.add(moveWord);
					}
				}

			}
			currentWord.moves.trimToSize();
		}
		//prints all links
		for(int i = 0; i < list.size(); i++){
			ThreeLetterWord w = list.get(i);
			for(int j = 0; j < w.moves.size(); j++){
				//System.out.println(w.moves.get(j).word);
			}	
		}
		//prints all moves for a certain word
		ThreeLetterWord w = list.get(1);
		System.out.println(w.word);
		System.out.println("***************");
		for(int j = 0; j < w.moves.size(); j++){
			System.out.println(w.moves.get(j).word);
		}
	
		



		//System.out.println(list);
		
		//System.out.println(replaceLeft(start,end));
		//System.out.println(replaceMid(start,end));
		//System.out.println(replaceRight(start,end));
		/*
		int numMoves =0;
		String moves = start;
		String change1;
		String change2;
		String change3;
		//generates 3 moves, 1 for each character, and picks the first valid move until end word is met 
		while(!moves.equals(end)){
			change1 = replaceLeft(moves, end);
			change2 = replaceMid(moves, end);
			change3 = replaceRight(moves, end);
			if(moves.charAt(0)!= end.charAt(0) && list.contains(change1)){
				System.out.println(change1);
				numMoves++;
				moves = change1;
			}else if(moves.charAt(1)!=end.charAt(1) && list.contains(change2)){
				System.out.println(change2);
				numMoves++;
				moves = change2;
			}else if(moves.charAt(2)!=end.charAt(2) && list.contains(change3)){
				System.out.println(change3);
				numMoves++;
				moves = change3;
			}
		}
		System.out.println("Number of moves: " + numMoves);
		*/
	}
	//methods that take in word, replace one letter with second words letter 
	public static String replace(String start, String end, char section){
		if(section=='l'){
			return end.charAt(0) + start.substring(1);
		}else if(section=='m'){
			return start.substring(0, 1) + end.charAt(1) +  start.charAt(2);
		}else if(section=='r'){
			return start.substring(0, 2) + end.charAt(2);
		}
		return "";
	}
}
