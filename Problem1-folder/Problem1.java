import java.util.*;
import java.lang.*;
import java.io.*;

class Person{
	String name;
	boolean visited;
	int generation;
	ArrayList<Person> children;

	public Person(){
		this.name = "";
		this.visited = false;
		this.generation = 0;
		this.children = new  ArrayList<Person>();
	}

	public Person(String name){
		this.name = name;
		this.visited = false;
		this.generation = 0;
		this.children = new  ArrayList<Person>();
	}

}
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

public class Problem1{
	public static void main(String[] args)
	throws FileNotFoundException{
		//gen 0
		Person root = new Person("root");
		//gen 1
		Person blanca = new Person("blanca");
		Person eguardo = new Person("eguardo");
		
		Person may = new Person("may");
		Person jake = new Person("jake");
		//gen 2
		Person wilfredo = new Person("wilfredo");
		Person mimi = new Person("mimi");
		
		Person elsi = new Person("elsi");
		Person eddy = new Person("eddy");

		Person tony = new Person("tony");
		Person denise = new Person("denise");

		Person lyn = new Person("lyn");
		Person piggie = new Person("piggie");  //yes my uncle's name is piggie
		
		//gen 3
		Person manny = new Person("manny");
		Person veronica = new Person("veronica");

		Person tito = new Person("tito");
		Person diego = new Person("diego");
		
		Person aja = new Person("aja");
		Person desiree = new Person("desiree");
		

		//gen 0 relations
		root.children.add(blanca);
		root.children.add(eguardo);

		//gen 1 relations
		blanca.children.add(wilfredo);
		blanca.children.add(mimi);
		blanca.children.add(elsi);
		blanca.children.add(eddy);
		blanca.children.add(tony);
		blanca.children.add(denise);
		eguardo.children = blanca.children;

		may.children.add(tony);
		may.children.add(lyn);
		may.children.add(denise);
		jake.children = may.children;

		//gen 2 relations
		elsi.children.add(desiree);
		eddy.children  = elsi.children;

		mimi.children.add(diego);
		mimi.children.add(tito);
		wilfredo.children = mimi.children;
		
		lyn.children.add(aja);
		piggie.children = lyn.children;

		denise.children.add(manny);
		denise.children.add(veronica);
		tony.children = denise.children;
		/*
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
		*/
		
	}	
	public static Person getUnvisitedChild(Person parent){
		if(parent.children.isEmpty()){
			return null;
		}
		return null;
	}
		

}
