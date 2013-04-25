import java.util.*;
import java.lang.*;
import java.io.*;

/* For the family tree problem, I used a pre-built barebones family tree graph using Person objects
 * modeled after my own family tree for testing purposes of the search by name and generation algorithm.
 * I implemented a breadth first search on the graph to find all family members that matched the name and/or
 * generation specified by the user
 */



/*Person object class used to represent family members in the family tree
 *Only implemented fields that  were neccesary for the breadth-first search
 *For the purposes of this algorithm, son and daughter-in-laws are considered the same as natural children
 */


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
		this.name = name.toLowerCase();
		this.visited = false;
		this.generation = 0;
		this.children = new  ArrayList<Person>();
	}

}
/*Generic Queue class 
 *Used for the breadth-first search
 */

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

public class Problem4{
	public static void main(String[] args){
		
		/*A pre-built family tree modeled after my own
		 * For the purposes of the breadth-first search, I made the oldest generation
		 *children of the root of the tree in order to have a constant starting point 
		 *search
		*/
		
		//generation 0
		Person root = new Person("root");
		
		//generation 1
		Person blanca = new Person("blanca");
		Person eguardo = new Person("eguardo");
		
		Person may = new Person("may");
		Person jake = new Person("jake");
		
		//generation 2
		Person wilfredo = new Person("wilfredo");
		Person mimi = new Person("mimi");
		
		Person elsi = new Person("elsi");
		Person eddy = new Person("eddy");

		Person tony = new Person("tony");
		Person denise = new Person("denise");

		Person lyn = new Person("lyn");
		Person piggie = new Person("piggie");  //yes my uncle's name is piggie
		
		//generation 3
		Person manny = new Person("manny");
		Person may2 = new Person("may");

		Person tito = new Person("tito");
		Person diego = new Person("diego");
		Person mary = new Person("mary");

		Person aja = new Person("aja");
		Person desiree = new Person("desiree");
		
		//generation 4
		Person titoJR = new Person("tito");
		Person maryJR = new Person("mary");

		//Assigning the parent/children relationships

		//generations 0 relations
		root.children.add(blanca);
		root.children.add(eguardo);
		root.children.add(may);
		root.children.add(jake);

		//generation  1 parents
		blanca.children.add(wilfredo);
		blanca.children.add(mimi);
		blanca.children.add(elsi);
		blanca.children.add(eddy);
		blanca.children.add(tony);
		blanca.children.add(denise);
		eguardo.children = blanca.children;

		may.children.add(tony);
		may.children.add(denise);
		may.children.add(lyn);
		may.children.add(piggie);
		jake.children = may.children;

		//generation 2 parents
		elsi.children.add(desiree);
		eddy.children  = elsi.children;

		mimi.children.add(diego);
		mimi.children.add(tito);
		mimi.children.add(mary);
		wilfredo.children = mimi.children;
		
		lyn.children.add(aja);
		piggie.children = lyn.children;

		denise.children.add(manny);
		denise.children.add(may2);
		tony.children = denise.children;
		
		//generation 3 parents
		mary.children.add(maryJR);
		mary.children.add(titoJR);
		tito.children = mary.children;

		//Start of menu/user input

		Scanner sc;
		String option = "";  
		String name = "";
		int genNum = 0;
		while(true){
			System.out.println("Enter number for search option");
			System.out.println("(1): search by name");
			System.out.println("(2): search by generation");
			System.out.println("(3): search by name and generation");
			sc = new Scanner(System.in);
			option = sc.nextLine();
			if(option.equals("1")){
				System.out.println("enter name");
				sc =  new Scanner(System.in);
				name = sc.nextLine().toLowerCase();
				break;
			}else if(option.equals("2")){
				System.out.println("enter generation");
				sc = new Scanner(System.in);
				genNum = sc.nextInt();
				break;
			}else if(option.equals("3")){
				System.out.println("enter name");
				sc = new Scanner(System.in);
				name = sc.nextLine().toLowerCase();
				
				System.out.println("enter generation");
				sc = new Scanner(System.in);
				genNum = sc.nextInt();
				break;

			}else{
				System.out.println("Not a valid option. Pick option 1, 2, or 3");
				System.out.println("");
			}
		}
		
		//beginning of breadth-first search on familiy tree
		//traverses through all family members, storing those that match by name and/or generation in results arrayList
		ArrayList<Person> results = new ArrayList<Person>();
		Queue<Person> q = new Queue<Person>();
		q.enqueue(root);
		root.visited = true;
		while(!q.isEmpty()){
			Person ancestor = q.dequeue();
			if(option.equals("1") && ancestor.name.equals(name)){
				results.add(ancestor);
			}else if(option.equals("2") && ancestor.generation == genNum){
				results.add(ancestor);
			}else if(option.equals("3") && ancestor.name.equals(name) && ancestor.generation == genNum){
				results.add(ancestor);
			}

			for(int i = 0; i<ancestor.children.size(); i++){
				Person relative = ancestor.children.get(i);
				if(!relative.visited){
					relative.generation = ancestor.generation+1; //keeps track of the current generation as steps away from root
					relative.visited = true;
					q.enqueue(relative);
				}
			}			
		}

		//no familiy members found 
		if(results.isEmpty()){
			System.out.println("No results found in the family tree");
		}else{   //custom print-out based on option chosed at start
			if(option.equals("1")){
				System.out.println("Family members with the name "+name);
			}else if (option.equals("2")){
				System.out.println("Family members of the " + genNum + " generation");
			}else if(option.equals("3")){
				System.out.println("Family members with the name  " +  name + " and of the " + genNum + " generation");  
			}

			//prints all family members that match the search preferences
			for(int i = 0; i<results.size(); i++){
				System.out.println("name: " + results.get(i).name  + " / genertaion: " +results.get(i).generation);
			}
		}


	}

}
