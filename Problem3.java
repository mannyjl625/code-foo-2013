import java.util.*;
import java.lang.*;
import java.io.*;

public class Problem3{
	public static void main(String[] args) throws FileNotFoundException{
		ArrayList<String> list = new ArrayList<String>();
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
		while(sc.hasNext()){
			word = sc.nextLine();
			list.add(word);
		}
		String start;
		String end;
		//takes in user input, rejects invalid words
		do{
			System.out.println("Enter starting three letter word \n");
			Scanner input = new Scanner(System.in);
			start = input.nextLine();
			start = start.toUpperCase();
			if(list.contains(start)){
				break;
			}
			System.out.println("word is not in list. try again");

		}while(true);
		do{
			System.out.println("Enter ending three letter word \n");
			Scanner input = new Scanner(System.in);
			end = input.nextLine();
			end = end.toUpperCase();
			if(list.contains(end)){
				break;
			}
			System.out.println("word is not in list. try again");

		}while(true);
		//System.out.println(list);
		
		//System.out.println(replaceLeft(start,end));
		//System.out.println(replaceMid(start,end));
		//System.out.println(replaceRight(start,end));
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

	}
	public static String replaceLeft(String start, String end){
		return end.charAt(0) + start.substring(1);
	}
	public static String replaceMid(String start, String end){
		return start.substring(0, 1) + end.charAt(1) +  start.charAt(2);
	}
	public static String replaceRight(String start, String end){
		return start.substring(0, 2) + end.charAt(2);
	}
}
