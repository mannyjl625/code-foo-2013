import java.util.*;
import java.lang.*;
import java.io.*;

class ThreeLetterWord{
	String word;
	boolean found;;
	ArrayList<ThreeLetterWord> moves;
	
	public ThreeLetterWord(){
		this.word = "";
		this.found = false;
		this.moves = new ArrayList<ThreeLetterWord>();
	}
	
	public ThreeLetterWord(String word){
		this.word = word;
		this.found = false;;
		this.moves = new ArrayList<ThreeLetterWord>();
	}
}
/*
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

*/
public class Stack<T> {

	private ArrayList<T> items;

	public Stack() {
		items = new ArrayList<T>();
	}

	public void push(T item) {
		items.add(item);
	}

	throws NoSuchElementException {
		if (items.isEmpty()) {
			throw new NoSuchElementException("can't pop from an empty stack");
		}
		return items.remove(items.size()-1);
	}

	public T peek() 
	throws NoSuchElementException {
		if (items.size() == 0) {
			throw new NoSuchElementException("can't peek into an empty stack");
		}
		return items.get(items.size()-1);
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}

	public int size() {
		return items.size();
	}

	public void clear() {
		items.clear();
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
		ThreeLetterWord startWord = null;
		ThreeLetterWord endWord = null;

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
		//prints all links
		for(int i = 0; i < list.size(); i++){
			ThreeLetterWord w = list.get(i);
			for(int j = 0; j < w.moves.size(); j++){
				//System.out.println(w.moves.get(j).word);
			}	
		}
		//prints all moves for a certain word
		ThreeLetterWord w = list.get(60);
		System.out.println(w.word);
		System.out.println("***************");
		for(int j = 0; j < w.moves.size(); j++){
			System.out.println(w.moves.get(j).word);
		}
		System.out.println("");
		System.out.println("Start word: " + startWord.word);
		System.out.println("end word: " + endWord.word);

		//Starting graph traversal with bfs
		/*
		/Queue<ThreeLetterWord> q = new Queue<ThreeLetterWord>();
		q.enqueue(startWord);
		startWord.found = true;
		int moves = 0;
		while(!q.isEmpty()){
			ThreeLetterWord searchWord = q.dequeue();
			if(searchWord.word.equals(endWord.word)){
				//System.out.println(moves + " moves");
				System.out.println("found endword " + endWord.word);
				return;
			}
			for(int i = 0; i<searchWord.moves.size(); i++){
				ThreeLetterWord link = searchWord.moves.get(i);
				if(!link.found){
					link.found = true;
					q.enqueue(link);
				}
			}
			
		}
		*/
	}
	//method that take in word, replace one letter with second words letter 
	public static ThreeLetterWord getUnvisitedWord(ThreeLetterWord word){
		for(int i = 0; i<word.moves.size(); i++){
			if(!word.moves.get(i).found){
				return word.moves.get(i);
			}
		}
		return null;


	}
	
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
