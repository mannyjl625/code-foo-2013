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

		//takes in user input, rejects invalid words
		do{
			System.out.println("Enter starting three letter word \n");
			Scanner input = new Scanner(System.in);
			word = input.nextLine();
			if(list.contains(word.toUpperCase())){
				break;
			}
			System.out.println("word is not in list. try again");

		}while(true);
		do{
			System.out.println("Enter ending three letter word \n");
			Scanner input = new Scanner(System.in);
			word = input.nextLine();
			if(list.contains(word.toUpperCase())){
				break;
			}
			System.out.println("word is not in list. try again");

		}while(true);
		//System.out.println(list);

	}
}
