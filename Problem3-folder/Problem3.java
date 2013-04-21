import java.util.*;
import java.lang.*;
import java.io.*;

//helper ThreeLetter Word object
class ThreeLetterWord{
	String word;   //the word it represents(UpperCase)
	boolean found; // whether it has been visited(for bfs)
	int steps;     // number of steps the word is away from start
	ArrayList<String> stepWords;  //words visited along the way from start
	ArrayList<ThreeLetterWord> moves;  //all other words adjacent by one 'move'
	
	public ThreeLetterWord(){
		this.word = "";
		this.found = false;
		int moves = 0;
		this.stepWords = new ArrayList<String>();
		this.moves = new ArrayList<ThreeLetterWord>();
		
	}
	
	public ThreeLetterWord(String word){
		this.word = word;
		this.found = false;;
		int moves = 0;
		this.stepWords = new ArrayList<String>();
		this.moves = new ArrayList<ThreeLetterWord>();
	}
}
//generic Queue structure
class Queue<T> {
	
	class CLLNode<E> {
		E data; CLLNode<E> next;
		CLLNode(E data) { 
		this.data = data; next = null;
		}
	}
	
	CLLNode<T> rear; 
	int size;

	public Queue() {
		rear = null; size=0;
	}

	public void enqueue(T item) {
		CLLNode<T> temp = new CLLNode<T>(item);
		if (size == 0) {
			temp.next = temp;
		} else {
			temp.next = rear.next;
			rear.next = temp;
		}
		rear = temp;
		size++;
	}

	public T dequeue() 
	throws NoSuchElementException { 
		if (size == 0) throw new NoSuchElementException();
		T o = rear.next.data;
		if (size == 1) {
			rear = null; 
		} else {
			rear.next = rear.next.next;
		}
		size--;
		return o;
	}

	public T peek() 
	throws NoSuchElementException { 
		if (size == 0) throw new NoSuchElementException();
		return rear.next.data;
	}

	public boolean isEmpty(){
		return size == 0;
	}

	public int size(){
		return size;
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
		String start;
		String end;
		//takes in user input, rejects invalid words
		do{
			System.out.println("Enter starting three letter word \n");
			Scanner input = new Scanner(System.in);
			start = input.nextLine();
			start = start.toUpperCase();
			boolean found = false;
			//checks if user input is in the list of words
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
			//checks if user input is in the list of words
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
		//skip making graph if start and end match
		if(start.equals(end)){
			System.out.println("Moves: 0");
			return;
		}

		//Make graph of one-move connections. Makes pointes to start and end words
		ThreeLetterWord startWord = null;
		ThreeLetterWord endWord = null;

		for(int i = 0; i<list.size(); i++){
			ThreeLetterWord currentWord = list.get(i);
			//makes pointers to start and end words
			if(start.equals(currentWord.word)){
				startWord = currentWord;
			}
			if(end.equals(currentWord.word)){
				endWord = currentWord;
			}
			//for each word, check every other word to see if they are linked by one move
			
			for(int j = 0; j<list.size(); j++){
				ThreeLetterWord moveWord = list.get(j);
				if(moveWord.word.equals(currentWord.word)){
					continue;
				}
				//change current word towards end word by one move, add to movelist if equal
				if(currentWord.word.charAt(0)!=moveWord.word.charAt(0)){
					String change1 = replace(currentWord.word, moveWord.word, 'l');
					if(change1.equals(moveWord.word)){
						currentWord.moves.add(moveWord);
					}
				}else if(currentWord.word.charAt(1)!=moveWord.word.charAt(1)){
					String change2 = replace(currentWord.word, moveWord.word, 'm');
					if(change2.equals(moveWord.word)){
						currentWord.moves.add(moveWord);
					}
				}else if(currentWord.word.charAt(2)!=moveWord.word.charAt(2)){
					String change3 = replace(currentWord.word, moveWord.word, 'r');
					if(change3.equals(moveWord.word)){
						currentWord.moves.add(moveWord);
					}
				}

			}
			currentWord.moves.trimToSize();
		}
		/*
		Debuggin print statments
		//prints all links
		for(int i = 0; i < list.size(); i++){
			ThreeLetterWord w = list.get(i);
			for(int j = 0; j < w.moves.size(); j++){
				//System.out.println(w.moves.get(j).word);
			}	
		}
		//prints all moves for a certain word
		ThreeLetterWord w = list.get(0);
		System.out.println(w.word);
		System.out.println("***************");
		for(int j = 0; j < w.moves.size(); j++){
			System.out.println(w.moves.get(j).word);
		}
		System.out.println("");
		System.out.println("Start word: " + startWord.word);
		System.out.println("end word: " + endWord.word);

		*/

		//Breadth first search starting at start word, looking for endword
		//keeping track of moves from start and path of words taked
		Queue<ThreeLetterWord> q = new Queue<ThreeLetterWord>();
		q.enqueue(startWord);
		startWord.found = true;
		int moves = 0;
		while(!q.isEmpty()){
			
			ThreeLetterWord searchWord = q.dequeue();
			//if searchword matches endword, print moves and path. end
			if(searchWord.word.equals(endWord.word)){
				searchWord.stepWords.add(searchWord.word); //tac on end word to path of words traveled
				System.out.println("Path: " + searchWord.stepWords);
				System.out.println("Moves: " + endWord.steps);
				return;
			}
			for(int i = 0; i<searchWord.moves.size(); i++){
				ThreeLetterWord link = searchWord.moves.get(i);
				if(!link.found){
					link.steps = searchWord.steps+1; //keeps track of nubmer of steps taken to each word from start word
					copyHistory(link, searchWord);
					link.stepWords.add(searchWord.word);
					link.found = true;
					q.enqueue(link);
				}
			}			
		}
		System.out.println("No valid move path between " + start + " and " + end);  
	}
	/*
	 *returns first one-move linked word from the arguement word that hasnt been visited
	 *takes in ThreeLetterWord, returns first ThreeLetter word link
	*/
	public static ThreeLetterWord getUnvisitedWord(ThreeLetterWord word){
		for(int i = 0; i<word.moves.size(); i++){
			if(!word.moves.get(i).found){
				return word.moves.get(i);
			}
		}
		return null;


	}
	/*
	 * copys words tranversed  history of word2 into word1 stepWords ArrayList
	*/
	public static void copyHistory(ThreeLetterWord word1, ThreeLetterWord word2){
		for(int i = 0 ; i<word2.stepWords.size(); i++){
			word1.stepWords.add(word2.stepWords.get(i));
		}
	}
	/*
	generates 3 moves, one for each charater, from the start word to end word
	input:start String, end String, character to speficty which move to make(left, middle, or right characters)
	returns new string, or "" if invalid section character was passed in
	*/
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
